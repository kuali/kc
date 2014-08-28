package org.kuali.coeus.common.view.wizard.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.PersonTypeConstants;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.view.wizard.framework.WizardResultsDto;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.impl.role.RoleBo;
import org.kuali.rice.krad.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("wizardControllerService")
public class WizardControllerServiceImpl implements WizardControllerService {

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("lookupService")
    private LookupService lookupService;

    @Override
    public List<Object> performWizardSearch(Map<String,String> searchCriteria, String lineType){
        if (StringUtils.equals(lineType, PersonTypeConstants.EMPLOYEE.getCode())) {
            return preparePersonResults(searchCriteria);
        } else if(StringUtils.equals(lineType, PersonTypeConstants.NONEMPLOYEE.getCode())) {
            return prepareRolodexResults(searchCriteria);
        } else {
            return prepareRoleResults(searchCriteria);
        }
    }

    protected List<Object> preparePersonResults(Map<String,String> searchCriteria) {
        List<Object> results = new ArrayList<Object>();
        getKcPersonService().modifyFieldValues(searchCriteria);
        List<Person> persons = getPersonService().findPeople(filterCriteria(searchCriteria));
        List<KcPerson> kcPersons = getKcPersonService().createKcPersonsFromPeople(persons);
        for (KcPerson person: kcPersons) {
            WizardResultsDto wizardResult = new WizardResultsDto();
            wizardResult.addParameter("resultClass",KcPerson.class.getName());
            wizardResult.addParameter("personId",person.getPersonId());
            wizardResult.addParameter("personName", person.getFullName());
            wizardResult.addParameter("userName", person.getUserName());
            wizardResult.addParameter("emailAddress", person.getEmailAddress());
            if (person.getUnit() != null) {
                wizardResult.addParameter("unitNumber",person.getUnit().getUnitNumber());
                wizardResult.addParameter("unitName",person.getUnit().getUnitName());
            }
            wizardResult.addParameter("organization",person.getContactOrganizationName());
            wizardResult.addParameter("city",person.getCity());

            results.add(wizardResult);
        }
        return results;
    }

    protected List<Object> prepareRolodexResults(Map<String,String> searchCriteria) {
        List<Object> results = new ArrayList<Object>();
        Collection<Rolodex> rolodexes = getLookupService().findCollectionBySearchHelper(Rolodex.class, filterCriteria(searchCriteria), Collections.EMPTY_LIST, false, 100);
        for (Rolodex rolodex : rolodexes) {
            WizardResultsDto wizardResult = new WizardResultsDto();
            wizardResult.addParameter("resultClass",Rolodex.class.getName());
            wizardResult.addParameter("rolodexId",rolodex.getRolodexId());
            wizardResult.addParameter("fullName",rolodex.getFullName());
            results.add(wizardResult);
        }
        return results;
    }

    protected List<Object> prepareRoleResults(Map<String,String> searchCriteria) {
        List<Object> results = new ArrayList<Object>();
        Collection<Role> roles = getRoleService().findRoles(QueryByCriteria.Builder.andAttributes(filterCriteria(searchCriteria)).build()).getResults();
        for (Role role : roles) {
            WizardResultsDto wizardResult = new WizardResultsDto();
            wizardResult.addParameter("resultClass",Role.class.getName());
            wizardResult.addParameter("name",role.getName());
            wizardResult.addParameter("description",role.getDescription());
            wizardResult.addParameter("namespaceCode",role.getNamespaceCode());
            wizardResult.addParameter("kimTypeId",role.getKimTypeId());
            wizardResult.addParameter("kimTypeName", RoleBo.from(role).getKimRoleType().getName());
            results.add(wizardResult);
        }
        return results;
    }

    private Map<String,String> filterCriteria(Map<String,String> lookupCriteria) {
        Map<String,String> filteredCriteria = new HashMap<String,String>();
        for (Map.Entry<String,String> entry : lookupCriteria.entrySet()) {
            if (StringUtils.isNotEmpty(entry.getValue())) {
                filteredCriteria.put(entry.getKey(),entry.getValue());
            }
        }
        return filteredCriteria;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public LookupService getLookupService() {
        return lookupService;
    }

    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }
}
