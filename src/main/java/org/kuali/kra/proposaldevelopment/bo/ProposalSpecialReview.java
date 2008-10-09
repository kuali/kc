package org.kuali.kra.proposaldevelopment.bo;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumns;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.Basic;
import javax.persistence.Lob;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.core.util.TypedArrayList;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SpecialReview;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.ValidSpecialReviewApproval;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;

@IdClass(org.kuali.kra.proposaldevelopment.bo.id.ProposalSpecialReviewId.class)
@Entity
@Table(name="EPS_PROP_SPECIAL_REVIEW")
public class ProposalSpecialReview extends KraPersistableBusinessObjectBase {
    // TODO : temporarily change proposalnumber from string to integer to see if ojb willwork
    @Id
	@Column(name="PROPOSAL_NUMBER")
	private String proposalNumber;
    @Id
	@Column(name="SPECIAL_REVIEW_NUMBER")
	private Integer specialReviewNumber;
    @Column(name="APPLICATION_DATE")
	private Date applicationDate;
    @Column(name="APPROVAL_DATE")
	private Date approvalDate;
    @Column(name="EXPIRATION_DATE")
	private Date expirationDate;
    @Column(name="APPROVAL_TYPE_CODE")
	private String approvalTypeCode;
    @Lob
	@Basic(fetch=FetchType.LAZY)
	@Column(name="COMMENTS")
	private String comments;
    @Column(name="PROTOCOL_NUMBER")
	private String protocolNumber;
    @Column(name="SPECIAL_REVIEW_CODE")
	private String specialReviewCode;
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="SPECIAL_REVIEW_CODE", insertable=false, updatable=false)
	private SpecialReview specialReview;
    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="APPROVAL_TYPE_CODE", insertable=false, updatable=false)
	private SpecialReviewApprovalType specialReviewApprovalType;

    @OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumns({@JoinColumn(name="APPROVAL_TYPE_CODE", insertable=false, updatable=false), @JoinColumn(name="SPECIAL_REVIEW_CODE", insertable=false, updatable=false)})
	private ValidSpecialReviewApproval validSpecialReviewApproval;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.proposaldevelopment.bo.ProposalExemptNumber.class, mappedBy="proposalSpecialReview")
	private List proposalExemptNumbers;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST })
    @JoinColumn(name="PROPOSAL_NUMBER", insertable = false, updatable = false)
    private ProposalDevelopmentDocument proposalDevelopmentDocument;

    public ProposalSpecialReview() {
        super();
        proposalExemptNumbers = new TypedArrayList(ProposalExemptNumber.class);
    }
    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
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

    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        
        managedLists.add(getProposalExemptNumbers());
           
        return managedLists;
    }

    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap hashMap = new LinkedHashMap();
        hashMap.put("proposalNumber", getProposalNumber());
        hashMap.put("specialReviewNumber", getSpecialReviewNumber());
        hashMap.put("applicationDate", getApplicationDate());
        hashMap.put("approvalDate", getApprovalDate());
        hashMap.put("approvalTypeCode", getApprovalTypeCode());
        hashMap.put("comments", getComments());
        hashMap.put("protocolNumber", getProtocolNumber());
        hashMap.put("specialReviewCode", getSpecialReviewCode());
        return hashMap;
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
    
    public List getProposalExemptNumbers() {
        return proposalExemptNumbers;
    }
    public void setProposalExemptNumbers(List proposalExemptNumbers) {
        this.proposalExemptNumbers = proposalExemptNumbers;
    }
    
    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return proposalDevelopmentDocument;
    }
    
    public void setProposalDevelopmentDocument(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this.proposalDevelopmentDocument = proposalDevelopmentDocument;
    }
}

