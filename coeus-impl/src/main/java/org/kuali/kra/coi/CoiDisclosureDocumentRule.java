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
package org.kuali.kra.coi;

import org.kuali.coeus.common.framework.custom.KcDocumentBaseAuditRule;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.coi.disclosure.DisclosureFinancialEntityAuditRule;
import org.kuali.kra.coi.disclosure.SaveDisclosureReporterUnitEvent;
import org.kuali.kra.coi.questionnaire.DisclosureQuestionnaireAuditRule;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * 
 * This class is the rule class for coidisclosuredocument
 */
public class CoiDisclosureDocumentRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule, DocumentAuditRule {


    @Override
    protected boolean processCustomRouteDocumentBusinessRules(Document document) {
        boolean retval = true;
        retval &= super.processCustomRouteDocumentBusinessRules(document);

        return retval;
    }

    @Override
    protected boolean processCustomSaveDocumentBusinessRules(Document document) {
        if (!(document instanceof CoiDisclosureDocument)) {
            return false;
        }

        boolean valid = true;
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) document;
        valid &= processReporterUnitRules(coiDisclosureDocument);
        return valid;
    }


    public boolean processReporterUnitRules(CoiDisclosureDocument document) {
        return processRules(new SaveDisclosureReporterUnitEvent("document.coiDisclosureList[0].disclosurePersons[0]",
                document.getCoiDisclosure().getDisclosureReporter().getDisclosurePersonUnits()));
    }

    @Override
    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }

    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        
        retval &= new KcDocumentBaseAuditRule().processRunAuditBusinessRules(document);
        retval &= new DisclosureFinancialEntityAuditRule().processRunAuditBusinessRules((CoiDisclosureDocument) document);
        retval &= new DisclosureQuestionnaireAuditRule().processRunAuditBusinessRules((CoiDisclosureDocument) document);
        return retval;
    }

}
