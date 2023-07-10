package com.avivasa.rpa.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.avivasa.rpa.model.AvivasaComputersModel;
import com.avivasa.rpa.model.MCafeeModel;


public class MCafeeReportsDTO {
	
	public void createMCafeeReportExcel(  List<MCafeeModel> contentReportList, String filePath, String fileName   )
	{
		try
		{
			File file = new File( filePath + fileName);

			if( !file.exists( ) )
			{
				file.createNewFile( );
			}

			Workbook workbook = new XSSFWorkbook( );

			Sheet sheetContentReport = workbook.createSheet( "MCafee de var Agesa da Yok" );

		
			Row rowContentReportData = sheetContentReport.createRow( 0 );

			Cell cell0 = rowContentReportData.createCell( 0 );
			Cell cell1 = rowContentReportData.createCell( 1 );
						
			cell0.setCellValue( "Last Communication" );
			cell1.setCellValue( "System Name" );
	

			for( int i = 0; i < contentReportList.size( ); i++ )
			{
				MCafeeModel tmpReportDTO = contentReportList.get( i );

				Row row = sheetContentReport.createRow( 1 + i );

				Cell cell15 = row.createCell( 0 );
				cell15.setCellValue( tmpReportDTO.getLastCommunication() );
				
				Cell cell16 = row.createCell( 1 );
				cell16.setCellValue( tmpReportDTO.getSystemName() );

				//sheetContentReport.autoSizeColumn( i + 1 );

			}
			
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream( file  );
			workbook.write( fileOut );
			fileOut.close( );

			// Closing the workbook
			workbook.close( );
		}catch( FileNotFoundException e )
		{
			e.printStackTrace( );
		}catch( IOException e )
		{
			e.printStackTrace( );
		}
	}
	
	public void appendListToExcel(  List<AvivasaComputersModel> contentReportList, String filePath, String fileName  )
	{
		File file = null;

		try
		{
			file = new File( filePath + fileName );
			
			Workbook workbook = new XSSFWorkbook( file );
			
			Sheet sheetContentReport1 = workbook.createSheet( "Agesa da var MCafee de Yok" );
			Row rowContentReportData1 = sheetContentReport1.createRow( 0 );

			Cell cell00 = rowContentReportData1.createCell( 0 );
			cell00.setCellValue( "Name" );
						
			for( int i = 0; i < contentReportList.size( ); i++ )
			{
				AvivasaComputersModel tmpReportDTO = contentReportList.get( i );

				Row row = sheetContentReport1.createRow( i + 1 );   // first row is header so index start i + 1

				Cell cell04 = row.createCell( 0 );
				cell04.setCellValue( tmpReportDTO.getName() );
				
				//sheetContentReport.autoSizeColumn( i + 1 );

			}
			
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream( file, true );
			workbook.write( fileOut );
			fileOut.close( );

			// Closing the workbook
			workbook.close( );
			
		} catch (InvalidFormatException | IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
