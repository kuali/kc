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

import static org.kuali.kra.infrastructure.Constants.NEW_PROPOSAL_PERSON_PROPERTY_NAME;
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
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.KNSServiceLocator;

public class ProposalDevelopmentAbstractsAttachmentsAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAbstractsAttachmentsAction.class);
   
    private static final String CONFIRM_DELETE_ABSTRACT_KEY = "confirmDeleteAbstract";

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        List<Narrative> narrList = pd.getNarratives();
        for (Narrative narrative : narrList) {
            populateNarrativeUserRights(pd, narrative);
        }
        return super.save(mapping, form, request, response);
    }

    public ActionForward addProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument propDoc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = propDoc.getNewNarrative();
        narr.setProposalNumber(propDoc.getProposalNumber());
        narr.setModuleNumber(propDoc.getProposalNextValue(Constants.NARRATIVE_MODULE_NUMBER));
        narr.setModuleSequenceNumber(propDoc.getProposalNextValue(Constants.NARRATIVE_MODULE_SEQUENCE_NUMBER));
        narr.setUpdateUser(propDoc.getUpdateUser());
        narr.setUpdateTimestamp(propDoc.getUpdateTimestamp());

        Map narrTypeMap = new HashMap();
        narrTypeMap.put("narrativeTypeCode", narr.getNarrativeTypeCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeType narrType = (NarrativeType) service.findByPrimaryKey(NarrativeType.class, narrTypeMap);
        if (narrType != null)
            narr.setNarrativeType(narrType);

        boolean rulePassed = true;

        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new AddNarrativeEvent("newNarrative", propDoc, narr));

        if (!rulePassed){
            // remove the status stuff from institute attachment if rule is not satisfied
            if (narr.getNarrativeType().getNarrativeTypeGroup().equals(Constants.INSTITUTE_NARRATIVE_TYPE_GROUP_CODE)) {
                propDoc.setNewNarrative(new Narrative());            
            }
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        Map narrStatusMap = new HashMap();
        narrStatusMap.put("narrativeStatusCode", narr.getModuleStatusCode());
        NarrativeStatus narrStatus = (NarrativeStatus) service.findByPrimaryKey(NarrativeStatus.class, narrStatusMap);
        if (narrStatus != null)
            narr.setNarrativeStatus(narrStatus);
        populateNarrativeAttachment(narr);
        populateNarrativeUserRights(propDoc, narr);
        propDoc.getNarratives().add(narr);
        propDoc.setNewNarrative(new Narrative());

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    private void populateNarrativeAttachment(Narrative narr) throws IOException{
        FormFile narrFile = narr.getNarrativeFile();
        byte[] fileData = narrFile.getFileData();
        if (fileData.length > 0) {
            LOG.info("File exists. Creating new NarrativeAttachment and adding it to Narrative collection ");
            NarrativeAttachment narrAtt;
            if (narr.getNarrativeAttachmentList().isEmpty()){
                narrAtt = new NarrativeAttachment(); 
                narr.getNarrativeAttachmentList().add(narrAtt);
            }else{
                narrAtt = narr.getNarrativeAttachmentList().get(0);
            }
             
            narrAtt.setFileName(narrFile.getFileName());
            narrAtt.setContentType(narrFile.getContentType());
            narrAtt.setNarrativeData(narrFile.getFileData());
            narrAtt.setProposalNumber(narr.getProposalNumber());
            narrAtt.setModuleNumber(narr.getModuleNumber());
            narr.setFileName(narrAtt.getFileName());
//            if (narr.getNarrativeAttachmentList().isEmpty())
//                narr.getNarrativeAttachmentList().add(narrPdf);
//            else
//                narr.getNarrativeAttachmentList().set(0, narrPdf);
        }else{
        	narr.getNarrativeAttachmentList().clear();
        }
    }
    private void populateNarrativeUserRights(ProposalDevelopmentDocument pd, Narrative narr) {
        List<NarrativeUserRights> narrUserRights = narr.getNarrativeUserRights();
        // Collection<ProposalUserRoles> usrRights = getBusinessObjectService().findMatching(ProposalUserRoles.class, proRoleMap);
        List<ProposalUserRoles> usrRights = pd.getProposalUserRoles();
        for (ProposalUserRoles proposalUserRoles : usrRights) {
            boolean continueFlag = false;
            Map personVal = new HashMap();
            personVal.put("personId", proposalUserRoles.getUserId());
            Person per = (Person) getBusinessObjectService().findByPrimaryKey(Person.class, personVal);
            String personName = per.getFirstName() + " " + per.getLastName();
            for (NarrativeUserRights tempNarrUserRight : narrUserRights) {
                if (tempNarrUserRight.getUserId().equalsIgnoreCase(proposalUserRoles.getUserId())) {
                    continueFlag = true;
                    tempNarrUserRight.setPersonName(personName);
                    break;
                }
            }
            if (continueFlag)
                continue;
            Map proRoleMap = new HashMap();
            proRoleMap.put("roleId", proposalUserRoles.getRoleId());
            // List<RoleRights> roleRights = proposalUserRoles.getRoleRightsList();
            Collection<RoleRight> roleRights = getBusinessObjectService().findMatching(RoleRight.class, proRoleMap);
            String accessType = "N";
            for (RoleRight roleRight : roleRights) {
                if (roleRight.getRightId().equals(RightConstants.VIEW_NARRATIVE)) {// create RightConstants class and define these
                    // there
                    accessType = "R";
                }
                else if (roleRight.getRightId().equals(RightConstants.MODIFY_NARRATIVE)) {
                    accessType = "M";
                }
            }
            NarrativeUserRights narrUserRight = new NarrativeUserRights();
            narrUserRight.setProposalNumber(narr.getProposalNumber());
            narrUserRight.setModuleNumber(narr.getModuleNumber());
            narrUserRight.setUserId(proposalUserRoles.getUserId());
            narrUserRight.setAccessType(accessType);
            narrUserRight.setPersonName(personName);
            narrUserRights.add(narrUserRight);
        }

    }

    public ActionForward downloadProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        String line = request.getParameter("line");
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = pd.getNarratives().get(lineNumber);
        NarrativeAttachment narrAttachment = findNarrativeAttachment(narr);
        if(narrAttachment==null && !narr.getNarrativeAttachmentList().isEmpty()){//get it from the memory
            narrAttachment = narr.getNarrativeAttachmentList().get(0);
        }
        streamToResponse(narrAttachment,response);
//        byte[] xbts = narrAttachment.getContent();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream(xbts.length);
//        baos.write(xbts);
//        WebUtils.saveMimeOutputStreamAsFile(response, narrAttachment.getContentType(), baos, narrAttachment.getFileName());
        return null;
    }
    private NarrativeAttachment findNarrativeAttachment(Narrative narr){
        Map narrAttVal = new HashMap();
        narrAttVal.put("proposalNumber", narr.getProposalNumber());
        narrAttVal.put("moduleNumber", narr.getModuleNumber());
        return (NarrativeAttachment)getBusinessObjectService().findByPrimaryKey(NarrativeAttachment.class, narrAttVal);
    }
    public void streamToResponse(AttachmentDataSource dataSource,HttpServletResponse response) throws Exception{
        byte[] xbts = dataSource.getContent();
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            WebUtils.saveMimeOutputStreamAsFile(response, dataSource.getContentType(), baos, dataSource.getFileName());
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
    
   
    public ActionForward getProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.setShowMaintenanceLinks(false);
        String line = request.getParameter("line");
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = pd.getNarratives().get(lineNumber);
        populateNarrativeUserRights(pd, narr);
        request.setAttribute("line", line);
        // proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().remove(getLineToDelete(request));
        return mapping.findForward("attachmentRights");
    }

    public ActionForward addProposalAttachmentRights(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_CLOSE_PAGE);
    }

    
    public ActionForward replaceProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        Narrative narr = pd.getNarratives().get(getLineToDelete(request));
        NarrativeAttachment narrAtt = findNarrativeAttachment(narr);
        if (narr.getNarrativeAttachmentList().isEmpty())
            narr.getNarrativeAttachmentList().add(narrAtt);
        else
            narr.getNarrativeAttachmentList().set(0, narrAtt);
        populateNarrativeAttachment(narr);
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        
        // If the user didn't select an abstract type, i.e. he/she choose the "select:" option,
        // then the Abstract Type will be null.  Display an error message telling the user
        // to select an abstract type.
        
        if (proposalAbstract.getAbstractType() == null) {
            GlobalVariables.getErrorMap().putError(Constants.ABSTRACTS_PROPERTY_KEY, 
                                                   KeyConstants.ERROR_ABSTRACT_TYPE_NOT_SELECTED);
        } else {
            // check any business rules
            boolean rulePassed = getKualiRuleService().applyRules(new AddAbstractEvent(proposalDevelopmentForm.getProposalDevelopmentDocument(), proposalAbstract));
                    
            // if the rule evaluation passed, let's add it
            if (rulePassed) {
                updateUserTimestamp(proposalAbstract);
                proposalDevelopmentForm.getProposalDevelopmentDocument().getProposalAbstracts().add(proposalAbstract);
                proposalDevelopmentForm.setNewProposalAbstract(new ProposalAbstract());
        
            }
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
                                                    RiceConstants.CONFIRMATION_QUESTION, "deleteAbstract", "");
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
        ProposalDevelopmentDocument propDoc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalPersonBiography newPropPersonBio = proposalDevelopmentForm.getNewPropPersonBio();
        newPropPersonBio.setProposalNumber(propDoc.getProposalNumber());
        newPropPersonBio.setUpdateUser(propDoc.getUpdateUser());
        newPropPersonBio.setUpdateTimestamp(propDoc.getUpdateTimestamp());
        // TODO :  bionumber ok?
        newPropPersonBio.setBiographyNumber(propDoc.getProposalNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        newPropPersonBio.setPropPerDocType(new PropPerDocType());
        newPropPersonBio.getPropPerDocType().setDocumentTypeCode(newPropPersonBio.getDocumentTypeCode());
        newPropPersonBio.refreshReferenceObject("propPerDocType");
        FormFile personnelAttachmentFile = newPropPersonBio.getPersonnelAttachmentFile();
        newPropPersonBio.setFileName(personnelAttachmentFile.getFileName());
        byte[] fileData = personnelAttachmentFile.getFileData();
        if (fileData.length > 0) {
            ProposalPersonBiographyAttachment personnelAttachment = new ProposalPersonBiographyAttachment();
            personnelAttachment.setFileName(personnelAttachmentFile.getFileName());
            personnelAttachment.setProposalNumber(newPropPersonBio.getProposalNumber());
            personnelAttachment.setProposalPersonNumber(newPropPersonBio.getProposalPersonNumber());
            personnelAttachment.setBiographyData(personnelAttachmentFile.getFileData());
            personnelAttachment.setContentType(personnelAttachmentFile.getContentType());
            if (newPropPersonBio.getPersonnelAttachmentList().isEmpty())
                newPropPersonBio.getPersonnelAttachmentList().add(personnelAttachment);
            else
                newPropPersonBio.getPersonnelAttachmentList().set(0, personnelAttachment);
        }
        propDoc.getPropPersonBios().add(newPropPersonBio);
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
        String line = request.getParameter("line");
        int lineNumber = line == null ? 0 : Integer.parseInt(line);
        ProposalDevelopmentDocument pd = proposalDevelopmentForm.getProposalDevelopmentDocument();
        ProposalPersonBiography propPersonBio = pd.getPropPersonBios().get(lineNumber);
        Map propPersonBioAttVal = new HashMap();
        propPersonBioAttVal.put("proposalNumber", propPersonBio.getProposalNumber());
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
        ProposalDevelopmentDocument propDoc = proposalDevelopmentForm.getProposalDevelopmentDocument();
        propDoc.setNewNarrative(proposalDevelopmentForm.getNewInstitute());
        propDoc.getNewNarrative().setNarrativeTypeCode(propDoc.getNewNarrative().getInstitutionalAttachmentTypeCode());
        propDoc.getNewNarrative().setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
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
