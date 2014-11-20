package org.kuali.coeus.sys.framework.controller;

import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller Service methods common to KC.  Used by other controller services.
 */
public interface KcCommonControllerService {

    UifFormBase initForm(UifFormBase requestForm, HttpServletRequest request, HttpServletResponse response);
    public ModelAndView closeDialog(String dialogId, UifFormBase form);
    
}

