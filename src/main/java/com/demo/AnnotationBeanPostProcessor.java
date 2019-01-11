package com.demo;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by RongJie.
 */
public class AnnotationBeanPostProcessor extends AbstractAnnotationBeanPostProcessor {
    private ConfigurableEnvironment environment;

    public AnnotationBeanPostProcessor(ConfigurableEnvironment environment){
        this.environment = environment;
    }

    @Override
    protected void processField(Object bean, String beanName, Field field) {
        DecryptValue neptuneAnnotation = AnnotationUtils.getAnnotation(field, DecryptValue.class);
        if (neptuneAnnotation == null){
            return;
        }
        String propertyString = neptuneAnnotation.value();
        String decryptedValue = null;
        Map<String, String> keyAndDefaultValue = PlaceHolderResolver.obtainPlaceHolderKey(propertyString);
        for (Map.Entry<String, String> entry : keyAndDefaultValue.entrySet()) {
            String valueBeforeDecrypt = environment.getProperty(entry.getKey());
            if (valueBeforeDecrypt != null){
                //todo 对valueBeforeDecrypt进行解密后赋值给decryptedValue
                decryptedValue = "lisi";
            }else {
                String defaultValue = entry.getValue();
                if (defaultValue == null){
                    throw new RuntimeException(
                            String.format("Could not resolve placeholder '%s' in neptuneDecryptValue \"%s\""
                                    ,entry.getKey()
                                    ,propertyString));
                }else {
                    decryptedValue = defaultValue;
                }
            }
        }

        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field,bean,decryptedValue);
    }
}
