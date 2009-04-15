/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.LinkedHashMap;

import org.kuali.core.bo.DocumentType;

/**
 * 
 * This class bo of CustomAttributeDocument.
 */
public class CustomAttributeDocument extends KraPersistableBusinessObjectBase {

	private Integer customAttributeId;
	private String documentTypeCode;
	private boolean required;
	private String typeName;

    private CustomAttribute customAttribute;
    private DocumentType documentType;
    private boolean active;

	public CustomAttributeDocument(){
		super();
	}

	public Integer getCustomAttributeId() {
		return customAttributeId;
	}

	public void setCustomAttributeId(Integer customAttributeId) {
		this.customAttributeId = customAttributeId;
	}

    /**
     * Sets the documentTypeCode attribute value.
     * @param documentTypeCode The documentTypeCode to set.
     */
    public void setDocumentTypeCode(String documentTypeCode) {
        this.documentTypeCode = documentTypeCode;
    }

    /**
     * Gets the documentTypeCode attribute.
     * @return Returns the documentTypeCode.
     */
    public String getDocumentTypeCode() {
        return documentTypeCode;
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


	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("customAttributeId", getCustomAttributeId());
		hashMap.put("documentTypeCode", getDocumentTypeCode());
		hashMap.put("required", isRequired());
		hashMap.put("typeName", getTypeName());
		return hashMap;
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

    /**
     * Sets the documentType attribute value.
     * @param documentType The documentType to set.
     */
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    /**
     * Gets the documentType attribute.
     * @return Returns the documentType.
     */
    public DocumentType getDocumentType() {
        return documentType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
