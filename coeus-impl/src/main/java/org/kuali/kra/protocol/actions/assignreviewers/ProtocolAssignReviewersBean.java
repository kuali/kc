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
