/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi;

/**
 * 
 * This class are the common API for all projects that need coi disclosure
 */
public interface Disclosurable {
// TODO : this should keep expanding when we moving forward for coi implementation
    /**
     * 
     * This method is mainly to get the title
     * @return
     */
    String getProjectName();
    
    /**
     * 
     * This method to get project number
     * @return
     */
    String getProjectId();
}
