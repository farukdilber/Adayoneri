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
import com.avivasa.rpa.model.KeepnetModel;

public class KeepnetReportsDTO {
	
	public void createKeepnetReportExcel(  List<KeepnetModel> contentReportList, String filePath, String fileName   )
    {
          try
          {
               File file = new File( filePath + fileName);

               if( !file.exists( ) )
               {
                    file.createNewFile( );
               }

               Workbook workbook = new XSSFWorkbook( );

               Sheet sheetContentReport = workbook.createSheet( "KeepNet de var Agesa da Yok" );

          
               Row rowContentReportData = sheetContentReport.createRow( 0 );

               Cell cell0 = rowContentReportData.createCell( 0 );
               Cell cell1 = rowContentReportData.createCell( 1 );
               Cell cell2 = rowContentReportData.createCell( 2 );
               Cell cell3 = rowContentReportData.createCell( 3 );
               Cell cell4 = rowContentReportData.createCell( 4 );
               Cell cell5 = rowContentReportData.createCell( 5 );
               Cell cell6 = rowContentReportData.createCell( 6 );
               Cell cell7 = rowContentReportData.createCell( 7 );

               
               cell0.setCellValue( "First Name" );
               cell1.setCellValue( "Last Name" );
               cell2.setCellValue( "E-Mail" );
               cell3.setCellValue( "Status" );
               cell4.setCellValue( "Last Seen" );
               cell5.setCellValue( "Diagnostic Tool" );
               cell6.setCellValue( "Device" );
               cell7.setCellValue( "Version" );

               for( int i = 0; i < contentReportList.size( ); i++ )
               {
                    KeepnetModel tmpReportDTO = contentReportList.get( i );

                    Row row = sheetContentReport.createRow( 1 + i );

                    Cell cell18 = row.createCell( 0 );
                    cell18.setCellValue( tmpReportDTO.getFirstName() );

                    Cell cell19 = row.createCell( 1 );
                    cell19.setCellValue( tmpReportDTO.getLastName() );

                    Cell cell20 = row.createCell( 2 );
                    cell20.setCellValue( tmpReportDTO.geteMail() );

                    Cell cell21 = row.createCell( 3 );
                    cell21.setCellValue( tmpReportDTO.getStatus());

                    Cell cell22 = row.createCell( 4 );
                    cell22.setCellValue( tmpReportDTO.getLastSeen());
                    
                    Cell cell23 = row.createCell( 5 );
                    cell23.setCellValue( tmpReportDTO.getDiagnosticTool() );
                    
                    Cell cell24 = row.createCell( 6 );
                    cell24.setCellValue( tmpReportDTO.getDevice() );
                    
                    Cell cell25 = row.createCell( 7 );
                    cell25.setCellValue( tmpReportDTO.getVersion() );                
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
			
			Sheet sheetContentReport1 = workbook.createSheet( "Agesa da var KeepNet de Yok" );
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
