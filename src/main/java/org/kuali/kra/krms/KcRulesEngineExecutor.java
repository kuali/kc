/**
 * Copyright 2005-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.krms;

import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.framework.support.krms.RulesEngineExecutor;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krms.api.KrmsApiServiceLocator;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple sample RulesEngineExecutor usable in the sample app which is hard-coded to select a context with
 * namespaceCode="KR-SAP" and name="Travel Account ".  It also is hardcoded to select an agenda from the context with an
 * event name of "workflow".
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class KcRulesEngineExecutor implements RulesEngineExecutor {

    private static final String CONTEXT_NAMESPACE_CODE = "KC-PD";
    private static final String CONTEXT_NAME = "Proposal Development Routing";

    @Override
    public EngineResults execute(RouteContext routeContext, Engine engine) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        contextQualifiers.put("namespaceCode", CONTEXT_NAMESPACE_CODE);
        contextQualifiers.put("name", CONTEXT_NAME);

        // extract facts from routeContext
        String docContent = routeContext.getDocument().getDocContent();
        
        String unitNumber = getElementValue(docContent, "//ownedByUnitNumber");
        String sponsorCode = getElementValue(docContent, "//sponsorCode");
        
        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers,
                Collections.singletonMap("unitNumber", unitNumber));

        Facts.Builder factsBuilder = Facts.Builder.create();

        factsBuilder.addFact("Sponsor Code", sponsorCode);

        EngineResults results = engine.execute(selectionCriteria, factsBuilder.build(), null);
        return results;
    }

    private String getElementValue(String docContent, String xpathExpression) {
        try {
            Document document = XmlHelper.trimXml(new ByteArrayInputStream(docContent.getBytes()));

            XPath xpath = XPathHelper.newXPath();
            String value = (String)xpath.evaluate(xpathExpression, document, XPathConstants.STRING);

            return value;

        } catch (Exception e) {
            throw new RiceRuntimeException();
        }
    }

//    public static void main(String [] args) throws Exception {
//
//        String sampleDocContent = FileUtils.readFileToString(new File("/home/gilesp/tmp/SampleDocContent.txt"));
//
//        RouteContext rc = new RouteContext();
//        DocumentRouteHeaderValue document = new DocumentRouteHeaderValue();
//        DocumentRouteHeaderValueContent content = new DocumentRouteHeaderValueContent();
//        content.setDocumentContent(sampleDocContent);
//        document.setDocumentContent(content);
//        rc.setDocument(document);
//
//        new TravelAccountRulesEngineExecutor().execute(rc, null);
//    }
}
