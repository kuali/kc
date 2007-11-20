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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AbstractType;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

/**
 * See the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CopyBudgetVersionsValuesFinder extends KeyValuesBase {
    
    /**
     * TODO: Come back and fix this.  The Final Version option can only
     * be displayed if there is a budget marked as final.
     * 
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair(ProposalCopyCriteria.BUDGET_ALL_VERSIONS, 
                                       ProposalCopyCriteria.BUDGET_ALL_VERSIONS));
        keyValues.add(new KeyLabelPair(ProposalCopyCriteria.BUDGET_FINAL_VERSION, 
                                       ProposalCopyCriteria.BUDGET_FINAL_VERSION));
        return keyValues;
    }
}
