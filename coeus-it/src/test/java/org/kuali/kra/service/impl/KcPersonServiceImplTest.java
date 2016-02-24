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
package org.kuali.kra.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.impl.person.KcPersonServiceImpl;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import static org.junit.Assert.assertTrue;
public class KcPersonServiceImplTest extends KcIntegrationTestBase {
    
    private KcPersonService service;

    @Before
    public void getServices() throws Exception {
        service = KcServiceLocator.getService(KcPersonService.class);
    }
    
    @Test
    public void testCorrectClass(){
        assertTrue("Should be the same", service.getClass().equals(KcPersonServiceImpl.class));
    }

    @Test
    public void testGetKcPersonByUserName() {
        String userName = "quickstart";
        KcPerson person = service.getKcPersonByUserName(userName);
        assertTrue("Should have found 'quickstart', but found:" + person.getUserName(), userName.equals(person.getUserName()));
    }

    @Test
    public void testGetKcPersonByPersonId() {
        String personID = "10000000002";
        String expectedUserName = "jtester";
        KcPerson person = service.getKcPersonByPersonId(personID);
        assertTrue("Should have found:" + expectedUserName  + ", but found:" + person.getUserName(), expectedUserName.equals(person.getUserName()));
    }
}
