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
package org.kuali.kra.protocol.actions.assignreviewers;

import org.kuali.kra.protocol.actions.ProtocolActionBean;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;

import java.io.Serializable;
import java.util.List;

/**
 * This class is really just a "form" for assigning a protocol
 * to one or more reviewers.
 */
public interface ProtocolAssignReviewersBean extends ProtocolActionBean, Serializable {
    
    /**
     * Create the list of reviewers based upon the current committee
     * and schedule, and assigns their reviewer types if any have been saved in the past
     */
    public void prepareView();
    
    public List<ProtocolReviewerBeanBase> getReviewers();
    
    public ProtocolReviewerBeanBase getReviewer(int i);
    
    /**
     * We display the reviewers in two columns.  These are the
     * reviewers in the left column.
     * @return
     */
    public List<ProtocolReviewerBeanBase> getLeftReviewers();
    
    /**
     * We display the reviewers in two columns.  These are the
     * reviewers in the right column.
     * @return
     */
    public List<ProtocolReviewerBeanBase> getRightReviewers();
    
}
