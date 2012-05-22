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
package org.kuali.kra.protocol.actions.decision;

import java.io.Serializable;
import java.util.List;
import org.kuali.kra.common.committee.bo.CommitteeDecisionMotionType;
import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.ProtocolOnlineReviewCommentable;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewAttachmentsBean;
import org.kuali.kra.protocol.actions.reviewcomments.ReviewCommentsBean;

/**
 * This class is a bean for managing the input for a committee decision.
 */
public interface CommitteeDecision extends ProtocolActionBean, ProtocolOnlineReviewCommentable, Serializable {
    
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

    public List<CommitteePerson> getAbstainers();

    public void setAbstainers(List<CommitteePerson> abstainers);
    
    public List<CommitteePerson> getAbstainersToDelete();

    public List<CommitteePerson> getRecused();

    public void setRecused(List<CommitteePerson> recused);
    
    public List<CommitteePerson> getRecusedToDelete();

    public CommitteePerson getNewAbstainer();

    public void setNewAbstainer(CommitteePerson newAbstainer);

    public CommitteePerson getNewRecused();

    public void setNewRecused(CommitteePerson newRecused);
    
    public int getTotalVoteCount();

    public void setReviewCommentsBean(ReviewCommentsBean reviewCommentsBean);


}