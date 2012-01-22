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
package org.kuali.kra.subaward.bo;

import java.sql.Date;
import org.kuali.kra.subaward.bo.SubAward;

public class SubAwardCloseout extends SubAwardAssociate { 

    private static final long serialVersionUID = 1L;

    private Integer subAwardCloseoutId;
    private Long subAwardId; 
    private String subAwardCode;
    private Integer closeoutNumber;

    private Integer closeoutTypeCode;

    private Date dateRequested;

    private Date dateFollowup;

    private Date dateReceived;

    private String comments;

    private SubAward subAward;

    public SubAwardCloseout() {
    }

    public Integer getSubAwardCloseoutId() {
        return subAwardCloseoutId;
    }

    public void setSubAwardCloseoutId(Integer subAwardCloseoutId) {
        this.subAwardCloseoutId = subAwardCloseoutId;
    }

    public Long getSubAwardId() {
        return subAwardId;
    }

    public void setSubAwardId(Long subAwardId) {
        this.subAwardId = subAwardId;
    }

    public String getSubAwardCode() {
        return subAwardCode;
    }

    public void setSubAwardCode(String subAwardCode) {
        this.subAwardCode = subAwardCode;
    }

    public Integer getCloseoutNumber() {
        return closeoutNumber;
    }

    public void setCloseoutNumber(Integer closeoutNumber) {
        this.closeoutNumber = closeoutNumber;
    }

    public Integer getCloseoutTypeCode() {
        return closeoutTypeCode;
    }

    public void setCloseoutTypeCode(Integer closeoutTypeCode) {
        this.closeoutTypeCode = closeoutTypeCode;
    }

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    public Date getDateFollowup() {
        return dateFollowup;
    }

    public void setDateFollowup(Date dateFollowup) {
        this.dateFollowup = dateFollowup;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public void resetPersistenceState() {

        this.subAwardCloseoutId=null;
    }
}
