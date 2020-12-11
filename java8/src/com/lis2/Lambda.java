package com.lis2;

import org.junit.Test;

import java.io.PrintStream;
import java.lang.module.ResolutionException;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
    //JAVA内置四大核心函数式接口
    //消费型接口 接受一个参数，无返回值
    @Test
    public void test6() {
        Consumer<Integer> consumer = x -> System.out.println (x);
        consumer.accept (1000);

        Consumer<Integer> consumer1 = System.out::println;//方法引用
        consumer1.accept (200);
    }

    //供给型接口  没有参数，一个返回值
    @Test
    public void test7() {
        //获取Employee对象
        Supplier<Employee> sup = ()-> new Employee ();
        Employee employee = sup.get ();
        System.out.println (employee);
    }

    //函数式接口  一个参数，一个返回值
    @Test
    public void test8() {
        //字符串截取功能
        Function<String, String> fun = (str)-> str.substring (1, 2);
        String apply = fun.apply ("123456");
        System.out.println (apply);
    }

    //断言型接口  一个参数 返回bool
    @Test
    public void test9() {
        //判断员工的工资是否大于5000
        Predicate<Employee> predicate = (emp)->{
            return emp.getSalary () > 5000;
        };
        boolean test = predicate.test (new Employee ("lis2",2,5000.1));
        System.out.println (test);
    }

    //方法引用：若Lambda方法体中的内容已有方法实现，可以使用方法引用
    //对象::实例方法
    @Test
    public void test10() {
        PrintStream out = System.out;
        Consumer<String> con = x ->out.println (x);
        //Lambda表达实体中调用方法的参数列表和返回值类型和函数式接口中抽象方法保持一致
        Consumer<String> con1 = out::println;
    }

    //类::静态方法
    @Test
    public void test11() {
        //注意不要使用Compare
        Comparator<Integer> com = (x, y) -> Integer.compare (x, y);
        Comparator<Integer> com1 = Integer::compare;//使用方法引用是不需要参数的
        com.compare (1,2);
    }

    //构造器引用
    @Test
    public void test12() {
        Supplier<Employee> supplier = ()-> new Employee ();
        Employee employee = supplier.get ();
    }



}
