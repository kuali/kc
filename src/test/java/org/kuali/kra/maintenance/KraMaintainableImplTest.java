/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.maintenance;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.proposaldevelopment.bo.MailType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class tests KraMaintainableImpl.
 */
public class KraMaintainableImplTest extends KcUnitTestBase {

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        GlobalVariables.setUserSession(null);
    }

    @Test public void testPrepareForSaveInsertQuickstart() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        KraMaintainableImpl kraMaintainableImpl = new KraMaintainableImpl();
        kraMaintainableImpl.setBusinessObject(kraPersistableBusinessObjectBase);

        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());

        kraMaintainableImpl.prepareForSave();

        GlobalVariables.setUserSession(new UserSession("jtester"));
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("quickstart", kraPersistableBusinessObjectBase);
    }

    @Test public void testPrepareForSaveUpdateQuickstart() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        KraMaintainableImpl kraMaintainableImpl = new KraMaintainableImpl();
        kraMaintainableImpl.setBusinessObject(kraPersistableBusinessObjectBase);

        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());

        kraMaintainableImpl.prepareForSave();

        GlobalVariables.setUserSession(new UserSession("jtester"));
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("quickstart", kraPersistableBusinessObjectBase);
    }

    @Test public void testPrepareForSaveInsertJtester() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        KraMaintainableImpl kraMaintainableImpl = new KraMaintainableImpl();
        kraMaintainableImpl.setBusinessObject(kraPersistableBusinessObjectBase);

        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());

        kraMaintainableImpl.prepareForSave();

        GlobalVariables.setUserSession(new UserSession("quickstart"));
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("jtester", kraPersistableBusinessObjectBase);
    }

    @Test public void testPrepareForSaveUpdateJtester() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        KraMaintainableImpl kraMaintainableImpl = new KraMaintainableImpl();
        kraMaintainableImpl.setBusinessObject(kraPersistableBusinessObjectBase);

        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());

        kraMaintainableImpl.prepareForSave();

        GlobalVariables.setUserSession(new UserSession("quickstart"));
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("jtester", kraPersistableBusinessObjectBase);
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
