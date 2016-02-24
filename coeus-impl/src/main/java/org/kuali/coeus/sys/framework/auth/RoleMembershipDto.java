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
package org.kuali.coeus.sys.framework.auth;

import com.codiform.moo.annotation.Ignore;
import com.codiform.moo.annotation.MapProperty;
import org.joda.time.DateTime;

import java.util.Map;

public class RoleMembershipDto {

    private String roleId;
    private String id;
    private String memberId;

    @MapProperty(keyClass = String.class, valueClass = String.class, nullKeys = false, source = "qualifier")
    private Map<String, String> qualifiers;
    private DateTime activeFromDate;
    private DateTime activeToDate;
    @Ignore
    private String fullName;
    @Ignore
    private String email;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Map<String, String> getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(Map<String, String> qualifiers) {
        this.qualifiers = qualifiers;
    }

    public DateTime getActiveFromDate() {
        return activeFromDate;
    }

    public void setActiveFromDate(DateTime activeFromDate) {
        this.activeFromDate = activeFromDate;
    }

    public DateTime getActiveToDate() {
        return activeToDate;
    }

    public void setActiveToDate(DateTime activeToDate) {
        this.activeToDate = activeToDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
