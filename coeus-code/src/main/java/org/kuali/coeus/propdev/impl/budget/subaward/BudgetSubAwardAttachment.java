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
package org.kuali.coeus.propdev.impl.budget.subaward;

import javax.persistence.*;

import org.kuali.coeus.propdev.api.budget.subaward.BudgetSubAwardAttachmentContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "BUDGET_SUB_AWARD_ATT")
public class BudgetSubAwardAttachment extends KcPersistableBusinessObjectBase implements BudgetSubAwardAttachmentContract {

    private static final long serialVersionUID = -2467480179750426256L;

    @Column(name = "ATTACHMENT")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] data;

    @Column(name = "CONTENT_ID")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @Column(name = "SUB_AWARD_NUMBER")
    private Integer subAwardNumber;

    @PortableSequenceGenerator(name = "SEQ_SUB_AWD_BGT_ATT_ID")
    @GeneratedValue(generator = "SEQ_SUB_AWD_BGT_ATT_ID")
    @Id
    @Column(name = "SUB_AWARD_ATTACHMENT_ID")
    private Long id;

    @Column(name = "BUDGET_ID")
    private Long budgetId;

    public BudgetSubAwardAttachment() {
        super();
    }

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String contentId) {
        this.name = name;
    }

    @Override
    public Integer getSubAwardNumber() {
        return subAwardNumber;
    }

    public void setSubAwardNumber(Integer subAwardNumber) {
        this.subAwardNumber = subAwardNumber;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String contentType) {
        this.type = type;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
