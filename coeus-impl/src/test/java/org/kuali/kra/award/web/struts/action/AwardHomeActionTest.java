/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.award.web.struts.action;

import static org.junit.Assert.*;

import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardConstants;

public class AwardHomeActionTest {
	
	

	private static final String PARENT_OF_ROOT_AWARD_NUMBER = "0000001-" + AwardConstants.ROOT_AWARD_SUFFIX;

	@Test
	public void testUpdateCurrentAwardAmountInfo_newAward_indirectEnabled() {
		ScaleTwoDecimal anticipatedTotalDirect = new ScaleTwoDecimal(15000.00);
		ScaleTwoDecimal anticipatedTotalIndirect = new ScaleTwoDecimal(1500.00);
		ScaleTwoDecimal obligatedTotalDirect = new ScaleTwoDecimal(10000.00);
		ScaleTwoDecimal obligatedTotalIndirect = new ScaleTwoDecimal(1000.00);
		AwardHomeAction action = new AwardHomeAction() {
			@Override
			public boolean isDirectIndirectViewEnabled() {
				return true;
			}
			@Override
			protected Award getActiveAwardVersion(Award award) {
				return null;
			}
		};
		
		Award award = createAwardWithDirectIndirectAmounts(anticipatedTotalDirect, anticipatedTotalIndirect,
				obligatedTotalDirect, obligatedTotalIndirect);
		action.updateCurrentAwardAmountInfo(award);
		
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAntDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAnticipatedChange());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getAmountObligatedToDate());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObligatedChange());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAntDistributableAmount());
	}

	protected Award createAwardWithDirectIndirectAmounts(ScaleTwoDecimal anticipatedTotalDirect,
			ScaleTwoDecimal anticipatedTotalIndirect, ScaleTwoDecimal obligatedTotalDirect,
			ScaleTwoDecimal obligatedTotalIndirect) {
		Award award = new Award();
		award.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
		award.getAwardAmountInfos().set(0, createNewAwardAmountInfoForTesting());
		award.getLastAwardAmountInfo().setAnticipatedTotalDirect(anticipatedTotalDirect);
		award.getLastAwardAmountInfo().setAnticipatedTotalIndirect(anticipatedTotalIndirect);
		award.getLastAwardAmountInfo().setObligatedTotalDirect(obligatedTotalDirect);
		award.getLastAwardAmountInfo().setObligatedTotalIndirect(obligatedTotalIndirect);
		return award;
	}
	
	@Test
	public void testUpdateCurrentAwardAmountInfo_copiedAward_indirectEnabled() {
		ScaleTwoDecimal anticipatedTotalDirect = new ScaleTwoDecimal(15000.00);
		ScaleTwoDecimal anticipatedTotalIndirect = new ScaleTwoDecimal(1500.00);
		ScaleTwoDecimal obligatedTotalDirect = new ScaleTwoDecimal(10000.00);
		ScaleTwoDecimal obligatedTotalIndirect = new ScaleTwoDecimal(1000.00);
		AwardHomeAction action = new AwardHomeAction() {
			@Override
			public boolean isDirectIndirectViewEnabled() {
				return true;
			}
			@Override
			protected Award getActiveAwardVersion(Award award) {
				return null;
			}
		};
		
		Award award = createAwardWithDirectIndirectAmounts(anticipatedTotalDirect, anticipatedTotalIndirect,
				obligatedTotalDirect, obligatedTotalIndirect);
		action.updateCurrentAwardAmountInfo(award);
		
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAntDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAnticipatedChange());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getAmountObligatedToDate());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObligatedChange());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAntDistributableAmount());
	}
	
	@Test
	public void testUpdateCurrentAwardAmountInfo_existingAward_indirectEnabled() {
		final Award previousAward = new Award();
		previousAward.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
		final AwardAmountInfo previousAwardAmountInfo = createNewAwardAmountInfoForTesting();
		previousAward.getAwardAmountInfos().set(0, previousAwardAmountInfo);
		ScaleTwoDecimal previousAnticipatedTotalDirect = new ScaleTwoDecimal(5000.00);
		ScaleTwoDecimal previousAnticipatedTotalIndirect = new ScaleTwoDecimal(500.00);
		ScaleTwoDecimal previousObligatedTotalDirect = new ScaleTwoDecimal(3000.00);
		ScaleTwoDecimal previousObligatedTotalIndirect = new ScaleTwoDecimal(200.00);
		previousAwardAmountInfo.setAmountObligatedToDate(previousObligatedTotalDirect.add(previousObligatedTotalIndirect));
		previousAwardAmountInfo.setAnticipatedTotalAmount(previousAnticipatedTotalDirect.add(previousAnticipatedTotalIndirect));
		previousAwardAmountInfo.setAntDistributableAmount(previousAwardAmountInfo.getAnticipatedTotalAmount());
		previousAwardAmountInfo.setObliDistributableAmount(previousAwardAmountInfo.getAmountObligatedToDate());
		
		ScaleTwoDecimal anticipatedTotalDirect = new ScaleTwoDecimal(15000.00);
		ScaleTwoDecimal anticipatedTotalIndirect = new ScaleTwoDecimal(1500.00);
		ScaleTwoDecimal obligatedTotalDirect = new ScaleTwoDecimal(10000.00);
		ScaleTwoDecimal obligatedTotalIndirect = new ScaleTwoDecimal(1000.00);
		AwardHomeAction action = new AwardHomeAction() {
			@Override
			public boolean isDirectIndirectViewEnabled() {
				return true;
			}
			@Override
			protected Award getActiveAwardVersion(Award award) {
				return previousAward;
			}
		};
		
		Award award = createAwardWithDirectIndirectAmounts(anticipatedTotalDirect, anticipatedTotalIndirect,
				obligatedTotalDirect, obligatedTotalIndirect);
		action.updateCurrentAwardAmountInfo(award);
		
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getAmountObligatedToDate());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect).subtract(previousAwardAmountInfo.getObliDistributableAmount()), 
				award.getLastAwardAmountInfo().getObligatedChange());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAntDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect).subtract(previousAwardAmountInfo.getAnticipatedTotalAmount()), 
				award.getLastAwardAmountInfo().getAnticipatedChange());
		assertEquals(anticipatedTotalDirect.subtract(previousAwardAmountInfo.getAnticipatedTotalDirect()), 
				award.getLastAwardAmountInfo().getAnticipatedChangeDirect());
	}
	
	@Test
	public void testUpdateCurrentAwardAmountInfo_previousTMAward_indirectEnabled() {
		final AwardAmountInfo previousAwardAmountInfo = createNewAwardAmountInfoForTesting();
		ScaleTwoDecimal previousAnticipatedTotalDirect = new ScaleTwoDecimal(5000.00);
		ScaleTwoDecimal previousAnticipatedTotalIndirect = new ScaleTwoDecimal(500.00);
		ScaleTwoDecimal previousObligatedTotalDirect = new ScaleTwoDecimal(3000.00);
		ScaleTwoDecimal previousObligatedTotalIndirect = new ScaleTwoDecimal(200.00);
		previousAwardAmountInfo.setAmountObligatedToDate(previousObligatedTotalDirect.add(previousObligatedTotalIndirect));
		previousAwardAmountInfo.setAnticipatedTotalAmount(previousAnticipatedTotalDirect.add(previousAnticipatedTotalIndirect));
		previousAwardAmountInfo.setAntDistributableAmount(previousAwardAmountInfo.getAnticipatedTotalAmount());
		previousAwardAmountInfo.setObliDistributableAmount(previousAwardAmountInfo.getAmountObligatedToDate());
		
		ScaleTwoDecimal anticipatedTotalDirect = new ScaleTwoDecimal(15000.00);
		ScaleTwoDecimal anticipatedTotalIndirect = new ScaleTwoDecimal(1500.00);
		ScaleTwoDecimal obligatedTotalDirect = new ScaleTwoDecimal(10000.00);
		ScaleTwoDecimal obligatedTotalIndirect = new ScaleTwoDecimal(1000.00);
		AwardHomeAction action = new AwardHomeAction() {
			@Override
			public boolean isDirectIndirectViewEnabled() {
				return true;
			}
			@Override
			protected Award getActiveAwardVersion(Award award) {
				return null;
			}
		};
		
		Award award = new Award();
		award.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
		award.getAwardAmountInfos().add(previousAwardAmountInfo);
		award.getAwardAmountInfos().add(createNewAwardAmountInfoForTesting());
		
		award.getLastAwardAmountInfo().setAnticipatedTotalDirect(anticipatedTotalDirect);
		award.getLastAwardAmountInfo().setAnticipatedTotalIndirect(anticipatedTotalIndirect);
		award.getLastAwardAmountInfo().setObligatedTotalDirect(obligatedTotalDirect);
		award.getLastAwardAmountInfo().setObligatedTotalIndirect(obligatedTotalIndirect);
		action.updateCurrentAwardAmountInfo(award);
		
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getAmountObligatedToDate());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect).subtract(previousAwardAmountInfo.getObliDistributableAmount()), 
				award.getLastAwardAmountInfo().getObligatedChange());
		assertEquals(obligatedTotalDirect.add(obligatedTotalIndirect), award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect), award.getLastAwardAmountInfo().getAntDistributableAmount());
		assertEquals(anticipatedTotalDirect.add(anticipatedTotalIndirect).subtract(previousAwardAmountInfo.getAnticipatedTotalAmount()), 
				award.getLastAwardAmountInfo().getAnticipatedChange());
		assertEquals(anticipatedTotalDirect.subtract(previousAwardAmountInfo.getAnticipatedTotalDirect()), 
				award.getLastAwardAmountInfo().getAnticipatedChangeDirect());
	}
	
	@Test
	public void testUpdateCurrentAwardAmountInfo_previousTMAward() {
		final AwardAmountInfo previousAwardAmountInfo = createNewAwardAmountInfoForTesting();
		ScaleTwoDecimal previousAnticipated = new ScaleTwoDecimal(5000.00);
		ScaleTwoDecimal previousObligated = new ScaleTwoDecimal(3000.00);
		previousAwardAmountInfo.setAmountObligatedToDate(previousObligated);
		previousAwardAmountInfo.setAnticipatedTotalAmount(previousAnticipated);
		previousAwardAmountInfo.setAntDistributableAmount(previousAwardAmountInfo.getAnticipatedTotalAmount());
		previousAwardAmountInfo.setObliDistributableAmount(previousAwardAmountInfo.getAmountObligatedToDate());
		
		ScaleTwoDecimal anticipated = new ScaleTwoDecimal(15000.00);
		ScaleTwoDecimal obligated = new ScaleTwoDecimal(10000.00);
		AwardHomeAction action = new AwardHomeAction() {
			@Override
			public boolean isDirectIndirectViewEnabled() {
				return false;
			}
			@Override
			protected Award getActiveAwardVersion(Award award) {
				return null;
			}
		};
		
		Award award = new Award();
		award.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
		award.getAwardAmountInfos().add(previousAwardAmountInfo);
		award.getAwardAmountInfos().add(createNewAwardAmountInfoForTesting());
		
		award.getLastAwardAmountInfo().setAnticipatedTotalAmount(anticipated);
		award.getLastAwardAmountInfo().setAmountObligatedToDate(obligated);
		action.updateCurrentAwardAmountInfo(award);
		
		assertEquals(obligated, award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(obligated, award.getLastAwardAmountInfo().getAmountObligatedToDate());
		assertEquals(obligated.subtract(previousAwardAmountInfo.getObliDistributableAmount()), 
				award.getLastAwardAmountInfo().getObligatedChange());
		assertEquals(obligated, award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(anticipated, award.getLastAwardAmountInfo().getAntDistributableAmount());
		assertEquals(anticipated.subtract(previousAwardAmountInfo.getAnticipatedTotalAmount()), 
				award.getLastAwardAmountInfo().getAnticipatedChange());
	}
	
	@Test
	public void testUpdateCurrentAwardAmountInfo_newAward() {
		ScaleTwoDecimal anticipated = new ScaleTwoDecimal(15000.00);
		ScaleTwoDecimal obligated = new ScaleTwoDecimal(10000.00);
		AwardHomeAction action = new AwardHomeAction() {
			@Override
			public boolean isDirectIndirectViewEnabled() {
				return false;
			}
			@Override
			protected Award getActiveAwardVersion(Award award) {
				return null;
			}
		};
		
		Award award = new Award();
		award.setAwardNumber(PARENT_OF_ROOT_AWARD_NUMBER);
		award.getAwardAmountInfos().set(0, createNewAwardAmountInfoForTesting());
		
		award.getLastAwardAmountInfo().setAnticipatedTotalAmount(anticipated);
		award.getLastAwardAmountInfo().setAmountObligatedToDate(obligated);
		action.updateCurrentAwardAmountInfo(award);
		
		assertEquals(obligated, award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(obligated, award.getLastAwardAmountInfo().getAmountObligatedToDate());
		assertEquals(obligated, award.getLastAwardAmountInfo().getObligatedChange());
		assertEquals(obligated, award.getLastAwardAmountInfo().getObliDistributableAmount());
		assertEquals(anticipated, award.getLastAwardAmountInfo().getAntDistributableAmount());
		assertEquals(anticipated, award.getLastAwardAmountInfo().getAnticipatedChange());
	}

	protected AwardAmountInfo createNewAwardAmountInfoForTesting() {
		return new AwardAmountInfo() {
			@Override
			protected void changeUpdateElements(Object newObject, Object oldObject) {
				
			}
		};
	}
}
