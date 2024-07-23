package org.java.java8;

/**
 * 匿名类实现
 *
 * @author WQ
 * @date 2024/5/6/006
 * @since 1.0.0
 */
public class NMing {

    interface Polygon {
        void display();
    }

    static class AnonymousDemo {
        public void createClass() {
            Polygon p1 = new Polygon() {
                public void display() {
                    System.out.println("传统写法");
                }
            };
            p1.display();
        }

        public void createClassWithFunction() {
            Polygon polygon = () -> System.out.println("lambda写法");
        }
    }

    public static void main(String[] args) {
        AnonymousDemo sn = new AnonymousDemo();
    }

}
