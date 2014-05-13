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
package org.kuali.coeus.propdev.impl.s2s;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.propdev.api.s2s.S2sProviderContract;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

import javax.persistence.*;

@Entity
@Table(name = "S2S_PROVIDERS")
public class S2sProvider extends KcPersistableBusinessObjectBase implements MutableInactivatable, S2sProviderContract {

    private static final long serialVersionUID = 7014451402458842899L;

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CONNECTOR_SERVICE_NAME")
    private String connectorServiceName;

    @Column(name = "ACTIVE")
    @Convert(converter = BooleanYNConverter.class)
    private boolean active = true;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getConnectorServiceName() {
        return connectorServiceName;
    }

    public void setConnectorServiceName(String connectorServiceName) {
        this.connectorServiceName = connectorServiceName;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
