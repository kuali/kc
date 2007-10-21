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
package org.kuali.kra.proposaldevelopment.rules;

import static org.kuali.core.util.GlobalVariables.getErrorMap;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_ALL_PERSON_CREDIT_SPLIT_UPBOUND;
import static org.kuali.kra.infrastructure.KeyConstants.ERROR_PERSON_CREDIT_SPLIT_UPBOUND;

import java.util.Iterator;
import java.util.List;

import org.kuali.core.util.KualiDecimal;
import org.kuali.kra.proposaldevelopment.bo.CreditSplit;
import org.kuali.kra.proposaldevelopment.bo.CreditSplitable;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonCreditSplit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

/**
 * Validates Credit Splits on a <code>{@link ProposalPerson}</code> and/or <code>{@link ProposalPersonUnit}</code> by
 * traversing the tree of <code>{@link ProposalPerson}</code> <code>{@link ProposalPersonUnit}</code> instances.
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.1 $
 */
public class CreditSplitValidator {
    private static final KualiDecimal CREDIT_UPBOUND = new KualiDecimal(1.0);
    
    /**
     * Validates the credit splits of an entire document by traversing it. If the Investigator is instead a Principal Investigator,
     * the units should all add up to 1.0.
     *
     * @param document The document to validate the credit splits of
     * @return boolean
     */
//     public boolean validate(ProposalDevelopmentDocument document) {
//         boolean retval = true;

//         KualiDecimal allPersonCreditTotal = KualiDecimal.ZERO;
//         retval &= validate(document.getInvestigators().iterator(), allPersonCreditTotal);
        
//         // Reloop investigators. Maybe there's a better way to do this.
//         for (ProposalPerson investigator : document.getInvestigators()) {
//             if (new ProposalDevelopmentKeyPersonsRule().isPrincipalInvestigator(investigator)) {
//                 retval &= validate(investigator.getUnits().iterator(), KualiDecimal.ZERO);
//             }
//         }
        
//         return retval;        
//     }
    
    /**
     * Validates a collection of anything splitable. This implies that it contains <code>{@link CreditSplit}</code> instances.
     * 
     * @param splitable_it
     * @param greaterCummulative
     * @return boolean
     */
//     public boolean validate(Iterator<? extends CreditSplitable> splitable_it, KualiDecimal greaterCummulative) {
//         boolean retval = true;
        
//         if (!splitable_it.hasNext()) {
//             return CREDIT_UPBOUND.compareTo(greaterCummulative) > 0;
//         }

//         CreditSplitable splitable = splitable_it.next();
        
//         KualiDecimal lesserCummulative = KualiDecimal.ZERO;        
//         retval &= validate(splitable.getCreditSplits().iterator(), lesserCummulative);
        
//         greaterCummulative.add(lesserCummulative);
        
//         return validate(splitable_it, greaterCummulative);
//     }

    /**
     * Validates a collection of anything splits. 
     * 
     * @param creditSplit_it
     * @param lesserCummulative
     * @return boolean
     */
//     public boolean validate(Iterator<CreditSplit> creditSplit_it, KualiDecimal lesserCummulative) {
//         if (!creditSplit_it.hasNext()) {
//             return CREDIT_UPBOUND.compareTo(lesserCummulative) >= 0;
//         }
        
//         CreditSplit creditSplit = creditSplit_it.next();
//         lesserCummulative.add(creditSplit.getCredit());
        
//         return validate(creditSplit_it, lesserCummulative);
//     }

}
