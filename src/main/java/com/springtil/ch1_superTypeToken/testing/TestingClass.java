package com.springtil.ch1_superTypeToken.testing;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TestingClass {

    // Type Token
    static class TypesafeMap {

        Map<Class<?>, Object> map = new HashMap<>();

        <T> void put(Class<T> clazz, T value) {
            map.put(clazz, value);
        }

        <T> T get(Class<T> clazz) {
            return clazz.cast(map.get(clazz));
        }
    }

    static class TypesafeMapV2 {
        Map<Type, Object> map = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value) {
            map.put(tr.type, value);
        }

        <T> T get(TypeReference<T> tr) {
            if (tr.type instanceof Class<?>) {
                return ((Class<T>) tr.type).cast(map.get(tr.type));
            } else {
                return ((Class<T>) ((ParameterizedType)tr.type).getRawType()).cast(map.get(tr.type));
            }
        }

    }

    // 슈퍼타입토큰을 사용하기 위해서 따로 타입을 생성해야 한다.
    static class TypeReference<T> {
        Type type;

        public TypeReference() {
            Type stype = getClass().getGenericSuperclass();
            if (stype instanceof ParameterizedType) {
                this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
            } else {
                throw new RuntimeException();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {return true;}
            if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) {
                return false;
            }
            TypeReference<?> that = (TypeReference<?>) o;
            return Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type);
        }
    }


    public static void main(String[] args) {
        generalTypeToken();
        problem1();
        superTypeToken();
    }

    private static void generalTypeToken() {
        System.out.println("==General Type Token==");
        // TypeToken을 사용하면 대략적으로 이용이 가능하다.
        TypesafeMap m = new TypesafeMap();
        m.put(Integer.class, 1);
        m.put(String.class, "String");
        m.put(List.class, List.of("a", "b", "c"));

        System.out.println(m.get(Integer.class));
        System.out.println(m.get(String.class));
        System.out.println(m.get(List.class));
    }

    private static void problem1() {
        System.out.println("==Problem With Type Token==");
        // 문제는 List와 같이 Generic 사용하는 경우 여러 Generic을 사용하게 된다면?
        TypesafeMap m1 = new TypesafeMap();
        // 중복이 된다.
        m1.put(List.class, Arrays.asList(1, 2, 3, 4, 5));
        m1.put(List.class, Arrays.asList("a", "b", "c"));

        List x = m1.get(List.class);
        System.out.println(x);
    }

    private static void superTypeToken() {
        System.out.println("==super Type Token==");
        TypesafeMapV2 m = new TypesafeMapV2();
        // 메모리 누수가 생길 수 있지 않을까?
        m.put(new TypeReference<Integer>() {}, 1);
        m.put(new TypeReference<String>() {}, "String");
        m.put(new TypeReference<List<List<String>>>() {},
            Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("c", "d"),Arrays.asList("e")));

        System.out.println(m.get(new TypeReference<Integer>() {}));
        System.out.println(m.get(new TypeReference<String>() {}));
        System.out.println(m.get(new TypeReference<List<List<String>>>() {}));

    }
}
