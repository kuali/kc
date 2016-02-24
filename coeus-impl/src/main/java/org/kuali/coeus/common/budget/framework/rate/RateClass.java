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
package org.kuali.coeus.common.budget.framework.rate;

import org.kuali.coeus.common.budget.api.rate.RateClassContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

@Entity
@Table(name = "RATE_CLASS")
public class RateClass extends KcPersistableBusinessObjectBase implements RateClassContract {

    @Id
    @Column(name = "RATE_CLASS_CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RATE_CLASS_TYPE")
    private String rateClassTypeCode;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "RATE_CLASS_TYPE", referencedColumnName = "RATE_CLASS_TYPE", insertable = false, updatable = false)
    private RateClassType rateClassType;

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRateClassTypeCode() {
        return rateClassTypeCode;
    }

    public void setRateClassTypeCode(String rateClassTypeCode) {
        this.rateClassTypeCode = rateClassTypeCode;
    }

    @Override
    public RateClassType getRateClassType() {
        return rateClassType;
    }

    public void setRateClassType(RateClassType rateClassType) {
        this.rateClassType = rateClassType;
    }
}
