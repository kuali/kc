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
import org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation;

/**
 * This class hooks Rule to Event in KNS
 */
public class AddProcedureLocationEvent extends KcDocumentEventBaseExtension {

    private IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation;
    private IacucProtocolDocument iacucProtocolDocument;
    
    public AddProcedureLocationEvent(IacucProtocolDocument document, IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation) { 
        super("Add Procedure Location", "", document);
        this.iacucProtocolStudyGroupLocation = iacucProtocolStudyGroupLocation;
        this.iacucProtocolDocument = document;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public KcBusinessRule getRule() {
        return new AddProcedureLocationRule();
    }

    public IacucProtocolStudyGroupLocation getIacucProtocolStudyGroupLocation() {
        return iacucProtocolStudyGroupLocation;
    }

    public void setIacucProtocolStudyGroupLocation(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation) {
        this.iacucProtocolStudyGroupLocation = iacucProtocolStudyGroupLocation;
    }

    public IacucProtocolDocument getIacucProtocolDocument() {
        return iacucProtocolDocument;
    }

    public void setIacucProtocolDocument(IacucProtocolDocument iacucProtocolDocument) {
        this.iacucProtocolDocument = iacucProtocolDocument;
    }


}
