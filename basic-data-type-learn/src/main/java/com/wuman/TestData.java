package com.wuman;

/**
 * @author lmf
 * @version 1.0
 * @date 2022/10/27 16:12
 */
public class TestData {
    //成员变量有默认值，可直接使用
     static int a;

    public static void main(String[] args) {
        System.out.println("a = " + a);
    }
    //局部变量无法直接使用，必须赋值
//    public void test(){
//        int a;
//        System.out.println("a = " + a);
//    }
}
