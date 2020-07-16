package com.benett.company;

import com.benett.utils.FileUtils;
import com.benett.utils.SeriUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Created by chenpeng
 * @Date 2020/7/16
 * @Description
 */
public class NonTjfromServie{

	public static void main( String[] args ){

		NonTjfromServie nonTjfromServie = new NonTjfromServie();
//		nonTjfromServie.dealWithNonTjfromRequest();
		nonTjfromServie.getSecondaryReferer();

	}

	private void getSecondaryReferer() {
		String writeFileName = "/Users/benettchen/Desktop/diffTjfrom/secondaryReferer.txt";
		List<String> nonTjfromRequests = FileUtils.getFileLines( "/Users/benettchen/Desktop/tjfrom/nonTjfromRequest.log" );
		Set<String> nonTjfromReuqestSet = nonTjfromRequests.stream().map( request -> {
			NonTjfromReuqest nonTjfromReuqest = SeriUtils.readValue( request, NonTjfromReuqest.class );
			String referer = nonTjfromReuqest.getReferer();
			nonTjfromReuqest.setReferer( replace( referer ) + "\n" );
			return nonTjfromReuqest.getSecondaryReferer();
		} ).collect( Collectors.toSet() );
		FileUtils.clearInfoForFile( writeFileName );
		FileUtils.write( writeFileName, nonTjfromReuqestSet );
	}

	private void dealWithNonTjfromRequest() {
		List<String> nonTjfromRequests = FileUtils.getFileLines( "/Users/benettchen/Desktop/tjfrom/nonTjfromRequest.log" );
		Set<NonTjfromReuqest> nonTjfromReuqestSet = nonTjfromRequests.stream().map( request -> {
			NonTjfromReuqest nonTjfromReuqest = SeriUtils.readValue( request, NonTjfromReuqest.class );
			String referer = nonTjfromReuqest.getReferer();
			nonTjfromReuqest.setReferer( replace( referer ) );
			return nonTjfromReuqest;
		} ).collect( Collectors.toSet() );

		for( NonTjfromReuqest reuqest : nonTjfromReuqestSet ){
			System.out.println( reuqest );
		}
		System.out.println( nonTjfromReuqestSet.size() );

		FileUtils.write( "/Users/benettchen/Desktop/diffTjfrom/nonTjfromRequest.txt", nonTjfromReuqestSet );

		//		String replace = replace( "https://xm.58.com/zpwentiyingshi/41602443252738x.shtml?PGTID=0d000000-0000-0492-ca5d-eea76794c634&ClickID=5" );
		//		System.out.println(replace);
	}

	public static String replace( String request ){

		//		request = request.replaceAll( "58.com", "wby" );
		//		Pattern p2 = Pattern.compile( "[0-9]" ); // 只允数字
		//		Matcher m = p2.matcher( request );
		//		//替换与模式匹配的所有字符（即非数字的字符将被""替换）
		//		String trim = m.replaceAll( "*" ).trim();
		//		trim = trim.replaceAll( "wby", "58.com" );
		//		trim = trim.replaceFirst( "\\*", "{id}" );
		//		trim = trim.replaceAll( "\\*", "" );
		//
		//		try{
		//			int i = trim.indexOf( "?" );
		//			trim = trim.substring( 0, i );
		//		} catch( Exception e ){
		//
		//		}

		return request;
	}
}
