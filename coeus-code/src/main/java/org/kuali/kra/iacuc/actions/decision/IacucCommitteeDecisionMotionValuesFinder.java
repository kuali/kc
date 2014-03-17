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
package org.kuali.kra.iacuc.actions.decision;

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeDecisionMotionValuesFinder;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolReviewType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.document.Document;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class IacucCommitteeDecisionMotionValuesFinder extends CommitteeDecisionMotionValuesFinder {
       

    private static final long serialVersionUID = -449881228899486120L;
         
    /*
     * We replace the default descriptions of SRR and SMR with IACUC-specific labels, and filter out 
     * the disallowed motion types 
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> returnList;
        List<KeyValue> originalList = super.getKeyValues();
        List<KeyValue> newList = new ArrayList<KeyValue>();
        
        for(KeyValue originalKV: originalList) {
            if(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS.equals(originalKV.getKey())) {
                newList.add(new ConcreteKeyValue(CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS, "Minor Revisions"));
            }
            else if(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(originalKV.getKey())) {
                newList.add(new ConcreteKeyValue(CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED, "Major Revisions"));
            }
            else {
                newList.add(originalKV); 
            }
        }
        
        // filter out the motions that we don't wish to list in the drop down (for example those with follow-up actions that are not allowed)
        returnList = removeDisallowedMotions(newList);
        
        return returnList;
    }
    
     
    // TODO Ideally we should check if the motion is allowed in the current state of the protocol and submission, i.e. use the follow-up service and 
    // the protocol action service to determine if the follow-up actions for each motion can be performed---and only then should the motion be allowed 
    // to appear in the selection drop down.
    // Currently, however, we will make do with a hardcoded check for review type of FYI, and defer the more robust check for later. 
    private List<KeyValue> removeDisallowedMotions(List<KeyValue> srcList) {
        
        List<KeyValue> returnList = new ArrayList<KeyValue>();
        IacucProtocol protocol = getProtocol();
        boolean isFYI = false;
        boolean isDMR = false;
        
        if(null != protocol) {
            IacucProtocolSubmission submission = (IacucProtocolSubmission) protocol.getProtocolSubmission();
            if( (null != submission) && (IacucProtocolReviewType.FYI.equals(submission.getProtocolReviewTypeCode())) ) { 
                isFYI = true;
            }
            else if( (null != submission) && (IacucProtocolReviewType.DESIGNATED_MEMBER_REVIEW.equals(submission.getProtocolReviewTypeCode())) ) { 
                isDMR = true;
            }
        }
        
        for(KeyValue srcKV: srcList) {
            if(isFYI) { 
                // we filter out major revisions, minor revisions and approve motions for FYI
                if(!( CommitteeDecisionMotionType.APPROVE.equals(srcKV.getKey()) ||
                       CommitteeDecisionMotionType.SPECIFIC_MINOR_REVISIONS.equals(srcKV.getKey()) ||
                       CommitteeDecisionMotionType.SUBSTANTIVE_REVISIONS_REQUIRED.equals(srcKV.getKey()) )) {
                    returnList.add(srcKV);
                }
            }
            else if(isDMR) {
                // we filter out disapprove motion
                if(!CommitteeDecisionMotionType.DISAPPROVE.equals(srcKV.getKey())) {
                    returnList.add(srcKV);
                }
            }
            else {
                // just copy over all motions
                returnList.add(srcKV);
            }
        }
        
        return returnList;
    }


    private IacucProtocol getProtocol() {
        IacucProtocol retVal = null;
        Document document = getDocument();
        if (document != null && document instanceof IacucProtocolDocument) {
            retVal = (IacucProtocol) ((IacucProtocolDocument) document).getProtocol();
        }
        
        return retVal;
    }

}