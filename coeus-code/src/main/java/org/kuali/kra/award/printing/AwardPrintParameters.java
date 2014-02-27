/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.printing;

/**
 * This class represents different parameters of reports for Award Printing
 */
public enum AwardPrintParameters {
		ADDRESS_LIST("addressList"), 
		FOREIGN_TRAVEL("foreignTravel"), 
		REPORTING("reporting"), CLOSEOUT("closeout"), 
		FUNDING_SUMMARY("fundingSummary"), 
		SPECIAL_REVIEW("specialReview"), 
		COMMENTS("comments"), 
		HIERARCHY_INFO("hierarchyInfo"), 
		SUBCONTRACT("subcontract"), 
		COST_SHARING("costSharing"), 
		KEYWORDS("keywords"), 
		TECHNICAL_REPORTING("technicalReporting"), 
		EQUIPMENT("equipment"), 
		OTHER_DATA("otherData"), 
		TERMS("terms"), 
		FA_COST("fACost"), 
		PAYMENT("payment"), 
		FLOW_THRU("flowThru"), 
		PROPOSAL_DUE("proposalDue"), 
		INDIRECT_COST("indirectCost"), 
		SCIENCE_CODE("scienceCode"), 
		SIGNATURE_REQUIRED("signatureRequired"), 
		SEQUENCE_NUMBER("sequenceNumber"), 
		TRANSACTION_ID("transactionId"), 
		TRANSACTION_ID_INDEX("transactionIdIndex");
		
	private final String awardPrintParameter;

	AwardPrintParameters(String awardPrintParameter) {
		this.awardPrintParameter = awardPrintParameter;
	}

	public String getAwardPrintParameter() {
		return awardPrintParameter;
	}

}
