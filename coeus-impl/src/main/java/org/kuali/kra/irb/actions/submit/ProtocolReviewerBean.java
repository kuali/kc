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
