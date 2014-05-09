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
package org.kuali.kra.s2s.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class S2sUserAttachedFormAtt extends KraPersistableBusinessObjectBase { 
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5561511868250800552L;
    private Long s2sUserAttachedFormAttId; 
    private Long s2sUserAttachedFormId; 
    private String proposalNumber; 
    private String contentType; 
    private String fileName; 
    private String contentId; 
    private List<S2sUserAttachedFormAttFile> s2sUserAttachedFormAttFileList;
    
    public S2sUserAttachedFormAtt() { 
        s2sUserAttachedFormAttFileList = new ArrayList<S2sUserAttachedFormAttFile>();
    } 
    
    public Long getS2sUserAttachedFormAttId() {
        return s2sUserAttachedFormAttId;
    }

    public void setS2sUserAttachedFormAttId(Long s2sUserAttachedFormAttId) {
        this.s2sUserAttachedFormAttId = s2sUserAttachedFormAttId;
    }

    public Long getS2sUserAttachedFormId() {
        return s2sUserAttachedFormId;
    }

    public void setS2sUserAttachedFormId(Long s2sUserAttachedFormId) {
        this.s2sUserAttachedFormId = s2sUserAttachedFormId;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public void setProposalNumber(String proposalNumber) {
        this.proposalNumber = proposalNumber;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * Gets the s2sUserAttachedFormAttFileList attribute. 
     * @return Returns the s2sUserAttachedFormAttFileList.
     */
    public List<S2sUserAttachedFormAttFile> getS2sUserAttachedFormAttFileList() {
        return s2sUserAttachedFormAttFileList;
    }

    /**
     * Sets the s2sUserAttachedFormAttFileList attribute value.
     * @param s2sUserAttachedFormAttFileList The s2sUserAttachedFormAttFileList to set.
     */
    public void setS2sUserAttachedFormAttFileList(List<S2sUserAttachedFormAttFile> s2sUserAttachedFormAttFileList) {
        this.s2sUserAttachedFormAttFileList = s2sUserAttachedFormAttFileList;
    }

}