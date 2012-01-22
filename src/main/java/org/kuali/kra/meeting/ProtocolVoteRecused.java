/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.meeting;


/**
 * 
 * This class is for member recused from vote.
 */
public class ProtocolVoteRecused extends ProtocolMeetingVoter {

    private static final long serialVersionUID = 6207540592702779518L;

    private Long protocolVoteRecusedId;

    public ProtocolVoteRecused() {
    }

    public Long getProtocolVoteRecusedId() {
        return protocolVoteRecusedId;
    }

    public void setProtocolVoteRecusedId(Long protocolVoteRecusedId) {
        this.protocolVoteRecusedId = protocolVoteRecusedId;
    }
}
