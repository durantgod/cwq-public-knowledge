import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Desc: desc
 * @Author: WQ
 * @Date: 2023/3/8
 */
@Slf4j
public class StringDemo {

    @Test
    @Order(3)
    @DisplayName("测试字符串创建几次")
    public void test3() {
        String s3 = new String("xyz");
        String s4 = new String("xyz");
        // 上面创建了三个对象
        String s5 = "xyz";
        // s5没有创建对象，因为s3在字符串缓存池中已经创建了

        String s1 = "abc";
        String s2 = "abc";

        log.info("s3 == s4 ? {}", s3 == s4);
        log.info("s3 == s5 ? {}", s3 == s5);
        log.info("s1 == s2 ? {}", s1 == s2);
    }

    @Test
    @Order(4)
    @DisplayName("循环中remove元素")
    public void test4() {
        List<Integer> stuList = new ArrayList<>();
        stuList.add(1);
        stuList.add(2);
        stuList.add(3);

        for (int i = 0; i < stuList.size(); i++) {
            System.out.println(stuList.get(i));
            if (i == 2) {
                stuList.remove(stuList.get(i));
                i--;
            }
        }
    }

    @Test
    @Order(5)
    @DisplayName("循环中remove元素")
    public void test5() {
        List<Student> stuList = new ArrayList<>();
        stuList.add(new Student(1L, 2d, 3d, 4d));
        stuList.add(new Student(2L, 200d, 3d, 5d));
        stuList.add(new Student(3L, 2d, 3d, 6d));

        // 默认从小到大
        List<Student> stu1 = stuList.stream().sorted((a, b) -> Double.compare(b.getT3() + b.getT2() + b.getT1(), a.getT3() + a.getT2() + a.getT1())).collect(Collectors.toList());
        List<Student> stu2 = stuList.stream().sorted(Comparator.comparingDouble(a -> a.getT3() + a.getT2() + a.getT1())).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(stu1));
        System.out.println(JSON.toJSONString(stu2));
    }


    @Data
    static class Student {
        Long id;
        Double t1;
        Double t2;
        Double t3;

        public Student() {
        }

        public Student(Long id, Double t1, Double t2, Double t3) {
            this.id = id;
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }
    }
}
