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
package org.kuali.coeus.common.impl.person;


import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.impl.identity.PersonImpl;
import org.kuali.rice.krad.lookup.LookupForm;
import org.kuali.rice.krad.lookup.LookupableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("kcPersonLookupable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class KcPersonLookupable extends LookupableImpl {

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Override
    public List<?> performSearch(LookupForm form, Map<String, String> searchCriteria, boolean unbounded) {
        getKcPersonService().modifyFieldValues(searchCriteria);
        convertToPersonImpl(form.getViewPostMetadata().getLookupCriteria());
        List<Person> personResults = (List<Person>) super.performSearch(form, searchCriteria, unbounded);
        convertToKcPerson(form.getViewPostMetadata().getLookupCriteria(),searchCriteria);
        return getKcPersonService().createKcPersonsFromPeople(personResults);

    }

    protected void convertToPersonImpl(Map<String,Map<String,Object>> lookupCriteria) {
        lookupCriteria.put("principalName",lookupCriteria.get("userName"));
        lookupCriteria.put("principalId",lookupCriteria.get("personId"));
        lookupCriteria.put("phoneNumber",lookupCriteria.get("officePhone"));
        lookupCriteria.put("primaryDepartmentCode",lookupCriteria.get("organizationIdentifier"));
        this.setDataObjectClass(PersonImpl.class);
    }

    protected void convertToKcPerson(Map<String,Map<String,Object>> lookupCriteria, Map<String,String> searchCriteria) {
        lookupCriteria.remove("principalName");
        lookupCriteria.remove("principalId");
        lookupCriteria.remove("phoneNumber");
        lookupCriteria.remove("primaryDepartmentCode");
        searchCriteria.clear();
        this.setDataObjectClass(KcPerson.class);
    }

    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
