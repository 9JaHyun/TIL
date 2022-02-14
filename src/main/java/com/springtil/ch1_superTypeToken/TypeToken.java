package com.springtil.ch1_superTypeToken;


// 일반적인 타입을 구별하게 되는 타입 토큰
public class TypeToken {
    // 이렇게 하기에는 너무 불편
    static class Generic<T>{
        T value;

        void set(T t) {}

        T get() {return null;}
    }
}
