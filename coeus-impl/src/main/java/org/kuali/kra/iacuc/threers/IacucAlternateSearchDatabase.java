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
package org.kuali.kra.iacuc.threers;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class IacucAlternateSearchDatabase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 5292404765020148399L;

    private String alternateSearchDbCode;
    private String alternateSearchDbName;
    
    public String getAlternateSearchDbCode() {
        return alternateSearchDbCode;
    }
    
    public void setAlternateSearchDbCode(String alternateSearchDbCode) {
        this.alternateSearchDbCode = alternateSearchDbCode;
    }
    
    public String getAlternateSearchDbName() {
        return alternateSearchDbName;
    }
    
    public void setAlternateSearchDbName(String alternateSearchDbName) {
        this.alternateSearchDbName = alternateSearchDbName;
    }
}
