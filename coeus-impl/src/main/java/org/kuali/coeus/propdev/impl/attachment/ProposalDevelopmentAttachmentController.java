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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.propdev.impl.person.attachment.AddPersonnelAttachmentEvent;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.FileControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class ProposalDevelopmentAttachmentController extends ProposalDevelopmentControllerBase {

    private static final Logger LOG = Logger.getLogger(ProposalDevelopmentAttachmentController.class);

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
    @Qualifier("personService")
    private PersonService personService;

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=save", "pageId=PropDev-AttachmentsPage"})
    public ModelAndView saveAttachments(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
       return super.narrativePageSave(form,form.isCanEditView());
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addFileUploadLine")
    public ModelAndView addFileUploadLine(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                          MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = request.getParameter("bindingPath");

        addEditableCollectionLine(form, selectedCollectionPath);

        synchronized (ObjectPropertyUtils.getPropertyValue(form, selectedCollectionPath)) {
            return getFileControllerService().addFileUploadLine(form);
        }
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteFileUploadLine")
    public ModelAndView deleteFileUploadLine(@ModelAttribute("KualiForm") final UifFormBase uifForm,
                                             BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getFileControllerService().deleteFileUploadLine(uifForm);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=getFileFromLine")
    public void getFileFromLine(@ModelAttribute("KualiForm") final UifFormBase uifForm, BindingResult result,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        getKcFileControllerService().getFileFromLine(uifForm,response);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=markAllComplete")
    public ModelAndView markAllComplete(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                        BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception{
        final String collectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(form, collectionPath);

        for (Object object : collection) {
            if(object instanceof Narrative) {
                ((Narrative) object).setModuleStatusCode(form.getProposalDevelopmentAttachmentHelper().getMarkAllStatus());
                getDataObjectService().wrap(object).fetchRelationship("narrativeStatus");
            }
        }
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

        return getModelAndViewService().showDialog("PropDev-AttachmentsPage-ProposalDetails",true,form);
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

        return getModelAndViewService().showDialog("PropDev-AttachmentsPage-PersonnelDetails", true, form);
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

        return getModelAndViewService().showDialog("PropDev-AttachmentsPage-AbstractDetails", true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareInstituteAttachment")
    public ModelAndView prepareInstituteAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        form.getProposalDevelopmentAttachmentHelper().reset();

        if (StringUtils.isNotEmpty(selectedLine)) {
            Narrative tmpNarrative = new Narrative();
            form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
            PropertyUtils.copyProperties(tmpNarrative,form.getDevelopmentProposal().getInstituteAttachment(Integer.parseInt(selectedLine)));
            form.getProposalDevelopmentAttachmentHelper().setInstituteAttachment(tmpNarrative);
        }

        return getModelAndViewService().showDialog("PropDev-AttachmentsPage-InternalDetails",true,form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareNote")
    public ModelAndView prepareNote(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        form.getProposalDevelopmentAttachmentHelper().reset();

        if (StringUtils.isNotEmpty(selectedLine)) {
            Note tmpNote = new Note();
            form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
            PropertyUtils.copyProperties(tmpNote,form.getProposalDevelopmentDocument().getNote(Integer.parseInt(selectedLine)));
            form.getProposalDevelopmentAttachmentHelper().setNote(tmpNote);
        }

        return getModelAndViewService().showDialog("PropDev-AttachmentsPage-NoteDetails", true, form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addNarrative")
    public ModelAndView addNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getNarrative();
        initializeNarrative(narrative, form);
        if ( getKualiRuleService().applyRules(new AddNarrativeEvent("proposalDevelopmentAttachmentHelper.narrative",form.getProposalDevelopmentDocument(),form.getProposalDevelopmentAttachmentHelper().getNarrative()))) {
            form.getDevelopmentProposal().getNarratives().add(0,narrative);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            form.setUpdateComponentId("PropDev-AttachmentsPage-ProposalDetails");
            form.setAjaxReturnType("update-component");
        }
        return getRefreshControllerService().refresh(form);

    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addInstituteAttachment")
    public ModelAndView addInstituteAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getInstituteAttachment();
        initializeNarrative(narrative,form);
        narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        form.getDevelopmentProposal().getInstituteAttachments().add(0,narrative);
        form.getProposalDevelopmentAttachmentHelper().reset();

        return getRefreshControllerService().refresh(form);
    }

    protected void initializeNarrative(Narrative narrative, ProposalDevelopmentDocumentForm form) {
        getLegacyNarrativeService().prepareNarrative(form.getProposalDevelopmentDocument(),narrative);
        try {
            narrative.init(narrative.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).updateAttachmentInformation(narrative.getNarrativeAttachment());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }
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
        getDataObjectService().wrap(biography).fetchRelationship("propPerDocType");
        try {
            biography.init(biography.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).updateAttachmentInformation(biography.getPersonnelAttachment());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }

        if (getKualiRuleService().applyRules(new AddPersonnelAttachmentEvent("proposalDevelopmentAttachmentHelper.biography",document,biography))){
            form.getDevelopmentProposal().getPropPersonBios().add(0,biography);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            form.setUpdateComponentId("PropDev-AttachmentsPage-PersonnelDetails");
            form.setAjaxReturnType("update-component");
        }
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveNarrative")
    public ModelAndView saveNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getNarrative();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
        narrative.refreshReferenceObject("narrativeType");
        narrative.refreshReferenceObject("narrativeStatus");
        try {
            narrative.init(narrative.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).updateAttachmentInformation(narrative.getNarrativeAttachment());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }

        if ( getKualiRuleService().applyRules(new AddNarrativeEvent("proposalDevelopmentAttachmentHelper.narrative",form.getProposalDevelopmentDocument(),form.getProposalDevelopmentAttachmentHelper().getNarrative()))) {
            form.getDevelopmentProposal().getNarratives().set(selectedLineIndex,narrative);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            form.setUpdateComponentId("PropDev-AttachmentsPage-ProposalDetails");
            form.setAjaxReturnType("update-component");
        }
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveBiography")
    public ModelAndView saveBiography(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        ProposalPersonBiography biography = form.getProposalDevelopmentAttachmentHelper().getBiography();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
        biography.setUpdateUser(globalVariableService.getUserSession().getPrincipalName());
        biography.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        getDataObjectService().wrap(biography).fetchRelationship("propPerDocType");
        try {
            biography.init(biography.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).updateAttachmentInformation(biography.getPersonnelAttachment());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }
        if (getKualiRuleService().applyRules(new AddPersonnelAttachmentEvent("proposalDevelopmentAttachmentHelper.biography",form.getProposalDevelopmentDocument(),biography))){
            form.getDevelopmentProposal().getPropPersonBios().set(selectedLineIndex, biography);
            form.getProposalDevelopmentAttachmentHelper().reset();
        } else {
            form.setUpdateComponentId("PropDev-AttachmentsPage-PersonnelDetails");
            form.setAjaxReturnType("update-component");
        }
        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveInstituteAttachment")
    public ModelAndView saveInstituteAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getInstituteAttachment();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
        getDataObjectService().wrap(narrative).fetchRelationship("narrativeType");
        try {
            narrative.init(narrative.getMultipartFile());
            ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).updateAttachmentInformation(narrative.getNarrativeAttachment());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }
        form.getDevelopmentProposal().getInstituteAttachments().set(selectedLineIndex,narrative);
        form.getProposalDevelopmentAttachmentHelper().reset();

        return getRefreshControllerService().refresh(form);
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

        form.getProposalDevelopmentDocument().getNotes().set(selectedLineIndex, note);
        form.getProposalDevelopmentAttachmentHelper().reset();

        return getRefreshControllerService().refresh(form);
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=updateEditableFileAttachment")
    public ModelAndView updateEditableFileAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{

        String collectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).toggleAttachmentFile(form, collectionPath, selectedLine);
        List<ProposalDevelopmentAttachment> attachments = (List<ProposalDevelopmentAttachment>)PropertyUtils.getNestedProperty(form, collectionPath);
        attachments.get(Integer.parseInt(selectedLine)).setMultipartFile(null);

        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=updateEditableProposalFileAttachment")
    public ModelAndView updateEditableProposalFileAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String collectionPath = ProposalDevelopmentConstants.PropertyConstants.NARRATIVES;
        String selectedLine = form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex();

        ((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).toggleAttachmentFile(form, ProposalDevelopmentConstants.PropertyConstants.NARRATIVES, form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
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

        List<NarrativeUserRights> editableRights = new ArrayList<NarrativeUserRights>();
        for (NarrativeUserRights right : selectedNarrative.getNarrativeUserRights()) {
            NarrativeUserRights editableRight = new NarrativeUserRights();
            PropertyUtils.copyProperties(editableRight,right);
            editableRights.add(editableRight);
        }

        form.setNarrativeUserRights(editableRights);
        form.setNarrativeUserRightsSelectedAttachment(selectedLine);
        form.getActionParameters().put("attachmentType","proposalAttachment");
        return getModelAndViewService().showDialog("PropDev-AttachmentPage-ViewEditRightDialog", true, form);
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

        List<NarrativeUserRights> editableRights = new ArrayList<NarrativeUserRights>();
        for (NarrativeUserRights right : selectedNarrative.getNarrativeUserRights()) {
            NarrativeUserRights editableRight = new NarrativeUserRights();
            PropertyUtils.copyProperties(editableRight,right);
            editableRights.add(editableRight);
        }

        form.setNarrativeUserRights(editableRights);
        form.setNarrativeUserRightsSelectedAttachment(selectedLine);
        form.getActionParameters().put("attachmentType","instituteAttachment");
        return getModelAndViewService().showDialog("PropDev-AttachmentPage-ViewEditRightDialog", true, form);
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

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
