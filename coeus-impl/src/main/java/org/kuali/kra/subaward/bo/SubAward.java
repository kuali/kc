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
package org.kuali.kra.subaward.bo;

import org.kuali.coeus.common.framework.custom.CustomDataContainer;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.framework.version.history.VersionHistorySearchBo;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.common.framework.type.ProposalType;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.SkipVersioning;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.kra.subaward.customdata.SubAwardCustomData;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.util.AutoPopulatingList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubAward extends KcPersistableBusinessObjectBase
implements Permissionable, SequenceOwner<SubAward>, CustomDataContainer, Negotiable {

    private static final long serialVersionUID = 1L;
    private static final String ROLODEX_ID_FIELD_NAME = "rolodexId";
    public static final String NOTIFICATION_TYPE_SUBMIT = "501";
 
    private Long subAwardId;
    private String subAwardCode;

    private Integer sequenceNumber;

    private String organizationId;

    private Date startDate;

    private Date endDate;

    private Integer subAwardTypeCode;

    private String purchaseOrderNum;

    private String title;

    private Integer statusCode;
    private String statusDescription;
    private String accountNumber;

    private String vendorNumber;

    private String requisitionerId;

    private String requisitionerUnit;

    private String archiveLocation;

    private Date closeoutDate;

    private String closeoutIndicator;

    private String fundingSourceIndicator;

    private String comments;

    private Integer siteInvestigator;

    private SubAwardDocument subAwardDocument;

    private SubAwardFundingSource subAwardFundingSource;

    private SubAwardContact subAwardContact;

    private SubAwardCloseout subAwardCloseout;

    private SubAwardReports subAwardReports;

    private String organizationName;

    private String requisitionerName;
    private String requisitionerUnitName;
    private String requisitionerUserName;
    private String siteInvestigatorName;
    private String siteInvestigatorId;
    private Organization organization;
    private Unit unit;
    private Rolodex rolodex;
    private transient String rolodexFirstName;
    private SubAwardStatus subAwardStatus;
    private AwardType subAwardType;
    private KcPerson kcPerson;
    private String subAwardSequenceStatus;
    private boolean newVersion;
    private ScaleTwoDecimal totalObligatedAmount ;
    private ScaleTwoDecimal totalAnticipatedAmount;
    private ScaleTwoDecimal totalAmountReleased;
    private ScaleTwoDecimal totalAvailableAmount;
    private transient String docIdStatus;
    private transient String lastUpdate;
    private String awardNumber;
    private transient boolean editSubAward = false;
    private transient boolean defaultOpen = true;

    private Integer costType;
    
    private Date executionDate;
    
    private String requisitionId;

    private String fedAwardProjDesc;
    private ScaleTwoDecimal fAndARate;
    private Boolean deMinimus;

    private SubAwardCostType subAwardCostType;

    private Date modificationEffectiveDate;
    private String modificationId;
    private Date performanceStartDate;
    private Date performanceEnddate;
    private List<SubAwardAttachments> subAwardAttachments;
    private List<SubAwardReports> subAwardReportList;
    private List<SubAwardTemplateInfo> subAwardTemplateInfo;
    private List<SubAwardPrintAgreement> subAwardPrintAgreement;
    private List<SubAwardForms> subAwardForms;
	private List<SubAwardFundingSource> subAwardFundingSourceList;
    private List<SubAwardAmountInfo> subAwardAmountInfoList;
    private List<SubAwardAmountInfo> allSubAwardAmountInfos;
    private List<SubAwardContact> subAwardContactsList;
    private List<SubAwardCloseout> subAwardCloseoutList;
    private List<SubAwardCustomData> subAwardCustomDataList;

    @SkipVersioning
    private transient List<SubAwardAmountReleased> subAwardAmountReleasedList;

    private VersionHistorySearchBo versionHistory;

    public List<SubAwardForms> getSubAwardForms() {
        return subAwardForms;
    }

    public void setSubAwardForms(List<SubAwardForms> subAwardForms) {
        this.subAwardForms = subAwardForms;
    }
    
    public void addForms(SubAwardForms subAwardForms) {
        this.getSubAwardForms().add(subAwardForms);
       
    }

    public List<SubAwardPrintAgreement> getSubAwardPrintAgreement() {
        if (this.subAwardPrintAgreement == null) {
            this.subAwardPrintAgreement = new ArrayList<>();
        }
        return subAwardPrintAgreement;
    }

    public void setSubAwardPrintAgreement(List<SubAwardPrintAgreement> subAwardPrintAgreement) {
        this.subAwardPrintAgreement = subAwardPrintAgreement;
    }

    public List<SubAwardTemplateInfo> getSubAwardTemplateInfo() {
        if (this.subAwardTemplateInfo == null) {
            this.subAwardTemplateInfo = new ArrayList<>();
        }
        return subAwardTemplateInfo;
    }

    public void setSubAwardTemplateInfo(List<SubAwardTemplateInfo> subAwardTemplateInfo) {
        this.subAwardTemplateInfo = subAwardTemplateInfo;
    }

    public SubAwardReports getSubAwardReports() {
        return subAwardReports;
    }

    public void setSubAwardReports(SubAwardReports subAwardReports) {
        this.subAwardReports = subAwardReports;
    }

    public List<SubAwardReports> getSubAwardReportList() {
        if (this.subAwardReportList == null) {
            this.subAwardReportList = new ArrayList<>();
        }
        return this.subAwardReportList;
    }
    
    public List<SubAwardAttachments> getSubAwardAttachments() { 
        if (this.subAwardAttachments == null) {
            this.subAwardAttachments = new ArrayList<>();
        }

        return this.subAwardAttachments;
    }

    public void setAttachments(List<SubAwardAttachments> attachments) {
            this.subAwardAttachments = attachments;
       }
    
    public void setReports(List<SubAwardReports> reports) {
        this.subAwardReportList = reports;
   }

    public SubAwardReports getSubAwardReportList(int index) {
        return this.subAwardReportList.get(index);
    }
    
    public SubAwardAttachments getSubAwardAttachment(int index) {
        return this.subAwardAttachments.get(index);
    }

    public void addAttachment(SubAwardAttachments attachment) {
        this.getSubAwardAttachments().add(attachment);
        attachment.setSubAward(this);
    }

    public void addReport(SubAwardReports report) {
        this.getSubAwardReportList().add(report);
        report.setSubAward(this);
    }

	public Rolodex getRolodex() {
		return rolodex;
	}

	public void setRolodex(Rolodex rolodex) {
		this.rolodex = rolodex;
	}

	public KcPerson getKcPerson() {
		return kcPerson;
	}

	public void setKcPerson(KcPerson kcPerson) {
		this.kcPerson = kcPerson;
	}

	public String getSubAwardSequenceStatus() {
		return subAwardSequenceStatus;
	}

	public void setSubAwardSequenceStatus(String subAwardSequenceStatus) {
		this.subAwardSequenceStatus = subAwardSequenceStatus;
	}

	public String getRolodexFirstName() {
        if (getRolodex() == null) {
            return this.rolodexFirstName;
        } else {
            return getRolodex().getFirstName();
        }
    }

    public void setRolodexFirstName(String rolodexFirstName) {
        this.rolodexFirstName = rolodexFirstName;
    }

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getOrganizationName() {
        Organization organization = getOrganization();
        if (organization != null) {
            return organization.getOrganizationName();
        } else {
            return organizationName;
        }
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

	public String getRequisitionerName() {
	    //calling getRequisitionerUserName() as that function sets the requestionsitioner name
	    getRequisitionerUserName();
		return requisitionerName;
	}

	public void setRequisitionerName(String requisitionerName) {
		this.requisitionerName = requisitionerName;
	}

	public String getRequisitionerUnitName() {
		return requisitionerUnitName;
	}

	public void setRequisitionerUnitName(String requisitionerUnitName) {
		this.requisitionerUnitName = requisitionerUnitName;
	}

	public String getRequisitionerUserName() {
        if (requisitionerId != null) {
            KcPerson requisitioner = getRequisitioner();
            if (requisitioner != null) {
                requisitionerName = requisitioner.getFullName();
                requisitionerUserName = requisitioner.getUserName();
            }
        } else {
            this.requisitionerName = null;
        }
        return this.requisitionerUserName;
    }

    public void setRequisitionerUserName(String requisitionerUserName) {
        if (requisitionerUserName != null) {
            KcPerson requisitioner = KcServiceLocator.
            getService(KcPersonService.class).getKcPersonByUserName(
                    requisitionerUserName);
            if (requisitioner != null) {
                requisitionerId = requisitioner.getPersonId();
            }
        } else {
            this.requisitionerName = null;
        }
        this.requisitionerUserName = requisitionerUserName;
    }
    
    public KcPerson getRequisitioner() {
        if (requisitionerId != null) {
            return KcServiceLocator.getService(
                    KcPersonService.class).getKcPersonByPersonId(requisitionerId);
        } else {
            return null;
        }
    }

	public String getSiteInvestigatorName() {
		return siteInvestigatorName;
	}

	public void setSiteInvestigatorName(String siteInvestigatorName) {
		this.siteInvestigatorName = siteInvestigatorName;
	}

    public SubAward() {
        super();
        initializeCollections();
        initialize();

    }

    protected void initialize() {
        setSequenceNumber(1);
        subAwardSequenceStatus = VersionStatus.PENDING.name();
        setNewVersion(false);
    }

	public Long getSubAwardId() {
		return subAwardId;
	}

	public void setSubAwardId(Long subAwardId) {
		this.subAwardId = subAwardId;
	}

	public String getSubAwardCode() {
		return subAwardCode;
	}

	public void setSubAwardCode(String subAwardCode) {
		this.subAwardCode = subAwardCode;
	}

	@Override
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getOrganizationId() {
        OrganizationService organizationService = KcServiceLocator.
       getService(OrganizationService.class);
        this.organization = organizationService.getOrganization(organizationId);
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getSubAwardTypeCode() {
		return subAwardTypeCode;
	}

	public void setSubAwardTypeCode(Integer subAwardTypeCode) {
		this.subAwardTypeCode = subAwardTypeCode;
	}

	public String getPurchaseOrderNum() {
		return purchaseOrderNum;
	}

	public void setPurchaseOrderNum(String purchaseOrderNum) {
		this.purchaseOrderNum = purchaseOrderNum;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getVendorNumber() {
		return vendorNumber;
	}

	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	public String getRequisitionerId() {
		return requisitionerId;
	}

	public void setRequisitionerId(String requisitionerId) {
		this.requisitionerId = requisitionerId;
	}

	public String getRequisitionerUnit() {
        if (this.requisitionerUnit != null) {
            UnitService unitService = KcServiceLocator.
            getService(UnitService.class);
            this.unit = unitService.getUnit(requisitionerUnit);
        }
        return requisitionerUnit;
    }

    public void setRequisitionerUnit(String requisitionerUnit) {
        this.requisitionerUnit = requisitionerUnit;
    }

	public String getArchiveLocation() {
		return archiveLocation;
	}

	public void setArchiveLocation(String archiveLocation) {
		this.archiveLocation = archiveLocation;
	}

	public Date getCloseoutDate() {
		return closeoutDate;
	}

	public void setCloseoutDate(Date closeoutDate) {
		this.closeoutDate = closeoutDate;
	}

	public String getCloseoutIndicator() {
		return closeoutIndicator;
	}

	public void setCloseoutIndicator(String closeoutIndicator) {
		this.closeoutIndicator = closeoutIndicator;
	}

	public String getFundingSourceIndicator() {
		return fundingSourceIndicator;
	}

	public void setFundingSourceIndicator(String fundingSourceIndicator) {
		this.fundingSourceIndicator = fundingSourceIndicator;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getSiteInvestigator() {
        if (siteInvestigator != null) {
            BusinessObjectService businessObjectService = KcServiceLocator.
            getService(BusinessObjectService.class);
            this.rolodex = businessObjectService.
            findByPrimaryKey(Rolodex.class,
            getIdentifierMap(ROLODEX_ID_FIELD_NAME, siteInvestigator));
            this.siteInvestigatorId = rolodex.getRolodexId().toString();
        } else {
            this.rolodex = null;
        }
        return siteInvestigator;
    }

    public void setSiteInvestigator(Integer siteInvestigator) {
        if (siteInvestigator != null) {
            BusinessObjectService businessObjectService = KcServiceLocator.
            getService(BusinessObjectService.class);
            this.rolodex = businessObjectService.
            findByPrimaryKey(NonOrganizationalRolodex.class,
            getIdentifierMap(ROLODEX_ID_FIELD_NAME, siteInvestigator));
            this.siteInvestigatorId = rolodex.getRolodexId().toString();
        }
        this.siteInvestigator = siteInvestigator;
    }

	public SubAwardFundingSource getSubAwardFundingSource() {
		return subAwardFundingSource;
	}

	public void setSubAwardFundingSource(SubAwardFundingSource subAwardFundingSource) {
		this.subAwardFundingSource = subAwardFundingSource;
	}

	public SubAwardContact getSubAwardContact() {
		return subAwardContact;
	}

	public void setSubAwardContact(SubAwardContact subAwardContact) {
		this.subAwardContact = subAwardContact;
	}

	public SubAwardCloseout getSubAwardCloseout() {
		return subAwardCloseout;
	}

	public void setSubAwardCloseout(SubAwardCloseout subAwardCloseout) {
		this.subAwardCloseout = subAwardCloseout;
	}

	public List<SubAwardFundingSource> getSubAwardFundingSourceList() {
		return subAwardFundingSourceList;
	}

	public void setSubAwardFundingSourceList(
			List<SubAwardFundingSource> subAwardFundingSourceList) {
		this.subAwardFundingSourceList = subAwardFundingSourceList;
	}

	public List<SubAwardAmountInfo> getSubAwardAmountInfoList() {
		return subAwardAmountInfoList;
	}

	public void setSubAwardAmountInfoList(
			List<SubAwardAmountInfo> subAwardAmountInfoList) {
		this.subAwardAmountInfoList = subAwardAmountInfoList;
	}

	public List<SubAwardAmountReleased> getSubAwardAmountReleasedList() {
        if (this.subAwardAmountReleasedList == null) {
            Map<String, Object> values = new HashMap<>();
            values.put("subAwardCode", this.getSubAwardCode());
            this.setSubAwardAmountReleasedList((List<SubAwardAmountReleased>)KcServiceLocator.getService(BusinessObjectService.class).findMatchingOrderBy(SubAwardAmountReleased.class, values, "createdDate", false));
        }
        return this.subAwardAmountReleasedList;
	}

    public void setSubAwardAmountReleasedList(List<SubAwardAmountReleased> subAwardAmountReleasedList) {
        this.subAwardAmountReleasedList = subAwardAmountReleasedList;
    }

	public List<SubAwardContact> getSubAwardContactsList() {
		return subAwardContactsList;
	}

	public void setSubAwardContactsList(List<SubAwardContact> subAwardContactsList) {
		this.subAwardContactsList = subAwardContactsList;
	}

	public List<SubAwardCloseout> getSubAwardCloseoutList() {
		return subAwardCloseoutList;
	}

	public void setSubAwardCloseoutList(List<SubAwardCloseout> subAwardCloseoutList) {
		this.subAwardCloseoutList = subAwardCloseoutList;
	}

	public List<SubAwardCustomData> getSubAwardCustomDataList() {
		return subAwardCustomDataList;
	}

	public void setSubAwardCustomDataList(
			List<SubAwardCustomData> subAwardCustomDataList) {
		this.subAwardCustomDataList = subAwardCustomDataList;
	}

	public String getStatusDescription() {
        SubAwardStatus status = getSubAwardStatus();
        statusDescription = status != null ? status.getDescription() : null;
        return statusDescription;
    }

    protected void initializeCollections() {
        subAwardFundingSourceList = new AutoPopulatingList<>(SubAwardFundingSource.class);
        subAwardAmountInfoList = new AutoPopulatingList<>(SubAwardAmountInfo.class);
        allSubAwardAmountInfos = new ArrayList<>();
        subAwardContactsList = new AutoPopulatingList<>(SubAwardContact.class);
        subAwardCloseoutList = new AutoPopulatingList<>(SubAwardCloseout.class);
        subAwardCustomDataList = new AutoPopulatingList<>(SubAwardCustomData.class);
        subAwardReportList = new AutoPopulatingList<>(SubAwardReports.class);
    }

    public void setSubAwardDocument(SubAwardDocument subAwardDocument) {
        this.subAwardDocument = subAwardDocument;
    }

    public SubAwardDocument getSubAwardDocument() {
        if (subAwardDocument == null) {
            this.refreshReferenceObject("subAwardDocument");
        }
        return subAwardDocument;
    }

    public void setSubAwardStatus(SubAwardStatus subAwardStatus) {
        this.subAwardStatus = subAwardStatus;
    }

    public SubAwardStatus getSubAwardStatus() {
        if (subAwardStatus == null && statusCode != null) {
            refreshReferenceObject("subAwardStatus");
        }
        return subAwardStatus;
    }

    public void setSubAwardType(AwardType subAwardType) {
        this.subAwardType = subAwardType;
    }

    public AwardType getSubAwardType() {
        return subAwardType;
    }

    @Override
    public String getDocumentNumberForPermission() {
        if (subAwardId != null) {
            return subAwardId.toString();
        } else {
            return null;
        }
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.SUBAWARD_KEY;
    }

    @Override
    public List<String> getRoleNames() {
        return null;
    }

    @Override
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_SUBAWARD;
}

    @Override
    public String getLeadUnitNumber() {
        return this.getUnit() != null ?
       this.getUnit().getUnitNumber() : EMPTY_STRING;
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return null;
    }

    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        if (getSubAwardDocument() != null) {
            qualifiedRoleAttributes.put("documentNumber", getSubAwardDocument().getDocumentNumber());
        }

    }
    @Override
    public void incrementSequenceNumber() {
        this.sequenceNumber++;
    }
    @Override
    public void setSequenceOwner(SubAward newlyVersionedOwner) {

    }
    @Override
    public SubAward getSequenceOwner() {
        return this;
    }
    @Override
    public void resetPersistenceState() {
       this.subAwardId=null;
    }

    @Override
    public Integer getOwnerSequenceNumber() {
        return null;
    }

    @Override
    public String getVersionNameField() {
        return "subAwardCode";
    }

    @Override
    public String getVersionNameFieldValue() {
        return subAwardCode;
    }

    public void setNewVersion (boolean newVersion){
        this.newVersion = newVersion;
    }

    public boolean getNewVersion( ) {
        return this.newVersion;
    }

	public ScaleTwoDecimal getTotalObligatedAmount() {
		return totalObligatedAmount;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setTotalObligatedAmount(ScaleTwoDecimal totalObligatedAmount) {
		this.totalObligatedAmount = totalObligatedAmount;
	}

	public ScaleTwoDecimal getTotalAnticipatedAmount() {
		return totalAnticipatedAmount;
	}

	public void setTotalAnticipatedAmount(ScaleTwoDecimal totalAnticipatedAmount) {
		this.totalAnticipatedAmount = totalAnticipatedAmount;
	}

	public ScaleTwoDecimal getTotalAmountReleased() {
		return totalAmountReleased;
	}

	public void setTotalAmountReleased(ScaleTwoDecimal totalAmountReleased) {
		this.totalAmountReleased = totalAmountReleased;
	}

	public ScaleTwoDecimal getTotalAvailableAmount() {
		return totalAvailableAmount;
	}

	public void setTotalAvailableAmount(ScaleTwoDecimal totalAvailableAmount) {
		this.totalAvailableAmount = totalAvailableAmount;
	}

	public String getDocIdStatus() {
		return docIdStatus;
	}

	public void setDocIdStatus(String docIdStatus) {
		this.docIdStatus = docIdStatus;
	}

	public String getSiteInvestigatorId() {
        if (this.siteInvestigatorId == null && this.siteInvestigator != null) {
            siteInvestigatorId = this.siteInvestigator.toString();
        }
        return siteInvestigatorId;
    }

    public void setSiteInvestigatorId(String siteInvestigatorId) {        
        this.siteInvestigatorId = siteInvestigatorId;
    }

    @Override
    public String getAssociatedDocumentId() {
        return this.getSubAwardCode();
    }

    @Override
    public String getLeadUnitName() {
        return this.getUnit() != null ? this.getUnit().getUnitName() : EMPTY_STRING;
    }

    @Override
    public String getPiName() {
        return EMPTY_STRING;
    }

    @Override
    public String getPiEmployeeName() {
        return EMPTY_STRING;
    }

    @Override
    public String getPiNonEmployeeName() {
        return EMPTY_STRING;
    }

    @Override
    public String getAdminPersonName() {
        return EMPTY_STRING;
    }

    @Override
    public String getSponsorCode() {
        return EMPTY_STRING;
    }

    @Override
    public String getSponsorName() {
        return EMPTY_STRING;
    }

    @Override
    public String getPrimeSponsorCode() {
        return EMPTY_STRING;
    }

    @Override
    public String getPrimeSponsorName() {
        return EMPTY_STRING;
    }

    @Override
    public String getSponsorAwardNumber() {
        return EMPTY_STRING;
    }

    @Override
    public String getSubAwardOrganizationName() {
        return this.getOrganization() != null ?
       this.getOrganization().getOrganizationName() : EMPTY_STRING;
    }

    @Override
    public List<NegotiationPersonDTO> getProjectPeople() {
        List<NegotiationPersonDTO> people = new ArrayList<>();
        if (this.getKcPerson() != null) {
            people.add(new NegotiationPersonDTO(this.getKcPerson(), "admin"));
        }
        return people;
    }

    @Override
    public String getNegotiableProposalTypeCode() {
        return EMPTY_STRING;
    }

    @Override
    public ProposalType getNegotiableProposalType() {
        return null;
    }

    @Override
    public String getSubAwardRequisitionerName() {
        return this.getRequisitionerName();
    }

    @Override
    public String getSubAwardRequisitionerUnitNumber() {
        return this.getUnit() != null ? this.getUnit().getUnitNumber() : EMPTY_STRING;
    }

    @Override
    public String getSubAwardRequisitionerUnitName() {
        return this.getUnit() != null ? this.getUnit().
        		getUnitName() : EMPTY_STRING;
    }

    @Override
    public String getSubAwardRequisitionerId() {
        return this.getRequisitionerId();
    }

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public boolean isEditSubAward() {
		return editSubAward;
	}

	public void setEditSubAward(boolean editSubAward) {
		this.editSubAward = editSubAward;
	}

    protected Map<String, Object> getIdentifierMap(String identifierField, Object identifierValue) {
        Map<String, Object> map = new HashMap<>();
        map.put(identifierField, identifierValue);
        return map;
    }

    public boolean isDefaultOpen() {
        return defaultOpen;
    }

    public void setDefaultOpen(boolean defaultOpen) {
        this.defaultOpen = defaultOpen;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public String getRequisitionId() {
        return requisitionId;
    }

    public void setRequisitionId(String requisitionId) {
        this.requisitionId = requisitionId;
    }

    public SubAwardCostType getSubAwardCostType() {
        return subAwardCostType;
    }

    public void setSubAwardCostType(SubAwardCostType subAwardCostType) {
        this.subAwardCostType = subAwardCostType;
    }

    public Date getModificationEffectiveDate() {
        return modificationEffectiveDate;
    }

    public void setModificationEffectiveDate(Date modificationEffectiveDate) {
        this.modificationEffectiveDate = modificationEffectiveDate;
    }

    public String getModificationId() {
        return modificationId;
    }

    public void setModificationId(String modificationId) {
        this.modificationId = modificationId;
    }

    public Date getPerformanceStartDate() {
        return performanceStartDate;
    }

    public void setPerformanceStartDate(Date performanceStartDate) {
        this.performanceStartDate = performanceStartDate;
    }

    public Date getPerformanceEnddate() {
        return performanceEnddate;
    }

    public void setPerformanceEnddate(Date performanceEnddate) {
        this.performanceEnddate = performanceEnddate;
    }

    public VersionHistorySearchBo getVersionHistory() {
        return versionHistory;
    }

    public void setVersionHistory(VersionHistorySearchBo versionHistory) {
        this.versionHistory = versionHistory;
    }

    public Boolean getDeMinimus() {
        return deMinimus;
    }

    public void setDeMinimus(Boolean deMinimus) {
        this.deMinimus = deMinimus;
    }

    public ScaleTwoDecimal getfAndARate() {
        return fAndARate;
    }

    public void setfAndARate(ScaleTwoDecimal fAndARate) {
        this.fAndARate = fAndARate;
    }

    public String getFedAwardProjDesc() {
        return fedAwardProjDesc;
    }

    public void setFedAwardProjDesc(String fedAwardProjDesc) {
        this.fedAwardProjDesc = fedAwardProjDesc;
    }

	public List<SubAwardAmountInfo> getAllSubAwardAmountInfos() {
		return allSubAwardAmountInfos;
	}

    public SubAwardAmountInfo getLatestSubAwardAmountInfo() {
        if (allSubAwardAmountInfos.size() > 0) {
            return allSubAwardAmountInfos.get(allSubAwardAmountInfos.size() - 1);
        }
        return null;
    }

	public void setAllSubAwardAmountInfos(
			List<SubAwardAmountInfo> allSubAwardAmountInfos) {
		this.allSubAwardAmountInfos = allSubAwardAmountInfos;
	}
	
	public List<SubAwardAmountInfo> getHistoricalAmountInfos() {
		final List<Integer> currentAmountInfoIds = getSubAwardAmountInfoList().stream()
				.map(SubAwardAmountInfo::getSubAwardAmountInfoId).collect(Collectors.toList());
		return getAllSubAwardAmountInfos().stream()
				.filter(amountInfo -> !currentAmountInfoIds.contains(amountInfo.getSubAwardAmountInfoId()))
				.collect(Collectors.toList());
	}

    @Override
    public List<? extends DocumentCustomData> getCustomDataList() {
        return getSubAwardCustomDataList();
    }
}
