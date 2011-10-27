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
package org.kuali.kra.coi.disclosure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class CoiDisclosureServiceImpl implements CoiDisclosureService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private FinancialEntityService financialEntityService;

    @SuppressWarnings("rawtypes")
    public DisclosurePerson getDisclosureReporter(String personId, Long coiDisclosureId) {
        List<DisclosurePerson> reporters = new ArrayList<DisclosurePerson>();
        if (coiDisclosureId != null) {
            Map fieldValues = new HashMap();
            fieldValues.put("personId", personId);
            fieldValues.put("personRoleId", "COIR");
            fieldValues.put("coiDisclosureId", coiDisclosureId);

            reporters = (List<DisclosurePerson>) businessObjectService.findMatching(DisclosurePerson.class, fieldValues);
        }
        if (reporters.isEmpty()) {
            DisclosurePerson reporter = new DisclosurePerson();
            reporter.setDisclosurePersonUnits(new ArrayList<DisclosurePersonUnit>());
            reporter.setPersonId(personId);
            reporter.setPersonRoleId("COIR");
            DisclosurePersonUnit leadUnit = createLeadUnit(personId);
            if (leadUnit != null) {
                reporter.getDisclosurePersonUnits().add(leadUnit);
            }
            return reporter;
        }
        else {
            int i = 0;
            for (DisclosurePersonUnit disclosurePersonUnit : reporters.get(0).getDisclosurePersonUnits()) {
                if (disclosurePersonUnit.isLeadUnitFlag()) {
                    reporters.get(0).setSelectedUnit(i);
                    break;
                }
                i++;
            }
        }
        return reporters.get(0);
    }

    public void addDisclosureReporterUnit(DisclosureReporter disclosureReporter , DisclosureReporterUnit newDisclosureReporterUnit) {
        
        List<DisclosureReporterUnit> disclosureReporterUnits = (List<DisclosureReporterUnit>)disclosureReporter.getDisclosureReporterUnits();
        if (newDisclosureReporterUnit.isLeadUnitFlag()) {
            resetLeadUnit(disclosureReporterUnits);
            disclosureReporter.setSelectedUnit(disclosureReporterUnits.size());
        }
        disclosureReporterUnits.add(newDisclosureReporterUnit);
    }

    public void deleteDisclosureReporterUnit(DisclosureReporter disclosureReporter,List<? extends DisclosureReporterUnit> deletedUnits, int unitIndex) {
        
        List<DisclosureReporterUnit> disclosureReporterUnits = (List<DisclosureReporterUnit>)disclosureReporter.getDisclosureReporterUnits();
        DisclosureReporterUnit deletedUnit = disclosureReporterUnits.get(unitIndex);
        if (deletedUnit.getReporterUnitId() != null) {
            ((List<DisclosureReporterUnit>)deletedUnits).add(deletedUnit);
        }
        disclosureReporterUnits.remove(unitIndex);
        if (deletedUnit.isLeadUnitFlag() && !disclosureReporterUnits.isEmpty()) {
            disclosureReporterUnits.get(0).setLeadUnitFlag(true);
            disclosureReporter.setSelectedUnit(0);
        }
    }

    public void resetLeadUnit(DisclosureReporter disclosureReporter) {
        List<? extends DisclosureReporterUnit> disclosureReporterUnits = disclosureReporter.getDisclosureReporterUnits();
        if (CollectionUtils.isNotEmpty(disclosureReporterUnits)) {
            resetLeadUnit(disclosureReporterUnits);
            disclosureReporterUnits.get(disclosureReporter.getSelectedUnit()).setLeadUnitFlag(true);
        }
    }


    private void resetLeadUnit(List<? extends DisclosureReporterUnit> disclosureReporterUnits) {
        for (DisclosureReporterUnit  disclosureReporterUnit : disclosureReporterUnits) {
            disclosureReporterUnit.setLeadUnitFlag(false);
        }
        
    }

    private DisclosurePersonUnit createLeadUnit(String personId) {

        DisclosurePersonUnit leadUnit = null;
        KcPerson kcPerson = kcPersonService.getKcPersonByPersonId(personId);
        if (kcPerson != null && kcPerson.getUnit() != null) {
            leadUnit = new DisclosurePersonUnit();
            leadUnit.setLeadUnitFlag(true);
            leadUnit.setUnitNumber(kcPerson.getUnit().getUnitNumber());
            leadUnit.setUnitName(kcPerson.getUnit().getUnitName());
            leadUnit.setPersonId(personId);
        }
        return leadUnit;
    }

    
    public void getProjects() {
        // get all user's pd/protocol/Award, user is PI 
        // TODO : WIP
        List<Protocol> protocols = getProtocols(GlobalVariables.getUserSession().getPrincipalId());
        
    }
    
    public void initializeDisclosureDetails(CoiDisclosure coiDisclosure) {
        // When creating a disclosure.  the detail will be created at first
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables.getUserSession().getPrincipalId(), true);
        List<Protocol> protocols = getProtocols(GlobalVariables.getUserSession().getPrincipalId());
        for (Protocol protocol : protocols) {
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                disclosureDetails.add(createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure, protocol.getProtocolNumber()));
            }            
        }
        coiDisclosure.setCoiDiscDetails(disclosureDetails);
        
    }
    
    
    public void initializeDisclosureDetails(CoiDisclProject coiDisclProject) {
        // When creating a disclosure. the detail will be created at first
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            disclosureDetails.add(createNewCoiDiscDetail(coiDisclProject.getCoiDisclosure(), personFinIntDisclosure, coiDisclProject.getCoiProjectId()));
        }
        coiDisclProject.setCoiDiscDetails(disclosureDetails);

    }
    
    
    public void updateDisclosureDetails(CoiDisclosure coiDisclosure) {
        // When creating a disclosure. the detail will be created at first
        // TODO : what if FE is deactivate
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        List<String> moduleItemKeys = new ArrayList<String>();
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            boolean isNewFinancialEntity = true;
            for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
                if (StringUtils.equals(personFinIntDisclosure.getEntityNumber(), coiDiscDetail.getEntityNumber())) {
                    isNewFinancialEntity = false;
                    if (!personFinIntDisclosure.getSequenceNumber().equals(coiDiscDetail.getEntitySequenceNumber())) {
                        coiDiscDetail.setEntitySequenceNumber(personFinIntDisclosure.getSequenceNumber());
                        coiDiscDetail.setPersonFinIntDisclosureId(personFinIntDisclosure.getPersonFinIntDisclosureId());
                        coiDiscDetail.refreshReferenceObject("personFinIntDisclosure");
                    }
                }

            }
            if (isNewFinancialEntity) {
                // TODO : if this method is what we need, then check moduleitemkey alone is ot enough
                // may also need to check event type, which will be added later to db schema
                for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
                    if (!moduleItemKeys.contains(coiDiscDetail.getModuleItemKey())) {
                        moduleItemKeys.add(coiDiscDetail.getModuleItemKey());
                        disclosureDetails.add(createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                            coiDiscDetail.getModuleItemKey()));
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(disclosureDetails)) {
            coiDisclosure.getCoiDiscDetails().addAll(disclosureDetails);
        }
    }

    public void updateDisclosureDetails(CoiDisclProject coiDisclProject) {
        // When creating a disclosure. the detail will be created at first
        // TODO : what if FE is deactivate
        Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureId", coiDisclProject.getCoiDisclosureId());
        fieldValues.put("moduleCode", "11");
        fieldValues.put("moduleItemKey", coiDisclProject.getCoiProjectId());

        coiDisclProject.setCoiDiscDetails((List<CoiDiscDetail>) businessObjectService.findMatching(CoiDiscDetail.class, fieldValues));

        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        List<String> moduleItemKeys = new ArrayList<String>();
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            boolean isNewFinancialEntity = true;
            for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {
                if (StringUtils.equals(personFinIntDisclosure.getEntityNumber(), coiDiscDetail.getEntityNumber())) {
                    isNewFinancialEntity = false;
                    if (!personFinIntDisclosure.getSequenceNumber().equals(coiDiscDetail.getEntitySequenceNumber())) {
                        coiDiscDetail.setEntitySequenceNumber(personFinIntDisclosure.getSequenceNumber());
                        coiDiscDetail.setPersonFinIntDisclosureId(personFinIntDisclosure.getPersonFinIntDisclosureId());
                        coiDiscDetail.refreshReferenceObject("personFinIntDisclosure");
                    }
                }

            }
            if (isNewFinancialEntity) {
                // TODO : if this method is what we need, then check moduleitemkey alone is ot enough
                // may also need to check event type, which will be added later to db schema
                for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {
                    if (!moduleItemKeys.contains(coiDiscDetail.getModuleItemKey())) {
                        moduleItemKeys.add(coiDiscDetail.getModuleItemKey());
                        disclosureDetails.add(createNewCoiDiscDetail(coiDisclProject.getCoiDisclosure(), personFinIntDisclosure,
                            coiDiscDetail.getModuleItemKey()));
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(disclosureDetails)) {
            coiDisclProject.getCoiDiscDetails().addAll(disclosureDetails);
        }
        if (CollectionUtils.isEmpty(coiDisclProject.getCoiDiscDetails())) {
            initializeDisclosureDetails(coiDisclProject);
        }
    }

    private CoiDiscDetail createNewCoiDiscDetail(CoiDisclosure coiDisclosure,PersonFinIntDisclosure personFinIntDisclosure, String moduleItemKey) {
        CoiDiscDetail disclosureDetail = new CoiDiscDetail(personFinIntDisclosure);
        disclosureDetail.setModuleItemKey(moduleItemKey);
        // TODO : this is how coeus set. not sure ?
        disclosureDetail.setModuleCode(coiDisclosure.getModuleCode());
        coiDisclosure.initCoiDisclosureNumber();
        disclosureDetail.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        disclosureDetail.setSequenceNumber(coiDisclosure.getSequenceNumber());
        disclosureDetail.setDescription("Sample Description"); // this is from coeus.
        return disclosureDetail;
        
    }
    
    private List<Protocol> getProtocols(String personId) {
        
        // TODO : does this include amendment/renewal
        List<Protocol> protocols = new ArrayList<Protocol>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        fieldValues.put("protocolPersonRoleId", "PI");
        List<ProtocolPerson> protocolPersons = (List<ProtocolPerson>) businessObjectService.findMatching(ProtocolPerson.class, fieldValues);
        for (ProtocolPerson protocolPerson : protocolPersons) {
            if (protocolPerson.getProtocol().isActive()) {
                protocols.add(protocolPerson.getProtocol());
            }
        }
        return protocols;
        
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public void setFinancialEntityService(FinancialEntityService financialEntityService) {
        this.financialEntityService = financialEntityService;
    }

}
