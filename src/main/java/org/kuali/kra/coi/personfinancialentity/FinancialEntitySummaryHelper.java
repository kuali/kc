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
package org.kuali.kra.coi.personfinancialentity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;


public class FinancialEntitySummaryHelper implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6990736543009439730L;
   
    private int currentVersionNumber;
    private FinancialEntityForm financialEntityForm;
    private PersonFinIntDisclosure currentFinancialEntity;
    private PersonFinIntDisclosure previousFinancialEntity;
    private List<PersonFinIntDisclosure> versions;
    
    private static final String newLine = "<BR>";
    private static final String[] relationshipType = {"Self", "Spouse", "Children", "Student", "Other"};
    private static final String[] percentages = {"5-25%", "26-50%", "51-75%", "76-100%"};
    private static final String[] remuneration = {"10-50K", "51-100K", "101-200K", "Over 200K"};
    private static final String plusString = "+";
    

    public FinancialEntitySummaryHelper(FinancialEntityForm financialEntityForm) {
        this.financialEntityForm = financialEntityForm;
    }

    public FinancialEntityForm getFinancialEntityForm() {
        return financialEntityForm;
    }

    public void setFinancialEntityForm(FinancialEntityForm financialEntityForm) {
        this.financialEntityForm = financialEntityForm;
    }

    public PersonFinIntDisclosure getCurrentFinancialEntity() {
        return currentFinancialEntity;
    }

    public void setCurrentFinancialEntity(PersonFinIntDisclosure currentFinancialEntity) {
        this.currentFinancialEntity = currentFinancialEntity;
    }

    public PersonFinIntDisclosure getPreviousFinancialEntity() {
        return previousFinancialEntity;
    }

    public void setPreviousFinancialEntity(PersonFinIntDisclosure previousFinancialEntity) {
        this.previousFinancialEntity = previousFinancialEntity;
    }
    
    /**
     * This method...
     * @param currentVersionNumber
     * @param entityNumber
     * @param status
     */
    public void setSummaryDetails(int currentVersionNumber, String entityNumber, String status) {
        this.currentVersionNumber = currentVersionNumber;
        currentFinancialEntity = new PersonFinIntDisclosure();
        previousFinancialEntity = new PersonFinIntDisclosure();
        if (StringUtils.equalsIgnoreCase(status, Constants.FINANCIAL_ENTITY_STATUS_INACTIVE)) {
            List<PersonFinIntDisclosure> inactiveDisclosures = financialEntityForm.getFinancialEntityHelper().getInactiveFinancialEntities();
            for (PersonFinIntDisclosure financialEntity : inactiveDisclosures) {
                if (StringUtils.equalsIgnoreCase(financialEntity.getEntityNumber(), entityNumber)) {
                    List<PersonFinIntDisclosure> currentVersions = financialEntity.getVersions();
                    currentFinancialEntity = currentVersions.get(currentVersions.size() - currentVersionNumber);
                    setVersions(currentVersions);
                    if (currentVersionNumber > 1) {                     
                        previousFinancialEntity = currentVersions.get(currentVersions.size() - currentVersionNumber + 1);
                    }
                }
            }
            
        } else {
            List<PersonFinIntDisclosure> activeDisclosures = financialEntityForm.getFinancialEntityHelper().getActiveFinancialEntities();
            for (PersonFinIntDisclosure financialEntity : activeDisclosures) {
                if (StringUtils.equalsIgnoreCase(financialEntity.getEntityNumber(), entityNumber)) { 
                    List<PersonFinIntDisclosure> currentVersions = financialEntity.getVersions();
                    currentFinancialEntity = currentVersions.get(currentVersions.size() - currentVersionNumber);
                    setVersions(currentVersions);
                    if (currentVersionNumber > 1) {                     
                        previousFinancialEntity = currentVersions.get(currentVersions.size() - currentVersionNumber + 1);
                    }
                }
            }
        }
        setSummaryBeans(currentVersionNumber, currentFinancialEntity, previousFinancialEntity);
    }

    /**
     * This method...
     * @param currentVersionNumber
     * @param currentFinancialEntity
     * @param previousFinancialEntity
     */
    protected void setSummaryBeans(int currentVersionNumber, PersonFinIntDisclosure currentFinancialEntity, PersonFinIntDisclosure previousFinancialEntity) {
        setCurrentVersionNumber(currentVersionNumber);
        FinancialEntitySummaryBean currentSummary = financialEntityForm.getCurrentSummary();
        FinancialEntitySummaryBean previousSummary = financialEntityForm.getPreviousSummary();
        setSummaryDetails(currentFinancialEntity, currentSummary);       
        setSummaryDetails(previousFinancialEntity, previousSummary); 
        //financialEntityForm.setCurrentSummary(currentSummary);
        //financialEntityForm.setPreviousSummary(previousSummary);
        
        Map<String, String> currentRelationshipDetails = getRelationshipDetails(currentFinancialEntity);
        if (currentVersionNumber > 1) {                     
            Map<String, String> previousRelationshipDetails = getRelationshipDetails(previousFinancialEntity);
            formatDetails(currentRelationshipDetails, previousRelationshipDetails);
        }
        
    }

    /**
     * This method...
     * @param currentRelationshipDetails
     * @param previousRelationshipDetails
     */
    protected void formatDetails(Map<String, String> currentRelationshipDetails, Map<String, String> previousRelationshipDetails) {
        FinancialEntitySummaryBean currentSummary = financialEntityForm.getCurrentSummary();
        FinancialEntitySummaryBean previousSummary = financialEntityForm.getPreviousSummary();
        for (String group : currentRelationshipDetails.keySet()) {
            if (previousRelationshipDetails.containsKey(group)) {
                if (!StringUtils.equalsIgnoreCase(previousRelationshipDetails.get(group), currentRelationshipDetails.get(group))) {
                    currentRelationshipDetails.put(group, addSpan(currentRelationshipDetails.get(group)));
                }
            } else {
                currentRelationshipDetails.put(group, addSpan(currentRelationshipDetails.get(group)));
            }
        } 
        Map<String, String> dataGroups = getDataGroups();
        currentSummary.setRelationshipDetails(cleanupDetails(currentRelationshipDetails, dataGroups));
        previousSummary.setRelationshipDetails(cleanupDetails(previousRelationshipDetails, dataGroups));
        
        if (!StringUtils.equalsIgnoreCase(currentSummary.getAddress(), previousSummary.getAddress())) {
            currentSummary.setAddress(addSpan(currentSummary.getAddress()));
        }
        if (!StringUtils.equalsIgnoreCase(currentSummary.getStatusCode(), previousSummary.getStatusCode())) {
            currentSummary.setStatusCode(addSpan(currentSummary.getStatusCode()));
        }
        if (!StringUtils.equalsIgnoreCase(currentSummary.getDetails(), previousSummary.getDetails())) {
            currentSummary.setDetails(addSpan(currentSummary.getDetails()));
        }
        if (ObjectUtils.isNotNull(currentSummary.getSponsorCode())) {
            if (ObjectUtils.isNotNull(previousSummary.getSponsorCode())) {
                if (!StringUtils.equalsIgnoreCase(currentSummary.getSponsorCode().toString(), previousSummary.getSponsorCode().toString())) {
                    currentSummary.setDetails(addSpan(currentSummary.getSponsorCode().toString()));
                } 
            } else {
                currentSummary.setDetails(addSpan(currentSummary.getSponsorCode().toString()));
            }
        }
      
    }
    
    /**
     * This method...
     * @param relationshipDetails
     * @param dataGroups 
     * @return
     */
    protected Map<String, String> cleanupDetails(Map<String, String> relationshipDetails, Map<String, String> dataGroups) {
        Map<String, String> formattedRelationshipDetails = new HashMap<String, String>();
        for (String group : relationshipDetails.keySet()) {
            String heading = group.substring(0, group.indexOf(plusString));
            String subHeading = group.substring(group.indexOf(plusString) + 1);
            formattedRelationshipDetails.put(heading, subHeading + newLine + relationshipDetails.get(group));
        }
              
        for (String group : dataGroups.keySet()) {
            if (!formattedRelationshipDetails.containsKey(dataGroups.get(group))) {
                formattedRelationshipDetails.put(dataGroups.get(group), "");
            }
        }
    return formattedRelationshipDetails;    
    }
    
    protected String addSpan(String htmlString) {
        return "<span class=\"changed\">" + htmlString + "</span>";
    }
   
    public List<PersonFinIntDisclosure> getVersions() {
       return versions;
    }
    
    public void setVersions(List<PersonFinIntDisclosure> versions) {
        this.versions = versions;
    }
    
    public int getNumberOfVersions() {
        return getVersions().size(); 
    }
    
    public void setCurrentVersionNumber(int currentVersionNumber) {
        this.currentVersionNumber = currentVersionNumber;
    }
    
    public int getCurrentVersionNumber() {
        return currentVersionNumber;
    }
   
    /**
     * This method...
     * @param financialEntity
     * @param summary
     */
    protected void setSummaryDetails(PersonFinIntDisclosure financialEntity, FinancialEntitySummaryBean summary) {
        String entityAddress = "";
        for (FinancialEntityContactInfo address : financialEntity.getFinEntityContactInfos()) {
            entityAddress += ObjectUtils.isNull(address.getAddressLine1()) ? "" : address.getAddressLine1() + newLine;
            entityAddress += ObjectUtils.isNull(address.getAddressLine2()) ? "" : address.getAddressLine2() + newLine;
            entityAddress += ObjectUtils.isNull(address.getAddressLine3()) ? "" : address.getAddressLine3() + newLine;
            entityAddress += ObjectUtils.isNull(address.getCity()) ? "" : address.getCity() + newLine;
            entityAddress += ObjectUtils.isNull(address.getPostalCode()) ? "" : address.getPostalCode() + newLine;
            entityAddress += ObjectUtils.isNull(address.getCountryCode()) ? "" : address.getCountryCode() + newLine;
        }

        summary.setAddress(entityAddress);
        summary.setOwnershipType(
                StringUtils.equalsIgnoreCase(financialEntity.getEntityOwnershipType(), Constants.ENTITY_OWNERSHIP_TYPE_CODE_PRIVATE) ? "Private" : "Public");
        
        String details = "";
        if (ObjectUtils.isNotNull(financialEntity.getOrgRelationDescription())) {
            details = "Relationship Description: " + newLine + financialEntity.getRelationshipDescription() + newLine;
        }
        if (ObjectUtils.isNotNull(financialEntity.getOrgRelationDescription())) {
            details += "Org Relation Description: " + newLine + financialEntity.getOrgRelationDescription()  + newLine;
        }
        if (ObjectUtils.isNotNull(financialEntity.getPrincipalBusinessActivity())) {
            details += "Entity Principal Business/Activity: " + newLine + financialEntity.getPrincipalBusinessActivity() + newLine;
        }
        summary.setDetails(details);
        
    }
    
    /**
     * This method...
     * @param financialEntity
     * @return
     */
    protected Map<String, String> getRelationshipDetails(PersonFinIntDisclosure financialEntity) {
        List<PersonFinIntDisclDet> details = financialEntity.getPerFinIntDisclDetails();
        
        Map<String, DataMatrix> dataType = getFinancialEntityDataMatrix();
        Map<String, String> oDetails = new HashMap<String, String>();
        Map<String, String> dataGroups = getDataGroups();
        String value = "";
        for (PersonFinIntDisclDet detail : details) {
            int columnValue = Integer.parseInt(detail.getColumnValue());
            DataMatrix dm = dataType.get(detail.getColumnName());
            if (ObjectUtils.isNull(dm.getLookupArgument())) {
                value = relationshipType[Integer.parseInt(detail.getRelationshipTypeCode()) - 1] + ", ";
            } else {
                if (dm.getLookupArgument().equalsIgnoreCase("remuneration_range")) {
                    value = relationshipType[Integer.parseInt(detail.getRelationshipTypeCode()) - 1]  + " : "
                            + remuneration[columnValue - 1] + ", ";
                } else if (dm.getLookupArgument().equalsIgnoreCase("ownership_interest")) {
                    value = relationshipType[Integer.parseInt(detail.getRelationshipTypeCode()) - 1] + " : " 
                            + percentages[columnValue - 1] + ", ";
                } 
            }
            
            String groupName = dataGroups.get(dm.getDataGroupId().toString());
            String hashKey = groupName + plusString + dm.getColumnLabel();
            if (oDetails.containsKey(hashKey)) {
                oDetails.put(hashKey, oDetails.get(hashKey) + value);
            } else {
                oDetails.put(hashKey, value);
            }
            
        }
        return oDetails;
    }
    
    protected Map<String, DataMatrix> getFinancialEntityDataMatrix() {
        Map<String, DataMatrix> dataType = new HashMap<String, DataMatrix>();
        List<FinEntitiesDataMatrix> matrix = (List<FinEntitiesDataMatrix>) getBusinessObjectService().findAll(FinEntitiesDataMatrix.class);
        for (FinEntitiesDataMatrix row : matrix) {
            DataMatrix dataMatrix = new DataMatrix();
            dataMatrix.setColumnLabel(row.getColumnLabel());
            dataMatrix.setDataGroupId(row.getDataGroupId());
            dataMatrix.setLookupArgument(row.getLookupArgument());
            dataType.put(row.getColumnName(), dataMatrix);
        }
        return dataType;
    }
    
    protected Map<String, String> getDataGroups() {
        Map<String, String> dataGroups = new HashMap<String, String>();
        List<FinEntitiesDataGroup> groups = (List<FinEntitiesDataGroup>) getBusinessObjectService().findAll(FinEntitiesDataGroup.class);
        for (FinEntitiesDataGroup row : groups) {
           
            dataGroups.put(row.getDataGroupId().toString(), row.getDataGroupName());
        }
        return dataGroups;
    }
    protected BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }
    
    protected FinancialEntityService getFinancialEntityService() {
        return KraServiceLocator.getService(FinancialEntityService.class);
    }
  
}
