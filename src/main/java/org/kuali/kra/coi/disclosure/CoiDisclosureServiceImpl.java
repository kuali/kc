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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.kuali.kra.coi.CoiUserRole;
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureDao;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.protocol.IacucProtocolType;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSource;
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
import org.kuali.kra.questionnaire.answer.Answer;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;
import org.kuali.kra.util.DateUtils;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

public class CoiDisclosureServiceImpl implements CoiDisclosureService {

    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;
    private FinancialEntityService financialEntityService;
    private ProtocolFinderDao protocolFinderDao;
    private VersioningService versioningService;
    private ParameterService parameterService;
    private DateTimeService dateTimeService;
    private CoiDisclosureDao coiDisclosureDao;

    private static final String PROTOCOL_DISCLOSE_STATUS_CODES = "PROTOCOL_DISCLOSE_STATUS_CODES";
    private static final String IACUC_DISCLOSE_STATUS_CODES = "IACUC_DISCLOSE_STATUS_CODES";
    private static final String PROPOSAL_DISCLOSE_STATUS_CODES = "PROPOSAL_DISCLOSE_STATUS_CODES";
    private static final String INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES = "INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES";
    private static final String AWARD_DISCLOSE_STATUS_CODES = "AWARD_DISCLOSE_STATUS_CODES";
    private static final String SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE = "SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE";
    private static final String SPONSORS_FOR_PROTOCOL_DISCLOSE = "SPONSORS_FOR_PROTOCOL_DISCLOSE";
    private static final String ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE = "ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE";
    private static final String ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE = "ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE";
    private static final String ALL_SPONSORS_FOR_IACUC_PROTOCOL_DISCLOSE = "ALL_SPONSORS_FOR_IACUC_PROTOCOL_DISCLOSE";
    private static Map<String, String> eventModuleMap = new HashMap<String, String>();
    static {
        eventModuleMap.put(CoiDisclosureEventType.AWARD, CoeusModule.AWARD_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.IRB_PROTOCOL, CoeusModule.IRB_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.IACUC_PROTOCOL, CoeusModule.IACUC_PROTOCOL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, CoeusModule.INSTITUTIONAL_PROPOSAL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.MANUAL_AWARD, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.MANUAL_IACUC_PROTOCOL, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
        eventModuleMap.put(CoiDisclosureEventType.TRAVEL, CoiDisclosure.MANUAL_DISCL_MODULE_CODE);
    }
    private static final String MODULE_ITEM_CODE = "moduleItemCode";
    private static final String MODULE_ITEM_KEY = "moduleItemKey";
    private static final String MODULE_SUB_ITEM_KEY = "moduleSubItemKey";

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

    /*
    public void setDisclProjectForSave(CoiDisclosure coiDisclosure) {
        //List<CoiDisclProject> coiDisclProjects = coiDisclosure.getCoiDisclProjects();
        List<CoiDisclProject> coiDisclProjects = null;
        if (CollectionUtils.isEmpty(coiDisclProjects)) {
            coiDisclProjects = new ArrayList<CoiDisclProject>();
        }  
        if (CollectionUtils.isNotEmpty(coiDisclosure.getCoiDisclEventProjects())) {
            for (CoiDisclEventProject coiDisclEventProject : coiDisclosure.getCoiDisclEventProjects()) {
                coiDisclProjects.add(createDisclProject(coiDisclosure, coiDisclEventProject.getProjectId(), "", coiDisclEventProject.getCoiDiscDetails()));
            }
        } else {
            coiDisclProjects.add(createDisclProject(coiDisclosure, "", "", null));
        }
        
        coiDisclosure.setCoiDisclProjects(coiDisclProjects);
    }
    */

    public void setDisclProjectForSave(CoiDisclosure coiDisclosure, MasterDisclosureBean masterDisclosureBean) {
 //         List<CoiDisclProject> coiDisclProjects = coiDisclosure.getCoiDisclProjects();
//        if (CollectionUtils.isEmpty(coiDisclProjects)) {
//            coiDisclProjects = new ArrayList<CoiDisclProject>();
//        }
          coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
            // reset disclosure's disclprojects from master disclosure bean
        for (List<CoiDisclosureProjectBean> projects : masterDisclosureBean.getProjectLists()) {
            for (CoiDisclosureProjectBean coiDisclProject : projects) {
               // coiDisclProjects.add(createDisclProject(coiDisclosure, coiDisclProject.getProjectId(), coiDisclProject.getProjectName()));
                coiDisclosure.getCoiDisclProjects().add(coiDisclProject.getCoiDisclProject());
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
        List<CoiDisclProject> coiDisclProjects = new ArrayList<CoiDisclProject>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        if (coiDisclosure.isProtocolEvent() || coiDisclosure.isAnnualEvent()) {
            initProtocols(coiDisclProjects, financialEntities, coiDisclosure);
        }
        if (coiDisclosure.isIacucProtocolEvent() || coiDisclosure.isAnnualEvent()) {
            initIacucProtocols(coiDisclProjects, financialEntities, coiDisclosure);
        } 
        if (coiDisclosure.isProposalEvent() || coiDisclosure.isAnnualEvent()) {
            initProposals(coiDisclProjects, financialEntities, coiDisclosure);
        } 
        if (coiDisclosure.isInstitutionalProposalEvent() || coiDisclosure.isAnnualEvent()) {
            initInstitutionalProposals(coiDisclProjects, financialEntities, coiDisclosure);
        } 
        if (coiDisclosure.isAwardEvent() || coiDisclosure.isAnnualEvent()) {
            initAwards(coiDisclProjects, financialEntities, coiDisclosure);
        } 
        coiDisclosure.setCoiDisclProjects(coiDisclProjects);
    }
    
    /*
     * set up protocols, that need disclosure, with FE relationship
     */
    private void initProtocols(List<CoiDisclProject> coiDisclProjects, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {      
        List<Protocol> protocols = getProtocols(GlobalVariables.getUserSession().getPrincipalId());
        for (Protocol protocol : protocols) {
            CoiDisclProject coiDisclProject = createNewCoiDisclProject(coiDisclosure, CoiDisclosureEventType.IRB_PROTOCOL);
            coiDisclProject.setProtocolType(protocol.getProtocolType());
            coiDisclProject.setCoiProjectId(protocol.getProtocolId().toString()); //Project Id
            coiDisclProject.setModuleItemKey(protocol.getProtocolNumber()); //Module Item Key
            coiDisclProject.setCoiProjectTitle(protocol.getTitle()); //Project Title
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        protocol.getProtocolNumber(), protocol.getProtocolId().toString(), CoiDisclosureEventType.IRB_PROTOCOL);
                coiDisclProject.getCoiDiscDetails().add(disclosureDetail);
            }
            coiDisclProjects.add(coiDisclProject);
        }     
    }
    
    /*
     * set up IACUC protocols, that need disclosure, with FE relationship
     */
    private void initIacucProtocols(List<CoiDisclProject> coiDisclProjects, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {      
        List<IacucProtocol> protocols = getIacucProtocols(GlobalVariables.getUserSession().getPrincipalId());
        for (IacucProtocol protocol : protocols) {
            CoiDisclProject coiDisclProject = createNewCoiDisclProject(coiDisclosure, CoiDisclosureEventType.IRB_PROTOCOL);
            coiDisclProject.setIacucProtocolType((IacucProtocolType)protocol.getProtocolType());
            coiDisclProject.setCoiProjectId(protocol.getProtocolId().toString()); //Project Id
            coiDisclProject.setModuleItemKey(protocol.getProtocolNumber()); //Module Item Key
            coiDisclProject.setCoiProjectTitle(protocol.getTitle()); //Project Title
            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        protocol.getProtocolNumber(), protocol.getProtocolId().toString(), CoiDisclosureEventType.IACUC_PROTOCOL);
                coiDisclProject.getCoiDiscDetails().add(disclosureDetail);
            }
            coiDisclProjects.add(coiDisclProject);
        }     
    }
    
    /*
     * set up proposals, that need disclosure, with FE relationship
     */
    private void initProposals(List<CoiDisclProject> coiDisclProjects, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {
        List<DevelopmentProposal> proposals = getProposals(GlobalVariables.getUserSession().getPrincipalId());
        for (DevelopmentProposal proposal : proposals) {
            CoiDisclProject coiDisclProject = createNewCoiDisclProject(coiDisclosure, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
            coiDisclProject.setProposalType(proposal.getProposalType());
            coiDisclProject.setCoiProjectId(proposal.getProposalNumber()); //Project Id
            coiDisclProject.setModuleItemKey(proposal.getProposalNumber()); //Module Item Key
            coiDisclProject.setCoiProjectTitle(proposal.getTitle()); //Project Title

            for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                        proposal.getProposalNumber(), proposal.getProposalNumber(), CoiDisclosureEventType.DEVELOPMENT_PROPOSAL);
                coiDisclProject.getCoiDiscDetails().add(disclosureDetail);
            }
            coiDisclProjects.add(coiDisclProject);
        }
    }
    
    /*
     * set up institutional proposals, that need disclosure, with FE relationship
     */
    private void initInstitutionalProposals(List<CoiDisclProject> coiDisclProjects, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {
        List<InstitutionalProposal> iProposals = getInstitutionalProposals(GlobalVariables.getUserSession().getPrincipalId());
        for (InstitutionalProposal proposal : iProposals) {
                CoiDisclProject coiDisclProject = createNewCoiDisclProject(coiDisclosure, CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL);
                coiDisclProject.setProposalType(proposal.getProposalType());
                coiDisclProject.setCoiProjectId(proposal.getProposalId().toString()); //Project Id
                coiDisclProject.setModuleItemKey(proposal.getProposalNumber()); //Module Item Key
                coiDisclProject.setCoiProjectTitle(proposal.getTitle()); //Project Title
                
                for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                    CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                                proposal.getProposalNumber(), proposal.getProposalId().toString(), CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL);
                    coiDisclProject.getCoiDiscDetails().add(disclosureDetail);
                }
                coiDisclProjects.add(coiDisclProject);
            }
        }
    
