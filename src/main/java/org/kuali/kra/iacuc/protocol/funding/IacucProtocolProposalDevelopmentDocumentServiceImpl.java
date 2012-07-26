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
package org.kuali.kra.iacuc.protocol.funding;

import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.common.specialreview.service.impl.SpecialReviewServiceImpl;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.protocol.funding.impl.ProtocolProposalDevelopmentDocumentServiceImpl;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.specialreview.ProposalSpecialReview;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

/**
 * 
 * This service creates Proposal Development Document from Protocol for users authorized to create proposal. This created
 * proposal is then added to Protocol Funding sources. 
 */
public class IacucProtocolProposalDevelopmentDocumentServiceImpl extends ProtocolProposalDevelopmentDocumentServiceImpl implements IacucProtocolProposalDevelopmentDocumentService {

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
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        String parameter= parameterService.getParameterValueAsString(IacucProtocolDocument.class, IACUC_PROJECT_END_DATE_NUMBER_OF_YEARS);
        return parameter;
    }

}