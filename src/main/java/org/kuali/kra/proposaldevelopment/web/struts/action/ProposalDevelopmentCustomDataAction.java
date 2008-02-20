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
import org.kuali.RiceConstants;
import org.kuali.core.service.KualiRuleService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.rule.event.SaveCustomAttributeEvent;
import org.kuali.kra.service.CustomAttributeService;

public class ProposalDevelopmentCustomDataAction extends ProposalDevelopmentAction {

    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentCustomDataAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();

        for (Map.Entry<String, String[]>customAttributeValue: proposalDevelopmentForm.getCustomAttributeValues().entrySet()) {
            String customAttributeId = customAttributeValue.getKey().substring(2);
            String value = customAttributeValue.getValue()[0];
            proposalDevelopmentDocument.getCustomAttributeDocuments().get(customAttributeId).getCustomAttribute().setValue(value);
        }
//        List customAttributeGroups = new ArrayList();
//        Map groups = new HashMap();
//
//        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
//        List<CustomAttributeDocValue> customAttributeDocValues = proposalDevelopmentForm.getProposalDevelopmentDocument().getCustomAttributeDocValues();
//        for (CustomAttributeDocValue customAttributeDocValue: customAttributeDocValues) {
//            String groupName = customAttributeDocValue.getCustomAttribute().getGroupName();
//            CustomAttributeGroup customAttributeGroup = (CustomAttributeGroup) groups.get(groupName);
//            if (customAttributeGroup == null) {
//                customAttributeGroup = new CustomAttributeGroup();
//                customAttributeGroup.setFullName(groupName);
//                customAttributeGroup.setCustomAttributeDocValues(new ArrayList());
//                groups.put(groupName, customAttributeGroup);
//            }
//            customAttributeGroup.getCustomAttributeDocValues().add(customAttributeDocValue);
//        }
//        customAttributeGroups.addAll(groups.entrySet());

/*
        CustomAttributeGroup customAttributeGroup = new CustomAttributeGroup();

        List customAttributeDocuments = new ArrayList(3);

//        private Integer id;
//        private Integer dataLength;
//        private String dataTypeCode;
//        private String defaultValue;
//        private String groupName;
//        private String label;
//        private String lookupClass;
//        private String lookupReturn;
//        private String name;

        CustomAttributeDocument customAttributeDocument = new CustomAttributeDocument();

        CustomAttribute customAttribute = new CustomAttribute();
        customAttribute.setId(1);
        customAttribute.setDataLength(30);
        customAttribute.setDataTypeCode("1");
        customAttribute.setGroupName("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
        customAttribute.setLabel("Billing Element");
        customAttribute.setName("billingElement");

        customAttributeDocument.setDocumentTypeCode("PDEV");
        customAttributeDocument.setRequired(true);
        customAttributeDocument.setCustomAttribute(customAttribute);

        customAttributeDocuments.add(customAttributeDocument);

        customAttributeDocument = new CustomAttributeDocument();

        customAttribute = new CustomAttribute();
        customAttribute.setId(2);
        customAttribute.setDataLength(30);
        customAttribute.setDataTypeCode("1");
        customAttribute.setGroupName("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
        customAttribute.setLabel("Cost Sharing Budget");
        customAttribute.setName("costSharingBudget");

        customAttributeDocument.setDocumentTypeCode("PDEV");
        customAttributeDocument.setRequired(false);
        customAttributeDocument.setCustomAttribute(customAttribute);

        customAttributeDocuments.add(customAttributeDocument);

        customAttributeGroup.setFullName("asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf asdf");
        customAttributeGroup.setCustomAttributeDocValues(customAttributeDocuments);
        customAttributeGroups.add(customAttributeGroup);

        customAttributeGroup = new CustomAttributeGroup();

        customAttributeDocuments = new ArrayList(2);

        customAttributeDocument = new CustomAttributeDocument();

        customAttribute = new CustomAttribute();
        customAttribute.setId(3);
        customAttribute.setDataLength(30);
        customAttribute.setDataTypeCode("1");
        customAttribute.setGroupName("Personnel Items for Review");
        customAttribute.setLabel("Graduate Student Count");
        customAttribute.setName("graduateStudentCount");

        customAttributeDocument.setDocumentTypeCode("PDEV");
        customAttributeDocument.setRequired(true);
        customAttributeDocument.setCustomAttribute(customAttribute);

        customAttributeDocuments.add(customAttributeDocument);

        customAttributeDocument = new CustomAttributeDocument();

        customAttribute = new CustomAttribute();
        customAttribute.setId(4);
        customAttribute.setDataLength(30);
        customAttribute.setDataTypeCode("1");
        customAttribute.setGroupName("Personal Items for Review");
        customAttribute.setLabel("Tenured");
        customAttribute.setName("tenured");
        customAttribute.setLookupClass("org.kuali.kra.bo.Country");
        customAttribute.setLookupReturn("countryName");

        customAttributeDocument.setDocumentTypeCode("PDEV");
        customAttributeDocument.setRequired(false);
        customAttributeDocument.setCustomAttribute(customAttribute);

        customAttributeDocuments.add(customAttributeDocument);

        customAttributeGroup.setFullName("Personal Items for Review");
        customAttributeGroup.setCustomAttributeDocValues(customAttributeDocuments);
        customAttributeGroups.add(customAttributeGroup);

*/
  //      ((ProposalDevelopmentForm)form).setCustomAttributeGroups(customAttributeGroups);

