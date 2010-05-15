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
package org.kuali.kra.budget.core;

import java.util.Map;

import org.apache.ojb.broker.accesslayer.RowReaderDefaultImpl;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.AwardBudgetVersionOverviewExt;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * This class...
 */
public class BudgetDocumentRowReaderImpl extends RowReaderDefaultImpl {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -633262608528458894L;

    private ClassDescriptor cld;
    public BudgetDocumentRowReaderImpl(ClassDescriptor cld) {
        super(cld);
        this.cld = cld;
    }
    @Override
    public void readObjectArrayFrom(org.apache.ojb.broker.accesslayer.ResultSetAndStatement rs_stmt, Map row){
        super.readObjectArrayFrom(rs_stmt, row);
        String parentDocumentTypeCode = (String) row.get("PARENT_DOCUMENT_TYPE_CODE");
        boolean isBudgetDocument = cld.getClassNameOfObject().equals(BudgetDocument.class.getName());
        String budgetCollectionName = isBudgetDocument?"budgets":"budgetVersionOverviews";
        CollectionDescriptor cd = cld.getCollectionDescriptorByName(budgetCollectionName);
        Class classDescrClazz = Budget.class;
        if(ProposalDevelopmentDocument.DOCUMENT_TYPE_CODE.equals(parentDocumentTypeCode)){
            classDescrClazz = isBudgetDocument?Budget.class:BudgetVersionOverview.class;
        }
        if(AwardDocument.DOCUMENT_TYPE_CODE.equals(parentDocumentTypeCode)){
            classDescrClazz = isBudgetDocument?AwardBudgetExt.class:AwardBudgetVersionOverviewExt.class;
        }
        cd.setItemClass(classDescrClazz);
        
    }
}
