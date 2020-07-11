package com.benett.utils;

import com.benett.company.NonTjfromReuqest;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Created by chenpeng
 * @Date 2020/7/10
 * @Description
 */
public class FileUtils{

	private static Set<NonTjfromReuqest> nonTjfromReuqests = new HashSet<NonTjfromReuqest>( 1000 );

	public static void main( String[] args ){

				getNonTjfromRequests();
				for( NonTjfromReuqest reuqest : nonTjfromReuqests ) {
					System.out.println(reuqest);
				}
				System.out.println(nonTjfromReuqests.size());
				write();
//		String replace = replace( "https://haikou.58.com/tech/{id}x.shtml?PGTID=d--b-e-ccabca&ClickID=" );
//		System.out.println(replace);
	}

	private static void getNonTjfromRequests(){

		File file = new File( "/Users/benettchen/Desktop/tjfrom/nonTjfromRequest.log" );
		if( file.isFile() && file.exists() ){
			try{
				InputStreamReader inputStreamReader = new InputStreamReader( new FileInputStream( file ), "utf-8" );
				BufferedReader bufferReader = new BufferedReader( inputStreamReader );
				String lineStr = null;
				while( ( lineStr = bufferReader.readLine() ) != null ){
					NonTjfromReuqest nonTjfromReuqest = SeriUtils.readValue( lineStr, NonTjfromReuqest.class );
					String referer = nonTjfromReuqest.getReferer();
					nonTjfromReuqest.setReferer( replace( referer ) );
					nonTjfromReuqests.add( nonTjfromReuqest );

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
	}


	private static void write(){

		BufferedWriter out = null;
		OutputStreamWriter osw = null;
		File file = null;
		try{
			String filePath = "/Users/benettchen/Desktop/diffTjfrom/nonTjfromRequest.txt";
			file = new File( filePath );
			osw = new OutputStreamWriter( new FileOutputStream( file, true ), "UTF-8" );
			out = new BufferedWriter( osw );
			for( NonTjfromReuqest reuqest: nonTjfromReuqests ) {
				osw.write( reuqest.toString() );

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

	public static String replace( String request ){


		request = request.replaceAll( "58.com", "wby" );
		Pattern p2 = Pattern.compile( "[0-9]" ); // 只允数字
		Matcher m = p2.matcher( request );
		//替换与模式匹配的所有字符（即非数字的字符将被""替换）
		String trim = m.replaceAll( "*" ).trim();
		trim = trim.replaceAll( "wby", "58.com" );
		trim = trim.replaceFirst( "\\*", "{id}" );
		trim = trim.replaceAll( "\\*", "" );

		try{
			int i = trim.indexOf( "?" );
			trim = trim.substring( 0, i );
		} catch( Exception e ){

		}

		return trim;
	}
}

