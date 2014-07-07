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
package org.kuali.kra.iacuc.protocol.funding;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.framework.compliance.core.SpecialReviewType;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.protocol.protocol.funding.impl.ProtocolProposalDevelopmentDocumentServiceImplBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * 
 * This service creates Proposal Development Document from ProtocolBase for users authorized to create proposal. This created
 * proposal is then added to ProtocolBase Funding sources. 
 */
public class IacucProtocolProposalDevelopmentDocumentServiceImpl extends ProtocolProposalDevelopmentDocumentServiceImplBase implements IacucProtocolProposalDevelopmentDocumentService {

    public final static String IACUC_PROJECT_END_DATE_NUMBER_OF_YEARS = "iacucProtocolProjectEndDateNumberOfYears";
    ParameterService parameterService;
    @Override 
    protected String getSpecialReviewTypeHook()
    {
        return SpecialReviewType.ANIMAL_USAGE;
    }
    
    @Override
    protected String getProjectEndDateNumberOfYearsHook()
    {
        if ( parameterService ==null)
        {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        String parameter= parameterService.getParameterValueAsString(IacucProtocolDocument.class, IACUC_PROJECT_END_DATE_NUMBER_OF_YEARS);
        return parameter;
    }

}
