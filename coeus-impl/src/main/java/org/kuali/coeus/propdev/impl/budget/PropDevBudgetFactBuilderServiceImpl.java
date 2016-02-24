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
