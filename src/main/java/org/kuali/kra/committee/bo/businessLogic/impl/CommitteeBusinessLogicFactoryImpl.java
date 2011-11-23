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
package org.kuali.kra.committee.bo.businessLogic.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.CommitteeType;
import org.kuali.kra.committee.bo.businessLogic.CommitteeBusinessLogic;
import org.kuali.kra.committee.bo.businessLogic.CommitteeCollaboratorBusinessLogicFactory;
import org.kuali.kra.committee.bo.businessLogic.CommitteeCollaboratorBusinessLogicFactoryGroup;

public class CommitteeBusinessLogicFactoryImpl implements CommitteeCollaboratorBusinessLogicFactory<Committee, CommitteeBusinessLogic>  {

    private CommitteeCollaboratorBusinessLogicFactoryGroup committeeCollaboratorFactoryGroup;


    public void setCommitteeCollaboratorBusinessLogicFactoryGroup(CommitteeCollaboratorBusinessLogicFactoryGroup committeeCollaboratorFactoryGroup) {
        this.committeeCollaboratorFactoryGroup = committeeCollaboratorFactoryGroup;
    }

    public CommitteeCollaboratorBusinessLogicFactoryGroup getCommitteeCollaboratorBusinessLogicFactoryGroup() {
        return committeeCollaboratorFactoryGroup;
    }


    public CommitteeBusinessLogic getBusinessLogicFor(Committee committee) {
        CommitteeBusinessLogic retVal = null;

        // return appropriate subclass of committee business logic based on committee type
        if (StringUtils.equals(committee.getCommitteeTypeCode(), CommitteeType.IRB_TYPE_CODE)) {
            retVal = new IrbCommitteeBusinessLogicImpl(committee, getCommitteeCollaboratorBusinessLogicFactoryGroup());
        }
        else if (StringUtils.equals(committee.getCommitteeTypeCode(), CommitteeType.COI_TYPE_CODE)) {
            retVal = new CoiCommitteeBusinessLogicImpl(committee, getCommitteeCollaboratorBusinessLogicFactoryGroup());
        }
        else {
            // return irb committee business logic by default
            retVal = new IrbCommitteeBusinessLogicImpl(committee, getCommitteeCollaboratorBusinessLogicFactoryGroup());
        }

        return retVal;
    }

}