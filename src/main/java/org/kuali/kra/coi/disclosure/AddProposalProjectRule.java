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
package org.kuali.kra.coi.disclosure;

import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.GlobalVariables;

public class AddProposalProjectRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddProposalProjectEvent>  {

    
    @Override
    public boolean processRules(AddProposalProjectEvent event) {
        
        CoiDisclProject coiDisclProject = event.getCoiDisclProject();
        GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());        
        getDictionaryValidationService().validateBusinessObject(coiDisclProject);
        boolean valid = GlobalVariables.getMessageMap().hasNoErrors();

        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);

        //if either is missing, it should be caught on the DD validation.
        if (coiDisclProject.getCoiProjectStartDate()!= null
                && coiDisclProject.getCoiProjectEndDate() != null) {
            if (coiDisclProject.getCoiProjectStartDate().after(
                    coiDisclProject.getCoiProjectEndDate())) {
                valid = false;
                GlobalVariables.getMessageMap().putError("coiProjectStartDate", KeyConstants.ERROR_START_DATE_AFTER_END_DATE,
                        new String[] {
                                dataDictionaryService.getAttributeErrorLabel(CoiDisclProject.class,
                                        "coiProjectStartDate"),
                                dataDictionaryService.getAttributeErrorLabel(CoiDisclProject.class,
                                        "coiProjectEndDate") });
            }
        }

    
        return valid;
    }
}
