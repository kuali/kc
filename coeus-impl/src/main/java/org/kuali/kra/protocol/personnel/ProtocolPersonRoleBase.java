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
package org.kuali.kra.protocol.personnel;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

public abstract class ProtocolPersonRoleBase extends KcPersistableBusinessObjectBase implements Comparable<ProtocolPersonRoleBase>, MutableInactivatable {


    private static final long serialVersionUID = -4525940858799917386L;

    public static final String ROLE_PRINCIPAL_INVESTIGATOR = "PI";

    public static final String ROLE_CO_INVESTIGATOR = "COI";

    public static final String ROLE_STUDY_PERSONNEL = "SP";
    
    public static final String ROLE_CORRESPONDENT_ADMINISTRATOR = "CA";

    private String protocolPersonRoleId;

    private String description;

    private boolean unitDetailsRequired;

    private boolean affiliationDetailsRequired;

    private boolean trainingDetailsRequired;

    private boolean commentsDetailsRequired;

    private boolean active;

    public ProtocolPersonRoleBase() {
    }

    public String getProtocolPersonRoleId() {
        return protocolPersonRoleId;
    }

    public void setProtocolPersonRoleId(String protocolPersonRoleId) {
        this.protocolPersonRoleId = protocolPersonRoleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isUnitDetailsRequired() {
        return unitDetailsRequired;
    }

    public void setUnitDetailsRequired(boolean unitDetailsRequired) {
        this.unitDetailsRequired = unitDetailsRequired;
    }

    public boolean isAffiliationDetailsRequired() {
        return affiliationDetailsRequired;
    }

    public void setAffiliationDetailsRequired(boolean affiliationDetailsRequired) {
        this.affiliationDetailsRequired = affiliationDetailsRequired;
    }

    public boolean isTrainingDetailsRequired() {
        return trainingDetailsRequired;
    }

    public void setTrainingDetailsRequired(boolean trainingDetailsRequired) {
        this.trainingDetailsRequired = trainingDetailsRequired;
    }

    public boolean isCommentsDetailsRequired() {
        return commentsDetailsRequired;
    }

    public void setCommentsDetailsRequired(boolean commentsDetailsRequired) {
        this.commentsDetailsRequired = commentsDetailsRequired;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int compareTo(ProtocolPersonRoleBase other) {
        int result = 0;
        if (other != null) {
            if (description != null && other.description != null) {
                result = description.compareTo(other.getDescription());
            } else if (description == null && other.getDescription() != null) {
                result = 1;
            } else if (description != null && other.getDescription() == null) {
                result = -1;
            } else {
                result = 0;
            }
        }
        return result;
    }
}
