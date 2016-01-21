/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.negotiations.bo.Negotiable;
import org.kuali.kra.negotiations.bo.NegotiationPersonDTO;
import org.kuali.kra.subaward.customdata.SubAwardCustomData;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.subaward.lookup.SubAwardDocumentStatusConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.util.AutoPopulatingList;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 
 * This class is using for SubAward...
 */
public class SubAward extends KcPersistableBusinessObjectBase
implements Permissionable, SequenceOwner<SubAward>, Negotiable {

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

    private VersionHistorySearchBo versionHistory;

    /**
     * Gets the subAwardForms attribute. 
     * @return Returns the subAwardForms.
     */
    public List<SubAwardForms> getSubAwardForms() {
        return subAwardForms;
    }

    /**
     * Sets the subAwardForms attribute value.
     * @param subAwardForms The subAwardForms to set.
     */
    public void setSubAwardForms(List<SubAwardForms> subAwardForms) {
        this.subAwardForms = subAwardForms;
    }
    
    public void addForms(SubAwardForms subAwardForms) {
        this.getSubAwardForms().add(subAwardForms);
       
    }

    /**
     * Gets the subAwardPrintAgreement attribute. 
     * @return Returns the subAwardPrintAgreement.
     */
    public List<SubAwardPrintAgreement> getSubAwardPrintAgreement() {
        if (this.subAwardPrintAgreement == null) {
            this.subAwardPrintAgreement = new ArrayList<SubAwardPrintAgreement>();
        }
        return subAwardPrintAgreement;
    }

    /**
     * Sets the subAwardPrintAgreement attribute value.
     * @param subAwardPrintAgreement The subAwardPrintAgreement to set.
     */
    public void setSubAwardPrintAgreement(List<SubAwardPrintAgreement> subAwardPrintAgreement) {
        this.subAwardPrintAgreement = subAwardPrintAgreement;
    }

    /**
         * Gets the subAwardTemplateInfo attribute. 
         * @return Returns the subAwardTemplateInfo.
         */
        public List<SubAwardTemplateInfo> getSubAwardTemplateInfo() {
            if (this.subAwardTemplateInfo == null) {
                this.subAwardTemplateInfo = new ArrayList<SubAwardTemplateInfo>();
            }
            return subAwardTemplateInfo;
        }

        /**
         * Sets the subAwardTemplateInfo attribute value.
         * @param subAwardTemplateInfo The subAwardTemplateInfo to set.
         */
        public void setSubAwardTemplateInfo(List<SubAwardTemplateInfo> subAwardTemplateInfo) {
            this.subAwardTemplateInfo = subAwardTemplateInfo;
        }
    
    
    /**
     * Gets the subAwardReports attribute. 
     * @return Returns the subAwardReports.
     */
    public SubAwardReports getSubAwardReports() {
        return subAwardReports;
    }

    /**
     * Sets the subAwardReports attribute value.
     * @param subAwardReports The subAwardReports to set.
     */
    public void setSubAwardReports(SubAwardReports subAwardReports) {
        this.subAwardReports = subAwardReports;
    }
    
    /**
     * Gets the subAwardReportList attribute. 
     * @return Returns the subAwardReportList.
     */
    public List<SubAwardReports> getSubAwardReportList() {
        if (this.subAwardReportList == null) {
            this.subAwardReportList = new ArrayList<SubAwardReports>();
        }
        return this.subAwardReportList;
    }
    
    public List<SubAwardAttachments> getSubAwardAttachments() { 
        if (this.subAwardAttachments == null) {
            this.subAwardAttachments = new ArrayList<SubAwardAttachments>();
        }

        return this.subAwardAttachments;
    }

    public void setAttachments(List<SubAwardAttachments> attachments) {
            this.subAwardAttachments = attachments;
       }
    
    public void setReports(List<SubAwardReports> reports) {
        this.subAwardReportList = reports;
   }
    /**
     * Gets an attachment.
     * @param index the index
     * @return an attachment personnel
     */
    public SubAwardReports getSubAwardReportList(int index) {
        return this.subAwardReportList.get(index);
    }
    
    public SubAwardAttachments getSubAwardAttachment(int index) {
        return this.subAwardAttachments.get(index);
    }
    /**
     * add an attachment.
     * @param attachment the attachment
     * @throws IllegalArgumentException if attachment is null
     */
    public void addAttachment(SubAwardAttachments attachment) {
        this.getSubAwardAttachments().add(attachment);
        attachment.setSubAward(this);
    }
    /**
     * add an attachment.
     * @param report 
     * @throws IllegalArgumentException if attachment is null
     */
    public void addReport(SubAwardReports report) {
        this.getSubAwardReportList().add(report);
        report.setSubAward(this);
    }
    /**.
     * This is the Getter Method for rolodex
     * @return Returns the rolodex.
     */


    /**.
	 * This is the Getter Method for rolodex
	 * @return Returns the rolodex.
	 */
	public Rolodex getRolodex() {
		return rolodex;
	}

	/**.
	 * This is the Setter Method for rolodex
	 * @param rolodex The rolodex to set.
	 */
	public void setRolodex(Rolodex rolodex) {
		this.rolodex = rolodex;
	}

	/**.
	 * This is the Getter Method for kcPerson
	 * @return Returns the kcPerson.
	 */
	public KcPerson getKcPerson() {
		return kcPerson;
	}

	/**.
	 * This is the Setter Method for kcPerson
	 * @param kcPerson The kcPerson to set.
	 */
	public void setKcPerson(KcPerson kcPerson) {
		this.kcPerson = kcPerson;
	}

	/**.
	 * This is the Getter Method for subAwardSequenceStatus
	 * @return Returns the subAwardSequenceStatus.
	 */
	public String getSubAwardSequenceStatus() {
		return subAwardSequenceStatus;
	}

	/**.
	 * This is the Setter Method for subAwardSequenceStatus
	 * @param subAwardSequenceStatus The subAwardSequenceStatus to set.
	 */
	public void setSubAwardSequenceStatus(String subAwardSequenceStatus) {
		this.subAwardSequenceStatus = subAwardSequenceStatus;
	}

    public String getSubAwardSequenceStatusResult() {
        if (SubAwardDocumentStatusConstants.Active.code().equals(getSubAwardSequenceStatus())) {
            return SubAwardDocumentStatusConstants.Active.description();
        }
        else {
            return SubAwardDocumentStatusConstants.Pending.description();
        }
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

    /**.
	 * This is the Getter Method for organization
	 * @return Returns the organization.
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**.
	 * This is the Setter Method for organization
	 * @param organization The organization to set.
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**.
	 * This is the Getter Method for unit
	 * @return Returns the unit.
	 */
	public Unit getUnit() {
		return unit;
	}

	/**.
	 * This is the Setter Method for unit
	 * @param unit The unit to set.
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	/**.
	 * This is the Getter Method for OrganizationName
	 * @return Returns organizationName.
	 */
	public String getOrganizationName() {
        Organization organization = getOrganization();
        if (organization != null) {
            return organization.getOrganizationName();
        } else {
            return organizationName;
        }
    }
	/**.
	 * This is the Setter Method for Organizationname
	 * @param organizationName The organizationName to set.
	 */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }



    /**.
	 * This is the Getter Method for requisitionerName
	 * @return Returns the requisitionerName.
	 */
	public String getRequisitionerName() {
	    //calling getRequisitionerUserName() as that function sets the requestionsitioner name
	    getRequisitionerUserName();
		return requisitionerName;
	}

	/**.
	 * This is the Setter Method for requisitionerName
	 * @param requisitionerName The requisitionerName to set.
	 */
	public void setRequisitionerName(String requisitionerName) {
		this.requisitionerName = requisitionerName;
	}

	/**.
	 * This is the Getter Method for requisitionerUnitName
	 * @return Returns the requisitionerUnitName.
	 */
	public String getRequisitionerUnitName() {
		return requisitionerUnitName;
	}

	/**.
	 * This is the Setter Method for requisitionerUnitName
	 * @param requisitionerUnitName The requisitionerUnitName to set.
	 */
	public void setRequisitionerUnitName(String requisitionerUnitName) {
		this.requisitionerUnitName = requisitionerUnitName;
	}
	/**.
	 * This is the Getter Method for requisitionerUserName
	 * @return Returns the requisitionerUserName.
	 */
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
	/**.
	 * This is the Setter Method for requisitionerUserName
	 * @param requisitionerUserName The requisitionerUserName to set.
	 */
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

  

    /**.
	 * This is the Getter Method for siteInvestigatorName
	 * @return Returns the siteInvestigatorName.
	 */
	public String getSiteInvestigatorName() {
		return siteInvestigatorName;
	}

	/**.
	 * This is the Setter Method for siteInvestigatorName
	 * @param siteInvestigatorName The siteInvestigatorName to set.
	 */
	public void setSiteInvestigatorName(String siteInvestigatorName) {
		this.siteInvestigatorName = siteInvestigatorName;
	}

    /**.
	 * This creates subAwardConstructor
	 */
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


    /**.
	 * This is the Getter Method for subAwardId
	 * @return Returns the subAwardId.
	 */
	public Long getSubAwardId() {
		return subAwardId;
	}

	/**.
	 * This is the Setter Method for subAwardId
	 * @param subAwardId The subAwardId to set.
	 */
	public void setSubAwardId(Long subAwardId) {
		this.subAwardId = subAwardId;
	}

	/**.
	 * This is the Getter Method for subAwardCode
	 * @return Returns the subAwardCode.
	 */
	public String getSubAwardCode() {
		return subAwardCode;
	}

	/**.
	 * This is the Setter Method for subAwardCode
	 * @param subAwardCode The subAwardCode to set.
	 */
	public void setSubAwardCode(String subAwardCode) {
		this.subAwardCode = subAwardCode;
	}

	/**.
	 * This is the Getter Method for sequenceNumber
	 * @return Returns the sequenceNumber.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**.
	 * This is the Setter Method for sequenceNumber
	 * @param sequenceNumber The sequenceNumber to set.
	 */
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	/**.
	 * This is the Getter Method for OrganizationId
	 * @return Returns the OrganizationId.
	 */
	public String getOrganizationId() {
        OrganizationService organizationService = KcServiceLocator.
       getService(OrganizationService.class);
        this.organization = organizationService.getOrganization(organizationId);
        return organizationId;
    }
	/**.
	 * This is the Setter Method for organizationId
	 * @param organizationId The organizationId to set.
	 */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

   
    /**.
	 * This is the Getter Method for startDate
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**.
	 * This is the Setter Method for startDate
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**.
	 * This is the Getter Method for endDate
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**.
	 * This is the Setter Method for endDate
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**.
	 * This is the Getter Method for subAwardTypeCode
	 * @return Returns the subAwardTypeCode.
	 */
	public Integer getSubAwardTypeCode() {
		return subAwardTypeCode;
	}

	/**.
	 * This is the Setter Method for subAwardTypeCode
	 * @param subAwardTypeCode The subAwardTypeCode to set.
	 */
	public void setSubAwardTypeCode(Integer subAwardTypeCode) {
		this.subAwardTypeCode = subAwardTypeCode;
	}

	

    /**.
	 * This is the Getter Method for purchaseOrderNum
	 * @return Returns the purchaseOrderNum.
	 */
	public String getPurchaseOrderNum() {
		return purchaseOrderNum;
	}

	/**.
	 * This is the Setter Method for purchaseOrderNum
	 * @param purchaseOrderNum The purchaseOrderNum to set.
	 */
	public void setPurchaseOrderNum(String purchaseOrderNum) {
		this.purchaseOrderNum = purchaseOrderNum;
	}

	/**.
	 * This is the Getter Method for title
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**.
	 * This is the Setter Method for title
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**.
	 * This is the Getter Method for statusCode
	 * @return Returns the statusCode.
	 */
	public Integer getStatusCode() {
		return statusCode;
	}

	/**.
	 * This is the Setter Method for statusCode
	 * @param statusCode The statusCode to set.
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	/**.
	 * This is the Getter Method for accountNumber
	 * @return Returns the accountNumber.
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**.
	 * This is the Setter Method for accountNumber
	 * @param accountNumber The accountNumber to set.
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**.
	 * This is the Getter Method for vendorNumber
	 * @return Returns the vendorNumber.
	 */
	public String getVendorNumber() {
		return vendorNumber;
	}

	/**.
	 * This is the Setter Method for vendorNumber
	 * @param vendorNumber The vendorNumber to set.
	 */
	public void setVendorNumber(String vendorNumber) {
		this.vendorNumber = vendorNumber;
	}

	/**.
	 * This is the Getter Method for requisitionerId
	 * @return Returns the requisitionerId.
	 */
	public String getRequisitionerId() {
		return requisitionerId;
	}

	/**.
	 * This is the Setter Method for requisitionerId
	 * @param requisitionerId The requisitionerId to set.
	 */
	public void setRequisitionerId(String requisitionerId) {
		this.requisitionerId = requisitionerId;
	}
	/**.
	 * This is the Getter Method for RequisitionerUnit
	 * @return Returns the requisitionerUnit.
	 */
	public String getRequisitionerUnit() {
        if (this.requisitionerUnit != null) {
            UnitService unitService = KcServiceLocator.
            getService(UnitService.class);
            this.unit = unitService.getUnit(requisitionerUnit);
        }
        return requisitionerUnit;
    }
	/**.
	 * This is the Setter Method for requisitionerUnit
	 * @param requisitionerUnit The requisitionerUnit to set.
	 */

    public void setRequisitionerUnit(String requisitionerUnit) {
        this.requisitionerUnit = requisitionerUnit;
    }

    /**.
	 * This is the Getter Method for archiveLocation
	 * @return Returns the archiveLocation.
	 */
	public String getArchiveLocation() {
		return archiveLocation;
	}

	/**.
	 * This is the Setter Method for archiveLocation
	 * @param archiveLocation The archiveLocation to set.
	 */
	public void setArchiveLocation(String archiveLocation) {
		this.archiveLocation = archiveLocation;
	}

	/**.
	 * This is the Getter Method for closeoutDate
	 * @return Returns the closeoutDate.
	 */
	public Date getCloseoutDate() {
		return closeoutDate;
	}

	/**.
	 * This is the Setter Method for closeoutDate
	 * @param closeoutDate The closeoutDate to set.
	 */
	public void setCloseoutDate(Date closeoutDate) {
		this.closeoutDate = closeoutDate;
	}

	/**.
	 * This is the Getter Method for closeoutIndicator
	 * @return Returns the closeoutIndicator.
	 */
	public String getCloseoutIndicator() {
		return closeoutIndicator;
	}

	/**.
	 * This is the Setter Method for closeoutIndicator
	 * @param closeoutIndicator The closeoutIndicator to set.
	 */
	public void setCloseoutIndicator(String closeoutIndicator) {
		this.closeoutIndicator = closeoutIndicator;
	}

	/**.
	 * This is the Getter Method for fundingSourceIndicator
	 * @return Returns the fundingSourceIndicator.
	 */
	public String getFundingSourceIndicator() {
		return fundingSourceIndicator;
	}

	/**.
	 * This is the Setter Method for fundingSourceIndicator
	 * @param fundingSourceIndicator The fundingSourceIndicator to set.
	 */
	public void setFundingSourceIndicator(String fundingSourceIndicator) {
		this.fundingSourceIndicator = fundingSourceIndicator;
	}

	/**.
	 * This is the Getter Method for comments
	 * @return Returns the comments.
	 */
	public String getComments() {
		return comments;
	}

	/**.
	 * This is the Setter Method for comments
	 * @param comments The comments to set.
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**.
	 * This is the Getter Method for siteinvestigator
	 * @return Returns the siteInvestigator.
	 */
	public Integer getSiteInvestigator() {
        if (siteInvestigator != null) {
            BusinessObjectService businessObjectService = KcServiceLocator.
            getService(BusinessObjectService.class);
            this.rolodex = (Rolodex) businessObjectService.
            findByPrimaryKey(Rolodex.class,
            getIdentifierMap(ROLODEX_ID_FIELD_NAME, siteInvestigator));
            this.siteInvestigatorId = rolodex.getRolodexId().toString();
        } else {
            this.rolodex = null;
        }
        return siteInvestigator;
    }
	/**.
	 * This is the Setter Method for siteInvestigator
	 * @param siteInvestigator The siteInvestigator to set.
	 */
    public void setSiteInvestigator(Integer siteInvestigator) {
        if (siteInvestigator != null) {
            BusinessObjectService businessObjectService = KcServiceLocator.
            getService(BusinessObjectService.class);
            this.rolodex = (NonOrganizationalRolodex) businessObjectService.
            findByPrimaryKey(NonOrganizationalRolodex.class,
            getIdentifierMap(ROLODEX_ID_FIELD_NAME, siteInvestigator));
            this.siteInvestigatorId = rolodex.getRolodexId().toString();
        }
        this.siteInvestigator = siteInvestigator;
    }

    /**.
	 * This is the Getter Method for subAwardFundingSource
	 * @return Returns the subAwardFundingSource.
	 */
	public SubAwardFundingSource getSubAwardFundingSource() {
		return subAwardFundingSource;
	}

	/**.
	 * This is the Setter Method for subAwardFundingSource
	 * @param subAwardFundingSource The subAwardFundingSource to set.
	 */
	public void setSubAwardFundingSource(SubAwardFundingSource subAwardFundingSource) {
		this.subAwardFundingSource = subAwardFundingSource;
	}

	/**.
	 * This is the Getter Method for subAwardContact
	 * @return Returns the subAwardContact.
	 */
	public SubAwardContact getSubAwardContact() {
		return subAwardContact;
	}

	/**.
	 * This is the Setter Method for subAwardContact
	 * @param subAwardContact The subAwardContact to set.
	 */
	public void setSubAwardContact(SubAwardContact subAwardContact) {
		this.subAwardContact = subAwardContact;
	}

	/**.
	 * This is the Getter Method for subAwardCloseout
	 * @return Returns the subAwardCloseout.
	 */
	public SubAwardCloseout getSubAwardCloseout() {
		return subAwardCloseout;
	}

	/**.
	 * This is the Setter Method for subAwardCloseout
	 * @param subAwardCloseout The subAwardCloseout to set.
	 */
	public void setSubAwardCloseout(SubAwardCloseout subAwardCloseout) {
		this.subAwardCloseout = subAwardCloseout;
	}

    /**.
	 * This is the Getter Method for subAwardFundingSourceList
	 * @return Returns the subAwardFundingSourceList.
	 */
	public List<SubAwardFundingSource> getSubAwardFundingSourceList() {
		return subAwardFundingSourceList;
	}

	/**.
	 * This is the Setter Method for subAwardFundingSourceList
	 * @param subAwardFundingSourceList
	 *  The subAwardFundingSourceList to set.
	 */
	public void setSubAwardFundingSourceList(
			List<SubAwardFundingSource> subAwardFundingSourceList) {
		this.subAwardFundingSourceList = subAwardFundingSourceList;
	}

    /**.
	 * This is the Getter Method for subAwardAmountInfoList
	 * @return Returns the subAwardAmountInfoList.
	 */
	public List<SubAwardAmountInfo> getSubAwardAmountInfoList() {
		return subAwardAmountInfoList;
	}

	/**.
	 * This is the Setter Method for subAwardAmountInfoList
	 * @param subAwardAmountInfoList The subAwardAmountInfoList to set.
	 */
	public void setSubAwardAmountInfoList(
			List<SubAwardAmountInfo> subAwardAmountInfoList) {
		this.subAwardAmountInfoList = subAwardAmountInfoList;
	}

	/**.
	 * This is the Getter Method for subAwardAmountReleasedList
	 * @return Returns the subAwardAmountReleasedList.
	 */
	public List<SubAwardAmountReleased> getSubAwardAmountReleasedList() {
	    Map<String, Object> values = new HashMap<String, Object>();
	    values.put("subAwardCode", this.getSubAwardCode());
		return (List<SubAwardAmountReleased>) 
		    KcServiceLocator.getService(BusinessObjectService.class).findMatchingOrderBy(SubAwardAmountReleased.class, values, "createdDate", false);
	}

	/**.
	 * This is the Getter Method for subAwardContactsList
	 * @return Returns the subAwardContactsList.
	 */
	public List<SubAwardContact> getSubAwardContactsList() {
		return subAwardContactsList;
	}

	/**.
	 * This is the Setter Method for subAwardContactsList
	 * @param subAwardContactsList The subAwardContactsList to set.
	 */
	public void setSubAwardContactsList(List<SubAwardContact> subAwardContactsList) {
		this.subAwardContactsList = subAwardContactsList;
	}

	/**.
	 * This is the Getter Method for subAwardCloseoutList
	 * @return Returns the subAwardCloseoutList.
	 */
	public List<SubAwardCloseout> getSubAwardCloseoutList() {
		return subAwardCloseoutList;
	}

	/**.
	 * This is the Setter Method for subAwardCloseoutList
	 * @param subAwardCloseoutList The subAwardCloseoutList to set.
	 */
	public void setSubAwardCloseoutList(List<SubAwardCloseout> subAwardCloseoutList) {
		this.subAwardCloseoutList = subAwardCloseoutList;
	}

	/**.
	 * This is the Getter Method for subAwardCustomDataList
	 * @return Returns the subAwardCustomDataList.
	 */
	public List<SubAwardCustomData> getSubAwardCustomDataList() {
		return subAwardCustomDataList;
	}

	/**.
	 * This is the Setter Method for subAwardCustomDataList
	 * @param subAwardCustomDataList The subAwardCustomDataList to set.
	 */
	public void setSubAwardCustomDataList(
			List<SubAwardCustomData> subAwardCustomDataList) {
		this.subAwardCustomDataList = subAwardCustomDataList;
	}
	/**.
	 * This is the Getter Method for getStatusDescription
	 * @return Returns the statusDescription.
	 */
	public String getStatusDescription() {
        SubAwardStatus status = getSubAwardStatus();
        statusDescription = status != null ? status.getDescription() : null;
        return statusDescription;
    }
	/**.
	 * This is the Getter Method for initializeCollections
	 */
    protected void initializeCollections() {
        subAwardFundingSourceList = new AutoPopulatingList<SubAwardFundingSource>(SubAwardFundingSource.class);
        subAwardAmountInfoList = new AutoPopulatingList<SubAwardAmountInfo>(SubAwardAmountInfo.class);
        allSubAwardAmountInfos = new ArrayList<>();
        subAwardContactsList = new AutoPopulatingList<SubAwardContact>(SubAwardContact.class);
        subAwardCloseoutList = new AutoPopulatingList<SubAwardCloseout>(SubAwardCloseout.class);
        subAwardCustomDataList = new AutoPopulatingList<SubAwardCustomData>(SubAwardCustomData.class);
        subAwardReportList = new AutoPopulatingList<SubAwardReports>(SubAwardReports.class);
    }
    /**.
	 * This is the Setter Method for subAwardDocument
	 * @param subAwardDocument
	 *  The subAwardDocument to set.
	 */
    public void setSubAwardDocument(SubAwardDocument subAwardDocument) {
        this.subAwardDocument = subAwardDocument;
    }
    /**.
	 * This is the Getter Method for getSubAwardDocument
	 * @return Returns the subAwardDocument.
	 */
    public SubAwardDocument getSubAwardDocument() {
        if (subAwardDocument == null) {
            this.refreshReferenceObject("subAwardDocument");
        }
        return subAwardDocument;
    }
    /*.
	 * This is the Setter Method for subAwardStatus
	 * @param subAwardStatus
	 *  The subAwardStatus to set.
	 */
    public void setSubAwardStatus(SubAwardStatus subAwardStatus) {
        this.subAwardStatus = subAwardStatus;
    }
    /**.
	 * This is the Getter Method for getSubAwardStatus
	 * @return Returns the subAwardDocument.
	 */
    public SubAwardStatus getSubAwardStatus() {
        if (subAwardStatus == null && statusCode != null) {
            refreshReferenceObject("subAwardStatus");
        }
        return subAwardStatus;
    }
    /*.
	 * This is the Setter Method for subAwardType
	 * @param subAwardType
	 *  The subAwardType to set.
	 */
    public void setSubAwardType(AwardType subAwardType) {
        this.subAwardType = subAwardType;
    }
    /**.
	 * This is the Getter Method for subAwardType
	 * @return Returns the subAwardType.
	 */
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
        if(getSubAwardDocument()!=null)
            qualifiedRoleAttributes.put("documentNumber", getSubAwardDocument().getDocumentNumber());

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



    /**.
     * sets newVersion to specified value
     * @param newVersion the newVersion to be set
     */
    public void setNewVersion (boolean newVersion){
        this.newVersion = newVersion;

    }

    /**.
     * Gets the newVersion attribute
     * @return Returns the newVersion attribute
     */
    public boolean getNewVersion( ) {
        return this.newVersion;
    }
    

    /**.
	 * This is the Getter Method for totalObligatedAmount
	 * @return Returns the totalObligatedAmount.
	 */
	public ScaleTwoDecimal getTotalObligatedAmount() {
		return totalObligatedAmount;
	}

	/**.
	 * This is the Getter Method for lastUpdate
	 * @return Returns the lastUpdate.
	 */
	public String getLastUpdate() {
		return lastUpdate;
	}

	/**.
	 * This is the Setter Method for lastUpdate
	 * @param lastUpdate The lastUpdate to set.
	 */
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	/**.
	 * This is the Setter Method for totalObligatedAmount
	 * @param totalObligatedAmount The totalObligatedAmount to set.
	 */
	public void setTotalObligatedAmount(ScaleTwoDecimal totalObligatedAmount) {
		this.totalObligatedAmount = totalObligatedAmount;
	}

	/**.
	 * This is the Getter Method for totalAnticipatedAmount
	 * @return Returns the totalAnticipatedAmount.
	 */
	public ScaleTwoDecimal getTotalAnticipatedAmount() {
		return totalAnticipatedAmount;
	}

	/**.
	 * This is the Setter Method for totalAnticipatedAmount
	 * @param totalAnticipatedAmount The totalAnticipatedAmount to set.
	 */
	public void setTotalAnticipatedAmount(ScaleTwoDecimal totalAnticipatedAmount) {
		this.totalAnticipatedAmount = totalAnticipatedAmount;
	}

	/**.
	 * This is the Getter Method for totalAmountReleased
	 * @return Returns the totalAmountReleased.
	 */
	public ScaleTwoDecimal getTotalAmountReleased() {
		return totalAmountReleased;
	}

	/**.
	 * This is the Setter Method for totalAmountReleased
	 * @param totalAmountReleased The totalAmountReleased to set.
	 */
	public void setTotalAmountReleased(ScaleTwoDecimal totalAmountReleased) {
		this.totalAmountReleased = totalAmountReleased;
	}

	/**.
	 * This is the Getter Method for totalAvailableAmount
	 * @return Returns the totalAvailableAmount.
	 */
	public ScaleTwoDecimal getTotalAvailableAmount() {
		return totalAvailableAmount;
	}

	/**.
	 * This is the Setter Method for totalAvailableAmount
	 * @param totalAvailableAmount The totalAvailableAmount to set.
	 */
	public void setTotalAvailableAmount(ScaleTwoDecimal totalAvailableAmount) {
		this.totalAvailableAmount = totalAvailableAmount;
	}

	/**.
	 * This is the Getter Method for docIdStatus
	 * @return Returns the docIdStatus.
	 */
	public String getDocIdStatus() {
		return docIdStatus;
	}

	/**.
	 * This is the Setter Method for docIdStatus
	 * @param docIdStatus The docIdStatus to set.
	 */
	public void setDocIdStatus(String docIdStatus) {
		this.docIdStatus = docIdStatus;
	}
	/**.
	 * This is the Getter Method for SiteInvestigatorId
	 * @return Returns the siteInvestigatorId.
	 */
	public String getSiteInvestigatorId() {
        if (this.siteInvestigatorId == null && this.siteInvestigator != null) {
            siteInvestigatorId = this.siteInvestigator.toString();
        }
        return siteInvestigatorId;
    }
	/**.
	 * This is the Setter Method for SiteInvestigatorId
	 * @param siteInvestigatorId The siteInvestigatorId to set.
	 */
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
        List<NegotiationPersonDTO> people = new
        ArrayList<NegotiationPersonDTO>();
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

    /**.
	 * This is the Getter Method for awardNumber
	 * @return Returns the awardNumber.
	 */
	public String getAwardNumber() {
		return awardNumber;
	}

	/**.
	 * This is the Setter Method for awardNumber
	 * @param awardNumber The awardNumber to set.
	 */
	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	/**.
	 * This is the Getter Method for editSubAward
	 * @return Returns the editSubAward.
	 */
	public boolean isEditSubAward() {
		return editSubAward;
	}

	/**.
	 * This is the Setter Method for editSubAward
	 * @param editSubAward The editSubAward to set.
	 */
	public void setEditSubAward(boolean editSubAward) {
		this.editSubAward = editSubAward;
	}

	/**.
     * Build an identifier map for the BOS lookup
     * @param identifierField
     * @param identifierValue
     * @return
     */
    protected Map<String, Object> getIdentifierMap(String identifierField, Object identifierValue) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(identifierField, identifierValue);
        return map;
    }

    public boolean isDefaultOpen() {
        return defaultOpen;
    }

    public void setDefaultOpen(boolean defaultOpen) {
        this.defaultOpen = defaultOpen;
    }

    /**
     * Gets the costType attribute. 
     * @return Returns the costType.
     */
    public Integer getCostType() {
        return costType;
    }

    /**
     * Sets the costType attribute value.
     * @param costType The costType to set.
     */
    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    /**
     * Gets the executionDate attribute. 
     * @return Returns the executionDate.
     */
    public Date getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the executionDate attribute value.
     * @param executionDate The executionDate to set.
     */
    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    /**
     * Gets the requisitionId attribute. 
     * @return Returns the requisitionId.
     */
    public String getRequisitionId() {
        return requisitionId;
    }

    /**
     * Sets the requisitionId attribute value.
     * @param requisitionId The requisitionId to set.
     */
    public void setRequisitionId(String requisitionId) {
        this.requisitionId = requisitionId;
    }

    /**
     * Gets the subAwardCostType attribute. 
     * @return Returns the subAwardCostType.
     */
    public SubAwardCostType getSubAwardCostType() {
        return subAwardCostType;
    }

    /**
     * Sets the subAwardCostType attribute value.
     * @param subAwardCostType The subAwardCostType to set.
     */
    public void setSubAwardCostType(SubAwardCostType subAwardCostType) {
        this.subAwardCostType = subAwardCostType;
    }
    
    /**.
     * This is the Getter Method for modificationEffectiveDate
     * @return Returns the modificationEffectiveDate.
     */
    public Date getModificationEffectiveDate() {
        return modificationEffectiveDate;
    }

    /**.
     * This is the Setter Method for modificationEffectiveDate
     * @param modificationEffectiveDate The modificationEffectiveDate to set.
     */
    public void setModificationEffectiveDate(Date modificationEffectiveDate) {
        this.modificationEffectiveDate = modificationEffectiveDate;
    }
    
    /**.
     * This is the Getter Method for modificationId
     * @return Returns the modificationId.
     */
    public String getModificationId() {
        return modificationId;
    }

    /**.
     * This is the Setter Method for modificationId
     * @param modificationId The modificationId to set.
     */
    public void setModificationId(String modificationId) {
        this.modificationId = modificationId;
    }

    /**.
     * This is the Getter Method for performanceStartDate
     * @return Returns the performanceStartDate.
     */
    public Date getPerformanceStartDate() {
        return performanceStartDate;
    }

    /**.
     * This is the Setter Method for performanceStartDate
     * @param performanceStartDate The performanceStartDate to set.
     */
    public void setPerformanceStartDate(Date performanceStartDate) {
        this.performanceStartDate = performanceStartDate;
    }

    /**.
     * This is the Getter Method for performanceEnddate
     * @return Returns the performanceEnddate.
     */
    public Date getPerformanceEnddate() {
        return performanceEnddate;
    }

    /**.
     * This is the Setter Method for performanceEnddate
     * @param performanceEnddate The performanceEnddate to set.
     */
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
}
