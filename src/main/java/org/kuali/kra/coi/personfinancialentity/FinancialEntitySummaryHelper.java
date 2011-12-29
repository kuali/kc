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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.keyvalue.ArgValueLookupValuesFinder;
import org.kuali.rice.core.util.KeyLabelPair;
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
    private String[] relationshipType; 
    private String[] percentages; 
    private String[] remuneration;
    
    private static final String newLine = "<BR>";
    private static final String remunerationRange = "remuneration_range";
    private static final String ownershipInterests = "ownership_interest";
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
     * This method is called from the action class.
     * @param currentVersionNumber
     * @param entityNumber
     * @param status
     */
    public void setSummaryDetails(int currentVersionNumber, String entityNumber, String status) {
        initRanges();
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
     * This method set the current and previous beans
     * @param currentVersionNumber
     * @param currentFinancialEntity
     * @param previousFinancialEntity
     */
    protected void setSummaryBeans(int currentVersionNumber, PersonFinIntDisclosure currentFinancialEntity, PersonFinIntDisclosure previousFinancialEntity) {
        setCurrentVersionNumber(currentVersionNumber);
        FinancialEntitySummaryBean currentSummary = financialEntityForm.getCurrentSummary();
        FinancialEntitySummaryBean previousSummary = financialEntityForm.getPreviousSummary();
        setSummaryInformation(currentFinancialEntity, currentSummary);       
        setSummaryInformation(previousFinancialEntity, previousSummary); 
        
        Map<String, String> currentRelationshipDetails = getRelationshipDetails(currentFinancialEntity);
        Map<String, String> previousRelationshipDetails = new HashMap<String, String>();
        if (currentVersionNumber > 1) { 
            previousRelationshipDetails = getRelationshipDetails(previousFinancialEntity);
            formatDetails(currentRelationshipDetails, previousRelationshipDetails);
        }
        Map<String, String> dataGroups = getDataGroups();
        currentSummary.setRelationshipDetails(cleanupDetails(currentRelationshipDetails, dataGroups));
        previousSummary.setRelationshipDetails(cleanupDetails(previousRelationshipDetails, dataGroups));
    }

    /**
     * This method adds a span to current details when it is different from the previous
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
        if (!StringUtils.equalsIgnoreCase(currentSummary.getStatusDescription(), previousSummary.getStatusDescription())) {
            currentSummary.setStatusDescription(addSpan(currentSummary.getStatusDescription()));
        }
        if (!StringUtils.equalsIgnoreCase(currentSummary.getDetails(), previousSummary.getDetails())) {
            currentSummary.setDetails(addSpan(currentSummary.getDetails()));
        }
        if (ObjectUtils.isNotNull(currentSummary.getSponsorName())) {
            if (ObjectUtils.isNotNull(previousSummary.getSponsorName())) {
                if (!StringUtils.equalsIgnoreCase(currentSummary.getSponsorName().toString(), previousSummary.getSponsorName().toString())) {
                    currentSummary.setSponsorName(addSpan(currentSummary.getSponsorName().toString()));
                } 
            } else {
                currentSummary.setSponsorName(addSpan(currentSummary.getSponsorName().toString()));
            }
        }
        if (!StringUtils.equalsIgnoreCase(currentSummary.getOwnershipType(), previousSummary.getOwnershipType())) {
            currentSummary.setOwnershipType(addSpan(currentSummary.getOwnershipType()));
        }
      
    }
    
    /**
     * This method adds the correct header and sub headers for the details.
     * @param relationshipDetails
     * @param dataGroups 
     * @return
     */
    protected Map<String, String> cleanupDetails(Map<String, String> relationshipDetails, Map<String, String> dataGroups) {
        Map<String, String> formattedRelationshipDetails = new TreeMap<String, String>();
        for (String group : relationshipDetails.keySet()) {
            String heading = group.substring(0, group.indexOf(plusString));
            String subHeading = group.substring(group.indexOf(plusString) + 1);
            if (formattedRelationshipDetails.containsKey(heading)) {
                formattedRelationshipDetails.put(heading, 
                                                 formattedRelationshipDetails.get(heading) 
                                                 + subHeading + ": " + newLine + relationshipDetails.get(group) + newLine);

            } else {
                formattedRelationshipDetails.put(heading, subHeading + ": " + newLine + relationshipDetails.get(group) + newLine);
            }
        }
        /*
         * Add an empty string if a group is missing or it will not show up 
         * in the summary.
         */
        for (String group : dataGroups.keySet()) {
            if (!formattedRelationshipDetails.containsKey(dataGroups.get(group))) {
                formattedRelationshipDetails.put(dataGroups.get(group), "");
            }
        }
       
        
        return formattedRelationshipDetails;    
    }
    
    /**
     * This method adds a span class to a string
     * @param htmlString
     * @return
     */
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
     * This method sets all the information required for the summary.
     * @param financialEntity
     * @param summary
     */
    protected void setSummaryInformation(PersonFinIntDisclosure financialEntity, FinancialEntitySummaryBean summary) {
        String entityAddress = "";
        String webAddress = "";
        for (FinancialEntityContactInfo address : financialEntity.getFinEntityContactInfos()) {
            entityAddress += ObjectUtils.isNull(address.getAddressLine1()) ? "" : address.getAddressLine1() + newLine;
            entityAddress += ObjectUtils.isNull(address.getAddressLine2()) ? "" : address.getAddressLine2() + newLine;
            entityAddress += ObjectUtils.isNull(address.getAddressLine3()) ? "" : address.getAddressLine3() + newLine;
            entityAddress += ObjectUtils.isNull(address.getCity()) ? "" : address.getCity() + newLine;
            entityAddress += ObjectUtils.isNull(address.getPostalCode()) ? "" : address.getPostalCode() + newLine;
            entityAddress += ObjectUtils.isNull(address.getCountryCode()) ? "" : address.getCountryCode() + newLine;
            webAddress += ObjectUtils.isNull(address.getWebAddress1()) ? "" : address.getWebAddress1() + newLine;
            webAddress += ObjectUtils.isNull(address.getWebAddress2()) ? "" : address.getWebAddress2() + newLine;
        }

        summary.setAddress(entityAddress);
        summary.setWebAddress(webAddress);
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
        summary.setStatusDescription(financialEntity.getFinIntEntityStatus() != null ? financialEntity.getFinIntEntityStatus().getDescription(): "");
        summary.setSponsorName(financialEntity.getSponsor() != null ? financialEntity.getSponsor().getSponsorName() : "");
       
    }
    
    /**
     * This method gets the relationship details for a financial entity.
     * @param financialEntity
     * @return
     */
    protected Map<String, String> getRelationshipDetails(PersonFinIntDisclosure financialEntity) {
        List<PersonFinIntDisclDet> details = financialEntity.getPerFinIntDisclDetails();
        
        Map<String, FinEntitiesDataMatrix> dataType = getFinancialEntityDataMatrix();
        Map<String, String> relationshipDetails = new HashMap<String, String>();
        Map<String, String> dataGroups = getDataGroups();
        String value = "";
        for (PersonFinIntDisclDet detail : details) {
            FinEntitiesDataMatrix dm = dataType.get(detail.getColumnName());
            
            // if column value is null, it is a comment
            if (ObjectUtils.isNotNull(detail.getColumnValue())) {
                int columnValue = Integer.parseInt(detail.getColumnValue());
                // if lookup argument is null, it is a select. Hence get the relationship type
                if (ObjectUtils.isNull(dm.getLookupArgument())) {
                    value = relationshipType[Integer.parseInt(detail.getRelationshipTypeCode()) - 1] + ", ";
                } else {
                    if (dm.getLookupArgument().equalsIgnoreCase(remunerationRange)) {
                        value = relationshipType[Integer.parseInt(detail.getRelationshipTypeCode()) - 1]  + " : "
                        + remuneration[columnValue - 1] + ", ";
                    } else if (dm.getLookupArgument().equalsIgnoreCase(ownershipInterests)) {
                        value = relationshipType[Integer.parseInt(detail.getRelationshipTypeCode()) - 1] + " : " 
                        + percentages[columnValue - 1] + ", ";
                    } 
                }
                
                String groupName = dataGroups.get(dm.getDataGroupId().toString());
                String hashKey = groupName + plusString + dm.getColumnLabel();
                if (relationshipDetails.containsKey(hashKey)) {
                    relationshipDetails.put(hashKey, relationshipDetails.get(hashKey) + value);
                } else {
                    
                    if (ObjectUtils.isNotNull(detail.getComments())) {
                        value =  "Comments: " + detail.getComments() + newLine + value; 
                    } 
                    relationshipDetails.put(hashKey, value);
                    
                } 
                
            }
        }
       
        return relationshipDetails;
    }
    
    protected Map<String, FinEntitiesDataMatrix> getFinancialEntityDataMatrix() {
        Map<String, FinEntitiesDataMatrix> dataType = new HashMap<String, FinEntitiesDataMatrix>();
        List<FinEntitiesDataMatrix> matrix = (List<FinEntitiesDataMatrix>) getBusinessObjectService().findAll(FinEntitiesDataMatrix.class);
        for (FinEntitiesDataMatrix row : matrix) {
           /* DataMatrix dataMatrix = new DataMatrix();
            dataMatrix.setColumnLabel(row.getColumnLabel());
            dataMatrix.setDataGroupId(row.getDataGroupId());
            dataMatrix.setLookupArgument(row.getLookupArgument());
            dataType.put(row.getColumnName(), dataMatrix);*/
            dataType.put(row.getColumnName(), row);
        }
        return dataType;
    }
    
    /*
     * 
     */
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
  
    
    /**
     * This method sets the salary and percentage ranges and the relationship values.
     */
    protected void initRanges() {
        ArgValueLookupValuesFinder finder = new  ArgValueLookupValuesFinder();
        finder.setArgName(remunerationRange);
        List<KeyLabelPair> kv = finder.getKeyValues();
        
        int index = 0;
        String[] temp = new String[kv.size()];
        for (KeyLabelPair pair : kv) {
            if (!pair.getLabel().equalsIgnoreCase("select")) {
                temp[index] = pair.getLabel();
                index++;
            }
        }
        setRemuneration(temp);
        
        finder.setArgName(ownershipInterests);
        kv = finder.getKeyValues();
        index = 0;
        String[] tempPercentage = new String[kv.size()];
        for (KeyLabelPair pair : kv) {
            if (!pair.getLabel().equalsIgnoreCase("select")) {
                tempPercentage[index] = pair.getLabel();
                index++;
            }
        }
        setPercentages(tempPercentage);
        
        List<FinIntEntityRelType> relTypes = (List<FinIntEntityRelType>) getBusinessObjectService().findAll(FinIntEntityRelType.class);
        String[] types = new String[relTypes.size()];
        index = 0;
        for (FinIntEntityRelType type : relTypes) {
            types[index] = type.getDescription();
            index++;
        }
        setRelationshipType(types);
    }

    public String[] getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String[] relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String[] getPercentages() {
        return percentages;
    }

    public void setPercentages(String[] percentages) {
        this.percentages = percentages;
    }

    public String[] getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(String[] remuneration) {
        this.remuneration = remuneration;
    }
    
    
    
}