        return super.execute(mapping, form, request, response);
    }

    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.refresh(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();

        for (Enumeration i = request.getParameterNames(); i.hasMoreElements();) {
            String parameterName = (String) i.nextElement();
            if (parameterName.startsWith("document.customAttributeDocuments")) {
                //document.customAttributeDocuments[Project Details][1].customAttribute.value=tdurkin
                int beginIndex = parameterName.indexOf("[") + 1;
                int endIndex = parameterName.indexOf("]", beginIndex);
                if (beginIndex > 0 && endIndex > 0) {
                    String key = parameterName.substring(beginIndex, endIndex);
                    beginIndex = parameterName.indexOf("[", endIndex) + 1;
                    endIndex = parameterName.indexOf("]", beginIndex);
                    if (beginIndex > 0 && endIndex > 0) {
                        String indexString = parameterName.substring(beginIndex, endIndex);
                        //int index = Integer.parseInt(indexString);
                        String value = request.getParameter(parameterName);
                        // TODO : why it becomes List ?
                        //((List<CustomAttributeDocument>)proposalDevelopmentDocument.getCustomAttributeDocuments()).get(index).getCustomAttribute().setValue(value);
                        proposalDevelopmentDocument.getCustomAttributeDocuments().get(indexString).getCustomAttribute().setValue(value);
                                          }
                }
            }
        }

        return mapping.findForward(RiceConstants.MAPPING_BASIC);
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getProposalDevelopmentDocument();
//        proposalDevelopmentDocument.mergeNarratives();
//        List<Narrative> narrativeList = proposalDevelopmentDocument.getNarratives();
//        
//        for (Narrative narrative : narrativeList) {
//            populateNarrativeUserRights(proposalDevelopmentDocument, narrative);
//            populateNarrativeType(narrative);
//        }
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
//        //WorkflowDocument document = new WorkflowDocument(new NetworkIdVO(proposalDevelopmentDocument.getUpdateUser()),"ProposalDevelopmentDocument"); 
//        WorkflowDocument document = new WorkflowDocument(new NetworkIdVO(proposalDevelopmentForm.getWorkflowDocument().getInitiatorNetworkId()),new Long (Long.parseLong(proposalDevelopmentDocument.getDocumentHeader().getDocumentNumber()))); 
//        //WorkflowDocument document = proposalDevelopmentForm.getWorkflowDocument().getInitiatorNetworkId();
//        
//        // Not sure to delete all the content, but there is no other options
//        document.clearAttributeContent();  
//        WorkflowAttributeDefinitionVO customDataDef = new WorkflowAttributeDefinitionVO("CustomDataAttribute");
//        
//        customDataDef.addProperty("billingElement", "This is billing element");
//        customDataDef.addProperty("graduateStudentCount", "5");
//        // if it is replace, then 'do clearAttributeContent() first.
//        document.addAttributeDefinition(customDataDef);
//        document.saveRoutingData(); 

        return forward;
    }

    private KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }

}
