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
package org.kuali.coeus.common.view.wizard.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.person.PersonTypeConstants;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.view.wizard.framework.WizardResultsDto;
import org.kuali.rice.core.api.criteria.*;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.impl.role.RoleBo;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.util.KRADConstants;
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
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

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
        searchCriteria.put("active","Y");
        searchCriteria.remove("officePhone");
        List<Person> persons = getPersonService().findPeople(filterCriteria(searchCriteria),false);
        searchCriteria.put("officePhone",searchCriteria.get("phoneNumber"));
        searchCriteria.remove("phoneNumber");
        List<KcPerson> kcPersons = getKcPersonService().createKcPersonsFromPeople(persons);
        for (KcPerson person: kcPersons) {
            WizardResultsDto result = new WizardResultsDto();
            result.setKcPerson(person);
            results.add(result);
        }
        return results;
    }

    protected List<Object> prepareRolodexResults(Map<String,String> searchCriteria) {
        List<Object> results = new ArrayList<Object>();
        searchCriteria.put("active","Y");


        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        builder.setPredicates(PredicateFactory.and(
                PredicateFactory.isNotNull("firstName"),
                PredicateFactory.isNotNull("lastName")),
                PredicateUtils.convertMapToPredicate(filterCriteria(searchCriteria)));
        builder.setMaxResults(Integer.parseInt(getParameterService().getParameterValueAsString(KRADConstants.KRAD_NAMESPACE,
                KRADConstants.DetailTypes.LOOKUP_PARM_DETAIL_TYPE,
                KRADConstants.SystemGroupParameterNames.LOOKUP_RESULTS_LIMIT)));
        builder.setOrderByFields(OrderByField.Builder.create("lastName", OrderDirection.ASCENDING).build());

        Collection<Rolodex> rolodexes = getDataObjectService().findMatching(Rolodex.class, builder.build()).getResults();

       for (Rolodex rolodex : rolodexes) {
                WizardResultsDto result = new WizardResultsDto();
                result.setRolodex(rolodex);
                results.add(result);

        }
        return results;
    }

    protected List<Object> prepareRoleResults(Map<String,String> searchCriteria) {
        List<Object> results = new ArrayList<Object>();
        searchCriteria.put("active","Y");
        QueryByCriteria.Builder builder = QueryByCriteria.Builder.create();
        builder.setPredicates(PredicateUtils.convertMapToPredicate(filterCriteria(searchCriteria)));
        builder.setMaxResults(Integer.parseInt(getParameterService().getParameterValueAsString(KRADConstants.KRAD_NAMESPACE,
                KRADConstants.DetailTypes.LOOKUP_PARM_DETAIL_TYPE,
                KRADConstants.SystemGroupParameterNames.LOOKUP_RESULTS_LIMIT)));
        Collection<Role> roles = getRoleService().findRoles(builder.build()).getResults();
        for (Role role : roles) {
            WizardResultsDto result = new WizardResultsDto();
            result.setRole(RoleBo.from(role));
            results.add(result);
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

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
