/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KeyValuesService;

import edu.emory.mathcs.backport.java.util.Collections;

/**
 * This class is the base class for special review BO.
 */
public abstract class AbstractSpecialReview<T extends AbstractSpecialReviewExemption> extends KraPersistableBusinessObjectBase {
    /**
     * Comment for <code>serialVersionUID</code>
     * Do we need this???
     */
    private static final long serialVersionUID = 3671069178534624665L;
    private Integer specialReviewNumber;
    private Date applicationDate;
    private Date approvalDate;
    private Date expirationDate;
    private String approvalTypeCode;
    private String comments;
    private String protocolNumber;
    private String specialReviewCode;
    private SpecialReview specialReview;
    private SpecialReviewApprovalType specialReviewApprovalType;

    private ValidSpecialReviewApproval validSpecialReviewApproval;
    private List<T> specialReviewExemptions;
    private String[] exemptionTypeCodes;
    private String[] newExemptionTypeCodes = null;
    private List<ExemptionType> exemptionTypes;
    
    public AbstractSpecialReview() {
        super();
        specialReviewExemptions = new ArrayList<T>();
        exemptionTypes = new ArrayList<ExemptionType>();
    }

    public Integer getSpecialReviewNumber() {
        return specialReviewNumber;
    }

    public void setSpecialReviewNumber(Integer specialReviewNumber) {
        this.specialReviewNumber = specialReviewNumber;
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

    public String getApprovalTypeCode() {
        return approvalTypeCode;
    }

    public void setApprovalTypeCode(String approvalTypeCode) {
        this.approvalTypeCode = approvalTypeCode;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public String getSpecialReviewCode() {
        return specialReviewCode;
    }

    public void setSpecialReviewCode(String specialReviewCode) {
        this.specialReviewCode = specialReviewCode;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(getSpecialReviewExemptions());
        return managedLists;
    }

    public ValidSpecialReviewApproval getValidSpecialReviewApproval() {
        return validSpecialReviewApproval;
    }

    public void setValidSpecialReviewApproval(ValidSpecialReviewApproval validSpecialReviewApproval) {
        this.validSpecialReviewApproval = validSpecialReviewApproval;
    }

    public SpecialReview getSpecialReview() {
        return specialReview;
    }

    public void setSpecialReview(SpecialReview specialReview) {
        this.specialReview = specialReview;
    }

    public SpecialReviewApprovalType getSpecialReviewApprovalType() {
        return specialReviewApprovalType;
    }

    public void setSpecialReviewApprovalType(SpecialReviewApprovalType specialReviewApprovalType) {
        this.specialReviewApprovalType = specialReviewApprovalType;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public T getSpecialReviewExemption(int index) {
        return specialReviewExemptions.get(index);
    }
    
    public List<T> getSpecialReviewExemptions() {
        return specialReviewExemptions;
    }
    public void setSpecialReviewExemptions(List<T> specialReviewExemptions) {
        this.specialReviewExemptions = specialReviewExemptions;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("specialReviewNumber", getSpecialReviewNumber());
        hashMap.put("applicationDate", getApplicationDate());
        hashMap.put("approvalDate", getApprovalDate());
        hashMap.put("approvalTypeCode", getApprovalTypeCode());
        hashMap.put("comments", getComments());
        hashMap.put("protocolNumber", getProtocolNumber());
        hashMap.put("specialReviewCode", getSpecialReviewCode());
        return hashMap;
    }

    public abstract T newSpecialReviewExemption(String exemptionTypeCode);
    
    public void addSpecialReviewExemption(String exemptionTypeCode){
      getSpecialReviewExemptions().add(newSpecialReviewExemption(exemptionTypeCode));
    }

    /**
     * Gets the exemptionTypeCode attribute. 
     * @return Returns the exemptionTypeCode array.
     */
    @SuppressWarnings("unchecked")
    public String[] getExemptionTypeCodes() {
        if(exemptionTypeCodes==null && specialReviewExemptions.size() > 0){
            exemptionTypeCodes = new String[specialReviewExemptions.size()];
            int i = 0;
            for (Iterator iterator = specialReviewExemptions.iterator(); iterator.hasNext();) {
                AbstractSpecialReviewExemption specialReviewExemption = 
                    (AbstractSpecialReviewExemption) iterator.next();
                exemptionTypeCodes[i++] = specialReviewExemption.getExemptionTypeCode();
            }
        }
        return exemptionTypeCodes;
    }

    /**
     * Sets the exemptionTypeCode attribute value.
     * @param exemptionTypeCodes The exemptionTypeCode to set.
     */
    public void setExemptionTypeCodes(String... exemptionTypeCodes) {
        this.exemptionTypeCodes = exemptionTypeCodes;
    }
    
    public void clearExemptionTypeCodes() {
        this.exemptionTypeCodes = null;
    }

    /**
     * Gets the exemptionTypes attribute. 
     * @return Returns the exemptionTypes.
     */
    public List<ExemptionType> getExemptionTypes() {
        if(exemptionTypes.isEmpty()){
            populateExemptionTypes();
        }
        return exemptionTypes;
    }

    private void populateExemptionTypes() {
        KeyValuesService keyValueService = KNSServiceLocator.getKeyValuesService();
        exemptionTypes = (List)keyValueService.findAll(ExemptionType.class);
        Collections.sort(exemptionTypes);
    }

    /**
     * Sets the exemptionTypes attribute value.
     * @param exemptionTypes The exemptionTypes to set.
     */
    public void setExemptionTypes(List<ExemptionType> exemptionTypes) {
        this.exemptionTypes = exemptionTypes;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((applicationDate == null) ? 0 : applicationDate.hashCode());
        result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
        result = prime * result + ((approvalTypeCode == null) ? 0 : approvalTypeCode.hashCode());
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + Arrays.hashCode(exemptionTypeCodes);
        result = prime * result + ((exemptionTypes == null) ? 0 : exemptionTypes.hashCode());
        result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
        result = prime * result + ((protocolNumber == null) ? 0 : protocolNumber.hashCode());
        result = prime * result + ((specialReview == null) ? 0 : specialReview.hashCode());
        result = prime * result + ((specialReviewApprovalType == null) ? 0 : specialReviewApprovalType.hashCode());
        result = prime * result + ((specialReviewCode == null) ? 0 : specialReviewCode.hashCode());
        result = prime * result + ((specialReviewExemptions == null) ? 0 : specialReviewExemptions.hashCode());
        result = prime * result + ((specialReviewNumber == null) ? 0 : specialReviewNumber.hashCode());
        result = prime * result + ((validSpecialReviewApproval == null) ? 0 : validSpecialReviewApproval.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractSpecialReview other = (AbstractSpecialReview) obj;
        if (applicationDate == null) {
            if (other.applicationDate != null)
                return false;
        }
        else if (!applicationDate.equals(other.applicationDate))
            return false;
        if (approvalDate == null) {
            if (other.approvalDate != null)
                return false;
        }
        else if (!approvalDate.equals(other.approvalDate))
            return false;
        if (approvalTypeCode == null) {
            if (other.approvalTypeCode != null)
                return false;
        }
        else if (!approvalTypeCode.equals(other.approvalTypeCode))
            return false;
        if (comments == null) {
            if (other.comments != null)
                return false;
        }
        else if (!comments.equals(other.comments))
            return false;
        if (!Arrays.equals(exemptionTypeCodes, other.exemptionTypeCodes))
            return false;
        if (exemptionTypes == null) {
            if (other.exemptionTypes != null)
                return false;
        }
        else if (!exemptionTypes.equals(other.exemptionTypes))
            return false;
        if (expirationDate == null) {
            if (other.expirationDate != null)
                return false;
        }
        else if (!expirationDate.equals(other.expirationDate))
            return false;
        if (protocolNumber == null) {
            if (other.protocolNumber != null)
                return false;
        }
        else if (!protocolNumber.equals(other.protocolNumber))
            return false;
        if (specialReview == null) {
            if (other.specialReview != null)
                return false;
        }
        else if (!specialReview.equals(other.specialReview))
            return false;
        if (specialReviewApprovalType == null) {
            if (other.specialReviewApprovalType != null)
                return false;
        }
        else if (!specialReviewApprovalType.equals(other.specialReviewApprovalType))
            return false;
        if (specialReviewCode == null) {
            if (other.specialReviewCode != null)
                return false;
        }
        else if (!specialReviewCode.equals(other.specialReviewCode))
            return false;
        if (specialReviewExemptions == null) {
            if (other.specialReviewExemptions != null)
                return false;
        }
        else if (!specialReviewExemptions.equals(other.specialReviewExemptions))
            return false;
        if (specialReviewNumber == null) {
            if (other.specialReviewNumber != null)
                return false;
        }
        else if (!specialReviewNumber.equals(other.specialReviewNumber))
            return false;
        if (validSpecialReviewApproval == null) {
            if (other.validSpecialReviewApproval != null)
                return false;
        }
        else if (!validSpecialReviewApproval.equals(other.validSpecialReviewApproval))
            return false;
        return true;
    }

    public abstract Long getSpecialReviewId();

    public void setNewExemptionTypeCodes(String[] newExemptionTypeCodes) {
        this.newExemptionTypeCodes = newExemptionTypeCodes;
    }

    public String[] getNewExemptionTypeCodes() {
        return newExemptionTypeCodes;
    }
}
