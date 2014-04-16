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
package org.kuali.coeus.common.framework.print;

import org.kuali.coeus.sys.api.model.KcFile;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

/**
 * 
 * This class helps create the foundation of attachment data sources.
 */
@MappedSuperclass
public abstract class AttachmentDataSource extends KcPersistableBusinessObjectBase implements KcFile {

    @Column(name = "FILE_NAME")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @Column(name = "DATA")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] data;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
