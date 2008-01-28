/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.document.authorization;

import java.util.HashMap;
import java.util.Map;

import org.kuali.core.authorization.AuthorizationConstants;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.document.Document;
import org.kuali.core.document.authorization.DocumentActionFlags;
import org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase;
import org.kuali.core.exceptions.DocumentInitiationAuthorizationException;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.service.UnitAuthorizationService;

/**
 * The Proposal Development Document Authorizer.  Primarily responsible for determining if
 * a user has permission to create/modify/view proposals.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
   
    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#getEditMode(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    public Map getEditMode(Document doc, UniversalUser user) {

        ProposalDevelopmentDocument proposalDoc = (ProposalDevelopmentDocument) doc;
        
        ProposalAuthorizationService proposalAuthService = (ProposalAuthorizationService) KraServiceLocator.getService(ProposalAuthorizationService.class);
        UnitAuthorizationService unitAuthService = (UnitAuthorizationService) KraServiceLocator.getService(UnitAuthorizationService.class);
        
        Map editModeMap = new HashMap();
        Integer proposalNbr = proposalDoc.getProposalNumber();
        
        // The getEditMode() method is invoked when a proposal is accessed for creation and when it
        // is accessed for modification.  New proposals under creation don't have a proposal number.
        // For a new proposal, we have to know if the user has the permission to create a proposal.
        // For a current proposal, we have to know if the user the permission to modify or view the proposal.

        if (proposalNbr == null) {
            if (hasCreatePermission(user)) {
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, "TRUE");
            } else {
                editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, "TRUE");
            }
        } else {
            if (proposalAuthService.hasPermission(user, proposalDoc, PermissionConstants.MODIFY_PROPOSAL)) {
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, "TRUE");
            }
            else if (proposalAuthService.hasPermission(user, proposalDoc, PermissionConstants.VIEW_PROPOSAL)) {
                editModeMap.put(AuthorizationConstants.EditMode.VIEW_ONLY, "TRUE");
            }
            else {
                // TODO: fix this!!!  At this time, the permissions to modify or view a proposal
                // have not been added.  If we do things correctly, no one will be able to modify/view
                // an existing proposal.  Until such time that we add permission for modify/view, we
                // will let anyone have full access to the proposal.
                //editModeMap.put(AuthorizationConstants.EditMode.UNVIEWABLE, "TRUE");
                editModeMap.put(AuthorizationConstants.EditMode.FULL_ENTRY, "TRUE");
            }
        }
 
        return editModeMap;
    }

    /**
     * @see org.kuali.core.document.authorization.DocumentAuthorizerBase#canInitiate(java.lang.String,
     *      org.kuali.core.bo.user.UniversalUser)
     */
    @Override
    public void canInitiate(String documentTypeName, UniversalUser user) {
        super.canInitiate(documentTypeName, user);
        if (!hasCreatePermission(user)) {
            throw new DocumentInitiationAuthorizationException(KeyConstants.ERROR_AUTHORIZATION_DOCUMENT_INITIATION, 
                                                               new String[] { user.getPersonUserIdentifier(), documentTypeName });
        }
    }
    
    /**
     * Does the user have permission to create a proposal.  Use the Unit Authorization Service to determine
     * if the user has the CREATE_PROPOSAL permission in any unit.
     * @param user the user
     * @return true if the user has the CREATE_PROPOSAL permission in at least one unit; otherwise false
     */
    private boolean hasCreatePermission(UniversalUser user) {
        UnitAuthorizationService auth = (UnitAuthorizationService) KraServiceLocator.getService(UnitAuthorizationService.class);
        return auth.hasPermission(user, PermissionConstants.CREATE_PROPOSAL);
    }

    /**
     * @see org.kuali.core.document.authorization.TransactionalDocumentAuthorizerBase#getDocumentActionFlags(org.kuali.core.document.Document, org.kuali.core.bo.user.UniversalUser)
     */
    @Override
    public DocumentActionFlags getDocumentActionFlags(Document document, UniversalUser user) {
        // no copy button
        DocumentActionFlags flags = super.getDocumentActionFlags(document, user);
        flags.setCanCopy(false);
        // NEED TO REDO ANNOTATE CHECK SINCE CHANGED THE VALUE OF FLAGS
        this.setAnnotateFlag(flags);

        return flags;
    }

}
