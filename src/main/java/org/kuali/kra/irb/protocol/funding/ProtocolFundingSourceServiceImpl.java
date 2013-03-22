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

import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceServiceImplBase;

/**
 * Implements ProtocolFundingSource.
 */
public class ProtocolFundingSourceServiceImpl extends ProtocolFundingSourceServiceImplBase implements ProtocolFundingSourceService {
    
    @Override
    protected ProtocolFundingSourceBase creatNewProtocolFundingSourceInstanceHook(String fundingSourceNumber, String fundingSourceTypeCode, String fundingSourceName, String fundingSourceTitle) {
        return new ProtocolFundingSource(fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
    }

    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook() {
        return ProtocolDocument.class;
    }
    
    @Override
    // Covariant return to eliminate type errors enmasse, rather than by adding individual casts at the error locations.
    public ProtocolFundingSource updateProtocolFundingSource(String fundingSourceTypeCode, String fundingSourceNumber, String fundingSourceName) {
        return (ProtocolFundingSource) super.updateProtocolFundingSource(fundingSourceTypeCode, fundingSourceNumber, fundingSourceName);
    }
}