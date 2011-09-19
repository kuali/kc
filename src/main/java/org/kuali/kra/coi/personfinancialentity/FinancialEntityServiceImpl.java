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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * This class is to implement the detail of methods declared in FinancialENtityService
 */
@Transactional
public class FinancialEntityServiceImpl implements FinancialEntityService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private VersioningService versioningService;

    /**
     * 
     * @see org.kuali.kra.coi.personfinancialentity.FinancialEntityService#getFinancialEntities(java.lang.String, boolean)
     */
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

        List<PersonFinIntDisclosure> personFinDisclosures =  (List<PersonFinIntDisclosure>) businessObjectService.findMatching(PersonFinIntDisclosure.class, fieldValues);
        for (PersonFinIntDisclosure personFinIntDisclosure : personFinDisclosures) {
            personFinIntDisclosure.setVersions(getFinDisclosureVersions(personFinIntDisclosure.getEntityNumber()));     
        }
        return personFinDisclosures;
    }

    /*
     * get all financial entities with the same entity number
     */
    private List<PersonFinIntDisclosure> getFinDisclosureVersions(String entityNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("entityNumber", entityNumber);
        return (List<PersonFinIntDisclosure>) businessObjectService.findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues, "sequenceNumber", false);

    }
    
    public List<FinIntEntityRelType> getFinancialEntityRelationshipTypes() {
        // TODO : consider to add sort_id for sorting purposes
        Map fieldValues = new HashMap();
        fieldValues.put("active", "Y");
        return (List<FinIntEntityRelType>) businessObjectService.findMatchingOrderBy(FinIntEntityRelType.class, fieldValues, "sortId", true);
    }
    
    public List<FinEntityDataMatrixBean> getFinancialEntityDataMatrix() {
        List<FinIntEntityRelType> relationshipTypes = getFinancialEntityRelationshipTypes();
        List<FinEntityDataMatrixBean> dataMatrixs = new ArrayList<FinEntityDataMatrixBean>();
        List<FinEntitiesDataGroup> dataGroups = (List<FinEntitiesDataGroup>) businessObjectService
                .findAll(FinEntitiesDataGroup.class);
        Collections.sort(dataGroups);
        for (FinEntitiesDataGroup dataGroup : dataGroups) {
            for (FinEntitiesDataMatrix dataMatrix : dataGroup.getFinEntitiesDataMatrixs()) {
                if (dataMatrix.getStatusFlag()) {
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
    
    private void setRelationshipTypeBeanValue(FinEntityDataMatrixBean dataRow, RelationshipTypeBean relationshipTypeBean, PersonFinIntDisclDet personFinIntDisclDet) {
        if (dataRow.isArgValueLookup()) {
            relationshipTypeBean.setStringValue(personFinIntDisclDet.getColumnValue());
        } else {
            if (StringUtils.equals("1", personFinIntDisclDet.getColumnValue())) {
                relationshipTypeBean.setBooleanValue(true);
            }
        }
    }

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

    public FinancialEntityReporter getFinancialEntityReporter(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("reporterRoleId", "FER");

        List<FinancialEntityReporter> reporters = (List<FinancialEntityReporter>) businessObjectService.findMatching(
                FinancialEntityReporter.class, fieldValues);
        if (reporters.isEmpty()) {
            FinancialEntityReporter reporter = new FinancialEntityReporter();
            reporter.setFinancialEntityReporterUnits(new ArrayList<FinancialEntityReporterUnit>());
            reporter.setPersonId(personId);
            reporter.setReporterRoleId("FER");
            FinancialEntityReporterUnit leadUnit = createLeadUnit(personId);
            if (leadUnit != null) {
                reporter.getFinancialEntityReporterUnits().add(leadUnit);
            }
            businessObjectService.save(reporter);
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

    public PersonFinIntDisclosure versionPersonFinintDisclosure(PersonFinIntDisclosure personFinIntDisclosure, List<FinEntityDataMatrixBean> newRelationDetails) throws VersionException {
        PersonFinIntDisclosure newDisclosure = versioningService.createNewVersion(personFinIntDisclosure);
        FinancialEntityContactInfo copiedContactInfo = (FinancialEntityContactInfo)ObjectUtils.deepCopy(newDisclosure.getFinEntityContactInfos().get(0));
        copiedContactInfo.setPersonFinIntDisclosureId(null);
        copiedContactInfo.setFinancialEntityContactInfoId(null);
        newDisclosure.setFinEntityContactInfos(new ArrayList<FinancialEntityContactInfo>());
        newDisclosure.getFinEntityContactInfos().add(copiedContactInfo);
        newDisclosure.setPerFinIntDisclDetails(getFinDisclosureDetails(
                newRelationDetails, newDisclosure.getEntityNumber(),
                newDisclosure.getSequenceNumber()));
        nonCurrentOldDisclosure(personFinIntDisclosure.getPersonFinIntDisclosureId());
        return newDisclosure;
    }
    
    private void nonCurrentOldDisclosure(Long disclosureId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personFinIntDisclosureId", disclosureId);
        PersonFinIntDisclosure personFinIntDisclosure = (PersonFinIntDisclosure) businessObjectService.findByPrimaryKey(PersonFinIntDisclosure.class, fieldValues);
        personFinIntDisclosure.setCurrentFlag(false);
        businessObjectService.save(personFinIntDisclosure);

    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }

}
