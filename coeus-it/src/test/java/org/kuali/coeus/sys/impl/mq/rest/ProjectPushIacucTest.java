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
package org.kuali.coeus.sys.impl.mq.rest;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.irb.api.dto.IrbProtocolDto;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.test.IacucProtocolFactory;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;

import javax.jms.ObjectMessage;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ProjectPushIacucTest extends ProjectPushTestBase {

    private ProjectRetrievalService irbProjectRetrievalService;
    final String IACUC_PROJECT_RETRIEVAL_SERVICE = "iacucProjectRetrievalService";
    ProjectRetrievalService iacucProjectRetrievalService;

    private IacucProtocolDocument iacucProtocolDocument;

    @Before
    public void setUp() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        iacucProtocolDocument = IacucProtocolFactory.createProtocolDocument();
        Document iacucDoc = getCommonApiService().getDocumentFromDocId(Long.parseLong(iacucProtocolDocument.getDocumentNumber()));
    }

    @Test
	public void test() throws Exception {
        final Project iacucProject = getProjectRetrievalService().retrieveProject(getDocumentIdentifier());
        if(isCoiEnabled()) {
            ObjectMessage message = getMessageFromProject(iacucProject);
            getRestMessageConsumer().onMessage(message);
        }
    }

    protected CommonApiService getCommonApiService() {
        return KcServiceLocator.getService(CommonApiService.class);
    }

    @Override
    public String getDocumentIdentifier() {
        return iacucProtocolDocument.getProtocol().getProtocolNumber();
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        if (iacucProjectRetrievalService == null) {
            iacucProjectRetrievalService = KcServiceLocator.getService(IACUC_PROJECT_RETRIEVAL_SERVICE);
        }
        return iacucProjectRetrievalService;
    }

}
