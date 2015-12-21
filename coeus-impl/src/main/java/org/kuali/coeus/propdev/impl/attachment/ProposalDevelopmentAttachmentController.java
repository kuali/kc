/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.coeus.propdev.impl.person.attachment.AddPersonnelAttachmentEvent;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.GrowlMessage;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.FileControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import java.util.*;

@Controller
public class ProposalDevelopmentAttachmentController extends ProposalDevelopmentControllerBase {

    private static final String ATTACHMENT_FILE = "multipartFile";

    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService legacyNarrativeService;

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("kcFileControllerService")
    private FileControllerService kcFileControllerService;

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("multipartFileValidationService")
    private MultipartFileValidationService multipartFileValidationService;

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addFileUploadLine")
    public ModelAndView addFileUploadLine(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                          MultipartHttpServletRequest request) throws Exception {
        final String selectedCollectionPath = request.getParameter(ProposalDevelopmentConstants.KradConstants.BINDING_PATH);

        addEditableCollectionLine(form, selectedCollectionPath);

        final MessageMap messages = new MessageMap();
        request.getFileNames().forEachRemaining(name -> messages.merge(multipartFileValidationService.validateMultipartFile(ATTACHMENT_FILE, request.getFile(name))));

        if (!messages.hasMessages()) {
            synchronized (ObjectPropertyUtils.getPropertyValue(form, selectedCollectionPath)) {
                return getFileControllerService().addFileUploadLine(form);
            }
        } else {
            getGlobalVariableService().getMessageMap().merge(messages);
            doErrorMessageWorkaround(messages);
            form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
            form.setAjaxRequest(true);

            return getRefreshControllerService().refresh(form);
        }
    }

