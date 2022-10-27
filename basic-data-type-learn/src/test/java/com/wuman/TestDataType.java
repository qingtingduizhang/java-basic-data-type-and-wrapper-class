package com.wuman;

import org.junit.Test;

/**
 * 使用包装类的好处就是便于不同类型的数值之间的转换
 * @author lmf
 * @version 1.0
 * @date 2022/10/27 10:36
 */
public class TestDataType {

    /**
     * 由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的（因为new生成的是两个对象，其内存地址不同）。
     */
    @Test
    public void test(){
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.print(i == j); //false
    }

    /**
     * Integer变量和int变量比较时，只要两个变量的值是向等的，
     * 则结果为true（因为包装类Integer和基本数据类型int比较时，Java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较）
     */
    @Test
    public void test2(){
        Integer i = new Integer(100);
        int j = 100;
        System.out.print(i == j); //true
    }

    /**
     * 非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。（因为 ①当变量值在-128~127之间时，
     * 非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同）
     */
    @Test
    public void test3(){
        Integer i = new Integer(100);
        Integer j = 100;
        System.out.print(i == j); //false
    }

    /**
     * 对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，
     * 则比较结果为true，如果两个变量的值不在此区间，则比较结果为false
     * 对于第4条的原因：
     * java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)；，而java API中对Integer类型的valueOf的定义如下：
     * public static Integer valueOf(int i){
     *     assert IntegerCache.high >= 127;
     *     if (i >= IntegerCache.low && i <= IntegerCache.high){
     *         return IntegerCache.cache[i + (-IntegerCache.low)];
     *     }
     *     return new Integer(i);
     * }
     *
     * 同样场景适配于Short/Long/Character这三种包装类
     */
    @Test
    public void test4(){
        Integer i = 100;
        Integer j = 100;
        System.out.println(i == j); //true
        Integer k = 128;
        Integer y = 128;
        System.out.println(k == y); //false
    }

    /**
     * 自动装拆箱是一个很好的功能，大大节省了我们开发人员的精力，但也会引发一些麻烦，比如下面这段代码，性能就很差。
     * sum 由于被声明成了包装类型 Long 而不是基本类型 long，所以 sum += i 进行了大量的拆装箱操作（sum 先拆箱和 i 相加，然后再装箱赋值给 sum），导致这段代码运行完花费的时间足足有 2986 毫秒；如果把 sum 换成基本类型 long，时间就仅有 554 毫秒，完全不一个等量级啊。
     */
    @Test
    public void test5(){
        long t1 = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i < Integer.MAX_VALUE;i++) {
            sum += i;
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
    }
}
