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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.framework.engine.AgendaTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UnitAgendaTest {
    public static final String ONE = "ONE";
    public static final String TWO = "TWO";
    public static final String THREE = "THREE";
    public static final String OTHER = "OTHER";
    public static final String ANOTHER = "ANOTHER";
    private Unit one;
    private Unit two;
    private Unit three;
    private Unit other;
    private Unit another;
    private UnitAgenda unitAgenda;
    @Before
    public void buildServiceToTest() {
        unitAgenda = new UnitAgenda(new HashMap<String,String>(), new AgendaTree() {
            @Override
            public void execute(ExecutionEnvironment environment) {

            }
        },"",true);
    }

    @Before
    public void buildAllUnits() {
        //unit hierarchy = one-->two-->three
        one = new Unit();
        one.setUnitNumber(ONE);

        two = new Unit();
        two.setUnitNumber(TWO);
        two.setParentUnitNumber(ONE);

        three = new Unit();
        three.setUnitNumber(THREE);
        three.setParentUnitNumber(TWO);

        //other unit hierachy = one-->other-->another
        other = new Unit();
        other.setUnitNumber(OTHER);
        other.setParentUnitNumber(ONE);

        another = new Unit();
        another.setUnitNumber(ANOTHER);
        another.setParentUnitNumber(OTHER);


    }

    @Test
    public void test_appliesToAnyUnitInHierarchy_bottom() {
        List<Unit> unitHierarchy = new ArrayList<Unit>(){{
            add(one);
            add(two);
            add(three);
        }};
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(ONE, unitHierarchy));
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(TWO, unitHierarchy));
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(THREE, unitHierarchy));
    }

    @Test
    public void test_appliesToAnyUnitInHierarchy_middle() {
        List<Unit> unitHierarchy = new ArrayList<Unit>(){{
            add(one);
            add(two);
        }};
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(ONE, unitHierarchy));
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(TWO, unitHierarchy));
        Assert.assertFalse(unitAgenda.appliesToAnyUnitInHierarchy(THREE, unitHierarchy));
    }

    @Test
    public void test_appliesToAnyUnitInHierarchy_top() {
        List<Unit> unitHierarchy = new ArrayList<Unit>(){{
            add(one);
        }};
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(ONE, unitHierarchy));
        Assert.assertFalse(unitAgenda.appliesToAnyUnitInHierarchy(TWO, unitHierarchy));
        Assert.assertFalse(unitAgenda.appliesToAnyUnitInHierarchy(THREE, unitHierarchy));
    }

    @Test
    public void test_appliesToAnyUnitInHierarchy_differentBranch() {
        List<Unit> unitHierarchy = new ArrayList<Unit>(){{
            add(one);
            add(other);
            add(another);
        }};
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(ONE, unitHierarchy));
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(OTHER, unitHierarchy));
        Assert.assertTrue(unitAgenda.appliesToAnyUnitInHierarchy(ANOTHER, unitHierarchy));
        Assert.assertFalse(unitAgenda.appliesToAnyUnitInHierarchy(TWO, unitHierarchy));
        Assert.assertFalse(unitAgenda.appliesToAnyUnitInHierarchy(THREE, unitHierarchy));
    }
}
