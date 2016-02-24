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
package org.kuali.kra.award.printing.xmlstream;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.printing.schema.AmountInfoType;
import org.kuali.kra.printing.schema.AwardNoticeDocument;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice;
import org.kuali.kra.printing.schema.AwardType;
import org.kuali.kra.printing.schema.AwardType.AwardAmountInfo;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;
/**
 * This class generates XML that conforms with the XSD related to Award Budget
 * hierarchy Report. The data for XML is derived from
 * {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} and {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class AwardBudgetHierarchyXmlStream extends AwardBudgetBaseStream {
	/**
	 * This method generates XML for Award Budget Hierarchy Report. It uses data
	 * passed in {@link org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> budgetHierarchyMap = new HashMap<String, XmlObject>();
		Award award = (Award) printableBusinessObject;
		AwardNoticeDocument awardNoticeDocument = AwardNoticeDocument.Factory.newInstance();
		AwardNotice awardNotice = AwardNotice.Factory.newInstance();
		awardNotice.setAward(getAwardType(award));
		awardNotice.setSchoolInfo(getSchoolInfoType());
		awardNoticeDocument.setAwardNotice(awardNotice);
		awardNoticeDocument.setAwardNotice(awardNotice);
		budgetHierarchyMap.put(AwardPrintType.AWARD_BUDGET_HIERARCHY
				.getAwardPrintType(), awardNoticeDocument);
		return budgetHierarchyMap;
	}

	/*
	 * This method will set the values to Award type xml object attributes. It
	 * will set the following values like award amount info , Transaction info .
	 * 
	 */
	private AwardType getAwardType(Award award) {
		AwardType awardType = AwardType.Factory.newInstance();
		awardType.setAwardAmountInfo(getAwardAmountInfo(award));
		awardType.setAwardTransactionInfo(getAwardTransactiontInfo(award));
		return awardType;
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes.
	 */
	private AwardAmountInfo getAwardAmountInfo(Award award) {
		AmountInfoType amountInfoType = null;
		AwardAmountInfo awardAmountInfo = AwardAmountInfo.Factory.newInstance();
		List<AmountInfoType> amountInfoTypes = new ArrayList<AmountInfoType>();
		AwardHierarchy branchNode=award.getAwardHierarchyService().loadFullHierarchyFromAnyNode(award.getParentNumber());
        BusinessObjectService businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        Collection<Award> awards = businessObjectService.findMatching(Award.class, Collections.singletonMap("awardNumber", branchNode.getAwardNumber()));
        Award parentAward = (!awards.isEmpty()) ? awards.iterator().next() : null;

		amountInfoType = setAwardAmountInfo(parentAward, parentAward.getLastAwardAmountInfo());
		amountInfoTypes = recurseTree(branchNode,amountInfoTypes);
		amountInfoTypes.add(0,amountInfoType);
		awardAmountInfo.setAmountInfoArray(amountInfoTypes.toArray(new AmountInfoType[0]));
		return awardAmountInfo;
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes .
	 */
	private AmountInfoType setAwardAmountInfo(Award award,
			org.kuali.kra.award.home.AwardAmountInfo awardAmount) {
	    AmountInfoType amountInfoType = AmountInfoType.Factory.newInstance();
		if (award.getAccountNumber() != null) {
			amountInfoType.setAccountNumber(award.getAccountNumber());
		}
		if (awardAmount.getTransactionId() != null) {
			amountInfoType.setAmountSequenceNumber(awardAmount
					.getTransactionId().intValue());
		}
		if (awardAmount.getAmountObligatedToDate() != null) {
			amountInfoType.setAmtObligatedToDate(awardAmount
					.getAmountObligatedToDate().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedChange() != null) {
			amountInfoType.setAnticipatedChange(awardAmount
					.getAnticipatedChange().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedChangeDirect() != null) {
			amountInfoType.setAnticipatedChangeDirect(awardAmount.getAnticipatedChangeDirect().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedChangeIndirect() != null) {
			amountInfoType.setAnticipatedChangeIndirect(awardAmount.getAnticipatedChangeIndirect().bigDecimalValue());
		}
		if (awardAmount.getAntDistributableAmount() != null) {
			amountInfoType.setAnticipatedDistributableAmt(awardAmount
					.getAntDistributableAmount().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalAmount() != null) {
			amountInfoType.setAnticipatedTotalAmt(awardAmount
					.getAnticipatedTotalAmount().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalDirect() != null) {
			amountInfoType.setAnticipatedTotalDirect(awardAmount
					.getAnticipatedTotalDirect().bigDecimalValue());
		}
		if (awardAmount.getAnticipatedTotalIndirect() != null) {
			amountInfoType.setAnticipatedTotalIndirect(awardAmount
					.getAnticipatedTotalIndirect().bigDecimalValue());
		}
		if (award.getAwardNumber() != null) {
			amountInfoType.setAwardNumber(award.getAwardNumber());
		}
		if (awardAmount.getObligationExpirationDate() != null) {
			amountInfoType.setObligationExpirationDate(dateTimeService
					.getCalendar(awardAmount.getObligationExpirationDate()));
		}
		if (awardAmount.getObligatedTotalIndirect() != null) {
			amountInfoType.setObligatedTotalIndirect(awardAmount
					.getObligatedTotalIndirect().bigDecimalValue());
		}
		if (awardAmount.getObligatedTotalDirect() != null) {
			amountInfoType.setObligatedTotalDirect(awardAmount
					.getObligatedTotalDirect().bigDecimalValue());
		}
		if (awardAmount.getCurrentFundEffectiveDate() != null) {
			amountInfoType.setCurrentFundEffectiveDate(dateTimeService
					.getCalendar(awardAmount.getCurrentFundEffectiveDate()));
		}
		// TODO : to be fixed
		// amountInfoType.setTreeLevel(awardAmount.get);
		return amountInfoType;
	}
	

    private List<AmountInfoType> recurseTree(AwardHierarchy branchNode,List<AmountInfoType> amountInfoTypes) {

        Map<String, Object> criteria = CollectionUtils.zipMap(new String[]{"parentAwardNumber", "active"}, new Object[]{branchNode.getAwardNumber(), Boolean.TRUE});
        Collection c = businessObjectService.findMatchingOrderBy(AwardHierarchy.class, criteria, AwardHierarchy.UNIQUE_IDENTIFIER_FIELD, true);
        branchNode.setChildren(new ArrayList<AwardHierarchy>(c));
        if(branchNode.hasChildren()) {
          for(AwardHierarchy childNode: branchNode.getChildren()) {
              org.kuali.kra.award.home.AwardAmountInfo awardAmount= childNode.getAward().getLastAwardAmountInfo();
              amountInfoTypes.add(setAwardAmountInfo(childNode.getAward(), awardAmount));
              childNode.setParent(branchNode);
              childNode.setRoot(branchNode.getRoot());  
              recurseTree(childNode,amountInfoTypes);
             
}
        } 
       return amountInfoTypes;
    }
    
 
}

