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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.rice.kew.api.KewApiServiceLocator;
import org.kuali.rice.kew.api.doctype.DocumentType;
import org.kuali.rice.krad.datadictionary.DocumentEntry;
import org.kuali.rice.krad.document.DocumentRequestAuthorizationCache;
import org.kuali.rice.krad.document.DocumentViewAuthorizerBase;
import org.kuali.rice.krad.document.DocumentViewPresentationControllerBase;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.view.FormView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * View for a ProposalDocument that is used as a summary view on the action list, used to workaround one
 * view per document type limitation.
 */
public class ProposalDocumentSummaryView extends ProposalDocumentView{

    public ProposalDocumentSummaryView () {
   		super();

        setRequestAuthorizationCacheClass(DocumentRequestAuthorizationCache.class);
   	}

    @Override
    public void performInitialization(Object model) {
        super.performInitialization(model);

        setObjectPathToConcreteClassMapping(new HashMap<String, Class<?>>());
        getObjectPathToConcreteClassMapping().put("document", ProposalDevelopmentDocument.class);

        // Workaround for non-set additionalScriptFiles files
        setAdditionalScriptFiles(new ArrayList<String>());
    }

    protected DocumentEntry getDocumentEntryForView() {
        DocumentEntry documentEntry = KRADServiceLocatorWeb.getDocumentDictionaryService().getDocumentEntryByClass(
                ProposalDevelopmentDocument.class);

        return documentEntry;
    }
}
