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

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import noNamespace.ReportType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureHistory;
import org.kuali.kra.coi.CoiDisclosureStatus;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.dao.SponsorHierarchyDao;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolFinderDao;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DateTimeService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.DateUtils;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

public class CoiDisclosureServiceImpl implements CoiDisclosureService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private FinancialEntityService financialEntityService;
    private ProtocolFinderDao protocolFinderDao;
    private VersioningService versioningService;
    private ParameterService parameterService;
    private DateTimeService dateTimeService;
    private SponsorHierarchyDao sponsorHierarchyDao;

    private static final String PROTOCOL_DISCLOSE_STATUS_CODES = "PROTOCOL_DISCLOSE_STATUS_CODES";
    private static final String PROPOSAL_DISCLOSE_STATUS_CODES = "PROPOSAL_DISCLOSE_STATUS_CODES";
    private static final String INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES = "INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES";
    private static final String AWARD_DISCLOSE_STATUS_CODES = "AWARD_DISCLOSE_STATUS_CODES";
    private static final String SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE = "SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE";
    private static final String SPONSORS_FOR_PROTOCOL_DISCLOSE = "SPONSORS_FOR_PROTOCOL_DISCLOSE";
    private static final String ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE = "ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE";
    private static final String ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE = "ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE";
    private static Map<String, String> eventModuleMap = new HashMap<String, String>();
    static {
        eventModuleMap.put(CoiDisclosureEventType.AWARD, CoeusModule.AWARD_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.IRB_PROTOCOL, CoeusModule.IRB_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, CoeusModule.INSTITUTIONAL_PROPOSAL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.MANUAL_AWARD, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
    }

    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getDisclosureReporter(java.lang.String, java.lang.Long)
     */
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

    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#addDisclosureReporterUnit(org.kuali.kra.coi.DisclosureReporter, org.kuali.kra.coi.DisclosureReporterUnit)
     */
    public void addDisclosureReporterUnit(DisclosureReporter disclosureReporter , DisclosureReporterUnit newDisclosureReporterUnit) {
        
        List<DisclosureReporterUnit> disclosureReporterUnits = (List<DisclosureReporterUnit>)disclosureReporter.getDisclosureReporterUnits();
        if (newDisclosureReporterUnit.isLeadUnitFlag()) {
            resetLeadUnit(disclosureReporterUnits);
            disclosureReporter.setSelectedUnit(disclosureReporterUnits.size());
        }
        disclosureReporterUnits.add(newDisclosureReporterUnit);
    }

    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#deleteDisclosureReporterUnit(org.kuali.kra.coi.DisclosureReporter, java.util.List, int)
     */
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

    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#resetLeadUnit(org.kuali.kra.coi.DisclosureReporter)
     */
    public void resetLeadUnit(DisclosureReporter disclosureReporter) {
        List<? extends DisclosureReporterUnit> disclosureReporterUnits = disclosureReporter.getDisclosureReporterUnits();
        if (CollectionUtils.isNotEmpty(disclosureReporterUnits)) {
            resetLeadUnit(disclosureReporterUnits);
            disclosureReporterUnits.get(disclosureReporter.getSelectedUnit()).setLeadUnitFlag(true);
        }
    }

    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#setDisclDetailsForSave(org.kuali.kra.coi.CoiDisclosure)
     */
    public void setDisclDetailsForSave(CoiDisclosure coiDisclosure) {
        if (coiDisclosure.isManualEvent()) {
            coiDisclosure.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
            for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
                coiDisclosure.getCoiDiscDetails().addAll(coiDisclProject.getCoiDiscDetails());
            }
        }
        else {
          if (coiDisclosure.isAnnualEvent()) {
            coiDisclosure.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
            for (CoiDisclEventProject coiDisclEventProject : coiDisclosure.getCoiDisclEventProjects()) {
                    coiDisclosure.getCoiDiscDetails().addAll(coiDisclEventProject.getCoiDiscDetails());
            }
          }
        }
    }


    /*
     * set all units leadunitflag to false
     */
    private void resetLeadUnit(List<? extends DisclosureReporterUnit> disclosureReporterUnits) {
        for (DisclosureReporterUnit  disclosureReporterUnit : disclosureReporterUnits) {
            disclosureReporterUnit.setLeadUnitFlag(false);
        }
        
    }

    /*
     * set the kcperson unit to leadunit when reporter is set up initially
     */
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
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#initializeDisclosureDetails(org.kuali.kra.coi.CoiDisclosure)
     */
    public void initializeDisclosureDetails(CoiDisclosure coiDisclosure) {
        // When creating a disclosure. the detail will be created at first
        //  this method should only for annual.  
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<CoiDisclEventProject> disclEventProjects = new ArrayList<CoiDisclEventProject>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        if (coiDisclosure.isProtocolEvent() || coiDisclosure.isAnnualEvent()) {
            initProtocols(disclEventProjects, disclosureDetails, financialEntities, coiDisclosure);
        }
        if (coiDisclosure.isProposalEvent() || coiDisclosure.isAnnualEvent()) {
            initProposals(disclEventProjects, disclosureDetails, financialEntities, coiDisclosure);
        } 
        if (coiDisclosure.isInstitutionalProposalEvent() || coiDisclosure.isAnnualEvent()) {
            initInstitutionalProposals(disclEventProjects, disclosureDetails, financialEntities, coiDisclosure);
        } 
        if (coiDisclosure.isAwardEvent() || coiDisclosure.isAnnualEvent()) {
            initAwards(disclEventProjects, disclosureDetails, financialEntities, coiDisclosure);
        } 

        coiDisclosure.setCoiDiscDetails(disclosureDetails);
        coiDisclosure.setCoiDisclEventProjects(disclEventProjects);

    }
    
    /*
     * set up protocols, that need disclosure, with FE relationship
     */
    private void initProtocols(List<CoiDisclEventProject> disclEventProjects, List<CoiDiscDetail> disclosureDetails, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {
        List<Protocol> protocols = getProtocols(GlobalVariables.getUserSession().getPrincipalId());
        for (Protocol protocol : protocols) {
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.IRB_PROTOCOL, protocol,
                new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        protocol.getProtocolNumber(), protocol.getProtocolId().toString(), CoiDisclosureEventType.IRB_PROTOCOL);
//                disclosureDetail.setProjectType(CoiDisclosureEventType.IRB_PROTOCOL);
                disclosureDetails.add(disclosureDetail);
                coiDisclEventProject.getCoiDiscDetails().add(disclosureDetail);
            }
            disclEventProjects.add(coiDisclEventProject);
        }
        
    }
    
    /*
     * set up proposals, that need disclosure, with FE relationship
     */
    private void initProposals(List<CoiDisclEventProject> disclEventProjects, List<CoiDiscDetail> disclosureDetails,
            List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {
        List<DevelopmentProposal> proposals = getProposals(GlobalVariables.getUserSession().getPrincipalId());
        for (DevelopmentProposal proposal : proposals) {
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, proposal, new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        proposal.getProposalNumber(), proposal.getProposalNumber(), CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
                // disclosureDetail.setProtocol(proposal);
//                disclosureDetail.setProjectType(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
                disclosureDetails.add(disclosureDetail);
                coiDisclEventProject.getCoiDiscDetails().add(disclosureDetail);
            }
            disclEventProjects.add(coiDisclEventProject);
        }
    }
    
    /*
     * set up institutional proposals, that need disclosure, with FE relationship
     */
    private void initInstitutionalProposals(List<CoiDisclEventProject> disclEventProjects, List<CoiDiscDetail> disclosureDetails, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {
        List<InstitutionalProposal> iProposals = getInstitutionalProposals(GlobalVariables.getUserSession().getPrincipalId());
        for (InstitutionalProposal proposal : iProposals) {
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, proposal,
                new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        proposal.getProposalNumber(), proposal.getProposalId().toString(), CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL);
//                disclosureDetail.setProjectType(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
//                disclosureDetail.setProtocol(proposal);
                disclosureDetails.add(disclosureDetail);
                coiDisclEventProject.getCoiDiscDetails().add(disclosureDetail);
            }
            disclEventProjects.add(coiDisclEventProject);
        }
        
    }
    
    /*
     * set up awards, that need disclosure, with FE relationship
     */
    private void initAwards(List<CoiDisclEventProject> disclEventProjects, List<CoiDiscDetail> disclosureDetails, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {
        List<Award> awards = getAwards(GlobalVariables.getUserSession().getPrincipalId());
        List<String> rootAwardNumbers = new ArrayList<String>();
        for (Award award : awards) {
            if (!isAwardHierarchyIncluded(rootAwardNumbers, award)) {
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.AWARD, award,
                new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        award.getAwardNumber(), award.getAwardId().toString(), CoiDisclosureEventType.AWARD);
//                disclosureDetail.setProtocol(proposal);
//                disclosureDetail.setProjectType(CoiDisclosureEventType.AWARD);
                disclosureDetails.add(disclosureDetail);
                coiDisclEventProject.getCoiDiscDetails().add(disclosureDetail);
            }
            disclEventProjects.add(coiDisclEventProject);
        }
        }
        
    }
    
    private boolean isAwardHierarchyIncluded(List<String> rootAwardNumbers, Award award) {
        boolean isIncluded = true;
        
        if (rootAwardNumbers.isEmpty()) {
            String rootAwardNumber = getRootAwardNumber(award.getAwardNumber());
            if (StringUtils.isNotBlank(rootAwardNumber)) {
                rootAwardNumbers.add(rootAwardNumber);
            }
            isIncluded = false;
        } else {
            String rootAwardNumber = getRootAwardNumber(award.getAwardNumber());
            if (StringUtils.isNotBlank(rootAwardNumber) && !rootAwardNumbers.contains(rootAwardNumber)) {
                rootAwardNumbers.add(rootAwardNumber);
                isIncluded = false;
            }
            if (StringUtils.isBlank(rootAwardNumber)) {
                isIncluded = false;
            }
        }
        return isIncluded;
        
    }
    
    private String getRootAwardNumber(String awardNumber) {
        HashMap<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("awardNumber", awardNumber);
        List<AwardHierarchy> awardHierarchies  = (List<AwardHierarchy>)businessObjectService.findMatching(AwardHierarchy.class, fieldValues);
        if (CollectionUtils.isNotEmpty(awardHierarchies)) {
               return awardHierarchies.get(0).getRootAwardNumber();                
            
        }
        return null;
        
    }
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#initializeDisclosureDetails(org.kuali.kra.coi.CoiDisclosure,
     *      java.lang.String)
     */
    public void initializeDisclosureDetails(CoiDisclosure coiDisclosure, String projectId) {
        // When creating a disclosure. the detail will be created at first
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        coiDisclosure.setEventBo(getEventBo(coiDisclosure, projectId));
        String moduleItemKey = getModuleItemKey(coiDisclosure, coiDisclosure.getEventBo());
        String projectIdFk = getProjectIdFk(coiDisclosure, coiDisclosure.getEventBo());
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                    moduleItemKey, projectIdFk, coiDisclosure.getEventTypeCode());
//            disclosureDetail.setProjectType(coiDisclosure.getEventTypeCode());
            disclosureDetails.add(disclosureDetail);
        }
        coiDisclosure.setCoiDiscDetails(disclosureDetails);
    }
    
    /*
     * get moduleitemkey from different project bo
     */
    private String getModuleItemKey(CoiDisclosure coiDisclosure, KraPersistableBusinessObjectBase eventBo) {
    // TODO : this is a temp method, should add interface and 'getmoduleitemkey' in the disclosurable bos    
        String moduleItemKey = null;
        if (coiDisclosure.isProtocolEvent()) {
            moduleItemKey = ((Protocol)eventBo).getProtocolNumber();
        }
        else if (coiDisclosure.isProposalEvent()) {
            moduleItemKey = ((DevelopmentProposal)eventBo).getProposalNumber();
        } else if (coiDisclosure.isInstitutionalProposalEvent()) {
            moduleItemKey = ((InstitutionalProposal)eventBo).getProposalNumber();
        } 
        else if (coiDisclosure.isAwardEvent()) {
            moduleItemKey = ((Award)eventBo).getAwardNumber();
       }
        return moduleItemKey;
    }
    
    private String getProjectIdFk(CoiDisclosure coiDisclosure, KraPersistableBusinessObjectBase eventBo) {
    // TODO : this is a temp method, should add interface and 'getmoduleitemkey' in the disclosurable bos    
        String projectIdFk = null;
        if (coiDisclosure.isProtocolEvent()) {
            projectIdFk = ((Protocol)eventBo).getProtocolId().toString();
        }
        else if (coiDisclosure.isProposalEvent()) {
            projectIdFk = ((DevelopmentProposal)eventBo).getProposalNumber();
        } else if (coiDisclosure.isInstitutionalProposalEvent()) {
            projectIdFk = ((InstitutionalProposal)eventBo).getProposalId().toString();
        } 
        else if (coiDisclosure.isAwardEvent()) {
            projectIdFk = ((Award)eventBo).getAwardId().toString();
       }
        return projectIdFk;
    }
    
    /*
     * set up the eventbo based on disclosure event and projectid
     */
    private KraPersistableBusinessObjectBase getEventBo(CoiDisclosure coiDisclosure, String projectId) {
        KraPersistableBusinessObjectBase eventBo = null;
        if (coiDisclosure.isProtocolEvent()) {
            eventBo = getProtocol(Long.valueOf(projectId));
        }
        else if (coiDisclosure.isProposalEvent()) {
            eventBo = getDevelopmentProposal(projectId);
        } else if (coiDisclosure.isInstitutionalProposalEvent()) {
                eventBo = getInstitutionalProposal(projectId);
        }
        else if (coiDisclosure.isAwardEvent()) {
            // TODO : for award
            eventBo = getAwardById(projectId);
       }
        return eventBo;

    }

    /*
     * this is for master disclosure where the copied disc_detail can't reference the mater disclosure directly
     * the disclosure event type may not match the disc detail project type
     */
    private KraPersistableBusinessObjectBase getEventBo(CoiDiscDetail coiDiscdetail, String projectId) {
        KraPersistableBusinessObjectBase eventBo = null;
        if (coiDiscdetail.isProtocolEvent()) {
            eventBo = getProtocol(Long.valueOf(projectId));
        }
        else if (coiDiscdetail.isProposalEvent()) {
            eventBo = getDevelopmentProposal(projectId);
        } else if (coiDiscdetail.isInstitutionalProposalEvent()) {
                eventBo = getInstitutionalProposal(projectId);
        }
        else if (coiDiscdetail.isAwardEvent()) {
            // TODO : for award
            eventBo = getAwardById(projectId);
       }
        return eventBo;

    }

    /*
     * get protocol by using protocolid
     */
    private Protocol getProtocol(Long protocolId) {
        HashMap<String, Object> pkMap = new HashMap<String, Object>();
        pkMap.put("protocolId", protocolId);
        return (Protocol) this.businessObjectService.findByPrimaryKey(Protocol.class, pkMap);
    
    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#initializeDisclosureDetails(org.kuali.kra.coi.CoiDisclProject)
     */
    public void initializeDisclosureDetails(CoiDisclProject coiDisclProject) {
        // When creating a disclosure. the detail will be created at first
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            CoiDiscDetail disclosureDetail =createNewCoiDiscDetail(coiDisclProject.getCoiDisclosure(), personFinIntDisclosure, coiDisclProject.getCoiProjectId(), coiDisclProject.getCoiProjectId(), coiDisclProject.getDisclosureEventType());
            disclosureDetails.add(disclosureDetail);
//            disclosureDetail.setProjectType(coiDisclProject.getDisclosureEventType());
        }
        coiDisclProject.setCoiDiscDetails(disclosureDetails);

    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#updateDisclosureDetails(org.kuali.kra.coi.CoiDisclosure)
     */
    public void updateDisclosureDetails(CoiDisclosure coiDisclosure) {
        Collections.sort(coiDisclosure.getCoiDiscDetails());
        String projectType = Constants.EMPTY_STRING;
        String projectIdFk = Constants.EMPTY_STRING;
        String moduleItemKey = Constants.EMPTY_STRING;
        List<CoiDisclEventProject> disclEventProjects = new ArrayList<CoiDisclEventProject>();
        CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject();
        String personId = GlobalVariables.getUserSession().getPrincipalId();
        if (!StringUtils.equals(personId, coiDisclosure.getPersonId())) {
            personId = coiDisclosure.getPersonId();
        }

        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(personId, true);

        List<String> disclEntityNumbers = new ArrayList<String>();
        if (!coiDisclosure.isManualEvent()) {
            for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
                if (!StringUtils.equals(projectType, coiDiscDetail.getProjectType()) || !StringUtils.equals(moduleItemKey, coiDiscDetail.getModuleItemKey())) {
                    if (StringUtils.isNotBlank(projectType) && coiDisclEventProject.getEventProjectBo() != null) {
                        // when switch to a different project for annual discl, check if there is any new FE need to add to previous project.
                        checkToAddNewFinancialEntity(financialEntities, coiDisclEventProject.getCoiDiscDetails(), disclEntityNumbers, coiDisclEventProject.getProjectId(), coiDisclosure, coiDiscDetail.getProjectType(), coiDiscDetail.getProjectIdFk());
                        disclEventProjects.add(coiDisclEventProject);
                    }
                    moduleItemKey = coiDiscDetail.getModuleItemKey();
                    projectType = coiDiscDetail.getProjectType();
                    projectIdFk = coiDiscDetail.getProjectIdFk();
                    if (!coiDisclosure.isAnnualEvent()) {
                        coiDisclEventProject = getEventBo(coiDisclosure, coiDiscDetail);
                    } else {
                        coiDisclEventProject = getEventBoForAnnualDiscl(coiDisclosure, coiDiscDetail);
                    }
                }
                getCurrentFinancialEntity(coiDiscDetail);
                if (coiDiscDetail.getPersonFinIntDisclosure().isStatusActive()) {
                    if (!disclEntityNumbers.contains(coiDiscDetail.getPersonFinIntDisclosure().getEntityNumber())) {
                        disclEntityNumbers.add(coiDiscDetail.getPersonFinIntDisclosure().getEntityNumber());
                    }
                    coiDisclEventProject.getCoiDiscDetails().add(coiDiscDetail);
                    coiDisclEventProject.setDisclosureFlag(true);
                }
            }
            // TODO : what if coi discl is created first, then create FE, then discl details is empty
            if (CollectionUtils.isEmpty(coiDisclosure.getCoiDiscDetails()) && CollectionUtils.isNotEmpty(financialEntities)) {
                CoiDiscDetail coiDiscDetail = new CoiDiscDetail();
                coiDiscDetail.setModuleItemKey(coiDisclosure.getModuleItemKey());
                if (!coiDisclosure.isAnnualEvent()) {
                    projectType = coiDisclosure.getEventTypeCode();
                    coiDisclEventProject = getEventBo(coiDisclosure, coiDiscDetail);
                    coiDiscDetail.setProjectIdFk(getProjectIdFk(coiDisclosure, coiDisclosure.getEventBo()));
                    projectIdFk = coiDiscDetail.getProjectIdFk();
                }
                
            }
            if (coiDisclEventProject.getEventProjectBo() != null) {
                checkToAddNewFinancialEntity(financialEntities, coiDisclEventProject.getCoiDiscDetails(), disclEntityNumbers, coiDisclEventProject.getProjectId(), coiDisclosure, projectType, projectIdFk);
                disclEventProjects.add(coiDisclEventProject); // the last project
            }

            coiDisclosure.setCoiDisclEventProjects(disclEventProjects);
            // TODO : single project
            if (!coiDisclosure.isAnnualEvent() && !disclEventProjects.isEmpty()) {
                coiDisclosure.setCoiDiscDetails(disclEventProjects.get(0).getCoiDiscDetails());
            }
            // TODO : what if coi discl is created first, then create FE, then discl details is empty - annual event
            if (CollectionUtils.isEmpty(coiDisclosure.getCoiDiscDetails()) && coiDisclosure.isAnnualEvent() && CollectionUtils.isNotEmpty(financialEntities)) {
                initializeDisclosureDetails(coiDisclosure);                
            }

        }
    }


    /*
     * check if there is new FE added since last update. if there is, then set up the coi disclose FE
     */
    private void checkToAddNewFinancialEntity(List<PersonFinIntDisclosure> financialEntities, List<CoiDiscDetail> coiDiscDetails, List<String> disclEntityNumbers, String projectId, CoiDisclosure coiDisclosure, String projectType, String projectIdFk) {
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            if (!disclEntityNumbers.contains(personFinIntDisclosure.getEntityNumber())) {
                coiDiscDetails.add(createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure, projectId, projectIdFk, projectType));
               // coiDiscDetails.get(coiDiscDetails.size() - 1).setProjectType(projectType);
            }
        }
        
        
    }

    /*
     * get current FE if there is new version FE
     */
    private void getCurrentFinancialEntity(CoiDiscDetail coiDiscDetail) {
        if (!coiDiscDetail.getPersonFinIntDisclosure().isCurrentFlag()) {
            PersonFinIntDisclosure financialEntity = financialEntityService.getCurrentFinancialEntities(coiDiscDetail.getPersonFinIntDisclosure().getEntityNumber());
            coiDiscDetail.setEntitySequenceNumber(financialEntity.getSequenceNumber());
            coiDiscDetail.setPersonFinIntDisclosureId(financialEntity.getPersonFinIntDisclosureId());
            coiDiscDetail.setPersonFinIntDisclosure(financialEntity);
        }
        
    }
    
    /*
     * get event bo based on event and moduleitemkey.  
     * TODO : this may not be enough.  may need to consider to save event pk to table
     */
    private CoiDisclEventProject getEventBo(CoiDisclosure coiDisclosure, CoiDiscDetail coiDiscDetail) {
        CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject();
        if (coiDisclosure.isProtocolEvent()) {
            Protocol protocol = protocolFinderDao.findCurrentProtocolByNumber(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.IRB_PROTOCOL, protocol, new ArrayList<CoiDiscDetail>());
            coiDisclosure.setEventBo(protocol);
        }
        else if (coiDisclosure.isProposalEvent()) {
            DevelopmentProposal proposal = getDevelopmentProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, proposal, new ArrayList<CoiDiscDetail>());
            coiDisclosure.setEventBo(proposal);
        } else if (coiDisclosure.isInstitutionalProposalEvent()) {
            InstitutionalProposal proposal = getInstitutionalProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, proposal, new ArrayList<CoiDiscDetail>());
            coiDisclosure.setEventBo(proposal);
        } 
        else if (coiDisclosure.isAwardEvent()) {
            // TODO : for award
            Award award = getAward(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.AWARD, award, new ArrayList<CoiDiscDetail>());
            coiDisclosure.setEventBo(award);
       }
        return coiDisclEventProject;

    }
    
    // temporary solution until coeus schema is finalized
    /*
     * get eventbo for annual disclosure
     */
    private CoiDisclEventProject getEventBoForAnnualDiscl(CoiDisclosure coiDisclosure, CoiDiscDetail coiDiscDetail) {
        CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject();
        if (coiDiscDetail.isProtocolEvent()) {
            Protocol protocol = protocolFinderDao.findCurrentProtocolByNumber(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.IRB_PROTOCOL, protocol, new ArrayList<CoiDiscDetail>());
        }
        else if (coiDiscDetail.isProposalEvent()) {
            DevelopmentProposal proposal = getDevelopmentProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, proposal, new ArrayList<CoiDiscDetail>());
        } else if (coiDiscDetail.isInstitutionalProposalEvent()) {
            InstitutionalProposal proposal = getInstitutionalProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, proposal, new ArrayList<CoiDiscDetail>());
        } else  {
            // TODO : for award
            Award award = getAward(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject(CoiDisclosureEventType.AWARD, award, new ArrayList<CoiDiscDetail>());
       }
        return coiDisclEventProject;

    }
        
    /*
     * retrieve award by awardid
     */
    private Award getAwardById(String awardId) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("awardId", awardId);
        return (Award)businessObjectService.findByPrimaryKey(Award.class, values);
    }
    
    /*
     * retrieve award by awardnumber
     */
    private Award getAward(String awardNumber) {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("awardNumber", awardNumber);
        List<Award> awards = (List<Award>)businessObjectService.findMatchingOrderBy(Award.class, values, "sequenceNumber", false);
        return awards.get(0);
    }

    /*
     * get PD by proposalNumber
     */
    private DevelopmentProposal getDevelopmentProposal(String proposalNumber) {
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("proposalNumber", proposalNumber);
        DevelopmentProposal currentProposal = (DevelopmentProposal) businessObjectService.findByPrimaryKey(DevelopmentProposal.class, primaryKeys);
        return currentProposal;
    }
    
    /*
     * get IP by using proposalNumber
     */
    private InstitutionalProposal getInstitutionalProposal(String proposalNumber) {
        // TODO : not sure IP pk is proposal#
        Map<String, Object> primaryKeys = new HashMap<String, Object>();
        primaryKeys.put("proposalId", proposalNumber);
        InstitutionalProposal currentProposal = (InstitutionalProposal) businessObjectService.findByPrimaryKey(InstitutionalProposal.class, primaryKeys);
        return currentProposal;
    }

    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#updateDisclosureDetails(org.kuali.kra.coi.CoiDisclProject)
     */
    public void updateDisclosureDetails(CoiDisclProject coiDisclProject) {
        // When creating a disclosure. the detail will be created at first
        // TODO : what if FE is deactivate
        Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureId", coiDisclProject.getCoiDisclosureId());
        fieldValues.put("moduleCode", CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        fieldValues.put("moduleItemKey", coiDisclProject.getCoiProjectId());
        List<String> disclEntityNumbers = new ArrayList<String>();
        List<CoiDiscDetail> activeDetails = new ArrayList<CoiDiscDetail>();
        List<CoiDiscDetail> coiDiscDetails = (List<CoiDiscDetail>) businessObjectService.findMatching(CoiDiscDetail.class, fieldValues);
        String personId = GlobalVariables.getUserSession().getPrincipalId();
        if (!StringUtils.equals(personId, coiDisclProject.getCoiDisclosure().getPersonId())) {
            personId = coiDisclProject.getCoiDisclosure().getPersonId();
        }
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(personId, true);
        for (CoiDiscDetail coiDiscDetail : coiDiscDetails) {
            getCurrentFinancialEntity(coiDiscDetail);
            if (coiDiscDetail.getPersonFinIntDisclosure().isStatusActive()) {
                if (!disclEntityNumbers.contains(coiDiscDetail.getPersonFinIntDisclosure().getEntityNumber())) {
                    disclEntityNumbers.add(coiDiscDetail.getPersonFinIntDisclosure().getEntityNumber());
                }
                activeDetails.add(coiDiscDetail);
            }            
        }
        checkToAddNewFinancialEntity(financialEntities, activeDetails, disclEntityNumbers, coiDisclProject.getCoiProjectId().toString(), coiDisclProject.getCoiDisclosure(), coiDisclProject.getDisclosureEventType(), coiDisclProject.getCoiProjectId());

        coiDisclProject.setCoiDiscDetails(activeDetails);

    }

    /*
     * utility method to create new coidiscl object
     */
    private CoiDiscDetail createNewCoiDiscDetail(CoiDisclosure coiDisclosure,PersonFinIntDisclosure personFinIntDisclosure, String moduleItemKey, String projectIdFk, String projectType) {
        CoiDiscDetail disclosureDetail = new CoiDiscDetail(personFinIntDisclosure);
        disclosureDetail.setModuleItemKey(moduleItemKey);
        disclosureDetail.setProjectIdFk(projectIdFk);
        disclosureDetail.setProjectType(projectType);
        // TODO : this is how coeus set. not sure ?
        disclosureDetail.setModuleCode(eventModuleMap.get(projectType));
        coiDisclosure.initCoiDisclosureNumber();
        disclosureDetail.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        disclosureDetail.setSequenceNumber(coiDisclosure.getSequenceNumber());
        disclosureDetail.setDescription("Sample Description"); // this is from coeus.
        return disclosureDetail;
        
    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getProtocols(java.lang.String)
     */
    public List<Protocol> getProtocols(String personId) {
        
        List<Protocol> protocols = new ArrayList<Protocol>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
//        fieldValues.put("protocolPersonRoleId", "PI");
        List<ProtocolPerson> protocolPersons = (List<ProtocolPerson>) businessObjectService.findMatching(ProtocolPerson.class, fieldValues);
        for (ProtocolPerson protocolPerson : protocolPersons) {
            if (protocolPerson.getProtocol().isActive() && isProtocolDisclosurable(protocolPerson.getProtocol()) && !isProjectReported(protocolPerson.getProtocol().getProtocolNumber(), CoiDisclosureEventType.IRB_PROTOCOL, protocolPerson.getPersonId())) {
                protocols.add(protocolPerson.getProtocol());
            }
        }
        return protocols;
        
    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getProposals(java.lang.String)
     */
    public List<DevelopmentProposal> getProposals(String personId) {
        
        List<DevelopmentProposal> proposals = new ArrayList<DevelopmentProposal>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
//        fieldValues.put("proposalPersonRoleId", "PI");
        List<ProposalPerson> proposalPersons = (List<ProposalPerson>) businessObjectService.findMatching(ProposalPerson.class, fieldValues);
        for (ProposalPerson proposalPerson : proposalPersons) {
            if (isProposalDisclosurable(proposalPerson.getDevelopmentProposal()) && !isProjectReported(proposalPerson.getDevelopmentProposal().getProposalNumber(), CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, proposalPerson.getPersonId())) {
            // TODO : condition to be implemented              
                proposals.add(proposalPerson.getDevelopmentProposal());
            }
        }
        return proposals;
        
    }
 
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getInstitutionalProposals(java.lang.String)
     */
    public List<InstitutionalProposal> getInstitutionalProposals(String personId) {
        
        List<InstitutionalProposal> proposals = new ArrayList<InstitutionalProposal>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
//        fieldValues.put("proposalPersonRoleId", "PI");
        List<InstitutionalProposalPerson> proposalPersons = (List<InstitutionalProposalPerson>) businessObjectService.findMatching(InstitutionalProposalPerson.class, fieldValues);
        for (InstitutionalProposalPerson proposalPerson : proposalPersons) {
            if (isInstitutionalProposalDisclosurable(proposalPerson.getInstitutionalProposal()) && !isProjectReported(proposalPerson.getInstitutionalProposal().getProposalNumber(), CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, proposalPerson.getPersonId())) {
            // TODO : condition to be implemented
                proposals.add(proposalPerson.getInstitutionalProposal());
            }
        }
        return proposals;
        
    }
 
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getAwards(java.lang.String)
     */
    public List<Award> getAwards(String personId) {
        
        List<Award> awards = new ArrayList<Award>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
//        fieldValues.put("roleCode", "PI");
        List<AwardPerson> awardPersons = (List<AwardPerson>) businessObjectService.findMatchingOrderBy(AwardPerson.class, fieldValues, "awardNumber", true);
        for (AwardPerson awardPerson : awardPersons) {
            if (isCurrentAward(awardPerson.getAward()) && isAwardDisclosurable(awardPerson.getAward()) && !isProjectReported(awardPerson.getAward().getAwardNumber(), CoiDisclosureEventType.AWARD, awardPerson.getPersonId())) {
                awards.add(awardPerson.getAward());
            }
        }
        return awards;
        
    }
 
    private boolean isCurrentAward(Award award) {
        Award currentAward = getAward(award.getAwardNumber());
        return award.getAwardId().equals(currentAward.getAwardId());
    }
    
    /*
     * check if project has been reported in the current report year
     */
    private boolean isProjectReported(String projectId, String projectType, String personId) {
        boolean isDisclosed = false;
        // TODO : is checking matching moduleitemkey & expiration date sufficient
        // TODO : uncomment this, so the criteria can be applied
//        HashMap<String, Object> fieldValues = new HashMap<String, Object>();
//        if (StringUtils.equals(CoiDisclosureEventType.AWARD, projectType)) {
//            // all award numbers in that hierarchy.  so any award in that hierarchy is reported, then the others
//            // don't have to report.
//            fieldValues.put("moduleItemKey", getAwardNumbersForHierarchy(projectId));
//        } else {
//            fieldValues.put("moduleItemKey", projectId);
//        }
//        fieldValues.put("projectType", projectType);
//        List<CoiDiscDetail> discDetails = (List<CoiDiscDetail>)businessObjectService.findMatching(CoiDiscDetail.class, fieldValues);
//        Date currentDate = dateTimeService.getCurrentSqlDateMidnight();
//        for (CoiDiscDetail discDetail : discDetails) {
//            if (StringUtils.equals(discDetail.getCoiDisclosure().getPersonId(), personId) && discDetail.getCoiDisclosure().getExpirationDate().after(currentDate)) {
//                isDisclosed = true;
//                break;
//            }
//        }
        return isDisclosed;
    }

    /*
     * get the awardnumbers in the award hierarchy.  projectId can be any awardnumber in that hierarchy 
     */
    private List<String> getAwardNumbersForHierarchy(String projectId) {
        List<String> awardNumbers = new ArrayList<String>();
        awardNumbers.add(projectId);
        HashMap<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("awardNumber", projectId);
        List<AwardHierarchy> awardHierarchies  = (List<AwardHierarchy>)businessObjectService.findMatching(AwardHierarchy.class, fieldValues);
        if (CollectionUtils.isNotEmpty(awardHierarchies)) {
            fieldValues.clear();
            fieldValues.put("rootAwardNumber", awardHierarchies.get(0).getRootAwardNumber());
            awardHierarchies  = (List<AwardHierarchy>)businessObjectService.findMatching(AwardHierarchy.class, fieldValues);
            for (AwardHierarchy awardHierarchy : awardHierarchies) {
                awardNumbers.add(awardHierarchy.getAwardNumber());                
            }
            
        }
        return awardNumbers;
        
    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#versionCoiDisclosure()
     */
    public CoiDisclosure versionCoiDisclosure() throws VersionException {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) businessObjectService.findMatchingOrderBy(CoiDisclosure.class, fieldValues, "sequenceNumber", false);
        CoiDisclosure newDisclosure = null;
        if (CollectionUtils.isNotEmpty(disclosures)) {
            newDisclosure = versioningService.createNewVersion(disclosures.get(0));
            newDisclosure.setCoiDisclProjects(null);
            newDisclosure.setCoiDiscDetails(null);
            newDisclosure.setCoiDisclosureAttachments(null);
            newDisclosure.setCoiDisclosureNotepads(null);
            newDisclosure.setCurrentDisclosure(false);
            newDisclosure.setCertificationTimestamp(null);
            newDisclosure.setDisclosureDispositionCode(CoiDisclosure.DISPOSITION_PENDING);
            newDisclosure.setDisclosureStatusCode(CoiDisclosureStatus.DISCLOSURE_PENDING);
            newDisclosure.setExpirationDate(new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), 365).getTime()));
        }

        return newDisclosure;
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

    public void setVersioningService(VersioningService versioningService) {
        this.versioningService = versioningService;
    }

    /**
     * 
     * This method is to check is award is active for disclosure
     * @param award
     * @return
     */
    private boolean isAwardDisclosurable(Award award) {
        List<String> params = new ArrayList<String>();
        try {
            params = parameterService.getParameterValues(AwardDocument.class, AWARD_DISCLOSE_STATUS_CODES);
        } catch (Exception e) {
        }
        if (params.isEmpty()) {
            // TODO : what if param is not set or not set properly ?
            params.add("1");
        }
        return params.contains(award.getStatusCode().toString()) && isSponsorForDisclosesure(ProposalDevelopmentDocument.class, award.getSponsorCode(), SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE, ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE);
   
    }
    
    /*
     * check if PD is active for disclosure
     */
    private boolean isProposalDisclosurable(DevelopmentProposal proposal) {
        List<String> params = new ArrayList<String>();
        try {
            params = parameterService.getParameterValues(ProposalDevelopmentDocument.class, PROPOSAL_DISCLOSE_STATUS_CODES);
        } catch (Exception e) {
            
        }
        if (params.isEmpty()) {
            // TODO : what if param is not set or not set properly ?
            params.add("1");
        }
        return params.contains(proposal.getProposalTypeCode()) && isSponsorForDisclosesure(ProposalDevelopmentDocument.class, proposal.getSponsorCode(), SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE, ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE);
   
    }

    /*
     * check if institutional proposal is active for disclosure
     */
    private boolean isInstitutionalProposalDisclosurable(InstitutionalProposal proposal) {
        List<String> params = new ArrayList<String>();
        try {
            params = parameterService.getParameterValues(InstitutionalProposalDocument.class, INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES);
        } catch (Exception e) {
            
        }
        if (params.isEmpty()) {
            // TODO : what if param is not set or not set properly ?
            params.add("1");
        }
        return params.contains(proposal.getStatusCode().toString()) && isSponsorForDisclosesure(ProposalDevelopmentDocument.class, proposal.getSponsorCode(), SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE, ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE);
   
    }
    
    /*
     * check if protocol is active for disclosure
     */
    private boolean isProtocolDisclosurable(Protocol protocol) {
        List<String> params = new ArrayList<String>();
        try {
            params = parameterService.getParameterValues(ProtocolDocument.class, PROTOCOL_DISCLOSE_STATUS_CODES);
        } catch (Exception e) {
            
        }
        if (params.isEmpty()) {
            // TODO : what if param is not set or not set properly ?
            params.add("100");
        }
        return params.contains(protocol.getProtocolStatusCode()) && isProtocolFundedByActiveSponsor(protocol);
   
    }

    /*
     * check if protocol funding source sponsor is in disclose list
     */
    private boolean isProtocolFundedByActiveSponsor(Protocol protocol) {
         boolean isActive = false;
         for (ProtocolFundingSource fundingSource : protocol.getProtocolFundingSources()) {
             if (fundingSource.isSponsorFunding() && isSponsorForDisclosesure(ProtocolDocument.class, fundingSource.getFundingSourceNumber(), SPONSORS_FOR_PROTOCOL_DISCLOSE, ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE)) {
                 isActive = true;
                 break;
             }
         }
         return isActive;
         
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    private boolean isSponsorForDisclosesure(Class clazz, String sponsorCode, String paramName, String paramNameAllSponsor) {
        return isAllSponsorActiveForDisclose(clazz, paramNameAllSponsor) || isSponsorHierarchyActiveForDisclose(clazz, sponsorCode, paramName);    
    }
    
    /*
     * check if sponsorcode in in sponsor hierarchy
     */
    private boolean isSponsorHierarchyActiveForDisclose(Class clazz, String sponsorCode, String paramName) {
        
        List<String> params = new ArrayList<String>();
        try {
            params = parameterService.getParameterValues(clazz, paramName);
        } catch (Exception e) {
            
        }
        List<String> sponsorCodes = new ArrayList<String>();
        for (String hierarchyName : params) {
            /*
             * Changed this to use the bo service instead of the sponsor hierarchy service
             * which uses the sponsorHierarchyDAO which is not transactional
             */
            List<SponsorHierarchy> sponsors = getAllSponsors(hierarchyName);
            for (SponsorHierarchy sponsor : sponsors) {
                sponsorCodes.add(sponsor.getSponsorCode());
            }
            
        }
   
        if (sponsorCodes.isEmpty()) {
            return true;
        } else {
            return sponsorCodes.contains(sponsorCode);
        }
    }
    
    protected List<SponsorHierarchy> getAllSponsors(String hierarchyName) {
        Map<String, String> criteria = new HashMap();
        criteria.put("hierarchyName", hierarchyName);
        List<SponsorHierarchy> sponsors = (List<SponsorHierarchy>) businessObjectService.findMatching(SponsorHierarchy.class, criteria);
        return sponsors;
    }
    /*
     * check if the all sponsor active for disclosure flag is true
     */
    private boolean isAllSponsorActiveForDisclose(Class clazz,  String paramName) {

        
        boolean isAllSponsors = false;
        try {
            isAllSponsors = parameterService.getIndicatorParameter(clazz, paramName);
        } catch (Exception e) {
            
        }
   
        return isAllSponsors;
    }


    public void setSponsorHierarchyDao(SponsorHierarchyDao sponsorHierarchyDao) {
        this.sponsorHierarchyDao = sponsorHierarchyDao;
    }

    public boolean isReporter() {
        // TODO : this is the initial implementation to check if a user need to report
        // more condition may be added
        // how to decide whether user has manual projects to report ?
        String personId = GlobalVariables.getUserSession().getPrincipalId();
        return hasExistingCoiDisclosure() || CollectionUtils.isNotEmpty(getProtocols(personId)) || CollectionUtils.isNotEmpty(getProposals(personId))
                || CollectionUtils.isNotEmpty(getAwards(personId)) || CollectionUtils.isNotEmpty(getInstitutionalProposals(personId));
    }
    
    private boolean hasExistingCoiDisclosure() {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());

        return businessObjectService.countMatching(CoiDisclosure.class, fieldValues) > 0;

    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getMasterDisclosureDetail(org.kuali.kra.coi.CoiDisclosure)
     */
    public MasterDisclosureBean getMasterDisclosureDetail(CoiDisclosure coiDisclosure) {
        MasterDisclosureBean masterDisclosureBean = new MasterDisclosureBean();
        String moduleItemKey = Constants.EMPTY_STRING;
        String projectType = Constants.EMPTY_STRING;
        CoiDisclosureProjectBean disclosureProjectBean = null;
        Collections.sort(coiDisclosure.getCoiDiscDetails());
        for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
            if (!StringUtils.equals(projectType, coiDiscDetail.getProjectType()) || !StringUtils.equals(moduleItemKey, coiDiscDetail.getModuleItemKey())) {
                disclosureProjectBean = getCoiDisclosureProjectBean(coiDiscDetail);
                masterDisclosureBean.addProject(disclosureProjectBean, coiDiscDetail.getProjectType());
                if (!StringUtils.equals(projectType, coiDiscDetail.getProjectType())) {
                    projectType = coiDiscDetail.getProjectType();
                }
                moduleItemKey = coiDiscDetail.getModuleItemKey();
            }
            disclosureProjectBean.getProjectDiscDetails().add(coiDiscDetail);            
        }
        
        setupDisclosures(masterDisclosureBean, coiDisclosure);
        return masterDisclosureBean;
    }
    
    /*
     * set up a list of disclosures that will be rendered in 'Disclosures' panel for master disclosure
     */
    private void setupDisclosures(MasterDisclosureBean masterDisclosureBean, CoiDisclosure coiDisclosure) {
        List<CoiDisclosureHistory> disclosureHistories = getDisclosureHistory(coiDisclosure.getCoiDisclosureNumber());
        CoiDisclosureHistory disclosureHistoryForView = getDisclosureHistoryForSelectedDiscl(disclosureHistories, coiDisclosure);
        disclosureHistoryForView.refreshReferenceObject("coiDisclosure");
        for (CoiDisclosureHistory disclosureHistory : disclosureHistories) {
            if (disclosureHistory.getCoiDisclosureHistoryId().compareTo(disclosureHistoryForView.getCoiDisclosureHistoryId()) <= 0) {
                // only list the history list up to the history associated to the disclosure selected
                // may need to revise this 
                CoiDiscDetail coiDiscDetail = getCurrentProjectDetail(disclosureHistory.getCoiDisclosure());
                CoiDisclosureProjectBean disclosureProjectBean = getCoiDisclosureProjectBean(coiDiscDetail);
                disclosureProjectBean.getProjectDiscDetails().add(coiDiscDetail);
                disclosureProjectBean.setApprovalDate(new Date(disclosureHistory.getUpdateTimestamp().getTime()));
                if (disclosureHistory.getCoiDisclosure() == null) {
                    // immediatly after approve may need this
                    disclosureHistory.refreshReferenceObject("coiDisclosure");
                }
                // immediatly after approve may need this.  pretty annoying.  TODO : ?
                disclosureHistory.getCoiDisclosure().refreshReferenceObject("coiDisclosureEventType");
                disclosureHistory.getCoiDisclosure().refreshReferenceObject("coiDisclosureStatus");
                disclosureHistory.getCoiDisclosure().refreshReferenceObject("coiDispositionStatus");
                disclosureProjectBean.setCoiDisclosure(disclosureHistory.getCoiDisclosure());
                masterDisclosureBean.getAllProjects().add(disclosureProjectBean);
            }
        }
    }
    
    /*
     * get the approved disclosure history record for the associated disclosure
     */
    private CoiDisclosureHistory getDisclosureHistoryForSelectedDiscl(List<CoiDisclosureHistory> disclosureHistories, CoiDisclosure coiDisclosure) {
        for (CoiDisclosureHistory disclosureHistory : disclosureHistories) {
            if (disclosureHistory.getCoiDisclosureId().equals(coiDisclosure.getCoiDisclosureId())) {
                return disclosureHistory;
            }
        }
        return null;
    }
    
    /*
     * get the approved disclosure history for the specified disclosurenumber
     */
    @SuppressWarnings({ "unused", "unchecked" })
    protected List<CoiDisclosureHistory> getDisclosureHistory(String coiDisclosureNumber) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureNumber", coiDisclosureNumber);
        fieldValues.put("disclosureDispositionStatus", CoiDispositionStatus.APPROVED);
        return (List<CoiDisclosureHistory>) businessObjectService.findMatchingOrderBy(CoiDisclosureHistory.class, fieldValues,
                "sequenceNumber", true);

    }

    /*
     * get the first disc detail for this disclosure.  if orginaldisclosureid is not null, then it is from previous master discl
     */
    protected CoiDiscDetail getCurrentProjectDetail(CoiDisclosure coiDisclosure) {
        if (CollectionUtils.isEmpty(coiDisclosure.getCoiDiscDetails())) {
            coiDisclosure.refreshReferenceObject("coiDiscDetails");
        }
        for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
            if (coiDiscDetail.getOriginalCoiDisclosure() == null || coiDiscDetail.getCoiDisclosureId().equals(coiDiscDetail.getOriginalCoiDisclosureId())) {
                if (coiDiscDetail.getCoiDisclosure() == null) {
                    // immediatly after approve may need this
                    coiDiscDetail.refreshReferenceObject("coiDisclosure");
                }
                return coiDiscDetail;
            }

        }
        return null;
    }
    
    /*
     * set up form bean for each project
     */
    protected CoiDisclosureProjectBean getCoiDisclosureProjectBean(CoiDiscDetail coiDiscDetail) {
        CoiDisclosureProjectBean disclosureProjectBean = new CoiDisclosureProjectBean();
        if (ObjectUtils.isNotNull(coiDiscDetail)) {
            if (coiDiscDetail.isManualEvent()) {
                disclosureProjectBean.setDisclosureProject(getCoiDisclProject(coiDiscDetail));
            } else {
                disclosureProjectBean.setDisclosureProject(getEventBo(coiDiscDetail, coiDiscDetail.getProjectIdFk()));
            }
        } 
        return disclosureProjectBean;
    }
    
    /*
     * retrieve manual project
     */
    private CoiDisclProject getCoiDisclProject(CoiDiscDetail coiDiscDetail) {
        Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureNumber", coiDiscDetail.getCoiDisclosureNumber());
        // this is unique
        fieldValues.put("coiProjectId", coiDiscDetail.getModuleItemKey());
        List<CoiDisclProject> coiDisclProjects = (List<CoiDisclProject>) businessObjectService.findMatching(CoiDisclProject.class, fieldValues);
        if (CollectionUtils.isNotEmpty(coiDisclProjects)) {
            return coiDisclProjects.get(0);
        } else {
            return null;
        }
    }
}
