/*
 * Copyright 2008 The Kuali Foundation.
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.generator.S2SBaseFormGenerator;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This abstract class has methods that are common to all the versions of RRSubAwardBudget form.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public abstract class RRSubAwardBudgetBaseGenerator extends S2SBaseFormGenerator {

    protected static final String NAMESPACE_URI = "http://apply.grants.gov/forms/RR_Budget-V1.0";
    protected static final String LOCAL_NAME = "RR_Budget";
    protected static final String ERROR1_PROPERTY_KEY = "s2sSubawardBudgetV1-2_10000";
    protected static final String ERROR2_PROPERTY_KEY = "s2sSubawardBudget_10002";
    
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
        attachmentName.append(budgetSubAwards.getOrganizationName());
        attachmentName.append(budgetSubAwards.getProposalNumber());
        attachmentName.append(budgetSubAwards.getVersionNumber());
        attachmentName.append(budgetSubAwards.getSubAwardNumber());
        return attachmentName.toString();
    }
}
