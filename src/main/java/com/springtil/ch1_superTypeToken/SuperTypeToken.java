package com.springtil.ch1_superTypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class SuperTypeToken {
    static class Sup<T>{
        T value;
    }

    // Erasure의 예외가 바로 제네릭 타입을 상속하는 경우
    static class Sub extends Sup<String> {
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Sup<String> s = new Sup<>();
        // Erasure 때문에 기본적으로는 타입 정보가 사라진다.
        System.out.println(s.getClass().getDeclaredField("value").getType());

        // 상속을 사용하면 Erasure가 작동하지 않는다.
        Sub b = new Sub();
        Type t = b.getClass().getGenericSuperclass();
        System.out.println(t);
        ParameterizedType ptype = (ParameterizedType) t;
        System.out.println(ptype.getActualTypeArguments()[0]);

        // 익명 클래스의 상속 방식
        Sup b2 = new Sup<Integer>() {};
        Type t2 = b2.getClass().getGenericSuperclass();
        System.out.println(t2);
        ParameterizedType ptype2 = (ParameterizedType) t2;
        System.out.println(ptype2.getActualTypeArguments()[0]);

    }

}
