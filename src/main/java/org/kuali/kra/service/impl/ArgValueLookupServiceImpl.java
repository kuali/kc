/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.service.impl;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.kra.service.ArgValueLookupService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * This class provides the implementation of the ArgValueLookup Service.
 * It provides service methods related to argument value lookup.
 */
public class ArgValueLookupServiceImpl implements ArgValueLookupService {

    private BusinessObjectService businessObjectService;
    
    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * 
     * @see org.kuali.kra.service.ArgValueLookupService#getArgumentNames()
     */
    public String getArgumentNames() {
        Collection<ArgValueLookup> argValueLookups = (Collection<ArgValueLookup>) businessObjectService.findAll(ArgValueLookup.class);
        SortedSet<String> argumentNames = new TreeSet<String>();
        String formattedArgumentNames = "";
        for (ArgValueLookup argValueLookup : argValueLookups) {
            argumentNames.add(argValueLookup.getArgumentName());
        }
        for (String argumentName : argumentNames) {
            formattedArgumentNames += "," + argumentName + ";" + argumentName;
        }
        return formattedArgumentNames;
    }
    
}
