/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.kra.bo;

import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.kew.doctype.bo.DocumentType;

/**
 * 
 * This class bo of CustomAttributeDocument.
 */
public class CustomAttributeDocument extends KraPersistableBusinessObjectBase implements MutableInactivatable {

    private Integer customAttributeId;

    private String documentTypeName;

    private boolean required;

    private String typeName;

    private CustomAttribute customAttribute;

    private DocumentType documentType;

    private boolean active;

    public CustomAttributeDocument() {
        super();
    }

    public Integer getCustomAttributeId() {
        return customAttributeId;
    }

    public void setCustomAttributeId(Integer customAttributeId) {
        this.customAttributeId = customAttributeId;
    }

    /**
     * Sets the documentTypeName attribute value.
     * @param documentTypeName The documentTypeName to set.
     */
    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    /**
     * Gets the documentTypeCode attribute.
     * @return Returns the documentTypeCode.
     */
    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Sets the customAttribute attribute value.
     * @param customAttribute The customAttribute to set.
     */
    public void setCustomAttribute(CustomAttribute customAttribute) {
        this.customAttribute = customAttribute;
    }

    /**
     * Gets the customAttribute attribute.
     * @return Returns the customAttribute.
     */
    public CustomAttribute getCustomAttribute() {
        return customAttribute;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Gets the documentType attribute. 
     * @return Returns the documentType.
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    /**
     * Sets the documentType attribute value.
     * @param documentType The documentType to set.
     */
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
}
