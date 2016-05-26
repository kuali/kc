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
package org.kuali.coeus.common.impl.unit;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UnitServiceImplTest {

    private List<Unit> allUnits;
    private Unit one;
    private Unit two;
    private Unit three;
    private UnitServiceImpl unitService;

/*
    mysql> select unit_number, unit_name, parent_unit_number from unit order by parent_unit_number;
+-------------+--------------------------------+--------------------+
| unit_number | unit_name                      | parent_unit_number |
+-------------+--------------------------------+--------------------+
| 000001      | University                     | NULL               |
| IU-UNIV     | UNIVERSITY LEVEL               | 000001             |
| BL-RUGS     | OFFICE OF VP FOR RESEARCH      | BL-BL              |
| BL-IIDC     | IND INST ON DISABILITY/COMMNTY | BL-RCEN            |
| BL-RCEN     | RESEARCH CENTERS               | BL-RUGS            |
| IN-CARR     | CARDIOLOGY RECHARGE CTR        | IN-CARD            |
| IN-MED      | SCHOOL OF MEDICINE             | IN-IN              |
| IN-CARD     | CARDIOLOGY                     | IN-MDEP            |
| IN-MDEP     | MEDICINE DEPT                  | IN-MED             |
| IN-PED      | PEDIATRICS                     | IN-MED             |
| IN-PERS     | PED-EMERGENCY ROOM SERVICES    | IN-PED             |
| BL-BL       | BLOOMINGTON CAMPUS             | IU-UNIV            |
| IN-IN       | IND UNIV-PURDUE UNIV INDPLS    | IU-UNIV            |
+-------------+--------------------------------+--------------------+
*/

    private List<Unit> allDemoUnits;
    private Unit university;
    private Unit universityLevel;
    private Unit bloomingtonCampus;
    private Unit indianapolis;
    private Unit officeVpResearch;
    private Unit schoolOfMedicine;
    private Unit researchCenters;
    private Unit medicineDept;
    private Unit pediatrics;
    private Unit disabilityCommnty;
    private Unit cardiology;
    private Unit pedEmergencyRoomServices;
    private Unit cardiologyRechargeCenter;

    @Before
    public void buildAllDemoUnits() {
        university = new Unit();
        university.setUnitNumber("000001");
        university.setUnitName("University");

        universityLevel = new Unit();
        universityLevel.setUnitNumber("IU-UNIV");
        universityLevel.setUnitName("UNIVERSITY LEVEL");

        bloomingtonCampus = new Unit();
        bloomingtonCampus.setUnitNumber("BL-BL");
        bloomingtonCampus.setUnitName("BLOOMINGTON CAMPUS");

        indianapolis = new Unit();
        indianapolis.setUnitNumber("IN-IN");
        indianapolis.setUnitName("IND UNIV-PURDUE UNIV INDPLS");

        officeVpResearch = new Unit();
        officeVpResearch.setUnitNumber("BL-RUGS");
        officeVpResearch.setUnitName("OFFICE OF VP FOR RESEARCH");

        schoolOfMedicine = new Unit();
        schoolOfMedicine.setUnitNumber("IN-MED");
        schoolOfMedicine.setUnitName("SCHOOL OF MEDICINE");

        researchCenters = new Unit();
        researchCenters.setUnitNumber("BL-RCEN");
        researchCenters.setUnitName("RESEARCH CENTERS");

        medicineDept = new Unit();
        medicineDept.setUnitNumber("IN-MDEP");
        medicineDept.setUnitName("MEDICINE DEPT");

        pediatrics = new Unit();
        pediatrics.setUnitNumber("IN-PED");
        pediatrics.setUnitName("PEDIATRICS");

        disabilityCommnty = new Unit();
        disabilityCommnty.setUnitNumber("BL-IIDC");
        disabilityCommnty.setUnitName("IND INST ON DISABILITY/COMMNTY");

        cardiology = new Unit();
        cardiology.setUnitNumber("IN-CARD");
        cardiology.setUnitName("CARDIOLOGY");

        pedEmergencyRoomServices = new Unit();
        pedEmergencyRoomServices.setUnitNumber("IN-PERS");
        pedEmergencyRoomServices.setUnitName("PED-EMERGENCY ROOM SERVICES");

        cardiologyRechargeCenter = new Unit();
        cardiologyRechargeCenter.setUnitNumber("IN-CARR");
        cardiologyRechargeCenter.setUnitName("CARDIOLOGY RECHARGE CTR");

        universityLevel.setParentUnit(university);
        universityLevel.setParentUnitNumber(university.getUnitNumber());

        bloomingtonCampus.setParentUnit(universityLevel);
        bloomingtonCampus.setParentUnitNumber(universityLevel.getUnitNumber());

        indianapolis.setParentUnit(universityLevel);
        indianapolis.setParentUnitNumber(universityLevel.getUnitNumber());

        officeVpResearch.setParentUnit(bloomingtonCampus);
        officeVpResearch.setParentUnitNumber(bloomingtonCampus.getUnitNumber());

        schoolOfMedicine.setParentUnit(indianapolis);
        schoolOfMedicine.setParentUnitNumber(indianapolis.getUnitNumber());

        researchCenters.setParentUnit(officeVpResearch);
        researchCenters.setParentUnitNumber(officeVpResearch.getUnitNumber());

        medicineDept.setParentUnit(schoolOfMedicine);
        medicineDept.setParentUnitNumber(schoolOfMedicine.getUnitNumber());

        pediatrics.setParentUnit(schoolOfMedicine);
        pediatrics.setParentUnitNumber(schoolOfMedicine.getUnitNumber());

        disabilityCommnty.setParentUnit(researchCenters);
        disabilityCommnty.setParentUnitNumber(researchCenters.getUnitNumber());

        cardiology.setParentUnit(medicineDept);
        cardiology.setParentUnitNumber(medicineDept.getUnitNumber());

        pedEmergencyRoomServices.setParentUnit(pediatrics);
        pedEmergencyRoomServices.setParentUnitNumber(pediatrics.getUnitNumber());

        cardiologyRechargeCenter.setParentUnit(cardiology);
        cardiologyRechargeCenter.setParentUnitNumber(cardiology.getUnitNumber());

        allDemoUnits = Stream.of(disabilityCommnty, cardiology, cardiologyRechargeCenter,
                pedEmergencyRoomServices, indianapolis, officeVpResearch, pediatrics, medicineDept,
                researchCenters, schoolOfMedicine, university, bloomingtonCampus, universityLevel).collect(Collectors.toList());
    }

    @Before
    public void buildServiceToTest() {
        unitService = new UnitServiceImpl();
    }

    @Before
    public void buildAllUnits() {
        //unit hierarchy = one-->two-->three
        one = new Unit();
        one.setUnitNumber("ONE");

        two = new Unit();
        two.setUnitNumber("TWO");
        two.setParentUnitNumber("ONE");

        three = new Unit();
        three.setUnitNumber("THREE");
        three.setParentUnitNumber("TWO");

        allUnits = Stream.of(one, two, three).collect(Collectors.toList());
    }

    @Test
    public void test_getUnitCaseInsensitive_blankUnitNumber() {
        Assert.assertNull(unitService.getUnitCaseInsensitive(""));
    }

    @Test
    public void test_getUnitCaseInsensitive_nullUnitNumber() {
        Assert.assertNull(unitService.getUnitCaseInsensitive(null));
    }

    @Test
    public void test_findUnitsWithDirectParent_blankUnitNumber() {
        Assert.assertEquals(Collections.emptyList(), unitService.findUnitsWithDirectParent(allUnits, ""));
    }

    @Test()
    public void test_findUnitsWithDirectParent_nullUnitNumber() {
        Assert.assertEquals(Collections.emptyList(), unitService.findUnitsWithDirectParent(allUnits, null));
    }

    @Test()
    public void test_findUnitsWithDirectParent_normal_top_UnitNumber() {
        final List<Unit> expects = new ArrayList<Unit>() {{
            add(two);
            add(three);
        }};

        Assert.assertEquals(expects, unitService.findUnitsWithDirectParent(allUnits, "ONE"));
    }

    @Test()
    public void test_findUnitsWithDirectParent_normal_middle_UnitNumber() {
        final List<Unit> expects = new ArrayList<Unit>() {{
            add(three);
        }};

        Assert.assertEquals(expects, unitService.findUnitsWithDirectParent(allUnits, "TWO"));
    }

    @Test()
    public void test_getParentUnitsInclusiveBottom() {
        final List<Unit> expects = new ArrayList<Unit>() {{
            add(one);
            add(two);
            add(three);
        }};

        List<Unit> returned = unitService.getParentUnitsInclusive(allUnits, "THREE");
        Assert.assertTrue("Parent unit list " + returned + " did not match expected " + expects,CollectionUtils.subtract(expects, returned).isEmpty());
    }

    @Test()
    public void test_getParentUnitsInclusiveMiddle() {
        final List<Unit> expects = new ArrayList<Unit>() {{
            add(one);
            add(two);
        }};

        List<Unit> returned = unitService.getParentUnitsInclusive(allUnits, "TWO");
        Assert.assertTrue("Parent unit list " + returned + " did not match expected " + expects, CollectionUtils.subtract(expects, returned).isEmpty());
    }

    @Test
    public void test_getParentUnitsInclusiveOrphanChild() {
        final List<Unit> expects = new ArrayList<Unit>() {{
            add(three);
        }};

        allUnits.remove(two);
        List<Unit> returned = unitService.getParentUnitsInclusive(allUnits, "THREE");
        Assert.assertTrue("Parent unit list " + returned + " did not match expected " + expects, CollectionUtils.subtract(expects, returned).isEmpty());
    }

    @Test
    public void test_truncate_level_1() {
        List<Unit> units = unitService.truncate(allDemoUnits, 1);
        Assert.assertTrue(CollectionUtils.isEqualCollection(Collections.singleton(university), units));
    }

    @Test
    public void test_truncate_level_3() {
        List<Unit> units = unitService.truncate(allDemoUnits, 3);
        Assert.assertTrue(CollectionUtils.isEqualCollection(Stream.of(university, universityLevel, bloomingtonCampus, indianapolis).collect(Collectors.toList()), units));
    }

    @Test
    public void test_truncate_level_less_than_lowest_level() {
        {
            List<Unit> units = unitService.truncate(allDemoUnits, -1);
            Assert.assertTrue(CollectionUtils.isEqualCollection(Collections.singleton(university), units));
        }
        {
            List<Unit> units = unitService.truncate(allDemoUnits, 0);
            Assert.assertTrue(CollectionUtils.isEqualCollection(Collections.singleton(university), units));
        }
    }

    @Test
    public void test_truncate_level_greater_than_max_level() {
        List<Unit> units = unitService.truncate(allDemoUnits, 1000);
        Assert.assertTrue(CollectionUtils.isEqualCollection(allDemoUnits, units));
    }

    @Test
    public void test_sort_one_element() {
        List<Unit> unsortedUnits = new ArrayList<>();
        unsortedUnits.add(university);
        List<Unit> units = unitService.sortUnits(unsortedUnits);
        Assert.assertEquals(unsortedUnits, units);
    }

    @Test
    public void test_sort_already_sorted() {
        List<Unit> alreadysortedUnits = Stream.of(university, universityLevel, bloomingtonCampus, indianapolis, officeVpResearch,
                schoolOfMedicine, researchCenters, medicineDept, pediatrics, disabilityCommnty, cardiology, pedEmergencyRoomServices,
                cardiologyRechargeCenter).collect(Collectors.toList());
        List<Unit> units = unitService.sortUnits(alreadysortedUnits);
        Assert.assertEquals(alreadysortedUnits, units);
    }

    @Test
    public void test_sort_not_sorted() {
        List<Unit> units = unitService.sortUnits(allDemoUnits);

        List<Unit> sortedUnits = Stream.of(university, universityLevel, bloomingtonCampus, indianapolis, officeVpResearch,
                schoolOfMedicine, researchCenters, medicineDept, pediatrics, disabilityCommnty, cardiology, pedEmergencyRoomServices,
                cardiologyRechargeCenter).collect(Collectors.toList());
        //the order could be slightly different depending on which part of the tree at a level is placed first on the list.
        List<Unit> alternativeSortedUnits = Stream.of(university, universityLevel, indianapolis, bloomingtonCampus, schoolOfMedicine,
                officeVpResearch, pediatrics, medicineDept, researchCenters, pedEmergencyRoomServices, cardiology, disabilityCommnty,
                cardiologyRechargeCenter).collect(Collectors.toList());

        Assert.assertTrue(sortedUnits.equals(units) || alternativeSortedUnits.equals(units));
    }

    @Test
    public void test_sort_truncate_associative_property() {
        List<Unit> unitsTruncateSort = unitService.truncate(unitService.sortUnits(allDemoUnits), 3);
        Assert.assertTrue(CollectionUtils.isEqualCollection(Stream.of(university, universityLevel, bloomingtonCampus, indianapolis).collect(Collectors.toList()), unitsTruncateSort));

        List<Unit> unitsSortTruncate = unitService.sortUnits(unitService.truncate(allDemoUnits, 3));
        Assert.assertTrue(CollectionUtils.isEqualCollection(Stream.of(university, universityLevel, bloomingtonCampus, indianapolis).collect(Collectors.toList()), unitsSortTruncate));

        Assert.assertEquals(unitsTruncateSort, unitsSortTruncate);
    }
}
