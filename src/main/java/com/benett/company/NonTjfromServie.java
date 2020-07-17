package com.benett.company;

import com.benett.utils.FileUtils;
import com.benett.utils.SeriUtils;
import com.benett.utils.URLUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Created by chenpeng
 * @Date 2020/7/16
 * @Description
 */
public class NonTjfromServie{

	/**
	 * 1. 过滤域名和二级目录相同的请求
	 * 2. 视x.58.com为同一域名？
	 *
	 * @param args
	 */

	public static void main( String[] args ){

		NonTjfromServie nonTjfromServie = new NonTjfromServie();
		//		nonTjfromServie.dealWithNonTjfromRequest();
		nonTjfromServie.getSecondaryReferer();
	}

	private void getSecondaryReferer(){

		String writeFileName = "/Users/benettchen/Desktop/diffTjfrom/secondaryReferer.txt";
		List<String> nonTjfromRequests = FileUtils.getFileLines( "/Users/benettchen/Desktop/tjfrom/nonTjfromRequest.log" );
		Set<NonTjfromReuqest> nonTjfromReuqestSet = nonTjfromRequests.stream().map( request -> {
			NonTjfromReuqest nonTjfromReuqest = SeriUtils.readValue( request, NonTjfromReuqest.class );
			String secondaryReferer = nonTjfromReuqest.getSecondaryReferer();
			nonTjfromReuqest.setSecondaryReferer( replace( secondaryReferer ) );
			return nonTjfromReuqest;
		} ).collect( Collectors.toSet() );

		Set<String> secondaryRefererSet = nonTjfromReuqestSet.stream().map( NonTjfromReuqest::getSecondaryReferer ).collect( Collectors.toSet() );
		FileUtils.deleteFile( writeFileName );
		FileUtils.write( writeFileName, secondaryRefererSet );
		System.out.println(secondaryRefererSet.size());
	}

	private void dealWithNonTjfromRequest(){

		String writeFileName = "/Users/benettchen/Desktop/diffTjfrom/nonTjfromRequest.txt";
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

		FileUtils.clearInfoForFile( writeFileName );
		FileUtils.write( writeFileName, nonTjfromReuqestSet );

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
		if( request.contains( "path" ) /*|| request.contains( "from" )*/ ) {
			request = URLUtils.decodeUrl( request );
		}

		return request + "\n";
	}

	private void test(){

		List<String> list = new ArrayList<>();
		String sl = "https://info.vip.58.com/info/v1/index?PGTID=0d000000-0000-0402-efea-284bec957a3f&ClickID=1&r=0.8976429773553102";
		String s2 = "https://entdict.58.com/enterpriselibrary/index/detail/2?qid=34012410359&uid=44428227698451&from=2";
		String s3 = "https://entdict.58.com/enterpriselibrary/index/detail/1?frompage=list&qid=6349204501&PGTID=0d000000-0000-0dd5-7b97" + "-17a5d2d91e8a&ClickID=42";
		list.add( sl );
		list.add( s2 );
		list.add( s3 );
		for( String s : list ){

			System.out.println();
		}

		String s5 = "{\"bizId\":\"biz,pcBindV2\",\"referer\":\"https://jn.58.com/chaoshishangye/38726224174223x.shtml\"," + "\"secondaryReferer" + "\":\"https://jianli.58.com/resumecommon/deliveryrecord?r=0.3925512837360179\",\"infoId\":38726224174223,\"userId\":64560533421586,\"os\":\"\",\"version\":\"\"}\n";
		String s6 = "{\"bizId\":\"biz,pcBindV2\",\"referer\":\"https://qz.58.com/zptaobao/37257473933953x.shtml\"," + "\"secondaryReferer\":\"https://jianli.58.com/resumecommon/deliveryrecord?r=0.2026915475877431\",\"infoId\":37257473933953,\"userId\":39738344921872,\"os\":\"\",\"version\":\"\"}";

		String qy = "{\"bizId\":\"biz,pcBindV2\",\"referer\":\"https://cq.58.com/chaoshishangye/40221836017957x.shtml?PGTID=0d002408-1baa-a08e-7a27-e1c0e9e96541&ClickID=1\",\"secondaryReferer\":\"https://qy.58.com/mq/67584650148119/\",\"infoId\":40221836017957,\"userId\":70877215423503,\"os\":\"\",\"version\":\"\"}";
	}
}
