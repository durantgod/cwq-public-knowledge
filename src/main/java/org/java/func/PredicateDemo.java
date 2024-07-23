package org.java.func;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @description: predicate 因为返回参数类型是boolean，因此使用场景一般是判断大小，判断类型，判断一个人的年龄等等 参考Collectors
 * @author: Weichuan.chen
 * @date: 2021/12/10
 */
public class PredicateDemo {

    public static void main(String[] args) {
        List<Person> ps = new ArrayList<>();
        ps.add(new Person("张三", 15));
        ps.add(new Person("李四", 18));
        ps.add(new Person("小米", 22));

        ps.stream().filter(filterAge(new Person("李四",27), new Person("z", 20))).forEach(System.out::println);
        ps.stream().filter(v -> (v.getAge() > 20) || "李四".equals(v.getName())).forEach(System.out::println);
    }

    static Predicate<Person> filterAge(Person... personList) {
        Predicate<Person> pd = null;

        for (Person p : personList) {
            if (Objects.isNull(pd)) {
                pd = p1 -> p1.getAge() > p.getAge();
            } else {
                pd = pd.or(p1 -> p1.getName().equals(p.getName()));
            }
        }
        return pd;
    }
}
