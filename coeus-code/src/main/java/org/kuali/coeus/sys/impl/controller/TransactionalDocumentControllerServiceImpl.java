package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.sys.framework.controller.KcCommonControllerService;
import org.kuali.coeus.sys.framework.controller.TransactionalDocumentControllerService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.exception.DocumentAuthorizationException;
import org.kuali.rice.krad.file.FileMeta;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.controller.TransactionalDocumentControllerBase;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/*
 * Copyright 2005-2010 The Kuali Foundation
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

/** this service must @Override methods and call super in order to elevate a method to public to satisfy the interface. */
@Service("transactionalDocumentControllerService")
public class TransactionalDocumentControllerServiceImpl extends TransactionalDocumentControllerBase implements TransactionalDocumentControllerService {

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

    @Override
    public void sendFileFromLineResponse(UifFormBase uifForm, HttpServletRequest request, HttpServletResponse response,
                                         List<FileMeta> collection, FileMeta fileLine) throws Exception{
        if (fileLine instanceof Narrative) {
            Narrative narrative = (Narrative) fileLine;
            byte[] data = narrative.getNarrativeAttachment().getData();
            long datasize = new Long(data.length);
           ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
           KRADUtils.addAttachmentToResponse(response,inputStream,narrative.getNarrativeAttachment().getType(),narrative.getName(),datasize);
        }
    }

    public void setKcCommonControllerService(KcCommonControllerService kcCommonControllerService) {
        this.kcCommonControllerService = kcCommonControllerService;
    }
}
