import lombok.SneakyThrows;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

import static org.mockito.Mockito.mock;

/**
 * @description: TestMock
 * @author: WeiQ.chen
 * @date: 2022/8/11
 */
@RunWith(MockitoJUnitRunner.class)
public class TestMock {

    @Mock
    TestJunit5 testJunit55;

    @InjectMocks
    TestJunit5 testJunit555;

    @Spy
    TestJunit5 testJunit5555;

    @SneakyThrows
    @Test
    public void test() {
        TestJunit5 testJunit5 = mock(TestJunit5.class);

        System.out.println("class5:" + testJunit5.getClass());
        testJunit5.test();
        Thread.sleep(1000L);

        System.out.println("class55:" + testJunit55.getClass());
        testJunit55.test();
        Thread.sleep(1000L);

        System.out.println("class555:" + testJunit555.getClass());
        testJunit555.test();
        Thread.sleep(1000L);

        System.out.println("class5555:" + testJunit5555.getClass());
        testJunit5555.test();
        Thread.sleep(1000L);
    }

    @Test
    public void t1() {
        String startDate = "2023-01-12 14:51:00";
        System.out.println(getDayOfWeek(string2DateByYMDHMS(startDate)));
    }

    @Test
    public void t2() {
        String startDate1 = "2023-01-12 14:00:00";
        String startDate = startDate1.substring(11, 16);
        System.out.println(startDate);

        String time = "8:00-12:00";
        String[] timeC;
        timeC = time.split("-");
        if (startDate.hashCode() >= timeC[0].hashCode() && startDate.hashCode() <= timeC[1].hashCode()) {
            System.out.println("=====");
        }
    }

    @Test
    public void test3() {
        TreeSet<Integer> s = new TreeSet<>();

        s.add(2);
        s.add(1);
        s.add(3);

        System.out.println(s.first());
    }

    @Test
    public void test4() {
        LocalDate l = null;

        //System.out.println(l.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        Assert.notNull(l, "5");
    }


    @Test
    @DisplayName("当月第一天或最后一天")
    public void test5() {
        LocalDate localDate = LocalDate.now().plusMonths(1L);
        //本月第一天
        LocalDate firstday = LocalDate.of(localDate.getYear(), localDate.getMonthValue(), 1);
        //本月的最后一天
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());

        System.out.println(firstday);
        System.out.println(lastDay);
    }


    @Test
    @DisplayName("两个localdate之间的间隔")
    public void test6() {
        LocalDate start = LocalDate.now().plusMonths(1L);
        LocalDate end = LocalDate.now().plusMonths(2L);

        long year = start.until(end, ChronoUnit.YEARS);
        long month = start.until(end, ChronoUnit.MONTHS);
        long day = start.until(end, ChronoUnit.DAYS);

        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
    }



    public static long getDayOfWeek(Date day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        return cal.get(Calendar.DAY_OF_WEEK);
    }


    /**
     *            日期字符串 例如：0000-00-00 00:00:00
     *            格式化字符串，例如：yyyy-MM-dd HH:mm:ss
     * @return Date 日期类型
     */
    public static Date string2DateByYMDHMS(String date) {
        // 格式校验

        // 上一步的正则表达式的判断，时间格式可能是 yyyy-M-d HH:mm:ss 的情况，因此，给这种情况的月、日 加上0
        // 更新后为 yyyy-0M-0d HH:mm:ss
        String tmpDate = date;
        if (DateUtil.YYYYMMDDHHMMSS.length() > date.length()) {
            String yearTail = date.substring(0, 5);
            String timeTail = date.substring(date.length() - 9);
            String middDate = date.substring(5, date.length() - 9);
            String[] md = middDate.split("-");
            StringBuilder sb = new StringBuilder();
            sb.append(yearTail);
            if (md[0].length() == 1) {
                sb.append("0");
            }
            sb.append(md[0]);
            sb.append("-");
            if (md[1].length() == 1) {
                sb.append("0");
            }
            sb.append(md[1]);
            sb.append(timeTail);
            tmpDate = sb.toString();
        }
        Date returnDate = null;
        SimpleDateFormat sf = null;

        try {
            sf = new SimpleDateFormat(DateUtil.YYYYMMDDHHMMSS);
            returnDate = sf.parse(tmpDate);
            // 将字符串格式化后的时间，再转成字符串
            String returnDate2Str = DateFormatUtils.format(returnDate,
                    DateUtil.YYYYMMDDHHMMSS);
            // 比较2者是否相同，相同，说明日期格式正确，不相同，日期格式不正确
            if (!tmpDate.equals(returnDate2Str)) {
                returnDate = null;
            }
        } catch (ParseException e) {
            returnDate = null;
        }
        return returnDate;
    }

}
