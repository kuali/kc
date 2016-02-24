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
package org.kuali.kra.institutionalproposal.proposallog;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
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
        return KcServiceLocator.getService(ParameterService.class).getParameterValueAsString(
                InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE, ParameterConstants.DOCUMENT_COMPONENT, paramName);
    }

}
