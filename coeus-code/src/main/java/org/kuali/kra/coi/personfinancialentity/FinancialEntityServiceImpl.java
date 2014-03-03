/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 
 * This class is to implement the detail of methods declared in FinancialENtityService
 */
@Transactional
public class FinancialEntityServiceImpl implements FinancialEntityService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private VersioningService versioningService;
    private SequenceAccessorService sequenceAccessorService;

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinancialEntities(java.lang.String, boolean)
     */
    @SuppressWarnings("unchecked")
    public List<PersonFinIntDisclosure> getFinancialEntities(String personId, boolean active) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        if (active) {
            fieldValues.put("statusCode", FinIntEntityStatus.ACTIVE);
        }
        else {
            fieldValues.put("statusCode", FinIntEntityStatus.INACTIVE);
        }
        fieldValues.put("currentFlag", "Y");

        List<PersonFinIntDisclosure> personFinDisclosures =  (List<PersonFinIntDisclosure>) businessObjectService.findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues, "entityName", true);
        for (PersonFinIntDisclosure personFinIntDisclosure : personFinDisclosures) {
            personFinIntDisclosure.setVersions(getFinDisclosureVersions(personFinIntDisclosure.getEntityNumber()));     
        }
        return personFinDisclosures;
    }

    /*
     * get all financial entities with the same entity number
     */
    @SuppressWarnings("unchecked")
    public List<PersonFinIntDisclosure> getFinDisclosureVersions(String entityNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("entityNumber", entityNumber);
        return (List<PersonFinIntDisclosure>) businessObjectService.findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues, "sequenceNumber", false);

    }
    
    @SuppressWarnings("unchecked")
    public List<FinIntEntityRelType> getFinancialEntityRelationshipTypes() {
        // TODO : consider to add sort_id for sorting purposes
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put("active", "Y");
        return (List<FinIntEntityRelType>) businessObjectService.findMatchingOrderBy(FinIntEntityRelType.class, fieldValues, "sortId", true);
    }
    
    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinancialEntityDataMatrix()
     */
    @SuppressWarnings("unchecked")
    public List<FinEntityDataMatrixBean> getFinancialEntityDataMatrix() {
        List<FinIntEntityRelType> relationshipTypes = getFinancialEntityRelationshipTypes();
        List<FinEntityDataMatrixBean> dataMatrixs = new ArrayList<FinEntityDataMatrixBean>();
        List<FinEntitiesDataGroup> dataGroups = (List<FinEntitiesDataGroup>) businessObjectService
                .findAll(FinEntitiesDataGroup.class);
        Collections.sort(dataGroups);
        for (FinEntitiesDataGroup dataGroup : dataGroups) {
            for (FinEntitiesDataMatrix dataMatrix : dataGroup.getFinEntitiesDataMatrixs()) {
                if (dataMatrix.isActive()) {
                    FinEntityDataMatrixBean dataMatrixBean = new FinEntityDataMatrixBean();
                    dataMatrixBean.setDataGroupId(dataGroup.getDataGroupId());
                    dataMatrixBean.setColumnLabel(dataMatrix.getColumnLabel());
                    dataMatrixBean.setColumnName(dataMatrix.getColumnName());
                    dataMatrixBean.setDataGroupName(dataGroup.getDataGroupName());
                    dataMatrixBean.setGuiType(dataMatrix.getGuiType());
                    dataMatrixBean.setLookupArgument(dataMatrix.getLookupArgument());
                    dataMatrixBean.setRelationshipTypeBeans(new ArrayList<RelationshipTypeBean>());
                    for (FinIntEntityRelType relationshipType : relationshipTypes) {
                        RelationshipTypeBean relationTypeBean = new RelationshipTypeBean();
                        relationTypeBean.setCode(relationshipType.getRelationshipTypeCode());
                        if (dataMatrixBean.isArgValueLookup()) {
                            relationTypeBean.setStringValue(Constants.EMPTY_STRING);
                        }
                        else {
                            relationTypeBean.setBooleanValue(false);

                        }
                        dataMatrixBean.getRelationshipTypeBeans().add(relationTypeBean);
                    }
                    dataMatrixs.add(dataMatrixBean);
                }
            }

        }
        return dataMatrixs;
    }

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinDisclosureDetails(java.util.List, java.lang.String, java.lang.Integer)
     */
    public List<PersonFinIntDisclDet> getFinDisclosureDetails(List<FinEntityDataMatrixBean> dataMatrixs, String entityNumber, Integer sequenceNumber) {
        List<PersonFinIntDisclDet>  disclosureDetails = new ArrayList<PersonFinIntDisclDet>();
        for (FinEntityDataMatrixBean dataRow : dataMatrixs) {
            for (RelationshipTypeBean relationshipTypeBean : dataRow.getRelationshipTypeBeans()) {
                if (StringUtils.isNotBlank(dataRow.getComments()) || (dataRow.isArgValueLookup() && StringUtils.isNotBlank(relationshipTypeBean.getStringValue()))
                        || (!dataRow.isArgValueLookup() && relationshipTypeBean.isBooleanValue())) {
                    PersonFinIntDisclDet personFinIntDisclDet = new PersonFinIntDisclDet();
                    personFinIntDisclDet.setColumnName(dataRow.getColumnName());
                    if (StringUtils.isNotBlank(dataRow.getComments())) {
                        personFinIntDisclDet.setComments(dataRow.getComments());
                    }
                    personFinIntDisclDet.setColumnValue(getRelationshipTypeValue( dataRow, relationshipTypeBean));
                    personFinIntDisclDet.setRelationshipTypeCode(relationshipTypeBean.getCode());
                    personFinIntDisclDet.setEntityNumber(entityNumber);
                    personFinIntDisclDet.setSequenceNumber(sequenceNumber);
                    disclosureDetails.add(personFinIntDisclDet);
                }
            }
            
        }
        
        return disclosureDetails;
    }

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinancialEntityDataMatrixForEdit(java.util.List)
     */
    public List<FinEntityDataMatrixBean> getFinancialEntityDataMatrixForEdit(List<PersonFinIntDisclDet> disclosureDetails) {
        // TODO : be aware this is not efficient.  investigate if there is more efficient approach
        List<FinEntityDataMatrixBean> dataMatrixs = getFinancialEntityDataMatrix();
        for (PersonFinIntDisclDet personFinIntDisclDet : disclosureDetails) {
            for (FinEntityDataMatrixBean dataRow : dataMatrixs) {
                if (StringUtils.equals(dataRow.getColumnName(), personFinIntDisclDet.getColumnName())) {
                    for (RelationshipTypeBean relationshipTypeBean : dataRow.getRelationshipTypeBeans()) {
                        if (StringUtils.isNotBlank(personFinIntDisclDet.getComments())) {
                            dataRow.setComments(personFinIntDisclDet.getComments());
                        }
                        if (StringUtils.equals(relationshipTypeBean.getCode(), personFinIntDisclDet.getRelationshipTypeCode())) {
                            setRelationshipTypeBeanValue(dataRow, relationshipTypeBean, personFinIntDisclDet);
                            break;
                        }
                    }
                    break;

                }
            }

        }
        return dataMatrixs;
    }
    
    /*
     * Utility method to convert column value to boolean
     */
    private void setRelationshipTypeBeanValue(FinEntityDataMatrixBean dataRow, RelationshipTypeBean relationshipTypeBean, PersonFinIntDisclDet personFinIntDisclDet) {
        if (dataRow.isArgValueLookup()) {
            relationshipTypeBean.setStringValue(personFinIntDisclDet.getColumnValue());
        } else {
            if (StringUtils.equals("1", personFinIntDisclDet.getColumnValue())) {
                relationshipTypeBean.setBooleanValue(true);
            }
        }
    }

    /*
     * Utility method to conver relation type from boolean to column value
     */
    private String getRelationshipTypeValue(FinEntityDataMatrixBean dataRow, RelationshipTypeBean relationshipTypeBean) {
        String retVal = null;
        if (dataRow.isArgValueLookup()) {
            retVal = relationshipTypeBean.getStringValue();
        } else {
            if (relationshipTypeBean.isBooleanValue()) {
                retVal = "1";
            }
        }
        return retVal;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinancialEntityReporter(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public FinancialEntityReporter getFinancialEntityReporter(String personId) {
        // TODO : this reporterroleid is KC filed.  may be changed
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("reporterRoleId", "FER");

        List<FinancialEntityReporter> reporters = (List<FinancialEntityReporter>) businessObjectService.findMatching(
                FinancialEntityReporter.class, fieldValues);
        if (reporters.isEmpty()) {
            Object reporterSaved = GlobalVariables.getUserSession().retrieveObject("reporterSaved-"+GlobalVariables.getUserSession().getPrincipalName());
            if (reporterSaved == null) {
                GlobalVariables.getUserSession().addObject("reporterSaved-"+GlobalVariables.getUserSession().getPrincipalName(),"saved");
            }
            FinancialEntityReporter reporter = new FinancialEntityReporter();
            reporter.setFinancialEntityReporterUnits(new ArrayList<FinancialEntityReporterUnit>());
            reporter.setPersonId(personId);
            reporter.setReporterRoleId("FER");
            FinancialEntityReporterUnit leadUnit = createLeadUnit(personId);
            if (leadUnit != null) {
                reporter.getFinancialEntityReporterUnits().add(leadUnit);
            }
            if (reporterSaved == null) {
                businessObjectService.save(reporter);
            }
            return reporter;
        } else {
            int i = 0;
            for (FinancialEntityReporterUnit financialEntityReporterUnit : reporters.get(0).getFinancialEntityReporterUnits()) {
                if (financialEntityReporterUnit.isLeadUnitFlag()) {
                    reporters.get(0).setSelectedUnit(i);
                    break;
                }
                i++;
            }
        }
        return reporters.get(0);
    }
    
    /*
     * set up the lead unit from person's unit
     */
    private FinancialEntityReporterUnit createLeadUnit(String personId) {

        FinancialEntityReporterUnit leadUnit = null;
        KcPerson kcPerson = kcPersonService.getKcPersonByPersonId(personId);
        if (kcPerson != null && kcPerson.getUnit() != null) {
            leadUnit = new FinancialEntityReporterUnit();
            leadUnit.setLeadUnitFlag(true);
            leadUnit.setUnitNumber(kcPerson.getUnit().getUnitNumber());
            leadUnit.setUnitName(kcPerson.getUnit().getUnitName());
            leadUnit.setPersonId(personId);
        }
        return leadUnit;
    }
    
    /**
     * 
     * This method returns the current list of FinancialEntityAttachment objects for the specified FE; null when FE ID is null.
     * @param entityId
     * @return null when input parameter is null; otherwise list of FinancialEntityAttachment objects 
     */   
    public List<FinancialEntityAttachment> retrieveFinancialEntityAttachmentsFor(Long entityId) {
        List<FinancialEntityAttachment> attachments = null;
        if (ObjectUtils.isNotNull(entityId)) {
            Map<String, Object> searchCriteria = new HashMap<String, Object>();
            searchCriteria.put("financialEntityId", entityId);    
            attachments =  new ArrayList<FinancialEntityAttachment>(businessObjectService.findMatching(FinancialEntityAttachment.class, searchCriteria));
        }
        return attachments;
    }

    /**
     * As part of the versioning process, the FE row presently seen as current/active will be made non-current/inactive.
     */
    public PersonFinIntDisclosure versionPersonFinintDisclosure(PersonFinIntDisclosure personFinIntDisclosure, 
                                                                List<FinEntityDataMatrixBean> newRelationDetails,
                                                                List<FinancialEntityAttachment> newFinancialEntityAttachments) throws VersionException {
        //make the current row non-current before creating new version
        nonCurrentOldDisclosure(personFinIntDisclosure.getPersonFinIntDisclosureId());
        
        //create new version based on present row
        PersonFinIntDisclosure newDisclosure = versioningService.createNewVersion(personFinIntDisclosure);
        FinancialEntityContactInfo copiedContactInfo = (FinancialEntityContactInfo)ObjectUtils.deepCopy(newDisclosure.getFinEntityContactInfos().get(0));
        copiedContactInfo.setPersonFinIntDisclosureId(null);
        copiedContactInfo.setFinancialEntityContactInfoId(null);
        newDisclosure.setFinEntityContactInfos(new ArrayList<FinancialEntityContactInfo>());
        newDisclosure.getFinEntityContactInfos().add(copiedContactInfo);
        newDisclosure.setPerFinIntDisclDetails(getFinDisclosureDetails(newRelationDetails, newDisclosure.getEntityNumber(), newDisclosure.getSequenceNumber()));
        newDisclosure.setFinEntityAttachments(FinancialEntityAttachment.copyAttachmentList(newFinancialEntityAttachments));
        return newDisclosure;
    }
    
    /*
     * set the current_flag of previous version to 'N'
     * current_flag is try to speed up the process to find the current person FE disclosure
     */
    private void nonCurrentOldDisclosure(Long disclosureId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personFinIntDisclosureId", disclosureId);
        PersonFinIntDisclosure personFinIntDisclosure = (PersonFinIntDisclosure) businessObjectService.findByPrimaryKey(PersonFinIntDisclosure.class, fieldValues);
        personFinIntDisclosure.setCurrentFlag(false);
        businessObjectService.save(personFinIntDisclosure);

    }
    
    public PersonFinIntDisclosure getCurrentFinancialEntities(String entityNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("entityNumber", entityNumber);
        fieldValues.put("currentFlag", "Y");

        return ((List<PersonFinIntDisclosure>) businessObjectService.findMatching(PersonFinIntDisclosure.class, fieldValues)).get(0);

    }

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getNextEntityNumber()
     */
    public String getNextEntityNumber() {
        return sequenceAccessorService.getNextAvailableSequenceNumber("SEQ_ENTITY_NUMBER_S", PersonFinIntDisclosure.class).toString(); // sequence #

    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

}
