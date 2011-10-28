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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.coi.CoiAction;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class CoiDisclosureServiceImpl implements CoiDisclosureService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private FinancialEntityService financialEntityService;
    private ProtocolFinderDao protocolFinderDao;

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

    public void setDisclDetailsForSave(CoiDisclosure coiDisclosure) {
        if (StringUtils.equals(CoiAction.PROTOCOL_DISCL_MODULE_CODE, coiDisclosure.getModuleCode())) {
            coiDisclosure.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
            for (CoiDisclEventProject coiDisclEventProject : coiDisclosure.getCoiDisclEventProjects()) {
                coiDisclosure.getCoiDiscDetails().addAll(coiDisclEventProject.getCoiDiscDetails());
            }
        }
        if (StringUtils.equals(CoiAction.PROPOSAL_DISCL_MODULE_CODE, coiDisclosure.getModuleCode())) {
            coiDisclosure.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
            for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
                coiDisclosure.getCoiDiscDetails().addAll(coiDisclProject.getCoiDiscDetails());
            }
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
        List<CoiDisclEventProject> disclEventProjects = new ArrayList<CoiDisclEventProject>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables.getUserSession().getPrincipalId(), true);
        List<Protocol> protocols = getProtocols(GlobalVariables.getUserSession().getPrincipalId());
        for (Protocol protocol : protocols) {
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject("IRB", protocol, new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure, protocol.getProtocolNumber());
                disclosureDetail.setProtocol(protocol);
                disclosureDetails.add(disclosureDetail);
                coiDisclEventProject.getCoiDiscDetails().add(disclosureDetail);
            }    
            disclEventProjects.add(coiDisclEventProject);
        }
        coiDisclosure.setCoiDiscDetails(disclosureDetails);
        coiDisclosure.setCoiDisclEventProjects(disclEventProjects);
        
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
        // TODO : this is for protocol now
        Collections.sort(coiDisclosure.getCoiDiscDetails());
        String moduleItemKey = Constants.EMPTY_STRING;
        List<CoiDisclEventProject> disclEventProjects = new ArrayList<CoiDisclEventProject>();
        CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject();

            for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
               if (!StringUtils.equals(moduleItemKey, coiDiscDetail.getModuleItemKey())) {
                   if (StringUtils.isNotBlank(moduleItemKey)) {
                       disclEventProjects.add(coiDisclEventProject);
                   }
                   Protocol protocol = protocolFinderDao.findCurrentProtocolByNumber(coiDiscDetail.getModuleItemKey());
                   moduleItemKey = coiDiscDetail.getModuleItemKey();
                   coiDisclEventProject = new CoiDisclEventProject("IRB", protocol, new ArrayList<CoiDiscDetail>());              
               }
               coiDisclEventProject.getCoiDiscDetails().add(coiDiscDetail);
               coiDisclEventProject.setDisclosureFlag(true);
            }
            disclEventProjects.add(coiDisclEventProject); // the last project

            coiDisclosure.setCoiDisclEventProjects(disclEventProjects);
    }

    public void updateDisclosureDetails(CoiDisclProject coiDisclProject) {
        // When creating a disclosure. the detail will be created at first
        // TODO : what if FE is deactivate
        Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureId", coiDisclProject.getCoiDisclosureId());
        fieldValues.put("moduleCode", CoiAction.PROPOSAL_DISCL_MODULE_CODE);
        fieldValues.put("moduleItemKey", coiDisclProject.getCoiProjectId());

        coiDisclProject.setCoiDiscDetails((List<CoiDiscDetail>) businessObjectService.findMatching(CoiDiscDetail.class, fieldValues));

//        if (CollectionUtils.isEmpty(coiDisclProject.getCoiDiscDetails())) {
//            initializeDisclosureDetails(coiDisclProject);
//        }
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

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

}
