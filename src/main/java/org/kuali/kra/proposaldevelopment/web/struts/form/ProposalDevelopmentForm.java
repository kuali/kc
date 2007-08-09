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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.web.struts.form.KualiTransactionalDocumentFormBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.PropLocation;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentForm extends KualiTransactionalDocumentFormBase {

    public ProposalDevelopmentForm() {
        super();
        this.setDocument(new ProposalDevelopmentDocument());
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument.class.getName())).getHeaderTabNavigation());
    }


    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) this.getDocument();
    }

    @Override
    public void populate(HttpServletRequest request) {

        this.setMethodToCall(null);
        this.setRefreshCaller(null);
        this.setAnchor(null);
        this.setTabStates(new HashMap<String, String>());
        this.setCurrentTabIndex(0);

        super.populate(request);

    }

}
