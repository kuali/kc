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
package org.kuali.coeus.common.framework.custom;

import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.impl.custom.CustomDataRule;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
    protected ErrorReporter errorReporter = KcServiceLocator.getService(ErrorReporter.class);
    
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
