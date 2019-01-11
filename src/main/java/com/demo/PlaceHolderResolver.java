package com.demo;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by RongJie.
 */
public class PlaceHolderResolver {
    private static final String PLACEHOLDER_PREFIX = "${";
    private static final String PLACEHOLDER_SUFFIX = "}";
    private static final String VALUE_SEPARATE = ":";

    /**
     * 提取占位符中的key
     * 支持的格式:
     * ${key}               提取出key
     * ${key:defaultValue}  提取出key和defaultValue
     * @param propertyString
     * @return
     */
    public static Map<String, String> obtainPlaceHolderKey(String propertyString) {
        Map<String, String> keyAndDefaultValue = Maps.newHashMap();
        if (propertyString.startsWith(PLACEHOLDER_PREFIX)
                && propertyString.endsWith(PLACEHOLDER_SUFFIX)) {
            String placeholder = propertyString.substring(PLACEHOLDER_PREFIX.length(), propertyString.length()-1);
            if (placeholder.indexOf(VALUE_SEPARATE) != -1) {
                String[] split = placeholder.split(VALUE_SEPARATE);
                if (StringUtils.isBlank(split[0])){
                    throw new RuntimeException("Resolve placeholder error! placeholder: "+propertyString);
                }
                keyAndDefaultValue.put(split[0],split[1]);
            }else {
                keyAndDefaultValue.put(placeholder,null);
            }
        }else {
            keyAndDefaultValue.put(propertyString,null);
        }
        return keyAndDefaultValue;
    }
}
