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
package org.kuali.kra.protocol.actions;

import org.kuali.kra.protocol.actions.notify.ProtocolActionAttachment;

import java.util.List;

/**
 * 
 * This class for the base property of request/notify action
 */
public interface ProtocolSubmissionBeanBase extends ProtocolActionBean {
   

    public String getCommitteeId();
    
    public void setCommitteeId(String committeeId);

    public ProtocolActionAttachment getNewActionAttachment();
    
    public void setNewActionAttachment(ProtocolActionAttachment newActionAttachment);

    public List<ProtocolActionAttachment> getActionAttachments();

    public void setActionAttachments(List<ProtocolActionAttachment> actionAttachments);
}
