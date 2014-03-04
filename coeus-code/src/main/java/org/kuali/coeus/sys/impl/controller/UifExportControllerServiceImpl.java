package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.controller.UifExportControllerService;
import org.kuali.rice.krad.web.controller.UifExportController;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Properties;

/** this service must @Override methods and call super in order to elevate a method to public to satisfy the interface. */
@Component("uifExportControllerService")
public class UifExportControllerServiceImpl extends UifExportController implements UifExportControllerService {

    @Autowired
    @Qualifier("kcCommonControllerService")
    private KcCommonControllerService kcCommonControllerService;

    public UifFormBase initForm(UifFormBase requestForm, HttpServletRequest request, HttpServletResponse response) {
        return kcCommonControllerService.initForm(requestForm,request,response);
    }

    @Override
    public UifFormBase createInitialForm(HttpServletRequest request) {
        return kcCommonControllerService.createInitialForm(request);
    }

    @Override
    public UifFormBase initForm(HttpServletRequest request, HttpServletResponse response) {
        return kcCommonControllerService.initForm(request, response);
    }

    @Override
    public boolean hasDialogBeenDisplayed(String dialogId, UifFormBase form) {
        return super.hasDialogBeenDisplayed(dialogId, form);
    }

    @Override
    public boolean hasDialogBeenAnswered(String dialogId, UifFormBase form) {
        return super.hasDialogBeenAnswered(dialogId, form);
    }

    @Override
    public void resetDialogStatus(String dialogId, UifFormBase form) {
        super.resetDialogStatus(dialogId, form);
    }

    @Override
    public boolean getBooleanDialogResponse(String dialogId, UifFormBase form, HttpServletRequest request,
                                            HttpServletResponse response) {
        return super.getBooleanDialogResponse(dialogId, form, request, response);
    }

    @Override
    public String getStringDialogResponse(String dialogId, UifFormBase form, HttpServletRequest request,
                                          HttpServletResponse response) {
        return super.getStringDialogResponse(dialogId, form, request, response);
    }

    @Override
    public ModelAndView showDialog(String dialogId, UifFormBase form, HttpServletRequest request,
                                   HttpServletResponse response) {
        return super.showDialog(dialogId, form, request, response);
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
    public String retrieveTableData(UifFormBase form, HttpServletRequest request,
                             HttpServletResponse response) {
        return super.retrieveTableData(form, request, response);
    }

    @Override
    public void setAttachmentResponseHeader(HttpServletResponse response, String filename, String contentType) {
        super.setAttachmentResponseHeader(response, filename, contentType);
    }

    @Override
    public String getValidatedFormatType(String formatType) {
        return super.getValidatedFormatType(formatType);
    }

    @Override
    public String getContentType(String formatType) {
        return super.getContentType(formatType);
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }
}
