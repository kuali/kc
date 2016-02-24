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
