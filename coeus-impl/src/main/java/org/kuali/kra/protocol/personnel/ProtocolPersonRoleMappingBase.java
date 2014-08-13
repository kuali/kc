/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.protocol.personnel;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * This class represents protocol person role mapping business object
 */
public abstract class ProtocolPersonRoleMappingBase extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = -4525940858799917386L;

    private Integer roleMappingId;

    private String sourceRoleId;

    private String targetRoleId;

    private ProtocolPersonRoleBase sourceRole;

    private ProtocolPersonRoleBase targetRole;

    public ProtocolPersonRoleMappingBase() {
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

    public ProtocolPersonRoleBase getSourceRole() {
        return sourceRole;
    }

    public void setSourceRole(ProtocolPersonRoleBase sourceRole) {
        this.sourceRole = sourceRole;
    }

    public ProtocolPersonRoleBase getTargetRole() {
        return targetRole;
    }

    public void setTargetRole(ProtocolPersonRoleBase targetRole) {
        this.targetRole = targetRole;
    }
}
