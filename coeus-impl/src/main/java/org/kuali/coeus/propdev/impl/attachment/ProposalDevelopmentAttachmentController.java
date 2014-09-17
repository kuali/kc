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
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.FileControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=cancelAttachment")
    public ModelAndView cancelAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)){
            form.getEditableCollectionLines().get(selectedCollectionPath).remove(selectedLine);
        }

        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAttachment")
    public ModelAndView saveAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = getCollectionControllerService().saveLine(form);

        if (getGlobalVariableService().getMessageMap().hasErrors()){
            return getRefreshControllerService().refresh(form);
        }

        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)){
            form.getEditableCollectionLines().get(selectedCollectionPath).remove(selectedLine);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addAttachment")
    public ModelAndView addAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);

        addEditableCollectionLine(form, selectedCollectionPath);
        return getCollectionControllerService().addLine(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addFileUploadLine")
    public ModelAndView addFileUploadLine(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                          MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = request.getParameter("bindingPath");

        addEditableCollectionLine(form, selectedCollectionPath);
        return getFileControllerService().addFileUploadLine(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteFileUploadLine")
    public ModelAndView deleteFileUploadLine(@ModelAttribute("KualiForm") final UifFormBase uifForm,
                                             BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getFileControllerService().deleteFileUploadLine(uifForm);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=getFileFromLine")
    public void getFileFromLine(@ModelAttribute("KualiForm") final UifFormBase uifForm, BindingResult result,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        getKcFileControllerService().getFileFromLine(uifForm,response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=markAllComplete")
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

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareNarrative")
    public ModelAndView prepareNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                          HttpServletRequest request, HttpServletResponse response) throws Exception{
       String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
       form.getProposalDevelopmentAttachmentHelper().reset();

       if (StringUtils.isNotEmpty(selectedLine)) {
           Narrative tmpNarrative = null;
           tmpNarrative = form.getDevelopmentProposal().getNarrative(Integer.parseInt(selectedLine));
           form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
           form.getProposalDevelopmentAttachmentHelper().setNarrative(tmpNarrative);
       }

        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=prepareBiography")
    public ModelAndView prepareBiography(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception{
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
        form.getProposalDevelopmentAttachmentHelper().reset();

        if (StringUtils.isNotEmpty(selectedLine)) {
            ProposalPersonBiography tmpBiography = null;
            tmpBiography = form.getDevelopmentProposal().getPropPersonBio(Integer.parseInt(selectedLine));
            form.getProposalDevelopmentAttachmentHelper().setSelectedLineIndex(selectedLine);
            form.getProposalDevelopmentAttachmentHelper().setBiography(tmpBiography);
        }

        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addNarrative")
    public ModelAndView addNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getNarrative();
        initializeNarrative(narrative, form.getProposalDevelopmentDocument());
        form.getDevelopmentProposal().getNarratives().add(0,narrative);
        form.getProposalDevelopmentAttachmentHelper().reset();

        String collectionPath = ProposalDevelopmentConstants.PropertyConstants.NARRATIVES;
        if (!((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).validateNarrativeRequiredFields(narrative, collectionPath + "[0]",false)){
            addEditableCollectionLine(form, collectionPath);
        }
        else if(form.getEditableCollectionLines().containsKey(collectionPath)
                && form.getEditableCollectionLines().get(collectionPath).size() > 0) {
            updateEditableCollectionLines(form, collectionPath);
        }

        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addInstituteAttachment")
    public ModelAndView addInstituteAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getInstituteAttachment();
        initializeNarrative(narrative,form.getProposalDevelopmentDocument());
        narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
        form.getDevelopmentProposal().getInstituteAttachments().add(0,narrative);
        form.getProposalDevelopmentAttachmentHelper().reset();

        String collectionPath = ProposalDevelopmentConstants.PropertyConstants.INSTITUTE_ATTACHMENTS;
        if (!((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).validateNarrativeRequiredFields(narrative, collectionPath + "[0]",false)){
            addEditableCollectionLine(form, collectionPath);
        }
        else if(form.getEditableCollectionLines().containsKey(collectionPath)
                && form.getEditableCollectionLines().get(collectionPath).size() > 0) {
            updateEditableCollectionLines(form, collectionPath);
        }
        return getRefreshControllerService().refresh(form);
    }

    protected void initializeNarrative(Narrative narrative, ProposalDevelopmentDocument document) {
        getLegacyNarrativeService().prepareNarrative(document,narrative);
        try {
            narrative.init(narrative.getMultipartFile());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addBiography")
    public ModelAndView addBiography(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception{
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
        } catch (Exception e) {
            LOG.info("No File Attached");
        }
        form.getDevelopmentProposal().getPropPersonBios().add(0,biography);
        form.getProposalDevelopmentAttachmentHelper().reset();

        String collectionPath = ProposalDevelopmentConstants.PropertyConstants.PERSONNEL_BIOS;
        if (!((ProposalDevelopmentViewHelperServiceImpl)form.getViewHelperService()).validateProposalPersonBiographyRequiredFields(biography, collectionPath + "[0]",false)){
            addEditableCollectionLine(form, collectionPath);
        }
        else if(form.getEditableCollectionLines().containsKey(collectionPath)
                && form.getEditableCollectionLines().get(collectionPath).size() > 0) {
            updateEditableCollectionLines(form, collectionPath);
        }

        return getRefreshControllerService().refresh(form);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveNarrative")
    public ModelAndView saveNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getNarrative();
        int selectedLineIndex = Integer.parseInt(form.getProposalDevelopmentAttachmentHelper().getSelectedLineIndex());
        narrative.refreshReferenceObject("narrativeType");
        narrative.refreshReferenceObject("narrativeStatus");
        try {
            narrative.init(narrative.getMultipartFile());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }
        form.getDevelopmentProposal().getNarratives().set(selectedLineIndex,narrative);
        form.getProposalDevelopmentAttachmentHelper().reset();
        return getRefreshControllerService().refresh(form);
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

}
