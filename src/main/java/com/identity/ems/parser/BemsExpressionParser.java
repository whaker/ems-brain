package com.identity.ems.parser;

import com.identity.ems.domain.BemsData;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BemsExpressionParser {
	private Map<String, Map<BigInteger, List<BemsData>>> dataSourceMap = new HashMap<>();
	private String expression = null;
	
	public void addDataSource(String identifier, Map<BigInteger, List<BemsData>> dataMap) {
		this.dataSourceMap.put(identifier, dataMap);
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	private Optional<BemsData> getBemsDataByPointType(String pointType, List<BemsData> bemsDatas) {
		if (bemsDatas == null) {
			return Optional.empty();
		}
		return bemsDatas.stream()
				.filter(bemsData -> bemsData.getPointType().equals(pointType))
				.findFirst();
	}

	public double evaluate(BigInteger bemsDataKey) throws IllegalArgumentException {
		ExpressionParser parser = new ExpressionParser(this.expression);
		Map<String, String> varMap = parser.parseVariables();
		for (Map.Entry<String, String> entry : varMap.entrySet()) {
			Map<BigInteger, List<BemsData>> dataMap = this.dataSourceMap.get(String.valueOf(entry.getValue().charAt(0)));
			if ((dataMap != null) && (dataMap.size() > 0)) {
				if (entry.getValue().charAt(0) == '@') {
					Optional<BemsData> bemsData = getBemsDataByPointType(entry.getValue().substring(1), dataMap.get(bemsDataKey));
					if (bemsData.isPresent() == false) {
						parser.setVariable(entry.getKey(),0.0);
						continue;
						//throw new IllegalArgumentException("Bems formula data value not found : " + entry.getValue());
					}
					if (bemsData.get().getPointValueType().equals("01")) {
						parser.setVariable(entry.getKey(), bemsData.get().getPointValue());
					} else {
						parser.setVariable(entry.getKey(), bemsData.get().getPointChangeValue());
					}
				} else {
					throw new IllegalArgumentException("Invalid formula expression : " + entry.getValue());
				}
			} else {
				parser.setVariable(entry.getKey(), entry.getValue());
			}
		}
		return parser.evaluate();
	}

}
