import org.junit.jupiter.api.*;

/**
 * @description: TestTemplate
 * @author: WeiQ.chen
 * @date: 2022/8/10
 */
public class TestJunit5 {
    @BeforeAll
    public static void init() {
        System.out.println("全局初始化.....");
    }

    @BeforeEach
    public void testBeforeEach() {
        System.out.println("单个部件初始化.....");
    }

    @DisplayName("部件初始化流程测试 共5次")
    @RepeatedTest(5)
    public void test() {
        System.out.println("测试完成！");
    }


    @Test
    public boolean haveConflict(String[] event1, String[] event2) {
        int e1Start = Integer.parseInt(event1[0].replace(":", ""));
        int e1End = Integer.parseInt(event1[1].replace(":", ""));

        int e2Start = Integer.parseInt(event2[0].replace(":", ""));
        int e2End = Integer.parseInt(event2[1].replace(":", ""));

        return (e2Start >= e1Start && e2Start <= e1End) || (e1Start >= e2Start && e1Start <= e2End);
    }

    /**
     * 给你两个字符串数组 event1 和 event2 ，表示发生在同一天的两个闭区间时间段事件，其中：
     *
     * event1 = [startTime1, endTime1] 且
     * event2 = [startTime2, endTime2]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/determine-if-two-events-have-conflict
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void ttt() {
        String[] e1 = {"10:00","11:00"};
        String[] e2 = {"14:00","15:00"};
        System.out.println(haveConflict(e1, e2));
    }

    /**
     * 解题思路2 取反
     */
    @Test
    public boolean haveConflict2(String[] event1, String[] event2) {
        return !(event1[1].hashCode() < event2[0].hashCode() || event2[1].hashCode() < event1[0].hashCode());
    }

    /**
     * 解题思路3 hash
     */
    @Test
    public boolean haveConflict3(String[] event1, String[] event2) {
        return !(event1[1].hashCode() < event2[0].hashCode() || event2[1].hashCode() < event1[0].hashCode());
    }
}
