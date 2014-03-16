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
package org.kuali.kra.negotiations.bo;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.kra.bo.AbstractProjectPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

public class NegotiationPersonDTO implements AbstractProjectPerson {
    
    private KcPerson person;
    private String roleCode;
    
    public NegotiationPersonDTO(KcPerson person, String roleCode) {
        this.person = person;
        this.roleCode = roleCode;
    }

    public KcPerson getPerson() {
        return person;
    }

    public void setPerson(KcPerson person) {
        this.person = person;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    public boolean isPrincipalInvestigator() {
        return StringUtils.equals(roleCode, Constants.PRINCIPAL_INVESTIGATOR_ROLE);
    }

    @Override
    public String getFullName() {
        return getPerson().getFullName();
    }

    @Override
    public PersistableBusinessObject getParent() {
        return null;
    }

    @Override
    public String getPersonId() {
        return getPerson().getPersonId();
    }

    @Override
    public Integer getRolodexId() {
        return null;
    }

}
