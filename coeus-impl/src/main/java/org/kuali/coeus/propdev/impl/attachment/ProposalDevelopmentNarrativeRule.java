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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.List;


import static org.kuali.kra.infrastructure.KeyConstants.*;


/**
 * Implementation of business rules required for the Proposal attachment page of the 
 * <code>{@link org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument}</code>.
 *
 * @author kualidev@oncourse.iu.edu
 * @version 1.0
 */
public class ProposalDevelopmentNarrativeRule extends KcTransactionalDocumentRuleBase implements AddNarrativeRule, ReplaceNarrativeRule, SaveNarrativesRule, NewNarrativeUserRightsRule { 
    private static final String PROPOSAL = "Proposal";
    private static final String MODULE_STATUS_CODE_COMPLETED = "C";
    private static final String ERROR_PREFIX_FOR_ATTACHMENTS = "multipartFile";

    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentNarrativeRule.class);
    
    private transient KcPersonService kcPersonService;
    private transient PersonService personService;
    private transient ParameterService parameterService;
    private transient KcAttachmentService  kcAttachmentService;
    private transient KcAuthorizationService kcAuthorizationService;

    /**
     * This method is used to validate narratives and institute proposal attachments before adding.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.coeus.propdev.impl.attachment.AddNarrativeRule#processAddNarrativeBusinessRules(org.kuali.coeus.propdev.impl.attachment.AddNarrativeEvent)
     */
    public boolean processAddNarrativeBusinessRules(AddNarrativeEvent narrativeEvent) {
        ProposalDevelopmentDocument document = (ProposalDevelopmentDocument)narrativeEvent.getDocument();
        Narrative narrative = narrativeEvent.getNarrative();
        boolean rulePassed = true;
        getDataObjectService().wrap(narrative).fetchRelationship("narrativeType");
        MessageMap map = GlobalVariables.getMessageMap();

        if(narrative.getNarrativeType()==null)
            rulePassed = false;
        
        if(!StringUtils.isBlank(narrative.getModuleStatusCode()) 
                && narrative.getModuleStatusCode().equalsIgnoreCase(MODULE_STATUS_CODE_COMPLETED)
                && StringUtils.isBlank(narrative.getName())) {
            LOG.debug(ERROR_NARRATIVE_STATUS_INVALID);
            reportError("moduleStatusCode", ERROR_NARRATIVE_STATUS_INVALID);
            rulePassed = false;
        }
        rulePassed &= getDictionaryValidationService().isBusinessObjectValid(narrative);
        rulePassed &= checkNarrative(document.getDevelopmentProposal().getNarratives(), narrative);
        rulePassed &= validFileNameCharacters(narrative);
        rulePassed &= getKcFileService().validPDFFile(narrative, getErrorReporter(), ERROR_PREFIX_FOR_ATTACHMENTS);

        return rulePassed;
    }
    private boolean validFileNameCharacters(Narrative narrative) {
        String attachmentFileName = narrative.getName();
        KcAttachmentService attachmentService = getKcFileService();
        boolean rulePassed = true;
        // Checking attachment file name for invalid characters.
        String invalidCharacters = attachmentService.getInvalidCharacters(attachmentFileName);
        if (ObjectUtils.isNotNull(invalidCharacters)) {
            String parameter = getParameterService().
                               getParameterValueAsString(ProposalDevelopmentDocument.class, Constants.INVALID_FILE_NAME_CHECK_PARAMETER);

            if (Constants.INVALID_FILE_NAME_ERROR_CODE.equals(parameter)) {
                rulePassed &= false;
                reportError(ERROR_PREFIX_FOR_ATTACHMENTS, KeyConstants.INVALID_FILE_NAME,
                        attachmentFileName, invalidCharacters);
            } else {
                rulePassed &= true;
                reportWarning(ERROR_PREFIX_FOR_ATTACHMENTS, KeyConstants.INVALID_FILE_NAME,
                        attachmentFileName, invalidCharacters);
            }
        }
        return rulePassed;
    }

    /**
     * This method is used to validate narratives and institute proposal attachments before saving.
     * It checks whether the narratives are duplicated for those of which have allowMultiple flag set as false.
     * @see org.kuali.coeus.propdev.impl.attachment.SaveNarrativesRule#processSaveNarrativesBusinessRules(org.kuali.coeus.propdev.impl.attachment.SaveNarrativesEvent)
     */
    public boolean processSaveNarrativesBusinessRules(SaveNarrativesEvent saveNarrativesEvent) {
        boolean rulePassed = true;
        Narrative narrative = saveNarrativesEvent.getNarrative();
        getDataObjectService().wrap(narrative).fetchRelationship("narrativeType");
        List<Narrative> narrativeList = saveNarrativesEvent.getNarratives();
        GlobalVariables.getMessageMap().getErrorPath().clear();
        GlobalVariables.getMessageMap().getErrorPath().add(saveNarrativesEvent.getErrorPathPrefix());
        rulePassed &= checkNarrative(narrativeList,saveNarrativesEvent.getNarrative());
        rulePassed &= checkUserRights(saveNarrativesEvent);

        if(!StringUtils.isBlank(narrative.getModuleStatusCode()) 
                && narrative.getModuleStatusCode().equalsIgnoreCase(MODULE_STATUS_CODE_COMPLETED)
                && StringUtils.isBlank(narrative.getName())) {
            LOG.debug(ERROR_NARRATIVE_STATUS_INVALID);
            reportError("moduleStatusCode", ERROR_NARRATIVE_STATUS_INVALID);
            rulePassed = false;
        }
        
        return rulePassed;
    }
    
    /**
     * This method is used to validate characters in the attachment file name, and should be invoked when a user attempts
     * to replace a narrative post-route.
     * @see org.kuali.coeus.propdev.impl.attachment.ReplaceNarrativeRule#processReplaceNarrativeBusinessRules(org.kuali.coeus.propdev.impl.attachment.ReplaceNarrativeEvent)
     */
    public boolean processReplaceNarrativeBusinessRules(ReplaceNarrativeEvent replaceNarrativeEvent) {
        Narrative narrative = replaceNarrativeEvent.getNarrative();
        boolean rulePassed = true;
        getDataObjectService().wrap(narrative).fetchRelationship("narrativeType");
        MessageMap map = GlobalVariables.getMessageMap();

        if(narrative.getNarrativeType()==null)
            rulePassed = false;
        
        rulePassed &= validFileNameCharacters(narrative);
        rulePassed &= getKcFileService().validPDFFile(narrative, getErrorReporter(), ERROR_PREFIX_FOR_ATTACHMENTS);

        map.addToErrorPath(replaceNarrativeEvent.getErrorPathPrefix());
        rulePassed &= getDictionaryValidationService().isBusinessObjectValid(narrative);
        map.removeFromErrorPath(replaceNarrativeEvent.getErrorPathPrefix());
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
                    reportError("narrativeTypeCode", ERROR_ATTACHMENT_NOT_AUTHORIZED, origNarrative.getNarrativeType().getDescription());
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
        boolean rulePassed = true;
        if(StringUtils.isBlank(narrative.getNarrativeTypeCode())){
            rulePassed = false;
            reportError("narrativeTypeCode", ERROR_ATTACHMENT_TYPE_NOT_SELECTED);
        }
        if(StringUtils.isBlank(narrative.getModuleStatusCode())){
            rulePassed = false;
            reportError("moduleStatusCode", ERROR_ATTACHMENT_STATUS_NOT_SELECTED);
        }
        if (rulePassed) {
            String[] param = {PROPOSAL, narrative.getNarrativeType().getDescription()};
            if (!narrative.getNarrativeType().isAllowMultiple()) {
                int matches = 0;
                for (Narrative narr : narrativeList) {
                    if (narr!=null && StringUtils.equals(narr.getNarrativeTypeCode(),narrative.getNarrativeTypeCode())) {
                        matches ++;
                        if (matches > 1) {
                            LOG.debug(ERROR_NARRATIVE_TYPE_DUPLICATE);
                            reportError("narrativeTypeCode", ERROR_NARRATIVE_TYPE_DUPLICATE, param);
                            rulePassed = false;
                            break;
                        }
                    }
                }
            }else if (StringUtils.isBlank(narrative.getModuleTitle())) {
                reportError("moduleTitle", ERROR_NARRATIVE_TYPE_DESCRITPION_REQUIRED, param);
                rulePassed = false;
            }
        }
        return rulePassed;
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
    protected KcAttachmentService getKcFileService() {
        if(this.kcAttachmentService == null) {
            this.kcAttachmentService = KcServiceLocator.getService(KcAttachmentService.class);
        }
        return this.kcAttachmentService;
    }

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

    protected  KcAuthorizationService getKcAuthorizationService (){
        if (kcAuthorizationService == null)
            kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
        return kcAuthorizationService;
    }
    /**
     * Does the given user have the given permission for the proposal?
     * @param userId the user's username
     * @param doc the Permissionable
     * @param permissionName the name of the permission
     * @return true if user has permission; otherwise false
     */
   protected boolean hasPermission(String userId, Permissionable doc, String permissionName) {
        return getKcAuthorizationService().hasPermission(userId, doc, permissionName);
    }
}
