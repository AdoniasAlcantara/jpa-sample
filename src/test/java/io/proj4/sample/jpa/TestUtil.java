package io.proj4.sample.jpa;

public class TestUtil {
	private TestUtil() {}
	
	public static void format(String title, TestCase testCase) {
		System.out.println("-----------------------------------------------------");
		System.out.println(title + ":");
		System.out.println("-----------------------------------------------------");
		testCase.run();
		System.out.println();
	}
	
	@FunctionalInterface
	public interface TestCase {
		void run();
	}
}
