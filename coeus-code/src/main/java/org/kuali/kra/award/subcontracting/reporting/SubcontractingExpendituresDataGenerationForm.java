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
package org.kuali.kra.award.subcontracting.reporting;

import org.kuali.rice.kns.web.struts.form.KualiForm;

import java.sql.Date;

@SuppressWarnings("deprecation")
public class SubcontractingExpendituresDataGenerationForm extends KualiForm {  


    private static final long serialVersionUID = 6069838251546593746L;
    
    private Date rangeStartDate;
    private Date rangeEndDate;
    
    public SubcontractingExpendituresDataGenerationForm() {
        super();
    }
    
    public void setRangeStartDate(Date rangeStartDate) {
        this.rangeStartDate = rangeStartDate;
    }


    public Date getRangeStartDate() {
        return rangeStartDate;
    }


    public void setRangeEndDate(Date rangeEndDate) {
        this.rangeEndDate = rangeEndDate;
    }


    public Date getRangeEndDate() {
        return rangeEndDate;
    }

}
