/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.propdev.impl.specialreview;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.compliance.core.AddSpecialReviewEvent;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentConstants;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.kra.iacuc.IacucProtocolFinderDao;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolFinderDao;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.web.bind.UifBeanPropertyBindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ProposalDevelopmentSpecialReviewController extends ProposalDevelopmentControllerBase {

    private static String NEW_SPECIAL_REVIEW_PATH = "newCollectionLines['document.developmentProposal.propSpecialReviews']";
    @Autowired
    @Qualifier("proposalDevelopmentSpecialReviewService")
    private ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService;

    @Autowired
    @Qualifier("protocolFinderDao")
    private ProtocolFinderDao protocolFinderDao;

    @Autowired
    @Qualifier("iacucProtocolFinderDao")
    private IacucProtocolFinderDao iacucProtocolFinderDao;

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;
    
    @ResponseBody
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=clearAddCompliance")
    public void clearAddCompliance(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm pdForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalSpecialReview proposalSpecialReview = ((ProposalSpecialReview)pdForm.getNewCollectionLines().get("document.developmentProposal.propSpecialReviews"));
        proposalSpecialReview.setSpecialReviewTypeCode(null);
        proposalSpecialReview.setSpecialReviewType(null);
        proposalSpecialReview.setApprovalTypeCode(null);
        proposalSpecialReview.setApprovalType(null);
        proposalSpecialReview.setProtocolNumber(null);
    }
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=refreshAddCompliance")
    public ModelAndView refreshAddCompliance(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm pdForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalSpecialReview proposalSpecialReview = ((ProposalSpecialReview)pdForm.getNewCollectionLines().get("document.developmentProposal.propSpecialReviews"));
        String protocolNumber = request.getParameter("newCollectionLines['document.developmentProposal.propSpecialReviews'].protocolNumber");

        UifBeanPropertyBindingResult propertyResult = (UifBeanPropertyBindingResult) result;

        handleTypeChange(propertyResult.getModifiedPaths(), proposalSpecialReview);
        determineProtocolStatus(protocolNumber, proposalSpecialReview);

        return getRefreshControllerService().refresh(pdForm);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=refreshComplianceEntry")
    public ModelAndView refreshComplianceEntry(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm pdForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String updateComponentId = request.getParameter("updateComponentId");
        String suffix = updateComponentId.substring(updateComponentId.indexOf(UifConstants.IdSuffixes.LINE));
        int index = Integer.valueOf(suffix.replace(UifConstants.IdSuffixes.LINE, ""));

        ProposalSpecialReview proposalSpecialReview = pdForm.getDevelopmentProposal().getPropSpecialReviews().get(index);

        UifBeanPropertyBindingResult propertyResult = (UifBeanPropertyBindingResult) result;

        handleTypeChange(propertyResult.getModifiedPaths(), proposalSpecialReview);
        determineProtocolStatus(proposalSpecialReview.getProtocolNumber(), proposalSpecialReview);

        return getRefreshControllerService().refresh(pdForm);
    }

    /**
     * When the specialReviewTypeCode is changed, clear out old data that no longer applies to the entry.
     *
     * @param modifiedPaths the modified fields
     * @param specialReview the ProposalSpecialReview to clear
     */
    protected void handleTypeChange(Set<String> modifiedPaths, ProposalSpecialReview specialReview) {
        for (String path: modifiedPaths) {
            if (path.endsWith("specialReviewTypeCode")) {
                specialReview.setApprovalTypeCode(null);
                specialReview.setApprovalType(null);
                specialReview.setProtocolNumber(null);
                specialReview.setProtocolStatus(null);
                specialReview.setExpirationDate(null);
                specialReview.setApprovalDate(null);
                specialReview.setApplicationDate(null);
                specialReview.setComments(null);
                specialReview.setExemptionTypeCodes(new ArrayList<String>());
                dataObjectService.wrap(specialReview).materializeReferencedObjects();
            }
        }
    }

    /**
     * Set the protocolStatus property based on the selected protocol's status.
     *
     * @param protocolNumber the selected protocol number
     * @param proposalSpecialReview the ProposalSpecialReview that is linked to the protocol by protocolNumber
     */
    protected void determineProtocolStatus(String protocolNumber, ProposalSpecialReview proposalSpecialReview) {
        ProtocolBase protocol = null;

        if (StringUtils.isNotBlank(protocolNumber) && proposalSpecialReview.getSpecialReviewTypeCode() != null &&
                (proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.HUMAN_SUBJECTS) &&
                getProposalDevelopmentSpecialReviewService().isIrbLinkingEnabled())) {
            protocol = getProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
        }
        else if (StringUtils.isNotBlank(protocolNumber) && proposalSpecialReview.getSpecialReviewTypeCode() != null &&
                proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.ANIMAL_USAGE) &&
                getProposalDevelopmentSpecialReviewService().isIacucLinkingEnabled()) {
            protocol = getIacucProtocolFinderDao().findCurrentProtocolByNumber(protocolNumber);
        }

        if (protocol != null && protocol.getProtocolStatus() != null) {
            String status = protocol.getProtocolStatus().getDescription();
            proposalSpecialReview.setApprovalTypeCode(protocol.getProtocolStatusCode());
            proposalSpecialReview.setProtocolStatus(status);
            proposalSpecialReview.setExpirationDate(protocol.getExpirationDate());
            proposalSpecialReview.setApprovalDate(protocol.getApprovalDate());
            proposalSpecialReview.setApplicationDate(protocol.getApplicationDate());
        }
        else {
            proposalSpecialReview.setProtocolStatus(null);
        }


    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addComplianceEntry")
    public ModelAndView addComplianceEntry(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm pdForm) throws Exception {
        ProposalSpecialReview proposalSpecialReview = ((ProposalSpecialReview)pdForm.getNewCollectionLines().get("document.developmentProposal.propSpecialReviews"));
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument) pdForm.getDocument();

        if (!getKualiRuleService().applyRules(new AddSpecialReviewEvent<ProposalSpecialReview>(pdForm.getProposalDevelopmentDocument(),
                proposalSpecialReview,pdForm.getDevelopmentProposal().getPropSpecialReviews(),
                protocolNeedsToBeLinked(proposalSpecialReview.getSpecialReviewTypeCode()),
                NEW_SPECIAL_REVIEW_PATH))) {
            pdForm.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATECOMPONENT.getKey());
            pdForm.setUpdateComponentId(ProposalDevelopmentConstants.KradConstants.COMPLIANCE_ADD_DIALOG);
            return getModelAndViewService().getModelAndView(pdForm);
        }

        if (proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.HUMAN_SUBJECTS) ||
                proposalSpecialReview.getSpecialReviewTypeCode().equals(SpecialReviewType.ANIMAL_USAGE)) {

            proposalSpecialReview.setDevelopmentProposal(proposalDevelopmentDocument.getDevelopmentProposal());
            pdForm.getSpecialReviewHelper().prepareProtocolLinkViewFields(proposalSpecialReview);

            // Invalid protrocol trying to be linked so blank out protocol info
            if (protocolNeedsToBeLinked(proposalSpecialReview.getSpecialReviewTypeCode()) && !proposalSpecialReview.isLinkedToProtocol()) {
                proposalSpecialReview.setProtocolStatus(null);
                proposalSpecialReview.setProtocolNumber(null);
            }
        }

        if(proposalSpecialReview.getSpecialReviewNumber() == null) {
            proposalSpecialReview.setSpecialReviewNumber(getProposalDevelopmentSpecialReviewService().generateSpecialReviewNumber(proposalDevelopmentDocument));
        }
        getCollectionControllerService().addLine(pdForm);
        super.save(pdForm);
        return getModelAndViewService().getModelAndView(pdForm);
    }
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=createProtocol")
    public ModelAndView createProtocol(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm pdForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocument document = pdForm.getProposalDevelopmentDocument();
        ProposalSpecialReview proposalSpecialReview = (ProposalSpecialReview) pdForm.getNewCollectionLines().get("document.developmentProposal.propSpecialReviews");
        proposalSpecialReview.setApprovalTypeCode(null);

        ProposalPerson person = pdForm.getDevelopmentProposal().getPrincipalInvestigator();
        if (person == null || org.apache.commons.lang3.StringUtils.isEmpty(person.getPersonId())) {
            getGlobalVariableService().getMessageMap().putError(pdForm.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_ID), "error.special.review.protocol.noprincipal");
        }
        else {
            boolean success = getProposalDevelopmentSpecialReviewService().createProtocol(proposalSpecialReview, document);
            if (success) {
                super.save((ProposalDevelopmentDocumentForm) pdForm);
            } else {
               displayErrors(pdForm);
            }

        }
        pdForm.getNewCollectionLines().clear();
        return getModelAndViewService().getModelAndView(pdForm);
    }

    protected void displayErrors(ProposalDevelopmentDocumentForm pdForm) {
        Map<String, List<ErrorMessage>> messages = getGlobalVariableService().getMessageMap().getErrorMessages();
        for (String message :messages.keySet()) {
            List<ErrorMessage> errors = messages.get(message);
            for(ErrorMessage error : errors) {
                getGlobalVariableService().getMessageMap().putError(pdForm.getActionParamaterValue(UifParameters.SELECTED_COLLECTION_ID), error.getErrorKey());
            }
        }
    }

    protected boolean protocolNeedsToBeLinked(String specialReviewTypeCode) {
    	if(specialReviewTypeCode.equals(SpecialReviewType.HUMAN_SUBJECTS)) {
    		return getProposalDevelopmentSpecialReviewService().isIrbLinkingEnabled();
    	}
    	if(specialReviewTypeCode.equals(SpecialReviewType.ANIMAL_USAGE) ) {
    		return getProposalDevelopmentSpecialReviewService().isIacucLinkingEnabled();
    	}
    	return true;
    }
    
    public ProposalDevelopmentSpecialReviewService getProposalDevelopmentSpecialReviewService() {
 		return proposalDevelopmentSpecialReviewService;
 	}

 	public void setProposalDevelopmentSpecialReviewService(
 			ProposalDevelopmentSpecialReviewService proposalDevelopmentSpecialReviewService) {
 		this.proposalDevelopmentSpecialReviewService = proposalDevelopmentSpecialReviewService;
 	}

    public ProtocolFinderDao getProtocolFinderDao() {
        return protocolFinderDao;
    }

    public void setProtocolFinderDao(ProtocolFinderDao protocolFinderDao) {
        this.protocolFinderDao = protocolFinderDao;
    }

    public IacucProtocolFinderDao getIacucProtocolFinderDao() {
        return iacucProtocolFinderDao;
    }

    public void setIacucProtocolFinderDao(IacucProtocolFinderDao iacucProtocolFinderDao) {
        this.iacucProtocolFinderDao = iacucProtocolFinderDao;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }
}
