/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.UserSession;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.proposaldevelopment.bo.CarrierType;

/**
 * This class tests KraPersistableBusinessObjectBase.
 */
public class KraPersistableBusinessObjectBaseTest extends KraTestBase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.setUserSession(null);
    }

    @Test public void testBeforeInsertQuickstart() throws Exception {
        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new CarrierType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("quicksta", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeUpdateQuickstart() throws Exception {
        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new CarrierType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("quicksta", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeInsertUser4() throws Exception {
        GlobalVariables.setUserSession(new UserSession("user4"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new CarrierType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("user4", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeUpdateUser4() throws Exception {
        GlobalVariables.setUserSession(new UserSession("user4"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new CarrierType();
        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("user4", kraPersistableBusinessObjectBase);
    }

    private void updateAsserts(String udpateUser, KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase) {
        assertEquals(udpateUser, kraPersistableBusinessObjectBase.getUpdateUser());
        Timestamp updateTimestamp = kraPersistableBusinessObjectBase.getUpdateTimestamp();
        assertNotNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());

        Date currentDate = new Date(System.currentTimeMillis());
        long diff = updateTimestamp.getTime() - currentDate.getTime();

        assertTrue("Should be less than one second difference between dates", diff < 1000);
    }

}
