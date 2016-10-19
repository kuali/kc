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
package org.kuali.coeus.award.dto;


import com.codiform.moo.annotation.Property;

public class AwardSponsorContactDto {

    protected Integer rolodexId;
    protected String roleCode;
    private Long awardContactId;
    private String orgName;
    @Property(translate = true, update = true)
    private RolodexDto rolodex;

    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Long getAwardContactId() {
        return awardContactId;
    }

    public void setAwardContactId(Long awardContactId) {
        this.awardContactId = awardContactId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public RolodexDto getRolodex() {
        return rolodex;
    }

    public void setRolodex(RolodexDto rolodex) {
        this.rolodex = rolodex;
    }
}
