package com.benett.javabase.java8;

import javafx.scene.input.DataFormat;

/**
 * @Created by chenpeng
 * @Date 2020/7/12
 * @Description
 */
public class ThreadLocalDemo{
	private static ThreadLocal<DataFormat> threadLocal = new ThreadLocal<>();

	public static void main( String[] args ){

		DataFormat dataFormat = new DataFormat();
		threadLocal.set( dataFormat );
		 dataFormat = threadLocal.get();

		ThreadLocal<DataFormat> dataFormatThreadLocal = ThreadLocal.withInitial( () -> new DataFormat() );
		DataFormat dataFormat1 = dataFormatThreadLocal.get();
	}
}
