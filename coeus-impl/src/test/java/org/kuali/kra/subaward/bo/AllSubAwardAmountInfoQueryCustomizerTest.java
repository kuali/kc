package org.kuali.kra.subaward.bo;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.stream.Stream;

import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.SelectionCriteria;
import org.junit.Test;

public class AllSubAwardAmountInfoQueryCustomizerTest {

	private static final String LESS_THAN_OR_EQUAL = " <= ";

	@Test
	public void test_buildCriteria() {
		SubAward subAward = new SubAward();
		subAward.setSubAwardCode("1");
		subAward.setSequenceNumber(1);
		
	    AllSubAwardAmountInfoQueryCustomizer customizer = new AllSubAwardAmountInfoQueryCustomizer();
	    Criteria customizedQuery = customizer.buildCriteria(subAward);
	    assertEquals(subAward.getSubAwardCode(), findCriteriaForProperty(AllSubAwardAmountInfoQueryCustomizer.SUB_AWARD_SUB_AWARD_CODE_PATH, customizedQuery).getValue());
	    assertEquals(AllSubAwardAmountInfoQueryCustomizer.ALLOWED_STATUSES, 
	    	findCriteriaForProperty(AllSubAwardAmountInfoQueryCustomizer.SUB_AWARD_SUB_AWARD_SEQUENCE_STATUS_PATH, customizedQuery).getValue());
	    assertEquals(subAward.getSequenceNumber(), findCriteriaForProperty(AllSubAwardAmountInfoQueryCustomizer.SUB_AWARD_SEQUENCE_NUMBER_PATH, customizedQuery).getValue());
	    assertEquals(LESS_THAN_OR_EQUAL, findCriteriaForProperty(AllSubAwardAmountInfoQueryCustomizer.SUB_AWARD_SEQUENCE_NUMBER_PATH, customizedQuery).getClause());
	}
	
	SelectionCriteria findCriteriaForProperty(String propertyName, Criteria crit) {
		return findSelectionCriteria(crit)
			.filter(subCrit -> subCrit.getAttribute().equals(propertyName))
			.findFirst().orElse(null);
	}
	
	Stream<SelectionCriteria> findSelectionCriteria(Object crit) {
		if (crit instanceof SelectionCriteria) {
			return Stream.of((SelectionCriteria)crit);
		} else if (crit instanceof Criteria) {
			return Collections.list(((Criteria)crit).getElements()).stream().flatMap(obj -> findSelectionCriteria(obj));
		} else {
			return Stream.empty();
		}
	}
}
