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
package org.kuali.kra.institutionalproposal.contacts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.kra.award.contacts.ContactRoleFixtureFactory;

/**
 * This class tests AwardContact
 */
public class InstitutionalProposalPersonTest {
    private InstitutionalProposalPerson contact;
    private Unit unitA;
    private Unit unitB;
    
    @Before
    public void setUp() throws Exception {
        contact = new InstitutionalProposalPerson();
        unitA = new Unit();
        unitA.setUnitName("UnitA");
        unitB = new Unit();
        unitB.setUnitName("UnitB");        
    }
    
    @After
    public void tearDown() throws Exception {
        contact = null;
        unitA = null;
        unitB = null;
    }
    
    @Test
    public void testFindingPrincipalInvestigator() {
        contact.setContactRole(ContactRoleFixtureFactory.MOCK_COI);
        Assert.assertFalse("PI misidentified", contact.isPrincipalInvestigator());
        
        contact.setContactRole(ContactRoleFixtureFactory.MOCK_PI);
        Assert.assertTrue("PI not identified", contact.isPrincipalInvestigator());
    }
}
