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
package org.kuali.kra.proposaldevelopment.web.krad;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.common.specialreview.rule.event.SaveDocumentSpecialReviewEvent;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalDevelopmentService;
import org.kuali.kra.proposaldevelopment.service.ProposalRoleTemplateService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.web.krad.TransactionalDocumentControllerService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.exception.AuthorizationException;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEventBase;
import org.kuali.rice.krad.service.AttachmentService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.UifConstants.WorkflowAction;
import org.kuali.rice.krad.uif.field.AttributeQueryResult;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.controller.TransactionalDocumentControllerBase;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public abstract class ProposalDevelopmentControllerBase {

    private TransactionalDocumentControllerService transactionalDocumentControllerService;
    private DocumentService documentService;
    
    protected String PROPDEV_DEFAULT_VIEW_ID = "PropDev-DefaultView";
    
    private KraAuthorizationService kraAuthorizationService;
    private ProposalDevelopmentService proposalDevelopmentService;
    private AttachmentService attachmentService;
    private LegacyDataAdapter legacyDataAdapter;
    
    protected DocumentFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalDevelopmentDocumentForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) {
        return getTransactionalDocumentControllerService().initForm(this.createInitialForm(request), request, response);
    }
    
    /**
     * Create the original set of Proposal Users for a new Proposal Development Document.
     * The creator the proposal is assigned to the AGGREGATOR role.
     */
     protected void initializeProposalUsers(ProposalDevelopmentDocument doc) {
         
         // Assign the creator of the proposal to the AGGREGATOR role.
         
         String userId = GlobalVariables.getUserSession().getPrincipalId();
         if (!kraAuthorizationService.hasRole(userId, doc, RoleConstants.AGGREGATOR))
             kraAuthorizationService.addRole(userId, RoleConstants.AGGREGATOR, doc);
         
         // Add the users defined in the role templates for the proposal's lead unit
         
         ProposalRoleTemplateService proposalRoleTemplateService = KraServiceLocator.getService(ProposalRoleTemplateService.class);
         proposalRoleTemplateService.addUsers(doc);
     }
     
     protected void initialSave(ProposalDevelopmentDocument proposalDevelopmentDocument) {
         proposalDevelopmentDocument.getDocumentHeader().setDocumentDescription("Testing");
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
     }
     
     public ModelAndView save(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
             HttpServletRequest request, HttpServletResponse response) throws Exception {
         ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
         ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
         getTransactionalDocumentControllerService().performWorkflowAction(form, WorkflowAction.SAVE, true);
         
         initializeProposalUsers(proposalDevelopmentDocument);
         
         String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
         ModelAndView view = null;
         if (StringUtils.isNotBlank(pageId)) {
             view = getTransactionalDocumentControllerService().getUIFModelAndView(form, pageId);
         } else {
             view = getTransactionalDocumentControllerService().getUIFModelAndView(form);
         }
         return view;
     }

     public ModelAndView save(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
             HttpServletRequest request, HttpServletResponse response, Class<? extends KualiDocumentEventBase> eventClazz) throws Exception {
         ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
         ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
         ModelAndView view = null;
         if (eventClazz == null) {
             getTransactionalDocumentControllerService().performWorkflowAction(form, WorkflowAction.SAVE, true);
         } else {
             performCustomSave(proposalDevelopmentDocument, SaveDocumentSpecialReviewEvent.class);
         }
         String pageId = form.getActionParamaterValue(UifParameters.NAVIGATE_TO_PAGE_ID);
         if (StringUtils.isNotBlank(pageId)) {
             view = getTransactionalDocumentControllerService().getUIFModelAndView(form, pageId);
         } else {
             view = getTransactionalDocumentControllerService().getUIFModelAndView(form);
         }
         initializeProposalUsers(proposalDevelopmentDocument);
         return view;
     }
     
     protected void performCustomSave(DocumentBase document, Class<? extends KualiDocumentEventBase> eventClazz) {
         try {
             getDocumentService().saveDocument(document, eventClazz);
             GlobalVariables.getMessageMap().putInfo(KRADConstants.GLOBAL_MESSAGES, RiceKeyConstants.MESSAGE_SAVED);
         } catch (ValidationException e) {
             // if errors in map, swallow exception so screen will draw with errors
             // if not then throw runtime because something bad happened
             if (GlobalVariables.getMessageMap().hasNoErrors()) {
                 throw new RiceRuntimeException("Validation Exception with no error message.", e);
             }
         } catch (Exception e) {
             throw new RiceRuntimeException(
                     "Exception trying to save document: " + document
                             .getDocumentNumber(), e);
         }
     }

    protected KraAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    @Autowired
    @Qualifier("kraAuthorizationService")
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    protected ProposalDevelopmentService getProposalDevelopmentService() {
        return proposalDevelopmentService;
    }

    @Autowired
    @Qualifier("proposalDevelopmentService")
    public void setProposalDevelopmentService(ProposalDevelopmentService proposalDevelopmentService) {
        this.proposalDevelopmentService = proposalDevelopmentService;
    }

    protected AttachmentService getAttachmentService() {
        return attachmentService;
    }

    @Autowired
    @Qualifier("attachmentService")
    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    protected TransactionalDocumentControllerService getTransactionalDocumentControllerService() {
        return transactionalDocumentControllerService;
    }

    @Autowired
    @Qualifier("transactionalDocumentControllerService")
    public void setTransactionalDocumentControllerService(TransactionalDocumentControllerService transactionalDocumentControllerService) {
        this.transactionalDocumentControllerService = transactionalDocumentControllerService;
    }
    
    protected DocumentService getDocumentService() {
        return documentService;
    }

    @Autowired
    @Qualifier("documentService")
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    @Autowired
    @Qualifier("legacyDataAdapter")
    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }    
    
}
