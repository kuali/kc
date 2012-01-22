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
package org.kuali.kra.core.datadictionary.validation;

import org.kuali.rice.krad.datadictionary.validation.FieldLevelValidationPattern;

public class Postive2DigitFieldLevelValidationPattern extends FieldLevelValidationPattern {

    private static final long serialVersionUID = -3023572453587788065L;

    @Override
    protected String getPatternTypeName() {
        return "postivenonzero";
    }
    
    protected String getRegexString() {
        return "^[1-9][0-9]?";
    }

}
