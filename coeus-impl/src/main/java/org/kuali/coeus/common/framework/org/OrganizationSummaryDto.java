package org.kuali.coeus.common.framework.org;

import org.kuali.coeus.common.framework.rolodex.RolodexDto;

import com.codiform.moo.annotation.Property;

public class OrganizationSummaryDto {

    private String organizationId;
    private String organizationName;
    private String address;
    @Property(translate = true, source = "rolodex")
    private RolodexDto contact;
    
	public String getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public RolodexDto getContact() {
		return contact;
	}
	public void setContact(RolodexDto contact) {
		this.contact = contact;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
}
