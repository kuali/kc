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
package org.kuali.coeus.propdev.impl.s2s;

import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormAttFileContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.*;

@Entity
@Table(name = "S2S_USER_ATTD_FORM_ATT_FILE")
public class S2sUserAttachedFormAttFile extends KcPersistableBusinessObjectBase implements S2sUserAttachedFormAttFileContract { 

    private static final long serialVersionUID = 435752267453958277L;

    @PortableSequenceGenerator(name = "SEQ_S2S_USR_ATD_FRM_ATT_FLE_ID")
    @GeneratedValue(generator = "SEQ_S2S_USR_ATD_FRM_ATT_FLE_ID")
    @Id
    @Column(name = "S2S_USER_ATTD_FORM_ATT_FILE_ID")
    private Long id;

    @Column(name = "S2S_USER_ATTACHED_FORM_ATT_ID")
    private Long s2sUserAttachedFormAttId;

    @Column(name = "ATTACHMENT")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] attachment;

    public Long getS2sUserAttachedFormAttId() {
        return s2sUserAttachedFormAttId;
    }

    public void setS2sUserAttachedFormAttId(Long s2sUserAttachedFormAttId) {
        this.s2sUserAttachedFormAttId = s2sUserAttachedFormAttId;
    }

    @Override
    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
    
}