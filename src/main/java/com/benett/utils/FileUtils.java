package com.benett.utils;

import com.benett.company.NonTjfromReuqest;
import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

	public static void main( String[] args ){



	}

	public static List<String> getFileLines( String filePath ){

		List<String> contents = Lists.newLinkedList();
		File file = new File( filePath );
		if( file.isFile() && file.exists() ){
			try{
				InputStreamReader inputStreamReader = new InputStreamReader( new FileInputStream( file ), "utf-8" );
				BufferedReader bufferReader = new BufferedReader( inputStreamReader );
				String lineStr;
				while( ( lineStr = bufferReader.readLine() ) != null ){
					contents.add( lineStr );
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

	public static void write( String filePath, Set<NonTjfromReuqest> nonTjfromReuqests ){

		BufferedWriter out = null;
		OutputStreamWriter osw = null;
		File file;
		try{
			file = new File( filePath );
			osw = new OutputStreamWriter( new FileOutputStream( file, true ), StandardCharsets.UTF_8 );
			out = new BufferedWriter( osw );
			for( NonTjfromReuqest request : nonTjfromReuqests ){
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

}

