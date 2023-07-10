package com.avivasa.rpa.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.util.file.FilenamePrefixFilter;

import com.avivasa.rpa.data.KPIAjanlariHesaplamalariData;
import com.avivasa.rpa.utiliy.log;

public class FileUtil
{
	public static void createDir( String dir, boolean ignoreIfExitst ) throws IOException
	{
		File file = new File(dir);

		if( ignoreIfExitst && file.exists( ) )
		{
			return;
		}

		if( file.mkdir() == false )
		{
			throw new IOException( "Cannot create the directory = " + dir );
		}
	}
	
	public static void listFilesOfFolder( String directoryName, List<File> files )
	{
		File directory = new File( directoryName );

		// Get all files from a directory.
		File[] fList = directory.listFiles( );
		
		if( fList != null )
		{
			for( File file : fList )
			{
				if( file.isFile( ) )
				{
					files.add( file );
					
				}else if( file.isDirectory( ) && !file.getAbsolutePath().contains("Backup") )
				{
					listFilesOfFolder( file.getAbsolutePath( ), files );
				}
			}
		}
	}
	
	
	public static List<String> getPathOfFileList( List<File> files )
	{
		List<String> fileNames = new ArrayList<>();
		
		for(int i = 0; i < files.size(); i++) 
		{
			fileNames.add( files.get(i).getAbsolutePath( ) ) ;
		}
		
		return fileNames ;
	}
	
	public static List<String> getNameOfFileList( List<File> files )
	{
		List<String> fileNames = new ArrayList<>();
		
		for(int i = 0; i < files.size(); i++) 
		{
			fileNames.add( files.get(i).getName()) ;
		}
		
		return fileNames ;
	}
	
	public static String getFilePrefix( String fileName )
	{
		String yearAndMonthStr = StringUtil.dateYearAndMonthToString( ) ;
		
		fileName = fileName.replace( yearAndMonthStr, "") ;
		
		String prefix = fileName.substring( fileName.indexOf("_") + 1, fileName.indexOf(".") );
		
		return prefix ;
	}
		
	public static void removeDataFiles( List<File> files, List<String> fileNames )
	{
		for( int i = 0; i < fileNames.size( ); i++ )
		{
			files.remove( new File( KPIAjanlariHesaplamalariData.COMMON_PATH + fileNames.get( i ) ) );
		}

	}
	
	public static String splitPartajNo( String fileName )
	{
		return fileName.split( "_" )[ 0 ] ;
	}
	
	
	public static String getFileNames( List<String> fileNames, String fileNamePrefix )
	{	
		String fileName = "";
		for (String currFileName : fileNames)	{
			
			if(currFileName.startsWith("Computers"))
				fileName = currFileName;
			else 
				if (currFileName.contains(fileNamePrefix))
					fileName = currFileName;
			}	
		
		return fileName;
	}
	
	public static List<String>  getResultsTrendMonthlyFileNames( List<String> fileNames )
	{
		List<String> resultsTrendMonthlyFileNames = new ArrayList<>( );
		
		for (String currFileName : fileNames)
		{
			if (currFileName.contains("ResultsTrendMonthlyTab") )
			{
				resultsTrendMonthlyFileNames.add( currFileName );
				
			}	
		}
		
		return resultsTrendMonthlyFileNames;
	}
	
	 public static void copyAndMoveFiles( String reportName ) 
	   {
		   String filePath = KPIAjanlariHesaplamalariData.COMMON_PATH + "Backup\\"; 
	   	   String getDate = StringUtil.getDate();
	   	   String getTime = StringUtil.getTime();
	   	   
	   	   String getDateTime = getDate +" "+ getTime;
	   	   String backupFolderDate = filePath + getDate;
	   	   String backupFolderDateTime = filePath + getDate +"\\"+getTime;

	   	   new File(backupFolderDate).mkdir( );
	   	   new File(backupFolderDateTime).mkdir( );
	   	   
	   	 copyMoveDeleteToFile( KPIAjanlariHesaplamalariData.COMMON_PATH,  reportName, getDateTime, backupFolderDateTime);
	   }
	   
	   public static void copyMoveDeleteToFile(String filePath, String fileName, String nowDateTime, String backupFolder) 
	   {
		   File file = new File(filePath + fileName); 
			  
	       // renaming the file and moving it to a new location 
	       if( file.renameTo( new File( backupFolder+ "\\" +nowDateTime +" "+ fileName ) ) )  
	       { 
	           // if file copied successfully then delete the original file 
	           file.delete( ); 
	           log.info("File moved successfully. " + filePath + fileName );
	       } 
	       else 
	       { 
	       	   log.info("Failed to move the file. " + filePath + fileName ); 
	       } 
		}

}
