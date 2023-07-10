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
import com.avivasa.rpa.model.CiscoModel;


public class CiscoReportsDTO {
	
	public void createCiscoReportExcel(  List<CiscoModel> contentReportList, String filePath, String fileName   )
	{
		try
		{
			File file = new File( filePath + fileName);

			if( !file.exists( ) )
			{
				file.createNewFile( );
			}

			Workbook workbook = new XSSFWorkbook( );

			Sheet sheetContentReport = workbook.createSheet( "Cisco da var Agesa da Yok" );

		
			Row rowContentReportData = sheetContentReport.createRow( 0 );

			Cell cell0 = rowContentReportData.createCell( 0 );
			Cell cell1 = rowContentReportData.createCell( 1 );
			Cell cell2 = rowContentReportData.createCell( 2 );
			Cell cell3 = rowContentReportData.createCell( 3 );
			Cell cell4 = rowContentReportData.createCell( 4 );
			Cell cell5 = rowContentReportData.createCell( 5 );
			Cell cell6 = rowContentReportData.createCell( 6 );
			Cell cell7 = rowContentReportData.createCell( 7 );
			Cell cell8 = rowContentReportData.createCell( 8 );
			Cell cell9 = rowContentReportData.createCell( 9 );
			Cell cell10 = rowContentReportData.createCell( 10 );
			Cell cell11 = rowContentReportData.createCell( 11 );
			Cell cell12 = rowContentReportData.createCell( 12 );
			Cell cell13 = rowContentReportData.createCell( 13 );
			Cell cell14 = rowContentReportData.createCell( 14 );

			
			cell0.setCellValue( "Connector GUID" );
			cell1.setCellValue( "Hostname" );
			cell2.setCellValue( "Operating System" );
			cell3.setCellValue( "Connector Version" );
			cell4.setCellValue( "Group" );
			cell5.setCellValue( "Install Date" );
			cell6.setCellValue( "Last Seen" );
			cell7.setCellValue( "Internal IP" );
			cell8.setCellValue( "External IP" );
			cell9.setCellValue( "MAC Adresses" );
			cell10.setCellValue( "IOS Serial Number" );
			cell11.setCellValue( "Connector Antivirus" );
			cell12.setCellValue( "Last Definitons Update" );
			cell13.setCellValue( "Last Definitons Update Failure" );
			cell14.setCellValue( "Process Hardware Identifier" );
	

			for( int i = 0; i < contentReportList.size( ); i++ )
			{
				CiscoModel tmpReportDTO = contentReportList.get( i );

				Row row = sheetContentReport.createRow( 1 + i );

				Cell cell15 = row.createCell( 0 );
				cell15.setCellValue( tmpReportDTO.getConnector_Guid() );
				
				Cell cell16 = row.createCell( 1 );
				cell16.setCellValue( tmpReportDTO.getHostName() );
				
				Cell cell17 = row.createCell( 2 );
				cell17.setCellValue( tmpReportDTO.getOperating_System() );
				
				Cell cell18 = row.createCell( 3 );
				cell18.setCellValue( tmpReportDTO.getConnector_Version() );

				Cell cell19 = row.createCell( 4 );
				cell19.setCellValue( tmpReportDTO.getGroup() );

				Cell cell20 = row.createCell( 5 );
				cell20.setCellValue( tmpReportDTO.getInstallDate() );

				Cell cell21 = row.createCell( 6 );
				cell21.setCellValue( tmpReportDTO.getLastSeen() );

				Cell cell22 = row.createCell( 7 );
				cell22.setCellValue( tmpReportDTO.getInternal_IP() );
				
				Cell cell23 = row.createCell( 8 );
				cell23.setCellValue( tmpReportDTO.getExternal_IP() );
				
				Cell cell24 = row.createCell( 9 );
				cell24.setCellValue( tmpReportDTO.getAdress_MAC() );
				
				Cell cell25 = row.createCell( 10 );
				cell25.setCellValue( tmpReportDTO.getIos_Serial_Number() );
				
				Cell cell26 = row.createCell( 11 );
				cell26.setCellValue( tmpReportDTO.getConnector_Antivirus() );
				
				Cell cell27 = row.createCell( 12 );
				cell27.setCellValue( tmpReportDTO.getLast_Definiton_Update() );
				
				Cell cell28 = row.createCell( 13 );
				cell28.setCellValue( tmpReportDTO.getLast_Definiton_Update_Failure() );
				
				Cell cell29 = row.createCell( 14 );
				cell29.setCellValue( tmpReportDTO.getProcess_Hardware_Identifier() );
				
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
			
			Sheet sheetContentReport1 = workbook.createSheet( "Agesa da var Cisco da Yok" );
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
