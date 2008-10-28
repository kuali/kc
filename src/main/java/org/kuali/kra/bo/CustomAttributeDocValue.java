package org.kuali.kra.bo;

import java.util.LinkedHashMap;

/**
 * 
 * This class is bo of CustomAttributeDocValue.
 */
public class CustomAttributeDocValue extends KraPersistableBusinessObjectBase {

	private Integer customAttributeId;
	private String documentNumber;
	private String value;

    private CustomAttribute customAttribute;

	public CustomAttributeDocValue(){
		super();
	}

	public Integer getCustomAttributeId() {
		return customAttributeId;
	}

	public void setCustomAttributeId(Integer customAttributeId) {
		this.customAttributeId = customAttributeId;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	@Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("customAttributeId", getCustomAttributeId());
		hashMap.put("documentNumber", getDocumentNumber());
		hashMap.put("value", getValue());
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
}
