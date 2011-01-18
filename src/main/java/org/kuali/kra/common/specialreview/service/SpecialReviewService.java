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
package org.kuali.kra.common.specialreview.service;

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
    Long getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber) throws Exception;
    
    /**
     * Determines whether the given Protocol contains a Funding Source linking to the Institutional Proposal or Award corresponding to the given id.
     * 
     * @param protocolNumber The number of the Protocol in which to check for the Funding Source
     * @param fundingSourceId The id of the Institutional Proposal or Award to check for
     * @param fundingSourceType The type code (for either Institutional Proposal or Award) to check for
     * @return
     */
    boolean isLinkedToProtocolFundingSource(String protocolNumber, Long fundingSourceId, String fundingSourceType);
    
    /**
     * Adds a Protocol Funding Source from a Special Review contained in either an Institutional Proposal or Award and adds it to the Protocol.
     * <p>
     * In order to avoid locking problems, saves the changes directly to the Protocol BO instead of the document.
     * 
     * @param protocolNumber The number of the Protocol to which to add the new Funding Source
     * @param fundingSourceId The id of the Institutional Proposal or Award in which the Special Review is added/saved
     * @param fundingSourceNumber The human-readable number of the Institutional Proposal or Award in which the Special Review is added/saved
     * @param fundingSourceTypeCode The type code (for either Institutional Proposal or Award) of the entity in which the Special Review is added/saved
     * @param fundingSourceName The name of the Institutional Proposal or Award in which the Special Review is added/saved
     * @param fundingSourceTitle The title of the Institutional Proposal or Award in which the Special Review is added/saved
     */
    void addProtocolFundingSourceForSpecialReview(String protocolNumber, Long fundingSourceId, String fundingSourceNumber, String fundingSourceTypeCode, 
        String fundingSourceName, String fundingSourceTitle);
    
    /**
     * Deletes the Protocol Funding Sources associated with the Special Review being deleted.
     * <p>
     * In order to avoid locking problems, saves the changes directly to the Protocol BO instead of the document.
     * 
     * @param protocolNumber The number of the Protocol to which to delete the Funding Sources
     * @param fundingSourceId The id of the Institutional Proposal or Award in which the Special Review is deleted
     * @param fundingSourceType The type code (for either Institutional Proposal or Award) of the entity in which the Special Review is deleted
     */
    void deleteProtocolFundingSourceForSpecialReview(String protocolNumber, Long fundingSourceId, String fundingSourceType);

}