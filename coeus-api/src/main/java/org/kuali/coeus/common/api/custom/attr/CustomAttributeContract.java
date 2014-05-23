package org.kuali.coeus.common.api.custom.attr;


import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface CustomAttributeContract extends IdentifiableNumeric {

    Integer getDataLength();
    String getDataTypeCode();
    String getDefaultValue();
    String getGroupName();
    String getLabel();
    String getLookupClass();
    String getLookupReturn();
    String getName();
    CustomAttributeDataTypeContract getCustomAttributeDataType();

}
