/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.service.SessionDocumentService;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.UserSession;

import java.sql.Timestamp;

public class KcKnsSessionDocumentServiceImpl implements SessionDocumentService {

    @Override
    public WorkflowDocument getDocumentFromSession(UserSession userSession, String docId) {
        return null;
    }

    @Override
    public void addDocumentToUserSession(UserSession userSession, WorkflowDocument document) {        
    }

    @Override
    public void purgeDocumentForm(String documentNumber, String docFormKey, UserSession userSession, String ipAddress) {
        
    }

    @Override
    public void purgeAllSessionDocuments(Timestamp expirationDate) {        
    }

    @Override
    public KualiDocumentFormBase getDocumentForm(String documentNumber, String docFormKey, UserSession userSession, String ipAddress) {
        return null;
    }

    @Override
    public void setDocumentForm(KualiDocumentFormBase form, UserSession userSession, String ipAddress) {        
    }

   

}
