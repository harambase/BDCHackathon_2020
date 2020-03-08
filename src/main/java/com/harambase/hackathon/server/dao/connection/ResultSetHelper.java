package com.harambase.hackathon.server.dao.connection;

import javax.persistence.Column;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ResultSetHelper {

    public static <T> List<T> getObjectFor(ResultSet rs, Class<T> resultObject)
            throws IllegalAccessException, InstantiationException, IntrospectionException, SQLException, InvocationTargetException {
        List<T> retList = new ArrayList<>();
        if (rs == null) {
            return retList;
        } else {
            Class<T> clz = resultObject;
            ArrayList<ClassField> cfList = new ArrayList<>();
            Class tmpClz = resultObject;

            do {
                cfList.add(new ClassField(tmpClz, tmpClz.getDeclaredFields()));
                tmpClz = tmpClz.getSuperclass();
            } while (!tmpClz.getName().equals(Object.class.getName()));

            while (rs.next()) {
                T ret = clz.newInstance();

                for (int i = cfList.size() - 1; i >= 0; --i) {
                    Field[] fields = (cfList.get(i)).fields;
                    Class<?> inkClz = (cfList.get(i)).clz;
                    int length = fields.length;

                    for (int j = 0; j < length; ++j) {
                        Field field = fields[j];
                        String filedName = field.getName();
                        String colName;

                        Column dbAnn = field.getAnnotation(Column.class);

                        if (dbAnn != null) {
                            colName = dbAnn.name();
                            Object value = rs.getObject(colName);
                            if (value != null) {
                                PropertyDescriptor pd = new PropertyDescriptor(filedName, inkClz);
                                Method writeMethod = pd.getWriteMethod();
                                writeMethod.invoke(ret, value);
                            }
                        }
                    }
                }

                retList.add(ret);
            }
            return retList;
        }
    }

    public static <T> List<LinkedHashMap> getObjectAsLinkedHashMap(ResultSet rs, Class<T> resultObject)
            throws IllegalAccessException, InstantiationException, IntrospectionException, SQLException, InvocationTargetException {
        List<LinkedHashMap> retList = new ArrayList<>();
        if (rs == null) {
            return retList;
        } else {
            ArrayList<ClassField> cfList = new ArrayList<>();
            Class tmpClz = resultObject;

            do {
                cfList.add(new ClassField(tmpClz, tmpClz.getDeclaredFields()));
                tmpClz = tmpClz.getSuperclass();
            } while (!tmpClz.getName().equals(Object.class.getName()));

            while (rs.next()) {
                LinkedHashMap ret = new LinkedHashMap();

                for (int i = cfList.size() - 1; i >= 0; --i) {
                    Field[] fields = (cfList.get(i)).fields;
                    int length = fields.length;

                    for (int j = 0; j < length; ++j) {
                        Field field = fields[j];
                        String filedName = field.getName();
                        String colName;

                        Column dbAnn = field.getAnnotation(Column.class);

                        if (dbAnn != null) {
                            colName = dbAnn.name();
                            Object value = rs.getObject(colName);
                            ret.put(filedName, value);
                        }
                    }
                }
                retList.add(ret);
            }

            return retList;
        }
    }


    private static class ClassField {
        Class<?> clz;
        Field[] fields;

        private ClassField(Class<?> c, Field[] f) {
            this.clz = c;
            this.fields = f;
        }
    }
}
