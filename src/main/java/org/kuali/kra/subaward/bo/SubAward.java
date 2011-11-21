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
package org.kuali.kra.subaward.bo;


import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.kra.subaward.bo.SubAwardCloseout;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kns.util.TypedArrayList;
import org.kuali.kra.subaward.customdata.SubAwardCustomData;

public class SubAward extends KraPersistableBusinessObjectBase implements Permissionable{ 
    
    private static final long serialVersionUID = 1L;

    private Integer subAwardId; 
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
    private SubAwardAmountReleased subAwardAmountReleased; 
    private SubAwardAmountInfo subAwardAmountInfo; 
    private String organizationName;
    private String requisitionerName;
    private String requisitionerUnitName;
    private String siteInvestigatorName;
    private Organization organization;
    private Unit unit;
    private Rolodex rolodex;
    private SubAwardStatus subAwardStatus;
    private AwardType subAwardType;
    private KcPerson kcPerson;
    
    public KcPerson getKcPerson() {
        return kcPerson;
    }

    public void setKcPerson(KcPerson kcPerson) {
        this.kcPerson = kcPerson;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
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
        Organization organization=getOrganization();
        if(organization!=null){
            return organization.getOrganizationName();
        }else{
            return organizationName;
        }
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getRequisitionerName() {
        if(requisitionerId!=null){
            return requisitionerName = KraServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(requisitionerId).getFullName();
        }else{
            return this.requisitionerName;
        }
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

    public String getSiteInvestigatorName() {
        return siteInvestigatorName;
    }

    public void setSiteInvestigatorName(String siteInvestigatorName) {
        this.siteInvestigatorName = siteInvestigatorName;
    }

    private List<SubAwardFundingSource> subAwardFundingSourceList;
    private List<SubAwardAmountInfo> subAwardAmountInfoList;  
    private List<SubAwardAmountReleased> subAwardAmountReleasedList;
    private List<SubAwardContact> subAwardContactsList; 
    private List<SubAwardCloseout> subAwardCloseoutList;
    private List<SubAwardCustomData> subAwardCustomDataList;

    public SubAward() { 
        super();
        
        initializeCollections();

    } 
    
    public Integer getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Integer subAwardId) {
        this.subAwardId = subAwardId;
    }

    public String getSubAwardCode() {
        return subAwardCode;
    }

    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }


    public String getOrganizationId() {
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
        return siteInvestigator;
    }

    public void setSiteInvestigator(Integer siteInvestigator) {
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

    public SubAwardAmountReleased getSubAwardAmountReleased() {
        return subAwardAmountReleased;
    }

    public void setSubAwardAmtReleased(SubAwardAmountReleased subAwardAmountReleased) {
        this.subAwardAmountReleased = subAwardAmountReleased;
    }

    public SubAwardAmountInfo getSubAwardAmountInfo() {
        return subAwardAmountInfo;
    }

    public void setSubAwardAmountInfo(SubAwardAmountInfo subAwardAmountInfo) {
        this.subAwardAmountInfo = subAwardAmountInfo;
    }
    
    public List<SubAwardFundingSource> getSubAwardFundingSourceList() {
        return subAwardFundingSourceList;
    }

    public void setSubAwardFundingSourceList(List<SubAwardFundingSource> subAwardFundingSourceList) {
        this.subAwardFundingSourceList = subAwardFundingSourceList;
    }
    
    public List<SubAwardAmountInfo> getSubAwardAmountInfoList() {
        return subAwardAmountInfoList;
    }

    public void setSubAwardAmountInfoList(List<SubAwardAmountInfo> subAwardAmountInfoList) {
        this.subAwardAmountInfoList = subAwardAmountInfoList;
    }
    
    public List<SubAwardAmountReleased> getSubAwardAmountReleasedList() {
        return subAwardAmountReleasedList;
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

    public void setSubAwardCustomDataList(List<SubAwardCustomData> subAwardCustomDataList) {
        this.subAwardCustomDataList = subAwardCustomDataList;
    }
    

    public String getStatusDescription() {
        SubAwardStatus status = getSubAwardStatus();
        statusDescription = status != null ? status.getDescription() : null;
        return statusDescription;
    }
    
    /** {@inheritDoc} */
    @Override 
    protected LinkedHashMap<String, Object> toStringMapper() {
        LinkedHashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("subAwardId", this.getSubAwardId());
        hashMap.put("subAwardCode", this.getSubAwardCode());
        hashMap.put("sequenceNumber", this.getSequenceNumber());
        hashMap.put("organizationId", this.getOrganizationId());
        hashMap.put("startDate", this.getStartDate());
        hashMap.put("endDate", this.getEndDate());
        hashMap.put("subAwardTypeCode", this.getSubAwardTypeCode());
        hashMap.put("purchaseOrderNum", this.getPurchaseOrderNum());
        hashMap.put("title", this.getTitle());
        hashMap.put("statusCode", this.getStatusCode());
        hashMap.put("accountNumber", this.getAccountNumber());
        hashMap.put("vendorNumber", this.getVendorNumber());
        hashMap.put("requisitionerId", this.getRequisitionerId());
        hashMap.put("requisitionerUnit", this.getRequisitionerUnit());
        hashMap.put("archiveLocation", this.getArchiveLocation());
        hashMap.put("closeoutDate", this.getCloseoutDate());
        hashMap.put("closeoutIndicator", this.getCloseoutIndicator());
        hashMap.put("fundingSourceIndicator", this.getFundingSourceIndicator());
        hashMap.put("comments", this.getComments());
        hashMap.put("siteInvestigator", this.getSiteInvestigator());
        return hashMap;
    }
    

    protected void initializeCollections() {
        subAwardFundingSourceList = new TypedArrayList(SubAwardFundingSource.class);
        subAwardAmountInfoList = new TypedArrayList(SubAwardAmountInfo.class);
        subAwardAmountReleasedList = new TypedArrayList(SubAwardAmountReleased.class);
        subAwardContactsList = new TypedArrayList(SubAwardContact.class);
        subAwardCloseoutList = new TypedArrayList(SubAwardCloseout.class); 
        subAwardCustomDataList = new TypedArrayList(SubAwardCustomData.class);
    }
    public void setSubAwardDocument(SubAwardDocument subAwardDocument) {
        this.subAwardDocument = subAwardDocument;
    }

    public SubAwardDocument getSubAwardDocument() {
        if(subAwardDocument==null){
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
        if(subAwardId!=null)
            return subAwardId.toString();
        else
            return null;
    }

    @Override
    public String getDocumentKey() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getRoleNames() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_SUBAWARD;
    }

    @Override
    public String getLeadUnitNumber() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDocumentRoleTypeCode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void populateAdditionalQualifiedRoleAttributes(Map<String, String> qualifiedRoleAttributes) {
        if(getSubAwardDocument()!=null)
            qualifiedRoleAttributes.put("documentNumber", getSubAwardDocument().getDocumentNumber());
        
    }


  
    
    
}