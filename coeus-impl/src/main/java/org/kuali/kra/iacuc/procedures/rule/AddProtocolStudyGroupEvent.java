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
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean;

public class AddProtocolStudyGroupEvent extends KcDocumentEventBaseExtension {

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
    public KcBusinessRule getRule() {
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
