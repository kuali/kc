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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.lookup.keyvalue.LookupReturnValuesFinder;
import org.kuali.rice.kns.lookup.KualiLookupableImpl;
import org.kuali.rice.kns.lookup.LookupableHelperService;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.lookup.LookupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * Implements Question specific methods necessary to render the lookup and provides 
 * search and return methods.
 */
@Component("questionLookupable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QuestionLookupableImpl extends KualiLookupableImpl {

    private static final long serialVersionUID = -5431630475561370731L;

    private static final String MAINTENANCE = "maintenance";
    private static final String NEW_MAINTENANCE = "../maintenanceQ";
    private static final String LOOKUP_RETURN ="lookupReturn";
    private static final String LOOKUP_CLASS ="lookupClass";

    @Autowired
    @Qualifier("questionAuthorizationService")
    private transient QuestionAuthorizationService questionAuthorizationService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    /**
     * Only create the URL to create a new Question when proper permission is given.
     * @see org.kuali.rice.kns.lookup.KualiLookupableImpl#getCreateNewUrl()
     */
    @Override
    public String getCreateNewUrl() {
        String url = "";
        if (questionAuthorizationService.hasPermission(PermissionConstants.MODIFY_QUESTION)) {
            url =  super.getCreateNewUrl();
            url = url.replace(MAINTENANCE, NEW_MAINTENANCE);
        }
        return url;
    }
    
    /**
     * This is to force to reload the lookupreturn dropdown list for the lookupform. It's not pretty. The
     * GlovalVaribles.getKualiForm() is not helping because the 'fields' on lookupForm is not set until all fields are set. So, the
     * lookupreturnvaluesfinder can't take advantage of that.
     */
    public boolean checkForAdditionalFields(Map fieldValues) {
        String lookupReturnFieldName = (String) fieldValues.get(LOOKUP_RETURN);
        String lookupClassName = (String) fieldValues.get(LOOKUP_CLASS);
        if (StringUtils.isNotBlank(lookupClassName)) {
            for (Iterator iter = getRows().iterator(); iter.hasNext();) {
                Row row = (Row) iter.next();
                for (Iterator iterator = row.getFields().iterator(); iterator.hasNext();) {
                    Field field = (Field) iterator.next();
                    if (field.getPropertyName().equals(LOOKUP_RETURN)) {
                        globalVariableService.getUserSession().addObject(Constants.LOOKUP_CLASS_NAME, (Object) lookupClassName);
                        LookupReturnValuesFinder finder = new LookupReturnValuesFinder();
                        field.setFieldValidValues(finder.getKeyValues());
                        globalVariableService.getUserSession().removeObject(Constants.LOOKUP_RETURN_FIELDS);
                        globalVariableService.getUserSession().removeObject(Constants.LOOKUP_CLASS_NAME);
                        if (StringUtils.isNotBlank(lookupReturnFieldName)) {
                            field.setPropertyValue(lookupReturnFieldName);
                            field.setPropertyValue(LookupUtils.forceUppercase(this.getBusinessObjectClass(), lookupReturnFieldName, field.getPropertyValue()));
                            fieldValues.put(lookupReturnFieldName, field.getPropertyValue());
                        }
                    }
                }
            }

        }
        return super.checkForAdditionalFields(fieldValues);
    }

    public void setQuestionAuthorizationService(QuestionAuthorizationService questionAuthorizationService) {
        this.questionAuthorizationService = questionAuthorizationService;
    }
    public QuestionAuthorizationService getQuestionAuthorizationService() {
        return questionAuthorizationService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
    
    @Autowired
    @Qualifier("questionLookupableHelperService")
    @Override
    public void setLookupableHelperService(LookupableHelperService lookupableHelperService) {
        super.setLookupableHelperService(lookupableHelperService);
    }
}
