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

public class UnitServiceImplTest {

    private List<Unit> allUnits;
    private Unit one;
    private Unit two;
    private Unit three;
    private UnitServiceImpl unitService;

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

        allUnits = new ArrayList<Unit>() {{
            add(one);
            add(two);
            add(three);
        }};
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

}
