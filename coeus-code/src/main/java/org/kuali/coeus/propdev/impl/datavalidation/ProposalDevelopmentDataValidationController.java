package org.kuali.coeus.propdev.impl.datavalidation;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.uif.element.Header;
import org.kuali.rice.krad.uif.view.ViewIndex;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.service.NavigationControllerService;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

@Controller
public class ProposalDevelopmentDataValidationController extends ProposalDevelopmentControllerBase {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentDataValidationController.class);

    @Autowired
    @Qualifier("krmsRulesExecutionService")
    private KrmsRulesExecutionService krmsRulesExecutionService;

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=validateData")
    public ModelAndView validateData(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (form.isAuditActivated()) {
            form.setDataValidationItems(createDataValidationItems(form,form.getView().getViewIndex()));
        }

        return getModelAndViewService().getModelAndView(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=toggleValidation")
    public ModelAndView toggleValidation(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.setAuditActivated(!form.isAuditActivated());
        if(form.isAuditActivated()) {
            form.setDataValidationItems(createDataValidationItems(form,form.getView().getViewIndex()));
        }

        return getModelAndViewService().getModelAndView(form);

    }


    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=navigateToError")
    public ModelAndView navigateToError(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.setAjaxReturnType("update-page");
        return getNavigationControllerService().navigate(form);
    }

    private List<ProposalDevelopmentDataValidationItem> createDataValidationItems(ProposalDevelopmentDocumentForm form, ViewIndex viewIndex) {
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        List<ProposalDevelopmentDataValidationItem> dataValidationItems = new ArrayList<ProposalDevelopmentDataValidationItem>();
        KNSGlobalVariables.getAuditErrorMap().clear();
        getAuditHelper().auditConditionally(form);
        for (Entry<String,AuditCluster> entry : KNSGlobalVariables.getAuditErrorMap().entrySet()) {
            AuditCluster auditCluster = (AuditCluster) entry.getValue();
            List<AuditError> auditErrors = auditCluster.getAuditErrorList();
            for (AuditError auditError : auditErrors) {
                ProposalDevelopmentDataValidationItem dataValidationItem = new ProposalDevelopmentDataValidationItem();
                String links[] = StringUtils.split(auditError.getLink(),".");
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setErrorKey(auditError.getMessageKey());
                errorMessage.setMessageParameters(auditError.getParams());

                dataValidationItem.setArea(auditCluster.getLabel());
                dataValidationItem.setSection(getComponentLabel(links[1],viewIndex));
                dataValidationItem.setDescription(KRADUtils.getMessageText(errorMessage,false));
                dataValidationItem.setSeverity(auditCluster.getCategory());
                dataValidationItem.setNavigateToPageId(links[0]);
                dataValidationItem.setNavigateToSectionId(links[1]);

                dataValidationItems.add(dataValidationItem);
            }
        }



        List<Map<String,String>> krmsErrors = getKrmsRulesExecutionService().processUnitKcValidations(document.getLeadUnitNumber(),document);
        for (Map<String,String> error: krmsErrors) {
            ProposalDevelopmentDataValidationItem dataValidationItem = new ProposalDevelopmentDataValidationItem();
            dataValidationItem.setArea(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE));
            dataValidationItem.setSection(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE));
            dataValidationItem.setDescription(error.get(ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE));
            dataValidationItem.setSeverity(error.get(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE).equals("E")?"Error":"Warning");
            dataValidationItem.setNavigateToPageId(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE));
            dataValidationItem.setNavigateToSectionId(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE));
            dataValidationItems.add(dataValidationItem);
        }
        return dataValidationItems;
    }

    private String getComponentLabel(String componentId, ViewIndex viewIndex) {
        try {
            Header header = (Header) PropertyUtils.getProperty(viewIndex.getComponentById(componentId),"header");
            return header.getHeaderText();
        } catch (Exception e) {
            LOG.error("SectionID does not have a header property");
        }
        return null;
    }

    public KrmsRulesExecutionService getKrmsRulesExecutionService() {
        return krmsRulesExecutionService;
    }

    public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
        this.krmsRulesExecutionService = krmsRulesExecutionService;
    }

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }
}
