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
package org.kuali.coeus.common.framework.compliance.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.compliance.core.SpecialReviewContract;
import org.kuali.coeus.common.framework.compliance.exemption.SpecialReviewExemption;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the base class for the Special Review business object for all modules.
 * @param <T> SpecialReviewExemption
 */
@MappedSuperclass
public abstract class SpecialReview<T extends SpecialReviewExemption> extends KcPersistableBusinessObjectBase implements SpecialReviewContract {

    private static final long serialVersionUID = -2168706171397009621L;

    @Column(name = "SPECIAL_REVIEW_NUMBER")
    private Integer specialReviewNumber;

    @Column(name = "SPECIAL_REVIEW_CODE")
    private String specialReviewTypeCode;

    @Column(name = "APPROVAL_TYPE_CODE")
    private String approvalTypeCode;

    @Column(name = "PROTOCOL_NUMBER")
    private String protocolNumber;

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "APPROVAL_DATE")
    private Date approvalDate;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    @Column(name = "COMMENTS")
    @Lob
    private String comments;

    @Column(name = "PROTOCOL_STATUS_DESCRIPTION")
    private String protocolStatus;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "SPECIAL_REVIEW_CODE", referencedColumnName = "SPECIAL_REVIEW_CODE", insertable = false, updatable = false)
    private SpecialReviewType specialReviewType;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "APPROVAL_TYPE_CODE", referencedColumnName = "APPROVAL_TYPE_CODE", insertable = false, updatable = false)
    private SpecialReviewApprovalType approvalType;

    @Transient
    private boolean linkedToProtocol;

    // Struts 1 does not like having objects in multiselect boxes, so these two fields are a hack to make this work nicely with Struts.  
    //  
    // The field specialReviewExemptions holds the objects stored in the database, while exemptionTypeCodes has just the currently selected string codes.  
    // These need to be initialized here so the syncing algorithms below can handle multiple calls to afterLookup during page load and not erase the already   
    // synced data.  
    @OneToMany(mappedBy = "proposalSpecialReview")
    private List<T> specialReviewExemptions = new ArrayList<T>();

    @Transient
    private transient List<String> exemptionTypeCodes = new ArrayList<String>();

    @Override
    public Integer getSpecialReviewNumber() {
        return specialReviewNumber;
    }

    public void setSpecialReviewNumber(Integer specialReviewNumber) {
        this.specialReviewNumber = specialReviewNumber;
    }

    public String getSpecialReviewTypeCode() {
        return specialReviewTypeCode;
    }

    public void setSpecialReviewTypeCode(String specialReviewTypeCode) {
        this.specialReviewTypeCode = specialReviewTypeCode;
    }

    public String getApprovalTypeCode() {
        return approvalTypeCode;
    }

    public void setApprovalTypeCode(String approvalTypeCode) {
        this.approvalTypeCode = approvalTypeCode;
    }

    @Override
    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    @Override
    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    @Override
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<T> getSpecialReviewExemptions() {
        return specialReviewExemptions;
    }

    public void setSpecialReviewExemptions(List<T> specialReviewExemptions) {
        this.specialReviewExemptions = specialReviewExemptions;
        this.syncSpecialReviewExemptionsToExemptionTypeCodes();
    }

    //refreshReferenceObject should be replaced with @TrackChanges which is not working at this point
    //Related jira - KRACOEUS-7159
    @Override
    public SpecialReviewType getSpecialReviewType() {
    	if(specialReviewType == null) {
    		refreshReferenceObject("specialReviewType");
    	}
        return specialReviewType;
    }

    public void setSpecialReviewType(SpecialReviewType specialReviewType) {
        this.specialReviewType = specialReviewType;
    }

    @Override
    public SpecialReviewApprovalType getApprovalType() {
    	if(approvalType == null) {
    		refreshReferenceObject("approvalType");
    	}
        return approvalType;
    }

    public void setApprovalType(SpecialReviewApprovalType approvalType) {
        this.approvalType = approvalType;
    }

    public List<String> getExemptionTypeCodes() {
        return exemptionTypeCodes;
    }

    public void setExemptionTypeCodes(List<String> exemptionTypeCodes) {
        this.exemptionTypeCodes = exemptionTypeCodes;
        syncExemptionTypeCodesToSpecialReviewExemptions();
    }

    @Override
    public String getProtocolStatus() {
        return protocolStatus;
    }

    public void setProtocolStatus(String protocolStatus) {
        this.protocolStatus = protocolStatus;
    }

    @Override
    protected void postLoad() {
        super.postLoad();
        this.syncSpecialReviewExemptionsToExemptionTypeCodes();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getSpecialReviewExemptions());
        return managedLists;
    }

    /**
     * Creates a SpecialReviewExemption for the given type based on the exemptionTypeCode.
     * @param exemptionTypeCode The exemption type code connected to the SpecialReviewExemption
     * @return a new SpecialReviewExemption connected to the exemption type code
     */
    public abstract T createSpecialReviewExemption(String exemptionTypeCode);

    /**
     * This method syncs the selected exemptions with the persisted exemptions.
     * 
     * This method is needed to ensure that when a user selects exemption(s) on the page that the corresponding persisted exemptions are created.
     */
    private void syncExemptionTypeCodesToSpecialReviewExemptions() {
        List<T> syncedSpecialReviewExemptions = new ArrayList<T>();
        if (exemptionTypeCodes != null) {
            for (String exemptionTypeCode : exemptionTypeCodes) {
                syncedSpecialReviewExemptions.add(getSpecialReviewExemption(exemptionTypeCode));
            }
        }
        specialReviewExemptions = new ArrayList<T>(syncedSpecialReviewExemptions);
    }

    /**
     * Returns the specific SpecialReviewExemption type based on the exemption type code, or creates one if it does not exist.
     * @param exemptionTypeCode The exemption type code connected to the SpecialReviewExemption
     * @return the SpecialReviewExemption connected to the exemption type code
     */
    private T getSpecialReviewExemption(String exemptionTypeCode) {
        T specialReviewExemption = null;
        if (specialReviewExemptions != null) {
            for (T exemption : specialReviewExemptions) {
                if (StringUtils.equals(exemption.getExemptionTypeCode(), exemptionTypeCode)) {
                    specialReviewExemption = exemption;
                    break;
                }
            }
        }
        if (specialReviewExemption == null) {
            specialReviewExemption = createSpecialReviewExemption(exemptionTypeCode);
        }
        return specialReviewExemption;
    }

    /**
     * This method syncs the persisted exemptions with the selected exemption type codes.
     * 
     * This method is needed to ensure that when the page loads, the currently selected exemption(s) are correctly highlighted on the page.
     */
    private void syncSpecialReviewExemptionsToExemptionTypeCodes() {
        List<String> syncedExemptionTypeCodes = new ArrayList<String>();
        if (specialReviewExemptions != null) {
            for (T exemption : specialReviewExemptions) {
                if (exemption.getExemptionTypeCode() != null) {
                    syncedExemptionTypeCodes.add(exemption.getExemptionTypeCode());
                }
            }
        }
        // The field specialReviewExemptions is loaded several times before the page is rendered, and the isEmpty does not always report accurate results,  
        // so we must double check here before changing the exemptionTypeCodes.  
        if (!syncedExemptionTypeCodes.isEmpty()) {
            exemptionTypeCodes = new ArrayList<String>(syncedExemptionTypeCodes);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((applicationDate == null) ? 0 : applicationDate.hashCode());
        result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
        result = prime * result + ((approvalTypeCode == null) ? 0 : approvalTypeCode.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
        result = prime * result + ((protocolNumber == null) ? 0 : protocolNumber.hashCode());
        result = prime * result + ((specialReviewNumber == null) ? 0 : specialReviewNumber.hashCode());
        result = prime * result + ((specialReviewTypeCode == null) ? 0 : specialReviewTypeCode.hashCode());
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
        SpecialReview<?> other = (SpecialReview<?>) obj;
        if (applicationDate == null) {
            if (other.applicationDate != null) {
                return false;
            }
        } else if (!applicationDate.equals(other.applicationDate)) {
            return false;
        }
        if (approvalDate == null) {
            if (other.approvalDate != null) {
                return false;
            }
        } else if (!approvalDate.equals(other.approvalDate)) {
            return false;
        }
        if (approvalTypeCode == null) {
            if (other.approvalTypeCode != null) {
                return false;
            }
        } else if (!approvalTypeCode.equals(other.approvalTypeCode)) {
            return false;
        }
        if (comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!comments.equals(other.comments)) {
            return false;
        }
        if (expirationDate == null) {
            if (other.expirationDate != null) {
                return false;
            }
        } else if (!expirationDate.equals(other.expirationDate)) {
            return false;
        }
        if (protocolNumber == null) {
            if (other.protocolNumber != null) {
                return false;
            }
        } else if (!protocolNumber.equals(other.protocolNumber)) {
            return false;
        }
        if (specialReviewNumber == null) {
            if (other.specialReviewNumber != null) {
                return false;
            }
        } else if (!specialReviewNumber.equals(other.specialReviewNumber)) {
            return false;
        }
        if (specialReviewTypeCode == null) {
            if (other.specialReviewTypeCode != null) {
                return false;
            }
        } else if (!specialReviewTypeCode.equals(other.specialReviewTypeCode)) {
            return false;
        }
        return true;
    }

	public boolean isLinkedToProtocol() {
		return linkedToProtocol;
	}

	public void setLinkedToProtocol(boolean linkedToProtocol) {
		this.linkedToProtocol = linkedToProtocol;
	}
}