    /*
     * set up awards, that need disclosure, with FE relationship
     */
    private void initAwards(List<CoiDisclProject> coiDisclProjects, List<PersonFinIntDisclosure> financialEntities, CoiDisclosure coiDisclosure) {
        List<Award> awards = getAwards(GlobalVariables.getUserSession().getPrincipalId());
        List<String> rootAwardNumbers = new ArrayList<String>();
        for (Award award : awards) {
            if (!isAwardHierarchyIncluded(rootAwardNumbers, award)) {
                CoiDisclProject coiDisclProject = createNewCoiDisclProject(coiDisclosure, CoiDisclosureEventType.AWARD);
                coiDisclProject.setCoiProjectId(award.getAwardId().toString()); //Project Id
                coiDisclProject.setModuleItemKey(award.getAwardNumber()); //Module Item Key
                coiDisclProject.setCoiProjectTitle(award.getTitle()); //Project Title
                
                for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
                    CoiDiscDetail disclosureDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure,
                            award.getAwardNumber(), award.getAwardId().toString(), CoiDisclosureEventType.AWARD);
                    coiDisclProject.getCoiDiscDetails().add(disclosureDetail);
                }
                coiDisclProjects.add(coiDisclProject);
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
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#initializeDisclosureDetails(org.kuali.kra.coi.CoiDisclProject)
     */
    public void initializeDisclosureDetails(CoiDisclProject coiDisclProject) {
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(GlobalVariables
                .getUserSession().getPrincipalId(), true);
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            CoiDiscDetail disclosureDetail =createNewCoiDiscDetail(coiDisclProject.getCoiDisclosure(), personFinIntDisclosure, coiDisclProject.getProjectId(), coiDisclProject.getProjectId(), coiDisclProject.getDisclosureEventType());
            disclosureDetail.setProjectType(coiDisclProject.getDisclosureEventType());
            disclosureDetails.add(disclosureDetail);
        }
        coiDisclProject.setCoiDiscDetails(disclosureDetails);

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
        else if (coiDisclosure.isIacucProtocolEvent()) {
            moduleItemKey = ((IacucProtocol)eventBo).getProtocolNumber();
        }
        else if (coiDisclosure.isProposalEvent()) {
            moduleItemKey = ((DevelopmentProposal)eventBo).getProposalNumber();
        } 
        else if (coiDisclosure.isInstitutionalProposalEvent()) {
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
        else if (coiDisclosure.isIacucProtocolEvent()) {
            projectIdFk = ((IacucProtocol)eventBo).getProtocolId().toString();
        }
        else if (coiDisclosure.isProposalEvent()) {
            projectIdFk = ((DevelopmentProposal)eventBo).getProposalNumber();
        } 
        else if (coiDisclosure.isInstitutionalProposalEvent()) {
            projectIdFk = ((InstitutionalProposal)eventBo).getProposalId().toString();
        } 
        else if (coiDisclosure.isAwardEvent()) {
            projectIdFk = ((Award)eventBo).getAwardId().toString();
        }
        return projectIdFk;
    }

    /*
     * get project title from different project bo
     */
    private String getProjectTitle(CoiDisclosure coiDisclosure, KraPersistableBusinessObjectBase eventBo) {
        String projectTitle = null;
        if (coiDisclosure.isProtocolEvent()) {
            projectTitle = ((Protocol)eventBo).getTitle();
        }
        else if (coiDisclosure.isIacucProtocolEvent()) {
            projectTitle = ((IacucProtocol)eventBo).getTitle();
        }
        else if (coiDisclosure.isProposalEvent()) {
            projectTitle = ((DevelopmentProposal)eventBo).getTitle();
        } else if (coiDisclosure.isInstitutionalProposalEvent()) {
            projectTitle = ((InstitutionalProposal)eventBo).getTitle();
        } 
        else if (coiDisclosure.isAwardEvent()) {
            projectTitle = ((Award)eventBo).getTitle();
       }
        return projectTitle;
    }
    
    /*
     * set up the eventbo based on disclosure event and projectid
     */
    private KraPersistableBusinessObjectBase getEventBo(CoiDisclosure coiDisclosure, String projectId) {
        KraPersistableBusinessObjectBase eventBo = null;
        if (coiDisclosure.isProtocolEvent()) {
            eventBo = getProtocol(Long.valueOf(projectId));
        }
        else if (coiDisclosure.isIacucProtocolEvent()) {
            eventBo = getIacucProtocol(Long.valueOf(projectId));
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
        else if (coiDiscdetail.isIacucProtocolEvent()) {
            eventBo = getIacucProtocol(Long.valueOf(projectId));
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
    
    /*
     * get protocol by using protocolid
     */
    private IacucProtocol getIacucProtocol(Long protocolId) {
        HashMap<String, Object> pkMap = new HashMap<String, Object>();
        pkMap.put("protocolId", protocolId);
        return (IacucProtocol) this.businessObjectService.findByPrimaryKey(IacucProtocol.class, pkMap);
    
    }
    

    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#updateDisclosureDetails(org.kuali.kra.coi.CoiDisclosure)
     */
    /*
    public void updateDisclosureDetails(CoiDisclosure coiDisclosure) {
        Collections.sort(coiDisclosure.getCoiDiscDetails());
        String projectType = Constants.EMPTY_STRING;
        String projectIdFk = Constants.EMPTY_STRING;
        String moduleItemKey = Constants.EMPTY_STRING;
        //List<CoiDisclEventProject> disclEventProjects = new ArrayList<CoiDisclEventProject>();
        //CoiDisclEventProject coiDisclEventProject = new CoiDisclEventProject();
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
*/

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
        fieldValues.put("coiDisclProjectId", coiDisclProject.getCoiDisclProjectsId());
//        fieldValues.put("coiDisclosureId", coiDisclProject.getCoiDisclosureId());
//        fieldValues.put("moduleCode", coiDisclProject.getCoiDisclosure().getEventTypeCode());
//        fieldValues.put("moduleItemKey", coiDisclProject.getProjectId());
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
        checkToAddNewFinancialEntity(financialEntities, activeDetails, disclEntityNumbers, coiDisclProject.getProjectId().toString(), coiDisclProject.getCoiDisclosure(), coiDisclProject.getDisclosureEventType(), coiDisclProject.getProjectId());

        coiDisclProject.setCoiDiscDetails(activeDetails);

    }
    
    /*
     * utility method to create a new coi discl project
     */
    private CoiDisclProject createNewCoiDisclProject(CoiDisclosure coiDisclosure, String disclosureEventType) {
        CoiDisclProject coiDisclProject = new CoiDisclProject();
        coiDisclProject.setDisclosureEventType(disclosureEventType);
        coiDisclProject.refreshReferenceObject("coiDisclosureEventType");
        coiDisclProject.setCoiDisclosure(coiDisclosure);
        coiDisclProject.setCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
        coiDisclProject.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        coiDisclProject.setSequenceNumber(coiDisclosure.getSequenceNumber());
        coiDisclProject.setCoiDiscDetails(new ArrayList<CoiDiscDetail>());
        coiDisclProject.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);
        coiDisclProject.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
        coiDisclProject.refreshReferenceObject("coiDispositionStatus");
        
        return coiDisclProject;
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
        disclosureDetail.setCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
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
//        if (!isEventExcluded(CoiDisclosureEventType.IRB_PROTOCOL)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("personId", personId);
    //        fieldValues.put("protocolPersonRoleId", "PI");
                List<ProtocolPerson> protocolPersons = (List<ProtocolPerson>) businessObjectService.findMatching(ProtocolPerson.class,
                        fieldValues);
            for (ProtocolPerson protocolPerson : protocolPersons) {
                    if (protocolPerson.getProtocol().isActive()
                            && isProtocolDisclosurable(protocolPerson.getProtocol())
                            && !isProjectReported(protocolPerson.getProtocol().getProtocolNumber(),
                                    CoiDisclosureEventType.IRB_PROTOCOL, protocolPerson.getPersonId())) {
                    protocols.add(protocolPerson.getProtocol());
                }
            }
//        }
        return protocols;
        
    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getProtocols(java.lang.String)
     */
    public List<IacucProtocol> getIacucProtocols(String personId) {
        
        List<IacucProtocol> protocols = new ArrayList<IacucProtocol>();
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        List<IacucProtocolPerson> protocolPersons = (List<IacucProtocolPerson>) businessObjectService.findMatching(IacucProtocolPerson.class,
                        fieldValues);
        for (IacucProtocolPerson protocolPerson : protocolPersons) {
            if (protocolPerson.getProtocol().isActive()
                    && isIacucProtocolDisclosurable(protocolPerson.getIacucProtocol())
                    && !isProjectReported(protocolPerson.getProtocol().getProtocolNumber(),
                                    CoiDisclosureEventType.IACUC_PROTOCOL, protocolPerson.getPersonId())) {
                protocols.add(protocolPerson.getIacucProtocol());
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
//        if (!isEventExcluded(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL)) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
//        fieldValues.put("proposalPersonRoleId", "PI");
            List<ProposalPerson> proposalPersons = (List<ProposalPerson>) businessObjectService.findMatching(ProposalPerson.class,
                    fieldValues);
        for (ProposalPerson proposalPerson : proposalPersons) {
                if (isProposalDisclosurable(proposalPerson.getDevelopmentProposal())
                        && !isProjectReported(proposalPerson.getDevelopmentProposal().getProposalNumber(),
                                CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, proposalPerson.getPersonId())) {
            // TODO : condition to be implemented              
                proposals.add(proposalPerson.getDevelopmentProposal());
            }
        }
//        }
        return proposals;
        
    }
 
    /*
     * excluded from annual report and/or in master disclosure
     */
    private boolean isEventExcluded(String eventTypeCode) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("eventTypeCode", eventTypeCode);
        CoiDisclosureEventType CoiDisclosureEventType =  businessObjectService.findByPrimaryKey(CoiDisclosureEventType.class, fieldValues);
        return CoiDisclosureEventType.isExcludeFromMasterDisclosure();
    }
    
    /**
     * 
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#getInstitutionalProposals(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<InstitutionalProposal> getInstitutionalProposals(String personId) {
        
        List<InstitutionalProposal> proposals = new ArrayList<InstitutionalProposal>();
        Map<String, Object> propFieldValues = new HashMap<String, Object>();
        Map<String, Integer> propNumbers = new HashMap<String, Integer>();
        propFieldValues.put("personId", personId);
        List<InstitutionalProposalPerson> proposalPersons = (List<InstitutionalProposalPerson>) businessObjectService.findMatching(InstitutionalProposalPerson.class, propFieldValues);
        for (InstitutionalProposalPerson proposalPerson : proposalPersons) {
            Integer newSequenceNumber = proposalPerson.getSequenceNumber();
            Integer oldSequenceNumber = propNumbers.get(proposalPerson.getProposalNumber());
            if ((oldSequenceNumber == null) || (oldSequenceNumber.compareTo(newSequenceNumber) < 0)) { 
                propNumbers.put(proposalPerson.getProposalNumber(), newSequenceNumber);
            }
        }
        for (String propNumber: propNumbers.keySet()) {
            Map<String, Object> ipFieldValues = new HashMap<String, Object>();
            ipFieldValues.put("proposalNumber", propNumber);
            ipFieldValues.put("sequenceNumber", propNumbers.get(propNumber));
            // get singleton list of IP's that match IP number
            List<InstitutionalProposal> searchResults = (List<InstitutionalProposal>) businessObjectService.findMatching(InstitutionalProposal.class, ipFieldValues);
            for (InstitutionalProposal institutionalProposal: searchResults) {
                if (isInstitutionalProposalDisclosurable(institutionalProposal) && 
                    !isProjectReported(institutionalProposal.getProposalNumber(), CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, personId)) {
                    proposals.add(institutionalProposal);
                }
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
//        if (!isEventExcluded(CoiDisclosureEventType.AWARD)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("personId", personId);
    //        fieldValues.put("roleCode", "PI");
            List<AwardPerson> awardPersons = (List<AwardPerson>) businessObjectService.findMatchingOrderBy(AwardPerson.class,
                    fieldValues, "awardNumber", true);
            for (AwardPerson awardPerson : awardPersons) {
                if (isCurrentAward(awardPerson.getAward())
                        && isAwardDisclosurable(awardPerson.getAward())
                        && !isProjectReported(awardPerson.getAward().getAwardNumber(), CoiDisclosureEventType.AWARD,
                                awardPerson.getPersonId())) {
                    awards.add(awardPerson.getAward());
                }
            }
//        }
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
        HashMap<String, Object> fieldValues = new HashMap<String, Object>();
        if (StringUtils.equals(CoiDisclosureEventType.AWARD, projectType)) {
            // all award numbers in that hierarchy.  so any award in that hierarchy is reported, then the others
            // don't have to report.
            //fieldValues.put("moduleItemKey", getAwardNumbersForHierarchy(projectId));
            fieldValues.put("moduleItemKey", getAwardNumbersForHierarchy(projectId));
        } else {
            //fieldValues.put("moduleItemKey", projectId);
            fieldValues.put("moduleItemKey", projectId);
        }
        fieldValues.put("disclosureEventType", projectType);
        List<CoiDisclProject> disclProjects = (List<CoiDisclProject>) businessObjectService.findMatching(CoiDisclProject.class, fieldValues);
        Date currentDate = dateTimeService.getCurrentSqlDateMidnight();
        for (CoiDisclProject disclProject : disclProjects) {
            if (StringUtils.equals(disclProject.getCoiDisclosure().getPersonId(), personId) 
                    && disclProject.getCoiDisclosure().getExpirationDate().after(currentDate)) {
                isDisclosed = true;
                break;
            }
        }

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
        List<AwardHierarchy> awardHierarchies  = (List<AwardHierarchy>) businessObjectService.findMatching(AwardHierarchy.class, fieldValues);
        if (CollectionUtils.isNotEmpty(awardHierarchies)) {
            fieldValues.clear();
            fieldValues.put("rootAwardNumber", awardHierarchies.get(0).getRootAwardNumber());
            awardHierarchies  = (List<AwardHierarchy>) businessObjectService.findMatching(AwardHierarchy.class, fieldValues);
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
            //newDisclosure.setCoiDiscDetails(null);
            newDisclosure.setCoiDisclosureAttachments(null);
            newDisclosure.setCoiDisclosureNotepads(null);
            newDisclosure.setCoiUserRoles(new ArrayList<CoiUserRole>());
            newDisclosure.setCurrentDisclosure(false);
            newDisclosure.setCertificationTimestamp(null);
            newDisclosure.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
            newDisclosure.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);
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
         Collection<String> params = parameterService.getParameterValuesAsString(AwardDocument.class, AWARD_DISCLOSE_STATUS_CODES);
        return params.contains(award.getStatusCode().toString()) && isSponsorForDisclosure(ProposalDevelopmentDocument.class, award.getSponsorCode(), SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE, ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE);
   
    }
    
    /*
     * check if PD is active for disclosure
     */
    private boolean isProposalDisclosurable(DevelopmentProposal proposal) {
        Collection<String> params = parameterService.getParameterValuesAsString(ProposalDevelopmentDocument.class, PROPOSAL_DISCLOSE_STATUS_CODES);
        return params.contains(proposal.getProposalStateTypeCode()) && isSponsorForDisclosure(ProposalDevelopmentDocument.class, proposal.getSponsorCode(), SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE, ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE);
   
    }

    /*
     * check if institutional proposal is active for disclosure
     */
    private boolean isInstitutionalProposalDisclosurable(InstitutionalProposal proposal) {
        Collection<String> params = parameterService.getParameterValuesAsString(InstitutionalProposalDocument.class, INSTITUTIONAL_PROPOSAL_DISCLOSE_STATUS_CODES);
        return params.contains(proposal.getStatusCode().toString()) && isSponsorForDisclosure(ProposalDevelopmentDocument.class, proposal.getSponsorCode(), SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE, ALL_SPONSORS_FOR_PROPOSAL_AWD_DISCLOSE);
   
    }
    
    /*
     * check if protocol is active for disclosure
     */
    private boolean isProtocolDisclosurable(Protocol protocol) {
        Collection<String> params = parameterService.getParameterValuesAsString(ProtocolDocument.class, PROTOCOL_DISCLOSE_STATUS_CODES);
        return params.contains(protocol.getProtocolStatusCode()) && isProtocolFundedByActiveSponsor(protocol);
   
    }

    /*
     * check if protocol is active for disclosure
     */
    private boolean isIacucProtocolDisclosurable(IacucProtocol protocol) {
        Collection<String> params = parameterService.getParameterValuesAsString(IacucProtocolDocument.class, IACUC_DISCLOSE_STATUS_CODES);
        return params.contains(protocol.getProtocolStatusCode()) && isProtocolFundedByActiveSponsor(protocol);
   
    }

    /*
     * check if protocol funding source sponsor is in disclose list
     */
    private boolean isProtocolFundedByActiveSponsor(Protocol protocol) {
         boolean isActive = false;
         for (ProtocolFundingSource fundingSource : protocol.getProtocolFundingSources()) {
             if (fundingSource.isSponsorFunding() && isSponsorForDisclosure(ProtocolDocument.class, fundingSource.getFundingSourceNumber(), SPONSORS_FOR_PROTOCOL_DISCLOSE, ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE)) {
                 isActive = true;
                 break;
             }
         } 
         // it is ok if there is no sponsor set up for protocol
         if (CollectionUtils.isEmpty(protocol.getProtocolFundingSources())) {
             isActive = isAllSponsorActiveForDisclose(ProtocolDocument.class, ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE);
         }
         return isActive;
         
    }
    
    /*
     * check if protocol funding source sponsor is in disclose list
     */
    private boolean isProtocolFundedByActiveSponsor(IacucProtocol protocol) {
         boolean isActive = false;
         for (org.kuali.kra.protocol.protocol.funding.ProtocolFundingSource fundingSource : protocol.getProtocolFundingSources()) {
             if (fundingSource.isSponsorFunding() && isSponsorForDisclosure(ProtocolDocument.class, fundingSource.getFundingSourceNumber(), SPONSORS_FOR_PROTOCOL_DISCLOSE, ALL_SPONSORS_FOR_PROTOCOL_DISCLOSE)) {
                 isActive = true;
                 break;
             }
         } 
         // it is ok if there is no sponsor set up for protocol
         if (CollectionUtils.isEmpty(protocol.getProtocolFundingSources())) {
             isActive = isAllSponsorActiveForDisclose(ProtocolDocument.class, ALL_SPONSORS_FOR_IACUC_PROTOCOL_DISCLOSE);
         }
         return isActive;
    }
    
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    private boolean isSponsorForDisclosure(Class clazz, String sponsorCode, String paramName, String paramNameAllSponsor) {
        return isAllSponsorActiveForDisclose(clazz, paramNameAllSponsor) || isSponsorHierarchyActiveForDisclose(clazz, sponsorCode, paramName);    
    }
    
    /*
     * check if sponsorcode in in sponsor hierarchy
     */
    private boolean isSponsorHierarchyActiveForDisclose(Class clazz, String sponsorCode, String paramName) {
        
        Collection<String> params = new ArrayList<String>();
        try {
            params = parameterService.getParameterValuesAsString(clazz, paramName);
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
            isAllSponsors = parameterService.getParameterValueAsBoolean(clazz, paramName);
        } catch (Exception e) {
            
        }
   
        return isAllSponsors;
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
        if (coiDisclosure.isUpdateEvent() || (coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
            updateMasterDisclosureDetails(coiDisclosure);
        }

        //Collections.sort(coiDisclosure.getCoiDiscDetails());
        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        Long currentDisclosureId = coiDisclosure.getCoiDisclosureId();
        if (currentDisclosureId == null) {
            // if this is click update discl link
            CoiDisclosure masterDisclosure = getCurrentDisclosure();
            currentDisclosureId = masterDisclosure.getCoiDisclosureId();
            answerHeaders = copyDisclosureQuestionnaire(masterDisclosure, coiDisclosure);
        } else {
            answerHeaders = retrieveAnswerHeaders(coiDisclosure);
        }
        masterDisclosureBean.setAnswerHeaders(answerHeaders);
        for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
            CoiDisclProject coiDisclProject1 = (CoiDisclProject)ObjectUtils.deepCopy(coiDisclProject);
            coiDisclProject1.setCoiDispositionStatus(coiDisclProject.getCoiDispositionStatus());
            if (CollectionUtils.isNotEmpty(coiDisclProject1.getCoiDiscDetails())) {
            for (CoiDiscDetail coiDiscDetail : coiDisclProject1.getCoiDiscDetails()) {
                if (!StringUtils.equals(projectType, coiDiscDetail.getProjectType()) || !StringUtils.equals(moduleItemKey, coiDiscDetail.getModuleItemKey())) {
                    disclosureProjectBean = getCoiDisclosureProjectBean(coiDiscDetail);
                    masterDisclosureBean.addProject(disclosureProjectBean, coiDiscDetail.getProjectType());
                    if (!StringUtils.equals(projectType, coiDiscDetail.getProjectType())) {
                        projectType = coiDiscDetail.getProjectType();
                    }
                    moduleItemKey = coiDiscDetail.getModuleItemKey();
                    addProjectDisclAttachments(disclosureProjectBean, coiDisclosure, coiDiscDetail.getOriginalCoiDisclosureId());
                    addProjectDisclNotepads(disclosureProjectBean, coiDisclosure, coiDiscDetail.getOriginalCoiDisclosureId());
                    addProjectDisclQuestionnaires(disclosureProjectBean, answerHeaders, currentDisclosureId);
                }
      //          disclosureProjectBean.getCoiDisclProject().getCoiDiscDetails().add(coiDiscDetail);            
            }
            } else {
                disclosureProjectBean = getCoiDisclosureProjectBean(coiDisclProject1);
                masterDisclosureBean.addProject(disclosureProjectBean, coiDisclProject1.getDisclosureEventType());
                    projectType = coiDisclProject1.getDisclosureEventType();
                moduleItemKey = coiDisclProject1.getModuleItemKey();
//                addProjectDisclAttachments(disclosureProjectBean, coiDisclosure, coiDiscDetail.getOriginalCoiDisclosureId());
//                addProjectDisclNotepads(disclosureProjectBean, coiDisclosure, coiDiscDetail.getOriginalCoiDisclosureId());
                addProjectDisclQuestionnaires(disclosureProjectBean, answerHeaders, currentDisclosureId);
               
            }
        }
        
        // unless we are doing an update
        if (coiDisclosure.isApprovedDisclosure() || coiDisclosure.isDisapprovedDisclosure() || (!coiDisclosure.isUpdateEvent() && !coiDisclosure.isAnnualEvent())) {
            setupDisclosures(masterDisclosureBean, coiDisclosure);
        }
        return masterDisclosureBean;
    }
        
    private List<AnswerHeader> retrieveAnswerHeaders(CoiDisclosure coiDisclosure) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(MODULE_ITEM_CODE, CoeusModule.COI_DISCLOSURE_MODULE_CODE);
        fieldValues.put(MODULE_ITEM_KEY, coiDisclosure.getCoiDisclosureId());
        fieldValues.put(MODULE_SUB_ITEM_KEY, "-1");
        return (List<AnswerHeader>) businessObjectService.findMatching(AnswerHeader.class, fieldValues);
    }
    
    private void addProjectDisclQuestionnaires(CoiDisclosureProjectBean disclosureProjectBean, List<AnswerHeader> answerHeaders, Long originalDisclosureId) {
        disclosureProjectBean.populateAnswers(originalDisclosureId.toString());
    }
    
    private void addProjectDisclAttachments(CoiDisclosureProjectBean disclosureProjectBean, CoiDisclosure coiDisclosure, Long originalDisclosureId) {
        for (CoiDisclosureAttachment disclattachment : coiDisclosure.getCoiDisclosureAttachments()) {
            if ((disclattachment.getOriginalCoiDisclosureId() == null && originalDisclosureId == null) || 
                    (disclattachment.getOriginalCoiDisclosureId() != null && originalDisclosureId != null && disclattachment.getOriginalCoiDisclosureId().equals(originalDisclosureId))) {
                disclosureProjectBean.getProjectDiscAttachments().add(disclattachment);
            }
        }        
    }
    
    private void addProjectDisclNotepads(CoiDisclosureProjectBean disclosureProjectBean, CoiDisclosure coiDisclosure, Long originalDisclosureId) {
        for (CoiDisclosureNotepad disclNotepad : coiDisclosure.getCoiDisclosureNotepads()) {
            if ((disclNotepad.getOriginalCoiDisclosureId() == null && originalDisclosureId == null) || 
                    (disclNotepad.getOriginalCoiDisclosureId() != null && originalDisclosureId != null && disclNotepad.getOriginalCoiDisclosureId().equals(originalDisclosureId))) {
                disclosureProjectBean.getProjectDiscNotepads().add(disclNotepad);
            }
        }        
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
 //               disclosureProjectBean.getCoiDisclProject().getCoiDiscDetails().add(coiDiscDetail);
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
    
    public void setCoiDisclosureDao(CoiDisclosureDao coiDisclosureDao) {
        this.coiDisclosureDao = coiDisclosureDao;
    }
    
    public CoiDisclosureDao getCoiDisclosureDao() {
        return coiDisclosureDao;
    }
    /*
     * get the approved and disapproved disclosure history for the specified disclosurenumber
     */
    @SuppressWarnings({ "unused", "unchecked" })
    protected List<CoiDisclosureHistory> getDisclosureHistory(String coiDisclosureNumber) {
        return getCoiDisclosureDao().getApprovedAndDisapprovedDisclosureHistory(coiDisclosureNumber);
    }

    /*
     * get the first disc detail for this disclosure.  if orginaldisclosureid is not null, then it is from previous master discl
     */
    protected CoiDiscDetail getCurrentProjectDetail(CoiDisclosure coiDisclosure) {
        if (CollectionUtils.isEmpty(coiDisclosure.getCoiDisclProjects())) {
            coiDisclosure.refreshReferenceObject("coiDisclProjects");
        }
        for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
            // after normalization coidisclosureid in detail is not setting ?
            for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {
                if (coiDiscDetail.getOriginalCoiDisclosure() == null || coiDisclProject.getCoiDisclosureId().equals(coiDiscDetail.getOriginalCoiDisclosureId())) {
//                    if (coiDiscDetail.getCoiDisclosure() == null) {
//                        // immediatly after approve may need this
//                        coiDiscDetail.refreshReferenceObject("coiDisclosure");
//                    }
                    return coiDiscDetail;
                }
    
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
//            disclosureProjectBean.setCoiDisclProject(getCoiDisclProject(coiDiscDetail));
            disclosureProjectBean.setCoiDisclProject(coiDiscDetail.getCoiDisclProject());
            if (disclosureProjectBean.getCoiDisclProject().isManualEvent()) {
                disclosureProjectBean.getCoiDisclProject().initHeaderItems();
            }
/*
            if (coiDiscDetail.isManualEvent()) {
                disclosureProjectBean.setCoiDisclProject(getCoiDisclProject(coiDiscDetail));
            } else {
                disclosureProjectBean.setDisclosureProject(getEventBo(coiDiscDetail, coiDiscDetail.getProjectIdFk()));
            }
*/            
        } 
        return disclosureProjectBean;
    }
    
    // a quick hacky for release 5.0 should refactor
    protected CoiDisclosureProjectBean getCoiDisclosureProjectBean(CoiDisclProject coiDisclProject) {
        CoiDisclosureProjectBean disclosureProjectBean = new CoiDisclosureProjectBean();
            disclosureProjectBean.setCoiDisclProject(coiDisclProject);
            if (disclosureProjectBean.getCoiDisclProject().isManualEvent()) {
                disclosureProjectBean.getCoiDisclProject().initHeaderItems();
            }
        return disclosureProjectBean;
    }

    /*
     * retrieve manual project
     */
    private CoiDisclProject getCoiDisclProject(CoiDiscDetail coiDiscDetail) {
        // this is inconsistent : manual refere to field1 , project refer to field2
        Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureNumber", coiDiscDetail.getCoiDisclosureNumber());
        // this is unique
        fieldValues.put("moduleItemKey", coiDiscDetail.getModuleItemKey());
        List<CoiDisclProject> coiDisclProjects = (List<CoiDisclProject>) businessObjectService.findMatching(CoiDisclProject.class, fieldValues);
        if (CollectionUtils.isNotEmpty(coiDisclProjects)) {
            return coiDisclProjects.get(0);
        } else {
            return null;
        }
    }
    
    public List<CoiDispositionStatus> getDispositionStatuses(String disclosureStatusCode) {
        List<CoiDispositionStatus> coiDispositionStatuses  = new ArrayList<CoiDispositionStatus>();
        if (ObjectUtils.isNotNull(disclosureStatusCode)) {
            Map<String, String> criteria = new HashMap<String, String>();
            criteria.put("coiDisclosureStatusCode",disclosureStatusCode);
            coiDispositionStatuses = 
                (List<CoiDispositionStatus>) businessObjectService.findMatching(CoiDispositionStatus.class, criteria);
        }
        return coiDispositionStatuses;
    }
    
    
    
    public void initDisclosureFromMasterDisclosure(CoiDisclosure coiDisclosure) {
        CoiDisclosure masterDisclosure = getCurrentDisclosure();
        coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
        coiDisclosure.setCoiDisclosureAttachments(new ArrayList<CoiDisclosureAttachment>());
        coiDisclosure.setCoiDisclosureNotepads(new ArrayList<CoiDisclosureNotepad>());
        copyCollections(masterDisclosure, coiDisclosure);
    }

    /*
     * Return current disclosure, ie, the master disclosure
     */
    private CoiDisclosure getCurrentDisclosure() {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());
        fieldValues.put("currentDisclosure", "Y");

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) businessObjectService.findMatching(CoiDisclosure.class,
                fieldValues);
        if (CollectionUtils.isEmpty(disclosures)) {
            return null;
        } else {
            return disclosures.get(0);
        }

    }

    // TODO : following several copy related methods are from coidisclosureactionservice.  should try to make them sharable
    private void  copyCollections(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        
        copyDisclosureProjects(masterCoiDisclosure, coiDisclosure);
        copyDisclosureNotePads(masterCoiDisclosure, coiDisclosure);
        copyDisclosureAttachments(masterCoiDisclosure, coiDisclosure);
        copyDisclosureQuestionnaire(masterCoiDisclosure, coiDisclosure);
    }
    
    private List<AnswerHeader> copyDisclosureQuestionnaire(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {

        List<AnswerHeader> newAnswerHeaders = versioningQuestionnaireAnswer(masterCoiDisclosure);
         if (!newAnswerHeaders.isEmpty()) {
             for (AnswerHeader answerHeader : newAnswerHeaders) {
                 if (coiDisclosure.getCoiDisclosureId() != null) {
                     answerHeader.setModuleItemKey(coiDisclosure.getCoiDisclosureId().toString());
                 }                 
             }
        }
        return newAnswerHeaders;
    }
    
    private List<AnswerHeader> versioningQuestionnaireAnswer(CoiDisclosure coiDisclosure) {
        List<AnswerHeader> newAnswerHeaders = new ArrayList<AnswerHeader>();
        for (AnswerHeader answerHeader : retrieveAnswerHeaders(coiDisclosure)) {
               AnswerHeader copiedAnswerHeader = (AnswerHeader) ObjectUtils.deepCopy(answerHeader);
//                copiedAnswerHeader.setModuleSubItemKey(coiDisclosure.getSequenceNumber().toString());
                copiedAnswerHeader.setAnswerHeaderId(null);
                for (Answer answer : copiedAnswerHeader.getAnswers()) {
                    answer.setId(null);
                }
                newAnswerHeaders.add(copiedAnswerHeader);
        }
        return newAnswerHeaders;
    }
    
    /*
     * copy disclosure details of current master disclosure to the disclosure that is bing approved
     */
    /*
    private void copyDisclosureDetails(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {

        for (CoiDiscDetail coiDiscDetail : masterCoiDisclosure.getCoiDiscDetails()) {
                CoiDiscDetail copiedDiscDetail = (CoiDiscDetail) ObjectUtils.deepCopy(coiDiscDetail);
                copiedDiscDetail.setCopiedCoiDiscDetailId(copiedDiscDetail.getCoiDiscDetailId());
                copiedDiscDetail.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedDiscDetail.setCoiDiscDetailId(null);
                if (copiedDiscDetail.getOriginalCoiDisclosureId() == null) {
                    copiedDiscDetail.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDiscDetails().add(copiedDiscDetail);
        }
    }
    */
    
    private void copyDisclosureDetails(List<CoiDiscDetail> originalDiscDetails, CoiDisclProject copiedDisclProject) {
        List<CoiDiscDetail> copiedDiscDetails = new ArrayList<CoiDiscDetail>();
        for (CoiDiscDetail coiDiscDetail : originalDiscDetails) {
            CoiDiscDetail copiedDiscDetail = (CoiDiscDetail) ObjectUtils.deepCopy(coiDiscDetail);
            copiedDiscDetail.setCopiedCoiDiscDetailId(copiedDiscDetail.getCoiDiscDetailId());
            copiedDiscDetail.setSequenceNumber(copiedDisclProject.getSequenceNumber());
            copiedDiscDetail.setCoiDiscDetailId(null);
            if (copiedDiscDetail.getOriginalCoiDisclosureId() == null) {
                copiedDiscDetail.setOriginalCoiDisclosureId(copiedDisclProject.getCoiDisclosureId());
            }
            copiedDiscDetail.setCoiDisclProject(copiedDisclProject);
            copiedDiscDetails.add(copiedDiscDetail);
        }
        copiedDisclProject.setCoiDiscDetails(copiedDiscDetails);
        if (copiedDisclProject.getOriginalCoiDisclosureId() == null) {
        copiedDisclProject.setOriginalCoiDisclosureId(copiedDisclProject.getCoiDisclosureId());
        }
   }
    
    //TODO: finish project copy and work in subsequent details gettting copied
    private void copyDisclosureProjects(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        List<CoiDisclProject> copiedDisclProjects = new ArrayList<CoiDisclProject>();
        for (CoiDisclProject coiDisclProject : masterCoiDisclosure.getCoiDisclProjects()) {
//            if (!coiDisclProject.getCoiDisclosureEventType().isExcludeFromMasterDisclosure()) {
                List<CoiDiscDetail> coiDiscDetails = coiDisclProject.getCoiDiscDetails();
                // coiDisclProject.setCoiDiscDetails(null);
                CoiDisclProject copiedDisclProject = (CoiDisclProject) ObjectUtils.deepCopy(coiDisclProject);
                copiedDisclProject.setCoiDispositionStatus(coiDisclProject.getCoiDispositionStatus());
                if (copiedDisclProject.getCoiDispositionStatus() == null) {
                    copiedDisclProject.refreshReferenceObject("coiDispositionStatus");
                }
                copiedDisclProject.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedDisclProject.setCoiDisclProjectsId(null);
                // copy disc details
                copyDisclosureDetails(coiDiscDetails, copiedDisclProject);
                copiedDisclProjects.add(copiedDisclProject);
                copiedDisclProject.setCoiDisclosure(coiDisclosure);
                copiedDisclProject.setCoiDisclosureId(null);
//            }
        }
        coiDisclosure.setCoiDisclProjects(copiedDisclProjects);
    }
    
    private void copyDisclosureNotePads(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
//        CoiDisclosure copiedDisclosure = (CoiDisclosure) ObjectUtils.deepCopy(masterCoiDisclosure);
        for (CoiDisclosureNotepad coiDisclosureNotepad : masterCoiDisclosure.getCoiDisclosureNotepads()) {
//            if (!isDisclosureNotePadExist(coiDisclosure, coiDisclosureNotepad)) {
                // TODO implement the if check when originaldisclosureid is added to notepad
                //CoiDisclosureNotepad copiedCoiDisclosureNotepad = (CoiDisclosureNotepad) ObjectUtils.deepCopy(coiDisclosureNotepad);
                CoiDisclosureNotepad copiedCoiDisclosureNotepad = copyCoiDisclosureNotepad(coiDisclosureNotepad);
                copiedCoiDisclosureNotepad.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedCoiDisclosureNotepad.setId(null);
                if (copiedCoiDisclosureNotepad.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureNotepad.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDisclosureNotepads().add(copiedCoiDisclosureNotepad);
//            }
        }
    }

    private void copyDisclosureAttachments(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
//        CoiDisclosure copiedDisclosure = (CoiDisclosure) ObjectUtils.deepCopy(masterCoiDisclosure);
        for (CoiDisclosureAttachment coiDisclosureAttachment : masterCoiDisclosure.getCoiDisclosureAttachments()) {
//            if (!isDisclosureNotePadExist(coiDisclosure, coiDisclosureNotepad)) {
                // TODO implement the if check when originaldisclosureid is added to notepad
                //CoiDisclosureAttachment copiedCoiDisclosureAttachment = (CoiDisclosureAttachment) ObjectUtils.deepCopy(coiDisclosureAttachment);
                CoiDisclosureAttachment copiedCoiDisclosureAttachment = copyCoiDisclosureAttachment(coiDisclosureAttachment);
                copiedCoiDisclosureAttachment.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedCoiDisclosureAttachment.setAttachmentId(null);
                copiedCoiDisclosureAttachment.setFileId(coiDisclosureAttachment.getFileId());
                copiedCoiDisclosureAttachment.setFile(coiDisclosureAttachment.getFile());
                if (copiedCoiDisclosureAttachment.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureAttachment.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDisclosureAttachments().add(copiedCoiDisclosureAttachment);
//            }
        }
    }

    /*
     * check if disclosure detail is exist in the disclosure being approved
     * if it is, then there is no need to copy over.
     */
    private boolean isDisclosureDetailExist(CoiDisclosure coiDisclosure,CoiDiscDetail coiDiscDetail) {
        boolean isExist = false;
        for (CoiDisclProject disclProject : coiDisclosure.getCoiDisclProjects()) {
            for (CoiDiscDetail discDetail : disclProject.getCoiDiscDetails()) {
                if (StringUtils.equals(discDetail.getProjectType(), coiDiscDetail.getProjectType()) && StringUtils.equals(discDetail.getProjectIdFk(), coiDiscDetail.getProjectIdFk()) && discDetail.getPersonFinIntDisclosureId().equals(coiDiscDetail.getPersonFinIntDisclosureId())) {
                    isExist = true;
                    break;
                }
            }
        }
        return isExist;
    }
    
    
    /**
     * Called during a "Report Coi" instance for System Events.  This method assumes a 1:1 disclosure to project relationship
     * @see org.kuali.kra.coi.disclosure.CoiDisclosureService#initializeDisclosureProject(org.kuali.kra.coi.CoiDisclosure, java.lang.String)
     */
    public void initializeDisclosureProject(CoiDisclosure coiDisclosure, String projectId) {
        List<CoiDisclProject> coiDisclProjects = coiDisclosure.getCoiDisclProjects();
        if (CollectionUtils.isEmpty(coiDisclProjects)) {
            coiDisclProjects = new ArrayList<CoiDisclProject>();
        }
        
        coiDisclosure.setEventBo(getEventBo(coiDisclosure, projectId));
        String moduleItemKey = getModuleItemKey(coiDisclosure, coiDisclosure.getEventBo());
        String projectIdFk = getProjectIdFk(coiDisclosure, coiDisclosure.getEventBo());
        String projectTitle = getProjectTitle(coiDisclosure, coiDisclosure.getEventBo());
        
        CoiDisclProject newProject = new CoiDisclProject();
        newProject.setCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
        newProject.setDisclosureEventType(coiDisclosure.getEventTypeCode());
        newProject.refreshReferenceObject("coiDisclosureEventType");
        newProject.setCoiDisclosure(coiDisclosure);
        newProject.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        newProject.setSequenceNumber(coiDisclosure.getSequenceNumber());
        newProject.setCoiProjectId(projectIdFk);
        newProject.setModuleItemKey(moduleItemKey);
        newProject.setCoiProjectTitle(projectTitle);
        newProject.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
        newProject.refreshReferenceObject("coiDispositionStatus");
        newProject.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);
        
        this.initializeDisclosureDetails(newProject);
        coiDisclProjects.add(newProject);
        coiDisclosure.setCoiDisclProjects(coiDisclProjects);
    }
    

/* this is apparently no longer used...    
    private CoiDisclProject createDisclProject(CoiDisclosure coiDisclosure, String projectId, String projectName) {
        CoiDisclProject disclProject = new CoiDisclProject();

        disclProject.setCoiDisclosure(coiDisclosure);
        disclProject.setCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
        disclProject.setSequenceNumber(coiDisclosure.getSequenceNumber());
        disclProject.setCoiDisclosureNumber(coiDisclosure.getCoiDisclosureNumber());
        disclProject.setDisclosureEventType(coiDisclosure.getEventTypeCode());
        
        disclProject.setCoiProjectId(projectId);
        disclProject.setModuleItemKey(projectId);
        disclProject.setCoiProjectTitle(projectName);
      //  disclProject.setCoiDiscDetails(coiDiscDetails);
        
        return disclProject;
    }
*/    
    
    /*
     * This method is compare the person FE and the FE disclosed.  If there is any addition/activate/inactivate,
     * then the disclosed FE should be updated accordingly.
     * This is mainly for 'Update Disclosure' or update annual disclosure
     */
    private void updateMasterDisclosureDetails(CoiDisclosure coiDisclosure) {
        /*
        Collections.sort(coiDisclosure.getCoiDiscDetails(), new Comparator() {
            public int compare(Object o1, Object o2) {
                CoiDiscDetail detail1 = (CoiDiscDetail)o1;                
                CoiDiscDetail detail2 = (CoiDiscDetail)o2;                
                if (detail1.getOriginalCoiDisclosureId().equals(detail2.getOriginalCoiDisclosureId())) {
                    return detail1.getModuleItemKey().compareTo(detail2.getModuleItemKey());
                } else {
                    return detail1.getOriginalCoiDisclosureId().compareTo(detail2.getOriginalCoiDisclosureId());
                }
            }
        });
        */
        Map <Long, List<CoiDiscDetail>> projectDetailMap = setupDetailMap(coiDisclosure);
        //List<CoiDisclProject> coiDisclProjects = new ArrayList<CoiDisclProject>();
        String personId = GlobalVariables.getUserSession().getPrincipalId();
        if (!StringUtils.equals(personId, coiDisclosure.getPersonId())) {
            personId = coiDisclosure.getPersonId();
        }
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(personId, true);
        Long disclosureId = coiDisclosure.getCoiDisclosureId();
        for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {
            if (!coiDisclProject.getCoiDisclosureEventType().isExcludeFromMasterDisclosure()) {
                List<CoiDiscDetail> coiDiscDetails = new ArrayList<CoiDiscDetail>();
                for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {
                    if (!coiDiscDetail.getOriginalCoiDisclosureId().equals(disclosureId)) {
                        disclosureId = coiDiscDetail.getOriginalCoiDisclosureId();
                        if (coiDiscDetail.getOriginalCoiDisclosure().isAnnualEvent()) {
                            checkToAddNewFEForAnnualEvent(financialEntities, coiDiscDetails, disclosureId, coiDisclosure,
                                    projectDetailMap.get(disclosureId), coiDisclProject);
                        } else {
                            checkToAddNewFinancialEntity(financialEntities, coiDiscDetails, disclosureId, coiDisclosure,
                                    projectDetailMap.get(disclosureId), coiDisclProject);
                        }
                    }
                    getCurrentFinancialEntity(coiDiscDetail);
                    if (coiDiscDetail.getPersonFinIntDisclosure().isStatusActive()
                            && coiDiscDetail.getPersonFinIntDisclosure().isCurrentFlag()) {
                        coiDiscDetails.add(coiDiscDetail);
                    }
                }
                coiDisclProject.setCoiDiscDetails(coiDiscDetails);
            }
        }
    }

    /*
     * set up the detail list map for each reported disclosure.
     */
    private Map <Long, List<CoiDiscDetail>> setupDetailMap(CoiDisclosure coiDisclosure) {
        Map <Long, List<CoiDiscDetail>> projectDetailMap = new HashMap<Long, List<CoiDiscDetail>>();
        for (CoiDisclProject disclProject : coiDisclosure.getCoiDisclProjects()) {
            for (CoiDiscDetail detail : disclProject.getCoiDiscDetails()) {
                if (detail.getOriginalCoiDisclosureId() == null) {
                    detail.setOriginalCoiDisclosureId(coiDisclosure.getCoiDisclosureId());
                }
                if (!projectDetailMap.containsKey(detail.getOriginalCoiDisclosureId())) {
                    projectDetailMap.put(detail.getOriginalCoiDisclosureId(), new ArrayList<CoiDiscDetail>());
                }
                projectDetailMap.get(detail.getOriginalCoiDisclosureId()).add(detail);
            }
        }
        return projectDetailMap;
        
    }

    /*
     * Loop thru detail list in each project to see if any the FE is new, and then a new detail will be created.
     */
    private void checkToAddNewFEForAnnualEvent(List<PersonFinIntDisclosure> financialEntities, List<CoiDiscDetail> coiDiscDetails,
            Long disclosureId, CoiDisclosure coiDisclosure, List<CoiDiscDetail> projectDetails, CoiDisclProject coiDisclProject) {
        Map <String, List<CoiDiscDetail>> projectDetailMap = setupDetailMapForAnnual(projectDetails);
        for (String itemKey : projectDetailMap.keySet()) {
            checkToAddNewFinancialEntity(financialEntities, coiDiscDetails, disclosureId, coiDisclosure, projectDetailMap.get(itemKey), coiDisclProject);
        }
    }

    /*
     * Set up the map which contains the detail list for each project reported in annual event
     */
    private Map <String, List<CoiDiscDetail>> setupDetailMapForAnnual(List<CoiDiscDetail> projectDetails) {
        Map <String, List<CoiDiscDetail>> projectDetailMap = new HashMap<String, List<CoiDiscDetail>>();
        for (CoiDiscDetail detail : projectDetails) {
            if (!projectDetailMap.containsKey(detail.getModuleItemKey())) {
                projectDetailMap.put(detail.getModuleItemKey(), new ArrayList<CoiDiscDetail>());
            }
            projectDetailMap.get(detail.getModuleItemKey()).add(detail);
        }
        return projectDetailMap;
        
    }

    /*
     * This is for update master disclosure.  if FE is new or FE has been updated to new version
     */
    private void checkToAddNewFinancialEntity(List<PersonFinIntDisclosure> financialEntities, List<CoiDiscDetail> coiDiscDetails,
            Long disclosureId, CoiDisclosure coiDisclosure, List<CoiDiscDetail> projectDetails, CoiDisclProject coiDisclProject) {
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            boolean isNewFe = true;
            String projectType = Constants.EMPTY_STRING;
            String projectIdFk = Constants.EMPTY_STRING;
            String moduleItemKey = Constants.EMPTY_STRING;
            boolean isSet = false;
            for (CoiDiscDetail detail : projectDetails) {
                if (detail.getPersonFinIntDisclosure().getEntityNumber().equals(personFinIntDisclosure.getEntityNumber())) {
                    isNewFe = false;
                    break;
                }
                if (!isSet) {
                    projectType = detail.getProjectType();
                    projectIdFk = detail.getProjectIdFk();
                    moduleItemKey = detail.getModuleItemKey();
                    isSet = true;
                }
            }
            if (isNewFe) {
                // if this is newversion, do we keep the related information and comment ?
                CoiDiscDetail newDetail = createNewCoiDiscDetail(coiDisclosure, personFinIntDisclosure, moduleItemKey, projectIdFk, projectType);
                newDetail.setOriginalCoiDisclosureId(disclosureId);
                newDetail.setCoiDisclProject(coiDisclProject);
                coiDiscDetails.add(newDetail);
            }
        }


    }   

    
    
    private CoiDisclosureAttachment copyCoiDisclosureAttachment(CoiDisclosureAttachment attachment) {
        CoiDisclosureAttachment attachCopy = new CoiDisclosureAttachment();
        
        attachCopy.setAttachmentId(attachment.getAttachmentId());
        attachCopy.setAttachmentIdForPermission(attachment.getAttachmentIdForPermission());
        attachCopy.setCoiAttachmentType(attachment.getCoiAttachmentType());
        attachCopy.setCoiDisclosure(attachment.getCoiDisclosure());
        attachCopy.setCoiDisclosureId(attachment.getCoiDisclosureId());
        attachCopy.setCoiDisclosureNumber(attachment.getCoiDisclosureNumber());
        attachCopy.setComments(attachment.getComments());
        attachCopy.setContactEmailAddress(attachment.getContactEmailAddress());
        attachCopy.setContactName(attachment.getContactName());
        attachCopy.setContactPhoneNumber(attachment.getContactPhoneNumber());
        attachCopy.setCreateTimestamp(attachment.getCreateTimestamp());
        attachCopy.setDescription(attachment.getDescription());
        attachCopy.setDocumentId(attachment.getDocumentId());
        attachCopy.setDocumentStatusCode(attachment.getDocumentStatusCode());
        attachCopy.setEventTypeCode(attachment.getEventTypeCode());
        attachCopy.setFileId(attachment.getFileId());
        attachCopy.setFinancialEntityId(attachment.getFinancialEntityId());
        attachCopy.setOriginalCoiDisclosureId(attachment.getOriginalCoiDisclosureId());
        attachCopy.setProjectId(attachment.getProjectId());
        attachCopy.setSequenceNumber(attachment.getSequenceNumber());
        attachCopy.setTypeCode(attachment.getTypeCode());
        
        attachCopy.setUpdateTimestamp(attachment.getUpdateTimestamp());
        attachCopy.setUpdateUser(attachment.getUpdateUser());
        attachCopy.setUpdateUserFullName(attachment.getUpdateUserFullName());
        attachCopy.setVersionNumber(attachment.getVersionNumber());
        attachCopy.setObjectId(attachment.getObjectId());
        
        return attachCopy;
    }  
    
    
    private CoiDisclosureNotepad copyCoiDisclosureNotepad(CoiDisclosureNotepad notepad) {
        CoiDisclosureNotepad notesCopy = new CoiDisclosureNotepad();
        
        notesCopy.setCoiDisclosure(notepad.getCoiDisclosure());
        notesCopy.setCoiDisclosureId(notepad.getCoiDisclosureId());
        notesCopy.setCoiDisclosureNumber(notepad.getCoiDisclosureNumber());
        notesCopy.setComments(notepad.getComments());
        notesCopy.setEditable(notepad.isEditable());
        notesCopy.setEntryNumber(notepad.getEntryNumber());
        notesCopy.setEventTypeCode(notepad.getEventTypeCode());
        notesCopy.setFinancialEntityId(notepad.getFinancialEntityId());
        notesCopy.setId(notepad.getId());
        notesCopy.setNoteTopic(notepad.getNoteTopic());
        notesCopy.setObjectId(notepad.getObjectId());
        notesCopy.setOriginalCoiDisclosure(notepad.getOriginalCoiDisclosure());
        notesCopy.setOriginalCoiDisclosureId(notepad.getOriginalCoiDisclosureId());
        notesCopy.setProjectId(notepad.getProjectId());
        notesCopy.setRestrictedView(notepad.getRestrictedView());
        notesCopy.setSequenceNumber(notepad.getSequenceNumber());
        notesCopy.setUpdateTimestamp(notepad.getUpdateTimestamp());
        notesCopy.setUpdateUser(notepad.getUpdateUser());
        notesCopy.setUpdateUserFullName(notepad.getUpdateUserFullName());
        notesCopy.setVersionNumber(notepad.getVersionNumber());
        
        return notesCopy;
    }
}


