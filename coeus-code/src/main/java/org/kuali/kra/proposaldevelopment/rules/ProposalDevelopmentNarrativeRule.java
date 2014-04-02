/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.NewNarrativeUserRightsRule;
import org.kuali.kra.proposaldevelopment.rule.ReplaceNarrativeRule;
import org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ReplaceNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.KeyConstants.*;


/**
 * Implementation of business rules required for the Proposal attachment page of the 
 * <code>{@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}</code>.
 *
 * @author kualidev@oncourse.iu.edu
 * @version 1.0
 */
public class ProposalDevelopmentNarrativeRule extends KcTransactionalDocumentRuleBase implements AddNarrativeRule, ReplaceNarrativeRule, SaveNarrativesRule, NewNarrativeUserRightsRule { 
    private static final String NARRATIVE_TYPE_ALLOWMULTIPLE_NO = "N";
    private static final String DOCUMENT_NARRATIVES = "document.narratives";
    private static final String PROPOSAL = "Proposal";
    private static final String NARRATIVE_TYPE_CODE = "narrativeTypeCode";
    private static final String MODULE_STATUS_CODE_COMPLETED = "C";
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentNarrativeRule.class);
    
    private transient KcPersonService kcPersonService;
    private transient PersonService personService;
    private transient ParameterService parameterService;
    private transient KcAttachmentService  kcAttachmentService;
    private DictionaryValidationService dictionaryValidationService;
    /**
     * This method is used to validate narratives and institute proposal attachments before adding.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.kra.proposaldevelopment.rule.AddNarrativeRule#processAddNarrativeBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent)
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent narrativeEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)narrativeEvent.getDocument();
        Narrative narrative = narrativeEvent.getNarrative();
        boolean rulePassed = true;
        populateNarrativeType(narrative);
        MessageMap map = GlobalVariables.getMessageMap();

        if(narrative.getNarrativeType()==null)
            rulePassed = false;
        
        if(!StringUtils.isBlank(narrative.getModuleStatusCode()) 
                && narrative.getModuleStatusCode().equalsIgnoreCase(MODULE_STATUS_CODE_COMPLETED)
                && StringUtils.isBlank(narrative.getFileName())) {
            LOG.debug(ERROR_NARRATIVE_STATUS_INVALID);
            reportError("newNarrative.moduleStatusCode", ERROR_NARRATIVE_STATUS_INVALID);
            rulePassed = false;
        }
        
        if (StringUtils.isBlank(narrative.getFileName())) {
            rulePassed = false;
            reportError("newNarrative.narrativeFile", KeyConstants.ERROR_REQUIRED_FOR_FILE_NAME, "File Name");
        }
        
        rulePassed &= validFileNameCharacters(narrative);
        
        map.addToErrorPath("newNarrative");
        getKnsDictionaryValidationService().validateBusinessObject(narrative,false);
        map.removeFromErrorPath("newNarrative");
        int size = map.getErrorMessages().keySet().size();
        rulePassed &= size<=0;
        rulePassed &= checkNarrative(document.getDevelopmentProposal().getNarratives(), narrative);
        
        return rulePassed;
    }
    private boolean validFileNameCharacters(Narrative narrative) {
        String attachmentFileName = narrative.getFileName();
        KcAttachmentService attachmentService = getKcAttachmentService();
        boolean rulePassed = true;
        // Checking attachment file name for invalid characters.
        String invalidCharacters = attachmentService.getInvalidCharacters(attachmentFileName);
        if (ObjectUtils.isNotNull(invalidCharacters)) {
            String parameter = getParameterService().
                               getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INVALID_FILE_NAME_CHECK_PARAMETER);
           
            if (Constants.INVALID_FILE_NAME_ERROR_CODE.equals(parameter)) {
                rulePassed &= false;
                reportError("newNarrative.narrativeFile", KeyConstants.INVALID_FILE_NAME,
                        attachmentFileName, invalidCharacters);
            } else {
                rulePassed &= true;
                reportWarning("newNarrative.narrativeFile", KeyConstants.INVALID_FILE_NAME,
                        attachmentFileName, invalidCharacters);
            }
        }
        return rulePassed;
    }
    /**
     * This method is used to validate narratives and institute proposal attachments before saving.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.kra.proposaldevelopment.rule.SaveNarrativesRule#processSaveNarrativesBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent)
     */
    public boolean processSaveNarrativesBusinessRules(SaveNarrativesEvent saveNarrativesEvent) {
        
        boolean rulePassed = checkUserRights(saveNarrativesEvent);
        
        List<Narrative> narrativeList = saveNarrativesEvent.getNarratives();
        int size = narrativeList.size();
       
        for (int i = 0; i < size; i++) {
            Narrative narrative = narrativeList.get(0);
            
            narrativeList.remove(narrative);  
            //--size;
            rulePassed &= checkNarrative(narrativeList,narrative);
        }
        
        Narrative narrative = saveNarrativesEvent.getNarrative();
        populateNarrativeType(narrative);
        if(!StringUtils.isBlank(narrative.getModuleStatusCode()) 
                && narrative.getModuleStatusCode().equalsIgnoreCase(MODULE_STATUS_CODE_COMPLETED)
                && StringUtils.isBlank(narrative.getFileName())) {
            LOG.debug(ERROR_NARRATIVE_STATUS_INVALID);
            reportError("newNarrative.moduleStatusCode", ERROR_NARRATIVE_STATUS_INVALID);
            rulePassed = false;
        }
        
        return rulePassed;
    }
    
    /**
     * This method is used to validate characters in the attachment file name, and should be invoked when a user attempts
     * to replace a narrative post-route.
     * @see org.kuali.kra.proposaldevelopment.rule.ReplaceNarrativeRule#processReplaceNarrativeBusinessRules(org.kuali.kra.proposaldevelopment.rule.event.ReplaceNarrativeEvent)
     */
    public boolean processReplaceNarrativeBusinessRules(ReplaceNarrativeEvent replaceNarrativeEvent) {
        Narrative narrative = replaceNarrativeEvent.getNarrative();
        boolean rulePassed = true;
        populateNarrativeType(narrative);
        MessageMap map = GlobalVariables.getMessageMap();

        if(narrative.getNarrativeType()==null)
            rulePassed = false;
        
        rulePassed &= validFileNameCharacters(narrative);
        
        map.addToErrorPath(replaceNarrativeEvent.getErrorPathPrefix());
        getKnsDictionaryValidationService().validateBusinessObject(narrative,false);
        map.removeFromErrorPath(replaceNarrativeEvent.getErrorPathPrefix());
        int size = map.getErrorMessages().keySet().size();
        rulePassed &= size<=0 ;
        
        return rulePassed;
    } 
    
    /**
     * Check to see if the user modified a narrative and verify that the
     * user has the necessary permission to make that modification.
     * @param saveNarrativesEvent
     * @return
     */
    private boolean checkUserRights(SaveNarrativesEvent saveNarrativesEvent) {
        boolean isValid = true;
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        
        List<Narrative> narratives = saveNarrativesEvent.getNarratives();
        List<Narrative> originalNarratives = saveNarrativesEvent.getOriginalNarratives();
        for (Narrative origNarrative : originalNarratives) {
            NarrativeUserRights userRights = getUserRights(userId, origNarrative);
            if (userRights != null && ((StringUtils.equals(userRights.getAccessType(), NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) ||
                (StringUtils.equals(userRights.getAccessType(), NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType())))) {
                
                Narrative narrative = findNarrative(narratives, origNarrative);
                if (!origNarrative.equals(narrative)) {
                    isValid = false;
                    reportError("newNarrative.narrativeTypeCode", ERROR_ATTACHMENT_NOT_AUTHORIZED, origNarrative.getNarrativeType().getDescription());
                }
            }
        }
        return isValid;
    }
    
    /**
     * Get the narrative rights for a user.
     * @param userId the user's unique username
     * @param narrative the narrative to search through for the user's rights
     * @return
     */
    private NarrativeUserRights getUserRights(String userId, Narrative narrative) {

        List<NarrativeUserRights> userRightsList = narrative.getNarrativeUserRights();
        for (NarrativeUserRights userRights : userRightsList) {
            String personId = userRights.getUserId();
            KcPerson person = getKcPersonService().getKcPersonByPersonId(personId);
            if (StringUtils.equals(userId, person.getPersonId())) {
                return userRights;
            }
        }
        return null;
    }
    
    /**
     * Find the narrative that matches the original narrative.  A match occurs
     * if they have the same module number.
     * @param narratives the list of narratives
     * @param origNarrative the original narrative to compare against
     * @return the found narrative or null if not found
     */
    private Narrative findNarrative(List<Narrative> narratives, Narrative origNarrative) {
        for (Narrative narrative : narratives) {
            if (narrative.getModuleNumber().equals(origNarrative.getModuleNumber())) {
                return narrative;
            }
        }
        return null;
    }
    
    /**
     * It checks for duplicate narrative types and mandatory description for narrative type 'Other'
     * @param narrativeList
     * @param narrative
     * @return true if rules passed, else false
     */
    private boolean checkNarrative(List<Narrative> narrativeList, Narrative narrative) {
        String errorPath=DOCUMENT_NARRATIVES;
        boolean rulePassed = true;
        if(StringUtils.isBlank(narrative.getNarrativeTypeCode())){
            rulePassed = false;
            reportError("newNarrative.narrativeTypeCode", ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }
        if(StringUtils.isBlank(narrative.getModuleStatusCode())){
            rulePassed = false;
            reportError("newNarrative.moduleStatusCode", ERROR_ATTACHMENT_STATUS_NOT_SELECTED);
        }
        if (rulePassed) {
            populateNarrativeType(narrative);
            String[] param = {PROPOSAL, narrative.getNarrativeType().getDescription()};
            if (narrative.getNarrativeType().getAllowMultiple().equalsIgnoreCase(NARRATIVE_TYPE_ALLOWMULTIPLE_NO)) {
                for (Narrative narr : narrativeList) {
                    if (narr!=null && StringUtils.equals(narr.getNarrativeTypeCode(),narrative.getNarrativeTypeCode())) {
                        LOG.debug(ERROR_NARRATIVE_TYPE_DUPLICATE);
                        reportError(errorPath, ERROR_NARRATIVE_TYPE_DUPLICATE, param);
                        rulePassed = false;
                    }
                }
            }else if (StringUtils.isBlank(narrative.getModuleTitle())) {
                reportError(errorPath, ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED, param);
                rulePassed = false;
            }
        }
        return rulePassed;
    }
    private void populateNarrativeType(Narrative narrative) {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(NARRATIVE_TYPE_CODE, narrative.getNarrativeTypeCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeType narrType = (NarrativeType) service.findByPrimaryKey(NarrativeType.class, narrativeTypeMap);
        if (narrType != null)
            narrative.setNarrativeType(narrType);
        
    }
    
    @Override
    public boolean processNewNarrativeUserRightsBusinessRules(ProposalDevelopmentDocument document,
            List<NarrativeUserRights> newNarrativeUserRights, int narrativeIndex) {
        
        boolean isValid = true;
        
        // Must have at least one user with the right to modify narratives.
       
        if (!hasNarrativeRight(newNarrativeUserRights, NarrativeRight.MODIFY_NARRATIVE_RIGHT)) {
            isValid = false;
            this.reportError(Constants.NEW_NARRATIVE_USER_RIGHTS_PROPERTY_KEY, 
                             KeyConstants.ERROR_REQUIRE_ONE_NARRATIVE_MODIFY);
        }
        
        // The users cannot be assigned narrative rights that are
        // greater than their assigned permission.  For example, if someone
        // only has the VIEW_NARRATIVES permission, they cannot be 
        // assigned a narrative right of modify.
        
        for (NarrativeUserRights userRights : newNarrativeUserRights) {
            if (!hasPermission(userRights, document)) {
                isValid = false;
                String personId = userRights.getUserId();
                KcPerson person = getKcPersonService().getKcPersonByPersonId(personId);
                this.reportError(Constants.NEW_NARRATIVE_USER_RIGHTS_PROPERTY_KEY, 
                                 KeyConstants.ERROR_NARRATIVE_USER_RIGHT_NO_PERMISSION, person.getFullName());
            }
        }

        return isValid;
    }
    
    /**
     * Do any of the users have the given narrative right?
     * @param narrativeUserRights the list of narrative user rights
     * @param narrativeRight the narrative right to look for
     * @return true if at least one user has this narrative right; otherwise false
     */
    private boolean hasNarrativeRight(List<NarrativeUserRights> narrativeUserRights, NarrativeRight narrativeRight) {
        for (NarrativeUserRights userRights : narrativeUserRights) {
            if (StringUtils.equals(userRights.getAccessType(), narrativeRight.getAccessType())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Does this person have the necessary permission for the narrative right they
     * have been assigned?  In other words, a person cannot be given the narrative
     * right of modify if they don't have the MODIFY_NARRATIVE permssion.
     * @param userRights the person's narrative right
     * @param doc the Proposal Development Document
     * @return true if the person has the necessary permission; otherwise false
     */
    private boolean hasPermission(NarrativeUserRights userRights, ProposalDevelopmentDocument doc) {
        
        String personId = userRights.getUserId();
        
        if (StringUtils.equals(userRights.getAccessType(), NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType())) {
            if (!hasPermission(personId, doc, PermissionConstants.MODIFY_NARRATIVE)) {
                return false;
            }
        }
        else if (StringUtils.equals(userRights.getAccessType(), NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
            if (!hasPermission(personId, doc, PermissionConstants.VIEW_NARRATIVE)) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * This method gets the attachment service
     * @return
     */
    protected KcAttachmentService getKcAttachmentService() {
        if(this.kcAttachmentService == null) {
            this.kcAttachmentService = KcServiceLocator.getService(KcAttachmentService.class);
        }
        return this.kcAttachmentService;
    }
    /**
     * Gets the parameter service.
     * @see org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase#getParameterService()
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null ) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }
    
    protected PersonService getPersonService() {
        if (this.personService == null) {
            this.personService = KcServiceLocator.getService(PersonService.class);
        }
        
        return this.personService;
    }

    /**
     * Does the given user have the given permission for the proposal?
     * @param userId the user's username
     * @param doc the Permissionable
     * @param permissionName the name of the permission
     * @return true if user has permission; otherwise false
     */
   protected boolean hasPermission(String userId, Permissionable doc, String permissionName) {
        KcAuthorizationService kraAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        return kraAuthorizationService.hasPermission(userId, doc, permissionName);
    }

    protected DictionaryValidationService getKnsDictionaryValidationService() {
        if (this.dictionaryValidationService == null) {
            this.dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return this.dictionaryValidationService;
    }
}
