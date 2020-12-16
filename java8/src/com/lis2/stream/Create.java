package com.lis2.stream;

import com.lis2.Employee;
import com.sun.source.doctree.StartElementTree;
import org.junit.Test;

import java.lang.module.ResolutionException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Create {
    List<Employee> employees = Arrays.asList (
            new Employee ("lis2",101,1000.1),
            new Employee ("lis",102,1000.1),
            new Employee ("lis4",103,1000.2)
    );
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


    //flatmap使用
    @Test
    public void test8() {
        List<String> list = Arrays.asList ("a", "b", "c");
        Create create = new Create ();
        list.stream ()
            .flatMap (create::filterCharacter)
            .forEach (System.out::println);


    }

    public Stream<Character> filterCharacter(String str) {//这里的str指的是"a","b","c"
        ArrayList<Character> characters = new ArrayList<> ();
        for (char c: str.toCharArray ()) {
            characters.add (c);
        }
        return characters.stream ();
    }

    //sorted排序
    @Test
    public void test9 () {
        List<Integer> integers = Arrays.asList (1, 2, 6, 4, 2);
        integers.sort ((x,y)->-Integer.compare (x,y));//不用Stream的用法
        System.out.println (integers);
        integers.stream ()
                .sorted ()
                .forEach (System.out::println);
    }

    //查找匹配
    @Test
    public void test10() {

        Optional<Employee> any = employees.stream ()
                .findAny ();
        System.out.println (any.get ());

        long count = employees.stream ()
                .count ();
        System.out.println (count);

        Optional<Employee> min = employees.stream ()
                .min ((x, y) -> x.getSalary ().compareTo (y.getSalary ()));
        System.out.println (min);
        System.out.println (min.get ());


    }

    //reduce归约 将流中的数据反复的结合起来，得到一个值
    @Test
    public void test11() {
        List<Integer> integers = Arrays.asList (1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> reduce = integers.stream ()
                .reduce ((x, y) -> x + y);
        System.out.println (reduce.get ());

    }

    //collect收集
    @Test
    public void test12() {
        Long collect = employees.stream ()
                .collect (Collectors.counting ());
        System.out.println (collect);

        Map<Double, List<Employee>> collect1 = employees.stream ()
                .collect (Collectors.groupingBy (Employee::getSalary));
        System.out.println (collect1);

        Map<Boolean, List<Employee>> collect2 = employees.stream ()
                .collect (Collectors.partitioningBy (employee -> employee.getSalary () > 100));

    }

}
