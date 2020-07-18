package com.benett.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

/**
 * @Created by chenpeng
 * @Date 2020/7/10
 * @Description
 */
public class FileUtils{

	public static List<String> getFileLines( String filePath ){

		List<String> contents = Lists.newLinkedList();
		File file = new File( filePath );
		if( file.isFile() && file.exists() ){
			try{
				InputStreamReader inputStreamReader = new InputStreamReader( new FileInputStream( file ), StandardCharsets.UTF_8 );
				BufferedReader bufferReader = new BufferedReader( inputStreamReader );
				String lineStr;
				while( ( lineStr = bufferReader.readLine() ) != null ){
					contents.add( lineStr + "\n" );
				}
				bufferReader.close();
				inputStreamReader.close();
			}
			catch( Exception e ){
				System.out.println( "file catch unsupported encoding!" );
				e.printStackTrace();
			}
		}
		else{
			System.out.println( "file is not a file or file is not existing!" );
		}
		return contents;
	}

	public static <T> void write( String filePath, Set<T> nonTjfromReuqests ){

		BufferedWriter out = null;
		OutputStreamWriter osw = null;
		File file;
		creatDirFile( filePath );
		try{
			file = new File( filePath );
			osw = new OutputStreamWriter( new FileOutputStream( file, true ), StandardCharsets.UTF_8 );
			out = new BufferedWriter( osw );
			for( T request : nonTjfromReuqests ){
				osw.write( request.toString() );
			}
			osw.flush();
		}
		catch( Exception e ){
			e.printStackTrace();
		}
		finally{
			try{
				if( osw != null ){
					osw.close();
				}
				if( out != null ){
					out.close();
				}
			}
			catch( IOException e ){
				e.printStackTrace();
			}
		}
	}

	private static void creatDirFile( String filePath ){

		int i = filePath.lastIndexOf( "/" );
		String dirFile = filePath.substring( 0, i );
		File file = new File( dirFile );
		if( !file.exists() ){
			file.mkdirs();
		}
	}

	public static void clearInfoForFile( String fileName ){

		File file = new File( fileName );
		try{
			if( !file.exists() ){
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter( file );
			fileWriter.write( "" );
			fileWriter.flush();
			fileWriter.close();
		}
		catch( IOException e ){
			e.printStackTrace();
		}
	}

	public static void deleteFile( String fileName ){
		File file = new File( fileName );
		if( file.isFile() ){
			file.delete();
		}
	}

	public static  void appendFileContent(List<String> sourceFilePaths, String targetFilePath) {

		Set<String> contents = Sets.newHashSet();
		for( String filePath : sourceFilePaths ) {
			List<String> fileLines = getFileLines( filePath );
			contents.addAll( fileLines );
		}
		deleteFile(targetFilePath);
		write(targetFilePath, contents);
	}

}

