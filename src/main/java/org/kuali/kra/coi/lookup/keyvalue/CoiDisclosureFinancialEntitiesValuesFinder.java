/*
 * Copyright 2005-2010 The Kuali Foundation
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

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.personfinancialentity.FinancialEntityService;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.util.KeyLabelPair;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.util.GlobalVariables;

public class CoiDisclosureFinancialEntitiesValuesFinder extends KeyValuesBase {


    @Override
    public List getKeyValues() {
        List<KeyLabelPair> keyLabels = new ArrayList<KeyLabelPair>();
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) GlobalVariables.getKualiForm();
        keyLabels.add(new KeyLabelPair("", "select"));
        List<PersonFinIntDisclosure> financialEntities = getAllFinancialEntities();
        for (PersonFinIntDisclosure fe : financialEntities) {
            keyLabels.add(new KeyLabelPair(fe.getEntityNumber(), fe.getEntityName()));
        }
        return keyLabels;
    }

    public List<PersonFinIntDisclosure> getAllFinancialEntities() {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        List<PersonFinIntDisclosure> finEntities = getFinancialEntityService().getFinancialEntities(userId, true);
        return finEntities;
    }
    
    public FinancialEntityService getFinancialEntityService() {
        return KraServiceLocator.getService(FinancialEntityService.class);
    }

}
