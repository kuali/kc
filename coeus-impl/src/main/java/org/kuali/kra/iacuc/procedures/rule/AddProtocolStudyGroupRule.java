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
package org.kuali.kra.iacuc.procedures.rule;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroup;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.List;

public class AddProtocolStudyGroupRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<AddProtocolStudyGroupEvent> {

    private static final String PROCEDURE_BEAN_PATH = "iacucProtocolStudyGroupBeans";
    private IacucProtocolProcedureService iacucProtocolProcedureService;
    
    @Override
    public boolean processRules(AddProtocolStudyGroupEvent event) {
        return processAddStudyGroupBusinessRules(event);
    }
    
    private boolean processAddStudyGroupBusinessRules(AddProtocolStudyGroupEvent event) {
        boolean rulePassed = true;
        rulePassed &= isStudyGroupValid(event);
        if(rulePassed && getIacucProtocolProcedureService().isProcedureViewedBySpecies()) {
            rulePassed &= !isDuplicateStudyGroup(event);
        }
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

    /**
     * This method is to check for duplicate study group in the list
     * @param event
     * @return
     */
    private boolean isDuplicateStudyGroup(AddProtocolStudyGroupEvent event) {
        boolean duplicateStudyGroup = false;
        IacucProtocolStudyGroupBean procedureBean = event.getProcedureBean();
        List<String> protocolSpeciesAndGroups =  procedureBean.getProtocolSpeciesAndGroups(); 
        for(String iacucProtocolSpeciesId : protocolSpeciesAndGroups) {
            Integer newProtocolSpeciesId = Integer.parseInt(iacucProtocolSpeciesId);
            for(IacucProtocolStudyGroup detailBean : procedureBean.getIacucProtocolStudyGroups()) {
                if(detailBean.getIacucProtocolSpeciesId().equals(newProtocolSpeciesId)) {
                    GlobalVariables.getMessageMap().putError(getErrorPath(event), 
                            KeyConstants.ERROR_IACUC_VALIDATION_DUPLICATE_STUDY_GROUP);                
                    duplicateStudyGroup = true;
                }
            }
        }
        return duplicateStudyGroup;
    }
    
    private String getErrorPath(AddProtocolStudyGroupEvent event) {
        StringBuffer errorPath = new StringBuffer();
        errorPath.append(PROCEDURE_BEAN_PATH);
        errorPath.append("[");
        errorPath.append(event.getProcedureBeanIndex());
        errorPath.append("]");
        return errorPath.toString();
    }
    
    public IacucProtocolProcedureService getIacucProtocolProcedureService() {
        if(ObjectUtils.isNull(iacucProtocolProcedureService)) {
            iacucProtocolProcedureService = KcServiceLocator.getService("iacucProtocolProcedureService");
        }
        return iacucProtocolProcedureService;
    }

}
