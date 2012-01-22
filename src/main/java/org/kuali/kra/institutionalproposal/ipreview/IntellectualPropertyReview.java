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
package org.kuali.kra.institutionalproposal.ipreview;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.SequenceOwner;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.versioning.VersionStatus;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.InstitutionalProposalAssociate;
import org.kuali.kra.institutionalproposal.ProposalComment;
import org.kuali.kra.institutionalproposal.ProposalIpReviewJoin;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class represents an Intellectual Property Review.
 */
public class IntellectualPropertyReview extends InstitutionalProposalAssociate implements SequenceOwner<IntellectualPropertyReview> {

    private static final long serialVersionUID = 1L;

    private static final String GENERAL_COMMENT_CODE_PARM_NM = "proposalcommenttype.generalcomment";

    private static final String REVIEWER_COMMENT_CODE_PARM_NM = "proposalcommenttype.reviewercomment";

    private Long ipReviewId;

    private String ipReviewRequirementTypeCode;

    private Date reviewSubmissionDate;

    private Date reviewReceiveDate;

    private String reviewResultCode;

    private String ipReviewer;

    private List<ProposalComment> comments;

    private List<IntellectualPropertyReviewActivity> ipReviewActivities;

    private IntellectualPropertyReviewRequirementType ipReviewRequirementType;

    private IntellectualPropertyReviewResultType reviewResult;

    private String generalComments;

    private String reviewerComments;

    private List<ProposalIpReviewJoin> proposalIpReviewJoins;

    private String ipReviewSequenceStatus;

    private Long proposalIdToLink;

    private KcPerson person;

    private transient ParameterService parameterService;

    private transient KcPersonService kcPersonService;

    private transient String generalCommentCode;

    private transient String reviewerCommentCode;

    /**
     * Constructs an IntellectualPropertyReview.java.
     */
    public IntellectualPropertyReview() {
        init();
    }

    protected void init() {
        comments = new ArrayList<ProposalComment>();
        ipReviewActivities = new ArrayList<IntellectualPropertyReviewActivity>();
        proposalIpReviewJoins = new ArrayList<ProposalIpReviewJoin>();
        this.setIpReviewSequenceStatus(VersionStatus.PENDING.toString());
    }

    public Long getIpReviewId() {
        return this.ipReviewId;
    }

    public void setIpReviewId(Long ipReviewId) {
        this.ipReviewId = ipReviewId;
    }

    public String getIpReviewRequirementTypeCode() {
        return ipReviewRequirementTypeCode;
    }

    public void setIpReviewRequirementTypeCode(String ipReviewRequirementTypeCode) {
        this.ipReviewRequirementTypeCode = ipReviewRequirementTypeCode;
    }

    public Date getReviewSubmissionDate() {
        return reviewSubmissionDate;
    }

    public void setReviewSubmissionDate(Date reviewSubmissionDate) {
        this.reviewSubmissionDate = reviewSubmissionDate;
    }

    public Date getReviewReceiveDate() {
        return reviewReceiveDate;
    }

    public void setReviewReceiveDate(Date reviewReceiveDate) {
        this.reviewReceiveDate = reviewReceiveDate;
    }

    public String getReviewResultCode() {
        return reviewResultCode;
    }

    public void setReviewResultCode(String reviewResultCode) {
        this.reviewResultCode = reviewResultCode;
    }

    public String getIpReviewer() {
        return ipReviewer;
    }

    public void setIpReviewer(String ipReviewer) {
        this.ipReviewer = ipReviewer;
    }

    public List<ProposalComment> getComments() {
        return comments;
    }

    public void setComments(List<ProposalComment> comments) {
        this.comments = comments;
    }

    public String getGeneralComments() {
        return generalComments;
    }

    public void setGeneralComments(String generalComments) {
        this.generalComments = generalComments;
    }

    public String getReviewerComments() {
        return reviewerComments;
    }

    public void setReviewerComments(String reviewerComments) {
        this.reviewerComments = reviewerComments;
    }

    public List<IntellectualPropertyReviewActivity> getIpReviewActivities() {
        return ipReviewActivities;
    }

