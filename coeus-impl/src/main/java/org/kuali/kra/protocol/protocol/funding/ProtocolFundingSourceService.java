/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.protocol.funding;

import org.kuali.kra.protocol.ProtocolActionBase;

import java.util.Map.Entry;

/**
 * Provides the required API for performing a multi-type lookup for funding sources, business rules used by the View, and business rule management for a 
 * protocol's funding source list.
 */
public interface ProtocolFundingSourceService {

    /**
     * Updates the name and title for a ProtocolBase funding source.
     * 
     * @param fundingSourceTypeCode the type code of the funding source
     * @param fundingSourceNumber the human-readable number of the funding source
     * @param fundingSourceName the name of the funding source
     * @return an instance of a ProtocolBase funding source
     */
    ProtocolFundingSourceBase updateProtocolFundingSource(String fundingSourceTypeCode, String fundingSourceNumber, String fundingSourceName);
    
    /**
     * Checks if the identifier contained in {@code protocolFundingSource} is valid for the type (e.g. for type Unit, verifies whether it has a valid unit 
     * number).
     * 
     * @param protocolFundingSource the ProtocolBase funding source to validate
     * @return true if the ProtocolBase funding source has a valid identifier, false otherwise
     */
    boolean isValidIdForType(ProtocolFundingSourceBase protocolFundingSource);
    
    /**
     * Returns lookup parameters to create a funding source lookup URL based its type.
     * 
     * @param fundingSourceTypeCode the type code of the funding source
     * @return a map of lookup parameters
     */
    Entry<String, String> getLookupParameters(String fundingSourceTypeCode);
    
    /**
     * Modifies the generic lookup URL contained in {@code parameter} to include the {@code boClassName} and {@code fieldConversions}.
     * 
     * @param parameter the generic lookup URL
     * @param boClassName the class name used in the lookup
     * @param fieldConversions the field conversions between the lookup and the page
     * @return a lookup URL specific to the given parameters
     */
    String updateLookupParameter(String parameter, String boClassName, String fieldConversions);


    /**
     * Creates a view URL for the ProtocolBase funding source.
     * 
     * @param protocolFundingSource the funding source to view
     * @param action a back reference back to the action
     * @return a valid URL to view the funding source
     * @throws Exception
     */
    String getViewProtocolFundingSourceUrl(ProtocolFundingSourceBase protocolFundingSource, ProtocolActionBase action) throws Exception;
    
    /**
     * Returns whether the name attribute is editable according to {@code fundingSourceTypeCode}.
     * 
     * @param fundingSourceTypeCode the type code of the funding source
     * @return true if the name attribute is editable, false otherwise
     */
    boolean isEditable(String fundingSourceTypeCode);
    
    /**
     * Returns whether a lookup can be performed according to {@code fundingSourceTypeCode}.
     * 
     * @param fundingSourceTypeCode the type code of the funding source
     * @return true if a lookup can be performed, false otherwise
     */
    boolean isLookupable(String fundingSourceTypeCode);
    
}
