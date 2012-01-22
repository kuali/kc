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
package org.kuali.kra.irb.actions.risklevel;

import java.sql.Date;

import org.kuali.kra.bo.RiskLevel;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.ProtocolAssociate;

/**
 * 
 * This is BO class of protocol risk levels. 
 */
public class ProtocolRiskLevel extends ProtocolAssociate {

    private static final long serialVersionUID = 4044241452792449333L;

    private Integer protocolRiskLevelId;

    private String riskLevelCode;

    private String comments;

    private Date dateAssigned;

    private Date dateInactivated;

    private String status;

    private RiskLevel riskLevel;

    /**
     * Constructs a ProtocolRiskLevel, ensuring that the dateAssigned defaults to the current date.
     */
    public ProtocolRiskLevel() {
        dateAssigned = new Date(System.currentTimeMillis());
        status = Constants.STATUS_ACTIVE;
    }

    public Integer getProtocolRiskLevelId() {
        return protocolRiskLevelId;
    }

    public void setProtocolRiskLevelId(Integer protocolRiskLevelId) {
        this.protocolRiskLevelId = protocolRiskLevelId;
    }

    public String getRiskLevelCode() {
        return riskLevelCode;
    }

    public void setRiskLevelCode(String riskLevelCode) {
        this.riskLevelCode = riskLevelCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Date getDateInactivated() {
        return dateInactivated;
    }

    public void setDateInactivated(Date dateInactivated) {
        this.dateInactivated = dateInactivated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RiskLevel getRiskLevel() {
        refreshReferenceObject("riskLevel");
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((dateAssigned == null) ? 0 : dateAssigned.hashCode());
        result = prime * result + ((dateInactivated == null) ? 0 : dateInactivated.hashCode());
        result = prime * result + ((getProtocolNumber() == null) ? 0 : getProtocolNumber().hashCode());
        result = prime * result + ((protocolRiskLevelId == null) ? 0 : protocolRiskLevelId.hashCode());
        result = prime * result + ((riskLevel == null) ? 0 : riskLevel.hashCode());
        result = prime * result + ((riskLevelCode == null) ? 0 : riskLevelCode.hashCode());
        result = prime * result + ((getSequenceNumber() == null) ? 0 : getSequenceNumber().hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProtocolRiskLevel other = (ProtocolRiskLevel) obj;
        if (getProtocolNumber() == null) {
            if (other.getProtocolNumber() != null) {
                return false;
            }
        } else if (!getProtocolNumber().equals(other.getProtocolNumber())) {
            return false;
        }
        if (protocolRiskLevelId == null) {
            if (other.protocolRiskLevelId != null) {
                return false;
            }
        } else if (!protocolRiskLevelId.equals(other.protocolRiskLevelId)) {
            return false;
        }
        if (riskLevelCode == null) {
            if (other.riskLevelCode != null) {
                return false;
            }
        } else if (!riskLevelCode.equals(other.riskLevelCode)) {
            return false;
        }
        if (getSequenceNumber() == null) {
            if (other.getSequenceNumber() != null) {
                return false;
            }
        } else if (!getSequenceNumber().equals(other.getSequenceNumber())) {
            return false;
        }
        return true;
    }

    public String getStatusText() {
        return status.equals(Constants.STATUS_ACTIVE) ? Constants.ACTIVE_STATUS_LITERAL : Constants.INACTIVE_STATUS_LITERAL;
    }

    public boolean isPersisted() {
        return this.protocolRiskLevelId != null;
    }

    /** {@inheritDoc} */
    public void resetPersistenceState() {
        this.setProtocolRiskLevelId(null);
    }
}
