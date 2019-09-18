package com.train.commonservice.utils;


import java.util.Random;

/**
 * 随机数生成类
 *
 * @author zhangLei
 * @serial 2019/09/17
 */
public class RandomUtils {

    /**
     * token生成方法
     *
     * @return String
     */
    public static String generateToken() {
        StringBuilder sb = new StringBuilder();
        final String[] lowercase = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        final String[] capital = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        final int second = 50;
        final int valueScope = 25;
        sb.append(System.currentTimeMillis());
        for (int x = 0; x < second; x++) {
            int lowercaseIndex = new Random().nextInt(valueScope);
            int capitalIndex = new Random().nextInt(valueScope);
            sb.append(lowercase[lowercaseIndex]);
            sb.append(capital[capitalIndex]);
        }
        return sb.toString();
    }
}
