package org.kuali.coeus.propdev.impl.datavalidation;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.rules.rule.event.DocumentAuditEvent;
import org.kuali.rice.krad.rules.rule.event.RouteDocumentEvent;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.GroupBase;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.Header;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewIndex;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.controller.MethodAccessible;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;

@Controller
public class ProposalDevelopmentDataValidationController extends ProposalDevelopmentControllerBase {
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentDataValidationController.class);

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=validateData")
    public ModelAndView validateData(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (form.isValidateData()) {
            form.setDataValidationItems(createDataValidationItems(form.getProposalDevelopmentDocument(),form.getView().getViewIndex()));
        }

        return getTransactionalDocumentControllerService().getUIFModelAndView(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=toggleValidation")
    public ModelAndView ToggleValidation(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.setValidateData(!form.isValidateData());

        if(form.isValidateData()) {
            form.setDataValidationItems(createDataValidationItems(form.getProposalDevelopmentDocument(),form.getView().getViewIndex()));
        }

        return getTransactionalDocumentControllerService().getUIFModelAndView(form);

    }

    private List<ProposalDevelopmentDataValidationItem> createDataValidationItems(ProposalDevelopmentDocument document, ViewIndex viewIndex) {
        List<ProposalDevelopmentDataValidationItem> dataValidationItems = new ArrayList<ProposalDevelopmentDataValidationItem>();
        KNSGlobalVariables.getAuditErrorMap().clear();
        KRADServiceLocatorWeb.getKualiRuleService().applyRules(new RouteDocumentEvent(document));
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
}
