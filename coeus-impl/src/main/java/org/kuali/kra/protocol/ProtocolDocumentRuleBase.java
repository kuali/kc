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
package org.kuali.kra.protocol;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.permissions.impl.bo.PermissionsUser;
import org.kuali.coeus.common.permissions.impl.web.bean.User;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.actions.decision.*;
import org.kuali.kra.protocol.actions.submit.ExecuteProtocolSubmitActionRule;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.protocol.noteattachment.SubmitProtocolAttachmentProtocolRuleImplBase;
import org.kuali.kra.protocol.permission.ProtocolPermissionsRule;
import org.kuali.kra.protocol.personnel.*;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceAuditRuleBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceRuleBase;
import org.kuali.kra.protocol.protocol.location.AddProtocolLocationEvent;
import org.kuali.kra.protocol.protocol.location.AddProtocolLocationRule;
import org.kuali.kra.protocol.protocol.location.ProtocolLocationRuleBase;
import org.kuali.kra.protocol.protocol.reference.AddProtocolReferenceEventBase;
import org.kuali.kra.protocol.protocol.reference.AddProtocolReferenceRule;
import org.kuali.kra.protocol.protocol.reference.ProtocolReferenceRuleBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaAuditRuleBase;
import org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.List;

/**
 * Main Business Rule class for <code>{@link ProtocolDocumentBase}</code>. Responsible for delegating rules to independent rule classes.
 *
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public abstract class ProtocolDocumentRuleBase<CD extends CommitteeDecision<? extends CommitteePersonBase>>
                                                                    extends KcTransactionalDocumentRuleBase
                                                                    implements                                                                  
                                                                                AddProtocolReferenceRule, 
                                                                                AddProtocolLocationRule,
                                                                                AddProtocolAttachmentPersonnelRule, 
                                                                                AddProtocolUnitRule,
        KcBusinessRule,
                                                                                ExecuteProtocolSubmitActionRule,
                                                                                ExecuteCommitteeDecisionRule<CD>,                                                                                
                                                                                ExecuteCommitteeDecisionAbstainerRule<CD>, 
                                                                                ExecuteCommitteeDecisionRecuserRule<CD>,
        DocumentAuditRule
                                                                                                    {

    private static final String PROTOCOL_LUN_FORM_ELEMENT="protocolHelper.leadUnitNumber";
    private static final String ERROR_PROPERTY_ORGANIZATION_ID = "protocolHelper.newProtocolLocation.organizationId";
    private static final String PROTOCOL_DOC_LUN_FORM_ELEMENT = "document.protocolList[0].leadUnitNumber";
    private static final String SEPERATOR = ".";
    private static final String INACTIVE_RESEARCH_AREAS_PREFIX = "document.protocolList[0].protocolResearchAreas.inactive";
    

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        retval &= super.processCustomRouteDocumentBusinessRules(document);
        
        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof ProtocolDocumentBase)) {
            return false;
        }
       
        boolean valid = true;
        ProtocolDocumentBase protocolDocument = (ProtocolDocumentBase) document;
        
         
        if ( ( protocolDocument.getDocumentHeader().getWorkflowDocument().isInitiated() || protocolDocument.getDocumentHeader().getWorkflowDocument().isSaved() ) 
                && getInProgressProtocolStatusCodeHook().equals(protocolDocument.getProtocol().getProtocolStatusCode())
                && StringUtils.isBlank(protocolDocument.getDocumentHeader().getDocumentTemplateNumber()) ) {
            valid &= processProtocolResearchAreaBusinessRules((ProtocolDocumentBase) document);
        }
        
       
        valid &= processLeadUnitBusinessRules((ProtocolDocumentBase) document);
        valid &= processProtocolLocationBusinessRules((ProtocolDocumentBase) document);            
        valid &= processProtocolPersonnelBusinessRules((ProtocolDocumentBase) document);
        return valid;
    }

    protected abstract String getInProgressProtocolStatusCodeHook();

    /**
     * This method will check if all the research areas that have been added to the protocol are indeed active.
     * @param document
     * @return
     */
    public boolean processProtocolResearchAreaBusinessRules(ProtocolDocumentBase document) {
        boolean inactiveFound = false;
        StringBuffer inactiveResearchAreaIndices = new StringBuffer();
        
        List<ProtocolResearchAreaBase> pras = document.getProtocol().getProtocolResearchAreas();
        // iterate over all the research areas for this protocol looking for inactive research areas
        if(CollectionUtils.isNotEmpty(pras)) {
            int raIndex = 0;
            for (ProtocolResearchAreaBase protocolResearchArea : pras) {
                if(!(protocolResearchArea.getResearchAreas().isActive())) {
                    inactiveFound = true;
                    inactiveResearchAreaIndices.append(raIndex).append(SEPERATOR);
                }
                raIndex++;
            }
        }
        // if we found any inactive research areas in the above loop, report as a single error key suffixed by the list of indices of the inactive areas
        if(inactiveFound) { 
            String protocolResearchAreaInactiveErrorPropertyKey = INACTIVE_RESEARCH_AREAS_PREFIX + SEPERATOR + inactiveResearchAreaIndices.toString();
            reportError(protocolResearchAreaInactiveErrorPropertyKey, KeyConstants.ERROR_PROTOCOL_RESEARCH_AREA_INACTIVE);
        }
        
        return !inactiveFound;
    }

    
    
    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;  
        retval &= new KcDocumentBaseAuditRule().processRunAuditBusinessRules(document);
        retval &= getNewProtocolFundingSourceAuditRuleInstanceHook().processRunAuditBusinessRules((ProtocolDocumentBase) document);         
        retval &= getNewProtocolResearchAreaAuditRuleInstanceHook().processRunAuditBusinessRules((ProtocolDocumentBase) document);
        retval &= getNewProtocolPersonnelAuditRuleInstanceHook().processRunAuditBusinessRules(document);
        retval &= this.processNoteAndAttachmentAuditRules((ProtocolDocumentBase) document);
        return retval;
    }
    
    
    protected abstract ProtocolFundingSourceAuditRuleBase getNewProtocolFundingSourceAuditRuleInstanceHook();

    protected abstract ProtocolPersonnelAuditRuleBase getNewProtocolPersonnelAuditRuleInstanceHook();
    
    protected abstract ProtocolResearchAreaAuditRuleBase getNewProtocolResearchAreaAuditRuleInstanceHook();

    /**
     * Validates lead unit rules for a {@link ProtocolDocumentBase ProtocolDocumentBase}.
     * @param document the document
     * @return true if validation passes false if not
     * @throws NullPointerException if the document is null
     */
    public boolean processLeadUnitBusinessRules(ProtocolDocumentBase document) {
        boolean isValid = true;
        
        if (document == null) {
            throw new NullPointerException("the document was null");
        }
        ProtocolBase protocol = document.getProtocol();
        
        if (StringUtils.isNotBlank(protocol.getLeadUnitNumber())) {
            Unit unit = getUnitService().getUnit(protocol.getLeadUnitNumber());
            if (unit == null) {
                if (getErrorReporter().propertyHasErrorReported(PROTOCOL_DOC_LUN_FORM_ELEMENT)) {
                    getErrorReporter().removeErrors(PROTOCOL_DOC_LUN_FORM_ELEMENT);
                }
                reportError(PROTOCOL_LUN_FORM_ELEMENT, KeyConstants.ERROR_PROTOCOL_LEAD_UNIT_NUM_INVALID);
                isValid = false;
            }
        }
        
        return isValid;
    }
 
    
    /**
     * At least one organization must be entered.  
     * If the default value is removed, another organization must be added before user 
     * can save
     * @see org.kuali.kra.irb.rule.SaveProtocolLocationRule#processSaveProtocolLocationBusinessRules(org.kuali.kra.irb.rule.event.SaveProtocolLocationEvent)
     */
    public boolean processProtocolLocationBusinessRules(ProtocolDocumentBase document) {
        boolean isValid = true;
        if(CollectionUtils.isEmpty(document.getProtocol().getProtocolLocations())) {
            reportError(ERROR_PROPERTY_ORGANIZATION_ID, KeyConstants.ERROR_PROTOCOL_LOCATION_SHOULD_EXIST);
            isValid = false;
        }
        return isValid;
    }
    
    private boolean processProtocolPersonnelBusinessRules(ProtocolDocumentBase document) {
        return processRules(getSaveProtocolPersonnelEventHook(document));
    }
    
    protected abstract KcDocumentEventBaseExtension getSaveProtocolPersonnelEventHook(ProtocolDocumentBase document);

    public boolean processAddProtocolReferenceBusinessRules(AddProtocolReferenceEventBase addProtocolReferenceEvent) {
        return getNewProtocolReferenceRuleInstanceHook().processAddProtocolReferenceBusinessRules(addProtocolReferenceEvent);        
    }
    
    protected abstract ProtocolReferenceRuleBase getNewProtocolReferenceRuleInstanceHook();


    @Override
    public boolean processAddProtocolLocationBusinessRules(AddProtocolLocationEvent addProtocolLocationEvent) {
        return getNewProtocolLocationRuleInstanceHook().processAddProtocolLocationBusinessRules(addProtocolLocationEvent);       
    }    
    
    protected abstract ProtocolLocationRuleBase getNewProtocolLocationRuleInstanceHook();


    protected abstract ProtocolFundingSourceRuleBase getNewProtocolFundingSourceRuleInstanceHook();

    public boolean processAddPermissionsUserBusinessRules(Document document, List<User> users, PermissionsUser newUser) {
      return new ProtocolPermissionsRule().processAddPermissionsUserBusinessRules(document, users, newUser);
  }  
    
    public boolean processAddProtocolAttachmentPersonnelRules(AddProtocolAttachmentPersonnelEvent addProtocolAttachmentPersonnelEvent) {
        return getProtocolAttachmentPersonnelRuleInstanceHook().processAddProtocolAttachmentPersonnelRules(addProtocolAttachmentPersonnelEvent);
    }
    
    public abstract ProtocolAttachmentPersonnelRuleBase getProtocolAttachmentPersonnelRuleInstanceHook();


    @Override
    public boolean processAddProtocolUnitBusinessRules(AddProtocolUnitEvent addProtocolUnitEvent) {

        return getNewProtocolUnitRuleInstanceHook().processAddProtocolUnitBusinessRules(addProtocolUnitEvent);
        
    }
    
    protected abstract ProtocolUnitRuleBase getNewProtocolUnitRuleInstanceHook();
    
    @Override
    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }

    
    /**
     * Executes the notes and attachment related audit rules.
     * @param document the document.
     * @return true if valid.
     */
    private boolean processNoteAndAttachmentAuditRules(ProtocolDocumentBase document) {
        assert document != null : "the document was null";
        
        boolean valid = true;
        valid &= newSubmitProtocolAttachmentProtocolRuleImplInstanceHook().processSubmitProtocolAttachmentProtocolRules(document);
     
        return valid;
    }
    
    protected abstract SubmitProtocolAttachmentProtocolRuleImplBase newSubmitProtocolAttachmentProtocolRuleImplInstanceHook();

    @Override
    public boolean processSubmitAction(ProtocolDocumentBase document, ProtocolSubmitAction submitAction) {
        return newProtocolSubmitActionRuleInstanceHook().processSubmitAction(document, submitAction);
    }

    protected abstract ExecuteProtocolSubmitActionRule newProtocolSubmitActionRuleInstanceHook();

    @Override
    public boolean proccessCommitteeDecisionRule(ProtocolDocumentBase document, CD actionBean) {
        return newCommitteeDecisionRuleInstanceHook().proccessCommitteeDecisionRule(document, actionBean);
    }

    protected abstract CommitteeDecisionRuleBase<CD> newCommitteeDecisionRuleInstanceHook();

    @Override
    public boolean proccessCommitteeDecisionAbstainerRule(ProtocolDocumentBase document, CD actionBean) {
        return newCommitteeDecisionAbstainerRuleInstanceHook().proccessCommitteeDecisionAbstainerRule(document, actionBean);
    }
    
    protected abstract ExecuteCommitteeDecisionAbstainerRule<CD> newCommitteeDecisionAbstainerRuleInstanceHook();

    
    @Override
    public boolean proccessCommitteeDecisionRecuserRule(ProtocolDocumentBase document, CD actionBean) {
        return newCommitteeDecisionRecuserRuleInstanceHook().proccessCommitteeDecisionRecuserRule(document, actionBean);
    }

    protected abstract ExecuteCommitteeDecisionRecuserRule<CD> newCommitteeDecisionRecuserRuleInstanceHook();

    
    private UnitService getUnitService() {
        return KcServiceLocator.getService(UnitService.class);
    }
}
