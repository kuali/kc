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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.core.api.util.KeyValue;

/**
 * 
 * This class tests {@link CopyBudgetVersionsValuesFinder CopyBudgetVersionsValuesFinder} class.
 */
public class CopyBudgetVersionsValuesFinderTest {

    private CopyBudgetVersionsValuesFinder finalVerFinder;
    private CopyBudgetVersionsValuesFinder nonFinalVerFinder;
    
    /**
     * sets up the final finder.
     */
    @Before
    public void setupfinalFinder() {

        this.finalVerFinder = new CopyBudgetVersionsValuesFinder() {
            @Override
            ProposalDevelopmentDocument getDocument() {
                List<BudgetDocumentVersion> overviews = new ArrayList<BudgetDocumentVersion>();
                overviews.add(new BudgetDocumentVersion());
                BudgetDocumentVersion o = new BudgetDocumentVersion();
                o.getBudgetVersionOverview().setFinalVersionFlag(true);
                overviews.add(o);
                
                ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
                document.setBudgetDocumentVersions(overviews);
                
                return document;
            }  
        };
    }
    
    /**
     * sets up the non final finder.
     */
    @Before
    public void setupNofinalFinder() {

        this.nonFinalVerFinder = new CopyBudgetVersionsValuesFinder() {
            @Override
            ProposalDevelopmentDocument getDocument() {
                List<BudgetDocumentVersion> overviews = new ArrayList<BudgetDocumentVersion>();
                overviews.add(new BudgetDocumentVersion());
                BudgetDocumentVersion o = new BudgetDocumentVersion();
                overviews.add(o);
                
                ProposalDevelopmentDocument document = new ProposalDevelopmentDocument();
                document.setBudgetDocumentVersions(overviews);
                
                return document;
            }  
        };
    }
    
    /**
     * Tests the finalVersionPresent method with a final budget version.
     */
    @Test
    public void isFinalVersionFinal() {
        
        Assert.assertTrue("final version should be present", finalVerFinder.finalVersionPresent());
    }
    
    /**
     * Tests the finalVersionPresent method with no final budget versions.
     */
    @Test
    public void isFinalVersionNotFinal() {
        Assert.assertTrue("final version should NOT be present", !nonFinalVerFinder.finalVersionPresent());
    }
    
    /**
     * Tests that the correct key/label pairs exist with a final version.
     */
    @Test
    public void finalVersionVerify() {
        
        List<KeyValue> keyValues = finalVerFinder.getKeyValues();
        
        Assert.assertEquals("should be two key/label pair", 2, keyValues.size());
        Assert.assertEquals("key should be", ProposalCopyCriteria.BUDGET_ALL_VERSIONS, keyValues.get(0).getKey());
        Assert.assertEquals("Label should be", ProposalCopyCriteria.BUDGET_ALL_VERSIONS, keyValues.get(0).getValue());
        Assert.assertEquals("key should be", ProposalCopyCriteria.BUDGET_FINAL_VERSION, keyValues.get(1).getKey());
        Assert.assertEquals("Label should be", ProposalCopyCriteria.BUDGET_FINAL_VERSION, keyValues.get(1).getValue());
    }
    
    /**
     * Tests that the correct key/label pairs exist with no final versions.
     */
    @Test
    public void nonFinalVersionVerify() {
        List<KeyValue> keyValues = nonFinalVerFinder.getKeyValues();
        
        Assert.assertEquals("should only be one key/label pair", 1, keyValues.size());
        Assert.assertEquals("key should be", ProposalCopyCriteria.BUDGET_ALL_VERSIONS, keyValues.get(0).getKey());
        Assert.assertEquals("Label should be", ProposalCopyCriteria.BUDGET_ALL_VERSIONS, keyValues.get(0).getValue());
    }

}
