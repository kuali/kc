/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.contacts;

import org.apache.ojb.broker.accesslayer.conversions.ConversionException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.UnitContactType;

/**
 * This class tests ContactCategoryConverter
 */
public class ContactCategoryConversionTest {
    private static final String UNSUPPORTED_ENUM_VALUE = "FOO_BAR";
    private UnitTypeConverter converter;
    
    @Before
    public void setUp() {
        converter = new UnitTypeConverter();
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
