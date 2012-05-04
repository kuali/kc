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
package org.kuali.kra.iacuc.actions.submit;

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionType;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmitActionRule;

/**
 * Validate a protocol submission to the IRB for review.
 */
/**
 * This class...
 */
public class IacucProtocolSubmitActionRule extends ProtocolSubmitActionRule {

    @Override
    protected Class<? extends ProtocolSubmissionType> getProtocolSubmissionTypeClassHook() {
        return IacucProtocolSubmissionType.class;
    }

    @Override
    protected Class<? extends ProtocolReviewType> getProtocolReviewTypeClassHook() {
        return IacucProtocolReviewType.class;
    }

    @Override
    protected Class<? extends ProtocolDocument> getProtocolDocumentClassHook() {
        return IacucProtocolDocument.class;
    }


}
