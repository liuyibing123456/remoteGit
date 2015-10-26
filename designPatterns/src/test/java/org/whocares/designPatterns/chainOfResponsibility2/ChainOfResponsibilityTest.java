package org.whocares.designPatterns.chainOfResponsibility2;

import org.junit.Test;

public class ChainOfResponsibilityTest {

	@Test
	public void test() {
		Request request = new DimissionRequest();
		request = new AddMoneyRequest();
		request = new PromotionRequest();
		
		RequestHandler leaderHandler = new LeaderRequestHandler();
		RequestHandler PMHandler = new PMRequestHandler(leaderHandler);
		RequestHandler HRHandler = new HRRequestHandler(PMHandler);
		
		HRHandler.handleRequest(request);
	}

}
