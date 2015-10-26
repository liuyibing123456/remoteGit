package org.whocares.designPatterns.proxy;

public class WriterProxy implements Writer {

	private Writer writer;
	
	public WriterProxy(Writer writer) {
		this.writer = writer;
	}
	
	@Override
	public void write() {
		this.writer.write();
	}

}
