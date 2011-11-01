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

import org.kuali.kra.committee.bo.CommitteeResearchArea;

public class CommitteeResearchAreaBusinessLogic {
    
    private CommitteeCollaboratorFactoryGroup committeeCollaboratorFactoryGroup;
    private CommitteeResearchArea committeeResearchArea;

    public CommitteeResearchAreaBusinessLogic(CommitteeResearchArea businessObject, CommitteeCollaboratorFactoryGroup committeeCollaborators) {
        this.committeeResearchArea = businessObject;
        this.committeeCollaboratorFactoryGroup = committeeCollaborators;
    }
    
    
    public CommitteeCollaboratorFactoryGroup getCommitteeCollaboratorFactoryGroup() {
        return committeeCollaboratorFactoryGroup;
    }

    public CommitteeResearchArea getCommitteeResearchArea() {
        return committeeResearchArea;
    }
    
    // this method is the only domain specific logic here, so perhaps this class is overkill and can be subsumed into CommitteeBusinessLogic
    public boolean isEnclosedResearchAreaActive() {
        return this.getCommitteeResearchArea().getResearchAreas().isActive();
    }

}
