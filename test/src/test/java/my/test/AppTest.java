package my.test;

import java.lang.reflect.Member;
import java.util.Set;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MemberUsageScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testGetMethodUsageInAnonymousClass()
			throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		Reflections r = new Reflections("my.test.scan", new MethodAnnotationsScanner(), new SubTypesScanner(),
				new MethodParameterScanner(), new TypeAnnotationsScanner(), new MemberUsageScanner());
		Class<?> callee = Class.forName("my.test.scan.TestTarget");
		Set<Member> set = r.getMethodUsage(callee.getDeclaredMethod("outerMethod", new Class<?>[0]));
		set.stream().forEach(m -> {
			System.out.println("caller: " + m.getName());
		});
	}
}
