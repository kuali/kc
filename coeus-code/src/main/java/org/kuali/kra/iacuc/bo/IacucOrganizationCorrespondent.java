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
package org.kuali.kra.iacuc.bo;

import org.kuali.coeus.common.framework.crrspndnt.Correspondent;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.kra.iacuc.correspondence.IacucCorrespondentType;
import org.kuali.kra.protocol.correspondence.CorrespondentType;

public class IacucOrganizationCorrespondent extends Correspondent {

    private static final long serialVersionUID = 1L;

    private String organizationId;
    
    private Organization organization;

    public IacucOrganizationCorrespondent() {
        super();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public IacucCorrespondentType getCorrespondentType() {
        return (IacucCorrespondentType) correspondentType;
    }

    public void setCorrespondentType(IacucCorrespondentType correspondentType) {
        this.correspondentType = (CorrespondentType) correspondentType;
    }

}
