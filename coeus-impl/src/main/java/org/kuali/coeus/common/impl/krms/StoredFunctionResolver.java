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
package org.kuali.coeus.common.impl.krms;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is for resolving terms for StoredFuncions. It extract values from prerequisites, execute Stored Function 
 * and resolves the result.
 */
public class StoredFunctionResolver extends FunctionTermResolver {

    public StoredFunctionResolver(List<String> orderedInputParams, Set<String> parameterNames, String output) {
        super(orderedInputParams, parameterNames, output);
    }

    protected String executeFunction(String packageName,String functionName,Map<String, Object> resolvedPrereqs,Map<String,String> resolvedParameters) {
        if(packageName!=null) {
            functionName = packageName+"."+functionName;
        }
        List<Object> orderedParamValues = extractParamValues(resolvedPrereqs,resolvedParameters);
        return callFunction(functionName,orderedParamValues);
    }
    
    private String callFunction(String functionName, List<Object> orderedParamValues) {
        StoredFunctionDao storedFunctionDao = getStoredFucntionDao();
        return storedFunctionDao.executeFunction(functionName, orderedParamValues);
    }
    private StoredFunctionDao getStoredFucntionDao() {
        return KcServiceLocator.getService(StoredFunctionDao.class);
    }
}
