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

import java.util.Collection;
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
