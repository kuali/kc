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
package org.kuali.kra.protocol.summary;

import java.io.Serializable;

public class ResearchAreaSummary implements Serializable {

    private static final long serialVersionUID = 4781269721650065533L;
    
    private String researchAreaCode;
    private String description;
    
    private boolean changed;

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setResearchAreaCode(String researchAreaCode) {
        this.researchAreaCode = researchAreaCode;
    }
    
    public String getResearchAreaCode() {
        return researchAreaCode;
    }

    public void compare(ProtocolSummary other) {
        ResearchAreaSummary otherResearchArea = other.findResearchArea(researchAreaCode);
        changed = (otherResearchArea == null);
    }
    
    public boolean isChanged() {
        return changed;
    }
}
