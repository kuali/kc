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
package org.kuali.kra.iacuc.actions.copy;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.test.IacucProtocolFactory;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.DocumentService;

public class IacucProtocolCopyServiceTest extends KcIntegrationTestBase {
    private DocumentService documentService;
    private IacucProtocolCopyService protocolCopyService;

    @Before
    public void setUpServices() {
        documentService = KcServiceLocator.getService(DocumentService.class);
        protocolCopyService = KcServiceLocator.getService(IacucProtocolCopyService.class);
    }

    @After
    public void tearDownServices() {
        documentService = null;
        protocolCopyService = null;
    }

    @Test
    public void test_basic_copy() throws Exception {
        IacucProtocolDocument protocolDocument = IacucProtocolFactory.createProtocolDocument();

        IacucProtocolDocument copyProtocolDocument = protocolCopyService.copyProtocol(protocolDocument);
        documentService.saveDocument(protocolDocument);

        Assert.assertTrue(protocolDocument.getProtocol().getProtocolId() < copyProtocolDocument.getProtocol().getProtocolId());
    }
}
