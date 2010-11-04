/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.customattributes.CustomDataAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

public class ProposalDevelopmentCustomDataAction extends ProposalDevelopmentAction {

    private static final String CUSTOM_ATTRIBUTE_NAME = "CustomDataAttribute";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();

        for (Map.Entry<String, String[]>customAttributeValue: proposalDevelopmentForm.getCustomAttributeValues().entrySet()) {
            String customAttributeId = customAttributeValue.getKey().substring(2);
            String value = customAttributeValue.getValue()[0];
            proposalDevelopmentDocument.getCustomAttributeDocuments().get(customAttributeId).getCustomAttribute().setValue(value);
        }

        return super.execute(mapping, form, request, response);
    }

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.refresh(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();

        //Does not seem we need this any more.
        // TODO : if there is no issue in QA, then remove this. 
        // now, seemes to have 'customAttributeValues(id7)=quickstart' when return from lookup.
        // so it will be populated to  form properly.
//        for (Enumeration i = request.getParameterNames(); i.hasMoreElements();) {
//            String parameterName = (String) i.nextElement();
//            if (parameterName.startsWith("document.customAttributeDocuments")) {
//                // TODO : do we still need this section ?
//                //document.customAttributeDocuments[Project Details][1].customAttribute.value=tdurkin
//                int beginIndex = parameterName.indexOf("[") + 1;
//                int endIndex = parameterName.indexOf("]", beginIndex);
//                if (beginIndex > 0 && endIndex > 0) {
//                    String key = parameterName.substring(beginIndex, endIndex);
//                    beginIndex = parameterName.indexOf("[", endIndex) + 1;
//                    endIndex = parameterName.indexOf("]", beginIndex);
//                    if (beginIndex > 0 && endIndex > 0) {
//                        String indexString = parameterName.substring(beginIndex, endIndex);
//                        //int index = Integer.parseInt(indexString);
//                        String value = request.getParameter(parameterName);
//                        // TODO : why it becomes List ?
//                        //((List<CustomAttributeDocument>)proposalDevelopmentDocument.getCustomAttributeDocuments()).get(index).getCustomAttribute().setValue(value);
//                        proposalDevelopmentDocument.getCustomAttributeDocuments().get(indexString).getCustomAttribute().setValue(value);
//                                          }
//                }
//            }
//        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#postDocumentSave(org.kuali.core.web.struts.form.KualiDocumentFormBase)
     */
    @Override
    public void postDocumentSave(KualiDocumentFormBase form) throws Exception {
        super.postDocumentSave(form);
        CustomDataAction.setCustomAttributeContent(form, CUSTOM_ATTRIBUTE_NAME);
    }

}