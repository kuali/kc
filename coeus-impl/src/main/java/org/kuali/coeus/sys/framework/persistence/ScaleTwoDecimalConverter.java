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
