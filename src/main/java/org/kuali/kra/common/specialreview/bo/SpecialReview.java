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
package org.kuali.kra.common.specialreview.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;

/**
 * Defines the base class for the Special Review business object for all modules.
 * @param <T> SpecialReviewExemption
 */
public abstract class SpecialReview<T extends SpecialReviewExemption> extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -2168706171397009621L;

    private Integer specialReviewNumber;

    private String specialReviewTypeCode;

    private String approvalTypeCode;

    private String protocolNumber;

    private Date applicationDate;

    private Date approvalDate;

    private Date expirationDate;

    private String comments;

    private SpecialReviewType specialReviewType;

    private SpecialReviewApprovalType approvalType;

    private String protocolStatus;

    // Struts 1 does not like having objects in multiselect boxes, so these two fields are a hack to make this work nicely with Struts. 
    // 
    // The field specialReviewExemptions holds the objects stored in the database, while exemptionTypeCodes has just the currently selected string codes. 
    // These need to be initialized here so the syncing algorithms below can handle multiple calls to afterLookup during page load and not erase the already  
    // synced data. 
    private List<T> specialReviewExemptions = new ArrayList<T>();

    private transient List<String> exemptionTypeCodes = new ArrayList<String>();

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

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

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

    public SpecialReviewType getSpecialReviewType() {
        return specialReviewType;
    }

    public void setSpecialReviewType(SpecialReviewType specialReviewType) {
        this.specialReviewType = specialReviewType;
    }

    public SpecialReviewApprovalType getApprovalType() {
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

    public String getProtocolStatus() {
        return protocolStatus;
    }

    public void setProtocolStatus(String protocolStatus) {
        this.protocolStatus = protocolStatus;
    }

    /** 
     * {@inheritDoc} 
     */
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
}
