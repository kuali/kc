/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions.submit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;
import org.kuali.kra.irb.personnel.ProtocolPersonRolodex;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.RolodexService;

@SuppressWarnings("serial")
public class ProtocolReviewer extends KraPersistableBusinessObjectBase {

    private Long protocolReviewerId;
    private Long protocolId;
    private Long submissionIdFk;
    private String protocolNumber;
    private Integer sequenceNumber;
    private Integer submissionNumber;

    private boolean nonEmployeeFlag;
    private String reviewerTypeCode;
    
    private Protocol protocol;
    private ProtocolSubmission protocolSubmission;
    private ProtocolReviewerType protocolReviewerType;
    
//    private ProtocolPersonRolodex rolodex;
//    private Integer rolodexId;
    
    private String personId;
    private transient KcPersonService kcPersonService;
    private transient KcPerson kcPerson;
    private transient RolodexService rolodexService;
    private transient Rolodex rolodex;
    
    // transient property for submission detail display
    
    @SkipVersioning
    transient private List<ProtocolOnlineReview> protocolOnlineReviews = new ArrayList<ProtocolOnlineReview>();

    public ProtocolReviewer() {
        
    }

    public Long getProtocolReviewerId() {
        return protocolReviewerId;
    }

    public void setProtocolReviewerId(Long protocolReviewerId) {
        this.protocolReviewerId = protocolReviewerId;
    }

    public Long getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(Long protocolId) {
        this.protocolId = protocolId;
    }

    public Long getSubmissionIdFk() {
        return submissionIdFk;
    }

    public void setSubmissionIdFk(Long submissionIdFk) {
        this.submissionIdFk = submissionIdFk;
    }

    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getSubmissionNumber() {
        return submissionNumber;
    }

    public void setSubmissionNumber(Integer submissionNumber) {
        this.submissionNumber = submissionNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }
    
    public void setReviewerTypeCode(String reviewerTypeCode) {
        this.reviewerTypeCode = reviewerTypeCode;
    }

    public String getReviewerTypeCode() {
        return reviewerTypeCode;
    }
    
    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public ProtocolSubmission getProtocolSubmission() {
        return protocolSubmission;
    }

    public void setProtocolSubmission(ProtocolSubmission protocolSubmission) {
        this.protocolSubmission = protocolSubmission;
    }

    public ProtocolReviewerType getProtocolReviewerType() {
        if (protocolReviewerType == null && StringUtils.isNotBlank(reviewerTypeCode)) {
            refreshReferenceObject("protocolReviewerType");
        }
        return protocolReviewerType;
    }

    public void setProtocolReviewerType(ProtocolReviewerType protocolReviewerType) {
        this.protocolReviewerType = protocolReviewerType;
    }

    /**
     * Gets the protocolOnlineReviews attribute. 
     * @return Returns the protocolOnlineReviews.
     */
    public List<ProtocolOnlineReview> getProtocolOnlineReviews() {
        return protocolOnlineReviews;
    }

    /**
     * Sets the protocolOnlineReviews attribute value.
     * @param protocolOnlineReviews The protocolOnlineReviews to set.
     */
    public void setProtocolOnlineReviews(List<ProtocolOnlineReview> protocolOnlineReviews) {
        this.protocolOnlineReviews = protocolOnlineReviews;
    }

    
//    public ProtocolPersonRolodex getRolodex() {
//        return this.rolodex;
//    }
//
//    /**
//     * Gets the rolodexId attribute. 
//     * @return Returns the rolodexId.
//     */
//    public Integer getRolodexId() {
//        return rolodexId;
//    }
//
//    /**
//     * Sets the rolodexId attribute value.
//     * @param rolodexId The rolodexId to set.
//     */
//    public void setRolodexId(Integer rolodexId) {
//        this.rolodexId = rolodexId;
//    }
//
//    /**
//     * Sets the rolodex attribute value.
//     * @param rolodex The rolodex to set.
//     */
//    public void setRolodex(ProtocolPersonRolodex rolodex) {
//        this.rolodex = rolodex;
//    }
//    
    
    @SuppressWarnings("unchecked")
    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("protocolReviewerId", getProtocolReviewerId());
        map.put("protocolId", getProtocolId());
        map.put("submissionIdFk", getSubmissionIdFk());
        map.put("protocolNumber", getProtocolNumber());
        map.put("sequenceNumber", getSequenceNumber());
        map.put("submissionNumber", getSubmissionNumber());
        map.put("personId", getPersonId());
        map.put("nonEmployeeFlag", getNonEmployeeFlag());
        map.put("reviewerTypeCode", getReviewerTypeCode());
        return map;
    }
    
    
    public Rolodex getRolodex() {
        if (nonEmployeeFlag) {
            return getRolodexService().getRolodex(Integer.parseInt(this.personId));
        } else {
            return null;
        }
    }

    public String getFullName() {
        if (nonEmployeeFlag) {
            return getRolodex().getFullName();
        } else {
            return getPerson().getFullName();
        }
    }

    
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(protocolOnlineReviews);
        return managedLists;
    }

    public KcPerson getPerson() {
        if (kcPerson == null) {
            kcPerson = getKcPersonService().getKcPersonByPersonId(this.personId);
        }
        return kcPerson;
    }
    
    
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }
    
    protected RolodexService getRolodexService() {
        if (this.rolodexService==null) {
            this.rolodexService = KraServiceLocator.getService(RolodexService.class);
        }
        return this.rolodexService;
    }


}
