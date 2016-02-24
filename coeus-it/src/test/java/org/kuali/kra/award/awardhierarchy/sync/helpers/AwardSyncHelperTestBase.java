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
package org.kuali.kra.award.awardhierarchy.sync.helpers;


import org.junit.After;
import org.junit.Before;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.sync.service.AwardSyncHelpersService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

public abstract class AwardSyncHelperTestBase extends KcIntegrationTestBase {

    protected AwardSyncHelpersService awardSyncHelpersService;
    protected String className;
    protected Award award;
    protected AwardSyncHelper awardSyncHelper;
    
    protected AwardSyncHelperTestBase(String className) {
        this.className = className;
    }
    
    @Before
    public void setUp() throws Exception {
        awardSyncHelpersService = KcServiceLocator.getService(AwardSyncHelpersService.class);
        awardSyncHelper = awardSyncHelpersService.getSyncHelper(className);
        award = new Award();
        award.setSponsorCode("000340");
    }

    @After
    public void tearDown() throws Exception {
    }

}
