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

public class IacucProtocolAlternateSearchDatabase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -1319710309210165533L;
    
    private Integer iacucProtocolAltSearchDbId;
    private Integer iacucAltSearchId;
    private String alternateSearchDatabaseName;
    
    public Integer getIacucProtocolAltSearchDbId() {
        return iacucProtocolAltSearchDbId;
    }
    public void setIacucProtocolAltSearchDbId(Integer iacucProtocolAltSearchDbId) {
        this.iacucProtocolAltSearchDbId = iacucProtocolAltSearchDbId;
    }
    public Integer getIacucAltSearchId() {
        return iacucAltSearchId;
    }
    public void setIacucAltSearchId(Integer iacucProtocolAltSearchId) {
        this.iacucAltSearchId = iacucProtocolAltSearchId;
    }
    public String getAlternateSearchDatabaseName() {
        return alternateSearchDatabaseName;
    }
    public void setAlternateSearchDatabaseName(String alternateSearchDatabaseName) {
        this.alternateSearchDatabaseName = alternateSearchDatabaseName;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IacucProtocolAlternateSearchDatabase) {
            return this.getAlternateSearchDatabaseName().equals(((IacucProtocolAlternateSearchDatabase)obj).getAlternateSearchDatabaseName());
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return this.getAlternateSearchDatabaseName().hashCode();
    }
    
    
}
