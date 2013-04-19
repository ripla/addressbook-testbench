package com.vaadin.tutorial.addressbook.it;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.jbehave.core.model.ExamplesTable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Rudimentary helpers for mapping JBehave tables to beans.
 */
public class JBehaveTableMapper {
    public static <T> List<T> tableToBean(ExamplesTable table, Class<T> clazz) {
        List<T> list = Lists.newLinkedList();
        for (Map<String, String> row : table.getRows()) {
            list.add(mapBean(row, clazz));
        }
        return list;
    }

    public static <T> T mapBean(Map<String, String> row, Class<T> clazz) {
        try {
            T newInstance = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            Map<String, PropertyDescriptor> fields = Maps.newHashMap();

            for (PropertyDescriptor descriptor : beanInfo
                    .getPropertyDescriptors()) {
                if (clazz
                        .equals(descriptor.getReadMethod().getDeclaringClass())) {
                    fields.put(descriptor.getName().toLowerCase(), descriptor);
                }
            }

            for (String column : row.keySet()) {
                PropertyDescriptor field = fields.get(column.toLowerCase());
                if (field != null) {
                    String value = row.get(column);
                    field.getWriteMethod().invoke(newInstance, value);
                }
            }
            return newInstance;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
