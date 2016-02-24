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
    @Lob
    private byte[] data;

    @Column(name = "CONTENT_ID")
    private String name;

    @Column(name = "CONTENT_TYPE")
    private String type;

    @PortableSequenceGenerator(name = "SEQ_SUB_AWD_BGT_ATT_ID")
    @GeneratedValue(generator = "SEQ_SUB_AWD_BGT_ATT_ID")
    @Id
    @Column(name = "SUB_AWARD_ATTACHMENT_ID")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumns({ @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID"), @JoinColumn(name = "SUB_AWARD_NUMBER", referencedColumnName = "SUB_AWARD_NUMBER") })
    private BudgetSubAwards budgetSubAward;

    public BudgetSubAwardAttachment() {
        super();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String contentId) {
        this.name = contentId;
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
        this.type = contentType;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public BudgetSubAwards getBudgetSubAward() {
		return budgetSubAward;
	}

	public void setBudgetSubAward(BudgetSubAwards budgetSubAward) {
		this.budgetSubAward = budgetSubAward;
	}
	
	@Override
	public Integer getSubAwardNumber() {
		if (budgetSubAward != null) {
			return budgetSubAward.getSubAwardNumber();
		} else {
			return null;
		}
	}

	@Override
	public Long getBudgetId() {
		if (budgetSubAward != null) {
			return budgetSubAward.getBudgetId();
		} else {
			return null;
		}		
	}	
}
