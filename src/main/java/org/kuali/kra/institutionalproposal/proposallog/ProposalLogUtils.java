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
package org.kuali.kra.institutionalproposal.proposallog;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * This class is a set of common convenience methods used by Proposal Log related classes.
 */
public final class ProposalLogUtils {
    
    private ProposalLogUtils() {
        
    }
    
    public static String getProposalLogPendingStatusCode() {
        return getCodeValue("PROPOSAL_LOG_PENDING_STATUS_CODE");
    }
    
    public static String getProposalLogMergedStatusCode() {
        return getCodeValue("PROPOSAL_LOG_MERGED_STATUS_CODE");
    }
    
    public static String getProposalLogSubmittedStatusCode() {
        return getCodeValue("PROPOSAL_LOG_SUBMITTED_STATUS_CODE");
    }
    
    public static String getProposalLogVoidStatusCode() {
        return getCodeValue("PROPOSAL_LOG_VOID_STATUS_CODE");
    }
    
    public static String getProposalLogTemporaryStatusCode() {
        return getCodeValue("PROPOSAL_LOG_TEMPORARY_STATUS_CODE");
    }
    
    public static String getProposalLogPermanentTypeCode() {
        return getCodeValue("PROPOSAL_LOG_PERMANENT_TYPE_CODE");
    }
    
    public static String getProposalLogTemporaryTypeCode() {
        return getCodeValue("PROPOSAL_LOG_TEMPORARY_TYPE_CODE");
    }
    
    private static String getCodeValue(String paramName) {
        return KraServiceLocator.getService(ParameterService.class).getParameterValueAsString(
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, ParameterConstants.DOCUMENT_COMPONENT, paramName);
    }

}
