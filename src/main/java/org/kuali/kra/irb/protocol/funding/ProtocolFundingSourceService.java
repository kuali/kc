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
package org.kuali.kra.irb.protocol.funding;

/**
 * Provides the required API for performing a multi-type lookup for funding sources, business rules used by the View, and business rule management for a 
 * protocol's funding source list.
 */
public interface ProtocolFundingSourceService extends org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService {

   // Covariant return to eliminate type errors enmasse, rather than by adding individual casts at the error locations. 
    /**
     * Updates the name and title for a Protocol funding source.
     * 
     * @param fundingSourceTypeCode the type code of the funding source
     * @param fundingSourceNumber the human-readable number of the funding source
     * @param fundingSourceName the name of the funding source
     * @return an instance of a Protocol funding source
     */
    ProtocolFundingSource updateProtocolFundingSource(String fundingSourceTypeCode, String fundingSourceNumber, String fundingSourceName);
    
}