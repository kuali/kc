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
package org.kuali.kra.irb.correspondence;

import org.kuali.rice.kns.web.struts.form.KualiForm;

import java.util.ArrayList;
import java.util.List;

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
