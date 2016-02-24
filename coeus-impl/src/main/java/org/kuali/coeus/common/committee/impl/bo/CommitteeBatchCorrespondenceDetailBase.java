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
package org.kuali.coeus.common.committee.impl.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondence;

/**
 * 
 * This class implements the CommitteeBatchCorrespondenceDetailBase business object.
 */
public abstract class CommitteeBatchCorrespondenceDetailBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private Long committeeBatchCorrespondenceDetailId;

    private String committeeBatchCorrespondenceId;

    private Long protocolActionId;

    private Long protocolCorrespondenceId;

    private boolean selected;

    private CommitteeBatchCorrespondenceBase committeeBatchCorrespondence;

    private ProtocolActionBase protocolAction;

    private ProtocolCorrespondence protocolCorrespondence;


    public CommitteeBatchCorrespondenceDetailBase() {
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

    public CommitteeBatchCorrespondenceBase getCommitteeBatchCorrespondence() {
        return committeeBatchCorrespondence;
    }

    public void setCommitteeBatchCorrespondence(CommitteeBatchCorrespondenceBase committeeBatchCorrespondence) {
        this.committeeBatchCorrespondence = committeeBatchCorrespondence;
    }

    public ProtocolActionBase getProtocolAction() {
        return protocolAction;
    }

    public void setProtocolAction(ProtocolActionBase protocolAction) {
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
