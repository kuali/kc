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
package org.kuali.kra.coi.lookup.keyvalue;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class CoiDisclosureFinancialEntitiesValuesFinder extends FormViewAwareUifKeyValuesFinderBase {


    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) getDocument();
        String personId = coiDisclosureDocument.getCoiDisclosure().getDisclosureReporter().getPersonId();
        keyLabels.add(new ConcreteKeyValue("", "select"));
        List<PersonFinIntDisclosure> financialEntities = getAllFinancialEntities(personId);
        for (PersonFinIntDisclosure fe : financialEntities) {
            keyLabels.add(new ConcreteKeyValue(fe.getPersonFinIntDisclosureId().toString(), fe.getEntityName()));
        }
        return keyLabels;
    }

    public List<PersonFinIntDisclosure> getAllFinancialEntities(String userId) {
        List<PersonFinIntDisclosure> finEntities = getFinancialEntityService().getFinancialEntities(userId, true);
        return finEntities;
    }
    
    public FinancialEntityService getFinancialEntityService() {
        return KcServiceLocator.getService(FinancialEntityService.class);
    }

}
