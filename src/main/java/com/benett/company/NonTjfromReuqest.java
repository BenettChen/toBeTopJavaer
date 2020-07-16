package com.benett.company;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Created by chenpeng
 * @Date 2020/7/9
 * @Description
 */
public class NonTjfromReuqest implements Serializable {
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

		return "NonTjfromReuqest{" + "bizId='" + bizId + '\'' + ", referer='" + referer + '\'' + ", secondaryReferer='" + secondaryReferer + '\'' + ", infoId=" + infoId + ", userId=" + userId + ", os='" + os + '\'' + ", version='" + version + '\'' + '}' +"\n";
	}

	@Override
	public boolean equals( Object o ){

		NonTjfromReuqest that = ( NonTjfromReuqest )o;
		return Objects.equals( bizId, that.bizId ) && Objects.equals( referer, that.referer ) && Objects.equals( secondaryReferer, that.secondaryReferer );
	}

	@Override
	public int hashCode(){

		return Objects.hash( bizId, referer, secondaryReferer );

	}
}
