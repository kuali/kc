/*
 * Copyright 2005-2006 The Kuali Foundation.
 * 
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.rice.shim;

import org.kuali.rice.kew.actionrequest.Recipient;
import org.kuali.rice.kew.user.AuthenticationUserId;
import org.kuali.rice.kew.user.EmplId;
import org.kuali.rice.kew.user.WorkflowUserId;

/**
 * Represents a user within Workflow.  A user has a series of Identifiers as well as
 * the user's name and email address.
 * 
 * @see UserService
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public interface WorkflowUser extends Recipient {

    public WorkflowUserId getWorkflowUserId();
    public String getWorkflowId();
    public AuthenticationUserId getAuthenticationUserId();
    public EmplId getEmplId();
    //public UuId getUuId();
    public String getGivenName();
    public String getLastName();
    public String getDisplayName();
    public String getEmailAddress();
    public String getTransposedName();
    public String getGivenNameSafe();
    public String getLastNameSafe();
    public String getDisplayNameSafe();
    public String getEmailAddressSafe();
    public String getTransposedNameSafe();
    
    public boolean isNameRestricted();
    public boolean isEmailRestricted();
    public boolean hasId();
   
    public void setWorkflowUserId(WorkflowUserId workflowUserId);
    public void setAuthenticationUserId(AuthenticationUserId authenticationUserId);
    public void setEmplId(EmplId emplId);
    //public void setUuId(UuId uuId);
    public void setGivenName(String givenName);
    public void setLastName(String lastName);
    public void setDisplayName(String displayName);
    public void setEmailAddress(String emailAddress);
    
    public void setDtype(String dtype);
    public String getDtype();
}