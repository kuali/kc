/*
 * Copyright 2005-2010 The Kuali Foundation.
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
package org.kuali.kra.s2s.generator.impl;


import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.kuali.kra.s2s.generator.bo.AttachmentData;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.kra.s2s.validator.S2SErrorHandler;
import org.kuali.kra.service.KcAttachmentService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This abstract class has methods that are common to all the versions of RRSubAwardBudget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class S2SAdobeFormAttachmentBaseGenerator extends S2SBaseFormGenerator {

    protected static final String RR_BUDGET_10_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget-V1.0";
    protected static final String RR_BUDGET_11_NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget-V1.1";
    protected static final String LOCAL_NAME = "RR_Budget";
    protected static final String ERROR1_PROPERTY_KEY = "s2sSubawardBudgetV1-2_10000";
    protected static final String ERROR2_PROPERTY_KEY = "s2sSubawardBudget_10002";
    private transient static KcAttachmentService  kcAttachmentService;

    /**
     * This method convert node of form in to a Document
     * 
     * @param node n {Node} node entry.
     * @return Document containing doc information
     */

    public Document nodeToDom(org.w3c.dom.Node node) throws S2SException {
        try {
            javax.xml.transform.TransformerFactory tf = javax.xml.transform.TransformerFactory.newInstance();
            javax.xml.transform.Transformer xf = tf.newTransformer();
            javax.xml.transform.dom.DOMResult dr = new javax.xml.transform.dom.DOMResult();
            xf.transform(new javax.xml.transform.dom.DOMSource(node), dr);
            return (Document) dr.getNode();

        }
        catch (javax.xml.transform.TransformerException ex) {
            throw new S2SException(ex.getMessage());
        }
    }


    /**
     * This method convert xml string in to a Document
     * 
     * @param xmlSource {xml String} xml source entry.
     * @return Document containing doc information
     */
    public Document stringToDom(String xmlSource) throws S2SException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlSource)));
        }
        catch (SAXException ex) {
            throw new S2SException(ex.getMessage());
        }
        catch (ParserConfigurationException ex) {
            throw new S2SException(ex.getMessage());
        }
        catch (IOException ex) {
            throw new S2SException(ex.getMessage());
        }
    }

    /**
     * This method convert Document to a byte Array
     * 
     * @param node {Document} node entry.
     * @return byte Array containing doc information
     */
    public byte[] docToBytes(Document node) throws S2SException {
        return docToString(node).getBytes();
    }

    /**
     * This method convert Document to a String
     * 
     * @param node {Document} node entry.
     * @return String containing doc information
     */
    public String docToString(Document node) throws S2SException {
        try {
            DOMSource domSource = new DOMSource(node);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);
            return writer.toString();
        }
        catch (Exception e) {
            throw new S2SException(e.getMessage());
        }
    }

    /**
     * 
     * This method is used to return the attachment name which comes from BudgetSubawards
     * 
     * @param budgetSubAwards(BudgetSubAwards) budget sub award entry.
     * @return String attachment name for the budget sub awards.
     */
    protected static final String prepareAttName(BudgetSubAwards budgetSubAwards) {
        StringBuilder attachmentName = new StringBuilder();
        
        attachmentName.append(budgetSubAwards.getBudgetId() + "-");
        //checking organization name and replacing invalid characters
        // with underscores.
        String cleanSubAwardOrganizationName = getKcAttachmentService().checkAndReplaceInvalidCharacters(budgetSubAwards.getOrganizationName());
        attachmentName.append(cleanSubAwardOrganizationName + "-");
        attachmentName.append(budgetSubAwards.getSubAwardNumber());
        return attachmentName.toString();
    }
    
    /**
     * This method gets the attachment service
     * @return
     */
    protected static KcAttachmentService getKcAttachmentService() {
        if (kcAttachmentService == null) {
            kcAttachmentService = KraServiceLocator.getService(KcAttachmentService.class);
        }
        return kcAttachmentService;
    }
    
    /**
     * Adding attachments to subaward
     */
    protected void addSubAwdAttachments(BudgetSubAwards budgetSubAwards) {
        budgetSubAwards.refreshReferenceObject("budgetSubAwardAttachments");
        List<BudgetSubAwardAttachment> subAwardAttachments = budgetSubAwards.getBudgetSubAwardAttachments();
        for (BudgetSubAwardAttachment budgetSubAwardAttachment : subAwardAttachments) {
            AttachmentData attachmentData = new AttachmentData();
            attachmentData.setContent(budgetSubAwardAttachment.getAttachment());
            attachmentData.setContentId(budgetSubAwardAttachment.getContentId());
            attachmentData.setContentType(budgetSubAwardAttachment.getContentType());
            attachmentData.setFileName(budgetSubAwardAttachment.getContentId());
            addAttachment(attachmentData);
        }
    }
    /**
     * 
     * This method is used to get BudgetSubAwrads from ProposalDevelopmentDocument
     * 
     * @param proposalDevelopmentDocument (ProposalDevelopmentDocument)
     * @return List<BudgetSubAwards> list of budget sub awards.
     */
    protected List<BudgetSubAwards> getBudgetSubAwards(ProposalDevelopmentDocument proposalDevelopmentDocument,
            String namespace,boolean checkNull) {
        List<BudgetSubAwards> budgetSubAwardsList = new ArrayList<BudgetSubAwards>();
        Budget budget = findBudgetFromProposal(proposalDevelopmentDocument);
        if(budget==null){
            getAuditErrors().add(S2SErrorHandler.getError(S2SConstants.SUB_AWARD_BUDGET_NOT_FOUND));
        }else{
            budgetSubAwardsList = findBudgetSubawards(namespace, budget,checkNull);
            if(budgetSubAwardsList.isEmpty()){
                getAuditErrors().add(S2SErrorHandler.getError(S2SConstants.SUB_AWARD_BUDGET_NOT_FOUND));
            }
        }
        return budgetSubAwardsList;
    }


    /**
     * This method is to find the subaward budget BOs for the given namespace
     * @param namespace
     * @param budget
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<BudgetSubAwards> findBudgetSubawards(String namespace, Budget budget,boolean checkNull) {
        List<BudgetSubAwards> budgetSubAwardsList;
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("budgetId", budget.getBudgetId());
        paramMap.put("namespace", namespace);
        budgetSubAwardsList = (List<BudgetSubAwards>)getBusinessObjectService().findMatching(BudgetSubAwards.class, paramMap);
        if(checkNull){
            paramMap.put("namespace", null);
            budgetSubAwardsList.addAll(
                    getBusinessObjectService().findMatching(BudgetSubAwards.class, paramMap));
        }
        return budgetSubAwardsList;
    }

    private Budget findBudgetFromProposal(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        Budget finalBudget = proposalDevelopmentDocument.getFinalBudgetForThisProposal();
        if(finalBudget==null){
            List<BudgetDocumentVersion> budgetDocumentVersions = proposalDevelopmentDocument.getBudgetDocumentVersions();
            BudgetVersionOverview budgetVersionOverview = null;
            for (BudgetDocumentVersion budgetDocumentVersion : budgetDocumentVersions) {
                if(budgetDocumentVersion.isBudgetComplete()){
                    budgetVersionOverview = budgetDocumentVersion.getBudgetVersionOverview();
                    return budgetDocumentVersion.findBudget();
                }
            }
            if(!budgetDocumentVersions.isEmpty()){
                finalBudget = budgetDocumentVersions.get(0).findBudget();
            }
        }
        return finalBudget;
        
    }


    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }


}
