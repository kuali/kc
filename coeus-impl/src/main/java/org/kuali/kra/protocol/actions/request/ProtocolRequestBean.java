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
package org.kuali.kra.protocol.actions.request;

import org.kuali.kra.protocol.actions.ProtocolSubmissionBeanBase;
import org.kuali.kra.protocol.questionnaire.ProtocolSubmissionQuestionnaireHelper;

import java.io.Serializable;

/**
 * The ProtocolRequestBean is used for some of the common, yet simple,
 * protocol request actions.  Those actions are:
 * 
 * 1. Request to Close
 * 2. Request for Suspension
 * 3. Request to Close Enrollment
 * 4. Request to Re-open Enrollment
 * 5. Request for Data Analysis Only
 * 
 * For each of these request actions, a user can select a committee and give
 * a reason for the request.  Each request, though, will require a different
 * protocol action type and submission type entry in the database.  Please
 * see the ActionHelperBase class for how this class is used.
 */
public interface ProtocolRequestBean extends ProtocolSubmissionBeanBase, Serializable {
   

    public void setReason(String reason);
    
    public String getReason();

    public String getProtocolActionTypeCode();

    public String getSubmissionTypeCode();
    
    public String getBeanName();

    public void setBeanName(String beanName);
    
    public ProtocolSubmissionQuestionnaireHelper getQuestionnaireHelper();
}
