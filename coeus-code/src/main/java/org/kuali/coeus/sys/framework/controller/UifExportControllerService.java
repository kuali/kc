package org.kuali.coeus.sys.framework.controller;

import org.kuali.rice.krad.web.form.UifFormBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** public and protected methods from Rice's UifExportController */
public interface UifExportControllerService {

    //public methods

    public String tableCsvRetrieval(UifFormBase form, HttpServletRequest request,
                                    HttpServletResponse response);

    public String tableXlsRetrieval(UifFormBase form, HttpServletRequest request,
                                    HttpServletResponse response);

    public String tableXmlRetrieval(UifFormBase form, HttpServletRequest request,
                                    HttpServletResponse response);
}
