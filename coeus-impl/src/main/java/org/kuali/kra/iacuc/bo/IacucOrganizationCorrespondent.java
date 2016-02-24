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
