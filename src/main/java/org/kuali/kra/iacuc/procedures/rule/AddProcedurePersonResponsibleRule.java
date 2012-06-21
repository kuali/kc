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
package org.kuali.kra.iacuc.procedures.rule;

import java.util.List;
import java.util.StringTokenizer;

import org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class adds rule for adding new <code>ProcedurePersonResponsible</code> object
 */
public class AddProcedurePersonResponsibleRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<AddProcedurePersonResponsibleEvent> { 

    private static final String PROCEDURE_BEAN_PATH = "iacucProtocolStudyGroupBeans[";
    private static final String PROCEDURE_DETAIL_BEAN_PATH = "].iacucProtocolStudyGroupDetailBeans[";
    private static final String NEW_PROCEDURE_PERSON_RESPONSIBLE_PATH = "].newIacucProcedurePersonResponsible";
    
    
    @Override
    public boolean processRules(AddProcedurePersonResponsibleEvent event) {
        return processAddProcedurePersonResponsibleBusinessRules(event);
    }
    
    private boolean processAddProcedurePersonResponsibleBusinessRules(AddProcedurePersonResponsibleEvent event) {
        boolean rulePassed = true;
        rulePassed &= isPersonListValid(event);
        if(rulePassed) {
            rulePassed &= !isDuplicatePerson(event);
        }
        return rulePassed;
    }
    
    /**
     * This method is to verify whether one or more persons are selected during Add
     * @param event
     * @return
     */
    private boolean isPersonListValid(AddProcedurePersonResponsibleEvent event) {
        boolean personListValid = true;
        IacucProtocolStudyGroupDetailBean procedureDetailBean = event.getProcedureDetailBean();
        List<String> protocolPersonsResponsible = procedureDetailBean.getNewIacucProcedurePersonResponsible().getProtocolPersonsResponsible();
        if(ObjectUtils.isNull(protocolPersonsResponsible) || protocolPersonsResponsible.isEmpty()) {
            GlobalVariables.getMessageMap().putError(getErrorPath(event) + "personId", 
                    KeyConstants.ERROR_IACUC_VALIDATION_PERSON_RESPONSIBLE_VALID);                
            personListValid = false;
        }
        return personListValid;
    }

    /**
     * This method is to check for duplicate person in the list
     * @param event
     * @return
     */
    private boolean isDuplicatePerson(AddProcedurePersonResponsibleEvent event) {
        boolean duplicatePerson = false;
        IacucProtocolStudyGroupDetailBean procedureDetailBean = event.getProcedureDetailBean();
        List<String> protocolPersonsResponsible = procedureDetailBean.getNewIacucProcedurePersonResponsible().getProtocolPersonsResponsible();
        for(String personKey : protocolPersonsResponsible) {
            String personId = null;
            String personName = null;
            StringTokenizer personInfo = new StringTokenizer(personKey, Constants.IACUC_PROCEDURE_PERSON_RESPONSIBLE_DELIMITER); 
            while(personInfo.hasMoreTokens()) { 
                personId = personInfo.nextToken(); 
                personName = personInfo.nextToken(); 
                for(IacucProcedurePersonResponsible personResponsible : procedureDetailBean.getIacucProcedurePersonsResponsible()) {
                    if(personResponsible.getPersonId().equals(personId)) {
                        GlobalVariables.getMessageMap().putError(getErrorPath(event) + "personId", 
                                KeyConstants.ERROR_IACUC_VALIDATION_DUPLICATE_PERSON_RESPONSIBLE);                
                        duplicatePerson = true;
                    }
                }
            } 
        }
        return duplicatePerson;
    }
    
    private String getErrorPath(AddProcedurePersonResponsibleEvent event) {
        StringBuffer errorPath = new StringBuffer();
        errorPath.append(PROCEDURE_BEAN_PATH);
        errorPath.append(event.getProcedureBeanIndex());
        errorPath.append(PROCEDURE_DETAIL_BEAN_PATH);
        errorPath.append(event.getProcedureDetailBeanIndex());
        errorPath.append(NEW_PROCEDURE_PERSON_RESPONSIBLE_PATH);
        return errorPath.toString();
    }
}
