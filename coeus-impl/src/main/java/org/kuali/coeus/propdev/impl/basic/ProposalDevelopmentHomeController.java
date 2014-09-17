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
package org.kuali.coeus.propdev.impl.basic;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.compliance.core.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyService;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.doctype.DocumentType;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.PessimisticLockService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentHomeController extends ProposalDevelopmentControllerBase {

   @Autowired
   @Qualifier("pessimisticLockService")
   private PessimisticLockService pessimisticLockService;

   @Autowired
   @Qualifier("dataObjectService")
   private DataObjectService dataObjectService;

   @Autowired
   @Qualifier("documentService")
   private DocumentService documentService;

   @Autowired
   @Qualifier("proposalCopyService")
   private ProposalCopyService proposalCopyService;

    @Autowired
    @Qualifier("documentTypeService")
    private DocumentTypeService documentTypeService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

   @MethodAccessible
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createProposal")
   public ModelAndView createProposal(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {

       ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
       initialSave(proposalDevelopmentDocument);
       save(form, result, request, response);
       initializeProposalUsers(form.getProposalDevelopmentDocument());
       //setting to null so the previous page id(PropDev-InitiatePage) doesn't override the default
       form.setPageId(null);
       return getModelAndViewService().getModelAndViewWithInit(form, PROPDEV_DEFAULT_VIEW_ID);
   }

   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=copy")
   public ModelAndView copy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {

       ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
       ProposalCopyService proposalCopyService = getProposalCopyService();
       getPessimisticLockService().releaseAllLocksForUser(proposalDevelopmentDocument.getPessimisticLocks(), getGlobalVariableService().getUserSession().getPerson());
       ProposalCopyCriteria proposalCopyCriteria = form.getProposalCopyCriteria();
       ProposalDevelopmentDocument newDoc = proposalCopyService.copyProposal(proposalDevelopmentDocument, proposalCopyCriteria);

       return returnToDocument(form, newDoc.getDocumentNumber());
   }

    protected  ModelAndView returnToDocument(ProposalDevelopmentDocumentForm form, String newDocNum) {
        DocumentType docType = getDocumentTypeService().getDocumentTypeByName(form.getDocTypeName());
        String docHandlerUrl = docType.getResolvedDocumentHandlerUrl();
        Properties props = new Properties();
        props.put(KewApiConstants.COMMAND_PARAMETER, KewApiConstants.DOCSEARCH_COMMAND);
        props.put(KewApiConstants.DOCUMENT_ID_PARAMETER, newDocNum);
        if (StringUtils.isNotBlank(form.getReturnFormKey())) {
            props.put(UifParameters.FORM_KEY, form.getReturnFormKey());
        }

        return getModelAndViewService().performRedirect(form, docHandlerUrl, props);
    }

   @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=save")
   public ModelAndView save(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
       return super.save(form);
   }
   
   @MethodAccessible
   @RequestMapping(value ="/proposalDevelopment", params = "methodToCall=navigate")
   public ModelAndView navigate(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
		   HttpServletRequest request, HttpServletResponse response) throws Exception {
	   return super.navigate(form, result, request, response);
   }
		   
   
   @MethodAccessible
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=getSponsor")
   public @ResponseBody Sponsor sponsorByCode(@RequestParam("sponsorCode") String sponsorCode) {
       Sponsor sponsor = getLegacyDataAdapter().findBySinglePrimaryKey(Sponsor.class, sponsorCode);
       //clear references that are likely circular
       if (sponsor != null) {
           sponsor.setUnit(null);
           sponsor.setRolodex(null);
       }
       return sponsor;
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveDetails")
   public ModelAndView saveDetails(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       return super.save(form, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveOpportunity")
   public ModelAndView saveOpportunity(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       return super.save(form, result, request, response);
   }      
   
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveCompliance")
   public ModelAndView saveComplaince(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        return super.save(pdForm, result, request, response, SaveDocumentSpecialReviewEvent.class);
   }   
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveProposalAttachments")
   public ModelAndView saveProposalAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveInternalAttachments")
   public ModelAndView saveInternalAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }   
   
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=savePersonnelAttachments")
   public ModelAndView savePersonnelAttachments(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAbstracts")
   public ModelAndView saveAbstracts(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveNotes")
   public ModelAndView saveNotes(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
       return super.save(pdForm, result, request, response);
   }

   @MethodAccessible
   @RequestMapping(value = "/proposalDevelopment", params="methodToCall=docHandler")
   public ModelAndView docHandler(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result, HttpServletRequest request,
           HttpServletResponse response) throws Exception {
       ModelAndView modelAndView = getTransactionalDocumentControllerService().docHandler(form);
       ProposalDevelopmentDocumentForm propDevForm = (ProposalDevelopmentDocumentForm) form;
       propDevForm.initialize();
       propDevForm.getCustomDataHelper().prepareCustomData();
       return modelAndView;
   }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=editCollectionLine")
    public ModelAndView editCollectionLine(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        final String selectedCollectionPath = form.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_PATH);
        String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);

        if(form.getEditableCollectionLines().containsKey(selectedCollectionPath)) {
            form.getEditableCollectionLines().get(selectedCollectionPath).add(selectedLine);
        } else {
            List<String> newKeyList = new ArrayList<String>();
            newKeyList.add(selectedLine);
            form.getEditableCollectionLines().put(selectedCollectionPath,newKeyList);
        }

        return getRefreshControllerService().refresh(form);
    }
   
    public PessimisticLockService getPessimisticLockService() {
        return pessimisticLockService;
    }

    public void setPessimisticLockService(PessimisticLockService pessimisticLockService) {
        this.pessimisticLockService = pessimisticLockService;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void setProposalCopyService(ProposalCopyService proposalCopyService) {
        this.proposalCopyService = proposalCopyService;
    }

    public ProposalCopyService getProposalCopyService() {
        return proposalCopyService;
    }

    public DocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public void setDocumentTypeService(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

}
