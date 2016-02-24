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
package org.kuali.kra.external.award;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proposalDTO", propOrder = {
    "proposalNumber",
    "requestedStartDateTotal",
    "requestedEndDateTotal",
    "proposalTotalAmount",
    "totalDirectCostTotal",
    "totalIndirectCostTotal",
    "proposalLastUpdateDate",
    "awardTypeCode",
    "sponsorCode",
    "statusCode",
    "cfdaNumber",
    "title",
    "sponsorAwardNumber"
})
public class ProposalDTO implements Serializable {

	private static final long serialVersionUID = -7622894652760586766L;
	
    private String proposalNumber;
    private Date requestedStartDateTotal;
    private Date requestedEndDateTotal;
    private ScaleTwoDecimal proposalTotalAmount;
    private ScaleTwoDecimal totalDirectCostTotal;
    private ScaleTwoDecimal totalIndirectCostTotal;
    private Date proposalLastUpdateDate;
    private Integer awardTypeCode;
    private String sponsorCode;
    private Integer statusCode;
    private String cfdaNumber;
    private String title;
    private String sponsorAwardNumber;
    
	public String getProposalNumber() {
		return proposalNumber;
	}
	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}
	public Date getRequestedStartDateTotal() {
		return requestedStartDateTotal;
	}
	public void setRequestedStartDateTotal(Date requestedStartDateTotal) {
		this.requestedStartDateTotal = requestedStartDateTotal;
	}
	public Date getRequestedEndDateTotal() {
		return requestedEndDateTotal;
	}
	public void setRequestedEndDateTotal(Date requestedEndDateTotal) {
		this.requestedEndDateTotal = requestedEndDateTotal;
	}
	public ScaleTwoDecimal getProposalTotalAmount() {
		return proposalTotalAmount;
	}
	public void setProposalTotalAmount(ScaleTwoDecimal proposalTotalAmount) {
		this.proposalTotalAmount = proposalTotalAmount;
	}
	public ScaleTwoDecimal getTotalDirectCostTotal() {
		return totalDirectCostTotal;
	}
	public void setTotalDirectCostTotal(ScaleTwoDecimal totalDirectCostTotal) {
		this.totalDirectCostTotal = totalDirectCostTotal;
	}
	public ScaleTwoDecimal getTotalIndirectCostTotal() {
		return totalIndirectCostTotal;
	}
	public void setTotalIndirectCostTotal(ScaleTwoDecimal totalIndirectCostTotal) {
		this.totalIndirectCostTotal = totalIndirectCostTotal;
	}
	public Date getProposalLastUpdateDate() {
		return proposalLastUpdateDate;
	}
	public void setProposalLastUpdateDate(Date proposalLastUpdateDate) {
		this.proposalLastUpdateDate = proposalLastUpdateDate;
	}
	public Integer getAwardTypeCode() {
		return awardTypeCode;
	}
	public void setAwardTypeCode(Integer awardTypeCode) {
		this.awardTypeCode = awardTypeCode;
	}
	public String getSponsorCode() {
		return sponsorCode;
	}
	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getCfdaNumber() {
		return cfdaNumber;
	}
	public void setCfdaNumber(String cfdaNumber) {
		this.cfdaNumber = cfdaNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSponsorAwardNumber() {
		return sponsorAwardNumber;
	}
	public void setSponsorAwardNumber(String sponsorAwardNumber) {
		this.sponsorAwardNumber = sponsorAwardNumber;
	}
}
