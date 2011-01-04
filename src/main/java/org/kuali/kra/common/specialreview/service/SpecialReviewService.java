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

import org.kuali.kra.irb.Protocol;

/**
 * Provides services for linking an IRB Protocol with other modules.
 */
public interface SpecialReviewService {
    
    /**
     * Gets the Protocol from the protocol number included in the parameter map.
     *
     * @param parameters The parameters from the request
     * @return the Protocol if the protocol number is in the request, null otherwise
     */
    Protocol getProtocol(Map<String, String[]> parameters);
    
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
     * @return the route header id of the protocol document
     * @throws Exception
     */
    Long getViewSpecialReviewProtocolRouteHeaderId(String protocolNumber) throws Exception;

}