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
package org.kuali.coeus.propdev.impl.specialreview;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;

public interface ProposalDevelopmentSpecialReviewService {

    boolean createProtocol(ProposalSpecialReview specialReview, ProposalDevelopmentDocument document) throws Exception;
    
    boolean isIrbLinkingEnabled();
    
    boolean isIacucLinkingEnabled();
    
    boolean canCreateIrbProtocol(ProposalDevelopmentDocument document);

    boolean canCreateIacucProtocol(ProposalDevelopmentDocument document);
    
    public boolean isCreateIrbProtocolEnabled();
    
    public boolean isCreateIacucProtocolEnabled();

    public boolean isCreateProtocolFromProposalEnabled(String protocolLinkParam);
    
    public Integer generateSpecialReviewNumber(ProposalDevelopmentDocument document);

    }
