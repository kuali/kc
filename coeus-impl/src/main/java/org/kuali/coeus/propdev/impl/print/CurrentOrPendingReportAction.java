/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.print;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.print.PrintConstants;
import org.kuali.coeus.common.proposal.framework.report.CurrentAndPendingReportService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.KualiAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CurrentOrPendingReportAction extends KualiAction{
    
    private static final Log LOG = LogFactory.getLog(CurrentOrPendingReportAction.class);

    private CurrentAndPendingReportService currentAndPendingReportService;

    protected CurrentAndPendingReportService getCurrentAndPendingReportService (){
        if (currentAndPendingReportService == null)
            currentAndPendingReportService = KcServiceLocator.getService (CurrentAndPendingReportService.class);
        return currentAndPendingReportService;
    }
    /**
     * Prepare current report (i.e. Awards that selected person is on)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printCurrentReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CurrentAndPendingReportService currentAndPendingReportService =
                getCurrentAndPendingReportService();
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(PrintConstants.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(PrintConstants.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printCurrentAndPendingSupportReport(
                PrintConstants.CURRENT_REPORT_TYPE, reportParameters);
        streamToResponse(dataStream.getData(), dataStream.getName(), null, response);
        return null;
    }

    /**
     * Prepare pending report (i.e. InstitutionalProposals that selected person is on) {@inheritDoc}
     */
    public ActionForward printPendingReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CurrentAndPendingReportService currentAndPendingReportService =
                getCurrentAndPendingReportService();
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(PrintConstants.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(PrintConstants.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printCurrentAndPendingSupportReport(
                PrintConstants.PENDING_REPORT_TYPE, reportParameters);
        streamToResponse(dataStream.getData(), dataStream.getName(), null, response);
        return null;
    }

    /**
     * Prepare current report (i.e. Awards that selected person is on)
     * {@inheritDoc}
     */
    public ActionForward prepareCurrentReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                            throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        request.setAttribute(PrintConstants.CURRENT_REPORT_ROWS_KEY, helper.prepareCurrentReport());
        request.setAttribute(PrintConstants.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Prepare pending report (i.e. InstitutionalProposals that selected person is on)
     * {@inheritDoc}
     */
    public ActionForward preparePendingReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                                    throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        request.setAttribute(PrintConstants.PENDING_REPORT_ROWS_KEY, helper.preparePendingReport());
        request.setAttribute(PrintConstants.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
    *
    * Handy method to stream the byte array to response object
    * @param fileContents
    * @param fileName
    * @param fileContentType
    * @param response
    * @throws Exception
    */
   protected void streamToResponse(byte[] fileContents, String fileName, String fileContentType,HttpServletResponse response) throws Exception{
       ByteArrayOutputStream baos = null;
       try{
           baos = new ByteArrayOutputStream(fileContents.length);
           baos.write(fileContents);
           WebUtils.saveMimeOutputStreamAsFile(response, fileContentType, baos, fileName);
       }finally{
           try{
               if(baos!=null){
                   baos.close();
                   baos = null;
               }
           }catch(IOException ioEx){
               LOG.error("Error while downloading attachment");
               throw new RuntimeException("IOException occurred while downloading attachment", ioEx);
           }
       }
   }
}
