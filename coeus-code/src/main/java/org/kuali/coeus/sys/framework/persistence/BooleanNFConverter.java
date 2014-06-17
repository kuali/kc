package org.kuali.coeus.sys.framework.persistence;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanNFConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean objectValue) {
        if (objectValue == null) {
            return "F";
        }
        return objectValue ? "N" : "F";
    }

    @Override
    public Boolean convertToEntityAttribute(String dataValue) {
        if (dataValue == null) {
            return Boolean.FALSE;
        }
        return dataValue.equals("N");
    }
}
