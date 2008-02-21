package org.kuali.kra.bo;

import java.util.LinkedHashMap;

/**
 * This is bo class of CustomAttribute.
 */
public class CustomAttribute extends KraPersistableBusinessObjectBase {

	private Integer id;
	private Integer dataLength;
	private String dataTypeCode;
	private String defaultValue;
	private String groupName;
	private String label;
	private String lookupClass;
	private String lookupReturn;
	private String name;
    private String value;

    private CustomAttributeDataType customAttributeDataType;

	public CustomAttribute(){
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDataLength() {
		return dataLength;
	}

	public void setDataLength(Integer dataLength) {
		this.dataLength = dataLength;
	}

	public String getDataTypeCode() {
		return dataTypeCode;
	}

	public void setDataTypeCode(String dataTypeCode) {
		this.dataTypeCode = dataTypeCode;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLookupClass() {
		return lookupClass;
	}

	public void setLookupClass(String lookupClass) {
		this.lookupClass = lookupClass;
	}

	public String getLookupReturn() {
		return lookupReturn;
	}

	public void setLookupReturn(String lookupReturn) {
		this.lookupReturn = lookupReturn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	/**
     * Sets the customAttributeDataType attribute value.
     * @param customAttributeDataType The customAttributeDataType to set.
     */
    public void setCustomAttributeDataType(CustomAttributeDataType customAttributeDataType) {
        this.customAttributeDataType = customAttributeDataType;
    }

    /**
     * Gets the customAttributeDataType attribute.
     * @return Returns the customAttributeDataType.
     */
    public CustomAttributeDataType getCustomAttributeDataType() {
        return customAttributeDataType;
    }

    @Override
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("id", getId());
		hashMap.put("dataLength", getDataLength());
		hashMap.put("dataTypeCode", getDataTypeCode());
		hashMap.put("defaultValue", getDefaultValue());
		hashMap.put("groupName", getGroupName());
		hashMap.put("label", getLabel());
		hashMap.put("lookupClass", getLookupClass());
		hashMap.put("lookupReturn", getLookupReturn());
		hashMap.put("name", getName());
		return hashMap;
	}

    /**
     * Sets the value attribute value.
     * @param value The value to set.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value attribute. 
     * @return Returns the value.
     */
    public String getValue() {
        return value;
    }
}
