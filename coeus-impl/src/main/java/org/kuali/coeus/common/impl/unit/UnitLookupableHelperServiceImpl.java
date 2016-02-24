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
package org.kuali.coeus.common.impl.unit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.auth.UnitAuthorizationService;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.coeus.common.framework.multicampus.MultiCampusConstants;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.location.impl.campus.CampusBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Unit lookup that accounts for the extra parameter {@code campusCode} and filters the search results if it is defined.
 */
@Component("unitLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UnitLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = -3661085880649722426L;
    
    private static final String CAMPUS_CODE_FIELD = "code";
    private static final String CAMPUS_LOOKUPABLE_CLASS_NAME = CampusBo.class.getName();

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("unitAuthorizationService")
    private UnitAuthorizationService unitAuthorizationService;
    
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        String personId = getKcPersonService().getKcPersonByPersonId(GlobalVariables.getUserSession().getPerson().getPrincipalId()).getPersonId();
        boolean hasModifyPermission = getUnitAuthorizationService().hasPermission(personId, "KC-UNT", PermissionConstants.MODIFY_UNIT);
        if (hasModifyPermission) {
            AnchorHtmlData editHtmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_EDIT_METHOD_TO_CALL, pkNames);
            htmlDataList.add(editHtmlData);

            AnchorHtmlData copyHtmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames);
            htmlDataList.add(copyHtmlData);

            AnchorHtmlData deleteHtmlData = getUrlData(businessObject, KRADConstants.MAINTENANCE_DELETE_METHOD_TO_CALL, pkNames);
            htmlDataList.add(deleteHtmlData);
        }
        return htmlDataList;
    }
    
    @Override
    public List<Row> getRows() {
        List<Row> rows = super.getRows();
        
        boolean multiCampusEnabled = getParameterService().getParameterValueAsBoolean(
            Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, MultiCampusConstants.PARAMETER_MULTI_CAMPUS_ENABLED);
        
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(CAMPUS_CODE_FIELD)) {
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
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        String campusCode = fieldValues.remove(CAMPUS_CODE_FIELD);
        List<Unit> searchResults = (List<Unit>) super.getSearchResults(fieldValues);

        List<Unit> filteredSearchResults = CollectionUtils.createCorrectImplementationForCollection(searchResults);
        filteredSearchResults.addAll(searchResults.stream().filter(unit -> StringUtils.startsWith(unit.getUnitNumber(), campusCode)).collect(Collectors.toList()));

        return filteredSearchResults;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public UnitAuthorizationService getUnitAuthorizationService() {
        return unitAuthorizationService;
    }

    public void setUnitAuthorizationService(UnitAuthorizationService unitAuthorizationService) {
        this.unitAuthorizationService = unitAuthorizationService;
    }

}
