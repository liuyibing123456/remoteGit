package org.whocares.designPatterns.chainOfResponsibility2;

public class HRRequestHandler implements RequestHandler {
	
	private RequestHandler requestHandler;
	
	public HRRequestHandler() {
	}
	
	public HRRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	@Override
	public void handleRequest(Request request) {
		if(request instanceof DimissionRequest) {
			System.out.println(this.getClass().getSimpleName() + " handles " + request.getClass().getSimpleName());
		} else {
			requestHandler.handleRequest(request);
		}
	}

}
