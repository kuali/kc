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
package org.kuali.kra.iacuc.procedures.rule;

import java.util.List;

import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

public class AddProtocolStudyGroupRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddProtocolStudyGroupEvent>{

    private static final String PROCEDURE_BEAN_PATH = "iacucProtocolStudyGroupBeans";
    
    @Override
    public boolean processRules(AddProtocolStudyGroupEvent event) {
        return processAddStudyGroupBusinessRules(event);
    }
    
    private boolean processAddStudyGroupBusinessRules(AddProtocolStudyGroupEvent event) {
        boolean rulePassed = true;
        rulePassed &= isStudyGroupValid(event);
        return rulePassed;
    }
    
    /**
     * This method is to verify whether one or more groups/persons are selected during Add
     * @param event
     * @return
     */
    private boolean isStudyGroupValid(AddProtocolStudyGroupEvent event) {
        boolean studyGroupValid = true;
        IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean = event.getProcedureBean();
        List<String> protocolSpeciesAndGroups =  selectedIacucProtocolStudyGroupBean.getProtocolSpeciesAndGroups(); 
        if(ObjectUtils.isNull(protocolSpeciesAndGroups)) {
            GlobalVariables.getMessageMap().putError(getErrorPath(event), 
                    KeyConstants.ERROR_IACUC_VALIDATION_STUDY_GROUP_VALID);                
            studyGroupValid = false;
        }
        return studyGroupValid;
    }

    private String getErrorPath(AddProtocolStudyGroupEvent event) {
        StringBuffer errorPath = new StringBuffer();
        errorPath.append(PROCEDURE_BEAN_PATH);
        errorPath.append("[");
        errorPath.append(event.getProcedureBeanIndex());
        errorPath.append("]");
        return errorPath.toString();
    }
}
