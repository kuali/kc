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
package org.kuali.kra.protocol.actions.decision;

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.ProtocolOnlineReviewCommentable;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBeanBase;

import java.io.Serializable;
import java.util.List;

/**
 * This class is a bean for managing the input for a committee decision.
 */
public interface CommitteeDecision<CP extends CommitteePersonBase> extends ProtocolActionBean, ProtocolOnlineReviewCommentable, Serializable {
    
    /**
     * This method initializes the class.
     */
    public void init();
    
    public Integer getRecusedCount();

    public void setRecusedCount(Integer recusedCount);
    
    public String getMotionTypeCode();

    public void setMotionTypeCode(String commDecisionMotionTypeCode);

    public Integer getNoCount();

    public void setNoCount(Integer noCount);

    public Integer getYesCount();

    public void setYesCount(Integer yesCount);

    public Integer getAbstainCount();

    public void setAbstainCount(Integer abstainCount);

    public String getVotingComments();

    public void setVotingComments(String votingComments);
    
    public CommitteeDecisionMotionType getMotionType();

    public void setMotionType(CommitteeDecisionMotionType motionType);

    public List<CP> getAbstainers();

    public void setAbstainers(List<CP> abstainers);
    
    public List<CP> getAbstainersToDelete();

    public List<CP> getRecused();

    public void setRecused(List<CP> recused);
    
    public List<CP> getRecusedToDelete();

    public CP getNewAbstainer();

    public void setNewAbstainer(CP newAbstainer);

    public CP getNewRecused();

    public void setNewRecused(CP newRecused);
    
    public int getTotalVoteCount();
    
    public int getYesCountValue();
    
    public int getNoCountValue();

    public void setReviewCommentsBean(ReviewCommentsBeanBase reviewCommentsBean);


}
