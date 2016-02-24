/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.coi.disclosure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureDao;
import org.kuali.kra.coi.lookup.dao.CoiDisclosureUndisclosedEventsDao;
import org.kuali.kra.coi.notesandattachments.attachments.CoiDisclosureAttachment;
import org.kuali.kra.coi.notesandattachments.notes.CoiDisclosureNotepad;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.protocol.IacucProtocolType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.DisclosureEventTypeConstants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.ProtocolType;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.common.framework.medusa.MedusaNode;
import org.kuali.coeus.common.framework.medusa.MedusaService;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;



public class CoiDisclosureServiceImpl implements CoiDisclosureService {
    private static final Log LOG = LogFactory.getLog(CoiDisclosureServiceImpl.class);

    public static final String UNIT_AGENDA_TYPE_ID = "KC1000";

    private BusinessObjectService businessObjectService;
    private DataObjectService dataObjectService;
    private KcPersonService kcPersonService;
    private FinancialEntityService financialEntityService;
    private VersioningService versioningService;
    private ParameterService parameterService;
    private DateTimeService dateTimeService;
    private CoiDisclosureDao coiDisclosureDao;
    private CoiDisclosureUndisclosedEventsDao coiDisclosureUndisclosedEventsDao;
    private MedusaService medusaService;
    private KrmsRulesExecutionService krmsRulesExecutionService;

    
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

    @Override
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

    @Override
    public void addDisclosureReporterUnit(DisclosureReporter disclosureReporter , DisclosureReporterUnit newDisclosureReporterUnit) {
        
        List<DisclosureReporterUnit> disclosureReporterUnits = (List<DisclosureReporterUnit>)disclosureReporter.getDisclosureReporterUnits();
        if (newDisclosureReporterUnit.isLeadUnitFlag()) {
            resetLeadUnit(disclosureReporterUnits);
            disclosureReporter.setSelectedUnit(disclosureReporterUnits.size());
        }
        disclosureReporterUnits.add(newDisclosureReporterUnit);
    }

    @Override
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

    @Override
    public void resetLeadUnit(DisclosureReporter disclosureReporter) {
        List<? extends DisclosureReporterUnit> disclosureReporterUnits = disclosureReporter.getDisclosureReporterUnits();
        if (CollectionUtils.isNotEmpty(disclosureReporterUnits)) {
            resetLeadUnit(disclosureReporterUnits);
            disclosureReporterUnits.get(disclosureReporter.getSelectedUnit()).setLeadUnitFlag(true);
        }
    }

    public boolean setDisclProjectForSave(CoiDisclosure coiDisclosure, MasterDisclosureBean masterDisclosureBean) {
        coiDisclosure.setCoiDisclProjects(new ArrayList<CoiDisclProject>());
        // reset disclosure's disclprojects from master disclosure bean
        if (masterDisclosureBean != null ) {
            for (List<CoiDisclosureProjectBean> projects : masterDisclosureBean.getProjectLists()) {
                for (CoiDisclosureProjectBean coiDisclProject : projects) {
                   // coiDisclProjects.add(createDisclProject(coiDisclosure, coiDisclProject.getProjectId(), coiDisclProject.getProjectName()));
                    coiDisclosure.getCoiDisclProjects().add(coiDisclProject.getCoiDisclProject());
                }
            }        
            return true;
        }
        return false;
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
        try {
            final KcPerson kcPerson = kcPersonService.getKcPersonByPersonId(personId);
        if (kcPerson != null && kcPerson.getUnit() != null) {
            leadUnit = new DisclosurePersonUnit();
            leadUnit.setLeadUnitFlag(true);
            leadUnit.setUnitNumber(kcPerson.getUnit().getUnitNumber());
            leadUnit.setUnitName(kcPerson.getUnit().getUnitName());
            leadUnit.setPersonId(personId);
        }
        }
        catch (IllegalArgumentException e) {
            LOG.info("createLeadUnit: ignoring missing person/entity: " + personId);
        }
        return leadUnit;
    }
    
    @Override
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
            coiDisclProject.setProtocolType((ProtocolType) protocol.getProtocolType());
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
            CoiDisclProject coiDisclProject = createNewCoiDisclProject(coiDisclosure, CoiDisclosureEventType.IACUC_PROTOCOL);
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

    
    @Override
    public void initializeDisclosureDetails(CoiDisclProject coiDisclProject) {
        List<CoiDiscDetail> disclosureDetails = new ArrayList<CoiDiscDetail>();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(coiDisclProject.getCoiDisclosure().getPersonId(), true);
        for (PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            CoiDiscDetail disclosureDetail =createNewCoiDiscDetail(coiDisclProject.getCoiDisclosure(), personFinIntDisclosure, coiDisclProject.getProjectId(), coiDisclProject.getProjectId(), coiDisclProject.getDisclosureEventType());
            disclosureDetail.setCoiDisclProject(coiDisclProject);
            disclosureDetail.setProjectType(coiDisclProject.getDisclosureEventType());
            disclosureDetails.add(disclosureDetail);
        }
        coiDisclProject.setCoiDiscDetails(disclosureDetails);

    }    
    
