package my.test.scan;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestTarget {

	protected ExecutorService es = Executors.newFixedThreadPool(1);

	// private method can reproduce the issue
	private void outerMethod(int outerParam) {
		// nothing to do
	}

	public Object callOuterMethodInAnonymousClass(boolean callOuterMethod) {
		return es.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				outerMethod(1);
				return null;
			}
		});
	}

}
