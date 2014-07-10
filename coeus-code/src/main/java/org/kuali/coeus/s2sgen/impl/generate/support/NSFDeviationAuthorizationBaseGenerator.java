/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.generate.support;

import org.kuali.coeus.propdev.api.abstrct.ProposalAbstractContract;
import org.kuali.coeus.s2sgen.impl.generate.S2SBaseFormGenerator;

/**
 * This abstract class has methods that are common to all the versions of NSFDeviationAuthorization form.
 *  
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class NSFDeviationAuthorizationBaseGenerator extends S2SBaseFormGenerator {

    protected static final String DEVIATION_AUTHORIZATION = "15";

    /**
     * 
     * This method returns the abstract Details based on the AbstractTypeCode from the ProposalAbstract
     * 
     * @param abstractType (String)  abstract type code.
     * @return abstractText (String) abstract details corresponding to the abstract type. 
     */
    protected String getAbstractText(String abstractType) {

        String abstractText = null;
        for (ProposalAbstractContract proposalAbstract : pdDoc.getDevelopmentProposal().getProposalAbstracts()) {
            if (proposalAbstract.getAbstractType() != null && proposalAbstract.getAbstractType().getCode().equals(abstractType)) {
                abstractText = proposalAbstract.getAbstractDetails();
                break;
            }
        }
        return abstractText;
    }

}
