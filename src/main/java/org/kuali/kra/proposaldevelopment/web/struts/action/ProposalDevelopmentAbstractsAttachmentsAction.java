/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.Constants.MAPPING_CLOSE_PAGE;
import static org.kuali.kra.infrastructure.Constants.MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE;
import static org.kuali.kra.infrastructure.Constants.NARRATIVE_MODULE_NUMBER;
import static org.kuali.kra.infrastructure.Constants.NARRATIVE_MODULE_SEQUENCE_NUMBER;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.RiceConstants;
import org.kuali.core.question.ConfirmationQuestion;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.WebUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.PropPerDocType;
import org.kuali.kra.bo.RoleRight;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.RightConstants;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeStatus;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserRoles;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.AddAbstractEvent;
import org.kuali.kra.proposaldevelopment.rule.event.AddNarrativeEvent;
import org.kuali.kra.proposaldevelopment.rule.event.SaveNarrativesEvent;
import org.kuali.kra.proposaldevelopment.service.NarrativeAuthZService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.KNSServiceLocator;
/**
 * <code>Struts Action</code> class process requests from Proposal Abstract Attachments page.
 * It handles Proposal attachments, Institutional attachments, Personnel attachments and Abstracts.
 * Attachment(Narrative) Module Maintenance, Narrative user Rights, Upload Attachment, View Attachment, 
 * Abstract Maintenance are the main features processed by this class 
 * 
 * @author KRADEV team
 * @version 1.0
 */
public class ProposalDevelopmentAbstractsAttachmentsAction extends ProposalDevelopmentAction {
    private static final String EMPTY_STRING = "";
    private static final String NARRATIVE_TYPE_CODE = "narrativeTypeCode";
    private static final String NARRATIVE_STATUS_CODE = "narrativeStatusCode";
    private static final String ROLE_ID = "roleId";
    private static final String PERSON_ID = "personId";
    private static final String MODULE_NUMBER = "moduleNumber";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAbstractsAttachmentsAction.class);
