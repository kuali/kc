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
import org.kuali.kra.committee.bo.businessLogic.CommitteeCollaboratorBusinessLogicFactoryGroup;

public class CoiCommitteeBusinessLogicImpl extends CommitteeBusinessLogicImpl {

    public CoiCommitteeBusinessLogicImpl(Committee businessObject, CommitteeCollaboratorBusinessLogicFactoryGroup committeeCollaborators) {
        super(businessObject, committeeCollaborators);
    }

    @Override
    public boolean validateCommitteeResearchAreas() {
        return true;
    }

    @Override
    public boolean checkReviewType() {
        return !StringUtils.isBlank(getCommitteeBusinessObject().getCoiReviewTypeCode());
    }

}
