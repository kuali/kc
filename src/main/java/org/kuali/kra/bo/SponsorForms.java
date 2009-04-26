package org.kuali.kra.bo;

import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.rice.kns.util.TypedArrayList;

public class SponsorForms extends KraPersistableBusinessObjectBase {

	private String packageName;
	private Integer packageNumber;
	private String sponsorCode;
	private Sponsor sponsor;
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
