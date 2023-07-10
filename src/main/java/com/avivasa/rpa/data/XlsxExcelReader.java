package com.avivasa.rpa.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class XlsxExcelReader
{

	private List<List<XlsxExcelReader.Row>> allSheetData;
	private List<XlsxExcelReader.Row> excelData;
	private int minColumns;
	private String excelFile;

	private List<String> sheetNames;

	public XlsxExcelReader( String excelFile )
	{
		this.excelFile = excelFile;
		this.sheetNames = new ArrayList<>( );
	}

	public List<List<XlsxExcelReader.Row>> readExcel( ) throws Exception
	{
		String sheetName = "";

		File xlsxFile = new File( excelFile );
		if( !xlsxFile.exists( ) )
		{
			throw new FileNotFoundException( "Not found or not a file: " + xlsxFile.getPath( ) );
		}

		try( OPCPackage opcPackage = OPCPackage.open( xlsxFile.getPath( ), PackageAccess.READ ) )
		{

			ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable( opcPackage );
			XSSFReader xssfReader = new XSSFReader( opcPackage );
			StylesTable styles = xssfReader.getStylesTable( );
			XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData( );

			allSheetData = new ArrayList<>( );

			while( iter.hasNext( ) )
			{
				try( InputStream stream = iter.next( ) )
				{
					sheetName = iter.getSheetName( );
					sheetNames.add( sheetName );
					excelData = new ArrayList<>( );

					DataFormatter formatter = new DataFormatter( );
					InputSource sheetSource = new InputSource( stream );

					try
					{
						XMLReader sheetParser = SAXHelper.newXMLReader( );
						ContentHandler handler = new XSSFSheetXMLHandler( styles, null, strings, new SheetToCSV( ),
								formatter, false );
						sheetParser.setContentHandler( handler );
						sheetParser.parse( sheetSource );
					}catch( ParserConfigurationException e )
					{
						throw new RuntimeException( "SAX parser appears to be broken - " + e.getMessage( ) );
					}
				}

				allSheetData.add( excelData );
			}
		}

		return allSheetData;
	}

	public List<List<String>> excelToList( List<XlsxExcelReader.Row> sheetData )
	{
		List<List<String>> excelList = new ArrayList<>( );

		try
		{
			sheetData.forEach( row -> {

				List<String> listRow = new ArrayList<>( );

				row.getCells( ).forEach( cell -> {

					if( cell.getType( ) == CellType.NUMERIC )
					{
						DecimalFormat df = new DecimalFormat( "#.##" ); // Check it for rounding numeric values
						listRow.add( String.valueOf( String.valueOf( df.format( cell.getNumericCellValue( ) ) ) ) );

					}else if( cell.getType( ) == CellType.BOOLEAN )
					{
						listRow.add( String.valueOf( cell.getBooleanCellValue( ) ) );

					}else if( cell.getType( ) == CellType.STRING )
					{
						listRow.add( cell.getStringCellValue( ) );

					}else
					{
						listRow.add( cell.getStringCellValue( ) );
					}

				} );

				excelList.add( listRow );

			} );

		}catch( Exception e )
		{
			e.printStackTrace( );
		}

		return excelList;
	}

	private class SheetToCSV implements SheetContentsHandler
	{
		XlsxExcelReader.Row rowData;
		private int currentRow = -1;
		private int currentCol = -1;

		@Override
		public void startRow( int rowNum )
		{
			rowData = new XlsxExcelReader.Row( );
			currentRow = rowNum;
			currentCol = -1;
		}

		@Override
		public void endRow( int rowNum )
		{
			for( int i = currentCol; i < minColumns; i++ )
			{
				rowData.addCell( getBlankCell( ) );
			}
			excelData.add( rowData );
		}

		@Override
		public void cell( String cellReference, String formattedValue, XSSFComment comment )
		{
			if( cellReference == null )
			{
				cellReference = new CellAddress( currentRow, currentCol ).formatAsString( );
			}

			int thisCol = (new CellReference( cellReference )).getCol( );
			int missedCols = thisCol - currentCol - 1;
			for( int i = 0; i < missedCols; i++ )
			{
				rowData.addCell( getBlankCell( ) );
			}
			currentCol = thisCol;

			if( minColumns < currentCol )
			{
				minColumns = currentCol;
			}
			rowData.addCell( getCell( formattedValue ) );

		}

		@Override
		public void headerFooter( String text, boolean isHeader, String tagName )
		{
			// codes for reading header/footer if required
		}

		private XlsxExcelReader.Cell getCell( String formattedValue )
		{

			XlsxExcelReader.Cell cell;

			if( isNumeric( formattedValue ) )
			{
				cell = new XlsxExcelReader.Cell( CellType.NUMERIC, Double.parseDouble( formattedValue ) );
			}else if( isBoolean( formattedValue ) )
			{
				cell = new XlsxExcelReader.Cell( CellType.BOOLEAN, Boolean.parseBoolean( formattedValue ) );
			}else
			{
				cell = new XlsxExcelReader.Cell( CellType.STRING, formattedValue );
			}

			return cell;
		}

		private boolean isNumeric( String value )
		{
			try
			{
				Double.parseDouble( value );
				return true;
			}catch( NumberFormatException e )
			{
				return false;
			}
		}

		private boolean isBoolean( String value )
		{
			return value != null
					&& Arrays.stream( new String[] { "true", "false" } ).anyMatch( b -> b.equalsIgnoreCase( value ) );
		}

		private XlsxExcelReader.Cell getBlankCell( )
		{
			return new XlsxExcelReader.Cell( CellType.BLANK, null );
		}
	}

	public class Row
	{
		private List<XlsxExcelReader.Cell> cells;

		public Row( )
		{
			this.cells = new ArrayList<>( );
		}

		public List<XlsxExcelReader.Cell> getCells( )
		{
			return cells;
		}

		public void addCell( XlsxExcelReader.Cell cell )
		{
			cells.add( cell );
		}
	}

	public class Cell
	{
		private CellType type;
		private Object data;

		public Cell( CellType type, Object data )
		{
			super( );
			this.type = type;
			this.data = data;
		}

		public CellType getType( )
		{
			return type;
		}

		public String getStringCellValue( )
		{
			return (String) data;
		}

		public double getNumericCellValue( )
		{
			return (double) data;
		}

		public boolean getBooleanCellValue( )
		{
			return (boolean) data;
		}

		public Date getDateCellValue( )
		{
			return (Date) data;
		}
	}

	public List<String> getSheetNames( )
	{
		return sheetNames;
	}

	public void setSheetNames( List<String> sheetNames )
	{
		this.sheetNames = sheetNames;
	}

}