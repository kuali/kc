package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.sys.framework.controller.DocumentControllerService;
import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.exception.DocumentAuthorizationException;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.web.controller.DocumentControllerBase;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/** this service must @Override methods and call super in order to elevate a method to public to satisfy the interface. */
@Service(value="documentControllerService")
public class DocumentControllerServiceImpl extends DocumentControllerBase implements DocumentControllerService {

    @Autowired
    @Qualifier("kcCommonControllerService")
    private KcCommonControllerService kcCommonControllerService;

    public UifFormBase initForm(UifFormBase requestForm, HttpServletRequest request, HttpServletResponse response) {
        return kcCommonControllerService.initForm(requestForm,request,response);
    }

    @Override
    public DocumentFormBase createInitialForm(HttpServletRequest request) {
        return (DocumentFormBase) kcCommonControllerService.createInitialForm(request);
    }

    @Override
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) {
        return kcCommonControllerService.initForm(request, response);
    }

    @Override
    public ModelAndView performRedirect(UifFormBase form, String baseUrl, Properties urlParameters) {
        return super.performRedirect(form, baseUrl, urlParameters);
    }

    @Override
    public ModelAndView performRedirect(UifFormBase form, String redirectUrl) {
        return super.performRedirect(form, redirectUrl);
    }

    @Override
    public ModelAndView getMessageView(UifFormBase form, String headerText, String messageText) {
        return super.getMessageView(form, headerText, messageText);
    }

    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form) {
        return super.getUIFModelAndView(form);
    }

    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form, String pageId) {
        return super.getUIFModelAndView(form, pageId);
    }

    @Override
    public ModelAndView getUIFModelAndViewWithInit(UifFormBase form, String viewId) {
        return super.getUIFModelAndViewWithInit(form, viewId);
    }

    @Override
    public ModelAndView getUIFModelAndView(UifFormBase form, Map<String, Object> additionalViewAttributes) {
        return super.getUIFModelAndView(form, additionalViewAttributes);
    }

    @Override
    public void loadDocument(DocumentFormBase form) throws WorkflowException {
        super.loadDocument(form);
    }

    @Override
    public void createDocument(DocumentFormBase form) throws WorkflowException {
        super.createDocument(form);
    }

    @Override
    public List<AdHocRouteRecipient> combineAdHocRecipients(DocumentFormBase form) {
        return super.combineAdHocRecipients(form);
    }

    @Override
    public DocumentAuthorizationException buildAuthorizationException(String action, Document document) {
        return super.buildAuthorizationException(action, document);
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }

    @Override
    public ModelAndView showDialog(String dialogId, boolean confirmation, UifFormBase form) {
        return super.showDialog(dialogId, confirmation, form);
    }

}
