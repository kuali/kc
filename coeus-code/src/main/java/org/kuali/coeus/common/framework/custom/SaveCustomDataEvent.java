/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.custom;

import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.impl.custom.CustomDataRule;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.rice.krad.document.Document;

import java.util.List;
import java.util.Map;

/**
 * Encapsulates a validation event for a Custom Attribute add action.
 */
public class SaveCustomDataEvent extends KcDocumentEventBaseExtension {

    private boolean validateRequiredFields = false;
    private List<? extends DocumentCustomData> customDataList;
    private Map<String, CustomAttributeDocument> customAttributeDocuments;
    protected ErrorReporter errorReporter = new ErrorReporter();
    
    /**
     * Constructs a SaveCustomAttributeEvent.
     * @param errorPathPrefix The error path prefix
     * @param document The document to validate
     */
    public SaveCustomDataEvent(String errorPrefix, Document document, List<? extends DocumentCustomData> customDataList, Map<String, CustomAttributeDocument> customAttributeDocuments) {
        super("Saving custom attribute to document " + getDocumentId(document), errorPrefix, document);
        this.customDataList = customDataList;
        this.customAttributeDocuments = customAttributeDocuments;
    }
    
    public SaveCustomDataEvent(String errorPrefix, Document document, List<? extends DocumentCustomData> customDataList, Map<String, CustomAttributeDocument> customAttributeDocuments, boolean validateRequiredFields) {
        this(errorPrefix, document, customDataList, customAttributeDocuments);
        this.validateRequiredFields = validateRequiredFields;
    }
    public SaveCustomDataEvent(KcTransactionalDocumentBase document) {
        this("customDataHelper.customDataList", document, document.getDocumentCustomData(), document.getCustomAttributeDocuments());
    }
    public SaveCustomDataEvent(KcTransactionalDocumentBase document, boolean validateRequiredFields) {
        this("customDataHelper.customDataList", document, document.getDocumentCustomData(), document.getCustomAttributeDocuments(), validateRequiredFields);
    }
    
    

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new CustomDataRule();
    }

    public boolean isValidateRequiredFields() {
        return validateRequiredFields;
    }

    public void setValidateRequiredFields(boolean validateRequiredFields) {
        this.validateRequiredFields = validateRequiredFields;
    }

    public List<? extends DocumentCustomData> getCustomDataList() {
        return customDataList;
    }

    public void setCustomDataList(List<? extends DocumentCustomData> customDataList) {
        this.customDataList = customDataList;
    }

    public Map<String, CustomAttributeDocument> getCustomAttributeDocuments() {
        return customAttributeDocuments;
    }

    public void setCustomAttributeDocuments(Map<String, CustomAttributeDocument> customAttributeDocuments) {
        this.customAttributeDocuments = customAttributeDocuments;
    }
    
    public void reportError(CustomAttribute customAttribute, String propertyName, String errorKey, String... errorParams) {
        errorReporter.reportError(propertyName, errorKey, errorParams);
    }

}