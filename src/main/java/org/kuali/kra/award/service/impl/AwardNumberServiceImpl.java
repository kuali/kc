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
package org.kuali.kra.award.service.impl;

import java.text.DecimalFormat;

import org.kuali.kra.award.service.AwardNumberService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.SequenceAccessorService;

public class AwardNumberServiceImpl implements AwardNumberService {

    private SequenceAccessorService sequenceAccessorService;
    
    /**
     * Set the Sequence Accessor Service.
     * @param sequenceAccessorService the Sequence Accessor Service
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    /** {@inheritDoc} */
    public String getNextAwardNumber() {
        Long nextAwardNumber = sequenceAccessorService.getNextAvailableSequenceNumber(Constants.AWARD_SEQUENCE_AWARD_NUMBER);
        
        // Use Coeus' xxxxxx-yyy format for compatibility
        DecimalFormat formatter = new DecimalFormat("000000");        
        return formatter.format(nextAwardNumber) + "-001";
    }

}