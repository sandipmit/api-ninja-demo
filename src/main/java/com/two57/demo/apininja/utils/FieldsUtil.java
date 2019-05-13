package com.two57.demo.apininja.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Lists;

/**
 * Hack to filter the fields based on what client needs
 */
public class FieldsUtil {
    private static final List<String> IGNORE_FIELDS = Arrays.asList("log");

    private static final Logger log = LoggerFactory.getLogger(FieldsUtil.class);

    public static <E> void setFields(final E original, final List<String> fields) {
        if (original == null || fields == null || fields.size() == 0) {
            return;
        }
        final List<Field> originalFields = getAllFields(new ArrayList<Field>(), original.getClass());
        final List<String> adjustedFields = Lists.newArrayList(fields);
        final Map<String, List<String>> nestedMap = new HashMap<>();
        //iterate and scan for nested objects
        for (final String name : fields) {
            if (name.indexOf(".") > 0) {
                final String[] result = name.split("\\.");
                String fieldName = result[0];
                if (!nestedMap.containsKey(fieldName)) {
                    nestedMap.put(fieldName, new ArrayList<String>());
                    adjustedFields.add(fieldName);
                }
                nestedMap.get(fieldName).add(result[1]);
            }
        }

        for (final String key : nestedMap.keySet()) {
            try {
                final Field f = original.getClass().getDeclaredField(key);
                f.setAccessible(true);
                //grab the object the field is associated with from the original object
                final Object object = f.get(original);
                setFields(object, nestedMap.get(key));
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                log.warn("Exception in FieldsUtil for class [" + original.getClass().getName() + "] with property [" + key + "] \n"
                        + e.getMessage());
            }
        }

        for (final Field field : originalFields) {
            if (!adjustedFields.contains(field.getName()) && !IGNORE_FIELDS.contains(field.getName())) {
                try {
                    PropertyUtils.setProperty(original, field.getName(), null);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    log.warn("Exception in FieldsUtil for class [" + original.getClass().getName() + "] with property [" + field.getName()
                            + "] \n" + e.getMessage());
                }
            }
        }
    }

    private static <T> List<Field> getAllFields(List<Field> fields, final Class<T> type) {
        for (final Field field : type.getDeclaredFields()) {
            fields.add(field);
        }

        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
}