//    private static final String NARRATIVE_ACCESS_TYPE_NONE = "N";
//    private static final String NARRATIVE_ACCESS_TYPE_VIEW = "R";
//    private static final String NARRATIVE_ACCESS_TYPE_MODIFY = "M";
    private static final String LINE_NUMBER = "line";
    private static final String CONFIRM_DELETE_ABSTRACT_KEY = "confirmDeleteAbstract";
    /**
     * Overridden method from ProposalDevelopmentAction. It populates Narrative module user rights
     * before the save.
     * Proposal Attachments and Institutional Attachments are being saved into <i>NARRATIVE</i> table
     * 
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        List<Narrative> narrativeList = proposalDevelopmentDocument.getNarratives();
        for (Narrative narrative : narrativeList) {
            populateNarrativeUserRights(proposalDevelopmentDocument, narrative);
            populateNarrativeType(narrative);
        }
        boolean rulePassed = true;
        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new SaveNarrativesEvent(EMPTY_STRING,proposalDevelopmentDocument));

        if (!rulePassed){
            mapping.findForward(Constants.MAPPING_BASIC);
        }
        return super.save(mapping, form, request, response);
    }
    /**
     * Populates module level rights for each narrative for logged in user.
     * @see org.kuali.kra.proposaldevelopment.web.struts.action.ProposalDevelopmentAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        //Need to check with Terry
//        populateNarrativeRightsForLoggedinUser(proposalDevelopmentForm);
        return super.execute(mapping, form, request, response);
    }    

    private void populateNarrativeRightsForLoggedinUser(ProposalDevelopmentForm proposalDevelopmentForm) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        List<Narrative> narrativeList = proposalDevelopmentDocument.getNarratives();
        NarrativeAuthZService narrativeAuthZService = getService(NarrativeAuthZService.class);
        for (Narrative narrative : narrativeList) {
            narrative.setModifyAttachment(false);
            narrative.setViewAttachment(false);
            switch(narrativeAuthZService.authorize(narrative, proposalDevelopmentDocument.getUpdateUser())){
                case MODIFY_NARRATIVE_RIGHT:
                    narrative.setModifyAttachment(true);
                    break;
                case VIEW_NARRATIVE_RIGHT:
                    narrative.setViewAttachment(true);
                    break;
                case NO_NARRATIVE_RIGHT:
                    break;
            }
//            List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
//            for (NarrativeUserRights narrativeRight : narrativeUserRights) {
////                if(narrativeRight.getUserId().equalsIgnoreCase(proposalDevelopmentDocument.getUpdateUser())){
//              if(narrativeRight.getUserId().equalsIgnoreCase("000000006")){
//                    if(narrativeRight.getAccessType().equals(NARRATIVE_ACCESS_TYPE_MODIFY)){ 
//                        narrative.setModifyAttachment(true);
//                    }else if(narrativeRight.getAccessType().equals(NARRATIVE_ACCESS_TYPE_VIEW)){ 
//                        narrative.setViewAttachment(true);
//                    }
//                    break;
//                }
//            }
        }
    }
    /**
     * 
     * This method populates narrative type details to narrative object by looking at the narrative type code
     * @param narrative
     */
    private void populateNarrativeType(Narrative narrative) {
        Map<String,String> narrativeTypeMap = new HashMap<String,String>();
        narrativeTypeMap.put(NARRATIVE_TYPE_CODE, narrative.getNarrativeTypeCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeType narrType = (NarrativeType) service.findByPrimaryKey(NarrativeType.class, narrativeTypeMap);
        if (narrType != null)
            narrative.setNarrativeType(narrType);
    }

    /**
     * 
     * This method adds new proposal attachment(narrative) to the narrative list.
     * User can not add more than one narrative with the same narrative type which 
     * has allowMultipleFlag as false. This rule is being validated by using AddNarrativeRule
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward of ProposalAbstractsAttachments page
     * @throws Exception
     */
    public ActionForward addProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narrative = proposalDevelopmentForm.getNewNarrative();
        narrative.setProposalNumber(proposalDevelopmentDocument.getProposalNumber());
        narrative.setModuleNumber(proposalDevelopmentDocument.getProposalNextValue(NARRATIVE_MODULE_NUMBER));
        narrative.setModuleSequenceNumber(proposalDevelopmentDocument.getProposalNextValue(NARRATIVE_MODULE_SEQUENCE_NUMBER));
        narrative.setUpdateUser(proposalDevelopmentDocument.getUpdateUser());
        narrative.setUpdateTimestamp(proposalDevelopmentDocument.getUpdateTimestamp());
        narrative.setModifyAttachment(true);
        populateNarrativeType(narrative);
        boolean rulePassed = true;
        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new AddNarrativeEvent(EMPTY_STRING, proposalDevelopmentDocument, narrative));

        if (!rulePassed){
            proposalDevelopmentForm.setNewNarrative(new Narrative());            
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        populateNarrativeStatus(narrative);
        populateNarrativeAttachment(narrative);
        populateNarrativeUserRights(proposalDevelopmentDocument, narrative);
        
        proposalDevelopmentDocument.getNarratives().add(narrative);
        proposalDevelopmentForm.setNewNarrative(new Narrative());

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * 
     * This method populates narrative status details to narrative object by looking at the narrative status code
     * @param narrative
     */
    private void populateNarrativeStatus(Narrative narrative) {
        Map<String,String> narrativeStatusMap = new HashMap<String,String>();
        narrativeStatusMap.put(NARRATIVE_STATUS_CODE, narrative.getModuleStatusCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeStatus narrStatus = (NarrativeStatus) service.findByPrimaryKey(NarrativeStatus.class, narrativeStatusMap);
        if (narrStatus != null)
            narrative.setNarrativeStatus(narrStatus);
    }

    /**
     * 
     * This method used to populate the narrative attachment byte stream to stick into 
     * Narrative object.
     * @param narrative
     * @throws IOException
     */
    private void populateNarrativeAttachment(Narrative narrative) throws IOException{
        FormFile narrativeFile = narrative.getNarrativeFile();
        byte[] narrativeFileData = narrativeFile.getFileData();
        if (narrativeFileData.length > 0) {
            LOG.info("File exists. Creating new NarrativeAttachment and adding it to Narrative collection ");
            NarrativeAttachment narrativeAttachment;
            if (narrative.getNarrativeAttachmentList().isEmpty()){
                narrativeAttachment = new NarrativeAttachment(); 
                narrative.getNarrativeAttachmentList().add(narrativeAttachment);
            }else{
                narrativeAttachment = narrative.getNarrativeAttachmentList().get(0);
                if(narrativeAttachment==null){
                    narrativeAttachment = new NarrativeAttachment(); 
                    narrative.getNarrativeAttachmentList().set(0,narrativeAttachment);
                }
            }
            String fileName = narrativeFile.getFileName();
            narrativeAttachment.setFileName(fileName);
            narrativeAttachment.setContentType(narrativeFile.getContentType());
            narrativeAttachment.setNarrativeData(narrativeFile.getFileData());
            narrativeAttachment.setProposalNumber(narrative.getProposalNumber());
            narrativeAttachment.setModuleNumber(narrative.getModuleNumber());
            narrative.setFileName(narrativeAttachment.getFileName());
        }else{
        	narrative.getNarrativeAttachmentList().clear();
        }
    }
    /**
     * 
     * This method fetches the users from <code>proposalUserRoles</code> list 
     * and populate <code>narrativeUserRights</code> for the proposal persons.
     * <li>Set <code>accessType</code> to 'R' if user has VIEW_NARRATIVE right for the proposal
     * <li>Set <code>accessType</code> to 'M' if user has MODIFY_NARRATIVE right for the proposal
     * <li>Set <code>accessType</code> to 'N' if user does not have any narrative rights for the proposal
     * @param proposalDevelopmentDocument
     * @param narrative
     */
    private void populateNarrativeUserRights(ProposalDevelopmentDocument proposalDevelopmentDocument, Narrative narrative) {
        List<NarrativeUserRights> narrativeUserRights = narrative.getNarrativeUserRights();
        Map<String,Integer> proposalUserRolesMap = new HashMap<String,Integer>();
        proposalUserRolesMap.put(PROPOSAL_NUMBER, narrative.getProposalNumber());
        Collection<ProposalUserRoles> proposalUserRolesList = getBusinessObjectService().findMatching(ProposalUserRoles.class, proposalUserRolesMap);
        
        List<ProposalUserRoles> usrRights = proposalDevelopmentDocument.getProposalUserRoles();
        for (ProposalUserRoles proposalUserRoles : proposalUserRolesList) {
            boolean continueFlag = false;
            Map<String,String> personVal = new HashMap<String,String>();
            personVal.put(PERSON_ID, proposalUserRoles.getUserId());
            Person per = (Person) getBusinessObjectService().findByPrimaryKey(Person.class, personVal);
            String personName = per.getFirstName() + " " + per.getLastName();
            for (NarrativeUserRights tempNarrUserRight : narrativeUserRights) {
                if (tempNarrUserRight.getUserId().equalsIgnoreCase(proposalUserRoles.getUserId())) {
                    continueFlag = true;
                    tempNarrUserRight.setPersonName(personName);
                    break;
                }
            }
            if (continueFlag)
                continue;
            Map<String,Integer> proRoleMap = new HashMap<String,Integer>();
            proRoleMap.put(ROLE_ID, proposalUserRoles.getRoleId());
            Collection<RoleRight> roleRights = getBusinessObjectService().findMatching(RoleRight.class, proRoleMap);
//            String accessType = NARRATIVE_ACCESS_TYPE_NONE;
            String accessType = NarrativeRight.NO_NARRATIVE_RIGHT.getAccessType();
            for (RoleRight roleRight : roleRights) {
                if (roleRight.getRightId().equals(RightConstants.VIEW_NARRATIVE)) {
//                    accessType = NARRATIVE_ACCESS_TYPE_VIEW;
                  accessType = NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType();
                }else if (roleRight.getRightId().equals(RightConstants.MODIFY_NARRATIVE)) {
//                    accessType = NARRATIVE_ACCESS_TYPE_MODIFY;
                    accessType = NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType();
                }
            }
            NarrativeUserRights narrUserRight = new NarrativeUserRights();
            narrUserRight.setProposalNumber(narrative.getProposalNumber());
            narrUserRight.setModuleNumber(narrative.getModuleNumber());
            narrUserRight.setUserId(proposalUserRoles.getUserId());
            narrUserRight.setAccessType(accessType);
            narrUserRight.setPersonName(personName);
            narrativeUserRights.add(narrUserRight);
        }
    }
    /**
     * 
     * This method used to stream the attachment byte array onto the browser.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narrative = pd.getNarratives().get(lineNumber);
        NarrativeAttachment narrativeAttachment = findNarrativeAttachment(narrative);
        if(narrativeAttachment==null && !narrative.getNarrativeAttachmentList().isEmpty()){//get it from the memory
            narrativeAttachment = narrative.getNarrativeAttachmentList().get(0);
        }
        streamToResponse(narrativeAttachment,response);
        return null;
    }
    /**
     * 
     * This method used to find the narrative attachment for a narrative
     * @param narrative
     * @return NarrativeAttachment
     */
    private NarrativeAttachment findNarrativeAttachment(Narrative narrative){
        Map<String,Integer> narrativeAttachemntMap = new HashMap<String,Integer>();
        narrativeAttachemntMap.put(PROPOSAL_NUMBER, narrative.getProposalNumber());
        narrativeAttachemntMap.put(MODULE_NUMBER, narrative.getModuleNumber());
        return (NarrativeAttachment)getBusinessObjectService().findByPrimaryKey(NarrativeAttachment.class, narrativeAttachemntMap);
    }
    /**
     * 
     * Handy method to stream the byte array to response object
     * @param attachmentDataSource
     * @param response
     * @throws Exception
     */
    private void streamToResponse(AttachmentDataSource attachmentDataSource,HttpServletResponse response) throws Exception{
        byte[] xbts = attachmentDataSource.getContent();
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            WebUtils.saveMimeOutputStreamAsFile(response, attachmentDataSource.getContentType(), baos, attachmentDataSource.getFileName());
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
    }

    /**
     * 
     * This method is used to delete the proposal attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        Narrative narr = proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().get(getLineToDelete(request));
        NarrativeAttachment narrAtt = new NarrativeAttachment();
        narrAtt.setProposalNumber(narr.getProposalNumber());
        narrAtt.setModuleNumber(narr.getModuleNumber());
        if (narr.getNarrativeAttachmentList().isEmpty())
            narr.getNarrativeAttachmentList().add(narrAtt);
        else
            narr.getNarrativeAttachmentList().set(0, narrAtt);
        proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().remove(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
   /**
    * 
    * This method used to get the proposal user rights
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return 
    * @throws Exception
    */
    public ActionForward getProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.setShowMaintenanceLinks(false);
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = pd.getNarratives().get(lineNumber);
        populateNarrativeUserRights(pd, narr);
        request.setAttribute(LINE_NUMBER, line);
        // proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().remove(getLineToDelete(request));
        return mapping.findForward(MAPPING_NARRATIVE_ATTACHMENT_RIGHTS_PAGE);
    }

    /**
     * 
     * This method to send the request back to a page which closes by itself. Since Attachment right page 
     * is opened in a new window, after saving, it should close by itself.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward of close page
     * @throws Exception
     */
    public ActionForward addProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(MAPPING_CLOSE_PAGE);
    }

    /**
     * 
     * This method used to replace the attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward replaceProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narrative = pd.getNarratives().get(getLineToDelete(request));
        NarrativeAttachment narrativeAttachment = findNarrativeAttachment(narrative);
        if(narrativeAttachment!=null)
            if (narrative.getNarrativeAttachmentList().isEmpty())
                narrative.getNarrativeAttachmentList().add(narrativeAttachment);
            else
                narrative.getNarrativeAttachmentList().set(0, narrativeAttachment);
        populateNarrativeAttachment(narrative);
        return mapping.findForward(MAPPING_BASIC);
    }
    
    /**
     * Update the User and Timestamp for the business object.
     * @param doc the business object
     */
    private void updateUserTimestamp(KraPersistableBusinessObjectBase bo) {
        String updateUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();
    
        // Since the UPDATE_USER column is only VACHAR(8), we need to truncate this string if it's longer than 8 characters
        if (updateUser.length() > 8) {
            updateUser = updateUser.substring(0, 8);
        }
        bo.setUpdateTimestamp(((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        bo.setUpdateUser(updateUser);
    }
    
    /**
     * Adds an Abstract to the Proposal Development Document.
     * 
     * Assuming we have a valid new abstract, it is taken from the form 
     * and moved into the document's list of abstracts.  The form's abstract
     * is then cleared for the next abstract to be added.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward addAbstract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalAbstract proposalAbstract = proposalDevelopmentForm.getNewProposalAbstract();
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new AddAbstractEvent(proposalDevelopmentForm.getProposalDevelopmentDocument(), proposalAbstract));
                    
        // if the rule evaluation passed, let's add it
        if (rulePassed) {
            updateUserTimestamp(proposalAbstract);
            proposalDevelopmentForm.getProposalDevelopmentDocument().getProposalAbstracts().add(proposalAbstract);
            proposalDevelopmentForm.setNewProposalAbstract(new ProposalAbstract());
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Deletes an Abstract from the Proposal Development Document.
     * 
     * If the user confirms the deletion, the abstract is removed from
     * the document's list of abstracts.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward deleteAbstract(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        
        Object question = request.getParameter(RiceConstants.QUESTION_INST_ATTRIBUTE_NAME);
        int lineNum = getLineToDelete(request);
        
        // Check to see if we have confirmed the deletion with the user.  If not,
        // then ask the confirmation question.
        
        if (question == null) {
            String description = doc.getProposalAbstracts().get(lineNum).getAbstractType().getDescription();
            return this.performQuestionWithoutInput(mapping, form, request, response, CONFIRM_DELETE_ABSTRACT_KEY, 
                                                    buildDeleteAbstractConfirmationQuestion(description),
                                                    RiceConstants.CONFIRMATION_QUESTION, "deleteAbstract", EMPTY_STRING);
        }
        else {
            // If the user has indicated that the deletion should occur, then go
            // ahead and remove it from the list of abstracts.
            
            Object buttonClicked = request.getParameter(RiceConstants.QUESTION_CLICKED_BUTTON);
            if ((CONFIRM_DELETE_ABSTRACT_KEY.equals(question)) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                proposalDevelopmentForm.getProposalDevelopmentDocument().getProposalAbstracts().remove(lineNum);
            }
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    /**
     * Builds the Delete Abstract Confirmation Question.  
     * 
     * The confirmation question is extracted from the resource bundle
     * and the parameter {0} is replaced with the name of the abstract type
     * that will be deleted.
     * 
     * @param abstractDescription the abstract's human-readable description
     * @return the confirmation question
     * @throws Exception
     */
    private String buildDeleteAbstractConfirmationQuestion(String abstractDescription) throws Exception {
        KualiConfigurationService kualiConfiguration = KNSServiceLocator.getKualiConfigurationService();
        return StringUtils.replace(kualiConfiguration.getPropertyString(KeyConstants.QUESTION_DELETE_ABSTRACT_CONFIRMATION), "{0}", abstractDescription);
    }

    private BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }
    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

    /**
     * Adds a personnel attachment.
     * 
     * Move the new attachment from the form 
     * into the document's list of personnelbiographyattachment.  The form's abstract
     * is then cleared for the next personnel attachment to be added.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward addPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().addProposalPersonBiography(proposalDevelopmentForm.getNewPropPersonBio());
        proposalDevelopmentForm.setNewPropPersonBio(new ProposalPersonBiography());

        return mapping.findForward("basic");
    }

    /**
     * Deletes a personnel attachment from the Proposal Development Document.
     * 
     * Removed the personnel attachment from the document's list of personnel attachments.
     * 
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward deletePersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().getPropPersonBios().remove(getLineToDelete(request));
        return mapping.findForward("basic");
    }

    /**
     * View a personnel attachment file.
     *      
     * @param mapping The mapping associated with this action.
     * @param form The Proposal Development form.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the destination (always the original proposal web page that caused this action to be invoked)
     * @throws Exception
     */
    public ActionForward viewPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter(LINE_NUMBER);
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalPersonBiography propPersonBio = pd.getPropPersonBios().get(lineNumber);
        Map<String,Integer> propPersonBioAttVal = new HashMap<String,Integer>();
        propPersonBioAttVal.put(PROPOSAL_NUMBER, propPersonBio.getProposalNumber());
        propPersonBioAttVal.put("biographyNumber", propPersonBio.getBiographyNumber());
        propPersonBioAttVal.put("proposalPersonNumber", propPersonBio.getProposalPersonNumber());
        ProposalPersonBiographyAttachment propPersonBioAttachment = (ProposalPersonBiographyAttachment)getBusinessObjectService().findByPrimaryKey(ProposalPersonBiographyAttachment.class, propPersonBioAttVal);
        if(propPersonBioAttachment==null && !propPersonBio.getPersonnelAttachmentList().isEmpty()){//get it from the memory
            propPersonBioAttachment = propPersonBio.getPersonnelAttachmentList().get(0);
        }
        //return streamDataToBrowser(mapping,propPersonBioAttachment,response);

        // alternative
//          byte[] xbts = propPersonBioAttachment.getContent();
//          ByteArrayOutputStream baos = new ByteArrayOutputStream(xbts.length);
//          baos.write(xbts);
//          WebUtils.saveMimeOutputStreamAsFile(response, propPersonBioAttachment.getContentType(), baos, propPersonBioAttachment.getFileName());
          streamToResponse(propPersonBioAttachment,response);
        return  null;
    }

    /**
     * 
     * It add and institutional attachment to proposal document.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addInstitutionalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.setNewNarrative(proposalDevelopmentForm.getNewInstitute());
        proposalDevelopmentForm.getNewNarrative().setNarrativeTypeCode(proposalDevelopmentForm.getNewNarrative().getInstitutionalAttachmentTypeCode());
        proposalDevelopmentForm.getNewNarrative().setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        proposalDevelopmentForm.setNewInstitute(new Narrative());
        return addProposalAttachment(mapping, form, request, response);
    }

    /**
     * 
     * Delete an institutional attachment
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteInstitutionalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return deleteProposalAttachment(mapping, form, request, response);
    }
    
    /**
     * 
     * View an institutional attachment file.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewInstitutionalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
          return downloadProposalAttachment(mapping, form, request, response);
    }
    

}
