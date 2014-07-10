/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.coi;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.disclosure.DisclosurePerson;
import org.kuali.kra.coi.disclosure.DisclosurePersonUnit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.KcRulesEngineExecuter;
import org.kuali.kra.krms.service.KcKrmsFactBuilderService;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.krms.api.engine.Engine;
import org.kuali.rice.krms.api.engine.EngineResults;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.SelectionCriteria;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CoiDisclosureRulesEngineExecutorImpl  extends KcRulesEngineExecuter {
    
    public EngineResults performExecute(RouteContext routeContext, Engine engine) {
        Map<String, String> contextQualifiers = new HashMap<String, String>();
        contextQualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_COIDISCLOSURE);
        contextQualifiers.put("name", KcKrmsConstants.CoiDisclosure.COI_DISCLOSURE_CONTEXT);

        // extract facts from routeContext
        String docContent = routeContext.getDocument().getDocContent();
        String unitNumber = getElementValue(docContent, "//document/coiDisclosureList[1]/" + CoiDisclosure.class.getName() + "[1]/disclosurePersons[1]/" + DisclosurePerson.class.getName() + "[1]/disclosurePersonUnits[1]/" + DisclosurePersonUnit.class.getName() + "[1]/unitNumber[1]");
        
        SelectionCriteria selectionCriteria = SelectionCriteria.createCriteria(null, contextQualifiers,
                Collections.singletonMap("Unit Number", unitNumber));

        KcKrmsFactBuilderService fbService = KcServiceLocator.getService("coiDisclosureFactBuilderService");
        Facts.Builder factsBuilder = Facts.Builder.create();
        fbService.addFacts(factsBuilder, docContent);
        EngineResults results = engine.execute(selectionCriteria, factsBuilder.build(), null);
        return results;
    }
}
