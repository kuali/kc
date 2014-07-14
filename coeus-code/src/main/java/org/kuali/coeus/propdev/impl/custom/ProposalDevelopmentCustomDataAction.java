/*
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.custom;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.impl.custom.CustomDataRule;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentAction;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.custom.SaveCustomDataEvent;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProposalDevelopmentCustomDataAction extends ProposalDevelopmentAction {

    private static final String CUSTOM_ATTRIBUTE_NAME = "CustomDataAttribute";
    
    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.reload(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getCustomDataHelper().prepareCustomData();

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        //have to do the custom data validation here, separate from the document save, as invalid default values could cause the
        //document to be unusable.
        if (new CustomDataRule().processRules(new SaveCustomDataEvent(proposalDevelopmentForm.getProposalDevelopmentDocument()))) {
            return super.save(mapping, form, request, response);   
        } else {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }
    
    
    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getCustomDataHelper().setCustomAttributeContent(proposalDevelopmentForm.getProposalDevelopmentDocument().getDocumentNumber(), CUSTOM_ATTRIBUTE_NAME);
    }

}
