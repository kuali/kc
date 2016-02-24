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
package org.kuali.kra.maintenance;

import org.junit.After;
import org.junit.Test;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.common.proposal.framework.mail.MailType;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;
/**
 * This class tests KraMaintainableImpl.
 */
public class KraMaintainableImplTest extends KcIntegrationTestBase {

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
    }

    @Test public void testPrepareForSaveInsertQuickstart() throws Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));

        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
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

        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
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

        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
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

        KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        KraMaintainableImpl kraMaintainableImpl = new KraMaintainableImpl();
        kraMaintainableImpl.setBusinessObject(kraPersistableBusinessObjectBase);

        assertNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());
        assertNull(kraPersistableBusinessObjectBase.getUpdateUser());

        kraMaintainableImpl.prepareForSave();

        GlobalVariables.setUserSession(new UserSession("quickstart"));
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("jtester", kraPersistableBusinessObjectBase);
    }

    private void updateAsserts(String udpateUser, KcPersistableBusinessObjectBase kraPersistableBusinessObjectBase) {
        assertEquals(udpateUser, kraPersistableBusinessObjectBase.getUpdateUser());
        Timestamp updateTimestamp = kraPersistableBusinessObjectBase.getUpdateTimestamp();
        assertNotNull(kraPersistableBusinessObjectBase.getUpdateTimestamp());

        Date currentDate = new Date(System.currentTimeMillis());
        long diff = updateTimestamp.getTime() - currentDate.getTime();

        assertTrue("Should be less than one second difference between dates", diff < 1000);
    }

}
