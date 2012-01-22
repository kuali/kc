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
package org.kuali.kra.authorizer;

import java.util.HashSet;
import java.util.Set;

import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentPresentationController;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentPresentationControllerBase;
import org.kuali.rice.krad.document.Document;

public class ResearchDocumentPresentationController 
    extends TransactionalDocumentPresentationControllerBase 
    implements TransactionalDocumentPresentationController {
    
    /**
     * 
     * @see org.kuali.rice.kns.document.authorization.DocumentPresentationController#getEditMode(org.kuali.rice.krad.document.Document)
     */
    public Set<String> getEditModes(Document document) {
        Set<String> editModes = new HashSet();
        editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
        return editModes;
        //return new AwardDocumentAuthorizer().getEditModes(document, user, editModes);
    }
    
}
