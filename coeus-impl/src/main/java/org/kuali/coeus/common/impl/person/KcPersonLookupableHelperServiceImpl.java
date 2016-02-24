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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.common.framework.multicampus.MultiCampusConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.impl.identity.PersonImpl;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.location.impl.campus.CampusBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Lookup helper that retrieves KcPerson BOs.
 */
@Component("kcPersonLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Lazy
public class KcPersonLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {
    
    private static final long serialVersionUID = 1L;
    
    private static final String CAMPUS_CODE_FIELD = "code";
    private static final String PERSON_CAMPUS_CODE_FIELD = "campusCode";
    private static final String CAMPUS_LOOKUPABLE_CLASS_NAME = CampusBo.class.getName();

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;
    
    @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        
        boolean multiCampusEnabled = getParameterService().getParameterValueAsBoolean(
            Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, MultiCampusConstants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(PERSON_CAMPUS_CODE_FIELD)) {
                    field.setFieldConversions(CAMPUS_CODE_FIELD + Constants.COLON + field.getPropertyName());
                    field.setLookupParameters(field.getPropertyName() + Constants.COLON + CAMPUS_CODE_FIELD);
                    field.setInquiryParameters(field.getPropertyName() + Constants.COLON + CAMPUS_CODE_FIELD);
                    field.setQuickFinderClassNameImpl(CAMPUS_LOOKUPABLE_CLASS_NAME);
                    field.setFieldDirectInquiryEnabled(true);
                    if (multiCampusEnabled) {
                        if (StringUtils.isBlank(field.getDefaultValue())) {
                            String campusCode = (String) GlobalVariables.getUserSession().retrieveObject(MultiCampusConstants.USER_CAMPUS_CODE_KEY);
                            field.setDefaultValue(campusCode);
                            field.setPropertyValue(field.getDefaultValue());
                        }
                    }
                }
            }
        }
        
        return rows;
    }

    @Override
    public List<KcPerson> getSearchResults(Map<String, String> fieldValues) {
        this.kcPersonService.modifyFieldValues(fieldValues);
        this.businessObjectClass = PersonImpl.class;
        List<Person> personResults = (List<Person>) super.getSearchResults(fieldValues);
        this.businessObjectClass = KcPerson.class;
        return this.kcPersonService.createKcPersonsFromPeople(personResults); 
    }

    /**
     * Sets the Kc Person Service.
     * @param kcPersonService the Kc person Service.
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
    
}
