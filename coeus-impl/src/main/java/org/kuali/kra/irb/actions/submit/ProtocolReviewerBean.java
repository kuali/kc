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
package org.kuali.kra.irb.actions.submit;

import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.protocol.actions.submit.ProtocolReviewerBeanBase;

/**
 * This class is really just a "form" for the reviewers that
 * are displayed to the user in the Submit for Review Action.
 * It is only displayed from BusinessObject in order to use
 * the Data Dictionary for displaying controls on the web page.
 */
public class ProtocolReviewerBean extends ProtocolReviewerBeanBase {

    
    

    private static final long serialVersionUID = 647867490941129499L;

    public ProtocolReviewerBean() {
        super();
    }

    public ProtocolReviewerBean(CommitteeMembership member) {
        super(member);
    }

}
