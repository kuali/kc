/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.personnel;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;

public class ProtocolPersonRole extends KraPersistableBusinessObjectBase implements Comparable<ProtocolPersonRole>, MutableInactivatable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4525940858799917386L;

    public static final String ROLE_PRINCIPAL_INVESTIGATOR = "PI";

    public static final String ROLE_CO_INVESTIGATOR = "COI";

    public static final String ROLE_STUDY_PERSONNEL = "SP";

    public static final String ROLE_CORRESPONDENT_CRC = "CRC";

    public static final String ROLE_CORRESPONDENT_ADMINISTRATOR = "CA";

    private String protocolPersonRoleId;

    private String description;

    private boolean unitDetailsRequired;

    private boolean affiliationDetailsRequired;

    private boolean trainingDetailsRequired;

    private boolean commentsDetailsRequired;

    private boolean active;

    public ProtocolPersonRole() {
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

    public int compareTo(ProtocolPersonRole other) {
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
