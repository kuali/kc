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
