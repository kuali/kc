/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.action;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.action.ProposalDevelopmentRejectionBean;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.core.api.util.RiceKeyConstants;

/**
 * 
 * This class manages the rules for rejecting a proposal development.
 */
public class ProposalDevelopmentRejectionRule extends KcTransactionalDocumentRuleBase {
    
    /**
     * 
     * This method validate the reject action.
     * @param bean
     * @return
     */
    public boolean proccessProposalDevelopmentRejection(ProposalDevelopmentRejectionBean bean) {
        boolean valid = true;
        if (StringUtils.isEmpty(bean.getRejectReason())) {
            valid = false;
            String errorParams = "";
            reportError("proposalDevelopmentRejectionBean.rejectReason", RiceKeyConstants.ERROR_REQUIRED, errorParams);
        }
        return valid;
    }

}
