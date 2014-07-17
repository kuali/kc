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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
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

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=editAttachment")
    public ModelAndView editAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableAttachments().containsKey(selectedCollectionPath)) {
            form.getEditableAttachments().get(selectedCollectionPath).add(selectedLine);
        } else {
            List<String> newKeyList = new ArrayList<String>();
            newKeyList.add(selectedLine);
            form.getEditableAttachments().put(selectedCollectionPath,newKeyList);
        }

        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }


    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=cancelAttachment")
    public ModelAndView cancelAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableAttachments().containsKey(selectedCollectionPath)){
            form.getEditableAttachments().get(selectedCollectionPath).remove(selectedLine);
        }

        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAttachment")
    public ModelAndView saveAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableAttachments().containsKey(selectedCollectionPath)){
            form.getEditableAttachments().get(selectedCollectionPath).remove(selectedLine);
        }

        return getTransactionalDocumentControllerService().saveLine(form, result, request, response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addAttachment")
    public ModelAndView addAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);

        if(form.getEditableAttachments().containsKey(selectedCollectionPath)) {
            List<String> indexes = new ArrayList<String>();
            for (String index : form.getEditableAttachments().get(selectedCollectionPath)) {
                Integer newIndex= Integer.parseInt(index) + 1;
                indexes.add(newIndex.toString());
            }
            form.getEditableAttachments().get(selectedCollectionPath).clear();
            form.getEditableAttachments().get(selectedCollectionPath).addAll(indexes);
        }
        return getTransactionalDocumentControllerService().addLine(form, result, request, response);
    }
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addFileUploadLine")
    public ModelAndView addFileUploadLine(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                          MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
        final String selectedCollectionPath = request.getParameter("bindingPath");

        if(form.getEditableAttachments().containsKey(selectedCollectionPath)) {
            List<String> indexes = new ArrayList<String>();
            indexes.add("0");
            for (String index : form.getEditableAttachments().get(selectedCollectionPath)) {
                Integer newIndex= Integer.parseInt(index) + 1;
                indexes.add(newIndex.toString());
            }
            form.getEditableAttachments().get(selectedCollectionPath).clear();
            form.getEditableAttachments().get(selectedCollectionPath).addAll(indexes);
        } else {
            List<String> newKeyList = new ArrayList<String>();
            newKeyList.add("0");
            form.getEditableAttachments().put(selectedCollectionPath,newKeyList);
        }

        return getTransactionalDocumentControllerService().addFileUploadLine(form,result,request,response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=deleteFileUploadLine")
    public ModelAndView deleteFileUploadLine(@ModelAttribute("KualiForm") final UifFormBase uifForm,
                                             BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return getTransactionalDocumentControllerService().deleteFileUploadLine(uifForm,result,request,response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=getFileFromLine")
    public void getFileFromLine(@ModelAttribute("KualiForm") final UifFormBase uifForm, BindingResult result,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        getTransactionalDocumentControllerService().getFileFromLine(uifForm,result,request,response);
    }

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-AttachmentsPage"})
    public ModelAndView navigateToAttachment(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
        List<Narrative> filteredInstituteAttachments = new ArrayList<Narrative>();
        List<Narrative> filteredNarratives = new ArrayList<Narrative>();
        for (Narrative narrative : propDevForm.getDevelopmentProposal().getNarratives()){
            if(narrative.getNarrativeType().getNarrativeTypeGroup().equals("P")) {
                filteredNarratives.add(narrative);
            } else {
                filteredInstituteAttachments.add(narrative);
            }
        }
        propDevForm.getDevelopmentProposal().setInstituteAttachments(filteredInstituteAttachments);
        propDevForm.getDevelopmentProposal().setNarratives(filteredNarratives);
        return getTransactionalDocumentControllerService().navigate(form, result, request, response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=markAllComplete")
    public ModelAndView markAllComplete(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                        BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception{
        final String collectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(form, collectionPath);

        for (Object object : collection) {
            if(object instanceof Narrative) {
                ((Narrative) object).setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
                getDataObjectService().wrap(object).fetchRelationship("narrativeStatus");
            }
        }
        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
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

       return getTransactionalDocumentControllerService().refresh(form, result, request, response);
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

        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addNarrative")
    public ModelAndView addNarrative(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        final String propertyName = form.getActionParamaterValue("propertyName");
        Narrative narrative = form.getProposalDevelopmentAttachmentHelper().getNarrative();
        narrative.setNarrativeTypeCode(StringUtils.strip(narrative.getNarrativeTypeCode(),","));
        getLegacyNarrativeService().prepareNarrative(form.getProposalDevelopmentDocument(),narrative);
        try {
            narrative.init(narrative.getMultipartFile());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }

        if(propertyName.equals("instituteAttachments")) {
            narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
            form.getDevelopmentProposal().getInstituteAttachments().add(narrative);
        } else if (propertyName.equals("narratives")) {
            form.getDevelopmentProposal().getNarratives().add(narrative);
        }

        form.getProposalDevelopmentAttachmentHelper().reset();
        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addBiography")
    public ModelAndView addBiography(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception{
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        ProposalPersonBiography biography = form.getProposalDevelopmentAttachmentHelper().getBiography();
        biography.setDevelopmentProposal(document.getDevelopmentProposal());
        biography.setBiographyNumber(document
                .getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        biography.setUpdateUser(GlobalVariables.getUserSession().getPrincipalName());
        biography.setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
        getDataObjectService().wrap(biography).fetchRelationship("propPerDocType");
        try {
            biography.init(biography.getMultipartFile());
        } catch (Exception e) {
            LOG.info("No File Attached");
        }
        form.getDevelopmentProposal().getPropPersonBios().add(biography);
        form.getProposalDevelopmentAttachmentHelper().reset();
        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
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
        return getTransactionalDocumentControllerService().refresh(form,result,request,response);
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
}
