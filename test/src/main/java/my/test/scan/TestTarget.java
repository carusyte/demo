package my.test.scan;

public class TestTarget {

	// private method can reproduce the issue
	private void outerMethod() {
		// nothing to do
	}

	public void callOuterMethodInAnonymousClass() {
		new Object() {
			public void run() {
				outerMethod();
			}
		}.run();
	}
}
