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
package org.kuali.kra.irb.specialreview;

import org.kuali.kra.common.specialreview.rules.AddSpecialReviewRuleTestBase;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

public class AddProtocolSpecialReviewRuleTest extends AddSpecialReviewRuleTestBase<ProtocolSpecialReview> {
    
    @Override
    public Document getDocument() throws WorkflowException {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        Document document = getDocumentService().getNewDocument(ProtocolDocument.class);
        return document;
    }

    @Override
    public ProtocolSpecialReview getSpecialReview() {
        return new ProtocolSpecialReview();
    }

}

