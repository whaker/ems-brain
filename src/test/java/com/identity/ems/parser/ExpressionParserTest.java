package com.identity.ems.parser;

import com.identity.ems.AbstractTestableContext;
import org.junit.Test;

import java.util.Map;

public class ExpressionParserTest extends AbstractTestableContext {

	@Test
	public void evaluate() {
		ExpressionParser parser = new ExpressionParser("10 + XE + XE + Y + Y, XE:$5, Y:$10");
		Map<String, String> varMap = parser.parseVariables();
		for (Map.Entry<String, String> entry : varMap.entrySet()) {
			String value = entry.getValue();
			if (value.charAt(0) == '$') {
				System.out.println("$ 변수");
				parser.setVariable(entry.getKey(), value.substring(1));
			} else {
				parser.setVariable(entry.getKey(), value);
			}
			System.out.println(entry.getKey());
		}
		Double result = parser.evaluate();
		System.out.println("Expression : " + result);
	}
	
	@Test
	public void evaluate2() {
		ExpressionParser parser = new ExpressionParser("1 + XE, XE:$5");
		Map<String, String> varMap = parser.parseVariables();
		for (Map.Entry<String, String> entry : varMap.entrySet()) {
			String value = entry.getValue();
			if (value.charAt(0) == '$') {
				System.out.println("$ 변수");
				parser.setVariable(entry.getKey(), value.substring(1));
			} else {
				parser.setVariable(entry.getKey(), value);
			}
			System.out.println(entry.getKey());
		}
		Double result = parser.evaluate();
		System.out.println("Expression : " + result);
	}
}
