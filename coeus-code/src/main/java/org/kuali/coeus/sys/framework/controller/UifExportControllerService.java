package org.kuali.coeus.sys.framework.controller;

import org.kuali.rice.krad.web.form.UifFormBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** public and protected methods from Rice's UifExportController */
public interface UifExportControllerService extends UifControllerService {

    //public methods

    public String tableCsvRetrieval(UifFormBase form, HttpServletRequest request,
                                    HttpServletResponse response);

    public String tableXlsRetrieval(UifFormBase form, HttpServletRequest request,
                                    HttpServletResponse response);

    public String tableXmlRetrieval(UifFormBase form, HttpServletRequest request,
                                    HttpServletResponse response);


    //protected methods

    @Override
    UifFormBase createInitialForm(HttpServletRequest request);

    
    String retrieveTableData(UifFormBase form, HttpServletRequest request,
                                       HttpServletResponse response);

    void setAttachmentResponseHeader(HttpServletResponse response, String filename, String contentType);

    String getValidatedFormatType(String formatType);

    String getContentType(String formatType);
}
