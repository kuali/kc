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
package org.kuali.kra.service.impl;

import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;

/**
 * This class...
 */
public class CustomAttributeDocumentMaintainableImpl extends KualiMaintainableImpl {
    
    public void prepareForSave() {
        CustomAttributeDocument customAttributeDocument = (CustomAttributeDocument) this.getBusinessObject();
        boolean needsTranslated = true;
        try {
            int val = Integer.parseInt(customAttributeDocument.getDocumentTypeName());
            //the getDocumentTypeName is a number, so it needs translated
            needsTranslated = true;
        } catch (Exception e) {
            // getDocumentTypeName has already been translated, so can't do it again.
            needsTranslated = false;
        }
        if (needsTranslated) {
            if ("7".equals(customAttributeDocument.getDocumentTypeName())) {
                customAttributeDocument.setDocumentTypeName("PROT");
                System.err.println("set to prot");
            } else if ("2".equals(customAttributeDocument.getDocumentTypeName())) {
                customAttributeDocument.setDocumentTypeName("INPR");
                System.err.println("set to inpr");
            } else {
                throw new IllegalArgumentException("invalid typeName provided: " + customAttributeDocument.getDocumentTypeName());
            }
        }
        super.prepareForSave();
    }

}
