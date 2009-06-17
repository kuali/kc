/*
 * Copyright 2006-2009 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.service.CustomAttributeService;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.util.KNSConstants;

public class ProposalDevelopmentCustomDataAction extends ProposalDevelopmentAction {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentCustomDataAction.class);

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

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        boolean rulePassed = true;
        // check any business rules
        rulePassed &= getKualiRuleService().applyRules(new SaveCustomAttributeEvent(Constants.EMPTY_STRING,proposalDevelopmentDocument));

        if (!rulePassed){
            mapping.findForward(Constants.MAPPING_BASIC);
        }
        

        // refresh, so the status can be displayed properly on tab title
        ActionForward forward =  super.save(mapping, form, request, response);
        // save the key/value pair.  probably in service
        getService(CustomAttributeService.class).setCustomAttributeKeyValue(proposalDevelopmentDocument, "CustomDataAttribute", proposalDevelopmentForm.getWorkflowDocument().getInitiatorNetworkId());

        return forward;
    }

    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

}
