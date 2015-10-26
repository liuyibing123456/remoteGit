package org.whocares.designPatterns.chainOfResponsibility2;

public class LeaderRequestHandler implements RequestHandler {
	
	private RequestHandler requestHandler;
	
	public LeaderRequestHandler() {
	}
	
	public LeaderRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}

	@Override
	public void handleRequest(Request request) {
		if(request instanceof PromotionRequest) {
			System.out.println(this.getClass().getSimpleName() + " handles " + request.getClass().getSimpleName());
		} else {
			requestHandler.handleRequest(request);
		}
	}

}
