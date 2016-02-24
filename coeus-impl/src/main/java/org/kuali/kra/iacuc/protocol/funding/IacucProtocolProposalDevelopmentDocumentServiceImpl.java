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
