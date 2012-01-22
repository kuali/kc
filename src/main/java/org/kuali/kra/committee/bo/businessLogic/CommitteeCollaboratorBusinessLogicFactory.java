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

import org.kuali.rice.krad.bo.BusinessObject;

public interface CommitteeCollaboratorBusinessLogicFactory <BO extends BusinessObject, BL extends CommitteeCollaboratorBusinessLogic> {

    public void setCommitteeCollaboratorBusinessLogicFactoryGroup(CommitteeCollaboratorBusinessLogicFactoryGroup committeeCollaboratorBusinessLogicFactoryGroup);

    public BL getBusinessLogicFor(BO businessObject);
    
}
