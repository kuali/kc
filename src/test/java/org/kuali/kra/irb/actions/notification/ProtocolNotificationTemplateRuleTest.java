/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.irb.actions.notification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import junit.framework.Assert;

import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

public class ProtocolNotificationTemplateRuleTest {
    Mockery context = new JUnit4Mockery();
    FormFile mockedFile = null;

    @Before
    public void setUp() throws Exception {

        mockedFile = this.context.mock(FormFile.class);

        // Clear any error messages that may have been created in prior tests.
        MessageMap messageMap = GlobalVariables.getMessageMap();
        messageMap.clearErrorMessages();
    }

    @After
    public void tearDown() throws Exception {
        mockedFile = null;
    }

    /**
     * 
     * This test simulates a correspondence template being added whose committee is not specified.
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testReplaceNotificationTemplateOK() throws Exception {
        simulateValidMockedFileBehavior(Constants.CORRESPONDENCE_TEMPLATE_CONTENT_TYPE_1);

        ProtocolNotificationTemplate template = new ProtocolNotificationTemplate();
        template.setActionTypeCode("116");
        template.setFileName("notifyirb.xsl");
        template.setNotificationTemplate(new byte[] { (byte) 1, (byte) 2, (byte) 3 });
        template.setTemplateFile(mockedFile);
        int index = 2;

        boolean rulePassed = new ProtocolNotificationTemplateRule()
                .processReplaceProtocolNotificationTemplateRules(template, index);
        assertTrue(rulePassed);

        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(0, messageMap.getErrorCount());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testReplaceNotificationTemplateNotOK() throws Exception {
        simulateValidMockedFileBehavior("pdf");

        ProtocolNotificationTemplate template = new ProtocolNotificationTemplate();
        template.setActionTypeCode("116");
        template.setFileName("test.pdf");
        template.setNotificationTemplate(new byte[] {});
        template.setTemplateFile(mockedFile);
        int index = 2;

        boolean rulePassed = new ProtocolNotificationTemplateRule()
                .processReplaceProtocolNotificationTemplateRules(template, index);
        Assert.assertFalse(rulePassed);

        /*
         * There should be no errors.
         */
        MessageMap messageMap = GlobalVariables.getMessageMap();
        assertEquals(1, messageMap.getErrorCount());
    }

    private void simulateValidMockedFileBehavior(final String contentType) throws IOException {
        this.context.checking(new Expectations() {
            {
                allowing(mockedFile).getContentType();
                will(returnValue(contentType));

                allowing(mockedFile).getFileData();
                will(returnValue(new byte[] { (byte) 1, (byte) 2, (byte) 3 }));
            }
        });
    }


}
