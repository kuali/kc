/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

/**
 * This class hooks Rule to Event in KNS
 */
public class AddProcedurePersonResponsibleEvent extends KraDocumentEventBaseExtension {

    private IacucProtocolStudyGroupDetailBean procedureDetailBean;
    private Integer procedureBeanIndex;
    private Integer procedureDetailBeanIndex;
    
    public AddProcedurePersonResponsibleEvent(IacucProtocolDocument document, IacucProtocolStudyGroupDetailBean procedureDetailBean, 
            Integer procedureBeanIndex, Integer procedureDetailBeanIndex) {
        super("Add Person Responsible", "", document);
        this.procedureDetailBean = procedureDetailBean;
        this.procedureBeanIndex = procedureBeanIndex;
        this.procedureDetailBeanIndex = procedureDetailBeanIndex;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public BusinessRuleInterface getRule() {
        return new AddProcedurePersonResponsibleRule();
    }

    public IacucProtocolStudyGroupDetailBean getProcedureDetailBean() {
        return procedureDetailBean;
    }

    public void setProcedureDetailBean(IacucProtocolStudyGroupDetailBean procedureDetailBean) {
        this.procedureDetailBean = procedureDetailBean;
    }

    public Integer getProcedureDetailBeanIndex() {
        return procedureDetailBeanIndex;
    }

    public void setProcedureDetailBeanIndex(Integer procedureDetailBeanIndex) {
        this.procedureDetailBeanIndex = procedureDetailBeanIndex;
    }

    public Integer getProcedureBeanIndex() {
        return procedureBeanIndex;
    }

    public void setProcedureBeanIndex(Integer procedureBeanIndex) {
        this.procedureBeanIndex = procedureBeanIndex;
    }

}
