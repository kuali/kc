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
package org.kuali.kra.award.contacts;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.UnitContactType;

/**
 * This class tests ContactCategoryConverter
 */
public class ContactCategoryConversionTest {
    private static final String UNSUPPORTED_ENUM_VALUE = "FOO_BAR";
    private UnitContactTypeConverter converter;
    
    @Before
    public void setUp() {
        converter = new UnitContactTypeConverter();
    }
    
    @After
    public void tearDown() {
        converter = null;
    }
    
    @Test(expected=ConversionException.class)
    public void testConvertingRoleToSqlString_ConversionException() {
        converter.javaToSql("Bad Type");
    }
    
    @Test
    public void testConvertingContactCategoryToSqlString() {
        for(UnitContactType unitContactType: UnitContactType.values()) {
            Assert.assertEquals(unitContactType.name(), converter.javaToSql(unitContactType));
        }
    }
    
    @Test(expected=ConversionException.class)
    public void testConvertingSqlValueToContactRole_ConversionException_BadType() {
        converter.sqlToJava(Long.MAX_VALUE);
    }
    
    @Test(expected=ConversionException.class)
    public void testConvertingSqlValueToContactRole_ConversionException_InvalidValue() {
        converter.sqlToJava(UNSUPPORTED_ENUM_VALUE);
    }
    
    @Test
    public void testConvertingSqlValueToContactCategory() {
        for(UnitContactType unitContactType: UnitContactType.values()) {
            Assert.assertEquals(unitContactType, converter.sqlToJava(unitContactType.name()));
        }
    }
}
