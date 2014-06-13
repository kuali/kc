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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.specialreview.impl.rule.event.SaveDocumentSpecialReviewEvent;
import org.kuali.coeus.propdev.impl.custom.ProposalDevelopmentCustomDataHelper;
import org.kuali.coeus.propdev.impl.docperm.ProposalRoleTemplateService;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.TransactionalDocumentControllerService;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.DocumentBase;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.rules.rule.event.DocumentEventBase;
import org.kuali.rice.krad.service.AttachmentService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.LegacyDataAdapter;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ProposalDevelopmentControllerBase {

    protected static final String PROPDEV_DEFAULT_VIEW_ID = "PropDev-DefaultView";

    @Autowired
    @Qualifier("transactionalDocumentControllerService")
    private TransactionalDocumentControllerService transactionalDocumentControllerService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kraAuthorizationService;

    @Autowired
    @Qualifier("proposalDevelopmentService")
    private ProposalDevelopmentService proposalDevelopmentService;

    @Autowired
    @Qualifier("attachmentService")
    private AttachmentService attachmentService;

    @Autowired
    @Qualifier("legacyDataAdapter")
    private LegacyDataAdapter legacyDataAdapter;
    
    @Autowired
    @Qualifier("proposalRoleTemplateService")
    private ProposalRoleTemplateService proposalRoleTemplateService;

    @Autowired
    @Qualifier("lookupService")
    private LookupService lookupService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    protected DocumentFormBase createInitialForm(HttpServletRequest request) {
        return new ProposalDevelopmentDocumentForm();
    }
    
    @ModelAttribute(value = "KualiForm")
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        UifFormBase form =  getTransactionalDocumentControllerService().initForm(this.createInitialForm(request), request, response);
        return form;
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
         proposalRoleTemplateService.addUsers(doc);
     }
     
     protected void initialSave(ProposalDevelopmentDocument proposalDevelopmentDocument) {
         proposalDevelopmentDocument.getDocumentHeader().setDocumentDescription("Testing");
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
     }
     
     public ModelAndView save(DocumentFormBase form, BindingResult result,
             HttpServletRequest request, HttpServletResponse response) throws Exception {
         ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
         ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();

         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
         getTransactionalDocumentControllerService().save(form, result, request, response);
         
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
             HttpServletRequest request, HttpServletResponse response, Class<? extends DocumentEventBase> eventClazz) throws Exception {
         ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
         ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
         proposalDevelopmentService.initializeUnitOrganizationLocation(
                 proposalDevelopmentDocument);
         proposalDevelopmentService.initializeProposalSiteNumbers(
                 proposalDevelopmentDocument);
         ModelAndView view = null;
         if (eventClazz == null) {
             getTransactionalDocumentControllerService().save(form, result, request, response);
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
     
     protected void performCustomSave(DocumentBase document, Class<? extends DocumentEventBase> eventClazz) {
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
     
     protected ModelAndView navigate(ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
    	 return getTransactionalDocumentControllerService().navigate(form, result, request, response);
     }

    protected KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    protected ProposalDevelopmentService getProposalDevelopmentService() {
        return proposalDevelopmentService;
    }

    public void setProposalDevelopmentService(ProposalDevelopmentService proposalDevelopmentService) {
        this.proposalDevelopmentService = proposalDevelopmentService;
    }

    protected AttachmentService getAttachmentService() {
        return attachmentService;
    }

    public void setAttachmentService(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    protected TransactionalDocumentControllerService getTransactionalDocumentControllerService() {
        return transactionalDocumentControllerService;
    }

    public void setTransactionalDocumentControllerService(TransactionalDocumentControllerService transactionalDocumentControllerService) {
        this.transactionalDocumentControllerService = transactionalDocumentControllerService;
    }
    
    protected DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    protected LegacyDataAdapter getLegacyDataAdapter() {
        return legacyDataAdapter;
    }

    public void setLegacyDataAdapter(LegacyDataAdapter legacyDataAdapter) {
        this.legacyDataAdapter = legacyDataAdapter;
    }

	protected ProposalRoleTemplateService getProposalRoleTemplateService() {
		return proposalRoleTemplateService;
	}

	public void setProposalRoleTemplateService(
			ProposalRoleTemplateService proposalRoleTemplateService) {
		this.proposalRoleTemplateService = proposalRoleTemplateService;
	}    
    
	protected LookupService getLookupService() {
		return lookupService;
	}

	public void setLookupService(LookupService lookupService) {
		this.lookupService = lookupService;
	}

	protected DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
	
	public void saveAnswerHeaders(ProposalDevelopmentDocumentForm pdForm) {
		for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
			if (person.getQuestionnaireHelper() != null && person.getQuestionnaireHelper().getAnswerHeaders() != null 
					&& !person.getQuestionnaireHelper().getAnswerHeaders().isEmpty()) {
				for (AnswerHeader answerHeader : person.getQuestionnaireHelper().getAnswerHeaders()) {
					getLegacyDataAdapter().save(answerHeader);
		        }
			}
	    }
	}
}
