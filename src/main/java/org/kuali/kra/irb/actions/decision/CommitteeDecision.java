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
package org.kuali.kra.irb.actions.decision;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.irb.actions.reviewcomments.ReviewerComments;
import org.kuali.kra.irb.actions.reviewcomments.ReviewerCommentsBean;

@SuppressWarnings("serial")
public class CommitteeDecision extends ReviewerCommentsBean implements Serializable{

    private String motion;
    private Integer noCount;
    private Integer yesCount;
    private Integer abstainCount;
    private String votingComments;
    
    private CommitteePerson newAbstainer = new CommitteePerson();
    private CommitteePerson newRecused = new CommitteePerson();
    
    private List<CommitteePerson> abstainers = new ArrayList<CommitteePerson>();
    private List<CommitteePerson> recused = new ArrayList<CommitteePerson>();

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public Integer getNoCount() {
        return noCount;
    }

    public void setNoCount(Integer noCount) {
        this.noCount = noCount;
    }

    public Integer getYesCount() {
        return yesCount;
    }

    public void setYesCount(Integer yesCount) {
        this.yesCount = yesCount;
    }

    public Integer getAbstainCount() {
        return abstainCount;
    }

    public void setAbstainCount(Integer abstainCount) {
        this.abstainCount = abstainCount;
    }

    public String getVotingComments() {
        return votingComments;
    }

    public void setVotingComments(String votingComments) {
        this.votingComments = votingComments;
    }

    public List<CommitteePerson> getAbstainers() {
        return abstainers;
    }

    public void setAbstainers(List<CommitteePerson> abstainers) {
        this.abstainers = abstainers;
    }

    public List<CommitteePerson> getRecused() {
        return recused;
    }

    public void setRecused(List<CommitteePerson> recused) {
        this.recused = recused;
    }

    public CommitteePerson getNewAbstainer() {
        return newAbstainer;
    }

    public void setNewAbstainer(CommitteePerson newAbstainer) {
        this.newAbstainer = newAbstainer;
    }

    public CommitteePerson getNewRecused() {
        return newRecused;
    }

    public void setNewRecused(CommitteePerson newRecused) {
        this.newRecused = newRecused;
    }
}
