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
package org.kuali.coeus.common.framework.custom.attr;

import org.kuali.coeus.common.api.custom.attr.CustomAttributeContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOM_ATTRIBUTE")
public class CustomAttribute extends KcPersistableBusinessObjectBase implements CustomAttributeContract {

    @PortableSequenceGenerator(name = "SEQ_CUSTOM_ATTRIBUTE")
    @GeneratedValue(generator = "SEQ_CUSTOM_ATTRIBUTE")
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_LENGTH")
    private Integer dataLength;

    @Column(name = "DATA_TYPE_CODE")
    private String dataTypeCode;

    @Column(name = "DEFAULT_VALUE")
    private String defaultValue;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "LABEL")
    private String label;

    @Column(name = "LOOKUP_CLASS")
    private String lookupClass;

    @Column(name = "LOOKUP_RETURN")
    private String lookupReturn;

    @Column(name = "NAME")
    private String name;

    @Transient
    private String value;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "DATA_TYPE_CODE", referencedColumnName = "DATA_TYPE_CODE", insertable = false, updatable = false)
    private CustomAttributeDataType customAttributeDataType;

    public CustomAttribute() {
        super();
        groupName = "General";
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    @Override
    public String getDataTypeCode() {
        return dataTypeCode;
    }

    public void setDataTypeCode(String dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getLookupClass() {
        return lookupClass;
    }

    public void setLookupClass(String lookupClass) {
        this.lookupClass = lookupClass;
    }

    @Override
    public String getLookupReturn() {
        return lookupReturn;
    }

    public void setLookupReturn(String lookupReturn) {
        this.lookupReturn = lookupReturn;
    }

    @Override
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
    @Override
    public CustomAttributeDataType getCustomAttributeDataType() {
        return customAttributeDataType;
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
