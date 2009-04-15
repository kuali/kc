/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.infrastructure;

/**
 * The set of all Permissions used by KRA.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public interface PermissionConstants {
    public static final String CREATE_PROPOSAL = "CREATE_PROPOSAL";
    public static final String MODIFY_PROPOSAL = "MODIFY_PROPOSAL";
    public static final String VIEW_PROPOSAL = "VIEW_PROPOSAL";
    public static final String MODIFY_NARRATIVE = "MODIFY_NARRATIVE";
    public static final String VIEW_NARRATIVE = "VIEW_NARRATIVE";
    public static final String MODIFY_BUDGET = "MODIFY_BUDGET";
    public static final String VIEW_BUDGET = "VIEW_BUDGET";
    public static final String MAINTAIN_PROPOSAL_ACCESS = "MAINTAIN_PROPOSAL_ACCESS";
    public static final String CERTIFY = "CERTIFY";
    public static final String PRINT_PROPOSAL = "PRINT_PROPOSAL";
    public static final String ALTER_PROPOSAL_DATA = "ALTER_PROPOSAL_DATA";
    public static final String SUBMIT_TO_SPONSOR = "SUBMIT_TO_SPONSOR";
    public static final String SUBMIT_PROPOSAL = "SUBMIT_PROPOSAL";
    public static final String ADD_PROPOSAL_VIEWER = "ADD_PROPOSAL_VIEWER";
}
