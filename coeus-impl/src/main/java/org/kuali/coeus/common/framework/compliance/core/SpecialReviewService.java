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
package org.kuali.coeus.common.framework.compliance.core;

import org.kuali.rice.kew.api.exception.WorkflowException;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Provides services for linking an IRB Protocol with other modules.
 */
public interface SpecialReviewService {
    
    /**
     * Gets the save location prefix string before the protocol number included in the parameter map.
     * <p>
     * This prefix can either be <code>specialReviewHelper.newSpecialReview</code> for a new Special Review or a string similar to 
     * <code>document.protocolList[0].specialReviews[0]</code> for an existing Special Review.
     * 
     * @param parameters The parameters from the request
     * @return the prefix string
     */
    String getProtocolSaveLocationPrefix(Map<String, String[]> parameters);
    
    /**
     * Gets the index of the protocol included in the prefix string.  This string must be in a form similar to 
     * <code>document.protocolList[0].specialReviews[0]</code>, or else the method will return -1.
     * 
     * @param prefix The prefix string in the form <code>document.protocolList[0].specialReviews[0]</code>
     * @return the index of the protocol, or -1 if it does not exist
     */
    int getProtocolIndex(String prefix);

    /**
     * Gets the route header id of the document represented by the given protocolNumber.
     * 
     * @param protocolNumber The number of the protocol
     * @return the route header id of the protocol document, or 0L if the protocolNumber is invalid
     * @throws Exception
     */
    public String getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber) throws Exception;
    
    /**
     * Gets the route header id of the document represented by the given protocolNumber.
     * 
     * @param protocolNumber The number of the protocol
     * @param specialReviewTypeCode for the protocol
     * @return the route header id of the protocol document, or 0L if the protocolNumber is invalid
     * @throws Exception
     */
    public String getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber, String specialReviewTypeCode) throws Exception;

    /**
     * Determines whether the given Protocol contains a Funding Source linking to the Institutional Proposal or Award corresponding to the given number.
     * 
     * @param protocolNumber The number of the Protocol in which to check for the Funding Source
     * @param fundingSourceNumber The number of the Institutional Proposal or Award to check for
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award) to check for
     * @return
     */
    boolean isLinkedToProtocolFundingSource(String protocolNumber, String fundingSourceNumber, String fundingSourceTypeCode);
    
    /**
     * Determines whether the given Institutional Proposal or Award Protocol contains a Special Review linking to the given protocol number.
     * 
     * @param fundingSourceNumber The number of the Institutional Proposal or Award in which to check for the Special Review
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award) in which to check for the Special Review
     * @param protocolNumber The number of the Protocol to check for
     * @return
     */
    boolean isLinkedToSpecialReview(String fundingSourceNumber, String fundingSourceTypeCode, String protocolNumber);
    
    /**
     * Creates a Protocol Funding Source from a Special Review contained in either an Institutional Proposal or Award and adds it to the Protocol.
     * 
     * @param protocolNumber The number of the Protocol to which to add the new Funding Source
     * @param fundingSourceNumber The human-readable number of the Institutional Proposal or Award in which the Special Review is added/saved
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award) of the entity in which the Special Review is added/saved
     * @param fundingSourceName The name of the Institutional Proposal or Award in which the Special Review is added/saved
     * @param fundingSourceTitle The title of the Institutional Proposal or Award in which the Special Review is added/saved
     * @throws WorkflowException
     */
    void addProtocolFundingSourceForSpecialReview(String protocolNumber, String fundingSourceNumber, String fundingSourceTypeCode, String fundingSourceName, 
        String fundingSourceTitle);
    
    /**
     * Deletes the Protocol Funding Sources associated with the Special Review being deleted.
     * 
     * @param protocolNumber The number of the Protocol to which to delete the Funding Sources
     * @param fundingSourceNumber The number of the Institutional Proposal or Award in which the Special Review is deleted
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award) of the entity in which the Special Review is deleted
     * @throws WorkflowException
     */
    void deleteProtocolFundingSourceForSpecialReview(String protocolNumber, String fundingSourceNumber, String fundingSourceTypeCode);

    /**
     * Creates an Institutional Proposal or Award Special Review based on the given Protocol and adds it to the specified Institutional Proposal or Award.
     * 
     * @param fundingSourceNumber The number of the Institutional Proposal or Award
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award)
     * @param protocolNumber The number of the given Protocol
     * @param applicationDate The application date (submission date) of the given Protocol
     * @param approvalDate The approval date of the given Protocol
     * @param expirationDate The expiration date of the given Protocol
     * @param specialReviewType The type code of the Protocol (Animal/Human)
     * @param exemptionTypeCodes The exemption type codes of the given Protocol
     * @throws WorkflowException
     */
    void addSpecialReviewForProtocolFundingSource(String fundingSourceNumber, String fundingSourceTypeCode, String protocolNumber, Date applicationDate, 
        Date approvalDate, Date expirationDate, String specialReviewType, List<String> exemptionTypeCodes);
    
    /**
     * Deletes the Institutional Proposal Special Review associated with the Protocol Funding Source being deleted.
     * 
     * @param fundingSourceNumber The number of the Institutional Proposal or Award to which to delete the Special Review
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award) to which to delete the Special Review
     * @param protocolNumber The number of the Protocol in which the Funding Source is deleted
     * @throws WorkflowException
     */
    void deleteSpecialReviewForProtocolFundingSource(String fundingSourceNumber, String fundingSourceTypeCode, String protocolNumber);
}
