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
package org.kuali.kra.proposaldevelopment;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * Common convenience methods for the Proposal Development module.
 */
public final class ProposalDevelopmentUtils {
    
    public static final String ACTIVITY_TYPE_CODE_CLINICAL_TRIAL_PARM = "ACTIVITY_TYPE_CODE_CLINICAL_TRIAL";
    public static final String ACTIVITY_TYPE_CODE_CONSTRUCTION_PARM = "ACTIVITY_TYPE_CODE_CONSTRUCTION";
    
    public static final String S2S_SUBMISSION_TYPE_CODE_NOTSELECTED_PARM = "S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION";
    
    public static final String PROPOSAL_TYPE_CODE_NEW_PARM = "PROPOSAL_TYPE_CODE_NEW";
    public static final String PROPOSAL_TYPE_CODE_RESUBMISSION_PARM = "PROPOSAL_TYPE_CODE_RESUBMISSION";
    public static final String PROPOSAL_TYPE_CODE_RENEWAL_PARM = "PROPOSAL_TYPE_CODE_RENEWAL";
    public static final String PROPOSAL_TYPE_CODE_CONTINUATION_PARM = "PROPOSAL_TYPE_CODE_CONTINUATION";
    public static final String PROPOSAL_TYPE_CODE_REVISION_PARM = "PROPOSAL_TYPE_CODE_REVISION";
    public static final String PROPOSAL_TYPE_CODE_TASK_ORDER_PARM = "PROPOSAL_TYPE_CODE_TASK_ORDER";
    
    private ProposalDevelopmentUtils() {
        
    }
    
    /**
     * Retrieve the parameter value from the system parameter service, using the Proposal 
     * Development namespace and Document component.
     * 
     * @param parmName The name of the parameter.
     * @return String
     */
    public static String getProposalDevelopmentDocumentParameter(final String parmName) {
        ParameterService parameterService = KraServiceLocator.getService(ParameterService.class);
        return parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, 
                ParameterConstants.DOCUMENT_COMPONENT, 
                parmName);
    }
    
}
        