    public void setIpReviewActivities(List<IntellectualPropertyReviewActivity> ipReviewActivities) {
        this.ipReviewActivities = ipReviewActivities;
    }

    public KcPerson getPerson() {
        if (ipReviewer != null) {
            return getKcPersonService().getKcPersonByPersonId(ipReviewer);
        }
        return null;
    }

    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public IntellectualPropertyReviewRequirementType getIpReviewRequirementType() {
        return ipReviewRequirementType;
    }

    public void setIpReviewRequirementType(IntellectualPropertyReviewRequirementType ipReviewRequirementType) {
        this.ipReviewRequirementType = ipReviewRequirementType;
    }

    public IntellectualPropertyReviewResultType getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(IntellectualPropertyReviewResultType reviewResult) {
        this.reviewResult = reviewResult;
    }

    public List<ProposalIpReviewJoin> getProposalIpReviewJoins() {
        return proposalIpReviewJoins;
    }

    public void setProposalIpReviewJoins(List<ProposalIpReviewJoin> proposalIpReviewJoins) {
        this.proposalIpReviewJoins = proposalIpReviewJoins;
    }

    public String getIpReviewSequenceStatus() {
        return ipReviewSequenceStatus;
    }

    public void setIpReviewSequenceStatus(String ipReviewSequenceStatus) {
        this.ipReviewSequenceStatus = ipReviewSequenceStatus;
    }

    public Long getProposalIdToLink() {
        return proposalIdToLink;
    }

    public void setProposalIdToLink(Long proposalIdToLink) {
        this.proposalIdToLink = proposalIdToLink;
    }

    public ProposalIpReviewJoin getProposalIpReviewJoin() {
        if (!this.proposalIpReviewJoins.isEmpty()) {
            return this.proposalIpReviewJoins.get(0);
        }
        return null;
    }

    public void setProposalIpReviewJoin(ProposalIpReviewJoin proposalIpReviewJoin) {
        this.proposalIpReviewJoins.add(0, proposalIpReviewJoin);
    }

