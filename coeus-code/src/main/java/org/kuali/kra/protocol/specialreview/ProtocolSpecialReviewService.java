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
package org.kuali.kra.protocol.specialreview;

import org.kuali.coeus.common.framework.compliance.core.SpecialReview;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.kra.protocol.ProtocolFinderDao;


public interface ProtocolSpecialReviewService {

    public static final String AMENDMENT_KEY = "A";
    public static final String RENEWAL_KEY = "R";

    @SuppressWarnings("rawtypes")
    public void populateSpecialReview(SpecialReview specialReview);
    /**
     * 
     * This method gets a DevelopmentProposal object based on the proposalNumber
     * @param proposalNumber
     * @return
     */
    public DevelopmentProposal getPropososalDevelopment(String proposalNumber);
    
    public ProtocolFinderDao getProtocolFinderDao();

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao);

}
