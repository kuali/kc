package org.kuali.coeus.propdev.impl.datavalidation;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.krad.rules.rule.event.RouteDocumentEvent;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.controller.MethodAccessible;
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
    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=validateData")
    public ModelAndView validateData(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (form.isValidateData()) {
            form.setDataValidationItems(createDataValidationItems(form.getProposalDevelopmentDocument()));
        }

        return getTransactionalDocumentControllerService().getUIFModelAndView(form);
    }

    @MethodAccessible
    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=toggleValidation")
    public ModelAndView ToggleValidation(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.setValidateData(!form.isValidateData());

        if(form.isValidateData()) {
            form.setDataValidationItems(createDataValidationItems(form.getProposalDevelopmentDocument()));
        }

        return getTransactionalDocumentControllerService().getUIFModelAndView(form);

    }

    private List<ProposalDevelopmentDataValidationItem> createDataValidationItems(ProposalDevelopmentDocument document) {
        List<ProposalDevelopmentDataValidationItem> dataValidationItems = new ArrayList<ProposalDevelopmentDataValidationItem>();

        KRADServiceLocatorWeb.getKualiRuleService().applyRules(new RouteDocumentEvent(document));
        MessageMap messages = GlobalVariables.getMessageMap();

        Map<String,Map<String,List<ErrorMessage>>> allMessageMap = new HashMap<String,Map<String,List<ErrorMessage>>>();
        allMessageMap.put("Error", messages.getErrorMessages());
        allMessageMap.put("Warning",messages.getWarningMessages());
        allMessageMap.put("FYI",messages.getInfoMessages());
        for (Entry<String,Map<String,List<ErrorMessage>>> messageMapEntry : allMessageMap.entrySet()) {
            for (Entry<String,List<ErrorMessage>> errorField : messageMapEntry.getValue().entrySet()) {
                for (ErrorMessage errorMessage : errorField.getValue()) {
                    ProposalDevelopmentDataValidationItem dataValidationItem = new ProposalDevelopmentDataValidationItem();
                    dataValidationItem.setArea("Basic");
                    dataValidationItem.setSection("Proposal Details");
                    dataValidationItem.setDescription(KRADUtils.getMessageText(errorMessage,false));
                    dataValidationItem.setSeverity(messageMapEntry.getKey());
                    dataValidationItems.add(dataValidationItem);
                }
            }
        }

        return dataValidationItems;
    }
}
