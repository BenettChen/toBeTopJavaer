package com.benett.utils;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

public class SeriUtils {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = Logger.getLogger(SeriUtils.class);

    public static <T> T readValue(String src) throws Exception {
        T result = null;
        try {
            result = mapper.readValue(src, new TypeReference<T>() {
            });
        } catch (Exception e) {
        	logger.error("反序列化错误 src = " + src, e);
        }
        return result;
    }

    public static <T> T readListValue(String src, Class<T> lstClazz, Class<?> innerClazz) throws Exception {
        T result = null;
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(lstClazz, innerClazz);
            result = mapper.readValue(src, javaType);
        } catch (Exception e) {
        	logger.error("反序列化错误 src = " + src, e);
        }
        return result;
    }

    public static <T> T readValue(String src, Class<T> clazz) throws Exception {
        T result = null;
        try {
            result = mapper.readValue(src, clazz);
        } catch (Exception e) {
        	logger.error("反序列化错误 src = " + src, e);
        }
        return result;
    }

    public static String writeObject(Object obj) {
        String result = "";
        try {
            result = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        	logger.error("序列化错误 class = " + obj.getClass().getName(), e);
        }
        return result;
    }


}