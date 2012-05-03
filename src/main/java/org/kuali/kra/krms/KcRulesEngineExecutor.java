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

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.framework.support.krms.RulesEngineExecutor;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;
import org.w3c.dom.Document;

public class KcRulesEngineExecutor implements RulesEngineExecutor {

    public EngineResults execute(RouteContext routeContext, Engine engine) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        contextQualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT);
        contextQualifiers.put("name", KcKrmsConstants.ProposalDevelopment.PROPOSAL_DEVELOPMENT_CONTEXT);

        // extract facts from routeContext
        String docContent = routeContext.getDocument().getDocContent();
        
        // TODO Need logic in here to only add these from the final, complete budget version (if it exists)
        String unitNumber = getElementValue(docContent, "//ownedByUnitNumber");
        String totalCost = getElementValue(docContent, "//totalCost");
        String totalDirectCost = getElementValue(docContent, "//totalDirectCost");
        String totalIndirectCost = getElementValue(docContent, "//totalIndirectCost");
        String costShareAmount = getElementValue(docContent, "//costShareAmount");
        String underrecoveryAmount = getElementValue(docContent, "//underrecoveryAmount");
        String totalCostInitial = getElementValue(docContent, "//totalCostInitial");
        String totalDirectCostLimit = getElementValue(docContent, "//totalDirectCostLimit");
        String cfdaNumber = getElementValue(docContent, "//cfdaNumber");
        String opportunityId = getElementValue(docContent, "//opportunityId");
        
        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers,
                Collections.singletonMap("Unit Number", unitNumber));

        Facts.Builder factsBuilder = Facts.Builder.create();
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_COST, totalCost);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST, totalDirectCost);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_INDIRECT_COST, totalIndirectCost);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.COST_SHARE_AMOUNT, costShareAmount);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.UNDERRECOVERY_AMOUNT, underrecoveryAmount);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_COST_INITIAL, totalCostInitial);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.TOTAL_DIRECT_COST_LIMIT, totalDirectCostLimit);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.CFDA_NUMBER, cfdaNumber);
        factsBuilder.addFact(KcKrmsConstants.ProposalDevelopment.OPPORTUNITY_ID, opportunityId);

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
}
