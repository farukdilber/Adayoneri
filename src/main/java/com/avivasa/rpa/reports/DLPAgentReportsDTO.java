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
import com.avivasa.rpa.model.DLPAgentModel;

public class DLPAgentReportsDTO {
	
	public void createDLPAgentReportExcel(  List<DLPAgentModel> contentReportList, String filePath, String fileName   )
	{
		try
		{
			File file = new File( filePath + fileName);

			if( !file.exists( ) )
			{
				file.createNewFile( );
			}

			Workbook workbook = new XSSFWorkbook( );

			Sheet sheetContentReport = workbook.createSheet( "DLP de var Agesa da Yok" );

		
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
			
			cell0.setCellValue( "Status" );
			cell1.setCellValue( "Machine Name" );
			cell2.setCellValue( "User Name" );
			cell3.setCellValue( "OS" );
			cell4.setCellValue( "Platform" );
			cell5.setCellValue( "Recent Error Messages" );
			cell6.setCellValue( "Endpoint Server" );
			cell7.setCellValue( "IP" );
			cell8.setCellValue( "Version" );
			cell9.setCellValue( "Connection" );
			cell10.setCellValue( "Last Update Received" );
			
	

			for( int i = 0; i < contentReportList.size( ); i++ )
			{
				DLPAgentModel tmpReportDTO = contentReportList.get( i );

				Row row = sheetContentReport.createRow( 1 + i );

				Cell cell15 = row.createCell( 0 );
				cell15.setCellValue( tmpReportDTO.getStatus() );
				
				Cell cell16 = row.createCell( 1 );
				cell16.setCellValue( tmpReportDTO.getMachineName() );
				
				Cell cell17 = row.createCell( 2 );
				cell17.setCellValue( tmpReportDTO.getUserName() );
				
				Cell cell18 = row.createCell( 3 );
				cell18.setCellValue( tmpReportDTO.getoS() );

				Cell cell19 = row.createCell( 4 );
				cell19.setCellValue( tmpReportDTO.getPlatform() );

				Cell cell20 = row.createCell( 5 );
				cell20.setCellValue( tmpReportDTO.getRecentErrorMessages() );

				Cell cell21 = row.createCell( 6 );
				cell21.setCellValue( tmpReportDTO.getEndpointServer() );

				Cell cell22 = row.createCell( 7 );
				cell22.setCellValue( tmpReportDTO.getAdress_IP() );
				
				Cell cell23 = row.createCell( 8 );
				cell23.setCellValue( tmpReportDTO.getVersion() );
				
				Cell cell24 = row.createCell( 9 );
				cell24.setCellValue( tmpReportDTO.getConnecttion() );
				
				Cell cell25 = row.createCell( 10 );
				cell25.setCellValue( tmpReportDTO.getLastUpdateReceived() );
				
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
			
			Sheet sheetContentReport1 = workbook.createSheet( "Agesa da var DLP de Yok" );
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