    public String getLeadUnitNumber() {
        if (!ObjectUtils.isNull(this.getProposalIpReviewJoin())) {
            return this.getProposalIpReviewJoin().getInstitutionalProposal().getUnitNumber();
        }
        return "";
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    protected void prePersist() {
        super.prePersist();
        this.setIpReviewSequenceStatus(VersionStatus.ACTIVE.toString());
        archiveCurrentActiveIpReview();
        transformDataBeforePersistence();
        getBusinessObjectService().save(this.getComments());
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    protected void preUpdate() {
        super.preUpdate();
        transformDataBeforePersistence();
        getBusinessObjectService().save(this.getComments());
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#afterLookup()
     */
    @Override
    protected void postLoad() {
        super.postLoad();
        loadProposalComments();
        transformDataAfterLookup();
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    protected void postPersist() {
        super.postPersist();
        if (ObjectUtils.isNotNull(this.getProposalIdToLink())) {
            updateProposalIpReviewJoin();
        }
    }

    protected void updateProposalIpReviewJoin() {
        ProposalIpReviewJoin proposalIpReviewJoin = this.getProposalIpReviewJoin();
        if (ObjectUtils.isNull(proposalIpReviewJoin)) {
            proposalIpReviewJoin = new ProposalIpReviewJoin();
        } else if (ObjectUtils.isNotNull(proposalIpReviewJoin.getProposalIpReviewJoinId())) {
            proposalIpReviewJoin.setProposalIpReviewJoinId(null);
        }
        proposalIpReviewJoin.setIpReviewId(this.getIpReviewId());
        proposalIpReviewJoin.setProposalId(this.getProposalIdToLink());
        getBusinessObjectService().save(proposalIpReviewJoin);
        this.setProposalIpReviewJoin(proposalIpReviewJoin);
    }

    @SuppressWarnings("unchecked")
    protected void archiveCurrentActiveIpReview() {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("proposalNumber", this.getProposalNumber());
        criteria.put("ipReviewSequenceStatus", VersionStatus.ACTIVE.toString());
        List<IntellectualPropertyReview> results = new ArrayList<IntellectualPropertyReview>(getBusinessObjectService().findMatching(IntellectualPropertyReview.class, criteria));
        if (!results.isEmpty()) {
            // There should only be one active version at a time  
            IntellectualPropertyReview ipReviewToArchive = results.get(0);
            ipReviewToArchive.setIpReviewSequenceStatus(VersionStatus.ARCHIVED.toString());
            getBusinessObjectService().save(ipReviewToArchive);
        }
    }

    protected void transformDataBeforePersistence() {
        if (this.getProposalIdToLink() != null) {
            loadProposalComments();
            if (this.getGeneralComments() != null) {
                addOrModifyComments(getGeneralCommentCode(), getGeneralComments());
            }
            if (this.getReviewerComments() != null) {
                addOrModifyComments(getReviewerCommentCode(), getReviewerComments());
            }
        }
        setReferenceFields();
    }

    protected void transformDataAfterLookup() {
        ProposalComment generalCommentFromList = getCommentFromList(getGeneralCommentCode());
        if (generalCommentFromList != null) {
            this.setGeneralComments(generalCommentFromList.getComments());
        }
        ProposalComment reviewerCommentFromList = getCommentFromList(getReviewerCommentCode());
        if (reviewerCommentFromList != null) {
            this.setReviewerComments(reviewerCommentFromList.getComments());
        }
    }

    protected void setReferenceFields() {
        for (IntellectualPropertyReviewActivity ipReviewActivity : ipReviewActivities) {
            ipReviewActivity.setIpReviewId(this.getIpReviewId());
            ipReviewActivity.setProposalNumber(this.getProposalNumber());
            ipReviewActivity.setSequenceNumber(this.getSequenceNumber());
        }
    }

    private void addOrModifyComments(String commentTypeCode, String comment) {
        ProposalComment commentFromList = getCommentFromList(commentTypeCode);
        if (commentFromList != null) {
            commentFromList.setComments(comment);
        } else {
            ProposalComment proposalComment = new ProposalComment();
            proposalComment.setCommentTypeCode(commentTypeCode);
            proposalComment.setProposalId(this.getProposalIdToLink());
            proposalComment.setProposalNumber(this.getProposalNumber());
            proposalComment.setSequenceNumber(this.getSequenceNumber());
            proposalComment.setComments(comment);
            comments.add(proposalComment);
        }
    }

    private ProposalComment getCommentFromList(String commentTypeCode) {
        for (ProposalComment proposalComment : this.getComments()) {
            if (proposalComment.getCommentTypeCode().equals(commentTypeCode)) {
                return proposalComment;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void loadProposalComments() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        if (!this.getProposalIpReviewJoins().isEmpty() && ObjectUtils.isNotNull(this.getProposalIpReviewJoin().getProposalId())) {
            fieldValues.put("proposalId", this.getProposalIpReviewJoin().getProposalId());
            this.setComments(new ArrayList<ProposalComment>(getBusinessObjectService().findMatching(ProposalComment.class, fieldValues)));
        } else if (this.getProposalIdToLink() != null) {
            fieldValues.put("proposalId", this.getProposalIdToLink());
            this.setComments(new ArrayList<ProposalComment>(getBusinessObjectService().findMatching(ProposalComment.class, fieldValues)));
        }
    }

    String getGeneralCommentCode() {
        if (generalCommentCode == null) {
            generalCommentCode = this.getParameterService().getParameterValueAsString(InstitutionalProposalDocument.class, GENERAL_COMMENT_CODE_PARM_NM);
        }
        return generalCommentCode;
    }

    String getReviewerCommentCode() {
        if (reviewerCommentCode == null) {
            reviewerCommentCode = this.getParameterService().getParameterValueAsString(InstitutionalProposalDocument.class, REVIEWER_COMMENT_CODE_PARM_NM);
        }
        return reviewerCommentCode;
    }

    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = CoreFrameworkServiceLocator.getParameterService();
        }
        return this.parameterService;
    }

    BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    InstitutionalProposalVersioningService getInstitutionalProposalVersioningService() {
        return KraServiceLocator.getService(InstitutionalProposalVersioningService.class);
    }

    /**
     * @see org.kuali.kra.SequenceOwner#getOwnerSequenceNumber()
     */
    public Integer getOwnerSequenceNumber() {
        return null;
    }

    /**
     * @see org.kuali.kra.SequenceOwner#incrementSequenceNumber()
     */
    public void incrementSequenceNumber() {
        this.setSequenceNumber(this.getSequenceNumber() + 1);
    }

    /**
     * @see org.kuali.kra.SequenceAssociate#getSequenceOwner()
     */
    public IntellectualPropertyReview getSequenceOwner() {
        return this;
    }

    public void setSequenceOwner(IntellectualPropertyReview newlyVersionedOwner) {
    }

    /**
     * @see org.kuali.kra.Sequenceable#resetPersistenceState()
     */
    public void resetPersistenceState() {
        this.ipReviewId = null;
    }

    /**
     * @see org.kuali.kra.SequenceOwner#getName()
     */
    public String getVersionNameField() {
        return "proposalNumber";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((comments == null) ? 0 : comments.hashCode());
        result = prime * result + ((generalComments == null) ? 0 : generalComments.hashCode());
        result = prime * result + ((ipReviewActivities == null) ? 0 : ipReviewActivities.hashCode());
        result = prime * result + ((ipReviewRequirementTypeCode == null) ? 0 : ipReviewRequirementTypeCode.hashCode());
        result = prime * result + ((ipReviewer == null) ? 0 : ipReviewer.hashCode());
        result = prime * result + ((ipReviewId == null) ? 0 : ipReviewId.hashCode());
        result = prime * result + ((reviewReceiveDate == null) ? 0 : reviewReceiveDate.hashCode());
        result = prime * result + ((reviewResultCode == null) ? 0 : reviewResultCode.hashCode());
        result = prime * result + ((reviewSubmissionDate == null) ? 0 : reviewSubmissionDate.hashCode());
        result = prime * result + ((reviewerComments == null) ? 0 : reviewerComments.hashCode());
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
        IntellectualPropertyReview other = (IntellectualPropertyReview) obj;
        if (comments == null) {
            if (other.comments != null) {
                return false;
            }
        } else if (!comments.equals(other.comments)) {
            return false;
        }
        if (generalComments == null) {
            if (other.generalComments != null) {
                return false;
            }
        } else if (!generalComments.equals(other.generalComments)) {
            return false;
        }
        if (ipReviewActivities == null) {
            if (other.ipReviewActivities != null) {
                return false;
            }
        } else if (!ipReviewActivities.equals(other.ipReviewActivities)) {
            return false;
        }
        if (ipReviewRequirementTypeCode == null) {
            if (other.ipReviewRequirementTypeCode != null) {
                return false;
            }
        } else if (!ipReviewRequirementTypeCode.equals(other.ipReviewRequirementTypeCode)) {
            return false;
        }
        if (ipReviewer == null) {
            if (other.ipReviewer != null) {
                return false;
            }
        } else if (!ipReviewer.equals(other.ipReviewer)) {
            return false;
        }
        if (ipReviewId == null) {
            if (other.ipReviewId != null) {
                return false;
            }
        } else if (!ipReviewId.equals(other.ipReviewId)) {
            return false;
        }
        if (reviewReceiveDate == null) {
            if (other.reviewReceiveDate != null) {
                return false;
            }
        } else if (!reviewReceiveDate.equals(other.reviewReceiveDate)) {
            return false;
        }
        if (reviewResultCode == null) {
            if (other.reviewResultCode != null) {
                return false;
            }
        } else if (!reviewResultCode.equals(other.reviewResultCode)) {
            return false;
        }
        if (reviewSubmissionDate == null) {
            if (other.reviewSubmissionDate != null) {
                return false;
            }
        } else if (!reviewSubmissionDate.equals(other.reviewSubmissionDate)) {
            return false;
        }
        if (reviewerComments == null) {
            if (other.reviewerComments != null) {
                return false;
            }
        } else if (!reviewerComments.equals(other.reviewerComments)) {
            return false;
        }
        return true;
    }
}
