/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.kuali.coeus.propdev.impl.s2s.connect;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.s2s.S2sOppForms.S2sOppFormsId;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * This class fetches an XSD and parses it, and returns back mandatory and optional forms for a particular opportunity
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 * 
 */
@Component("opportunitySchemaParserService")
public class OpportunitySchemaParserServiceImpl implements OpportunitySchemaParserService {

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

    @Autowired
    @Qualifier("formMappingService")
    private FormMappingService formMappingService;

    /**
     * This method fetches all the forms required from a given schema of opportunity
     * 
     * @param schema {@link String}
     * @return {@link HashMap} containing all form information
     */
    @Override
    public List<S2sOppForms> getForms(String proposalNumber,String schema) throws S2sCommunicationException {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        if (StringUtils.isBlank(schema)) {
            throw new IllegalArgumentException("schema is blank");
        }

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
            throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_FORM_PARSING, e.getMessage(), schema);
        }catch (SAXException e) {
            throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_FORM_PARSING_SAXEXCEPTION, e.getMessage(), schema);
        }catch (IOException e) {
            throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_FORM_SCHEMA_NOT_FOUND, e.getMessage(), schema);
        }
        
        Element schemaElement = document.getDocumentElement();
        NodeList importList = document.getElementsByTagNameNS(XSD_NS, IMPORT);
        Node allForms = document.getElementsByTagNameNS(XSD_NS, ALL).item(0);
        
        // If there is no "Forms" element, it's probably because this is an NIH complex project
        if(allForms == null) {
            Node topElementName = schemaElement.getElementsByTagNameNS(XSD_NS, ELEMENT).item(0).getAttributes().item(0);
            if(topElementName.getNodeName().equals("name") && !topElementName.getNodeValue().equals("GrantApplication")) {
                throw new S2sCommunicationException(KeyConstants.ERROR_GRANTSGOV_NO_FORM_ELEMENT, "", "");
            }
        }
        
        NodeList formsList = ((Element) allForms).getElementsByTagNameNS(XSD_NS, ELEMENT);
        String[] formNames = new String[formsList.getLength()];

        for (int formIndex = 0; formIndex < formsList.getLength(); formIndex++) {
            Node form = formsList.item(formIndex);
            String fullFormName = ((Element) form).getAttribute(REF);
            String formName = fullFormName.substring(0, fullFormName.indexOf(CH_COLON));
            String minOccurs = ((Element) form).getAttribute(MIN_OCCURS);
            String nameSpace = schemaElement.getAttribute(XMLNS + formName);
            FormMappingInfo info = formMappingService.getFormInfo(nameSpace, proposalNumber);
            String displayFormName = info==null?formName:info.getFormName();
            formNames[formIndex] = nameSpace;
            for (int impIndex = 0; impIndex < importList.getLength(); impIndex++) {
                Node importNode = importList.item(impIndex);
                if (((Element) importNode).getAttribute(NAMESPACE).equalsIgnoreCase(nameSpace)) {
                    String schemaUrl = ((Element) importNode).getAttribute(SCHEMA_LOCATION);
                    S2sOppForms oppForm = new S2sOppForms();
                    oppForm.setFormName(displayFormName);
                    S2sOppFormsId oppFormId = new S2sOppFormsId();
                    oppFormId.setProposalNumber(proposalNumber);
                    oppFormId.setOppNameSpace(nameSpace);
                    oppForm.setS2sOppFormsId(oppFormId);
                    oppForm.getS2sOppFormsId().setOppNameSpace(nameSpace);
                    oppForm.setSchemaUrl(schemaUrl);
                    mandatory = (minOccurs == null || minOccurs.trim().equals("") || Integer.parseInt(minOccurs) > 0);
                    oppForm.setMandatory(mandatory);
                    if(info!=null){
                        available=true;
                        oppForm.setUserAttachedForm(info.getUserAttachedForm());
                    }else{
                        available=false;
                    }
                    oppForm.setAvailable(available);
                    oppForm.setInclude(mandatory && available);
                    schemaList.add(oppForm);
                }
            }
        }
        return schemaList;
    }
}
