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
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;

public class AddProtocolStudyGroupEvent extends KraDocumentEventBaseExtension {

    private IacucProtocolStudyGroupBean procedureBean;
    private Integer procedureBeanIndex;
    
    public AddProtocolStudyGroupEvent(IacucProtocolDocument document, IacucProtocolStudyGroupBean procedureBean, 
            Integer procedureBeanIndex) {
        super("Add Study Group", "", document);
        this.procedureBean = procedureBean;
        this.procedureBeanIndex = procedureBeanIndex;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public BusinessRuleInterface getRule() {
        return new AddProtocolStudyGroupRule();
    }

    public Integer getProcedureBeanIndex() {
        return procedureBeanIndex;
    }

    public void setProcedureBeanIndex(Integer procedureBeanIndex) {
        this.procedureBeanIndex = procedureBeanIndex;
    }

    public IacucProtocolStudyGroupBean getProcedureBean() {
        return procedureBean;
    }

    public void setProcedureBean(IacucProtocolStudyGroupBean procedureBean) {
        this.procedureBean = procedureBean;
    }

}
