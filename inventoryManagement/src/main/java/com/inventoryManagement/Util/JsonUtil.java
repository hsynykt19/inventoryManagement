package com.inventoryManagement.Util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    static ObjectWriter ow = (new ObjectMapper()).writer().withDefaultPrettyPrinter();
    static ObjectMapper mapper = new ObjectMapper();

    public JsonUtil() {
    }

    public static String objectToJson(Object src) {
        String json = null;

        try {
            json = ow.writeValueAsString(src);
        } catch (JsonProcessingException var3) {
            log.error(var3.getMessage(), var3);
        }

        return json;
    }

    public static Object jsonToObject(String json, Class clazz) {
        Object obj = null;

        try {
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            obj = mapper.readValue(json, clazz);
        } catch (IOException var4) {
            log.error(var4.getMessage(), var4);
        }

        return obj;
    }

    public static Object jsonToParameterizedObject(String json, TypeReference tr) {
        Object obj = null;

        try {
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            obj = mapper.readValue(json, tr);
        } catch (IOException var4) {
            log.error(var4.getMessage(), var4);
        }

        return obj;
    }
}
