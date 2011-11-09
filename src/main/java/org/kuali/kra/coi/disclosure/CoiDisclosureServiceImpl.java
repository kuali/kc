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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDiscDetail;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
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
import org.kuali.rice.kns.util.GlobalVariables;

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
    private static Map<String, String> moduleEventMap = new HashMap<String, String>();
    static {
        moduleEventMap.put("11", "1");
        moduleEventMap.put("1", "2");
        moduleEventMap.put("12", "3");
        moduleEventMap.put("15", "4");
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
        // TODO : method too long need refactor.  this method should only for annual.  clean it. 
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
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject("3", protocol,
                new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        protocol.getProtocolNumber());
                disclosureDetail.setProjectType(CoiDisclProject.PROTOCOL_EVENT);
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
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject("1", proposal, new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        proposal.getProposalNumber());
                // disclosureDetail.setProtocol(proposal);
                disclosureDetail.setProjectType(CoiDisclProject.PROPOSAL_EVENT);
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
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject("4", proposal,
                new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        proposal.getProposalNumber());
                disclosureDetail.setProjectType(CoiDisclProject.INSTITUTIONAL_PROPOSAL_EVENT);
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
        for (Award award : awards) {
            CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject("2", award,
                new ArrayList<CoiDiscDetail>());
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        award.getAwardNumber());
//                disclosureDetail.setProtocol(proposal);
                disclosureDetail.setProjectType(CoiDisclProject.AWARD_EVENT);
                disclosureDetails.add(disclosureDetail);
                coiDisclEventProject.getCoiDiscDetails().add(disclosureDetail);
            }
            disclEventProjects.add(coiDisclEventProject);
        }
        
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
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                    moduleItemKey);
            disclosureDetail.setProjectType(moduleEventMap.get(coiDisclosure.getModuleCode()));
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
            CoiDiscDetail disclosureDetail =createNewCoiDiscDetail(coiDisclProject.getCoiDisclosure(), personFinIntDisclosure, coiDisclProject.getCoiProjectId());
            disclosureDetails.add(disclosureDetail);
            disclosureDetail.setProjectType(coiDisclProject.getDisclosureEventType());
        }
        coiDisclProject.setCoiDiscDetails(disclosureDetails);

    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#updateDisclosureDetails(org.kuali.kra.coi.CoiDisclosure)
     */
    public void updateDisclosureDetails(CoiDisclosure coiDisclosure) {
        // When creating a disclosure. the detail will be created at first
        // TODO : this is for protocol now
        Collections.sort(coiDisclosure.getCoiDiscDetails());
        String projectType = Constants.EMPTY_STRING;
        String moduleItemKey = Constants.EMPTY_STRING;
        List<CoiDisclEventProject> disclEventProjects = new ArrayList<CoiDisclEventProject>();
        CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);

        List<String> disclEntityNumbers = new ArrayList<String>();
        if (!coiDisclosure.isManualEvent()) {
            for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDiscDetails()) {
                if (!StringUtils.equals(projectType, coiDiscDetail.getProjectType()) || !StringUtils.equals(moduleItemKey, coiDiscDetail.getModuleItemKey())) {
                    if (StringUtils.isNotBlank(projectType) && coiDisclEventProject.getEventProjectBo() != null) {
                        // event bo is found in table. this is especially for PD to check null bo
                        checkToAddNewFinancialEntity(financialEntities, coiDisclEventProject.getCoiDiscDetails(), disclEntityNumbers, coiDisclEventProject.getProjectId(), coiDisclosure, coiDiscDetail.getProjectType());
                        disclEventProjects.add(coiDisclEventProject);
                    }
                    moduleItemKey = coiDiscDetail.getModuleItemKey();
                    projectType = coiDiscDetail.getProjectType();
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
            if (coiDisclEventProject.getEventProjectBo() != null) {
                checkToAddNewFinancialEntity(financialEntities, coiDisclEventProject.getCoiDiscDetails(), disclEntityNumbers, coiDisclEventProject.getProjectId(), coiDisclosure, projectType);
                disclEventProjects.add(coiDisclEventProject); // the last project
            }

            coiDisclosure.setCoiDisclEventProjects(disclEventProjects);
            // TODO : single project
            if (!coiDisclosure.isAnnualEvent() && !disclEventProjects.isEmpty()) {
                coiDisclosure.setCoiDiscDetails(disclEventProjects.get(0).getCoiDiscDetails());
            }
        }
    }


    /*
     * check if there is new FE added since last update. if there is, then set up the coi disclose FE
     */
    private void checkToAddNewFinancialEntity(List<PersonFinIntDisclosure> financialEntities, List<CoiDiscDetail> coiDiscDetails, List<String> disclEntityNumbers, String projectId, CoiDisclosure coiDisclosure, String projectType) {
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            if (!disclEntityNumbers.contains(personFinIntDisclosure.getEntityNumber())) {
                coiDiscDetails.add(createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure, projectId));
                coiDiscDetails.get(coiDiscDetails.size() - 1).setProjectType(projectType);
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
            coiDisclEventProject = new CoiDisclEventProject("3", protocol, new ArrayList<CoiDiscDetail>());
            coiDisclosure.setEventBo(protocol);
        }
        else if (coiDisclosure.isProposalEvent()) {
            DevelopmentProposal proposal = getDevelopmentProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject("1", proposal, new ArrayList<CoiDiscDetail>());
            coiDisclosure.setEventBo(proposal);
        } else if (coiDisclosure.isInstitutionalProposalEvent()) {
            InstitutionalProposal proposal = getInstitutionalProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject("4", proposal, new ArrayList<CoiDiscDetail>());
            coiDisclosure.setEventBo(proposal);
        } 
        else if (coiDisclosure.isAwardEvent()) {
            // TODO : for award
            Award award = getAward(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject("2", award, new ArrayList<CoiDiscDetail>());
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
            coiDisclEventProject = new CoiDisclEventProject("3", protocol, new ArrayList<CoiDiscDetail>());
        }
        else if (coiDiscDetail.isProposalEvent()) {
            DevelopmentProposal proposal = getDevelopmentProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject("1", proposal, new ArrayList<CoiDiscDetail>());
        } else if (coiDiscDetail.isInstitutionalProposalEvent()) {
            InstitutionalProposal proposal = getInstitutionalProposal(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject("4", proposal, new ArrayList<CoiDiscDetail>());
        } else  {
            // TODO : for award
            Award award = getAward(coiDiscDetail.getModuleItemKey());
            coiDisclEventProject = new CoiDisclEventProject("2", award, new ArrayList<CoiDiscDetail>());
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
        primaryKeys.put("proposalNumber", proposalNumber);
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
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        for (CoiDiscDetail coiDiscDetail : coiDiscDetails) {
            getCurrentFinancialEntity(coiDiscDetail);
            if (coiDiscDetail.getPersonFinIntDisclosure().isStatusActive()) {
                if (!disclEntityNumbers.contains(coiDiscDetail.getPersonFinIntDisclosure().getEntityNumber())) {
                    disclEntityNumbers.add(coiDiscDetail.getPersonFinIntDisclosure().getEntityNumber());
                }
                activeDetails.add(coiDiscDetail);
            }            
        }
        checkToAddNewFinancialEntity(financialEntities, activeDetails, disclEntityNumbers, coiDisclProject.getCoiProjectId().toString(), coiDisclProject.getCoiDisclosure(), coiDisclProject.getDisclosureEventType());

        coiDisclProject.setCoiDiscDetails(activeDetails);

    }

    /*
     * utility method to create new coidiscl object
     */
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
            if (protocolPerson.getProtocol().isActive() && isProtocolDisclosurable(protocolPerson.getProtocol()) && !isProjectReported(protocolPerson.getProtocol().getProtocolNumber(), CoiDisclProject.PROTOCOL_EVENT)) {
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
            if (isProposalDisclosurable(proposalPerson.getDevelopmentProposal()) && !isProjectReported(proposalPerson.getDevelopmentProposal().getProposalNumber(), CoiDisclProject.PROPOSAL_EVENT)) {
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
            if (isInstitutionalProposalDisclosurable(proposalPerson.getInstitutionalProposal()) && !isProjectReported(proposalPerson.getInstitutionalProposal().getProposalNumber(), CoiDisclProject.INSTITUTIONAL_PROPOSAL_EVENT)) {
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
        List<AwardPerson> awardPersons = (List<AwardPerson>) businessObjectService.findMatching(AwardPerson.class, fieldValues);
        for (AwardPerson awardPerson : awardPersons) {
            if (isAwardDisclosurable(awardPerson.getAward()) && !isProjectReported(awardPerson.getAward().getAwardNumber(), CoiDisclProject.AWARD_EVENT)) {
                awards.add(awardPerson.getAward());
            }
        }
        return awards;
        
    }
 
    /*
     * check if project has been reported in the current report year
     */
    private boolean isProjectReported(String projectId, String projectType) {
        boolean isDisclosed = false;
        // TODO : is checking matching moduleitemkey & expiration date sufficient
//        HashMap<String, Object> fieldValues = new HashMap<String, Object>();
//        fieldValues.put("moduleItemKey", projectId);
//        fieldValues.put("projectType", projectType);
//        List<CoiDiscDetail> discDetails = (List<CoiDiscDetail>)businessObjectService.findMatching(CoiDiscDetail.class, fieldValues);
//        Date currentDate = dateTimeService.getCurrentSqlDateMidnight();
//        for (CoiDiscDetail discDetail : discDetails) {
//            if (discDetail.getCoiDisclosure().getExpirationDate().after(currentDate)) {
//                isDisclosed = true;
//                break;
//            }
//        }
        return isDisclosed;
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
            Iterator sponsorHierarchyList = sponsorHierarchyDao.getAllSponsors(hierarchyName);
            while (sponsorHierarchyList.hasNext()) {
                sponsorCodes.add(((Object[])sponsorHierarchyList.next())[0].toString());
            }
            
        }
   
        if (sponsorCodes.isEmpty()) {
            return true;
        } else {
            return sponsorCodes.contains(sponsorCode);
        }
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

}
