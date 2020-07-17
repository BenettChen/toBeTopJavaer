package com.benett.company;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Created by chenpeng
 * @Date 2020/7/9
 * @Description
 */
public class NonTjfromReuqest implements Serializable{

	private static final long serialVersionUID = -6601903208557464574L;

	private String bizId;
	private String referer;
	private String secondaryReferer;
	private Long infoId;
	private Long userId;
	private String os;
	private String version;

	public String getBizId(){

		return bizId;
	}

	public void setBizId( String bizId ){

		this.bizId = bizId;
	}

	public String getReferer(){

		return referer;
	}

	public void setReferer( String referer ){

		this.referer = referer;
	}

	public Long getInfoId(){

		return infoId;
	}

	public void setInfoId( Long infoId ){

		this.infoId = infoId;
	}

	public Long getUserId(){

		return userId;
	}

	public void setUserId( Long userId ){

		this.userId = userId;
	}

	public String getOs(){

		return os;
	}

	public void setOs( String os ){

		this.os = os;
	}

	public String getVersion(){

		return version;
	}

	public void setVersion( String version ){

		this.version = version;
	}

	public String getSecondaryReferer(){

		return secondaryReferer;
	}

	public void setSecondaryReferer( String secondaryReferer ){

		this.secondaryReferer = secondaryReferer;
	}

	@Override
	public String toString(){

		return "NonTjfromReuqest{" + "bizId='" + bizId + '\'' + ", referer='" + referer + '\'' + ", secondaryReferer='" + secondaryReferer + '\'' + ", infoId=" + infoId + ", userId=" + userId + ", os='" + os + '\'' + ", version='" + version + '\'' + '}' + "\n";
	}

	@Override
	public boolean equals( Object o ){

		NonTjfromReuqest that = ( NonTjfromReuqest )o;
		String thisSecondCatalog = getSecondCatalog( secondaryReferer );
		String thatSecondCatalog = getSecondCatalog( that.getSecondaryReferer() );
		return thisSecondCatalog.equals( thatSecondCatalog );
	}

	//	@Override
	//	public boolean equals( Object o ){
	//
	//		NonTjfromReuqest that = ( NonTjfromReuqest )o;
	//		return Objects.equals( bizId, that.bizId ) && Objects.equals( referer, that.referer ) && Objects.equals( secondaryReferer, that.secondaryReferer );
	//	}

	@Override
	public int hashCode(){

		String thisSecondCatalog = getSecondCatalog( secondaryReferer );
		return Objects.hash( thisSecondCatalog );

	}

	private static String getSecondCatalog( String url ){

		try{
			String[] split = url.split( "/" );
			String domain = split[ 2 ];
			if( isSpecialDomain( domain, "qy", "webim" ) ){
				return domain;
			}
			//如果没带参数，并且二级域名是几个字母，返回几个*.58.com
			if( !url.contains( "?" ) ){
				return dealWithoutParams(domain);
			}
			String parseDomain = parseDomain( domain );
			if( isSpecialParams( url ) ){
				String urlParamNames = getUrlParamNames( url );
				return urlParamNames;
			}
			return parseDomain + "/" + split[ 3 ];
		}
		catch( Exception e ){
			System.out.println( "error -> url:" + url );
		}
		return url;
	}

	private static String dealWithoutParams( String domain ){

		String[] domainList = domain.split( "\\." );
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < domainList[ 0 ].length(); i++ ){
			sb.append( "*" );
		}
		return sb.toString() + "." + domainList[ 1 ] + "." + domainList[ 2 ];
	}

	private static String getUrlParamNames( String url ){
		//返回所以参数名
		StringBuilder paramNames = new StringBuilder();
		Set<String> paramSet = new TreeSet<>();
		String[] split = url.split( "\\?" );
		String params = split[ 1 ];
		String[] map = params.split( "&" );
		for( String str : map ){
			String[] kv = str.split( "=" );
			paramSet.add( kv[ 0 ] );
		}
		for( String k : paramSet ){
			paramNames.append( k ).append( "," );
		}
		System.out.println( paramNames.toString() );
		return paramNames.toString();
	}

	private static boolean isSpecialParams( String url ){

		List<String> list = Lists.newArrayList( "PGTID", "ClickID", "iuType" );
		for( String param : list ){
			if( url.contains( param ) ){
				return true;
			}
		}
		return false;
	}

	private static boolean isSpecialDomain( String domain, String... domainKeys ){

		for( String key : domainKeys ){
			boolean contains = domain.contains( key );
			if( contains ){
				return true;
			}
		}
		return false;
	}

	private static String parseDomain( String domain ){

		String[] domainList = domain.split( "\\." );
		return "**" + "." + domainList[ 1 ] + "." + domainList[ 2 ];
	}
}
