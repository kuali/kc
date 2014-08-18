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
package org.kuali.kra.protocol.correspondence;

import org.kuali.rice.kns.web.struts.form.KualiForm;

import java.util.ArrayList;
import java.util.List;

public abstract class BatchCorrespondenceDetailFormBase extends KualiForm {

    private static final long serialVersionUID = 8987463989398244188L;

    private BatchCorrespondenceBase batchCorrespondence;
    private String batchCorrespondenceTypeCode;
    private BatchCorrespondenceDetailBase newBatchCorrespondenceDetail;
    private List<BatchCorrespondenceDetailBase> deletedBatchCorrespondenceDetail;
    
    private boolean readOnly;
    
    public BatchCorrespondenceDetailFormBase() {
        super();
        // if set to null in rice20, it can't be populated for batch correspondence
        this.setBatchCorrespondence(getNewBatchCorrespondenceInstanceHook());
        this.setBatchCorrespondenceTypeCode(null);
        this.setNewBatchCorrespondenceDetail(getNewBatchCorrespondenceDetailInstanceHook());
        this.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetailBase>());
    }
    
    protected abstract BatchCorrespondenceBase getNewBatchCorrespondenceInstanceHook();
    
    protected abstract BatchCorrespondenceDetailBase getNewBatchCorrespondenceDetailInstanceHook();
    

    public BatchCorrespondenceBase getBatchCorrespondence() {
        if(null == batchCorrespondence) {
            this.setBatchCorrespondence(getNewBatchCorrespondenceInstanceHook());
        }
        return batchCorrespondence;
    }

    public void setBatchCorrespondence(BatchCorrespondenceBase batchCorrespondence) {
        this.batchCorrespondence = batchCorrespondence;
    }

    public String getBatchCorrespondenceTypeCode() {
        return batchCorrespondenceTypeCode;
    }

    public void setBatchCorrespondenceTypeCode(String batchCorrespondenceTypeCode) {
        this.batchCorrespondenceTypeCode = batchCorrespondenceTypeCode;
    }

    public BatchCorrespondenceDetailBase getNewBatchCorrespondenceDetail() {
        return newBatchCorrespondenceDetail;
    }

    public void setNewBatchCorrespondenceDetail(BatchCorrespondenceDetailBase newBatchCorrespondenceDetail) {
        this.newBatchCorrespondenceDetail = newBatchCorrespondenceDetail;
    }

    public List<BatchCorrespondenceDetailBase> getDeletedBatchCorrespondenceDetail() {
        return deletedBatchCorrespondenceDetail;
    }

    public void setDeletedBatchCorrespondenceDetail(List<BatchCorrespondenceDetailBase> deletedBatchCorrespondenceDetail) {
        this.deletedBatchCorrespondenceDetail = deletedBatchCorrespondenceDetail;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    

}
