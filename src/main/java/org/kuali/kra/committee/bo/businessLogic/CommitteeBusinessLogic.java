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
package org.kuali.kra.committee.bo.businessLogic;

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ErrorReporter;

public abstract class CommitteeBusinessLogic {
    
    private CommitteeCollaboratorFactoryGroup committeeCollaboratorFactoryGroup;
    private Committee committeeBusinessObject;
    private static final String REVIEW_TYPE_ERROR_PROPERTY_NAME = "document.committeeList[0].reviewTypeCode";

    public CommitteeBusinessLogic(Committee businessObject, CommitteeCollaboratorFactoryGroup committeeCollaborators) {
        this.committeeBusinessObject = businessObject;
        this.committeeCollaboratorFactoryGroup = committeeCollaborators;
    }

    
    public CommitteeCollaboratorFactoryGroup getCommitteeCollaboratorFactoryGroup() {
        return committeeCollaboratorFactoryGroup;
    }

    public Committee getCommitteeBusinessObject() {
        return committeeBusinessObject;
    }
    
    public abstract boolean validateCommitteeResearchAreas();
    
    // check that the review type corresponding to the committee type is non-null (not performing XOR check) 
    public boolean validateReviewType() {
        boolean valid = true;        
        if(!checkReviewType()) {
            // add error message
            new ErrorReporter().reportError(REVIEW_TYPE_ERROR_PROPERTY_NAME, KeyConstants.ERROR_COMMITTEE_REVIEW_TYPE_REQUIRED);
            valid = false;        
        }
        return valid;   
    }


    public abstract boolean checkReviewType();
    
}
