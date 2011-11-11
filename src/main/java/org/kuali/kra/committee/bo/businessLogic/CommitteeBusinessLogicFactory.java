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

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeType;

public class CommitteeBusinessLogicFactory {

    private CommitteeCollaboratorFactoryGroup committeeCollaboratorFactoryGroup;


    public void setCommitteeCollaboratorFactoryGroup(CommitteeCollaboratorFactoryGroup committeeCollaboratorFactoryGroup) {
        this.committeeCollaboratorFactoryGroup = committeeCollaboratorFactoryGroup;
    }

    public CommitteeCollaboratorFactoryGroup getCommitteeCollaboratorFactoryGroup() {
        return committeeCollaboratorFactoryGroup;
    }


    public CommitteeBusinessLogic getBusinessLogicFor(Committee committee) {
        CommitteeBusinessLogic retVal = null;

        // return appropriate subclass of committee business logic based on committee type
        if (StringUtils.equals(committee.getCommitteeTypeCode(), CommitteeType.IRB_TYPE_CODE)) {
            retVal = new IRBCommitteeBusinessLogic(committee, getCommitteeCollaboratorFactoryGroup());
        }
        else if (StringUtils.equals(committee.getCommitteeTypeCode(), CommitteeType.COI_TYPE_CODE)) {
            retVal = new CoiCommitteeBusinessLogic(committee, getCommitteeCollaboratorFactoryGroup());
        }
        else {
            retVal = new CommitteeBusinessLogic(committee, getCommitteeCollaboratorFactoryGroup()) {

                @Override
                public boolean validateCommitteeResearchAreas() {
                    return false;
                }

                @Override
                public boolean checkReviewType() {
                    return false;
                }
                
            };
        }

        return retVal;
    }

}