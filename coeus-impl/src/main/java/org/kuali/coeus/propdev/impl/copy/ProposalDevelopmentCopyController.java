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
package org.kuali.coeus.propdev.impl.copy;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.doctype.DocumentType;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.krad.uif.UifParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Controller
public class ProposalDevelopmentCopyController extends ProposalDevelopmentControllerBase {

    public static final String COPY_DIALOG = "PropDev-CopyDialog-Section";

    @Autowired
    @Qualifier("documentTypeService")
    private DocumentTypeService documentTypeService;

    @Autowired
    @Qualifier("proposalCopyService")
    private ProposalCopyService proposalCopyService;

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=copy")
    public ModelAndView copy(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {

        ProposalDevelopmentDocument proposalDevelopmentDocument = form.getProposalDevelopmentDocument();
        ProposalCopyService proposalCopyService = getProposalCopyService();
        getPessimisticLockService().releaseAllLocksForUser(proposalDevelopmentDocument.getPessimisticLocks(), getGlobalVariableService().getUserSession().getPerson());
        ProposalCopyCriteria proposalCopyCriteria = form.getProposalCopyCriteria();
        ProposalDevelopmentDocument newDoc = proposalCopyService.copyProposal(proposalDevelopmentDocument, proposalCopyCriteria);

        return returnToDocument(form, newDoc.getDocumentNumber());
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=displayCopyDialog")
    public ModelAndView displayCopyDialog(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        ((ProposalDevelopmentViewHelperServiceImpl)form.getView().getViewHelperService()).populateQuestionnaires(form);
        return getModelAndViewService().showDialog(COPY_DIALOG,true,form);
    }

    protected  ModelAndView returnToDocument(ProposalDevelopmentDocumentForm form, String newDocNum) {
        String docHandlerUrl = getDocHandlerUrl(form);
        Properties props = new Properties();
        props.put(KewApiConstants.COMMAND_PARAMETER, KewApiConstants.DOCSEARCH_COMMAND);
        props.put(KewApiConstants.DOCUMENT_ID_PARAMETER, newDocNum);
        if (StringUtils.isNotBlank(form.getReturnFormKey())) {
            props.put(UifParameters.FORM_KEY, form.getReturnFormKey());
        }

        return getModelAndViewService().performRedirect(form, docHandlerUrl, props);
    }

    protected String getDocHandlerUrl(ProposalDevelopmentDocumentForm form) {
        DocumentType docType = getDocumentTypeService().getDocumentTypeByName(form.getDocTypeName());
        return docType.getResolvedDocumentHandlerUrl();
    }

    public DocumentTypeService getDocumentTypeService() {
        return documentTypeService;
    }

    public void setDocumentTypeService(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    public ProposalCopyService getProposalCopyService() {
        return proposalCopyService;
    }

    public void setProposalCopyService(ProposalCopyService proposalCopyService) {
        this.proposalCopyService = proposalCopyService;
    }
}
