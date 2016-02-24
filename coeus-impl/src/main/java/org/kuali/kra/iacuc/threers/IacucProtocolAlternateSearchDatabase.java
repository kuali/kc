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
