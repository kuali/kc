/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.copy;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.copy.CopyProposalRule;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * Business Rule to determine if it valid for the user to copy the
 * given Proposal Development Document.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentCopyRule extends KcTransactionalDocumentRuleBase implements CopyProposalRule {

    /**
     * TODO: fill this in
     * 
     * @see org.kuali.coeus.propdev.impl.abstrct.AbstractsRule#processAddAbstractBusinessRules(org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument, org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract)
     */
    public boolean processCopyProposalBusinessRules(ProposalDevelopmentDocument document, ProposalCopyCriteria criteria) {
        boolean isValid = true;
        
        if (StringUtils.isBlank(criteria.getLeadUnitNumber())) {
            // If the user didn't select a lead unit, i.e. he/she choose the "select:" option,
            // then the Lead Unit Number will be "blank".
            isValid = false;
            GlobalVariables.getMessageMap().putError("copyCriteria.leadUnitNumber", 
                                                   KeyConstants.ERROR_LEAD_UNIT_REQUIRED);
        }

        return isValid;
    }
}
