package org.kuali.coeus.sys.framework.controller.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.service.ViewService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.controller.UifHandlerExceptionResolver;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.IncidentReportForm;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krad.web.service.ModelAndViewService;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A KC specific exception handler that delegates to a rice krad exception handler.
 */
//not exactly sure how to do this with autowiring since exception handlers are special
public class KcUifHandlerExceptionResolver implements org.springframework.web.servlet.HandlerExceptionResolver, Ordered {

    private static final Logger LOG = Logger.getLogger(UifHandlerExceptionResolver.class);

    HandlerExceptionResolver innerHandler;

    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //Avoids NPE in rice incident handler
        if (handler == null) {
            handler = NullHandler.INSTANCE;
        }

        LOG.error("The following error was caught by the UifHandlerExceptionResolver : ", ex);

        // log exception
        LOG.error(ex.getMessage(), ex);

        String incidentDocId = request.getParameter(KRADConstants.DOCUMENT_DOCUMENT_NUMBER);
        String incidentViewId = "";

        UifFormBase form = (UifFormBase)request.getAttribute(UifConstants.REQUEST_FORM);
        if (form instanceof DocumentFormBase) {
            if (((DocumentFormBase) form).getDocument() != null) {
                incidentDocId = ((DocumentFormBase) form).getDocument().getDocumentNumber();
            }
            incidentViewId = ((DocumentFormBase) form).getViewId();
        }

        if (GlobalVariables.getUifFormManager() != null) {
            GlobalVariables.getUifFormManager().removeSessionForm(form);
        }

        UserSession userSession = (UserSession) request.getSession().getAttribute(KRADConstants.USER_SESSION_KEY);
        IncidentReportForm incidentReportForm = new IncidentReportForm();
        incidentReportForm.setSessionId(request.getSession().getId());

        // Set the post url map to the incident report controller and not
        // the one the exception occurred on
        String postUrl = request.getRequestURL().toString();
        postUrl = postUrl.substring(0, postUrl.lastIndexOf("/")) + "/incidentReport";
        incidentReportForm.setFormPostUrl(postUrl);

        incidentReportForm.setException(ex);
        incidentReportForm.setIncidentDocId(incidentDocId);
        incidentReportForm.setIncidentViewId(incidentViewId);
        incidentReportForm.setController(handler.getClass().toString());

        if (userSession != null) {
            incidentReportForm.setUserId(userSession.getPrincipalId());
            incidentReportForm.setUserName(userSession.getPrincipalName());
            incidentReportForm.setUserEmail(userSession.getPerson().getEmailAddress());
        }

        incidentReportForm.setDevMode(!KRADUtils.isProductionEnvironment());
        incidentReportForm.setViewId("Uif-IncidentReportView");


        if (form != null) {
            incidentReportForm.setAjaxRequest(form.isAjaxRequest());
        } else {
            String ajaxRequestParm = request.getParameter(UifParameters.AJAX_REQUEST);
            if (StringUtils.isNotBlank(ajaxRequestParm)) {
                incidentReportForm.setAjaxRequest(Boolean.parseBoolean(ajaxRequestParm));
            }
        }

        // Set the view object
        incidentReportForm.setView(getViewService().getViewById("Uif-IncidentReportView"));

        // Set the ajax return type
        incidentReportForm.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATEVIEW.getKey());

        incidentReportForm.setRequest(request);
        incidentReportForm.postBind(request);

        ModelAndView modelAndView = getModelAndViewService().getModelAndView(incidentReportForm, "");
        try {
            getModelAndViewService().prepareView(request, modelAndView);
        } catch (Exception e) {
            LOG.error("An error stopped the incident form from loading", e);
        }

        return modelAndView;
    }

    protected ViewService getViewService() {
        return KRADServiceLocatorWeb.getViewService();
    }

    public void setInnerHandler(HandlerExceptionResolver innerHandler) {
        this.innerHandler = innerHandler;
    }

    private static final class NullHandler{
        private static final NullHandler INSTANCE = new NullHandler();
    }

    protected ModelAndViewService getModelAndViewService() {
        return KRADServiceLocatorWeb.getModelAndViewService();
    }
}
