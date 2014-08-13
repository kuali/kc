/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.committee.document.authorization;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeDocumentAuthorizerBase;
import org.kuali.coeus.common.committee.impl.document.authorization.CommitteeTaskBase;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.infrastructure.TaskName;

/**
 * This class is the Committee Document Authorizer.  It determines the edit modes and
 * document actions for all committee documents.
 */
public class CommitteeDocumentAuthorizer extends CommitteeDocumentAuthorizerBase {


    private static final long serialVersionUID = 6464453088283772104L;

    @Override
    protected String getAddCommitteeTaskNameHook() {
        return TaskName.ADD_COMMITTEE;
    }

    @Override
    protected CommitteeTaskBase getNewCommitteeTaskInstanceHook(String taskName, CommitteeBase committee) {
        return new CommitteeTaskBase<Committee>(TaskGroupName.COMMITTEE, taskName, (Committee) committee) {};
    }
}
