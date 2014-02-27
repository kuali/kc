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
package org.kuali.kra.irb.protocol.funding;

import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;

/**
 * This class provides fundamental elements of protocol funding source data for Protocols.
 */
public class ProtocolFundingSource extends ProtocolFundingSourceBase {

    private static final long serialVersionUID = 2732312377082408995L;    
    
    /**
     * Empty constructor for database.
     */
    public ProtocolFundingSource() {
    }

    /**
     * Constructs a ProtocolFundingSource.
     * @param fundingSourceNumber The user-readable number of the funding source (often the same as fundingSource)
     * @param fundingSourceTypeCode The type code of the funding source
     * @param fundingSourceName The name of the funding source
     * @param fundingSourceTitle The title of the funding source
     */
    public ProtocolFundingSource(String fundingSourceNumber, String fundingSourceTypeCode, String fundingSourceName, String fundingSourceTitle) {
        super(fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook() {
        return ProtocolFundingSourceService.class;
    }
}
