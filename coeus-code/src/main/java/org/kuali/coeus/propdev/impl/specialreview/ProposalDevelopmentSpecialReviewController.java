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
package org.kuali.coeus.propdev.impl.specialreview;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.coeus.common.framework.compliance.exemption.ExemptionType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProposalDevelopmentSpecialReviewController extends ProposalDevelopmentControllerBase {
    @Autowired
    @Qualifier("proposalDevelopmentSpecialReviewService")
    private ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=linkProtocol")
    public ModelAndView linkProtocol(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();
        for (ProposalSpecialReview specialReview : proposalDevelopmentDocument.getDevelopmentProposal().getPropSpecialReviews()) {
            if (!specialReview.isLinkedToProtocol()) {
                pdForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(specialReview);
            }
        }
        return getModelAndViewService().getModelAndView(form);
    }
    
    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createProtocol")
    public ModelAndView createProtocol(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        ProposalDevelopmentDocument document = pdForm.getProposalDevelopmentDocument();
        ProposalSpecialReview proposalSpecialReview = (ProposalSpecialReview) pdForm.getNewCollectionLines().get("document.developmentProposal.propSpecialReviews");
        getProposalDevelopmentSpecialReviewService().createProtocol(proposalSpecialReview, document);
        return getModelAndViewService().getModelAndView(form);
    }
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) throws Exception {
 	   binder.registerCustomEditor(List.class, "document.developmentProposal.propSpecialReviews.specialReviewExemptions", new PropSpecialReviewExemptionTypeEditor());
    }
    	  
    protected class PropSpecialReviewExemptionTypeEditor extends CustomCollectionEditor {
 		public PropSpecialReviewExemptionTypeEditor() {
 			super(List.class);
 		}

 		protected Object convertElement(Object element) {
 			if (element instanceof String) {
 				return new ProposalSpecialReviewExemption(null, getExemptionType(element));
 			}
 			return null;
 		}
 	}
    
    protected ExemptionType getExemptionType(Object element) {
 	   return (ExemptionType) getDataObjectService().findUnique(ExemptionType.class, QueryByCriteria.Builder.forAttribute("exemptionTypeCode", element).build());
    }

    public ProposalDevelopmentSpecialReviewService getProposalDevelopmentSpecialReviewService() {
 		return proposalDevelopmentSpecialReviewService;
 	}

 	public void setProposalDevelopmentSpecialReviewService(
 			ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService) {
 		this.proposalDevelopmentSpecialReviewService = proposalDevelopmentSpecialReviewService;
 	}

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
