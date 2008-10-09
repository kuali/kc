package org.kuali.kra.bo;

import java.util.LinkedHashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * This class is bo of CustomAttributeDocValue.
 */
@IdClass(org.kuali.kra.bo.id.CustomAttributeDocValueId.class)
@Entity
@Table(name="CUSTOM_ATTRIBUTE_DOC_VALUE")
public class CustomAttributeDocValue extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="CUSTOM_ATTRIBUTE_ID")
	private Integer customAttributeId;
	@Id
	@Column(name="DOCUMENT_NUMBER")
	private String documentNumber;
	@Column(name="VALUE")
	private String value;

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="CUSTOM_ATTRIBUTE_ID", insertable=false, updatable=false)
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

