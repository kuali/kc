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

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

public abstract class KcKrmsJavaFunctionTermServiceBase {
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String[] restrictedElements = { " ", "`", "@", "#", "!", "$", "%", "^", "&", "*", "(", ")", "[", "]", "{",
            "}", "|", "\\", "/", "?", "<", ">", ",", ";", ":", "'", "\"", "`", "+" };
    private BusinessObjectService businessObjectService;
    private ParameterService parameterService;
    private DocumentService documentService;

    protected String[] buildArrayFromCommaList(String commaList) {
        String[] newArray = commaList.split(","); // MIT Equity Interests
        if (commaList != null && newArray.length == 0) {
            newArray = new String[] { commaList.trim() };
        }
        return newArray;
    }

    /**
     * 
     * This method returns 'true' if 'stringToCheck' does not contain a special character, otherwise returns 'false'.
     * 
     * @param stringToCheck
     * @return
     */
    protected String specialCharacterRule(String stringToCheck) {
        for (String element : restrictedElements) {
            if (StringUtils.equalsIgnoreCase(element, stringToCheck)) {
                return FALSE;
            }
        }
        return TRUE;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
