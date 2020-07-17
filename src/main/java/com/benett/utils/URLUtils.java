package com.benett.utils;

import java.nio.charset.StandardCharsets;

/**
 * @Created by chenpeng
 * @Date 2020/7/17
 * @Description
 */
public class URLUtils{

	public static String decodeUrl( String url ){

		String[] split = url.split( "%" );
		StringBuilder sb = new StringBuilder( split[ 0 ] );
		for( int i = 1; i < split.length; i++ ){
			String subStr = split[ i ];
			String hexString = subStr.substring( 0, 2 );
			String endStr = subStr.substring( 2 );
			String str = hexStringToString( hexString );
			sb.append( str ).append( endStr );
		}
		return sb.toString();
	}

	private static String hexStringToString( String s ){

		if( s == null || s.equals( "" ) ){
			return null;
		}
		s = s.replace( " ", "" );
		byte[] baKeyword = new byte[ s.length() / 2 ];
		for( int i = 0; i < baKeyword.length; i++ ){
			try{
				baKeyword[ i ] = ( byte )( 0xff & Integer.parseInt( s.substring( i * 2, i * 2 + 2 ), 16 ) );
			}
			catch( Exception e ){
				e.printStackTrace();
			}
		}
		try{
			s = new String( baKeyword, StandardCharsets.UTF_8 );
		}
		catch( Exception e1 ){
			e1.printStackTrace();
		}
		return s;
	}
}
