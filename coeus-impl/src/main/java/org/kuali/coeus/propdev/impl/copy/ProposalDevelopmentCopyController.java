package org.kuali.coeus.propdev.impl.copy;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.doctype.DocumentType;
import org.kuali.rice.kew.api.doctype.DocumentTypeService;
import org.kuali.rice.krad.uif.UifParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Controller
public class ProposalDevelopmentCopyController extends ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("documentTypeService")
    private DocumentTypeService documentTypeService;

    @Autowired
    @Qualifier("proposalCopyService")
    private ProposalCopyService proposalCopyService;

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
