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
package org.kuali.kra.iacuc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.iacuc.actions.IacucProtocolStatus;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtBean;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtRule;
import org.kuali.kra.iacuc.actions.assignCmt.IacucProtocolAssignCmtRuleImpl;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecision;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionAbstainerRule;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionRecuserRule;
import org.kuali.kra.iacuc.actions.decision.IacucCommitteeDecisionRule;
import org.kuali.kra.iacuc.actions.modifysubmission.IacucProtocolModifySubmissionBean;
import org.kuali.kra.iacuc.actions.modifysubmission.IacucProtocolModifySubmissionRule;
import org.kuali.kra.iacuc.actions.modifysubmission.IacucProtocolModifySubmissionRuleImpl;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmitActionRule;
import org.kuali.kra.iacuc.noteattachment.SubmitIacucProtocolAttachmentProtocolRuleImpl;
import org.kuali.kra.iacuc.personnel.IacucProtocolAttachmentPersonnelRule;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelAuditRule;
import org.kuali.kra.iacuc.personnel.IacucProtocolUnitRule;
import org.kuali.kra.iacuc.personnel.SaveIacucProtocolPersonnelEvent;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSourceAuditRule;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSourceRule;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocationRule;
import org.kuali.kra.iacuc.protocol.reference.IacucProtocolReferenceRule;
import org.kuali.kra.iacuc.protocol.research.IacucProtocolResearchAreaAuditRule;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.species.exception.rule.AddProtocolExceptionEvent;
import org.kuali.kra.iacuc.species.exception.rule.AddProtocolExceptionRule;
import org.kuali.kra.iacuc.species.exception.rule.ProtocolExceptionRule;
import org.kuali.kra.iacuc.species.rule.AddProtocolSpeciesEvent;
import org.kuali.kra.iacuc.species.rule.AddProtocolSpeciesRule;
import org.kuali.kra.iacuc.species.rule.ProtocolSpeciesRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolDocumentRuleBase;
import org.kuali.kra.protocol.actions.decision.ExecuteCommitteeDecisionAbstainerRule;
import org.kuali.kra.protocol.actions.decision.ExecuteCommitteeDecisionRecuserRule;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionRuleBase;
import org.kuali.kra.protocol.noteattachment.SubmitProtocolAttachmentProtocolRuleImplBase;
import org.kuali.kra.protocol.personnel.ProtocolAttachmentPersonnelRuleBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelAuditRuleBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitRuleBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceAuditRuleBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceRuleBase;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationRuleBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaAuditRuleBase;
import org.kuali.rice.krad.document.Document;

