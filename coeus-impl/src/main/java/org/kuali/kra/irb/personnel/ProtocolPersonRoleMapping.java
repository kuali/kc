/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.irb.personnel;

import org.kuali.kra.protocol.personnel.ProtocolPersonRoleMappingBase;

/**
 * This class represents protocol person role mapping business object
 */
public class ProtocolPersonRoleMapping extends ProtocolPersonRoleMappingBase {


    private static final long serialVersionUID = -4525940858799917386L;

    private Integer roleMappingId;

    private String sourceRoleId;

    private String targetRoleId;

    private ProtocolPersonRole sourceRole;

    private ProtocolPersonRole targetRole;

    public ProtocolPersonRoleMapping() {
    }

    public Integer getRoleMappingId() {
        return roleMappingId;
    }

    public void setRoleMappingId(Integer roleMappingId) {
        this.roleMappingId = roleMappingId;
    }

    public String getSourceRoleId() {
        return sourceRoleId;
    }

    public void setSourceRoleId(String sourceRoleId) {
        this.sourceRoleId = sourceRoleId;
    }

    public String getTargetRoleId() {
        return targetRoleId;
    }

    public void setTargetRoleId(String targetRoleId) {
        this.targetRoleId = targetRoleId;
    }

    public ProtocolPersonRole getSourceRole() {
        return sourceRole;
    }

    public void setSourceRole(ProtocolPersonRole sourceRole) {
        this.sourceRole = sourceRole;
    }

    public ProtocolPersonRole getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(ProtocolPersonRole targetRole) {
        this.targetRole = targetRole;
    }
}
