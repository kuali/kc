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
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;
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

import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;

/**
 * Validates Credit Splits on a <code>{@link ProposalPerson}</code> and/or <code>{@link ProposalPersonUnit}</code> by
 * traversing the tree of <code>{@link ProposalPerson}</code> <code>{@link ProposalPersonUnit}</code> instances.
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.3 $
 */
public class CreditSplitValidator {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(CreditSplitValidator.class);
    private static final KualiDecimal CREDIT_UPBOUND = new KualiDecimal(100.0);
    
    /**
     * Validates the credit splits of an entire document by traversing it. If the Investigator is instead a Principal Investigator,
     * the units should all add up to 100.0.
     *
     * @param document The document to validate the credit splits of
     * @return boolean
     */
    public boolean validate(ProposalDevelopmentDocument document) {
        Collection<InvestigatorCreditType> creditTypes = getKeyPersonnelService().getInvestigatorCreditTypes();
        boolean retval = true;
        
        for (InvestigatorCreditType creditType : creditTypes) {
            if (creditType.addsToHundred()) {
                retval &= validate(document.getInvestigators(), creditType.getInvCreditTypeCode());
            }
        }
        
        return retval;
    }
    
    public boolean validate(Collection<ProposalPerson> investigators, String creditTypeCode) {
        boolean retval = true;
        
        KualiDecimal investigatorCreditTotal = KualiDecimal.ZERO;
        for (ProposalPerson investigator : investigators) {
            KualiDecimal unitCreditTotal = KualiDecimal.ZERO;
            
            retval &= validateCreditSplit(investigator.getCreditSplits().iterator(), creditTypeCode, investigatorCreditTotal);

            retval &= validateCreditSplitable(investigator.getUnits().iterator(), creditTypeCode, unitCreditTotal);
        }
        
        return retval;
    }

    /**
     * Validates a collection of anything splitable. This implies that it contains <code>{@link CreditSplit}</code> instances.
     * 
     * @param splitable_it
     * @param greaterCummulative
     * @return boolean is valid?
     */
    public boolean validateCreditSplitable(Iterator<? extends CreditSplitable> splitable_it, String creditTypeCode, KualiDecimal greaterCummulative) {
        boolean retval = true;
     
        if (!splitable_it.hasNext()) {
            return CREDIT_UPBOUND.compareTo(greaterCummulative) > 0;
        }
        
        CreditSplitable splitable = splitable_it.next();
     
        KualiDecimal lesserCummulative = KualiDecimal.ZERO;        
        retval &= validateCreditSplit(splitable.getCreditSplits().iterator(), creditTypeCode, lesserCummulative);
     
        greaterCummulative.add(lesserCummulative);
     
        return validateCreditSplitable(splitable_it, creditTypeCode, greaterCummulative);
    }


    /**
     * Validates a collection of anything splits. 
     * 
     * @param creditSplit_it
     * @param lesserCummulative
     * @return boolean is valid?
     */
    public boolean validateCreditSplit(Iterator<? extends CreditSplit> creditSplit_it, String creditTypeCode, KualiDecimal lesserCummulative) {
        if (!creditSplit_it.hasNext()) {
            return CREDIT_UPBOUND.compareTo(lesserCummulative) >= 0;
        }
        
        CreditSplit creditSplit = creditSplit_it.next();
        if (creditTypeCode.equals(creditSplit.getInvCreditTypeCode())) {
            lesserCummulative.add(creditSplit.getCredit());
        }
     
        return validateCreditSplit(creditSplit_it, creditTypeCode, lesserCummulative);
    }

    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }
}
