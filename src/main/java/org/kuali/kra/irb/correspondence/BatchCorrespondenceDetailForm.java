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
package org.kuali.kra.irb.correspondence;

import java.util.ArrayList;
import java.util.List;

import org.kuali.rice.kns.web.struts.form.KualiForm;

public class BatchCorrespondenceDetailForm extends KualiForm {

    private static final long serialVersionUID = 8987463989398244188L;

    private BatchCorrespondence batchCorrespondence;
    private String batchCorrespondenceTypeCode;
    private BatchCorrespondenceDetail newBatchCorrespondenceDetail;
    private List<BatchCorrespondenceDetail> deletedBatchCorrespondenceDetail;
    
    private boolean readOnly;
    
    public BatchCorrespondenceDetailForm() {
        super();
        // if set to null in rice20, it can't be populated for batch correspondence
        this.setBatchCorrespondence(new BatchCorrespondence());
        this.setBatchCorrespondenceTypeCode(null);
        this.setNewBatchCorrespondenceDetail(new BatchCorrespondenceDetail());
        this.setDeletedBatchCorrespondenceDetail(new ArrayList<BatchCorrespondenceDetail>());
    }

    public BatchCorrespondence getBatchCorrespondence() {
        return batchCorrespondence;
    }

    public void setBatchCorrespondence(BatchCorrespondence batchCorrespondence) {
        this.batchCorrespondence = batchCorrespondence;
    }

    public String getBatchCorrespondenceTypeCode() {
        return batchCorrespondenceTypeCode;
    }

    public void setBatchCorrespondenceTypeCode(String batchCorrespondenceTypeCode) {
        this.batchCorrespondenceTypeCode = batchCorrespondenceTypeCode;
    }

    public BatchCorrespondenceDetail getNewBatchCorrespondenceDetail() {
        return newBatchCorrespondenceDetail;
    }

    public void setNewBatchCorrespondenceDetail(BatchCorrespondenceDetail newBatchCorrespondenceDetail) {
        this.newBatchCorrespondenceDetail = newBatchCorrespondenceDetail;
    }

    public List<BatchCorrespondenceDetail> getDeletedBatchCorrespondenceDetail() {
        return deletedBatchCorrespondenceDetail;
    }

    public void setDeletedBatchCorrespondenceDetail(List<BatchCorrespondenceDetail> deletedBatchCorrespondenceDetail) {
        this.deletedBatchCorrespondenceDetail = deletedBatchCorrespondenceDetail;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    

}
