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
