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
import org.kuali.kra.committee.bo.CommitteeResearchArea;

public class CommitteeCollaboratorFactoryGroup {
    
    private CommitteeBusinessLogicFactory committeeBusinessLogicFactory;
    private CommitteeResearchAreaBusinessLogicFactory committeeResearchAreaBusinessLogicFactory;
    
    
    public void setCommitteeBusinessLogicFactory(CommitteeBusinessLogicFactory committeeBusinessLogicFactory) {
        this.committeeBusinessLogicFactory = committeeBusinessLogicFactory;
        // set back pointer to this group into the factory
        this.committeeBusinessLogicFactory.setCommitteeCollaboratorFactoryGroup(this);
    }
    
    public void setCommitteeResearchAreaBusinessLogicFactory(CommitteeResearchAreaBusinessLogicFactory committeeReserachAreaBusinessLogicFactory) {
        this.committeeResearchAreaBusinessLogicFactory = committeeReserachAreaBusinessLogicFactory;
        // set back pointer to this group into the factory
        this.committeeResearchAreaBusinessLogicFactory.setCommitteeCollaboratorFactoryGroup(this);
    }
    
    
    
    public CommitteeResearchAreaBusinessLogic getCommitteeReserachAreaBusinessLogic(CommitteeResearchArea businessObject) {
        return this.committeeResearchAreaBusinessLogicFactory.getBusinessLogicFor(businessObject);
    }
    
    public CommitteeBusinessLogic getCommitteeBusinessLogicFor(Committee businessObject) {
        return this.committeeBusinessLogicFactory.getBusinessLogicFor(businessObject);
    }
    
}
