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
