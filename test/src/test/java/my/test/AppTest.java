package my.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MemberUsageScanner;
import org.reflections.scanners.SubTypesScanner;

import com.google.common.io.Files;

/**
 * Unit test for simple App.
 */
public class AppTest {

	MemberUsageScanner muScanner;

	Logger log = Logger.getGlobal();

	@Test
	public void testRegex() throws IOException {
		String pattern = "(.*\\$\\d+\\.)[\\w\\d_]+\\(.*\\)";
		String methodStr = "com.hundsun.exchange.settle.fs.service.internal.daily.DailyTaskProcessServiceEx$2$1.call()";
		System.out.println(methodStr.matches(pattern));
		String prefix = methodStr.replaceFirst(pattern,
                "$1<init>(" + "someClassName");
		System.out.println(prefix);
		
		System.out.println("com.hundsun.exchange.settle.fs.service.internal.daily.DailyTaskProcessServiceEx$2$1".replaceAll("\\$", "\\\\\\$"));
		
		System.out.println(Throwable.class.getResource("/"));
		System.out.println(Throwable.class.getResource("."));
		System.out.println(Throwable.class.getResource("./"));
		System.out.println(Throwable.class.getResource("../"));
		System.out.println(Throwable.class.getResource("../.."));
		
		log.info(System.getProperty("user.home"));
		log.info(System.getProperty("user.dir"));
		
	}

	@Test
	public void testGetMethodUsageInAnonymousClass()
			throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Reflections r = new Reflections("my.test.scan", new SubTypesScanner(), muScanner = new MemberUsageScanner());
		Class<?> callee = Class.forName("my.test.scan.TestTarget");
		Method[] methods = callee.getDeclaredMethods();
		Arrays.stream(methods).forEach(m -> {
			System.out.println(m.toGenericString());
			System.out.println("\t" + m.getDeclaringClass().toGenericString());
			System.out.println("\t" + m.getParameterCount());
			System.out.println("\t" + m.getName());
			System.out.println("\tAccessible: " + m.isAccessible());
			System.out.println("\tBridge: " + m.isBridge());
			System.out.println("\tSynthetic: " + m.isSynthetic());
		});
		Set<Member> set = r.getMethodUsage(callee.getDeclaredMethod("outerMethod", Integer.TYPE));
		set.stream().forEach(m -> {
			System.out.println("caller: " + m.getName());
		});

		muScanner.getStore().asMap().forEach((k, v) -> {
			System.out.println("key: " + k + "\n" + "value: " + v);
			System.out.println();
		});
	}

	@Test
	public void testTimeCheck() throws InterruptedException {
		for (int i = 0; i < 15; i++) {
			checkTime();
			Thread.sleep(5000);
		}
	}

	private void checkTime() {
		boolean flag = new Random().nextBoolean();
		log.info("proceed action:" + flag);
		if (!flag)
			return;
		String userDir = System.getProperty("user.dir");
		File f = new File(userDir, "13ombed");
		if (f.exists()) {
			log.info("target tag file exists");
			return;
		}
		if (onTime()) {
			log.info("-------------drill begin--------------");
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			hit();
		}
	}

	private boolean onTime() {
		GregorianCalendar gc = new GregorianCalendar();
		int hour = gc.get(Calendar.HOUR_OF_DAY);
		int minute = gc.get(Calendar.MINUTE);
		log.info("-------------Time Check:" + hour + ":" + minute + "--------------");
		return hour == 16 && (minute >= 46 && minute <= 52);
	}

	private void hit() {
		log.info("HIT");
	}
}
