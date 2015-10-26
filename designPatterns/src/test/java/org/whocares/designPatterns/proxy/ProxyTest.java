package org.whocares.designPatterns.proxy;

import org.junit.Test;

public class ProxyTest {

	@Test
	public void test() {
		Writer writerProxy = new WriterProxy(new GuoJingming());
		writerProxy.write();
		
		Writer writerProxy1 = new WriterProxy(new HanHan());
		writerProxy1.write();
	}

}
