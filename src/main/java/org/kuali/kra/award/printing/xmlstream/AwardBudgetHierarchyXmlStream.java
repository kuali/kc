/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.printing.xmlstream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.bytecode.Descriptor.Iterator;

import noNamespace.AmountInfoType;
import noNamespace.AwardNoticeDocument;
import noNamespace.AwardType;
import noNamespace.AwardNoticeDocument.AwardNotice;
import noNamespace.AwardType.AwardAmountInfo;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.rice.kns.service.BusinessObjectService;
/**
 * This class generates XML that conforms with the XSD related to Award Budget
 * hierarchy Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 * @author
 * 
 */
public class AwardBudgetHierarchyXmlStream extends AwardBudgetBaseStream {
	/**
	 * This method generates XML for Award Budget Hierarchy Report. It uses data
	 * passed in {@link ResearchDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */

  
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
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
	
		AwardAmountInfo awardAmountInfo = AwardAmountInfo.Factory.newInstance();
		List<AmountInfoType> amountInfoTypes = new ArrayList<AmountInfoType>();
		AwardHierarchy branchNode = award.getAwardHierarchyService().loadAwardHierarchy(award.getAwardNumber());
	     List<Award> order=new ArrayList<Award>();
	      AwardHierarchy rootNode = branchNode.getRoot();
	      List<Award> awardList = new ArrayList<Award>();
	      Award parentAward=null;
	     if(branchNode!=null){
          Map<String, Collection<AwardHierarchy>> mapOfChildren = new HashMap<String, Collection<AwardHierarchy>>();
          Map<String, AwardHierarchy> awardHierarchies = createAwardHierarchy(rootNode, mapOfChildren);
          String parentAwardNumber = branchNode.getRootAwardNumber();        
          BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);   
          Collection<Award> awards = businessObjectService.findAll(Award.class);
	      for(Award awardParent : awards){
	       if(awardParent.getAwardNumber().equals(parentAwardNumber)){
	         parentAward = awardParent;
	         break;
	        }
	    }
          if(awardHierarchies.size() >= 1){
               awardList = createSortHierarchy(order, awardHierarchies, mapOfChildren, parentAwardNumber, null, null);
          }
             awardList.add(0,parentAward);
          for(Award awardNode :awardList){
              org.kuali.kra.award.home.AwardAmountInfo awardAmount=awardNode.getLastAwardAmountInfo();
              AmountInfoType amountInfoType =AmountInfoType.Factory.newInstance();
              amountInfoType = setAwardAmountInfo(awardNode, awardAmount,amountInfoType);
              amountInfoTypes.add(amountInfoType);
          }
        		
			awardAmountInfo.setAmountInfoArray(amountInfoTypes.toArray(new AmountInfoType[0]));
			 }
		return awardAmountInfo;
	}

	/*
	 * This method will set the values to award amount info xml object
	 * attributes .
	 */
	
	private AmountInfoType setAwardAmountInfo(Award award,
			org.kuali.kra.award.home.AwardAmountInfo awardAmount,AmountInfoType amountInfoType )
	       {	   
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
		if (award.getAwardNumber()!= null) {
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

    
    protected Map<String, AwardHierarchy> createAwardHierarchy(AwardHierarchy awardHierarchyRootNode,
            Map<String, Collection<AwardHierarchy>> mapOfChildren) {
            Map<String, AwardHierarchy> hierarchyMap = new HashMap<String, AwardHierarchy>();
            createAwardHierarchyMap(hierarchyMap, awardHierarchyRootNode, mapOfChildren);
            return hierarchyMap;
        }
            protected void createAwardHierarchyMap(Map<String, AwardHierarchy> hierarchyMap, AwardHierarchy node, Map<String, Collection<AwardHierarchy>> mapOfChildren) {
            if(node != null) {
            hierarchyMap.put(node.getAwardNumber(), node);
           mapOfChildren.put(node.getAwardNumber(), new ArrayList<AwardHierarchy>(node.getChildren()));
            for(AwardHierarchy childNode: node.getChildren()) {
            createAwardHierarchyMap(hierarchyMap, childNode, mapOfChildren);
            }
            }
           }

    private List createSortHierarchy(List<Award> listForAwardHierarchySort, Map<String, AwardHierarchy> awardHierarchies,
            Map<String, Collection<AwardHierarchy>> mapOfChildren, String parentAwardNumber,
            Collection<AwardHierarchy> ahCollection, AwardHierarchy awardHierarchy) {

                while(!StringUtils.equalsIgnoreCase(parentAwardNumber,Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                if(mapOfChildren.get(parentAwardNumber).size()!=0){
                    ahCollection = mapOfChildren.get(parentAwardNumber);
                    awardHierarchy = ahCollection.iterator().next();
                    parentAwardNumber = awardHierarchy.getAwardNumber();
                    listForAwardHierarchySort.add(awardHierarchy.getAward());
                    }else if(ahCollection!=null && ahCollection.size() ==0){
                        awardHierarchy = awardHierarchies.get(awardHierarchies.get(parentAwardNumber).getAwardNumber());
                    if(awardHierarchy!=null){
                    parentAwardNumber = awardHierarchy.getParentAwardNumber();
                if(!StringUtils.equalsIgnoreCase(parentAwardNumber,Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT)){
                   mapOfChildren.get(parentAwardNumber).remove(awardHierarchy);
                    }
                    }else{
                        parentAwardNumber = Constants.AWARD_HIERARCHY_DEFAULT_PARENT_OF_ROOT;
                    }
                    }
                    else if(awardHierarchy!=null){
                    parentAwardNumber = awardHierarchy.getParentAwardNumber();
                    ahCollection.remove(awardHierarchy);
                    }
                  
                }
        return listForAwardHierarchySort;
        }
 
}

