package org.whocares.designPatterns.chainOfResponsibility2;

public class PMRequestHandler implements RequestHandler {
	
	private RequestHandler requestHandler;
	
	public PMRequestHandler() {
	}
	
	public PMRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	@Override
	public void handleRequest(Request request) {
		if(request instanceof AddMoneyRequest) {
			System.out.println(this.getClass().getSimpleName() + " handles " + request.getClass().getSimpleName());
		} else {
			requestHandler.handleRequest(request);
		}
	}

}
