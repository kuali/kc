/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.protocol.research;

import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.protocol.ProtocolAssociateBase;

public abstract class ProtocolResearchAreaBase extends ProtocolAssociateBase {

    private static final long serialVersionUID = -1522011425745031200L;

    private Long id;

    private String researchAreaCode;

    private ResearchAreaBase researchAreas;

    public ProtocolResearchAreaBase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResearchAreaCode() {
        return researchAreaCode;
    }

    public void setResearchAreaCode(String researchAreaCode) {
        this.researchAreaCode = researchAreaCode;
    }

    public ResearchAreaBase getResearchAreas() {
        return researchAreas;
    }

    public void setResearchAreas(ResearchAreaBase researchAreas) {
        this.researchAreas = researchAreas;
    }

    @Override
    public void resetPersistenceState() {
        this.setId(null);
    }
}
