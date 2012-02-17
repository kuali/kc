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
package org.kuali.kra.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.PropertyConstants;

public class PersonCustomDataRuleBase extends ResearchDocumentRuleBase {
    
    protected Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        Map<String, CustomAttributeDocument> customAttributeDocuments = new HashMap<String, CustomAttributeDocument>();
        
        Map<String, String> fieldValues = new HashMap<String, String>();
        fieldValues.put(PropertyConstants.DOCUMENT.TYPE_NAME.toString(), "PERS");
        Collection<CustomAttributeDocument> customAttributeDocumentList = getBusinessObjectService().findMatching(CustomAttributeDocument.class, fieldValues);
        for (CustomAttributeDocument customAttributeDocument : customAttributeDocumentList) {
            if (customAttributeDocument.isActive()) {
                customAttributeDocuments.put(customAttributeDocument.getCustomAttributeId().toString(), customAttributeDocument);
            }
        }
        
        return customAttributeDocuments;
    }

}