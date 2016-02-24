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
package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * Common convenience methods for the Proposal Development module.
 */
public final class ProposalDevelopmentUtils {
    
    public static final String ACTIVITY_TYPE_CODE_CONSTRUCTION_PARM = "ACTIVITY_TYPE_CODE_CONSTRUCTION";
    public static final String ACTIVITY_TYPE_CODE_RESEARCH_PARM = "ACTIVITY_TYPE_CODE_RESEARCH";
    

    public static final String PROPOSAL_TYPE_CODE_NEW_PARM = "PROPOSAL_TYPE_CODE_NEW";
    public static final String PROPOSAL_TYPE_CODE_RESUBMISSION_PARM = "PROPOSAL_TYPE_CODE_RESUBMISSION";
    public static final String PROPOSAL_TYPE_CODE_RENEWAL_PARM = "PROPOSAL_TYPE_CODE_RENEWAL";
    public static final String PROPOSAL_TYPE_CODE_CONTINUATION_PARM = "PROPOSAL_TYPE_CODE_CONTINUATION";
    public static final String PROPOSAL_TYPE_CODE_REVISION_PARM = "PROPOSAL_TYPE_CODE_REVISION";
    public static final String PROPOSAL_TYPE_CODE_TASK_ORDER_PARM = "PROPOSAL_TYPE_CODE_TASK_ORDER";
    public static final String AUDIT_INCOMPLETE_PROPOSAL_ATTATCHMENTS_PARM = "AUDIT_INCOMPLETE_PROPOSAL_ATTACHMENTS";
    public static String KEY_PERSON_CERTIFICATION_DEFERRAL_PARM = "KEY_PERSON_CERTIFICATION_DEFERRAL";
    
    public static final String PROPOSAL_PI_CITIZENSHIP_TYPE_PARM = "PROPOSAL_PI_CITIZENSHIP_TYPE";
    
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
        ParameterService parameterService = KcServiceLocator.getService(ParameterService.class);
        return parameterService.getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, 
                ParameterConstants.DOCUMENT_COMPONENT, 
                parmName);
    }
    
}
        
