/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import java.io.Serializable;

/**
 * This class stores form data for adding a new congressional district to a Proposal Site.
 */
public class CongressionalDistrictHelper implements Serializable {
    private static final long serialVersionUID = 5292755756053197441L;

    private String newState;
    private String newDistrictNumber;

    public void setNewState(String newState) {
        this.newState = newState;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewDistrictNumber(String newDistrictNumber) {
        this.newDistrictNumber = newDistrictNumber;
    }

    public String getNewDistrictNumber() {
        return newDistrictNumber;
    }
}
