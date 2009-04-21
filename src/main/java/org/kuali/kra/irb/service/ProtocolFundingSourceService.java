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
package org.kuali.kra.irb.service;

import java.util.HashMap;

import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolFundingSource;
import org.kuali.kra.irb.lookup.ProtocolLookupableHelperServiceImpl;
import org.kuali.kra.irb.web.struts.action.ProtocolProtocolAction;

public interface ProtocolFundingSourceService {


    /**
     * This method adds ProtocolFundingSource to the List of ProtocolFundingSources.
     * @param protocol which contains list of ProtocolFundingSources.
     */
    public abstract void addProtocolFundingSource(Protocol protocol, ProtocolFundingSource fundingSource);
    

    /**
     * This method deletes ProtocolFundingSource from the List at specified position(lineNumber)
     * @param protocol which contains list of ProtocolFundingSources
     * @param lineNumber to be deleted
     */
    public abstract void deleteProtocolFundingSource(Protocol protocol, int lineNumber);

    
    public abstract ProtocolFundingSource calculateProtocolFundingSource(String sourceId, String sourceType, String sourceName, String sourceTitle);
    
    /**
     * 
     * This method checks if funding source id is valid for the type (e.g. If type is Unit, is it a valid UnitNumber)
     * @param source
     * @return
     */
    public boolean isValidIdForType(ProtocolFundingSource source);
    

    public HashMap<String, String> getLookupParameters(String boName);
    
    public String updateLookupParameter(String parameter, String boClassName, String fieldConversions);
    
    public ProtocolLookupableHelperServiceImpl getProtocolLookupableHelperService();
    
    public String getViewProtocolFundingSourceUrl(ProtocolFundingSource protocolFundingSource, ProtocolProtocolAction action) throws Exception;
}
