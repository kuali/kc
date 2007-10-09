/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.RiceConstants;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ScienceKeyword;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.KNSServiceLocator;

public class ProposalDevelopmentAction extends KraTransactionalDocumentActionBase {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAction.class);
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String keywordPanelDisplay = KNSServiceLocator.getKualiConfigurationService().getApplicationParameterValue(RiceConstants.ParameterGroups.SYSTEM, Constants.KEYWORD_PANEL_DISPLAY);
        request.setAttribute(Constants.KEYWORD_PANEL_DISPLAY, keywordPanelDisplay);
        return super.execute(mapping, form, request, response);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        return super.save(mapping, form, request, response);
    }

    public ActionForward proposal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("proposal");
    }
    
    public ActionForward keyPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("keyPersonnel");
    }
    
    public ActionForward template(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("template");
    }
    
    public ActionForward notes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("notes");
    }
    
    public ActionForward specialReview(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("specialReview");
    }
    
    public ActionForward abstractsAttachments(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("abstractsAttachments");
    }
    
    public ActionForward customData(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("customData");
    }
    
    public ActionForward actions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("actions");
    }
    public ActionForward streamDataToBrowser(ActionMapping mapping,AttachmentDataSource dataSource,HttpServletResponse response){
        javax.servlet.ServletOutputStream sos = null;
        ByteArrayOutputStream baos = null;
        try {
            sos = response.getOutputStream();
            byte[] xbts = dataSource.getContent();
            baos = new ByteArrayOutputStream(xbts.length);
            baos.write(xbts);
            StringBuffer sbContentDispValue = new StringBuffer();
//            sbContentDispValue.append("attachment");
            sbContentDispValue.append("inline");
            sbContentDispValue.append("; filename=");
            sbContentDispValue.append(dataSource.getFileName());
            
            response.setHeader("Cache-control", "");
            response.setHeader(
            "Content-disposition",
            sbContentDispValue.toString());
            response.setContentType(dataSource.getContentType());
            response.setContentLength(xbts.length);
            response.setContentType(dataSource.getContentType());
            baos.writeTo(sos);
            sos.flush();
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
            response.setContentType("text/html");
            try{
                PrintWriter writer = response.getWriter();
                writer.println(
                this.getClass().getName()
                + " caught an exception: "
                + e.getClass().getName()
                + "<br>");
                writer.println("<pre>");
                e.printStackTrace(writer);
                writer.println("</pre>");
            }catch(IOException ex){
                LOG.error(ex.getMessage(),ex);
            }
            return mapping.findForward("error");
//            saveMessage(request, errors);
        }
        finally {
            try{
                sos.close();
            }catch(IOException ioEx){
                LOG.error(ioEx.getMessage(),ioEx);
            }
        }
        return null;
    }
}
