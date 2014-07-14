/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.krms;

import org.kuali.rice.krms.api.repository.function.FunctionDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * This class is used for calling appropriate Stored Function Resolver from KRMS Type Service
 */
@Component("storedFunctionTermResolverTypeService")
public class StoredFunctionTermResolverTypeServiceImpl extends FunctionTermResolverTypeServiceBase {

    @Override
    public FunctionTermResolver createFunctionResolver(List<String> functionParams, Set<String> termResolverParams, String output, FunctionDefinition functionTerm) {
        FunctionTermResolver functionResolver = new StoredFunctionResolver(functionParams,termResolverParams,output);
        return functionResolver;
    }

}
