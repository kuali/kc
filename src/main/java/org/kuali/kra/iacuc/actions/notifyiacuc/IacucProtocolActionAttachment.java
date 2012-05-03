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
package org.kuali.kra.iacuc.actions.notifyiacuc;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

//TODO: Looks like this entire class could be promoted to protocol package during IRB-IACUC refactoring
/**
 * 
 * This class is an attachment bean for action attachment. these information will be saved to IacucProtocolSubmissionDoc
 */
public class IacucProtocolActionAttachment implements Serializable {
    
    private static final long serialVersionUID = -7078446426502414351L;

    private String fileName;
    private transient FormFile file;
    private String description;

    public IacucProtocolActionAttachment() {
        
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
