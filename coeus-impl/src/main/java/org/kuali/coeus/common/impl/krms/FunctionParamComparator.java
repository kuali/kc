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

import org.kuali.rice.krms.api.repository.function.FunctionParameterDefinition;

import java.util.Comparator;


public class FunctionParamComparator implements Comparator<FunctionParameterDefinition> {
    @Override
    public int compare(FunctionParameterDefinition param1, FunctionParameterDefinition param2) {
        return param1.getSequenceNumber().compareTo(param2.getSequenceNumber());
    }
}
