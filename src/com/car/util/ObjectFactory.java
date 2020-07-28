package com.car.util;

public class ObjectFactory {

    public static Object getObject(String className){
        Object object=null;

        try {
            Class clazz=Class.forName(className);
            object=clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }

}
