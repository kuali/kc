/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
