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
package org.kuali.kra.negotiations.service;

import org.kuali.kra.negotiations.bo.*;
import org.kuali.kra.negotiations.notifications.NegotiationNotification;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.List;

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
     * This method fine a NegotiationAssociatedDetail object from the DB and sets it to the passed in negotiation.
     * @param negotiation
     * @return
     */
    NegotiationUnassociatedDetail findAndLoadNegotiationUnassociatedDetail(Negotiation negotiation);
    
    List<NegotiationActivityHistoryLineBean> getNegotiationActivityHistoryLineBeans(List<NegotiationActivity> activities);
    
    List<NegotiationNotification> getNegotiationNotifications(Negotiation negotiation);
    
    /**
     * 
     * This method finds the negotiation, if it exists, that is association with the proposal log, and changes it's
     * association to the institutional proposal.
     * @param proposalLogProposalNumber
     * @param institutionalProposalProposalNumber
     */
    void promoteProposalLogNegotiation(String proposalLogProposalNumber, String institutionalProposalProposalNumber);
}
