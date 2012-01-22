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
package org.kuali.kra.committee.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondence;

/**
 * 
 * This class implements the CommitteeBatchCorrespondenceDetail business object.
 */
public class CommitteeBatchCorrespondenceDetail extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long committeeBatchCorrespondenceDetailId;

    private String committeeBatchCorrespondenceId;

    private Long protocolActionId;

    private Long protocolCorrespondenceId;

    private boolean selected;

    private CommitteeBatchCorrespondence committeeBatchCorrespondence;

    private ProtocolAction protocolAction;

    private ProtocolCorrespondence protocolCorrespondence;

    /**
     * 
     * Constructs a CommitteeBatchCorrespondenceDetail.java.
     */
    public CommitteeBatchCorrespondenceDetail() {
    }

    public Long getCommitteeBatchCorrespondenceDetailId() {
        return committeeBatchCorrespondenceDetailId;
    }

    public void setCommitteeBatchCorrespondenceDetailId(Long committeeBatchCorrespondenceDetailId) {
        this.committeeBatchCorrespondenceDetailId = committeeBatchCorrespondenceDetailId;
    }

    public String getCommitteeBatchCorrespondenceId() {
        return committeeBatchCorrespondenceId;
    }

    public void setCommitteeBatchCorrespondenceId(String committeeBatchCorrespondenceId) {
        this.committeeBatchCorrespondenceId = committeeBatchCorrespondenceId;
    }

    public Long getProtocolActionId() {
        return protocolActionId;
    }

    public void setProtocolActionId(Long protocolActionId) {
        this.protocolActionId = protocolActionId;
    }

    public Long getProtocolCorrespondenceId() {
        return protocolCorrespondenceId;
    }

    public void setProtocolCorrespondenceId(Long protocolCorrespondenceId) {
        this.protocolCorrespondenceId = protocolCorrespondenceId;
    }

    public CommitteeBatchCorrespondence getCommitteeBatchCorrespondence() {
        return committeeBatchCorrespondence;
    }

    public void setCommitteeBatchCorrespondence(CommitteeBatchCorrespondence committeeBatchCorrespondence) {
        this.committeeBatchCorrespondence = committeeBatchCorrespondence;
    }

    public ProtocolAction getProtocolAction() {
        return protocolAction;
    }

    public void setProtocolAction(ProtocolAction protocolAction) {
        this.protocolAction = protocolAction;
    }

    public ProtocolCorrespondence getProtocolCorrespondence() {
        return protocolCorrespondence;
    }

    public void setProtocolCorrespondence(ProtocolCorrespondence protocolCorrespondence) {
        this.protocolCorrespondence = protocolCorrespondence;
    }

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
