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
package org.kuali.kra.irb.actions.copy;


import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.test.ProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.DocumentService;

public class ProtocolCopyServiceTest extends KcIntegrationTestBase {

    private DocumentService documentService;
    private ProtocolCopyService protocolCopyService;

    @Before
    public void setUpServices() {
        documentService = KcServiceLocator.getService(DocumentService.class);
        protocolCopyService = KcServiceLocator.getService(ProtocolCopyService.class);
    }

    @After
    public void tearDownServices() {
        documentService = null;
        protocolCopyService = null;
    }

    @Test
    public void test_basic_copy() throws Exception {
        ProtocolDocument protocolDocument = ProtocolFactory.createProtocolDocument();

        ProtocolDocument copyProtocolDocument = protocolCopyService.copyProtocol(protocolDocument);
        documentService.saveDocument(protocolDocument);

        Assert.assertTrue(protocolDocument.getProtocol().getProtocolId() < copyProtocolDocument.getProtocol().getProtocolId());
    }
}
