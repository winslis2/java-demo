package com.lis2.stream;

import com.lis2.Employee;
import org.junit.Test;

import java.lang.module.ResolutionException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Create {
    @Test
    public void test1() {
        ArrayList<Employee> employees = new ArrayList<> ();
        Stream<Employee> stream = employees.stream ();
    }

    //数组流
    @Test
    public void test2() {
        String[] strings = new String[10];
        Stream<String> stream = Arrays.stream (strings);
    }

    //静态方法
    @Test
    public void test3() {
        Stream<String> hello = Stream.of ("hello", "world");
    }

    //无限流
    @Test
    public void test4() {
        Stream.iterate (0, (x)-> x+1)
                .forEach (System.out::println);
    }

    //生成
    @Test
    public void test5() {
        Stream.generate (()->Math.random ())
                .forEach (System.out::println);
    }

    //筛选和切片
    @Test
    public void test6() {
        List<Employee> employees = Arrays.asList (
                new Employee ("lis2",101,1000.1),
                new Employee ("lis2",101,1000.1),
                new Employee ("lis4",103,1000.2)
        );
        Iterator<Employee> iterator = employees.iterator ();
        while (iterator.hasNext ()) {
            System.out.println (iterator.next ());
        }

        employees.stream ()
                .filter (new Predicate<Employee> () {
                    @Override
                    public boolean test(Employee employee) {
                        return employee.getSalary () > 1000;
                    }
                })
                .filter ((x)->x.getSalary ()>1000)
                .distinct ()
                .skip (1)
                .forEach (System.out::println);
    }


    //map的使用
    @Test
    public void test7() {
        List<String> list = Arrays.asList ("a", "b", "c");
        list.stream ()
                .map ((x) -> x.toUpperCase ())
                .forEach (System.out::println);
    }
}
