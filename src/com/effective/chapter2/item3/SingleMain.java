package com.effective.chapter2.item3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SingleMain {
    //직렬화
    public static byte[] serialize(Object instance) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (bos; ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(instance);
        } catch (Exception e) {
            // ... 구현 생략
        }
        return bos.toByteArray();
    }
    //역직렬화
    public static Object deserialize(byte[] serializedData) {
        ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
        try (bis; ObjectInputStream ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        } catch (Exception e) {
            // ... 구현 생략
        }
        return null;
    }
    public static void main(String[] args) {
        //v1 readResolve x
        SingleV1 single = SingleV1.getInstance;
        byte[] serializeSingle = serialize(single);
        SingleV1 deserializeSingle = (SingleV1) deserialize(serializeSingle);
        System.out.println(single == deserializeSingle);
        //false

        //v2 readResolve o
        SingleV2 single2 = SingleV2.getInstance;
        byte[] serializeSingle2 = serialize(single2);
        SingleV2 deserializeSingle2 = (SingleV2) deserialize(serializeSingle2);
        System.out.println(single2 == deserializeSingle2);
        //true

        //enum
        EnumSingle enumSingle = EnumSingle.INSTANCE;
        byte[] serializeEnum = serialize(enumSingle);
        EnumSingle deserializeEnum = (EnumSingle) deserialize(serializeEnum);
        System.out.println(enumSingle ==deserializeEnum);
        //true
    }
}
