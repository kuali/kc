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
package org.kuali.kra.bo;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.proposaldevelopment.bo.MailType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class tests KraPersistableBusinessObjectBase.
 */
public class KraPersistableBusinessObjectBaseTest extends KcUnitTestBase {

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
        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertTrue(StringUtils.isBlank(kraPersistableBusinessObjectBase.getUpdateUser()));
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("quickstart", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeUpdateQuickstart() throws Exception {
        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertTrue(StringUtils.isBlank(kraPersistableBusinessObjectBase.getUpdateUser()));
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("quickstart", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeInsertUser4() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertTrue(StringUtils.isBlank(kraPersistableBusinessObjectBase.getUpdateUser()));
        kraPersistableBusinessObjectBase.beforeInsert(null);

        updateAsserts("jtester", kraPersistableBusinessObjectBase);
    }

    @Test public void testBeforeUpdateUser4() throws Exception {
        GlobalVariables.setUserSession(new UserSession("jtester"));

        KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase = new MailType();
        assertTrue(StringUtils.isBlank(kraPersistableBusinessObjectBase.getUpdateUser()));
        kraPersistableBusinessObjectBase.beforeUpdate(null);

        updateAsserts("jtester", kraPersistableBusinessObjectBase);
    }

    private void updateAsserts(String udpateUser, KraPersistableBusinessObjectBase kraPersistableBusinessObjectBase) {
        assertEquals(udpateUser, kraPersistableBusinessObjectBase.getUpdateUser());
    }

}
