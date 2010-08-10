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
package org.kuali.kra.award.rule;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.rules.SpecialReviewRuleTestBase;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.util.GlobalVariables;

public class AwardSpecialReviewRuleTest extends SpecialReviewRuleTestBase<AwardSpecialReview> {

    @Override
    public Document getDocument() throws WorkflowException, Exception {
        GlobalVariables.setUserSession(new UserSession("quickstart"));
        Document document = getDocumentService().getNewDocument(AwardDocument.class);
        return document;
    }

    @Override
    public AwardSpecialReview getSpecialReview() {
        return new AwardSpecialReview();
    }

}
