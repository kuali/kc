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
package org.kuali.kra.bo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.attr.KcPersonExtendedAttributes;

import java.sql.Date;

import static org.junit.Assert.assertTrue;

public class KcPersonExtrendedAttributesTest {
    
    private KcPersonExtendedAttributes kcPersonEA;

    @Before
    public void setUp() throws Exception {
        kcPersonEA = new KcPersonExtendedAttributes();        
    }

    @After
    public void tearDown() throws Exception {
        kcPersonEA = null;
    }

    @Test
    public void testGetPersonId() {
        String testValue = "testID";
        kcPersonEA.setPersonId(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getPersonId()));
    }

    @Test
    public void testGetAgeByFiscalYear() {
        int testValue = 31;
        kcPersonEA.setAgeByFiscalYear(testValue);
        assertTrue("should be equal.", testValue == kcPersonEA.getAgeByFiscalYear());
    }

    @Test
    public void testGetRace() {
        String testValue = "does it matter";
        kcPersonEA.setRace(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getRace()));
    }

    @Test
    public void testGetEducationLevel() {
        String testValue = "BS";
        kcPersonEA.setEducationLevel(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getEducationLevel()));
    }

    @Test
    public void testGetDegree() {
        String testValue = "AAS";
        kcPersonEA.setDegree(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getDegree()));
    }

    @Test
    public void testGetMajor() {
        String testValue = "CIS";
        kcPersonEA.setMajor(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getMajor()));
    }

    @Test
    public void testGetHandicappedFlag() {
        boolean testValue = true;
        kcPersonEA.setHandicappedFlag(testValue);
        assertTrue("should be equal.", testValue == kcPersonEA.getHandicappedFlag());
    }

    @Test
    public void testGetHandicapType() {
        String testValue = "just kind of jerky about things";
        kcPersonEA.setHandicapType(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getHandicapType()));
    }

    @Test
    public void testGetVeteranFlag() {
        boolean testValue = true;
        kcPersonEA.setVeteranFlag(testValue);
        assertTrue("should be equal.", testValue == kcPersonEA.getVeteranFlag());
    }

    @Test
    public void testGetVeteranType() {
        String testValue = "of reading bad code";
        kcPersonEA.setVeteranType(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getVeteranType()));
    }

    @Test
    public void testGetVisaCode() {
        String testValue = "yelp";
        kcPersonEA.setVisaCode(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getVisaCode()));
    }

    @Test
    public void testGetVisaType() {
        String testValue = "none";
        kcPersonEA.setVisaType(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getVisaType()));
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testGetVisaRenewalDate() {
        Date testValue = new Date(2009, 9, 8);
        kcPersonEA.setVisaRenewalDate(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getVisaRenewalDate()));
    }

    @Test
    public void testGetHasVisa() {
        boolean testValue = true;
        kcPersonEA.setHasVisa(testValue);
        assertTrue("should be equal.", testValue == kcPersonEA.getHasVisa());
    }

    @Test
    public void testGetOfficeLocation() {
        String testValue = "120 Maple Ave";
        kcPersonEA.setOfficeLocation(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getOfficeLocation()));
    }

    @Test
    public void testGetSecondaryOfficeLocation() {
        String testValue = "G70, 120 Maple Ave";
        kcPersonEA.setSecondaryOfficeLocation(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getSecondaryOfficeLocation()));
    }

    @Test
    public void testGetSchool() {
        String testValue = "Cornell";
        kcPersonEA.setSchool(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getSchool()));
    }

    @Test
    public void testGetYearGraduated() {
        String testValue = "1998";
        kcPersonEA.setYearGraduated(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getYearGraduated()));
    }

    @Test
    public void testGetDirectoryDepartment() {
        String testValue = "CIT";
        kcPersonEA.setDirectoryDepartment(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getDirectoryDepartment()));
    }

    @Test
    public void testGetPrimaryTitle() {
        String testValue = "Programmer Analyst Senior";
        kcPersonEA.setPrimaryTitle(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getPrimaryTitle()));
    }

    @Test
    public void testGetDirectoryTitle() {
        String testValue = "Software Developer";
        kcPersonEA.setDirectoryTitle(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getDirectoryTitle()));
    }

    @Test
    public void testGetVacationAccrualFlag() {
        boolean testValue = false;
        kcPersonEA.setVacationAccrualFlag(testValue);
        assertTrue("should be equal.", testValue == kcPersonEA.getVacationAccrualFlag());
    }

    @Test
    public void testGetOnSabbaticalFlag() {
        boolean testValue = true;
        kcPersonEA.setOnSabbaticalFlag(testValue);
        assertTrue("should be equal.", testValue == kcPersonEA.getOnSabbaticalFlag());
    }

    @Test
    public void testGetIdProvided() {
        String testValue = "some fool-hardy ID";
        kcPersonEA.setIdProvided(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getIdProvided()));
    }

    @Test
    public void testGetIdVerified() {
        String testValue = "some fool-hardy ID that is real";
        kcPersonEA.setIdVerified(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getIdVerified()));
    }

    @Test
    public void testGetCounty() {
        String testValue = "Tompkins";
        kcPersonEA.setCounty(testValue);
        assertTrue("should be equal.", testValue.equals(kcPersonEA.getCounty()));
    }
}
