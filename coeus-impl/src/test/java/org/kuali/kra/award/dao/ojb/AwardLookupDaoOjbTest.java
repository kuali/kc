package org.kuali.kra.award.dao.ojb;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.bo.BusinessObject;

public class AwardLookupDaoOjbTest {
	
	private static final String AWARD_NUMBER_2 = "2";
	private static final String AWARD_NUMBER_1 = "1";

	Award buildAward(String awardNumber, Integer sequenceNumber) {
		Award award = new Award();
		award.setAwardNumber(awardNumber);
		award.setSequenceNumber(sequenceNumber);
		return award;
	}

	@Test
	public void testAwardSearchResults_both() {
		List<Award> awardVersions = Arrays.asList(buildAward(AWARD_NUMBER_2, 3), buildAward(AWARD_NUMBER_1, 1), buildAward(AWARD_NUMBER_1, 2), buildAward(AWARD_NUMBER_2, 1));
		AwardLookupDaoOjb dao = mock(AwardLookupDaoOjb.class);
		when(dao.findAwards(anyMap(), anyBoolean())).thenReturn(awardVersions);
		when(dao.getAwardSearchResults(anyMap(), anyBoolean())).thenCallRealMethod();
		List<? extends BusinessObject> result = dao.getAwardSearchResults(Collections.emptyMap(), false);
		assertEquals(2, result.size());
		final Award award1 = result.stream().map(bo -> (Award)bo).filter(award -> award.getAwardNumber().equals(AWARD_NUMBER_1)).findFirst().orElse(new Award());
		final Award award2 = result.stream().map(bo -> (Award)bo).filter(award -> award.getAwardNumber().equals(AWARD_NUMBER_2)).findFirst().orElse(new Award());
		assertEquals(2, award1.getSequenceNumber().longValue());
		assertEquals(3, award2.getSequenceNumber().longValue());
	}
	
	@Test
	public void testAwardSearchResults_final() {
		List<Award> awardVersions = Arrays.asList(buildAward(AWARD_NUMBER_1, 1), buildAward(AWARD_NUMBER_2, 1));
		AwardLookupDaoOjb dao = mock(AwardLookupDaoOjb.class);
		when(dao.findAwards(anyMap(), anyBoolean())).thenReturn(awardVersions);
		when(dao.getAwardSearchResults(anyMap(), anyBoolean())).thenCallRealMethod();
		List<? extends BusinessObject> result = dao.getAwardSearchResults(Collections.emptyMap(), false);
		assertEquals(2, result.size());
		final Award award1 = result.stream().map(bo -> (Award)bo).filter(award -> award.getAwardNumber().equals(AWARD_NUMBER_1)).findFirst().orElse(new Award());
		final Award award2 = result.stream().map(bo -> (Award)bo).filter(award -> award.getAwardNumber().equals(AWARD_NUMBER_2)).findFirst().orElse(new Award());
		assertEquals(1, award1.getSequenceNumber().longValue());
		assertEquals(1, award2.getSequenceNumber().longValue());
	}
	
	@Test
	public void testAwardSearchResults_pending() {
		List<Award> awardVersions = Arrays.asList(buildAward(AWARD_NUMBER_1, 2), buildAward(AWARD_NUMBER_2, 3));
		AwardLookupDaoOjb dao = mock(AwardLookupDaoOjb.class);
		when(dao.findAwards(anyMap(), anyBoolean())).thenReturn(awardVersions);
		when(dao.getAwardSearchResults(anyMap(), anyBoolean())).thenCallRealMethod();
		List<? extends BusinessObject> result = dao.getAwardSearchResults(Collections.emptyMap(), false);
		assertEquals(2, result.size());
		final Award award1 = result.stream().map(bo -> (Award)bo).filter(award -> award.getAwardNumber().equals(AWARD_NUMBER_1)).findFirst().orElse(new Award());
		final Award award2 = result.stream().map(bo -> (Award)bo).filter(award -> award.getAwardNumber().equals(AWARD_NUMBER_2)).findFirst().orElse(new Award());
		assertEquals(2, award1.getSequenceNumber().longValue());
		assertEquals(3, award2.getSequenceNumber().longValue());
	}


}
