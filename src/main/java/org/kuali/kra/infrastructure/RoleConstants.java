/*
 * Copyright 2007 The Kuali Foundation.
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
package org.kuali.kra.infrastructure;

/**
 * The set of all Roles used by KRA.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface RoleConstants {
    
    // The names of the roles as used by KIM to identify a role.
    
    public static final String PROPOSAL_CREATOR = "Proposal Creator";
    public static final String AGGREGATOR = "Aggregator";
    public static final String NARRATIVE_WRITER = "Narrative Writer";
    public static final String BUDGET_CREATOR = "Budget Creator";
    public static final String VIEWER = "Viewer";
    public static final String UNASSIGNED = "unassigned";
    
    // The labels that are displayed to the user on the web page.
    // Current the same as the role names, but a separate set makes
    // it easier if a role name or label requires changing.
    
    public static final String PROPOSAL_CREATOR_LABEL = "Proposal Creator";
    public static final String AGGREGATOR_LABEL = "Aggregator";
    public static final String NARRATIVE_WRITER_LABEL = "Narrative Writer";
    public static final String BUDGET_CREATOR_LABEL = "Budget Creator";
    public static final String VIEWER_LABEL = "Viewer";
    public static final String UNASSIGNED_LABEL = "unassigned";
}
