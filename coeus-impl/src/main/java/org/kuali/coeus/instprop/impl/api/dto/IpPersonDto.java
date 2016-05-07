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

package org.kuali.coeus.instprop.impl.api.dto;

public class IpPersonDto {


    private String personId;
    private String roleCode;
    private String keyPersonRole;
    private Integer rolodexId;

    private Long institutionalProposalContactId;

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getPersonId() {
        return personId;
    }

    public Long getInstitutionalProposalContactId() {
        return institutionalProposalContactId;
    }

    public void setInstitutionalProposalContactId(Long institutionalProposalContactId) {
        this.institutionalProposalContactId = institutionalProposalContactId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getKeyPersonRole() {
        return keyPersonRole;
    }

    public void setKeyPersonRole(String keyPersonRole) {
        this.keyPersonRole = keyPersonRole;
    }
}
