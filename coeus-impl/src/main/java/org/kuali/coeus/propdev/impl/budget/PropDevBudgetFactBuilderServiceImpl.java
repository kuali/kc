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
package org.kuali.coeus.propdev.impl.budget;

import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.engine.Facts.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("propDevBudgetFactBuilderService")
public class PropDevBudgetFactBuilderServiceImpl extends KcKrmsFactBuilderServiceHelper {

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;
    
    public void addFacts(Facts.Builder factsBuilder, String docContent) {
    	throw new UnsupportedOperationException();
    }
    
    public void addFacts(Facts.Builder factsBuilder, KrmsRulesContext context) {
        Budget budget = (Budget) context;
        addBudgetFacts(factsBuilder,budget);
    }
    
    private void addBudgetFacts(Builder factsBuilder, Budget budget) {
        addObjectMembersAsFacts(factsBuilder,budget,KcKrmsConstants.PropDevBudget.BUDGET_CONTEXT_ID,Constants.MODULE_NAMESPACE_BUDGET);
        factsBuilder.addFact(KcKrmsConstants.PropDevBudget.BUDGET, budget);
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
    

}
