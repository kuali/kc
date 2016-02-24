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