    /**
     * workaround because regular message are not working in KRAD for fileUploadLine.  Using growls
     */
    protected void doErrorMessageWorkaround(MessageMap messages) {
        messages.getAllPropertiesWithErrors().stream()
                .flatMap(prop -> messages.getMessages(prop).stream())
                .forEach(message -> {
                    final GrowlMessage growl = new GrowlMessage();
                    growl.setMessageKey(message.getErrorKey());
                    growl.setMessageParameters(message.getMessageParameters());
                    growl.setComponentCode(message.getComponentCode());
                    growl.setNamespaceCode(message.getNamespaceCode());
                    growl.setTitle("Error");
                    getGlobalVariableService().getMessageMap().addGrowlMessage(growl);
                });
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteFileUploadLine")
    public ModelAndView deleteFileUploadLine(@ModelAttribute("KualiForm") final UifFormBase uifForm) throws Exception {
        return getFileControllerService().deleteFileUploadLine(uifForm);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=getFileFromLine")
    public void getFileFromLine(@ModelAttribute("KualiForm") final UifFormBase uifForm, HttpServletResponse response) throws Exception {
        getKcFileControllerService().getFileFromLine(uifForm,response);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=markAllProposalAttachments")
    public ModelAndView markAllProposalAttachments(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
    	return markAllAttachmentStatus(form, form.getProposalDevelopmentAttachmentHelper().getProposalAttachmentModuleStatusCode());
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=markAllInternalAttachments")
    public ModelAndView markAllInternalAttachments(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
    	return markAllAttachmentStatus(form, form.getProposalDevelopmentAttachmentHelper().getInternalAttachmentModuleStatusCode());
     }
    
    protected ModelAndView markAllAttachmentStatus(ProposalDevelopmentDocumentForm form, String moduleStatusCode) {
        final String collectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(form, collectionPath);
        collection.stream()
                .filter(object -> object instanceof Narrative)
                .map (object -> (Narrative) object)
                .forEach(narrative -> {
                    narrative.setModuleStatusCode(moduleStatusCode);
                    getDataObjectService().wrap(narrative).fetchRelationship(ProposalDevelopmentConstants.KradConstants.NARRATIVE_STATUS);
                });
        return getRefreshControllerService().refresh(form);
   }
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareNarrative")
    public ModelAndView prepareNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
       String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
       form.getProposalDevelopmentAttachmentHelper().reset();

       if (StringUtils.isNotEmpty(selectedLine)) {
           Narrative tmpNarrative = new Narrative();
           form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
           PropertyUtils.copyProperties(tmpNarrative,form.getDevelopmentProposal().getNarrative(Integer.parseInt(selectedLine)));
           form.getProposalDevelopmentAttachmentHelper().setNarrative(tmpNarrative);
       }

        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_PROPOSAL_DETAILS, true, form);
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareBiography")
    public ModelAndView prepareBiography(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        form.getProposalDevelopmentAttachmentHelper().reset();

        if (StringUtils.isNotEmpty(selectedLine)) {
            ProposalPersonBiography tmpBiography = new ProposalPersonBiography();
            form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
            PropertyUtils.copyProperties(tmpBiography,form.getDevelopmentProposal().getPropPersonBio(Integer.parseInt(selectedLine)));
            form.getProposalDevelopmentAttachmentHelper().setBiography(tmpBiography);
        }

        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_PERSONNEL_DETAILS, true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareAbstract")
    public ModelAndView prepareAbstract(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        form.getProposalDevelopmentAttachmentHelper().reset();

        if (StringUtils.isNotEmpty(selectedLine)) {
            ProposalAbstract tmpAbstract = new ProposalAbstract();
            form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
            PropertyUtils.copyProperties(tmpAbstract,form.getDevelopmentProposal().getProposalAbstract(Integer.parseInt(selectedLine)));
            form.getProposalDevelopmentAttachmentHelper().setProposalAbstract(tmpAbstract);
        }

        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_ABSTRACT_DETAILS, true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareInstituteAttachment")
    public ModelAndView prepareInstituteAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        form.getProposalDevelopmentAttachmentHelper().reset();

        if (StringUtils.isNotEmpty(selectedLine)) {
            Narrative tmpNarrative = new Narrative();
            form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
            PropertyUtils.copyProperties(tmpNarrative, form.getDevelopmentProposal().getInstituteAttachment(Integer.parseInt(selectedLine)));
            form.getProposalDevelopmentAttachmentHelper().setInstituteAttachment(tmpNarrative);
        }

        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_INTERNAL_DETAILS, true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareNote")
    public ModelAndView prepareNote(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        form.getProposalDevelopmentAttachmentHelper().reset();

        if (StringUtils.isNotEmpty(selectedLine)) {
            Note tmpNote = new Note();
            form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
            PropertyUtils.copyProperties(tmpNote, form.getProposalDevelopmentDocument().getNote(Integer.parseInt(selectedLine)));
            form.getProposalDevelopmentAttachmentHelper().setNote(tmpNote);
        }

        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_NOTE_DETAILS, true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addNarrative")
    public ModelAndView addNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getNarrative();
        initializeNarrative(narrative, form);
        if ( getKualiRuleService().applyRules(new AddNarrativeEvent(ProposalDevelopmentConstants.KradConstants.PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER_NARRATIVE,form.getProposalDevelopmentDocument(),form.getProposalDevelopmentAttachmentHelper().getNarrative()))) {
            form.getDevelopmentProposal().getNarratives().add(0,narrative);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            form.setUpdateComponentId(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_PROPOSAL_DETAILS);
            form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        }
        return getRefreshControllerService().refresh(form);

    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=checkForExistingNarratives")
    public ModelAndView checkForExistingNarratives(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                                   @RequestParam String currentValue,@RequestParam(required = false) String previousValue,@RequestParam String propertyPath) throws Exception {
        if (form.getDevelopmentProposal().isChild()) {
            NarrativeType narrativeType = getDataObjectService().find(NarrativeType.class, currentValue);
            DevelopmentProposal parentProposal = getDataObjectService().find(DevelopmentProposal.class,form.getDevelopmentProposal().getHierarchyParentProposalNumber());
            if(!narrativeType.isAllowMultiple() && getLegacyNarrativeService().doesProposalHaveNarrativeType(parentProposal,narrativeType)) {
                form.getProposalDevelopmentAttachmentHelper().setCurrentNarrativeTypeDescription(narrativeType.getDescription());
                form.getProposalDevelopmentAttachmentHelper().setPreviousNarrativeTypeValue(previousValue);
                form.getProposalDevelopmentAttachmentHelper().setNarrativeTypePropertyPath(propertyPath);
                return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENT_PAGE_CONFIRM_ADD_ATTACHMENT_TO_CHILD, true, form);
            }
        }
        return null;
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=revertToPreviousNarrativeType")
    public ModelAndView revertToPreviousNarrativeType(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        String propertyPath = form.getProposalDevelopmentAttachmentHelper().getNarrativeTypePropertyPath();
        String previousNarrativeTypeValue = form.getProposalDevelopmentAttachmentHelper().getPreviousNarrativeTypeValue();
        ObjectPropertyUtils.setPropertyValue(form, propertyPath, previousNarrativeTypeValue);
        if (StringUtils.startsWith(propertyPath, ProposalDevelopmentConstants.KradConstants.PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER)) {
            form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
            form.setUpdateComponentId(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_PROPOSAL_DETAILS);
        }
        return getModelAndViewService().getModelAndView(form);
    }

    protected void initializeNarrative(Narrative narrative, ProposalDevelopmentDocumentForm form) throws Exception {
        getLegacyNarrativeService().prepareNarrative(form.getProposalDevelopmentDocument(), narrative);
        final MessageMap messages = multipartFileValidationService.validateMultipartFile(ATTACHMENT_FILE, narrative.getMultipartFile());
        if (!messages.hasMessages()) {
            narrative.init(narrative.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).updateAttachmentInformation(narrative.getNarrativeAttachment());
        } else {
            getGlobalVariableService().getMessageMap().merge(messages);
        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addInstituteAttachment")
    public ModelAndView addInstituteAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getInstituteAttachment();
        initializeNarrative(narrative, form);
        form.getDevelopmentProposal().getInstituteAttachments().add(0,narrative);
        form.getProposalDevelopmentAttachmentHelper().reset();

        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addBiography")
    public ModelAndView addBiography(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        ProposalPersonBiography biography = form.getProposalDevelopmentAttachmentHelper().getBiography();
        biography.setDevelopmentProposal(document.getDevelopmentProposal());
        biography.setBiographyNumber(document
                .getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        biography.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
        biography.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        getDataObjectService().wrap(biography).fetchRelationship(ProposalDevelopmentConstants.KradConstants.PROP_PER_DOC_TYPE);

        String errorPath = ProposalDevelopmentConstants.KradConstants.PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER_BIOGRAPHY + "." + ATTACHMENT_FILE; 
        final MessageMap messages = multipartFileValidationService.validateMultipartFile(errorPath, biography.getMultipartFile());
        boolean rulePassed = true;
        if (!messages.hasMessages()) {
            biography.init(biography.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).updateAttachmentInformation(biography.getPersonnelAttachment());

            if (getKualiRuleService().applyRules(new AddPersonnelAttachmentEvent(ProposalDevelopmentConstants.KradConstants.PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER_BIOGRAPHY, document, biography))){
                form.getDevelopmentProposal().getPropPersonBios().add(0,biography);
                form.getProposalDevelopmentAttachmentHelper().reset();
            } else {
                rulePassed = false;
            }
        } else {
            getGlobalVariableService().getMessageMap().merge(messages);
            rulePassed = false;
        }
        if(!rulePassed) {
            form.setUpdateComponentId(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_PERSONNEL_DETAILS);
            form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        }
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveNarrative")
    public ModelAndView saveNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getNarrative();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
        narrative.refreshReferenceObject(ProposalDevelopmentConstants.KradConstants.NARRATIVE_TYPE);
        narrative.refreshReferenceObject(ProposalDevelopmentConstants.KradConstants.NARRATIVE_STATUS);

        if(isAttachmentFileChanged(narrative.getMultipartFile())) {
            narrative.init(narrative.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).updateAttachmentInformation(narrative.getNarrativeAttachment());
        }
        if ( getKualiRuleService().applyRules(new AddNarrativeEvent(ProposalDevelopmentConstants.KradConstants.PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER_NARRATIVE,form.getProposalDevelopmentDocument(),form.getProposalDevelopmentAttachmentHelper().getNarrative()))) {
            form.getDevelopmentProposal().getNarratives().set(selectedLineIndex,narrative);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            form.setUpdateComponentId(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_PROPOSAL_DETAILS);
            form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        }

        if(form.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument().isEnroute()) {
            ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(form.getProposalDevelopmentDocument().getDevelopmentProposal(),
                Constants.DATA_OVERRIDE_NOTIFICATION_ACTION, Constants.DATA_OVERRIDE_CONTEXT);
            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setModifiedNarrative(narrative);

            ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(form.getProposalDevelopmentDocument().getDevelopmentProposal());
            if (form.getNotificationHelper().getPromptUserForNotificationEditor(context)) {
                form.getNotificationHelper().initializeDefaultValues(context);
                form.setSendNarrativeChangeNotification(true);
            }
            else {
                getKcNotificationService().sendNotification(context);
            }
        }
        return super.save(form);
    }

    protected boolean isAttachmentFileChanged(MultipartFile multipartFile) {
    	boolean fileChanged = multipartFile != null;
    	if(fileChanged) {
            final MessageMap messages = multipartFileValidationService.validateMultipartFile(ATTACHMENT_FILE, multipartFile);
            if (messages.hasMessages()) {
                getGlobalVariableService().getMessageMap().merge(messages);
            }
    	}
    	return fileChanged;
    }
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=sendNarrativeChangeNotification")
    public ModelAndView sendNarrativeChangeNotification(ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm) {
        if (proposalDevelopmentDocumentForm.isSendNarrativeChangeNotification()) {
             final String step = proposalDevelopmentDocumentForm.getNotificationHelper().getNotificationRecipients().isEmpty() ? "0" : "2";
                proposalDevelopmentDocumentForm.getActionParameters().put(ProposalDevelopmentConstants.KradConstants.KC_SEND_NOTIFICATION_WIZARD + ".step", step);
                return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.KC_SEND_NOTIFICATION_WIZARD, true, proposalDevelopmentDocumentForm);
        }
        proposalDevelopmentDocumentForm.setSendNarrativeChangeNotification(false);
        return getModelAndViewService().getModelAndView(proposalDevelopmentDocumentForm);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveBiography")
    public ModelAndView saveBiography(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        ProposalPersonBiography biography = form.getProposalDevelopmentAttachmentHelper().getBiography();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
        biography.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
        biography.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        getDataObjectService().wrap(biography).fetchRelationship(ProposalDevelopmentConstants.KradConstants.PROP_PER_DOC_TYPE);

        if(isAttachmentFileChanged(biography.getMultipartFile())) {
        	biography.init(biography.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).updateAttachmentInformation(biography.getPersonnelAttachment());
        }

        if (getKualiRuleService().applyRules(new AddPersonnelAttachmentEvent(ProposalDevelopmentConstants.KradConstants.PROPOSAL_DEVELOPMENT_ATTACHMENT_HELPER_BIOGRAPHY, form.getProposalDevelopmentDocument(), biography))) {
            form.getDevelopmentProposal().getPropPersonBios().set(selectedLineIndex, biography);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            form.setUpdateComponentId(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENTS_PAGE_PERSONNEL_DETAILS);
            form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
        }
        return super.save(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveInstituteAttachment")
    public ModelAndView saveInstituteAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getInstituteAttachment();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
        getDataObjectService().wrap(narrative).fetchRelationship(ProposalDevelopmentConstants.KradConstants.NARRATIVE_TYPE);
        final MessageMap messages = multipartFileValidationService.validateMultipartFile(ATTACHMENT_FILE, narrative.getMultipartFile());

        if (!messages.hasMessages()) {
            narrative.init(narrative.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).updateAttachmentInformation(narrative.getNarrativeAttachment());

            form.getDevelopmentProposal().getInstituteAttachments().set(selectedLineIndex, narrative);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            getGlobalVariableService().getMessageMap().merge(messages);
        }
        return super.save(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAbstract")
    public ModelAndView saveAbstract(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        ProposalAbstract proposalAbstract = form.getProposalDevelopmentAttachmentHelper().getProposalAbstract();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());

        form.getDevelopmentProposal().getProposalAbstracts().set(selectedLineIndex,proposalAbstract);
        form.getProposalDevelopmentAttachmentHelper().reset();

        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveNote")
    public ModelAndView saveNote(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Note note = form.getProposalDevelopmentAttachmentHelper().getNote();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());

        @SuppressWarnings("unchecked")
        final List<Note> notes = form.getProposalDevelopmentDocument().getNotes();
        notes.set(selectedLineIndex, note);
        form.getProposalDevelopmentAttachmentHelper().reset();

        return getRefreshControllerService().refresh(form);
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=updateEditableFileAttachment")
    public ModelAndView updateEditableFileAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{

        String collectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).toggleAttachmentFile(form, collectionPath, selectedLine);
        @SuppressWarnings("unchecked")
        List<ProposalDevelopmentAttachment> attachments = (List<ProposalDevelopmentAttachment>)PropertyUtils.getNestedProperty(form, collectionPath);
        attachments.get(Integer.parseInt(selectedLine)).setMultipartFile(null);

        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=updateEditableProposalFileAttachment")
    public ModelAndView updateEditableProposalFileAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String collectionPath = ProposalDevelopmentConstants.PropertyConstants.NARRATIVES;
        String selectedLine = form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex();

        ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).toggleAttachmentFile(form, ProposalDevelopmentConstants.PropertyConstants.NARRATIVES, form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());

        @SuppressWarnings("unchecked")
        List<ProposalDevelopmentAttachment> attachments = (List<ProposalDevelopmentAttachment>)PropertyUtils.getNestedProperty(form, collectionPath);
        attachments.get(Integer.parseInt(selectedLine)).setMultipartFile(null);

        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=addProposalAttachmentRights"})
    public ModelAndView addProposalAttachmentRights(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        int selectedLine = Integer.parseInt(form.getNarrativeUserRightsSelectedAttachment());

        boolean rulePassed = getKualiRuleService().applyRules(new NewNarrativeUserRightsEvent(form.getProposalDevelopmentDocument(), form.getNarrativeUserRights(), selectedLine));

        if (rulePassed) {
            form.getDevelopmentProposal().getNarrative(selectedLine).getNarrativeUserRights().clear();
            form.getDevelopmentProposal().getNarrative(selectedLine).getNarrativeUserRights().addAll(form.getNarrativeUserRights());
        }
        return  super.save(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=getProposalAttachmentRights"})
    public ModelAndView getProposalAttachmentRights(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                     @RequestParam("actionParameters[" + UifParameters.SELECTED_LINE_INDEX + "]") String selectedLine) throws Exception {
        form.getDevelopmentProposal().populateNarrativeRightsForLoggedinUser();
        form.getDevelopmentProposal().populatePersonNameForNarrativeUserRights(Integer.parseInt(selectedLine));
        Narrative selectedNarrative= form.getDevelopmentProposal().getNarratives().get(Integer.parseInt(selectedLine));

        List<NarrativeUserRights> editableRights = new ArrayList<>();
        for (NarrativeUserRights right : selectedNarrative.getNarrativeUserRights()) {
            NarrativeUserRights editableRight = new NarrativeUserRights();
            PropertyUtils.copyProperties(editableRight,right);
            editableRights.add(editableRight);
        }

        form.setNarrativeUserRights(editableRights);
        form.setNarrativeUserRightsSelectedAttachment(selectedLine);
        form.getActionParameters().put(ProposalDevelopmentConstants.KradConstants.ATTACHMENT_TYPE, ProposalDevelopmentConstants.KradConstants.PROPOSAL_ATTACHMENT);
        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENT_PAGE_VIEW_EDIT_RIGHT_DIALOG, true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=addInstituteAttachmentRights"})
    public ModelAndView addInstituteAttachmentRights(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        int selectedLine = Integer.parseInt(form.getNarrativeUserRightsSelectedAttachment());

        boolean rulePassed = getKualiRuleService().applyRules(new NewNarrativeUserRightsEvent(form.getProposalDevelopmentDocument(), form.getNarrativeUserRights(), selectedLine));

        if (rulePassed) {
            form.getDevelopmentProposal().getInstituteAttachment(selectedLine).getNarrativeUserRights().clear();
            form.getDevelopmentProposal().getInstituteAttachment(selectedLine).getNarrativeUserRights().addAll(form.getNarrativeUserRights());
        }
        return super.save(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=getInstituteAttachmentRights"})
    public ModelAndView getInstituteAttachmentRights(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                                    @RequestParam("actionParameters[" + UifParameters.SELECTED_LINE_INDEX + "]") String selectedLine) throws Exception {
        form.getDevelopmentProposal().populateNarrativeRightsForLoggedinUser();
        form.getDevelopmentProposal().populatePersonNameForInstituteAttachmentUserRights(Integer.parseInt(selectedLine));
        Narrative selectedNarrative= form.getDevelopmentProposal().getInstituteAttachment(Integer.parseInt(selectedLine));

        List<NarrativeUserRights> editableRights = new ArrayList<>();
        for (NarrativeUserRights right : selectedNarrative.getNarrativeUserRights()) {
            NarrativeUserRights editableRight = new NarrativeUserRights();
            PropertyUtils.copyProperties(editableRight,right);
            editableRights.add(editableRight);
        }

        form.setNarrativeUserRights(editableRights);
        form.setNarrativeUserRightsSelectedAttachment(selectedLine);
        form.getActionParameters().put(ProposalDevelopmentConstants.KradConstants.ATTACHMENT_TYPE, ProposalDevelopmentConstants.KradConstants.INSTITUTE_ATTACHMENT);
        return getModelAndViewService().showDialog(ProposalDevelopmentConstants.KradConstants.PROP_DEV_ATTACHMENT_PAGE_VIEW_EDIT_RIGHT_DIALOG, true, form);
    }

    public LegacyNarrativeService getLegacyNarrativeService() {
        return legacyNarrativeService;
    }

    public void setLegacyNarrativeService(LegacyNarrativeService legacyNarrativeService) {
        this.legacyNarrativeService = legacyNarrativeService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public FileControllerService getKcFileControllerService() {
        return kcFileControllerService;
    }

    public void setKcFileControllerService(FileControllerService kcFileControllerService) {
        this.kcFileControllerService = kcFileControllerService;
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }
}
