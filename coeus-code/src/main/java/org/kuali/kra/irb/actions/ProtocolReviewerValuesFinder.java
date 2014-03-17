/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.actions;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.submit.ProtocolReviewer;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.actions.submit.ProtocolSubmissionStatus;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.document.Document;

import java.util.ArrayList;
import java.util.List;


public class ProtocolReviewerValuesFinder extends IrbActionsKeyValuesBase {
    

    private static final long serialVersionUID = 6339476452241934050L;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        Protocol protocol = getProtocol();
        if (protocol != null) {
            ProtocolSubmission submission = getCurrentSubmission(protocol);
            if (submission != null) {
                List<ProtocolReviewer> reviewers = (List)submission.getProtocolReviewers();
                for (ProtocolReviewer reviewer : reviewers) {
                    keyValues.add(new ConcreteKeyValue(reviewer.getProtocolReviewerId().toString(), reviewer.getFullName()));
                }
            }
        }
        
        return keyValues;
    }

    @SuppressWarnings("unchecked")
    private ProtocolSubmission getCurrentSubmission(Protocol protocol) {
        List<ProtocolSubmission> protocolSubmissions = (List)protocol.getProtocolSubmissions();
        for (ProtocolSubmission submission : protocolSubmissions) {
            if (StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.IN_AGENDA) ||
                StringUtils.equals(submission.getSubmissionStatusCode(), ProtocolSubmissionStatus.SUBMITTED_TO_COMMITTEE)) {
                return submission;
            }
        }
        return null;
    }

    private Protocol getProtocol() {
        Document document = getDocument();
        if (document instanceof ProtocolDocument) {
            return ((ProtocolDocument) document).getProtocol();
        }
        return null;
    }
}
