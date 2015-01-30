/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.institutionalproposal.document.authorization;

import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Map;

/**
 * This class is the Institutional Proposal Document Authorizer.  It determines the edit modes and
 * document actions for all institutional proposal documents.
 */
public class InstitutionalProposalDocumentAuthorizer extends TransactionalDocumentAuthorizerBase 
    implements TransactionalDocumentAuthorizer {
    
    public static final String ALLOW_INIT_FOR_DISAPPROVED_PD_SESSION_KEY = "DISAPPROVED_PD_WITH_LINKED_IP";
   
    @Override
    protected void addRoleQualification(
            Object primaryBusinessObjectOrDocument,
            Map<String, String> attributes) {
        super.addRoleQualification(primaryBusinessObjectOrDocument, attributes);
        InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) primaryBusinessObjectOrDocument;
        if (institutionalProposalDocument.getInstitutionalProposal() != null 
                && institutionalProposalDocument.getInstitutionalProposal().getLeadUnit() != null) {
            attributes.put(KcKimAttributes.UNIT_NUMBER, institutionalProposalDocument.getInstitutionalProposal().getLeadUnit().getUnitNumber());
        } else {
            attributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        }
    }    
    
    @Override
    public boolean canInitiate(String documentTypeName, Person user) {
        if (GlobalVariables.getUserSession().getObjectMap().get(ALLOW_INIT_FOR_DISAPPROVED_PD_SESSION_KEY) != null) {
            GlobalVariables.getUserSession().removeObject(ALLOW_INIT_FOR_DISAPPROVED_PD_SESSION_KEY);
            return true;
        } else {
            return super.canInitiate(documentTypeName, user);
        }
    }    
}
