/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.protocol;

import java.util.HashMap;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;

/**
 * This Service provides the required API for performing a multi-type lookup for funding sources. Business rules used by the View,
 * And business rule management for a a protocol's funding source list.
 */
public interface ProtocolFundingSourceService {
    
    /**
     * This method deletes ProtocolFundingSource from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolFundingSources
     * @param lineNumber to be deleted
     */
    public void deleteProtocolFundingSource(Protocol protocol, int lineNumber);

    /**
     * 
     * This method is used used by Ajax and the action to set the name and title for a funding source.
     * @param sourceId
     * @param sourceType
     * @param sourceName
     * @return
     */
    public ProtocolFundingSource updateProtocolFundingSource(String sourceId, String sourceType, String sourceName);
    
    /**
     * 
     * This method checks if funding source id is valid for the type (e.g. If type is Unit, is it a valid UnitNumber)
     * @param source
     * @return
     */
    public boolean isValidIdForType(ProtocolFundingSource source);
    

    /**
     * 
     * This method returns lookup parameters to create a fundingSource lookup URL based on funding source type
     * @param boName
     * @return
     */
    public HashMap<String, String> getLookupParameters(String boName);
    
    /**
     * 
     * This method creates the actual lookup URL for a funding source lookup
     * @param parameter
     * @param boClassName
     * @param fieldConversions
     * @return
     */
    public String updateLookupParameter(String parameter, String boClassName, String fieldConversions);
        
    /**
     * 
     * This method is used by protocolFundingSource Lookup action to create view URL based on funding source type
     * @param protocolFundingSource
     * @param action
     * @return
     * @throws Exception
     */
    public String getViewProtocolFundingSourceUrl(ProtocolFundingSource protocolFundingSource, ProtocolProtocolAction action) throws Exception;
    
    /**
     * 
     * This method is used by Ajax call to make fundingSource Name editable for certain types of funding sources
     * @param fundingTypeCode
     * @return
     */
    public boolean updateSourceNameEditable(String fundingTypeCode);
    
    /**
     * 
     * This method used to set the view button based on funding type and configuration
     * @param fundingTypeCode
     * @return
     */
    public boolean isViewable(int fundingTypeCode);
}
