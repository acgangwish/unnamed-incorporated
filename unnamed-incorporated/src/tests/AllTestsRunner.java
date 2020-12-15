package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AllTestsRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(AllTests.class);
		
		for(Failure failure : result.getFailures()) {
			System.out.println(failure);
		}
		
		System.out.println(result.wasSuccessful());
	}
}
