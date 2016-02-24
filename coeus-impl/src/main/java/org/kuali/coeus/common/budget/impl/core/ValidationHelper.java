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
package org.kuali.coeus.common.budget.impl.core;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * This class provides validation support
 */
@Component("validationHelper")
public class ValidationHelper {
    private static final String REQUIRED_ERROR_KEY = "error.required";
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    /**
     * This method checks a required Object field value and registers an error if the 
     * field value is null, or if a String, if field value is empty 
     * @param fieldValue
     * @param errorProperty
     * @param errorParms
     * @return True if field is valid according to required rules
     */
    public boolean checkRequiredField(Object fieldValue, String errorProperty, String... errorParms) {
        boolean isEmpty = isEmpty(fieldValue);
        if(isEmpty) {
        	globalVariableService.getMessageMap().putError(errorProperty, REQUIRED_ERROR_KEY, errorParms);
        }
        
        return !isEmpty; 
    }    

    /**
     * This method checks if field is null, or if a String, empty
     * @param fieldValue
     * @return
     */
    private boolean isEmpty(Object fieldValue) {
        boolean empty = (fieldValue == null);
        if (!empty && fieldValue instanceof String) {
            String value = (String) fieldValue;
            empty = StringUtils.trimWhitespace(value).length() == 0;
        }
        return empty;
    }

    public boolean checkValuePositive(ScaleTwoDecimal projectIncome, String errorProperty, String errorKey, String... parms) {
        boolean success = projectIncome.isPositive(); 
        if(!success) {
        	globalVariableService.getMessageMap().putError(errorProperty, errorKey, parms);
        }
        return success;
    }

	protected GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}
}
