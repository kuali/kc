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

import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.bo.businessLogic.CommitteeBusinessLogic;
import org.kuali.kra.committee.bo.businessLogic.CommitteeCollaboratorBusinessLogicFactory;
import org.kuali.kra.committee.bo.businessLogic.CommitteeCollaboratorBusinessLogicFactoryGroup;
import org.kuali.kra.committee.bo.businessLogic.CommitteeResearchAreaBusinessLogic;

public class CommitteeCollaboratorBusinessLogicFactoryGroupImpl implements CommitteeCollaboratorBusinessLogicFactoryGroup {
    
    private CommitteeCollaboratorBusinessLogicFactory<Committee, CommitteeBusinessLogic> committeeBusinessLogicFactory;
    private CommitteeCollaboratorBusinessLogicFactory<CommitteeResearchArea, CommitteeResearchAreaBusinessLogic> committeeResearchAreaBusinessLogicFactory;
    
    
    public void setCommitteeBusinessLogicFactory(CommitteeCollaboratorBusinessLogicFactory<Committee, CommitteeBusinessLogic> committeeBusinessLogicFactory) {
        this.committeeBusinessLogicFactory = committeeBusinessLogicFactory;
        // set back pointer to this group into the factory
        this.committeeBusinessLogicFactory.setCommitteeCollaboratorBusinessLogicFactoryGroup(this);
    }
    
    public void setCommitteeResearchAreaBusinessLogicFactory(CommitteeCollaboratorBusinessLogicFactory<CommitteeResearchArea, CommitteeResearchAreaBusinessLogic> committeeResearchAreaBusinessLogicFactory) {
        this.committeeResearchAreaBusinessLogicFactory = committeeResearchAreaBusinessLogicFactory;
        // set back pointer to this group into the factory
        this.committeeResearchAreaBusinessLogicFactory.setCommitteeCollaboratorBusinessLogicFactoryGroup(this);
    }
    
    
    
    public CommitteeResearchAreaBusinessLogic getCommitteeReserachAreaBusinessLogic(CommitteeResearchArea businessObject) {
        return this.committeeResearchAreaBusinessLogicFactory.getBusinessLogicFor(businessObject);
    }
    
    public CommitteeBusinessLogic getCommitteeBusinessLogicFor(Committee businessObject) {
        return this.committeeBusinessLogicFactory.getBusinessLogicFor(businessObject);
    }
    
}