package com.identity.ems.parser;

import com.identity.ems.AbstractTestableContext;
import com.identity.ems.domain.BemsData;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BemsExpressionParserTest extends AbstractTestableContext {

	@Test
	public void evaluate() {
		BemsExpressionParser parser = new BemsExpressionParser();
		
		Map<BigInteger, List<BemsData>> bemsDatasMap = new HashMap();

		List<BemsData> bemsDatas = new ArrayList<>();
		BemsData bemsData = new BemsData();
		bemsData.setPointIdx(BigInteger.valueOf(1));
		bemsData.setPointValue(2.0);
		bemsData.setPointType("WM2001");
		bemsData.setVirtualFlag("N");
		bemsData.setFormula("");
		bemsDatas.add(bemsData);

		bemsData = new BemsData();
		bemsData.setPointIdx(BigInteger.valueOf(2));
		bemsData.setPointValue(3.0);
		bemsData.setPointType("WM3001");
		bemsData.setVirtualFlag("N");
		bemsData.setFormula("");
		bemsDatas.add(bemsData);

		bemsDatasMap.put(BigInteger.valueOf(1), bemsDatas);

		parser.addDataSource("@", bemsDatasMap);

		double pointValue = 10.0f;
		String Test = Double.toString(pointValue);
	
		parser.setExpression(Test + "10 + XE + Y, XE:@WM2001, Y:@WM3001");
		double result = parser.evaluate(BigInteger.valueOf(1));
		
		System.out.println("expression result : " +  result);
		
		parser.setExpression("10 + XE + Y, XE:@WM2001, Y:@WM2001");
		result = parser.evaluate(BigInteger.valueOf(2));
		
		System.out.println("expression result2 : " +  result);
	}
}
