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