/**
 * Main Business Rule class for <code>{@link IacucProtocolDocument}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class IacucProtocolDocumentRule extends ProtocolDocumentRuleBase<IacucCommitteeDecision> implements AddProtocolSpeciesRule, 
                                                                               AddProtocolExceptionRule, 
                                                                               IacucProtocolAssignCmtRule,
                                                                               IacucProtocolModifySubmissionRule {

    private static final String NEW_PROTOCOL_SPECIES_PATH = "iacucProtocolSpeciesHelper.newIacucProtocolSpecies";
    private static final String PROTOCOL_EXCEPTION = "Exception";
    private static final String PROTOCOL_PROCEDURE = "Procedure";
    private static final String STUDY_GROUP_PROCEDURE_PATH = "iacucProtocolStudyGroupBeans";

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        boolean valid = super.processCustomSaveDocumentBusinessRules(document);
        if(valid) {
            valid &= processProtocolSpeciesRules((IacucProtocolDocument) document);
            valid &= isProtocolStudyGroupValid((IacucProtocolDocument) document);
        }
        return valid;
    }

    /**
     * This method is to validate protocol species defined and its references.
     * Protocol species are first defined in species/groups and these species/groups are
     * used in exception and procedure tabs. We need to make sure the dependency exist if user
     * tries to remove/modify existing species/groups once exception and procedures are defined.
     * @param document
     * @return
     */
    public boolean processProtocolSpeciesRules(IacucProtocolDocument document) {
        boolean valid = true;
        List<IacucProtocolException> protocolExceptions = document.getIacucProtocol().getIacucProtocolExceptions();
        List<IacucProtocolSpecies> speciesList = document.getIacucProtocol().getIacucProtocolSpeciesList();
        List<IacucProtocolStudyGroupBean> protocolStudyGroups = document.getIacucProtocol().getIacucProtocolStudyGroups();
        Set<Integer> distinctProtocolSpecies = getDistinctSpeciesList(speciesList);
        Set<String> distinctProtocolSpeciesGroups = getDistinctSpeciesGroups(speciesList);
        if(!isProtocolExceptionValid(protocolExceptions, distinctProtocolSpecies)) {
            reportError(NEW_PROTOCOL_SPECIES_PATH, KeyConstants.IACUC_PROTOCOL_SPECIES_DEPENDENCY_EXISTS, new String[] {PROTOCOL_EXCEPTION});
            valid = false;
        }
        if(!isProtocolProcedureValid(protocolStudyGroups, distinctProtocolSpecies) ||
            !isProtocolProcedureSpeciesGroupValid(protocolStudyGroups, distinctProtocolSpeciesGroups)) {
            reportError(NEW_PROTOCOL_SPECIES_PATH, KeyConstants.IACUC_PROTOCOL_SPECIES_DEPENDENCY_EXISTS, new String[] {PROTOCOL_PROCEDURE});
            valid = false;
        }
        return valid;
    }

    /**
     * This method is to check if list species used in exception are valid
     * @param protocolExceptions
     * @param protocolSpecies
     * @return
     */
    protected boolean isProtocolExceptionValid(List<IacucProtocolException> protocolExceptions, Set<Integer> protocolSpecies) {
        Set<Integer> exceptionSpecies = new HashSet<Integer>();
        for(IacucProtocolException protocolException : protocolExceptions) {
            exceptionSpecies.add(protocolException.getSpeciesCode());
        }
        exceptionSpecies.removeAll(protocolSpecies);
        return exceptionSpecies.size() == 0;
    }

    /**
     * This method is to check if list species used for procedures are valid
     * @param protocolStudyGroups
     * @param protocolSpecies
     * @return
     */
    protected boolean isProtocolProcedureValid(List<IacucProtocolStudyGroupBean> protocolStudyGroups, Set<Integer> protocolSpecies) {
        Set<Integer> procedureSpecies = new HashSet<Integer>();
        for(IacucProtocolStudyGroupBean studyGroupBean : protocolStudyGroups) {
            for(IacucProtocolStudyGroup studyGroup : studyGroupBean.getIacucProtocolStudyGroups()) {
                Integer speciesCode = studyGroup.getIacucProtocolSpecies().getSpeciesCode();
                procedureSpecies.add(speciesCode);
            }
        }
        procedureSpecies.removeAll(protocolSpecies);
        return procedureSpecies.size() == 0;
    }
    
    /**
     * This method is to get a disctinct list of species configured.
     * @param speciesList
     * @return
     */
    protected Set<Integer> getDistinctSpeciesList(List<IacucProtocolSpecies> speciesList) {
        Set<Integer> distinctSpecies = new HashSet<Integer>();
        for(IacucProtocolSpecies protocolSpecies : speciesList) {
            distinctSpecies.add(protocolSpecies.getSpeciesCode());
        }
        return distinctSpecies;
    }

    /**
     * This method is to get a distinct list of species group configured.
     * @param speciesList
     * @return
     */
    protected Set<String> getDistinctSpeciesGroups(List<IacucProtocolSpecies> speciesList) {
        Set<String> distinctSpeciesGroups = new HashSet<String>();
        for(IacucProtocolSpecies protocolSpecies : speciesList) {
            distinctSpeciesGroups.add(protocolSpecies.getSpeciesGroup());
        }
        return distinctSpeciesGroups;
    }
    
    /**
     * This method is to check if list species groups used for procedures are valid
     * @param protocolStudyGroups
     * @param protocolSpeciesGroups
     * @return
     */
    protected boolean isProtocolProcedureSpeciesGroupValid(List<IacucProtocolStudyGroupBean> protocolStudyGroups, Set<String> protocolSpeciesGroups) {
        List<String> speciesGroups = new ArrayList<String>();
        for(IacucProtocolStudyGroupBean studyGroupBean : protocolStudyGroups) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : studyGroupBean.getIacucProtocolStudyGroups()) {
                IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
                speciesGroups.add(protocolSpecies.getSpeciesGroup());
            }
        }
        boolean invalidSpeciesReference = false;
        for(String speciesGroup : speciesGroups) {
            if(!protocolSpeciesGroups.contains(speciesGroup)) {
                invalidSpeciesReference = true;
                break;
            }
        }
        return !invalidSpeciesReference;
    }
    
    /**
     * This method is to validate protocol study groups
     * @param document
     * @return
     */
    public boolean isProtocolStudyGroupValid(IacucProtocolDocument document) {
        boolean valid = true;
        int groupIndex = 0;
        List<IacucProtocolStudyGroupBean> protocolStudyGroups = document.getIacucProtocol().getIacucProtocolStudyGroupBeans();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocolStudyGroups) {
            String studyGroupPath = STUDY_GROUP_PROCEDURE_PATH.concat("[") + groupIndex + "]";
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                String groupAndSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies().getGroupAndSpecies();
                if(iacucProtocolStudyGroup.getPainCategoryCode() == null) {
                    reportError(studyGroupPath, KeyConstants.IACUC_PROCEDURE_STUDY_GROUP_SPECIES_PAIN_CATEGORY_REQUIRED, new String[] {groupAndSpecies});
                    valid = false;
                }
                if(iacucProtocolStudyGroup.getCount() == null) {
                    reportError(studyGroupPath, KeyConstants.IACUC_PROCEDURE_STUDY_GROUP_SPECIES_COUNT_REQUIRED, new String[] {groupAndSpecies});
                    valid = false;
                }
            }
            groupIndex++;
        }
        return valid;
    }
    
    @Override
    protected String getInProgressProtocolStatusCodeHook() {
        return IacucProtocolStatus.IN_PROGRESS;
    }

    @Override
    protected ProtocolResearchAreaAuditRuleBase getNewProtocolResearchAreaAuditRuleInstanceHook() {
        return new IacucProtocolResearchAreaAuditRule();
    }

    @Override
    public boolean processAddProtocolSpeciesBusinessRules(AddProtocolSpeciesEvent addProtocolSpeciesEvent) {
        return new ProtocolSpeciesRule().processAddProtocolSpeciesBusinessRules(addProtocolSpeciesEvent);
    }

    @Override
    public boolean processAddProtocolExceptionBusinessRules(AddProtocolExceptionEvent addProtocolExceptionEvent) {
        return new ProtocolExceptionRule().processAddProtocolExceptionBusinessRules(addProtocolExceptionEvent);
    }
    

    @Override
    protected IacucProtocolReferenceRule getNewProtocolReferenceRuleInstanceHook() {
        return new IacucProtocolReferenceRule();
    }

    @Override
    protected KcDocumentEventBaseExtension getSaveProtocolPersonnelEventHook(ProtocolDocumentBase document) {
        return new SaveIacucProtocolPersonnelEvent(Constants.EMPTY_STRING, document);
    }

    @Override
    protected ProtocolPersonnelAuditRuleBase getNewProtocolPersonnelAuditRuleInstanceHook() {
        return new IacucProtocolPersonnelAuditRule();
    }

    @Override
    protected ProtocolUnitRuleBase getNewProtocolUnitRuleInstanceHook() {
        return new IacucProtocolUnitRule();
    }

    @Override
    protected ProtocolSubmitActionRuleBase newProtocolSubmitActionRuleInstanceHook() {
        return new IacucProtocolSubmitActionRule();
    }

    @Override
    protected ProtocolLocationRuleBase getNewProtocolLocationRuleInstanceHook() {
        return new IacucProtocolLocationRule();
    }

    @Override
    protected ProtocolFundingSourceAuditRuleBase getNewProtocolFundingSourceAuditRuleInstanceHook() {
        return new IacucProtocolFundingSourceAuditRule();
    }

    @Override
    protected ProtocolFundingSourceRuleBase getNewProtocolFundingSourceRuleInstanceHook() {
        return new IacucProtocolFundingSourceRule();
    }
    

    @Override
    public boolean processAssignToCommittee(ProtocolDocumentBase document, IacucProtocolAssignCmtBean actionBean) {
        return new IacucProtocolAssignCmtRuleImpl().processAssignToCommittee(document, actionBean);
    }    

    @Override
    public ProtocolAttachmentPersonnelRuleBase getProtocolAttachmentPersonnelRuleInstanceHook() {
        return new IacucProtocolAttachmentPersonnelRule();
    }

    @Override
    protected SubmitProtocolAttachmentProtocolRuleImplBase newSubmitProtocolAttachmentProtocolRuleImplInstanceHook() {
        return new SubmitIacucProtocolAttachmentProtocolRuleImpl();
    }    

    @Override
    public boolean processModifySubmissionRule(ProtocolDocumentBase document, IacucProtocolModifySubmissionBean actionBean) {
        return new IacucProtocolModifySubmissionRuleImpl().processModifySubmissionRule(document, actionBean);
    }

    @Override
    protected IacucCommitteeDecisionRule newCommitteeDecisionRuleInstanceHook() {
        return new IacucCommitteeDecisionRule();
    }

    @Override
    protected ExecuteCommitteeDecisionAbstainerRule<IacucCommitteeDecision> newCommitteeDecisionAbstainerRuleInstanceHook() {
        return new IacucCommitteeDecisionAbstainerRule();
    }

    @Override
    protected ExecuteCommitteeDecisionRecuserRule<IacucCommitteeDecision> newCommitteeDecisionRecuserRuleInstanceHook() {
        return new IacucCommitteeDecisionRecuserRule();
    }
}
