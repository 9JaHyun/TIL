package com.springtil.ch1_superTypeToken;

public class GeneralType {

    // 일반적인 경우 (Generic X)
    static <T> T create(Class<T> clazz) throws Exception {
        return clazz.getDeclaredConstructor().newInstance();
    }


    public static void main(String[] args) throws Exception {
        Integer o = create(Integer.class);
        System.out.println(o.getClass());
    }

}
