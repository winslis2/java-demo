package com.lis2;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;

public class Lambda {
    public static void main(String[] args) {

    }
    
    @Test
    public void test1() {
        //使用内部类
        Comparator<Integer> integerComparator = new Comparator<> (){
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare (o1,o2);
            }
        };
        TreeSet<Integer> integers = new TreeSet<> (integerComparator);
    }

    @Test
    public void test2() {
        //Lambda表达式的写法，这里面是没有return的
        TreeSet<Integer> integers = new TreeSet<> ( (a, b) -> Integer.compare(a, b));
    }

    //消费者，传递一个参数没有返回值
    @Test
    public void test3() {
        //下面的写法是错误的
//        Consumer<String> stringConsumer = new Consumer<> (a-> System.out.println(a));
        //Consumer<String>是Consumer函数式接口的体现
        //把Lambda表达式看做一个函数
        Consumer<String> stringConsumer = a-> System.out.println (a);
        stringConsumer.accept ("hello world");
    }

    //使用自定义的函数式接口
    @Test
    public void test4() {
        FunctionalInterface1 myFun = (a,b)->a+b;
        Integer exec = myFun.exec (1, 3);
        System.out.println (exec);
    }

    public Integer operate(Integer a, Integer b, FunctionalInterface1 myfun) {
        return myfun.exec (a,b);
    }

    @Test
    public void test5() {
        //一般传递的实参是具体的值，下面传递的是Lambda表达式
        Integer operate = operate (1, 3, (a, b) -> a + b);
        System.out.println (operate);
    }
}
