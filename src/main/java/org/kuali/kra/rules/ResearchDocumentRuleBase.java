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
package org.kuali.kra.rules;


import org.kuali.core.document.Document;
import org.kuali.core.rules.DocumentRuleBase;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.ExceptionUtils;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * Base implementation class for KRA document business rules
 *
 * @author $Author: lprzybyl $
 * @version $Revision: 1.3 $
 */
public abstract class ResearchDocumentRuleBase extends DocumentRuleBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ResearchDocumentRuleBase.class);

    /**
     * Wrapper around global errorMap.put call, to allow better logging
     * 
     * @param propertyName
     * @param errorKey
     * @param errorParams
     */
    protected void reportError(String propertyName, String errorKey, String... errorParams) {
        LOG.debug("reportError(String, String, String) - start");

        GlobalVariables.getErrorMap().putError(propertyName, errorKey, errorParams);
        if (LOG.isDebugEnabled()) {
            LOG.debug("rule failure at " + ExceptionUtils.describeStackLevels(1, 2));
        }
    }

}
