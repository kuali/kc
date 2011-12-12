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
package org.kuali.kra.negotiations.service;

import java.util.List;

import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationActivity;
import org.kuali.kra.negotiations.bo.NegotiationActivityHistoryLineBean;
import org.kuali.kra.negotiations.bo.NegotiationAssociatedDetailBean;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.kra.negotiations.bo.NegotiationStatus;
import org.kuali.kra.negotiations.bo.NegotiationUnassociatedDetail;
import org.kuali.rice.kns.bo.BusinessObject;

/**
 * Service to help with Negotiation and working with Negotiations.
 */
public interface NegotiationService {
    
    /**
     * Get the in-progress status codes.
     * @return
     */
    List<String> getInProgressStatusCodes();
    
    /**
     * Get the completed status codes.
     * @return
     */
    List<String> getCompletedStatusCodes();
    
    /**
     * Get the completed status code. Primarily used to trigger the closed notification.
     * @return
     */
    String getCompleteStatusCode();
    
    /**
     * 
     * This method returns the associated Negotiable implemetned BO for the passed in negotiation.
     * @param negotiation
     * @return
     */
    Negotiable getAssociatedObject(Negotiation negotiation);
    
    /**
     * 
     * This method gets the award, proposal log, institutional proposal or sub award from the negotiation's associated doc ID, and
     * create a NegotiationAssociatedDetailBean to pass back;
     * @param negotiation
     * @return
     */
    NegotiationAssociatedDetailBean buildNegotiationAssociatedDetailBean(Negotiation negotiation);
    
    /**
     * Get any negotiations associated with BO(ProposalLog, Inst Prop, Award, Subaward).
     * User primarly by Medusa.
     * @param bo
     * @return
     */
    List<Negotiation> getAssociatedNegotiations(BusinessObject bo);
    
    /**
     * Retrieve the association type BO.
     * @param associationTypeCode
     * @return
     */
    NegotiationAssociationType getNegotiationAssociationType(String associationTypeCode);
    
    /**
     * Retrieve the status BO.
     * @param statusCode
     * @return
     */
    NegotiationStatus getNegotiationStatus(String statusCode);

    /**
     * Can a negotiation be linked to a proposal log?
     * @return
     */
    boolean isProposalLogLinkingEnabled();

    /**
     * Can a negotiation be linked to an inst prop?
     * @return
     */
    boolean isInstitutionalProposalLinkingEnabled();
    
    /**
     * Can a negotiation be linked to an award?
     * @return
     */
    boolean isAwardLinkingEnabled();
    
    /**
     * Can a negotiation be linked to a subaward?
     * @return
     */
    boolean isSubawardLinkingEnabled();
    
    /**
     * Can a negotiation be linked to nothing?
     * @return
     */
    boolean isNoModuleLinkingEnabled();
    
    /**
     * If the negotiation is linked to a proposal log that has been promoted to a inst prop, then
     * link the negotiation to the new inst prop.
     */
    void checkForPropLogPromotion(Negotiation negotiation);
    
    /**
     * 
     * This method checks to see if the passed in person id is the PI, CO-PI, or KeyPerson on the associated document.
     * @param negotiation
     * @param personToCheckPersonId
     * @return
     */
    boolean isPersonIsAssociatedPerson(Negotiation negotiation, String personToCheckPersonId);

    /**
     * This method fine a NegotiationAssociatedDetail object from the DB and sets it to the passed in negotiation.
     * @param negotiation
     * @return
     */
    NegotiationUnassociatedDetail findAndLoadNegotiationUnassociatedDetail(Negotiation negotiation);
        
    List<NegotiationActivityHistoryLineBean> getNegotiationActivityHistoryLineBeans(List<NegotiationActivity> activities);
    
    /**
     * 
     * This method finds the negotiation, if it exists, that is association with the proposal log, and changes it's
     * association to the institutional proposal.
     * @param proposalLogProposalNumber
     * @param institutionalProposalProposalNumber
     */
    void promoteProposalLogNegotiation(String proposalLogProposalNumber, String institutionalProposalProposalNumber);
}
