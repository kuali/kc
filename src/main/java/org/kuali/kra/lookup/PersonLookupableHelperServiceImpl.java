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
package org.kuali.kra.lookup;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.MultiCampusIdentityService;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Overrides the normal {@link org.kuali.rice.kim.lookup.PersonLookupableHelperServiceImpl} to add inquiry and lookup parameters to certain fields.
 */
public class PersonLookupableHelperServiceImpl extends org.kuali.rice.kim.lookup.PersonLookupableHelperServiceImpl {

    private static final long serialVersionUID = 5378644476985169785L;

    private static final String PRINCIPAL_NAME_FIELD = "principalName";
    
    private static final String CAMPUS_CODE_FIELD = "campusCode";
    private static final String CAMPUS_LOOKUPABLE_CLASS_NAME = "org.kuali.rice.location.impl.campus.CampusBo";
    
    private static final String PRIMARY_DEPARTMENT_CODE_FIELD = "primaryDepartmentCode";
    private static final String UNIT_NUMBER_FIELD = "unitNumber";
    private static final String UNIT_LOOKUPABLE_CLASS_NAME = "org.kuali.kra.bo.Unit";
    
    private MultiCampusIdentityService multiCampusIdentityService;
    
    @Override
    public List<Row> getRows() {
        boolean multiCampusEnabled = getParameterService().getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(PRIMARY_DEPARTMENT_CODE_FIELD)) {
                    field.setFieldConversions(UNIT_NUMBER_FIELD + Constants.COLON + field.getPropertyName());
                    field.setInquiryParameters(field.getPropertyName() + Constants.COLON + UNIT_NUMBER_FIELD);
                    field.setQuickFinderClassNameImpl(UNIT_LOOKUPABLE_CLASS_NAME);
                    field.setFieldDirectInquiryEnabled(true);
                } else if (field.getPropertyName().equals(CAMPUS_CODE_FIELD)) {
                    field.setFieldConversions(CAMPUS_CODE_FIELD + Constants.COLON + field.getPropertyName());
                    field.setLookupParameters(field.getPropertyName() + Constants.COLON + CAMPUS_CODE_FIELD);
                    field.setInquiryParameters(field.getPropertyName() + Constants.COLON + CAMPUS_CODE_FIELD);
                    field.setQuickFinderClassNameImpl(CAMPUS_LOOKUPABLE_CLASS_NAME);
                    field.setFieldDirectInquiryEnabled(true);
                    if (multiCampusEnabled) {
                        if (StringUtils.isBlank(field.getDefaultValue())) {
                            String campusCode = (String) GlobalVariables.getUserSession().retrieveObject(Constants.USER_CAMPUS_CODE_KEY);
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
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        boolean multiCampusEnabled = getParameterService().getParameterValueAsBoolean(
                Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, Constants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        if (multiCampusEnabled) {
            if (StringUtils.isNotBlank(fieldValues.get(PRINCIPAL_NAME_FIELD))) {
                String principalName = fieldValues.get(PRINCIPAL_NAME_FIELD);
                String campusCode = fieldValues.get(CAMPUS_CODE_FIELD);
                String multiCampusPrincipalName = getMultiCampusIdentityService().getMultiCampusPrincipalName(principalName, campusCode);
                fieldValues.put(PRINCIPAL_NAME_FIELD, multiCampusPrincipalName);
            }
        }
        
        return super.getSearchResults(fieldValues);
    }

    public MultiCampusIdentityService getMultiCampusIdentityService() {
        if (multiCampusIdentityService == null) {
            multiCampusIdentityService = KraServiceLocator.getService(MultiCampusIdentityService.class);
        }
        return multiCampusIdentityService;
    }

    public void setMultiCampusIdentityService(MultiCampusIdentityService multiCampusIdentityService) {
        this.multiCampusIdentityService = multiCampusIdentityService;
    }
   
}