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

import org.hibernate.annotations.Type;
import org.kuali.core.bo.DocumentType;

/**
 * 
 * This class bo of CustomAttributeDocument.
 */
@IdClass(org.kuali.kra.bo.id.CustomAttributeDocumentId.class)
@Entity
@Table(name="CUSTOM_ATTRIBUTE_DOCUMENT")
public class CustomAttributeDocument extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="CUSTOM_ATTRIBUTE_ID")
	private Integer customAttributeId;
	@Id
	@Column(name="DOCUMENT_TYPE_CODE")
	private String documentTypeCode;
    @Type(type="yes_no")
	@Column(name="IS_REQUIRED")
	private boolean required;
	@Column(name="TYPE_NAME")
	private String typeName;

    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="CUSTOM_ATTRIBUTE_ID", insertable=false, updatable=false)
	private CustomAttribute customAttribute;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="DOCUMENT_TYPE_CODE", insertable=false, updatable=false)
	private DocumentType documentType;
    @Type(type="yes_no")
    @Column(name="ACTIVE_FLAG")
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

