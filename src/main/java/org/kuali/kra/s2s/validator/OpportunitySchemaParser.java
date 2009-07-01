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

package org.kuali.kra.s2s.validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.formmapping.FormMappingInfo;
import org.kuali.kra.s2s.formmapping.FormMappingLoader;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;
import org.kuali.kra.s2s.util.S2SConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * This class fetches an XSD and parses it, and returns back mandatory and optional forms for a particular opportunity
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 * 
 */
public class OpportunitySchemaParser {

    private static final String XSD_NS = "http://www.w3.org/2001/XMLSchema";
    private static final Logger LOG = Logger.getLogger(OpportunitySchemaParser.class);

    /**
     * This method fetches all the forms required from a given schema of opportunity
     * 
     * @param schema {@link String}
     * @return {@link HashMap} containing all form information
     */
    public ArrayList<S2sOppForms> getForms(String schema) {
        boolean mandatory;
        boolean available;
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = null;
        ArrayList<S2sOppForms> schemaList = new ArrayList<S2sOppForms>();
        Document document;
        try {
            builder = domFactory.newDocumentBuilder();
            document = builder.parse(schema);
        }
        catch (ParserConfigurationException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            return schemaList;
        }
        catch (SAXException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            return schemaList;
        }
        catch (IOException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            return schemaList;
        }

        Element schemaElement = document.getDocumentElement();
        //FIXME Use constants instead literals throughout the method
        NodeList importList = document.getElementsByTagNameNS(XSD_NS, "import");
        Node allForms = document.getElementsByTagNameNS(XSD_NS, "all").item(0);
        NodeList formsList = ((Element) allForms).getElementsByTagNameNS(XSD_NS, "element");
        String[] formNames = new String[formsList.getLength()];

        for (int formIndex = 0; formIndex < formsList.getLength(); formIndex++) {
            Node form = formsList.item(formIndex);
            String fullFormName = ((Element) form).getAttribute("ref");
            String formName = fullFormName.substring(0, fullFormName.indexOf(':'));
            String minOccurs = ((Element) form).getAttribute("minOccurs");
            String nameSpace = schemaElement.getAttribute("xmlns:" + formName);
            formNames[formIndex] = nameSpace;
            for (int impIndex = 0; impIndex < importList.getLength(); impIndex++) {
                Node importNode = importList.item(impIndex);
                if (((Element) importNode).getAttribute("namespace").equalsIgnoreCase(nameSpace)) {
                    String schemaUrl = ((Element) importNode).getAttribute("schemaLocation");
                    S2sOppForms oppForm = new S2sOppForms();
                    oppForm.setFormName(formName);
                    oppForm.setOppNameSpace(nameSpace);
                    oppForm.setSchemaUrl(schemaUrl);
                    mandatory = (minOccurs == null || minOccurs.trim().equals("") || Integer.parseInt(minOccurs) > 0);
                    oppForm.setMandatory(mandatory);
                    available = isAvailable(nameSpace);
                    oppForm.setAvailable(available);
                    oppForm.setInclude(mandatory && available);
                    schemaList.add(oppForm);
                }
            }
        }
        return schemaList;
    }

    /**
     * 
     * Method determines whether a particular form name is available for submission
     * 
     * @param nameSpace String
     * @return boolean availability status
     */
    private boolean isAvailable(String nameSpace) {       
        try {
            FormMappingInfo info = new FormMappingLoader().getFormInfo(nameSpace);
            return (info != null);
        }
        catch (S2SGeneratorNotFoundException e) {
            // Form cannot be generated because generator class doesn't exist.
            return false;
        }
    }
}