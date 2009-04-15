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
 * Tasks are placed into groups corresponding to the object they
 * relate to.  For example, the "saveProposal" task corresponds to
 * the "proposal" group.  This file contains the constants for the
 * names of the Task Groups.  The group names must correspond to the 
 * values in the SpringBeans.xml.
 */
public interface TaskGroupName {
    
    public static final String APPLICATION = "application";
    public static final String PROPOSAL = "proposal";
    public static final String NARRATIVE = "narrative";
    public static final String BUDGET = "budget";
}
