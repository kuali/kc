package org.kuali.coeus.sys.framework.persistence;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true)
public class ScaleTwoDecimalConverter implements AttributeConverter<ScaleTwoDecimal, BigDecimal> {
    @Override
    public BigDecimal convertToDatabaseColumn(ScaleTwoDecimal attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.bigDecimalValue();
    }

    @Override
    public ScaleTwoDecimal convertToEntityAttribute(BigDecimal dbData) {
        if (dbData == null) {
            return null;
        }
        return new ScaleTwoDecimal(dbData);
    }
}
