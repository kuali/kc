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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.AbstractType;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.AbstractsRule;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * Business Rule to determine the validity of Proposal Abstracts.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentAbstractsRule extends ResearchDocumentRuleBase implements AbstractsRule {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentAbstractsRule.class);

    /**
     * Don't allow abstracts with an invalid abstract type code or duplicate abstracts, i.e.
     * same abstract type code, into the database.
     * 
     * @see org.kuali.kra.proposaldevelopment.rule.AbstractsRule#processAddAbstractBusinessRules(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalAbstract)
     */
    public boolean processAddAbstractBusinessRules(ProposalDevelopmentDocument document, ProposalAbstract proposalAbstract) {
        boolean isValid = true;
        String abstractTypeCode = proposalAbstract.getAbstractTypeCode();
        
        if (StringUtils.isBlank(abstractTypeCode)) {
            // If the user didn't select an abstract type, i.e. he/she choose the "select:" option,
            // then the Abstract Type Code will be "blank".
            isValid = false;
            GlobalVariables.getErrorMap().putError(Constants.ABSTRACTS_PROPERTY_KEY + ".abstractTypeCode", 
                                                   KeyConstants.ERROR_ABSTRACT_TYPE_NOT_SELECTED);
        }
        else if (isInvalid(abstractTypeCode)) {
            isValid = false;
            this.reportError(Constants.ABSTRACTS_PROPERTY_KEY, 
                             KeyConstants.ERROR_ABSTRACT_TYPE_INVALID);
        }
        else if (isDuplicate(document, abstractTypeCode)) {
            isValid = false;
            this.reportError(Constants.ABSTRACTS_PROPERTY_KEY, 
                             KeyConstants.ERROR_ABSTRACT_TYPE_DUPLICATE);
        }
        return isValid;
    }
    
    /**
     * Is this an invalid abstract type code?  Query the database for a matching abstract
     * type code.  If found, it is valid; otherwise it is invalid.
     * 
     * @param abstractTypeCode the abstract type code to test against.
     * @return true if invalid; false if valid
     */
    private boolean isInvalid(String abstractTypeCode) {
        if (abstractTypeCode != null) {
            BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
            Map<String,String> fieldValues = new HashMap<String,String>();
            fieldValues.put("abstractTypeCode", abstractTypeCode);
            if (businessObjectService.countMatching(AbstractType.class, fieldValues) == 1) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Is this a duplicate abstract?  Abstracts must have a unique abstract type code.
     * If an abstract already exists with the same abstract type code, do not allow
     * the next abstract.
     * 
     * @param document the proposal development document
     * @param abstractTypeCode the abstract type code to compare against
     * @return true if it is a duplicate; otherwise false
     */
    private boolean isDuplicate(ProposalDevelopmentDocument document, String abstractTypeCode) {
        List<ProposalAbstract> proposalAbstracts = document.getProposalAbstracts();
        for (ProposalAbstract proposalAbstract : proposalAbstracts) {
            if (proposalAbstract.getAbstractTypeCode().equals(abstractTypeCode)) {
                return true;
            }
        }
        return false;
    }
}
