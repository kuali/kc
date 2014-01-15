package org.kuali.kra.budget;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;

@Converter(autoApply = true)
public class BudgetDecimalConverter implements AttributeConverter<BudgetDecimal, BigDecimal> {
    @Override
    public BigDecimal convertToDatabaseColumn(BudgetDecimal attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.bigDecimalValue();
    }

    @Override
    public BudgetDecimal convertToEntityAttribute(BigDecimal dbData) {
        if (dbData == null) {
            return null;
        }
        return new BudgetDecimal(dbData);
    }
}
