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
package org.kuali.kra.irb.actions.risklevel;

import org.kuali.kra.bo.RiskLevel;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.ProtocolAssociateBase;

import java.sql.Date;

/**
 * 
 * This is BO class of protocol risk levels. 
 */
public class ProtocolRiskLevel extends ProtocolAssociateBase {

    private static final long serialVersionUID = 4044241452792449333L;

    private Integer protocolRiskLevelId;

    private String riskLevelCode;

    private String comments;

    private Date dateAssigned;

    private Date dateInactivated;

    private String status;

    private RiskLevel riskLevel;


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

    @Override
    public void resetPersistenceState() {
        this.setProtocolRiskLevelId(null);
    }
}
