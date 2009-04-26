/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.web.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * 
 * This class represents the Struts Action for Custom Data page(AwardCustomData.jsp)
 */
public class AwardCustomDataAction extends AwardAction {   
    
    
    private static final String CUSTOM_ATTRIBUTE_NAME = "AwardCustomDataAttribute";
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CustomDataAction.copyCustomDataToDocument(form);
        
        return super.execute(mapping, form, request, response);
    }
    
    /**
     * @see org.kuali.kra.irb.web.struts.action.AwardAction#isValidSave(org.kuali.kra.irb.web.struts.form.AwardForm)
     */
    @Override
    protected boolean isValidSave(AwardForm awardForm) {
        return super.isValidSave(awardForm) 
                && applyRules(new SaveCustomAttributeEvent(Constants.EMPTY_STRING, awardForm.getAwardDocument()));
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#postDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        CustomDataAction.setCustomAttributeContent(form, CUSTOM_ATTRIBUTE_NAME);
    }
}