    /*
     * get moduleitemkey from different project bo
     */
    private String getModuleItemKey(CoiDisclosure coiDisclosure, KcPersistableBusinessObjectBase eventBo) {
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
    
    private String getProjectIdFk(CoiDisclosure coiDisclosure, KcPersistableBusinessObjectBase eventBo) {
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
    private String getProjectTitle(CoiDisclosure coiDisclosure, KcPersistableBusinessObjectBase eventBo) {
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
    private KcPersistableBusinessObjectBase getEventBo(CoiDisclosure coiDisclosure, String projectId) {
        KcPersistableBusinessObjectBase eventBo = null;
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
        DevelopmentProposal currentProposal = dataObjectService.find(DevelopmentProposal.class, proposalNumber);
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

    @Override
    public void updateDisclosureDetails(CoiDisclProject coiDisclProject) {
        // When creating a disclosure. the detail will be created at first
        // TODO : what if FE is deactivate
        Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclProjectId", coiDisclProject.getCoiDisclProjectsId());
        List<String> disclEntityNumbers = new ArrayList<String>();
        List<CoiDiscDetail> activeDetails = new ArrayList<CoiDiscDetail>();
        List<CoiDiscDetail> coiDiscDetails = (List<CoiDiscDetail>) businessObjectService.findMatching(CoiDiscDetail.class, fieldValues);
        String personId = coiDisclProject.getCoiDisclosure().getPersonId();
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
    
    @Override
    public List<Protocol> getProtocols(String personId) {
        return getProtocols(getProtocolPersons(personId));    
    }

    private List<ProtocolPerson> getProtocolPersons(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        List<ProtocolPerson> protocolPersons = (List<ProtocolPerson>) businessObjectService.findMatching(ProtocolPerson.class,
                fieldValues);
        return protocolPersons;
    }
    
    private List<Protocol> getProtocols(List<ProtocolPerson> protocolPersons) {
        List<Protocol> protocols = new ArrayList<Protocol>();
        for (ProtocolPerson protocolPerson : protocolPersons) {
            if (protocolPerson.getProtocol().isActive()
                    && isProtocolDisclosurable((Protocol) protocolPerson.getProtocol())
                    && !isProjectReported(protocolPerson.getProtocol().getProtocolNumber(),
                            CoiDisclosureEventType.IRB_PROTOCOL, protocolPerson.getPersonId())) {
                protocols.add((Protocol) protocolPerson.getProtocol());
            }
        }
        return protocols;
    }
    
    @Override
    public List<IacucProtocol> getIacucProtocols(String personId) {
        return getIacucProtocols(getIacucProtocolPersons(personId));
    }

    private List<IacucProtocolPerson> getIacucProtocolPersons(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        List<IacucProtocolPerson> protocolPersons = (List<IacucProtocolPerson>) businessObjectService.findMatching(IacucProtocolPerson.class,
                        fieldValues);
        return protocolPersons;
    }
    
    private List<IacucProtocol> getIacucProtocols(List<IacucProtocolPerson> protocolPersons) {
        List<IacucProtocol> protocols = new ArrayList<IacucProtocol>();
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
    
    @Override
    public List<DevelopmentProposal> getProposals(String personId) {
        return getProposals(getProposalPersons(personId));
    }
    
    private List<ProposalPerson> getProposalPersons(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        List<ProposalPerson> proposalPersons = (List<ProposalPerson>) dataObjectService.findMatching(ProposalPerson.class,
                QueryByCriteria.Builder.andAttributes(fieldValues).build()).getResults();
        return proposalPersons;
    }
    
    private List<DevelopmentProposal> getProposals(List<ProposalPerson> proposalPersons) {
        List<DevelopmentProposal> proposals = new ArrayList<DevelopmentProposal>();
        for (ProposalPerson proposalPerson : proposalPersons) {
            if (isProposalDisclosurable(proposalPerson.getDevelopmentProposal())
                    && !isProjectReported(proposalPerson.getDevelopmentProposal().getProposalNumber(),
                            CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, proposalPerson.getPersonId())) {
                proposals.add(proposalPerson.getDevelopmentProposal());
            }
        }
        return proposals;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<InstitutionalProposal> getInstitutionalProposals(String personId) {
        return getInstitutionalProposals(getInstituteProposalPersons(personId));
    }
    
    private List<InstitutionalProposalPerson> getInstituteProposalPersons(String personId) {
        Map<String, Object> propFieldValues = new HashMap<String, Object>();
        propFieldValues.put("personId", personId);
        List<InstitutionalProposalPerson> proposalPersons = (List<InstitutionalProposalPerson>) businessObjectService.findMatching(InstitutionalProposalPerson.class, propFieldValues);
        return proposalPersons;
    }
    
    private List<InstitutionalProposal> getInstitutionalProposals(List<InstitutionalProposalPerson> proposalPersons) {
        List<InstitutionalProposal> proposals = new ArrayList<InstitutionalProposal>();
        Map<String, Integer> propNumbers = new HashMap<String, Integer>();
        Map<String, String> propPerson = new HashMap<String, String>();
        for (InstitutionalProposalPerson proposalPerson : proposalPersons) {
            Integer newSequenceNumber = proposalPerson.getSequenceNumber();
            Integer oldSequenceNumber = propNumbers.get(proposalPerson.getProposalNumber());
            if ((oldSequenceNumber == null) || (oldSequenceNumber.compareTo(newSequenceNumber) < 0)) { 
                propNumbers.put(proposalPerson.getProposalNumber(), newSequenceNumber);
                propPerson.put(proposalPerson.getProposalNumber(), proposalPerson.getPersonId());
            }
        }
        for (String propNumber: propNumbers.keySet()) {
            Map<String, Object> ipFieldValues = new HashMap<String, Object>();
            ipFieldValues.put("proposalNumber", propNumber);
            ipFieldValues.put("sequenceNumber", propNumbers.get(propNumber));
            String personId = propPerson.get(propNumber);
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
    
 
    @Override
    public List<Award> getAwards(String personId) {
        return getAwards(getAwardPersons(personId));
    }
    
    private List<AwardPerson> getAwardPersons(String personId) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("personId", personId);
        List<AwardPerson> awardPersons = (List<AwardPerson>) businessObjectService.findMatchingOrderBy(AwardPerson.class,
                fieldValues, "awardNumber", true);
        return awardPersons;
    }
    
    private List<Award> getAwards(List<AwardPerson> awardPersons) {
        List<Award> awards = new ArrayList<Award>();
        for (AwardPerson awardPerson : awardPersons) {
            if (isCurrentAward(awardPerson.getAward())
                    && isAwardDisclosurable(awardPerson.getAward())
                    && !isProjectReported(awardPerson.getAward().getAwardNumber(), CoiDisclosureEventType.AWARD,
                            awardPerson.getPersonId())) {
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
        HashMap<String, Object> fieldValues = new HashMap<String, Object>();
        if (StringUtils.equals(CoiDisclosureEventType.AWARD, projectType)) {
            // all award numbers in that hierarchy.  so any award in that hierarchy is reported, then the others
            // don't have to report.
            fieldValues.put("moduleItemKey", getAwardNumbersForHierarchy(projectId));
        } else {
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
    
    @Override
    public CoiDisclosure versionCoiDisclosure() throws VersionException {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", GlobalVariables.getUserSession().getPrincipalId());

        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) businessObjectService.findMatchingOrderBy(CoiDisclosure.class, fieldValues, "sequenceNumber", false);
        CoiDisclosure newDisclosure = null;
        if (CollectionUtils.isNotEmpty(disclosures)) {
            newDisclosure = versioningService.createNewVersion(disclosures.get(0));
            newDisclosure.setCoiDisclProjects(null);
            newDisclosure.setCoiDisclosureAttachments(null);
            newDisclosure.setCoiDisclosureNotepads(null);
            newDisclosure.setCoiUserRoles(new ArrayList<CoiUserRole>());
            newDisclosure.setCurrentDisclosure(false);
            newDisclosure.setCertificationTimestamp(null);
            newDisclosure.setDisclosureDispositionCode(CoiDispositionStatus.IN_PROGRESS);
            newDisclosure.setDisclosureStatusCode(CoiDisclosureStatus.IN_PROGRESS);
            newDisclosure.setExpirationDate(new Date(DateUtils.addDays(new Date(System.currentTimeMillis()), 365).getTime()));
            newDisclosure.setReviewStatusCode(CoiReviewStatus.IN_PROGRESS);
            newDisclosure.refreshReferenceObject("coiReviewStatus");
        }
        return newDisclosure;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public void setFinancialEntityService(FinancialEntityService financialEntityService) {
        this.financialEntityService = financialEntityService;
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
         for (ProtocolFundingSourceBase fundingSource : protocol.getProtocolFundingSources()) {
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
         for (org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase fundingSource : protocol.getProtocolFundingSources()) {
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
    
    @Override
    public MasterDisclosureBean getMasterDisclosureDetail(CoiDisclosure coiDisclosure) {
        MasterDisclosureBean masterDisclosureBean = new MasterDisclosureBean();
        String moduleItemKey = Constants.EMPTY_STRING;
        String projectType = Constants.EMPTY_STRING;
        CoiDisclosureProjectBean disclosureProjectBean = null;
        if (coiDisclosure.isUpdateEvent() || (coiDisclosure.isAnnualEvent() && coiDisclosure.isAnnualUpdate())) {
            updateMasterDisclosureDetails(coiDisclosure);
        }

        List<AnswerHeader> answerHeaders = new ArrayList<AnswerHeader>();
        CoiDisclosure currentDisclosure = coiDisclosure;
        boolean newMaster = false;
        if (currentDisclosure == null || currentDisclosure.getCoiDisclosureId() == null) {
            // if this is click update discl link
            newMaster = true;
            CoiDisclosure masterDisclosure = getCurrentDisclosure();
            currentDisclosure = masterDisclosure;
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
                        if (newMaster) {
                            versionProjectDisclQuestionnaires(disclosureProjectBean, answerHeaders, currentDisclosure);
                        } else {
                            addProjectDisclQuestionnaires(disclosureProjectBean, answerHeaders, currentDisclosure);
                        }
                    }
                }
            } else {
                disclosureProjectBean = getCoiDisclosureProjectBean(coiDisclProject1);
                masterDisclosureBean.addProject(disclosureProjectBean, coiDisclProject1.getDisclosureEventType());
                    projectType = coiDisclProject1.getDisclosureEventType();
                moduleItemKey = coiDisclProject1.getModuleItemKey();
                if (newMaster) {
                    versionProjectDisclQuestionnaires(disclosureProjectBean, answerHeaders, currentDisclosure);
                } else {
                    addProjectDisclQuestionnaires(disclosureProjectBean, answerHeaders, currentDisclosure);
                }
            }
        }
        
        // unless we are doing an update
        if (coiDisclosure.isApprovedDisclosure() || coiDisclosure.isDisapprovedDisclosure() || (!coiDisclosure.isUpdateEvent() && !coiDisclosure.isAnnualEvent())) {
            setupDisclosures(masterDisclosureBean, coiDisclosure);
        }
        
        createDisclosuresGroupedByEvent(masterDisclosureBean);
        
        return masterDisclosureBean;
    }

    @Override
    public void createDisclosuresGroupedByEvent(MasterDisclosureBean masterDisclosureBean) {
        List<CoiGroupedMasterDisclosureBean> disclosuresGroupedByEvent = new ArrayList<CoiGroupedMasterDisclosureBean>();
        for(CoiDisclosureProjectBean coiDisclosureProjectBean : masterDisclosureBean.getAllDisclosureProjects()) {
            CoiGroupedMasterDisclosureBean coiGroupedMasterDisclosureBean = new CoiGroupedMasterDisclosureBean();
            CoiDisclosure coiDisclosure = null;
            if(coiDisclosureProjectBean.getCoiDisclProject().getOriginalCoiDisclosureId() != null) {
                coiDisclosure = coiDisclosureProjectBean.getCoiDisclProject().getOriginalCoiDisclosure();
            }else {
                coiDisclosure = coiDisclosureProjectBean.getCoiDisclProject().getCoiDisclosure();
            }
            String projectEventTypeDescription =coiDisclosureProjectBean.getCoiDisclProject().getCoiDisclosureEventType().getDescription(); 
            coiGroupedMasterDisclosureBean.setDisclosureEventType(projectEventTypeDescription);
            coiGroupedMasterDisclosureBean.setDisclosureStatus(coiDisclosure.getCoiDisclosureStatus().getDescription());
            coiGroupedMasterDisclosureBean.setDispositionStatus(coiDisclosure.getCoiDispositionStatus().getDescription());
            coiGroupedMasterDisclosureBean.setProjectId(coiDisclosureProjectBean.getCoiDisclProject().getCoiProjectId());
            coiGroupedMasterDisclosureBean.setProjectTitle(coiDisclosureProjectBean.getCoiDisclProject().getCoiProjectTitle());
            coiGroupedMasterDisclosureBean.getAllRelatedProjects().add(coiDisclosureProjectBean);
            disclosuresGroupedByEvent.add(coiGroupedMasterDisclosureBean);
        }
        masterDisclosureBean.setDisclosureGroupedByEvent(true);
        masterDisclosureBean.setAllDisclosuresGroupedByProjects(disclosuresGroupedByEvent);
    }
    
    @Override
    public List<CoiGroupedMasterDisclosureBean> getUndisclosedProjectsGroupedByEvent(List<CoiDisclProject> coiDisclProjects) {
        List<CoiGroupedMasterDisclosureBean> disclosuresGroupedByEvent = new ArrayList<CoiGroupedMasterDisclosureBean>();
        for(CoiDisclProject coiDisclProject : coiDisclProjects) {
            CoiGroupedMasterDisclosureBean coiGroupedMasterDisclosureBean = new CoiGroupedMasterDisclosureBean();
            CoiDisclosureProjectBean coiDisclosureProjectBean = new CoiDisclosureProjectBean();
            coiDisclosureProjectBean.setCoiDisclProject(coiDisclProject);
            coiGroupedMasterDisclosureBean.setDisclosureEventType(coiDisclProject.getCoiDisclosureEventType().getDescription());
            coiGroupedMasterDisclosureBean.setProjectId(coiDisclProject.getCoiProjectId());
            coiGroupedMasterDisclosureBean.setProjectTitle(coiDisclProject.getCoiProjectTitle());
            coiGroupedMasterDisclosureBean.getAllRelatedProjects().add(coiDisclosureProjectBean);
            disclosuresGroupedByEvent.add(coiGroupedMasterDisclosureBean);
        }
        return disclosuresGroupedByEvent;
    }
    
    @Override
    public List<CoiGroupedMasterDisclosureBean> getUndisclosedProjectsGroupedByFinancialEntity(List<CoiDisclProject> coiDisclProjects) {
        List<CoiGroupedMasterDisclosureBean> disclosureGroupedByFinancialEntity = new ArrayList<CoiGroupedMasterDisclosureBean>();
        String personId = coiDisclProjects.get(0).getCoiDiscDetails().get(0).getPersonFinIntDisclosure().getPersonId();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(personId, true);
        for(PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            CoiGroupedMasterDisclosureBean coiGroupedMasterDisclosureBean = getPersonEntityDisclosureBean(personFinIntDisclosure);
            List<CoiDisclosureProjectBean> entityRelatedProjects = new ArrayList<CoiDisclosureProjectBean>();
            for(CoiDisclProject coiDisclProject : coiDisclProjects) {
                List<CoiDiscDetail> coiDiscDetails = coiDisclProject.getCoiDiscDetails();
                for(CoiDiscDetail coiDiscDetail : coiDiscDetails) {
                    if(coiDiscDetail.getPersonFinIntDisclosureId().equals(personFinIntDisclosure.getPersonFinIntDisclosureId())) {
                        CoiDisclosureProjectBean coiDisclosureProjectBean = new CoiDisclosureProjectBean();
                        coiDisclosureProjectBean.setCoiDisclProject(coiDisclProject);
                        entityRelatedProjects.add(coiDisclosureProjectBean);
                        break;
                    }
                }
            }
            coiGroupedMasterDisclosureBean.getAllRelatedProjects().addAll(entityRelatedProjects);
            disclosureGroupedByFinancialEntity.add(coiGroupedMasterDisclosureBean);
        }
        return disclosureGroupedByFinancialEntity;
    }

    @Override
    public void createDisclosuresGroupedByFinancialEntity(CoiDisclosure coiDisclosure, MasterDisclosureBean masterDisclosureBean) {
        List<CoiGroupedMasterDisclosureBean> disclosureGroupedByFinancialEntity = new ArrayList<CoiGroupedMasterDisclosureBean>();
        String personId = coiDisclosure.getPersonId();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(personId, true);
        for(PersonFinIntDisclosure personFinIntDisclosure : financialEntities) {
            disclosureGroupedByFinancialEntity.add(getMasterDisclosureBeanGroupedByFinancialEntity(masterDisclosureBean, personFinIntDisclosure));
        }
        masterDisclosureBean.setAllDisclosuresGroupedByProjects(disclosureGroupedByFinancialEntity);
        masterDisclosureBean.setDisclosureGroupedByEvent(false);
    }
    
    private CoiGroupedMasterDisclosureBean getMasterDisclosureBeanGroupedByFinancialEntity(MasterDisclosureBean masterDisclosureBean, PersonFinIntDisclosure personFinIntDisclosure) {
        CoiGroupedMasterDisclosureBean coiGroupedMasterDisclosureBean = getPersonEntityDisclosureBean(personFinIntDisclosure);
        coiGroupedMasterDisclosureBean.getAllRelatedProjects().addAll(getAllFinancialEntityRelatedProjects(masterDisclosureBean, personFinIntDisclosure));
        return coiGroupedMasterDisclosureBean;
    }
    
    private CoiGroupedMasterDisclosureBean getPersonEntityDisclosureBean(PersonFinIntDisclosure personFinIntDisclosure) {
        CoiGroupedMasterDisclosureBean coiGroupedMasterDisclosureBean = new CoiGroupedMasterDisclosureBean();
        coiGroupedMasterDisclosureBean.setEntityNumber(personFinIntDisclosure.getEntityNumber());
        coiGroupedMasterDisclosureBean.setEntityName(personFinIntDisclosure.getEntityName());
        return coiGroupedMasterDisclosureBean;
    }
    
    private List<CoiDisclosureProjectBean> getAllFinancialEntityRelatedProjects(MasterDisclosureBean masterDisclosureBean, PersonFinIntDisclosure personFinIntDisclosure) {
        List<CoiDisclosureProjectBean> coiDisclosureProjectBeans = new ArrayList<CoiDisclosureProjectBean>();
        for(CoiDisclosureProjectBean coiDisclosureProjectBean : masterDisclosureBean.getAllDisclosureProjects()) {
            List<CoiDiscDetail> coiDiscDetails = coiDisclosureProjectBean.getCoiDisclProject().getCoiDiscDetails();
            for(CoiDiscDetail coiDiscDetail : coiDiscDetails) {
                if(coiDiscDetail.getPersonFinIntDisclosureId().equals(personFinIntDisclosure.getPersonFinIntDisclosureId()) &&
                        coiDiscDetail.getEntityDispositionCode() != null) {
                    coiDisclosureProjectBeans.add(coiDisclosureProjectBean);
                    break;
                }
            }
        }
        return coiDisclosureProjectBeans;
    }
    
    private List<AnswerHeader> retrieveAnswerHeaders(CoiDisclosure coiDisclosure) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put(MODULE_ITEM_CODE, CoeusModule.COI_DISCLOSURE_MODULE_CODE);
        fieldValues.put(MODULE_ITEM_KEY, coiDisclosure.getCoiDisclosureId());
        fieldValues.put(MODULE_SUB_ITEM_KEY, "-1");
        return (List<AnswerHeader>) businessObjectService.findMatching(AnswerHeader.class, fieldValues);
    }
    
    private void addProjectDisclQuestionnaires(CoiDisclosureProjectBean disclosureProjectBean, List<AnswerHeader> answerHeaders, CoiDisclosure originalDisclosure) {
        disclosureProjectBean.populateAnswers(originalDisclosure);
    }
    
    private void versionProjectDisclQuestionnaires(CoiDisclosureProjectBean disclosureProjectBean, List<AnswerHeader> answerHeaders, CoiDisclosure originalDisclosure) {
        disclosureProjectBean.versionDisclosureAnswers(originalDisclosure);
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
        List<CoiDisclProject> coiDisclProjects = getCoiDisclProjects(coiDisclosure);
        disclosureHistoryForView.refreshReferenceObject("coiDisclosure");
        for (CoiDisclosureHistory disclosureHistory : disclosureHistories) {
            if (disclosureHistory.getCoiDisclosureHistoryId().compareTo(disclosureHistoryForView.getCoiDisclosureHistoryId()) <= 0) {
                // only list the history list up to the history associated to the disclosure selected
                // may need to revise this 
                CoiDiscDetail coiDiscDetail = getCurrentProjectDetail(disclosureHistory.getCoiDisclosure());
                CoiDisclosureProjectBean disclosureProjectBean = getCoiDisclosureProjectBean(coiDiscDetail);
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
                String projectId = null;
                String projectTitle = null;
                if(coiDiscDetail != null) {
                    projectId = coiDiscDetail.getCoiDisclProject().getCoiProjectId();
                    projectTitle = coiDiscDetail.getCoiDisclProject().getCoiProjectTitle();
                }else {
                    CoiDisclProject coiDisclProject = getCurrentProject(disclosureHistory.getCoiDisclosure(), coiDisclProjects);
                    if(coiDisclProject != null) {
                        projectId = coiDisclProject.getCoiProjectId();
                        projectTitle = coiDisclProject.getCoiProjectTitle();
                    }
                }
                disclosureProjectBean.setProjectId(projectId);
                disclosureProjectBean.setProjectName(projectTitle);
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
                    return coiDiscDetail;
                }
    
            }
        }
        return null;
    }
    
    protected CoiDisclProject getCurrentProject(CoiDisclosure historyDisclosure, List<CoiDisclProject> coiDisclProjects) {
        CoiDisclProject currentDisclProject = null;
        for (CoiDisclProject coiDisclProject : coiDisclProjects) {
            Long projectDisclosureId = coiDisclProject.getOriginalCoiDisclosureId()== null ? coiDisclProject.getCoiDisclosureId() : coiDisclProject.getOriginalCoiDisclosureId(); 
            if (projectDisclosureId.equals(historyDisclosure.getCoiDisclosureId())){
                currentDisclProject = coiDisclProject;
                break;
            }
        }
        return currentDisclProject;
    }
    
    private List<CoiDisclProject> getCoiDisclProjects(CoiDisclosure coiDisclosure) {
        Map <String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("coiDisclosureNumber", coiDisclosure.getCoiDisclosureNumber());
        List<CoiDisclProject> coiDisclProjects = (List<CoiDisclProject>) businessObjectService.findMatching(CoiDisclProject.class, fieldValues);
        return coiDisclProjects;
    }
    
    /*
     * set up form bean for each project
     */
    protected CoiDisclosureProjectBean getCoiDisclosureProjectBean(CoiDiscDetail coiDiscDetail) {
        CoiDisclosureProjectBean disclosureProjectBean = new CoiDisclosureProjectBean();
        if (ObjectUtils.isNotNull(coiDiscDetail)) {
            disclosureProjectBean.setCoiDisclProject(coiDiscDetail.getCoiDisclProject());
            if (disclosureProjectBean.getCoiDisclProject().isManualEvent()) {
                disclosureProjectBean.getCoiDisclProject().initHeaderItems();
            }
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
    public CoiDisclosure getCurrentDisclosure() {
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
                copiedAnswerHeader.setId(null);
                for (Answer answer : copiedAnswerHeader.getAnswers()) {
                    answer.setId(null);
                }
                newAnswerHeaders.add(copiedAnswerHeader);
        }
        return newAnswerHeaders;
    }
    
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
                List<CoiDiscDetail> coiDiscDetails = coiDisclProject.getCoiDiscDetails();
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
        }
        coiDisclosure.setCoiDisclProjects(copiedDisclProjects);
    }
    
    private void copyDisclosureNotePads(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
        for (CoiDisclosureNotepad coiDisclosureNotepad : masterCoiDisclosure.getCoiDisclosureNotepads()) {
                CoiDisclosureNotepad copiedCoiDisclosureNotepad = copyCoiDisclosureNotepad(coiDisclosureNotepad);
                copiedCoiDisclosureNotepad.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedCoiDisclosureNotepad.setId(null);
                if (copiedCoiDisclosureNotepad.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureNotepad.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDisclosureNotepads().add(copiedCoiDisclosureNotepad);
        }
    }

    private void copyDisclosureAttachments(CoiDisclosure masterCoiDisclosure, CoiDisclosure coiDisclosure) {
        // may also need to add note/attachment to new master disclosure
        for (CoiDisclosureAttachment coiDisclosureAttachment : masterCoiDisclosure.getCoiDisclosureAttachments()) {
                CoiDisclosureAttachment copiedCoiDisclosureAttachment = copyCoiDisclosureAttachment(coiDisclosureAttachment);
                copiedCoiDisclosureAttachment.setSequenceNumber(coiDisclosure.getSequenceNumber());
                copiedCoiDisclosureAttachment.setAttachmentId(null);
                copiedCoiDisclosureAttachment.setFileId(coiDisclosureAttachment.getFileId());
                copiedCoiDisclosureAttachment.setFile(coiDisclosureAttachment.getFile());
                if (copiedCoiDisclosureAttachment.getOriginalCoiDisclosureId() == null) {
                    copiedCoiDisclosureAttachment.setOriginalCoiDisclosureId(masterCoiDisclosure.getCoiDisclosureId());
                }
                coiDisclosure.getCoiDisclosureAttachments().add(copiedCoiDisclosureAttachment);
        }
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
    
    
    /*
     * This method is compare the person FE and the FE disclosed.  If there is any addition/activate/inactivate,
     * then the disclosed FE should be updated accordingly.
     * This is mainly for 'Update Disclosure' or update annual disclosure
     */
    private void updateMasterDisclosureDetails(CoiDisclosure coiDisclosure) {
        Map <Long, List<CoiDiscDetail>> projectDetailMap = setupDetailMap(coiDisclosure);
        String personId = coiDisclosure.getPersonId();
        List<PersonFinIntDisclosure> financialEntities = financialEntityService.getFinancialEntities(personId, true);
        Long disclosureId = coiDisclosure.getCoiDisclosureId();
        for (CoiDisclProject coiDisclProject : coiDisclosure.getCoiDisclProjects()) {

            List<CoiDiscDetail> coiDiscDetails = new ArrayList<CoiDiscDetail>();
            if(coiDisclProject.getCoiDiscDetails().size() == 0 && financialEntities.size() > 0) {
                initializeDisclosureDetails(coiDisclProject);
                checkToAddNewFinancialEntity(financialEntities, coiDiscDetails, disclosureId, coiDisclosure,
                        projectDetailMap.get(disclosureId), coiDisclProject);
            } else {
                if (!coiDisclProject.getCoiDisclosureEventType().isExcludeFromMasterDisclosure()) {
                    checkToAddNewFinancialEntity(financialEntities, coiDiscDetails, disclosureId, coiDisclosure,
                            coiDisclProject.getCoiDiscDetails(), coiDisclProject);
                    for (CoiDiscDetail coiDiscDetail : coiDisclProject.getCoiDiscDetails()) {
                        getCurrentFinancialEntity(coiDiscDetail);
                        if (coiDiscDetail.getPersonFinIntDisclosure().isStatusActive()
                                && coiDiscDetail.getPersonFinIntDisclosure().isCurrentFlag()) {
                            coiDiscDetails.add(coiDiscDetail);
                        }
                    }
                }
            }
            //if there aren't new coiDiscDetails, then we don't need to update.
            if(coiDiscDetails.size() > 0) {
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
            
            if(!(projectDetails == null)) {
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
            } else {
                projectType = coiDisclProject.getCoiDiscDetails().get(0).getProjectType();
                projectIdFk = coiDisclProject.getCoiDiscDetails().get(0).getProjectIdFk();
                moduleItemKey = coiDisclProject.getCoiDiscDetails().get(0).getModuleItemKey();
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
        notesCopy.setFinancialEntity(notepad.getFinancialEntity());
        notesCopy.setId(notepad.getId());
        notesCopy.setNoteTopic(notepad.getNoteTopic());
        notesCopy.setNoteTypeCode(notepad.getNoteTypeCode());
        notesCopy.setNoteType(notepad.getNoteType());
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
    
    
    @Override
    public void populateProposalsAndAwardToCompleteDisclosure(String userId, DisclosureHelper disclosureHelper) {
        List<DevelopmentProposal> initDevProposalsToCompleteDiscl = getProposals(userId);
        List<InstitutionalProposal> initInstProposalsToCompleteDiscl = getInstitutionalProposals(userId);
        List<Award> initAwardsToCompleteDiscl = getAwards(userId);

        List<CoiDisclosedProjectBean> disclosedProjects = new ArrayList<CoiDisclosedProjectBean>();
        disclosedProjects.addAll(getDisclosedProjectsBasedOnDevelopmentProposalLink(initDevProposalsToCompleteDiscl, userId));
        disclosedProjects.addAll(getDisclosedProjectsBasedOnInstituteProposalLink(initInstProposalsToCompleteDiscl, userId));
        disclosedProjects.addAll(getDisclosedProjectsBasedOnAwardLink(initAwardsToCompleteDiscl, userId));
        
        CoiProjectsToCompleteDisclosureBean projectsToCompleteDisclosure = getRevisedProposalsAndAwardsToCompleteDisclosure(disclosedProjects, initDevProposalsToCompleteDiscl, initInstProposalsToCompleteDiscl, initAwardsToCompleteDiscl);
        disclosureHelper.setNewProposals(projectsToCompleteDisclosure.getDevProposalsToDisclose());
        disclosureHelper.setNewInstitutionalProposals(projectsToCompleteDisclosure.getInstituteProposalsToDisclose());
        disclosureHelper.setNewAwards(projectsToCompleteDisclosure.getAwardsToDisclose());
    }
    
    /**
     * This method is to set a revised list of proposals and awards to complete disclosure.
     * Compare the initial list with the disclosed chain of proposals and awards and remove
     * those that are already disclosed as part of the proposal-award link.
     * @param disclosedProjects
     * @param initDevProposalsToCompleteDiscl
     * @param initInstProposalsToCompleteDiscl
     * @param initAwardsToCompleteDiscl
     */
    private CoiProjectsToCompleteDisclosureBean getRevisedProposalsAndAwardsToCompleteDisclosure(List<CoiDisclosedProjectBean> disclosedProjects, 
            List<DevelopmentProposal> initDevProposalsToCompleteDiscl, List<InstitutionalProposal> initInstProposalsToCompleteDiscl, List<Award> initAwardsToCompleteDiscl) {
        CoiProjectsToCompleteDisclosureBean projectsToCompleteDisclosure = new CoiProjectsToCompleteDisclosureBean();
        List<DevelopmentProposal> disclosedDevProposals = new ArrayList<DevelopmentProposal>();
        List<InstitutionalProposal> disclosedInstProposals = new ArrayList<InstitutionalProposal>();
        List<Award> disclosedAwards = new ArrayList<Award>();
        for(CoiDisclosedProjectBean disclosedProjectBean : disclosedProjects) {
            if(disclosedProjectBean.isProjectDisclosed()) {
                disclosedDevProposals.addAll(disclosedProjectBean.getDisclosedDevProposals());
                disclosedInstProposals.addAll(disclosedProjectBean.getDisclosedInstProposals());
                disclosedAwards.addAll(disclosedProjectBean.getDisclosedAwards());
            }
        }
        
        projectsToCompleteDisclosure.setDevProposalsToDisclose(getRevisedDevProposalToCompleteDiscl(initDevProposalsToCompleteDiscl, disclosedDevProposals));
        projectsToCompleteDisclosure.setInstituteProposalsToDisclose(getRevisedInstProposalToCompleteDiscl(initInstProposalsToCompleteDiscl, disclosedInstProposals));
        projectsToCompleteDisclosure.setAwardsToDisclose(getRevisedAwardsToCompleteDiscl(initAwardsToCompleteDiscl, disclosedAwards));
        return projectsToCompleteDisclosure;
    }
    
    /**
     * This method is to find disclosed projects based on medusa link by traversing through
     * each development proposal where a disclosure is required.
     * The requirement is that there needs to be only one disclosure required in the chain.
     * Say for example - if Development proposal is disclosed, then entire link based on that development
     * proposal is considered as disclosed.
     * This applies moving from Development proposal to Award and back.
     * @param devProposalsToCompleteDiscl
     * @param userId
     * @return
     */
    private List<CoiDisclosedProjectBean> getDisclosedProjectsBasedOnDevelopmentProposalLink(List<DevelopmentProposal> devProposalsToCompleteDiscl, 
            String userId) {
        List<CoiDisclosedProjectBean> disclosedProjects = new ArrayList<CoiDisclosedProjectBean>();
        for(DevelopmentProposal devProposal : devProposalsToCompleteDiscl) {
            List<MedusaNode> medusaNodes = getMedusaService().getMedusaByProposal(Constants.DEVELOPMENT_PROPOSAL_MODULE, Long.parseLong(devProposal.getProposalNumber()));
            CoiDisclosedProjectBean disclosedProjectBean = new CoiDisclosedProjectBean();
            populateDisclosedAwardsAndProposals(medusaNodes, userId, disclosedProjectBean);
            disclosedProjects.add(disclosedProjectBean);
        }
        return disclosedProjects;
    }

    /**
     * This method is to find disclosed projects based on medusa link by traversing through
     * each institute proposal where a disclosure is required.
     * The requirement is that there needs to be only one disclosure required in the chain.
     * Say for example - if Development proposal is disclosed, then entire link based on that development
     * proposal is considered as disclosed.
     * This applies moving from Development proposal to Award and back.
     * @param instProposalsToCompleteDiscl
     * @param userId
     * @return
     */
    private List<CoiDisclosedProjectBean> getDisclosedProjectsBasedOnInstituteProposalLink(List<InstitutionalProposal> instProposalsToCompleteDiscl, 
            String userId) {
        List<CoiDisclosedProjectBean> disclosedProjects = new ArrayList<CoiDisclosedProjectBean>();
        for(InstitutionalProposal instProposal : instProposalsToCompleteDiscl) {
            List<MedusaNode> medusaNodes = getMedusaService().getMedusaByProposal(Constants.INSTITUTIONAL_PROPOSAL_MODULE, instProposal.getProposalId());
            CoiDisclosedProjectBean disclosedProjectBean = new CoiDisclosedProjectBean();
            populateDisclosedAwardsAndProposals(medusaNodes, userId, disclosedProjectBean);
            disclosedProjects.add(disclosedProjectBean);
        }
        return disclosedProjects;
    }

    /**
     * This method is to find disclosed projects based on medusa link by traversing through
     * each award where a disclosure is required.
     * The requirement is that there needs to be only one disclosure required in the chain.
     * Say for example - if Development proposal is disclosed, then entire link based on that development
     * proposal is considered as disclosed.
     * This applies moving from Development proposal to Award and back.
     * @param awardsToCompleteDiscl
     * @param userId
     * @return
     */
    private List<CoiDisclosedProjectBean> getDisclosedProjectsBasedOnAwardLink(List<Award> awardsToCompleteDiscl, 
            String userId) {
        List<CoiDisclosedProjectBean> disclosedProjects = new ArrayList<CoiDisclosedProjectBean>();
        for(Award award : awardsToCompleteDiscl) {
            List<MedusaNode> medusaNodes = getMedusaService().getMedusaByAward(Constants.AWARD_MODULE, award.getAwardId());
            CoiDisclosedProjectBean disclosedProjectBean = new CoiDisclosedProjectBean();
            populateDisclosedAwardsAndProposals(medusaNodes, userId, disclosedProjectBean);
            disclosedProjects.add(disclosedProjectBean);
        }
        return disclosedProjects;
    }
    
    /**
     * This method is to get a revised list of awards to complete disclosure.
     * We need to eliminate awards that are already disclosed as part of the
     * proposal-award link.
     * @param initAwardsToCompleteDiscl
     * @param disclosedAwards
     * @return
     */
    private List<Award> getRevisedAwardsToCompleteDiscl(List<Award> initAwardsToCompleteDiscl, List<Award> disclosedAwards) {
        List<Award> revisedAwardsToCompleteDiscl = initAwardsToCompleteDiscl;
        List<Award> awardsDisclosed = new ArrayList<Award>();
        for(Award initAward : revisedAwardsToCompleteDiscl) {
            for(Award disclAward : disclosedAwards) {
                if(initAward.getAwardNumber().equals(disclAward.getAwardNumber())) {
                    awardsDisclosed.add(initAward);
                    break;
                }
            }
        }
        revisedAwardsToCompleteDiscl.removeAll(awardsDisclosed);
        return revisedAwardsToCompleteDiscl;
    }

    /**
     * This method is to get a revised list of institute proposals to complete disclosure.
     * We need to eliminate awards that are already disclosed as part of the
     * proposal-award link.
     * @param initInstProposalsToCompleteDiscl
     * @param disclosedInstProposals
     * @return
     */
    private List<InstitutionalProposal> getRevisedInstProposalToCompleteDiscl(List<InstitutionalProposal> initInstProposalsToCompleteDiscl, List<InstitutionalProposal> disclosedInstProposals) {
        List<InstitutionalProposal> revisedProposalToCompleteDiscl = initInstProposalsToCompleteDiscl;
        List<InstitutionalProposal> proposalsDisclosed = new ArrayList<InstitutionalProposal>();
        for(InstitutionalProposal initInstProposal : revisedProposalToCompleteDiscl) {
            for(InstitutionalProposal disclInstProposal : disclosedInstProposals) {
                if(initInstProposal.getProposalNumber().equals(disclInstProposal.getProposalNumber())) {
                    proposalsDisclosed.add(initInstProposal);
                    break;
                }
            }
        }
        revisedProposalToCompleteDiscl.removeAll(proposalsDisclosed);
        return revisedProposalToCompleteDiscl;
    }

    /**
     * This method is to get a revised list of development proposals to complete disclosure.
     * We need to eliminate awards that are already disclosed as part of the
     * proposal-award link.
     * @param initDevProposalsToCompleteDiscl
     * @param disclosedDevProposals
     * @return
     */
    private List<DevelopmentProposal> getRevisedDevProposalToCompleteDiscl(List<DevelopmentProposal> initDevProposalsToCompleteDiscl, List<DevelopmentProposal> disclosedDevProposals) {
        List<DevelopmentProposal> revisedProposalToCompleteDiscl = initDevProposalsToCompleteDiscl;
        List<DevelopmentProposal> proposalsDisclosed = new ArrayList<DevelopmentProposal>();
        for(DevelopmentProposal initDevProposal : revisedProposalToCompleteDiscl) {
            for(DevelopmentProposal disclDevProposal : disclosedDevProposals) {
                if(initDevProposal.getProposalNumber().equals(disclDevProposal.getProposalNumber())) {
                    proposalsDisclosed.add(initDevProposal);
                    break;
                }
            }
        }
        revisedProposalToCompleteDiscl.removeAll(proposalsDisclosed);
        return revisedProposalToCompleteDiscl;
    }
    
    /**
     * This method is to get all nodes in medusa.
     * @param medusaNodes
     * @param userId
     * @param projectDisclosureHelper
     */
    private void populateDisclosedAwardsAndProposals(List<MedusaNode> medusaNodes, String userId, CoiDisclosedProjectBean projectDisclosureHelper) {
        for(MedusaNode medusaNode : medusaNodes) {
            addAwardAndProposalNodes(medusaNode, userId, projectDisclosureHelper);
            populateDisclosedAwardsAndProposals((List<MedusaNode>) medusaNode.getChildNodes(), userId, projectDisclosureHelper);
        }
    }

    /**
     * This method is to separate all proposal and award nodes.
     * Also to identify any of these in the chain are already disclosed.
     * @param medusaNode
     * @param userId
     * @param projectDisclosureHelper
     */
    private void addAwardAndProposalNodes(MedusaNode medusaNode, String userId, CoiDisclosedProjectBean projectDisclosureHelper) {
        boolean isAwardOrProposalDisclosed = false;
        if (StringUtils.equals(medusaNode.getType(), Constants.AWARD_MODULE)) {
            Award award = (Award)medusaNode.getBo();
            projectDisclosureHelper.getDisclosedAwards().add(award);
            isAwardOrProposalDisclosed = isProjectReported(award.getAwardNumber(),
                    CoiDisclosureEventType.AWARD, userId);
        }else if(StringUtils.equals(medusaNode.getType(), Constants.DEVELOPMENT_PROPOSAL_MODULE)) {
            DevelopmentProposal devProposal = (DevelopmentProposal)medusaNode.getBo();
            projectDisclosureHelper.getDisclosedDevProposals().add(devProposal);
            isAwardOrProposalDisclosed = isProjectReported(devProposal.getProposalNumber(),
                    CoiDisclosureEventType.DEVELOPMENT_PROPOSAL, userId);
        }else if(StringUtils.equals(medusaNode.getType(), Constants.INSTITUTIONAL_PROPOSAL_MODULE)) {
            InstitutionalProposal instProposal = (InstitutionalProposal)medusaNode.getBo();
            projectDisclosureHelper.getDisclosedInstProposals().add(instProposal);
            isAwardOrProposalDisclosed = isProjectReported(instProposal.getProposalNumber(),
                    CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL, userId);
        }
        isAwardOrProposalDisclosed |= projectDisclosureHelper.isProjectDisclosed();
        projectDisclosureHelper.setProjectDisclosed(isAwardOrProposalDisclosed);
    }
    
    public MedusaService getMedusaService() {
        if(ObjectUtils.isNull(medusaService)) {
            this.medusaService = KcServiceLocator.getService(MedusaService.class);
        }
        return medusaService;
    }

    public void setMedusaService(MedusaService medusaService) {
        this.medusaService = medusaService;
    }

    @Override
    public List<CoiDisclosureUndisclosedEvents> getUndisclosedEvents(Map<String, String> searchCriteria) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        String eventType = searchCriteria.get(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_EVENT_TYPE);
        DisclosureEventTypeConstants selectedEventType = null;
        if(StringUtils.isBlank(eventType)) {
            selectedEventType = DisclosureEventTypeConstants.AllEvents;
        }else {
            selectedEventType = DisclosureEventTypeConstants.valueOf(eventType);
        }
        
        switch(selectedEventType) {
            case AllEvents:
                undisclosedEvents = getAllUndisclosedEvents(searchCriteria);
                break;
            case DevelopmentProposal:
                undisclosedEvents = getDevelopmentProposalUndisclosedEvents(searchCriteria);
                break;
            case InstituteProposal:
                undisclosedEvents = getInstitutionalProposalUndisclosedEvents(searchCriteria);
                break;
            case Award:
                undisclosedEvents = getAwardUndisclosedEvents(searchCriteria);
                break;
            case IRBProtocol:
                undisclosedEvents = getIrbProtocolUndisclosedEvents(searchCriteria);
                break;
            case IACUCProtocol:
                undisclosedEvents = getIacucProtocolUndisclosedEvents(searchCriteria);
                break;
        }
        return undisclosedEvents;
    }

    /**
     * This method is to identify the events that needs disclosure
     * @param selectedEventType
     * @param searchCriteria
     * @return
     */
    private List<CoiProjectsToCompleteDisclosureBean> getAllProjectsToDisclose(DisclosureEventTypeConstants selectedEventType, Map<String, String> searchCriteria) {
        Map<String, List<ProposalPerson>> personAndDevelopmentProposals = new HashMap<String, List<ProposalPerson>>();
        Map<String, List<InstitutionalProposalPerson>> personAndInstituteProposals = new HashMap<String, List<InstitutionalProposalPerson>>();
        Map<String, List<AwardPerson>> personAndAwards = new HashMap<String, List<AwardPerson>>();
        Map<String, List<ProtocolPerson>> personAndIrbProtocols = new HashMap<String, List<ProtocolPerson>>();
        Map<String, List<IacucProtocolPerson>> personAndIacucProtocols = new HashMap<String, List<IacucProtocolPerson>>();

        List<CoiProjectsToCompleteDisclosureBean> allProjectsToDisclose = new ArrayList<CoiProjectsToCompleteDisclosureBean>();
        
        switch(selectedEventType) {
            case AllEvents:
                personAndDevelopmentProposals = getDevelopmentProposalPersonsForUndisclosedEvents(searchCriteria);
                personAndInstituteProposals = getInstitutionalProposalPersonsForUndisclosedEvents(searchCriteria);
                personAndAwards = getAwardPersonsForUndisclosedEvents(searchCriteria);
                personAndIrbProtocols = getIrbProtocolPersonsForUndisclosedEvents(searchCriteria);
                personAndIacucProtocols = getIacucProtocolPersonsForUndisclosedEvents(searchCriteria);
                break;
            case DevelopmentProposal:
                personAndDevelopmentProposals = getDevelopmentProposalPersonsForUndisclosedEvents(searchCriteria);
                break;
            case InstituteProposal:
                personAndInstituteProposals = getInstitutionalProposalPersonsForUndisclosedEvents(searchCriteria);
                break;
            case Award:
                personAndAwards = getAwardPersonsForUndisclosedEvents(searchCriteria);
                break;
            case IRBProtocol:
                personAndIrbProtocols = getIrbProtocolPersonsForUndisclosedEvents(searchCriteria);
                break;
            case IACUCProtocol:
                personAndIacucProtocols = getIacucProtocolPersonsForUndisclosedEvents(searchCriteria);
                break;
        }
        allProjectsToDisclose = getAllProjectsToCompleteDisclosure(personAndDevelopmentProposals, personAndInstituteProposals, personAndAwards, personAndIrbProtocols, personAndIacucProtocols);
        return allProjectsToDisclose;
        
    }
    
    /**
     * This method is to get all projects required to complete disclosure for each person
     * @param personAndDevelopmentProposals
     * @param personAndInstituteProposals
     * @param personAndAwards
     * @return
     */
    private List<CoiProjectsToCompleteDisclosureBean> getAllProjectsToCompleteDisclosure(Map<String, List<ProposalPerson>> personAndDevelopmentProposals, Map<String, List<InstitutionalProposalPerson>> personAndInstituteProposals,
            Map<String, List<AwardPerson>> personAndAwards, Map<String, List<ProtocolPerson>> personAndIrbProtocols, Map<String, List<IacucProtocolPerson>> personAndIacucProtocols) {
        
        HashMap<String, String> allProjectPersonsForDisclosure = getAllProjectPersonsForUndisclosedEvents(personAndDevelopmentProposals, personAndInstituteProposals, personAndAwards, personAndIrbProtocols, personAndIacucProtocols); 
        Set<String> allProjectPersons = allProjectPersonsForDisclosure.keySet();
        List<CoiDisclosedProjectBean> disclosedProjects = new ArrayList<CoiDisclosedProjectBean>();
        List<DevelopmentProposal> initDevProposalsToCompleteDiscl = new ArrayList<DevelopmentProposal>();
        List<InstitutionalProposal> initInstProposalsToCompleteDiscl = new ArrayList<InstitutionalProposal>();
        List<Award> initAwardsToCompleteDiscl = new ArrayList<Award>();
        List<CoiProjectsToCompleteDisclosureBean> allProjectsToDisclose = new ArrayList<CoiProjectsToCompleteDisclosureBean>();
        for (String personId : allProjectPersons) {
            List<ProposalPerson> devProposalPersons = personAndDevelopmentProposals.get(personId);
            List<InstitutionalProposalPerson> instProposalPersons = personAndInstituteProposals.get(personId);
            List<AwardPerson> awardPersons = personAndAwards.get(personId);

            if(devProposalPersons != null && !devProposalPersons.isEmpty()) {
                initDevProposalsToCompleteDiscl = getProposals(devProposalPersons);
            }
            if(instProposalPersons != null && !instProposalPersons.isEmpty()) {
                initInstProposalsToCompleteDiscl = getInstitutionalProposals(instProposalPersons);
            }
            if(awardPersons != null && !awardPersons.isEmpty()) {
                initAwardsToCompleteDiscl = getAwards(awardPersons);
            }

            disclosedProjects.addAll(getDisclosedProjectsBasedOnDevelopmentProposalLink(initDevProposalsToCompleteDiscl, personId));
            disclosedProjects.addAll(getDisclosedProjectsBasedOnInstituteProposalLink(initInstProposalsToCompleteDiscl, personId));
            disclosedProjects.addAll(getDisclosedProjectsBasedOnAwardLink(initAwardsToCompleteDiscl, personId));

            CoiProjectsToCompleteDisclosureBean personProjectsToCompleteDisclosure = getRevisedProposalsAndAwardsToCompleteDisclosure(disclosedProjects, initDevProposalsToCompleteDiscl, initInstProposalsToCompleteDiscl, initAwardsToCompleteDiscl);
            personProjectsToCompleteDisclosure.setPersonId(personId);
            personProjectsToCompleteDisclosure.setPersonName(allProjectPersonsForDisclosure.get(personId));
            
            List<ProtocolPerson> irbProtocolPersons = personAndIrbProtocols.get(personId);
            List<Protocol> initIrbProtocolsToCompleteDiscl = new ArrayList<Protocol>();
            if(irbProtocolPersons != null && !irbProtocolPersons.isEmpty()) {
                initIrbProtocolsToCompleteDiscl = getProtocols(irbProtocolPersons);
            }
            personProjectsToCompleteDisclosure.setIrbProtocolsToDisclose(initIrbProtocolsToCompleteDiscl);

            List<IacucProtocolPerson> iacucProtocolPersons = personAndIacucProtocols.get(personId);
            List<IacucProtocol> initIacucProtocolsToCompleteDiscl = new ArrayList<IacucProtocol>();
            if(iacucProtocolPersons != null && !iacucProtocolPersons.isEmpty()) {
                initIacucProtocolsToCompleteDiscl = getIacucProtocols(iacucProtocolPersons);
            }
            personProjectsToCompleteDisclosure.setIacucProtocolsToDisclose(initIacucProtocolsToCompleteDiscl);

            allProjectsToDisclose.add(personProjectsToCompleteDisclosure);
        }
        return allProjectsToDisclose;
    }
    

    /**
     * This method is to get a complete list of all project persons
     * @param personAndDevelopmentProposals
     * @param personAndInstituteProposals
     * @param personAndAwards
     * @param personAndIrbProtocols
     * @param personAndIacucProtocols
     * @return
     */
    private HashMap<String, String> getAllProjectPersonsForUndisclosedEvents(Map<String, List<ProposalPerson>> personAndDevelopmentProposals, Map<String, List<InstitutionalProposalPerson>> personAndInstituteProposals,
            Map<String, List<AwardPerson>> personAndAwards, Map<String, List<ProtocolPerson>> personAndIrbProtocols, Map<String, List<IacucProtocolPerson>> personAndIacucProtocols) {
        HashMap<String, String> allProjectPersons = new HashMap<String, String>();
        
        for (Map.Entry<String, List<ProposalPerson>> person : personAndDevelopmentProposals.entrySet()) {
            String personId = person.getKey();
            String personName = person.getValue().get(0).getFullName();
            allProjectPersons.put(personId, personName);
        }
        
        for (Map.Entry<String, List<InstitutionalProposalPerson>> person : personAndInstituteProposals.entrySet()) {
            String personId = person.getKey();
            String personName = person.getValue().get(0).getFullName();
            allProjectPersons.put(personId, personName);
        }

        for (Map.Entry<String, List<AwardPerson>> person : personAndAwards.entrySet()) {
            String personId = person.getKey();
            String personName = person.getValue().get(0).getFullName();
            allProjectPersons.put(personId, personName);
        }
        
        for (Map.Entry<String, List<ProtocolPerson>> person : personAndIrbProtocols.entrySet()) {
            String personId = person.getKey();
            String personName = person.getValue().get(0).getFullName();
            allProjectPersons.put(personId, personName);
        }

        for (Map.Entry<String, List<IacucProtocolPerson>> person : personAndIacucProtocols.entrySet()) {
            String personId = person.getKey();
            String personName = person.getValue().get(0).getFullName();
            allProjectPersons.put(personId, personName);
        }

        return allProjectPersons;
    }
    
    /**
     * This method is to get undisclosed events for all projects.
     * Include prop dev, inst prop, award, irb and iacuc protocols
     * @param searchCriteria
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getAllUndisclosedEvents(Map<String, String> searchCriteria) {
        List<CoiProjectsToCompleteDisclosureBean> allProjectsToCompleteDisclosure = getAllProjectsToDisclose(DisclosureEventTypeConstants.AllEvents, searchCriteria);
        return getFormattedUndsclosedEvents(allProjectsToCompleteDisclosure);
    }
    
    /**
     * This method is to get undisclosed events for development proposal.
     * @param searchCriteria
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getDevelopmentProposalUndisclosedEvents(Map<String, String> searchCriteria) {
        List<CoiProjectsToCompleteDisclosureBean> allProjectsToCompleteDisclosure = getAllProjectsToDisclose(DisclosureEventTypeConstants.DevelopmentProposal, searchCriteria);
        return getFormattedUndsclosedEvents(allProjectsToCompleteDisclosure);
    }
    
    /**
     * This method is to get undisclosed events for institute proposal.
     * @param searchCriteria
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getInstitutionalProposalUndisclosedEvents(Map<String, String> searchCriteria) {
        List<CoiProjectsToCompleteDisclosureBean> allProjectsToCompleteDisclosure = getAllProjectsToDisclose(DisclosureEventTypeConstants.InstituteProposal, searchCriteria);
        return getFormattedUndsclosedEvents(allProjectsToCompleteDisclosure);
    }
    
    /**
     * This method is to get undisclosed events for awards.
     * @param searchCriteria
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getAwardUndisclosedEvents(Map<String, String> searchCriteria) {
        List<CoiProjectsToCompleteDisclosureBean> allProjectsToCompleteDisclosure = getAllProjectsToDisclose(DisclosureEventTypeConstants.Award, searchCriteria);
        return getFormattedUndsclosedEvents(allProjectsToCompleteDisclosure);
    }

    /**
     * This method is to get undisclosed events for irb protocols.
     * @param searchCriteria
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getIrbProtocolUndisclosedEvents(Map<String, String> searchCriteria) {
        List<CoiProjectsToCompleteDisclosureBean> allProjectsToCompleteDisclosure = getAllProjectsToDisclose(DisclosureEventTypeConstants.IRBProtocol, searchCriteria);
        return getFormattedUndsclosedEvents(allProjectsToCompleteDisclosure);
    }
    
    /**
     * This method is to get undisclosed events for iacuc protocols.
     * @param searchCriteria
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getIacucProtocolUndisclosedEvents(Map<String, String> searchCriteria) {
        List<CoiProjectsToCompleteDisclosureBean> allProjectsToCompleteDisclosure = getAllProjectsToDisclose(DisclosureEventTypeConstants.IACUCProtocol, searchCriteria);
        return getFormattedUndsclosedEvents(allProjectsToCompleteDisclosure);
    }
    
    /**
     * This method is to format undisclosed events to display in the result.
     * Look for all projects that requires a disclosure and format it.
     * @param allProjectsToCompleteDisclosure
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getFormattedUndsclosedEvents(List<CoiProjectsToCompleteDisclosureBean> allProjectsToCompleteDisclosure) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        for(CoiProjectsToCompleteDisclosureBean projectsToCompleteDisclosure : allProjectsToCompleteDisclosure) {
            String personId = projectsToCompleteDisclosure.getPersonId();
            String personName = projectsToCompleteDisclosure.getPersonName();
            undisclosedEvents.addAll(getUndisclosedEventsForDevProposal(projectsToCompleteDisclosure.getDevProposalsToDisclose(), personId, personName));
            undisclosedEvents.addAll(getUndisclosedEventsForInstProposal(projectsToCompleteDisclosure.getInstituteProposalsToDisclose(), personId, personName));
            undisclosedEvents.addAll(getUndisclosedEventsForAward(projectsToCompleteDisclosure.getAwardsToDisclose(), personId, personName));
            undisclosedEvents.addAll(getUndisclosedEventsForIrbProtocol(projectsToCompleteDisclosure.getIrbProtocolsToDisclose(), personId, personName));
            undisclosedEvents.addAll(getUndisclosedEventsForIacucProtocol(projectsToCompleteDisclosure.getIacucProtocolsToDisclose(), personId, personName));
        }
        return undisclosedEvents;
    }
    
    /**
     * This method is to build undisclosed events for Development proposal
     * @param devProposalsToDisclose
     * @param personId
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getUndisclosedEventsForDevProposal(List<DevelopmentProposal> devProposalsToDisclose, String personId, String personName) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        for (DevelopmentProposal devProposal : devProposalsToDisclose) {
            String projectId = devProposal.getProjectId();
            String projectTitle = devProposal.getProjectName();
            String createDate = getFormattedCreateDate(devProposal.getUpdateTimestamp());
            String disclosureEventType = DisclosureEventTypeConstants.DevelopmentProposal.description();
            String projectStatus = devProposal.getProposalState().getDescription();
            undisclosedEvents.add(getNewUndisclosedEvent(personId, personName, projectId, projectTitle, disclosureEventType, createDate, projectStatus));
        }
        return undisclosedEvents;
    }

    /**
     * This method is to build undisclosed events for Institutional proposal
     * @param instProposalsToDisclose
     * @param personId
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getUndisclosedEventsForInstProposal(List<InstitutionalProposal> instProposalsToDisclose, String personId, String personName) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        for (InstitutionalProposal instProposal : instProposalsToDisclose) {
            String projectId = instProposal.getProposalNumber();
            String projectTitle = instProposal.getTitle();
            String disclosureEventType = DisclosureEventTypeConstants.InstituteProposal.description();
            String createDate = getFormattedCreateDate(instProposal.getUpdateTimestamp());
            String projectStatus = instProposal.getProposalStatus().getDescription();
            undisclosedEvents.add(getNewUndisclosedEvent(personId, personName, projectId, projectTitle, disclosureEventType, createDate, projectStatus));
        }
        return undisclosedEvents;
    }

    /**
     * This method is to build undisclosed events for Award
     * @param awardsToDisclose
     * @param personId
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getUndisclosedEventsForAward(List<Award> awardsToDisclose, String personId, String personName) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        for (Award award : awardsToDisclose) {
            String projectId = award.getAwardNumber();
            String projectTitle = award.getTitle();
            String disclosureEventType = DisclosureEventTypeConstants.Award.description();
            String createDate = getFormattedCreateDate(award.getUpdateTimestamp());
            String projectStatus = award.getStatusDescription();
            undisclosedEvents.add(getNewUndisclosedEvent(personId, personName, projectId, projectTitle, disclosureEventType, createDate, projectStatus));
        }
        return undisclosedEvents;
    }

    /**
     * This method is to build undisclosed events for IRB protocol
     * @param protocolsToDisclose
     * @param personId
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getUndisclosedEventsForIrbProtocol(List<Protocol> protocolsToDisclose, String personId, String personName) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        for (Protocol protocol : protocolsToDisclose) {
            String projectId = protocol.getProtocolNumber();
            String projectTitle = protocol.getTitle();
            String disclosureEventType = DisclosureEventTypeConstants.IRBProtocol.description();
            String createDate = getFormattedCreateDate(protocol.getUpdateTimestamp());
            String projectStatus = protocol.getProtocolStatus().getDescription();
            undisclosedEvents.add(getNewUndisclosedEvent(personId, personName, projectId, projectTitle, disclosureEventType, createDate, projectStatus));
        }
        return undisclosedEvents;
    }

    /**
     * This method is to build undisclosed events for IACUC protocol
     * @param protocolsToDisclose
     * @param personId
     * @return
     */
    private List<CoiDisclosureUndisclosedEvents> getUndisclosedEventsForIacucProtocol(List<IacucProtocol> protocolsToDisclose, String personId, String personName) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        for (IacucProtocol protocol : protocolsToDisclose) {
            String projectId = protocol.getProtocolNumber();
            String projectTitle = protocol.getTitle();
            String disclosureEventType = DisclosureEventTypeConstants.IACUCProtocol.description();
            String createDate = getFormattedCreateDate(protocol.getUpdateTimestamp());
            String projectStatus = protocol.getProtocolStatus().getDescription();
            undisclosedEvents.add(getNewUndisclosedEvent(personId, personName, projectId, projectTitle, disclosureEventType, createDate, projectStatus));
        }
        return undisclosedEvents;
    }
    
    /**
     * This method is to create a new instance of undisclosed events.
     * Set all values required to display the result.
     * @param personId
     * @param projectId
     * @param projectTitle
     * @param disclosureEventType
     * @return
     */
    private CoiDisclosureUndisclosedEvents getNewUndisclosedEvent(String personId, String personName, String projectId, String projectTitle, String disclosureEventType,
            String createDate, String projectStatus) {
        CoiDisclosureUndisclosedEvents event = new CoiDisclosureUndisclosedEvents();
        event.setPersonId(personId);
        event.setPersonName(personName);
        event.setProjectId(projectId);
        event.setProjectTitle(projectTitle);
        event.setDisclosureEventType(disclosureEventType);
        event.setFormattedCreateDate(createDate);
        event.setProjectStatus(projectStatus);
        return event;
    }
    
    /**
     * This method is to check if there is any criteria that we need to consider
     * @param searchCriteria
     * @return
     */
    private boolean isSearchByCriteriaRequired(Map<String, String> searchCriteria) {
        boolean isCriteriaQueryRequired = false;
        for(String criteriaKey : getAllSearchCriteriasForUndisclosedEvents()) {
            if(StringUtils.isNotBlank(searchCriteria.get(criteriaKey))) {
                isCriteriaQueryRequired = true;
                break;
            }
        }
        return isCriteriaQueryRequired;
    }
    
    /**
     * This method is to get a list of all search criteria to build
     * undisclosed event list.
     * @return
     */
    private Set<String> getAllSearchCriteriasForUndisclosedEvents() {
        Set<String> searchCriterias = new HashSet<String>();
        searchCriterias.add(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_REPORTER);
        searchCriterias.add(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_FROM);
        searchCriterias.add(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_CREATE_DATE_TO);
        searchCriterias.add(CoiDisclosureUndisclosedEvents.SEARCH_CRITERIA_SPONSOR);
        return searchCriterias;
    }
    
    /**
     * This method is to get a list of IRB protocol persons based on search criteria.
     * If not criteria is defined, we need to get all IRB protocol persons
     * Let us return as person and their related iacuc protocols (which is linked to each person).
     * @param searchCriteria
     * @return
     */
    private Map<String, List<ProtocolPerson>> getIrbProtocolPersonsForUndisclosedEvents(Map<String, String> searchCriteria) {
        List<ProtocolPerson> protocolPersons = new ArrayList<ProtocolPerson>();
        if(isSearchByCriteriaRequired(searchCriteria)) {
            protocolPersons = getCoiDisclosureUndisclosedEventsDao().getIrbProtocolPersons(searchCriteria);
        } else {
            protocolPersons = (List<ProtocolPerson>) businessObjectService.findAll(ProtocolPerson.class);
        }    

        Map<String, List<ProtocolPerson>> personAndProtocols = new HashMap<String, List<ProtocolPerson>>();
        List<ProtocolPerson> personProtocols = null;
        for(ProtocolPerson protocolPerson : protocolPersons) {
            String personId = protocolPerson.getPersonId();
            personProtocols = personAndProtocols.get(personId);
            if(personProtocols == null) {
                personProtocols = new ArrayList<ProtocolPerson>();
            }
            personProtocols.add(protocolPerson);
            personAndProtocols.put(personId, personProtocols);
        }
        
        return personAndProtocols;
    }

    /**
     * This method is to get a list of IACUC protocol persons based on search criteria.
     * If not criteria is defined, we need to get all IACUC protocol persons
     * Let us return as person and their related iacuc protocols (which is linked to each person).
     * @param searchCriteria
     * @return
     */
    private Map<String, List<IacucProtocolPerson>> getIacucProtocolPersonsForUndisclosedEvents(Map<String, String> searchCriteria) {
        List<IacucProtocolPerson> protocolPersons = new ArrayList<IacucProtocolPerson>();
        if(isSearchByCriteriaRequired(searchCriteria)) {
            protocolPersons = getCoiDisclosureUndisclosedEventsDao().getIacucProtocolPersons(searchCriteria);
        } else {
            protocolPersons =  (List<IacucProtocolPerson>) businessObjectService.findAll(IacucProtocolPerson.class);
        }    

        Map<String, List<IacucProtocolPerson>> personAndProtocols = new HashMap<String, List<IacucProtocolPerson>>();
        List<IacucProtocolPerson> personProtocols = null;
        for(IacucProtocolPerson protocolPerson : protocolPersons) {
            String personId = protocolPerson.getPersonId();
            personProtocols = personAndProtocols.get(personId);
            if(personProtocols == null) {
                personProtocols = new ArrayList<IacucProtocolPerson>();
            }
            personProtocols.add(protocolPerson);
            personAndProtocols.put(personId, personProtocols);
        }
        
        return personAndProtocols;
    }

    /**
     * This method is to get a list of Development proposal persons based on search criteria.
     * If not criteria is defined, we need to get all Development proposal persons
     * Let us return as person and their related proposals (which is linked to each person).
     * @param searchCriteria
     * @return
     */
    private Map<String, List<ProposalPerson>> getDevelopmentProposalPersonsForUndisclosedEvents(Map<String, String> searchCriteria) {
        List<ProposalPerson> proposalPersons = new ArrayList<ProposalPerson>();
        if(isSearchByCriteriaRequired(searchCriteria)) {
            String personId = searchCriteria.get("personId");
            if (StringUtils.isNotEmpty(personId)) {
                // use DataObjectService for ProposalPerson's since CoiDisclosureUndisclosedEventsDao
                // uses BusinessObjectService, which doesn't work well for JPA
                Map<String, String> limitedFieldValues = new HashMap<String, String>();
                limitedFieldValues.put("personId", personId);
                proposalPersons = (List<ProposalPerson>) dataObjectService.findMatching(ProposalPerson.class,
                        QueryByCriteria.Builder.andAttributes(limitedFieldValues).build()).getResults();
            }
        }
        
        Map<String, List<ProposalPerson>> personAndProposals = new HashMap<String, List<ProposalPerson>>();
        List<ProposalPerson> personProposals = null;
        for(ProposalPerson proposalPerson : proposalPersons) {
            String personId = proposalPerson.getPersonId();
            personProposals = personAndProposals.get(personId);
            if(personProposals == null) {
                personProposals = new ArrayList<ProposalPerson>();
            }
            personProposals.add(proposalPerson);
            personAndProposals.put(personId, personProposals);
        }
        return personAndProposals;
    }

    /**
     * This method is to get a list of Institute proposal persons based on search criteria.
     * If not criteria is defined, we need to get all Institute proposal persons
     * Let us return as person and their related proposals (which is linked to each person).
     * @param searchCriteria
     * @return
     */
    private Map<String, List<InstitutionalProposalPerson>> getInstitutionalProposalPersonsForUndisclosedEvents(Map<String, String> searchCriteria) {
        List<InstitutionalProposalPerson> proposalPersons = new ArrayList<InstitutionalProposalPerson>();
        if(isSearchByCriteriaRequired(searchCriteria)) {
            proposalPersons = getCoiDisclosureUndisclosedEventsDao().getInstituteProposalPersons(searchCriteria);
        } else {
            proposalPersons =  (List<InstitutionalProposalPerson>) businessObjectService.findAll(InstitutionalProposalPerson.class);
        }    
        
        Map<String, List<InstitutionalProposalPerson>> personAndProposals = new HashMap<String, List<InstitutionalProposalPerson>>();
        List<InstitutionalProposalPerson> personProposals = null;
        for(InstitutionalProposalPerson proposalPerson : proposalPersons) {
            String personId = proposalPerson.getPersonId();
            personProposals = personAndProposals.get(personId);
            if(personProposals == null) {
                personProposals = new ArrayList<InstitutionalProposalPerson>();
            }
            personProposals.add(proposalPerson);
            personAndProposals.put(personId, personProposals);
        }
        
        return personAndProposals;
    }

    /**
     * This method is to get a list of Award persons based on search criteria.
     * If not criteria is defined, we need to get all Award persons
     * Let us return as person and their related awards (which is linked to each person).
     * @param searchCriteria
     * @return
     */
    private Map<String, List<AwardPerson>> getAwardPersonsForUndisclosedEvents(Map<String, String> searchCriteria) {
        List<AwardPerson> awardPersons = new ArrayList<AwardPerson>();
        if(isSearchByCriteriaRequired(searchCriteria)) {
            awardPersons = getCoiDisclosureUndisclosedEventsDao().getAwardPersons(searchCriteria);
        } else {
            awardPersons =  (List<AwardPerson>) businessObjectService.findAll(AwardPerson.class);
        }    
        
        Map<String, List<AwardPerson>> personAndAwards = new HashMap<String, List<AwardPerson>>();
        List<AwardPerson> personAwards = null;
        for(AwardPerson awardPerson : awardPersons) {
            String personId = awardPerson.getPersonId();
            personAwards = personAndAwards.get(personId);
            if(personAwards == null) {
                personAwards = new ArrayList<AwardPerson>();
            }
            personAwards.add(awardPerson);
            personAndAwards.put(personId, personAwards);
        }
        
        return personAndAwards;
    }
    
    private String getFormattedCreateDate(Timestamp updateTimeStamp) {
        return (new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT_PATTERN).format(updateTimeStamp));
    }

    public CoiDisclosureUndisclosedEventsDao getCoiDisclosureUndisclosedEventsDao() {
        return coiDisclosureUndisclosedEventsDao;
    }

    public void setCoiDisclosureUndisclosedEventsDao(CoiDisclosureUndisclosedEventsDao coiDisclosureUndisclosedEventsDao) {
        this.coiDisclosureUndisclosedEventsDao = coiDisclosureUndisclosedEventsDao;
    }
    
    public List<CoiDisclosure> getAllDisclosuresForUser(String personId) {
        Map fieldValues = new HashMap();
        fieldValues.put("personId", personId);
        List<CoiDisclosure> disclosures = (List<CoiDisclosure>) businessObjectService.findMatching(CoiDisclosure.class,
            fieldValues);
        return disclosures;
    }

    public boolean checkScreeningQuestionnaireRule(CoiDisclosureDocument coiDisclosureDocument) {
        String krmsRuleId = parameterService.getParameterValueAsString(CoiDisclosureDocument.class, Constants.COI_SCREENING_QUESTIONNAIRE_KRMS_RULE);
        if (StringUtils.isNotBlank(krmsRuleId)) {
            Map<String, Boolean> krmsResults = getKrmsRulesExecutionService().runApplicableRules(Arrays.asList(new String[]{krmsRuleId}), coiDisclosureDocument, UNIT_AGENDA_TYPE_ID);
            Boolean result = krmsResults.get(krmsRuleId);
            if (result == null || !result) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public KrmsRulesExecutionService getKrmsRulesExecutionService() {
        return krmsRulesExecutionService;
    }

    public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
        this.krmsRulesExecutionService = krmsRulesExecutionService;
    }
    
    protected Integer calculateMaximumDispositionStatusCode(CoiDisclosure coiDisclosure) {
        Integer defaultDisposition = getDefaultDispositionStatus(coiDisclosure);
        Integer retval = null;
        for (CoiDisclProject project : coiDisclosure.getCoiDisclProjects()) {
            Integer projectDisposition = null;
            for (CoiDiscDetail details : project.getCoiDiscDetails()) {
                if (projectDisposition == null || (details.getEntityDispositionCode() != null && projectDisposition < details.getEntityDispositionCode())) {
                    projectDisposition = details.getEntityDispositionCode();
                }
            }
            if (projectDisposition != null) {
                project.setDisclosureDispositionCode(projectDisposition);
                project.refreshReferenceObject("coiDispositionStatus");
            }
            if (retval == null || (projectDisposition != null && retval < projectDisposition)) {
                retval = projectDisposition;
            }
        }
        if (retval == null) {
            return getDefaultDispositionStatus(coiDisclosure);
        }
        return retval;
    }
    
    protected Integer getDefaultDispositionStatus(CoiDisclosure coiDisclosure) {
        Integer retval;
        if (coiDisclosure.getDisclosureStatusCode() == null || StringUtils.equals(coiDisclosure.getDisclosureStatusCode(), CoiDisclosureStatus.IN_PROGRESS)) {
            retval = Integer.valueOf(CoiDispositionStatus.IN_PROGRESS);
        } else {
            retval = Integer.valueOf(CoiDispositionStatus.NO_CONFLICT_EXISTS);
        }
        return retval;
    }
    
    public CoiDispositionStatus calculateMaximumDispositionStatus(CoiDisclosure coiDisclosure) {
        return businessObjectService.findBySinglePrimaryKey(CoiDispositionStatus.class, calculateMaximumDispositionStatusCode(coiDisclosure));
    }
    
    public void updateDisclosureAndProjectDisposition(CoiDisclosure coiDisclosure) {
        CoiDispositionStatus disposition = calculateMaximumDispositionStatus(coiDisclosure);
        coiDisclosure.setDisclosureDispositionCode(disposition.getCoiDispositionCode());
        coiDisclosure.setCoiDispositionStatus(disposition);
    }
}


