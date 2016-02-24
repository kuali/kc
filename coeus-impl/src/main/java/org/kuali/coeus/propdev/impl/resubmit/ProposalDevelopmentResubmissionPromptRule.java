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
package org.kuali.coeus.propdev.impl.resubmit;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.resubmit.ResubmissionPromptRule;
import org.kuali.coeus.propdev.impl.resubmit.ResubmissionRuleEvent;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

/**
 * Implements {@code ResubmissionPromptRule}.
 */
public class ProposalDevelopmentResubmissionPromptRule extends KcTransactionalDocumentRuleBase implements ResubmissionPromptRule {

    private static final String REBUSMISSION_OPTION = "resubmissionOption";
    
    @Override
    public boolean processResubmissionPromptBusinessRules(ResubmissionRuleEvent resubmissionRuleEvent) {
        boolean rulePassed = true;
        
        if (StringUtils.isBlank(resubmissionRuleEvent.getResubmissionOption())) {
            rulePassed = false;
            reportError(REBUSMISSION_OPTION, KeyConstants.ERROR_PROPOSAL_DEVELOPMENT_RESUBMISSION_PROMPT_OPTION_REQUIRED);
        }
        
        return rulePassed;
    }

}
