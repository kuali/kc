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
package org.kuali.coeus.propdev.impl.attachment;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentControllerBase;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.TransactionalDocumentView;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class ProposalDevelopmentAttachmentController extends ProposalDevelopmentControllerBase {

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=editAttachment")
    public ModelAndView editAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        final String attachmentType = pdForm.getActionParamaterValue("attachmentType");
        final String attachmentKey = pdForm.getActionParamaterValue("attachmentKey");

        if(pdForm.getEditableAttachmentsMap().containsKey(attachmentType)) {
            pdForm.getEditableAttachmentsMap().get(attachmentType).add(attachmentKey);
        } else {
            List<String> newKeyList = new ArrayList<String>();
            newKeyList.add(attachmentKey);
            pdForm.getEditableAttachmentsMap().put(attachmentType,newKeyList);
        }

        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }


    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=cancelAttachment")
    public ModelAndView cancelAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        final String attachmentType = pdForm.getActionParamaterValue("attachmentType");
        final String attachmentKey = pdForm.getActionParamaterValue("attachmentKey");

        if(pdForm.getEditableAttachmentsMap().containsKey(attachmentType)){
            pdForm.getEditableAttachmentsMap().get(attachmentType).remove(attachmentKey);
        }
        return getTransactionalDocumentControllerService().refresh(form, result, request, response);
    }

    @RequestMapping(value = "/proposalDevelopment", params="methodToCall=saveAttachment")
    public ModelAndView saveAttachment(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        final String attachmentType = pdForm.getActionParamaterValue("attachmentType");
        final String attachmentKey = pdForm.getActionParamaterValue("attachmentKey");

        if(pdForm.getEditableAttachmentsMap().containsKey(attachmentType)){
            pdForm.getEditableAttachmentsMap().get(attachmentType).remove(attachmentKey);
        }

        return getTransactionalDocumentControllerService().saveLine(form, result, request, response);
    }
}
