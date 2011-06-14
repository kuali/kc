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

package org.kuali.kra.s2s.validator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.s2s.S2SException;
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

    private static final String SCHEMA_LOCATION = "schemaLocation";
    private static final String NAMESPACE = "namespace";
    private static final char CH_COLON = ':';
    private static final String XMLNS = "xmlns:";
    private static final String MIN_OCCURS = "minOccurs";
    private static final String REF = "ref";
    private static final String ELEMENT = "element";
    private static final String ALL = "all";
    private static final String IMPORT = "import";
    private static final String XSD_NS = "http://www.w3.org/2001/XMLSchema";
    private static final Log LOG = LogFactory.getLog(OpportunitySchemaParser.class);

    /**
     * This method fetches all the forms required from a given schema of opportunity
     * 
     * @param schema {@link String}
     * @return {@link HashMap} containing all form information
     */
    public ArrayList<S2sOppForms> getForms(String schema) throws S2SException{
        boolean mandatory;
        boolean available;
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = null;
        ArrayList<S2sOppForms> schemaList = new ArrayList<S2sOppForms>();
        Document document;
        try {
            builder = domFactory.newDocumentBuilder();
            InputStream is = (InputStream)new URL(schema).getContent();
            document = builder.parse(is);
        }catch (ParserConfigurationException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            throw new S2SException(KeyConstants.ERROR_GRANTSGOV_FORM_PARSING,e.getMessage(),schema);
        }catch (SAXException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            throw new S2SException(KeyConstants.ERROR_GRANTSGOV_FORM_PARSING_SAXEXCEPTION,e.getMessage(),schema);
        }catch (IOException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            throw new S2SException(KeyConstants.ERROR_GRANTSGOV_FORM_SCHEMA_NOT_FOUND,e.getMessage(),schema);
        }

        Element schemaElement = document.getDocumentElement();
        NodeList importList = document.getElementsByTagNameNS(XSD_NS, IMPORT);
        Node allForms = document.getElementsByTagNameNS(XSD_NS, ALL).item(0);
        NodeList formsList = ((Element) allForms).getElementsByTagNameNS(XSD_NS, ELEMENT);
        String[] formNames = new String[formsList.getLength()];

        for (int formIndex = 0; formIndex < formsList.getLength(); formIndex++) {
            Node form = formsList.item(formIndex);
            String fullFormName = ((Element) form).getAttribute(REF);
            String formName = fullFormName.substring(0, fullFormName.indexOf(CH_COLON));
            String minOccurs = ((Element) form).getAttribute(MIN_OCCURS);
            String nameSpace = schemaElement.getAttribute(XMLNS + formName);
            FormMappingInfo info = null;
            try {
                info = new FormMappingLoader().getFormInfo(nameSpace);
            }catch (S2SGeneratorNotFoundException e) {
            }
            String displayFormName = info==null?formName:info.getFormName();
            formNames[formIndex] = nameSpace;
            for (int impIndex = 0; impIndex < importList.getLength(); impIndex++) {
                Node importNode = importList.item(impIndex);
                if (((Element) importNode).getAttribute(NAMESPACE).equalsIgnoreCase(nameSpace)) {
                    String schemaUrl = ((Element) importNode).getAttribute(SCHEMA_LOCATION);
                    S2sOppForms oppForm = new S2sOppForms();
                    oppForm.setFormName(displayFormName);
                    oppForm.setOppNameSpace(nameSpace);
                    oppForm.setSchemaUrl(schemaUrl);
                    mandatory = (minOccurs == null || minOccurs.trim().equals("") || Integer.parseInt(minOccurs) > 0);
                    oppForm.setMandatory(mandatory);
                    available = info!=null;//isAvailable(nameSpace);
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
