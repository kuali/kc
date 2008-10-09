package org.kuali.kra.bo;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.core.util.TypedArrayList;

@IdClass(org.kuali.kra.bo.id.SponsorFormsId.class)
@Entity
@Table(name="SPONSOR_FORMS")
public class SponsorForms extends KraPersistableBusinessObjectBase {

	@Column(name="PACKAGE_NAME")
	private String packageName;
	@Id
	@Column(name="PACKAGE_NUMBER")
	private Integer packageNumber;
	@Id
	@Column(name="SPONSOR_CODE")
	private String sponsorCode;
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="SPONSOR_CODE", insertable=false, updatable=false)
	private Sponsor sponsor;
    @OneToMany(cascade={CascadeType.PERSIST}, 
           targetEntity=org.kuali.kra.bo.SponsorFormTemplate.class, mappedBy="sponsorForms")
	private List<SponsorFormTemplate> sponsorFormTemplates;


	public SponsorForms(){
		super();
        sponsorFormTemplates = new TypedArrayList(SponsorFormTemplate.class);
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Integer getPackageNumber() {
		return packageNumber;
	}

	public void setPackageNumber(Integer packageNumber) {
		this.packageNumber = packageNumber;
	}

	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("packageName", getPackageName());
		hashMap.put("packageNumber", getPackageNumber());
		hashMap.put("sponsorCode", getSponsorCode());
		return hashMap;
	}

    public final Sponsor getSponsor() {
        return sponsor;
    }

    public final void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public final List<SponsorFormTemplate> getSponsorFormTemplates() {
        return sponsorFormTemplates;
    }

    public final void setSponsorFormTemplates(List<SponsorFormTemplate> sponsorFormTemplates) {
        this.sponsorFormTemplates = sponsorFormTemplates;
    }

}

