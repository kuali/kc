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
package org.kuali.kra.subaward.reporting.printing.xmlstream;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.hierarchy.SponsorHierarchy;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryAmounts;
import org.kuali.kra.award.subcontracting.reporting.SubcontractingExpenditureCategoryAmountsInDateRange;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.printing.schema.NameAndAddressTypeDocument.NameAndAddressType;
import org.kuali.kra.printing.schema.SubcontractReportPageType;
import org.kuali.kra.printing.schema.SubcontractReportPageType.VendorType;
import org.kuali.kra.printing.schema.SubcontractReportsDocument;
import org.kuali.kra.printing.schema.SubcontractReportsDocument.SubcontractReports;
import org.kuali.kra.printing.schema.SubcontractReportsDocument.SubcontractReports.AdministeringOfficial;
import org.kuali.kra.printing.schema.SubcontractReportsDocument.SubcontractReports.CompanyInfo;
import org.kuali.kra.printing.schema.SubcontractReportsDocument.SubcontractReports.ContractorType;
import org.kuali.kra.printing.schema.SubcontractReportsDocument.SubcontractReports.ReportingPeriod;
import org.kuali.kra.subaward.reporting.printing.SubAwardPrintType;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.math.BigDecimal;
import java.util.*;

public class SubawardXmlStream implements XmlStream {

    private static final String SF_295_REPORT = "SF295";
    private static final String ORGANIZATION_ID = "000001";

    private BusinessObjectService businessObjectService;
    private UnitService unitService;
    private String awardNumber;

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public String getAwardNumber() {
        return awardNumber;
    }

    public void setAwardNumber(String awardNumber) {
        this.awardNumber = awardNumber;
    }

    @Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject,
            Map<String, Object> reportParameters) {
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        SubcontractReportsDocument subcontractReportsDocument = SubcontractReportsDocument.Factory.newInstance();
        this.awardNumber = (String) reportParameters.get("awardNumber");

        SubcontractReports subcontractReports = SubcontractReports.Factory.newInstance();
        setCompanyInfo(subcontractReports);
        if (reportParameters.get("printType").equals(SF_295_REPORT)) {
            setAdminActivity295(subcontractReports);
        }
        else {
            setAdminActivity(subcontractReports);
        }
        setContractorType(subcontractReports);
        setOfficials(subcontractReports);
        setReportingPeriod(subcontractReports);

        Calendar calendar = Calendar.getInstance();
        subcontractReports.setDateSubmitted(calendar);
        subcontractReportsDocument.setSubcontractReports(subcontractReports);

        xmlObjectList.put(SubAwardPrintType.SUB_AWARD_SF_294_PRINT_TYPE.getSubAwardPrintType(), subcontractReportsDocument);
        return xmlObjectList;
    }

    public void setCompanyInfo(SubcontractReports subcontractReports) {
        Map<String, String> organizationMap = new HashMap<String, String>();
        organizationMap.put("organizationId", ORGANIZATION_ID);
        Organization organization = businessObjectService.findByPrimaryKey(Organization.class, organizationMap);

        Map<String, String> rolodexMap = new HashMap<String, String>();
        rolodexMap.put("rolodexId", organization.getContactAddressId().toString());
        Rolodex rolodex = businessObjectService.findByPrimaryKey(Rolodex.class, rolodexMap);

        CompanyInfo companyInfo = CompanyInfo.Factory.newInstance();
        NameAndAddressType nameAndAddressType = NameAndAddressType.Factory.newInstance();
        nameAndAddressType.setName(organization.getOrganizationName());
        nameAndAddressType.setCity(rolodex.getCity());
        nameAndAddressType.setState(rolodex.getState());
        nameAndAddressType.setZipCode(rolodex.getPostalCode());
        nameAndAddressType.setStreetAddress(rolodex.getAddressLine1() + " " + rolodex.getAddressLine2() + " "
                + rolodex.getAddressLine3());
        subcontractReports.setContractorIDNumber(organization.getDunsNumber());
        companyInfo.setNameAndAddressType(nameAndAddressType);
        subcontractReports.setCompanyInfo(companyInfo);
    }

    public void setReportingPeriod(SubcontractReports subcontractReports) {
        ReportingPeriod reportingPeriod = ReportingPeriod.Factory.newInstance();

        Date fromDate = new Date();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Date toDate = DateUtils.newDate(year, 9, 30);
        BigDecimal months = getMonthsBetweenDates(fromDate, toDate);
        if (months.intValue() > 0) {
            reportingPeriod.setIsMarchReport(true);
            reportingPeriod.setIsSeptReport(false);
        }
        else {
            reportingPeriod.setIsMarchReport(false);
            reportingPeriod.setIsSeptReport(true);
        }
        subcontractReports.setFiscalYearReportStart(String.valueOf(year));
        subcontractReports.setReportingPeriod(reportingPeriod);
    }

    private BigDecimal getMonthsBetweenDates(Date pFrom, Date pTo) {
        ScaleTwoDecimal projectDuration = null;
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTimeInMillis(pFrom.getTime());
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTimeInMillis(pTo.getTime());
        int yd = 12 * (calendarEnd.get(Calendar.YEAR) - calendarStart.get(Calendar.YEAR));
        int md = (calendarEnd.get(Calendar.MONTH) - calendarStart.get(Calendar.MONTH));
        int dd = (calendarEnd.get(Calendar.DAY_OF_MONTH) - calendarStart.get(Calendar.DAY_OF_MONTH));
        if (dd >= 15)
            md++;
        int result = yd + md;
        projectDuration = new ScaleTwoDecimal(result);
        return projectDuration.bigDecimalValue().setScale(0);
    }

    public void setAdminActivity(SubcontractReports subcontractReports) {
        SubcontractReportPageType subcontractReportPageType = SubcontractReportPageType.Factory.newInstance();
        String administrativeActivity;
        List<SubcontractReportPageType> SubcontractReportPageTypeList = new ArrayList<SubcontractReportPageType>();

        Map<String, String> awardMap = new HashMap<String, String>();
        awardMap.put("awardNumber", this.awardNumber);
        List<Award> awardList = (List<Award>) businessObjectService.findMatching(Award.class, awardMap);
        Award award = awardList.get(0);
        String sponsorCode = award.getPrimeSponsorCode();
        if (award.getPrimeSponsorCode() == null) {
            sponsorCode = award.getSponsorCode();
        }

        if (award != null) {
            Map<String, String> hierarchyMap = new HashMap<String, String>();
            hierarchyMap.put("hierarchyName", "Administering Activity");
            hierarchyMap.put("sponsorCode", sponsorCode);
            List<SponsorHierarchy> sponsorHierarchyList = (List<SponsorHierarchy>) businessObjectService.findMatching(
                    SponsorHierarchy.class, hierarchyMap);

            administrativeActivity = sponsorHierarchyList.get(0).getLevel1().toUpperCase();
            subcontractReportPageType.setAdministeringActivity(administrativeActivity);

            if (!(administrativeActivity.equalsIgnoreCase("ARMY") || administrativeActivity.equalsIgnoreCase("NAVY")
                    || administrativeActivity.equalsIgnoreCase("AIR FORCE") || administrativeActivity.equalsIgnoreCase("NASA")
                    || administrativeActivity.equalsIgnoreCase("DOE") || administrativeActivity.equalsIgnoreCase("DLA") || administrativeActivity
                        .equalsIgnoreCase("GSA"))) {

                Map<String, String> sponsorMap = new HashMap<String, String>();
                sponsorMap.put("sponsorCode", sponsorCode);
                List<Sponsor> sponsorList = (List<Sponsor>) businessObjectService.findMatching(Sponsor.class, sponsorMap);
                String sponsor = null;
                if (sponsorList.size() > 0) {
                    sponsor = sponsorList.get(0).getSponsorName().toUpperCase();
                }
                subcontractReportPageType.setSponsor(sponsor);
            }
            setVendorType(subcontractReportPageType);
            SubcontractReportPageTypeList.add(subcontractReportPageType);
        }

        subcontractReports.setSubcontractReportPageArray(SubcontractReportPageTypeList.toArray(new SubcontractReportPageType[0]));
    }

    public void setContractorType(SubcontractReports subcontractReports) {
        ContractorType contractorType = ContractorType.Factory.newInstance();

        Map<String, String> awardMap = new HashMap<String, String>();
        awardMap.put("awardNumber", awardNumber);
        List<Award> awardList = (List<Award>) businessObjectService.findMatching(Award.class, awardMap);
        String awardNum = awardList.get(0).getSponsorAwardNumber();
        contractorType.setPrimeContractNumber(awardNum);
        contractorType.setIsPrime(true);
        contractorType.setIsSub(false);
        subcontractReports.setIncludeIndirectCosts(false);
        subcontractReports.setContractorType(contractorType);
    }

    public void setVendorType(SubcontractReportPageType subcontractReportPageType) {

        SubcontractingExpenditureCategoryAmounts subcontractingExpenditureCategoryAmounts = getBusinessObjectService()
                .findBySinglePrimaryKey(SubcontractingExpenditureCategoryAmounts.class, this.awardNumber);
        if(subcontractingExpenditureCategoryAmounts != null){
            BigDecimal totalAmount = subcontractingExpenditureCategoryAmounts.getLargeBusinessExpenditureAmount()
                    .add(subcontractingExpenditureCategoryAmounts.getSmallBusinessExpenditureAmount()).bigDecimalValue();

            List<VendorType> vendorTypeList = new ArrayList<SubcontractReportPageType.VendorType>();

            VendorType vendorTypeLargeBusiness = VendorType.Factory.newInstance();
            vendorTypeLargeBusiness.setTypeOfVendor("LARGE BUSINESS");
            vendorTypeLargeBusiness.setActualAmount(subcontractingExpenditureCategoryAmounts.getLargeBusinessExpenditureAmount()
                    .bigDecimalValue());
            vendorTypeLargeBusiness.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts
                    .getLargeBusinessExpenditureAmount().bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeLargeBusiness);

            VendorType vendorTypeSmallBusiness = VendorType.Factory.newInstance();
            vendorTypeSmallBusiness.setTypeOfVendor("SMALL BUSINESS");
            vendorTypeSmallBusiness.setActualAmount(subcontractingExpenditureCategoryAmounts.getSmallBusinessExpenditureAmount()
                    .bigDecimalValue());
            vendorTypeSmallBusiness.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts
                    .getSmallBusinessExpenditureAmount().bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeSmallBusiness);

            VendorType vendorTypeWomenOwned = VendorType.Factory.newInstance();
            vendorTypeWomenOwned.setTypeOfVendor("WOMAN OWNED");
            vendorTypeWomenOwned.setActualAmount(subcontractingExpenditureCategoryAmounts.getWomanOwnedExpenditureAmount()
                    .bigDecimalValue());
            vendorTypeWomenOwned.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts.getWomanOwnedExpenditureAmount()
                    .bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeWomenOwned);

            VendorType vendorTypeDisadvantaged = VendorType.Factory.newInstance();
            vendorTypeDisadvantaged.setTypeOfVendor("DISADVANTAGED BUSINESS");
            vendorTypeDisadvantaged.setActualAmount(subcontractingExpenditureCategoryAmounts.getEightADisadvantageExpenditureAmount()
                    .bigDecimalValue());
            vendorTypeDisadvantaged.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts
                    .getEightADisadvantageExpenditureAmount().bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeDisadvantaged);

            VendorType vendorTypeHub = VendorType.Factory.newInstance();
            vendorTypeHub.setTypeOfVendor("HUB");
            vendorTypeHub.setActualAmount(subcontractingExpenditureCategoryAmounts.getHubZoneExpenditureAmount().bigDecimalValue());
            vendorTypeHub.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts.getHubZoneExpenditureAmount()
                    .bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeHub);

            VendorType vendorTypeVet = VendorType.Factory.newInstance();
            vendorTypeVet.setTypeOfVendor("VET");
            vendorTypeVet
                    .setActualAmount(subcontractingExpenditureCategoryAmounts.getVeteranOwnedExpenditureAmount().bigDecimalValue());
            vendorTypeVet.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts.getVeteranOwnedExpenditureAmount()
                    .bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeVet);

            VendorType vendorTypeSdvo = VendorType.Factory.newInstance();
            vendorTypeSdvo.setTypeOfVendor("SDVO");
            vendorTypeSdvo.setActualAmount(subcontractingExpenditureCategoryAmounts.getServiceDisabledVeteranOwnedExpenditureAmount()
                    .bigDecimalValue());
            vendorTypeSdvo.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts
                    .getServiceDisabledVeteranOwnedExpenditureAmount().bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeSdvo);

            VendorType vendorTypeHbcu = VendorType.Factory.newInstance();
            vendorTypeHbcu.setTypeOfVendor("HBCU");
            vendorTypeHbcu.setActualAmount(subcontractingExpenditureCategoryAmounts.getHistoricalBlackCollegeExpenditureAmount()
                    .bigDecimalValue());
            vendorTypeHbcu.setActualPercent(getPct(subcontractingExpenditureCategoryAmounts
                    .getHistoricalBlackCollegeExpenditureAmount().bigDecimalValue(), totalAmount));
            vendorTypeList.add(vendorTypeHbcu);
            subcontractReportPageType.setVendorTypeArray(vendorTypeList.toArray(new VendorType[0]));
        }
    }

    public void setOfficials(SubcontractReports subcontractReports) {
        AdministeringOfficial administeringOfficial = AdministeringOfficial.Factory.newInstance();

        Map<String, String> unitAdministratorMap = new HashMap<String, String>();
        unitAdministratorMap.put("unitNumber", unitService.getTopUnit().getUnitNumber());
        unitAdministratorMap.put("unitAdministratorTypeCode", "2");
        List<UnitAdministrator> unitAdministratorList = (List<UnitAdministrator>) businessObjectService.findMatching(
                UnitAdministrator.class, unitAdministratorMap);
        for (UnitAdministrator unitAdministrator : unitAdministratorList) {
            KcPerson person = KcServiceLocator.getService(KcPersonService.class).getKcPersonByPersonId(
                    unitAdministrator.getPersonId());
            administeringOfficial.setName(person.getFullName());
            administeringOfficial.setPhoneNumber(person.getPhoneNumber());
            administeringOfficial.setTitle(person.getDirectoryTitle());
        }
        subcontractReports.setAdministeringOfficial(administeringOfficial);
    }

    public void setAdminActivity295(SubcontractReports subcontractReports) {
        List<SubcontractReportPageType> subcontractReportPageTypeList = new ArrayList<SubcontractReportPageType>();
        SubcontractingExpenditureCategoryAmountsInDateRange expenditureCategoryAmounts = null;
       
        List<SubcontractingExpenditureCategoryAmountsInDateRange> expenditureCategoryAmountsList = (List<SubcontractingExpenditureCategoryAmountsInDateRange>) getBusinessObjectService()
                .findAll(SubcontractingExpenditureCategoryAmountsInDateRange.class);

        for (SubcontractingExpenditureCategoryAmountsInDateRange expenditureCategoryAmount : expenditureCategoryAmountsList) {
            if (expenditureCategoryAmount.getAwardNumber().equalsIgnoreCase(this.awardNumber))
                expenditureCategoryAmounts = expenditureCategoryAmount;
        }
        populateSubcontractReportPage("ARMY", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("NAVY", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("AIRFORCE", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("DLA", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("NASA", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("GSA", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("DOE", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("DOD", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("NIH", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("NSF", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("EPA", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("JET", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("EPA", subcontractReportPageTypeList, expenditureCategoryAmounts);
        populateSubcontractReportPage("OTHER", subcontractReportPageTypeList, expenditureCategoryAmounts);

        subcontractReports.setSubcontractReportPageArray(subcontractReportPageTypeList.toArray(new SubcontractReportPageType[0]));
    }
    
    public void populateSubcontractReportPage(String administratingActivity,List<SubcontractReportPageType> subcontractReportPageTypeList,
            SubcontractingExpenditureCategoryAmountsInDateRange expenditureCategoryAmounts){
        
        SubcontractReportPageType subcontractReportPageType = SubcontractReportPageType.Factory.newInstance();
        subcontractReportPageType.setAdministeringActivity(administratingActivity);
        List<VendorType> vendorTypeList = new ArrayList<SubcontractReportPageType.VendorType>();
        vendorTypeList.addAll(populateVendorType(administratingActivity, expenditureCategoryAmounts));
        subcontractReportPageType.setVendorTypeArray(vendorTypeList.toArray(new VendorType[0]));
        subcontractReportPageTypeList.add(subcontractReportPageType);
    }
    
    public List<VendorType> populateVendorType(String sponsorGroup, SubcontractingExpenditureCategoryAmountsInDateRange sECAIDR) {
        
        
        ScaleTwoDecimal largeBusinessTotal = get295AmountForSponsorGroupFirst(sponsorGroup,"LARGE BUSINESS").add(
                get295AmountForSponsorGroupSecond(sponsorGroup,"LARGE BUSINESS"));
        ScaleTwoDecimal smallBusinessTotal = get295AmountForSponsorGroupFirst(sponsorGroup,"SMALL BUSINESS").add(
                get295AmountForSponsorGroupSecond(sponsorGroup,"SMALL BUSINESS"));
        ScaleTwoDecimal totalAmount =largeBusinessTotal.add(smallBusinessTotal);
        
        List<VendorType> vendorTypeList = new ArrayList<SubcontractReportPageType.VendorType>();        
        
        populateVendorTypeAmounts(sponsorGroup, "LARGE BUSINESS", vendorTypeList, totalAmount);
        populateVendorTypeAmounts(sponsorGroup, "SMALL BUSINESS", vendorTypeList, totalAmount);
        populateVendorTypeAmounts(sponsorGroup, "WOMAN OWNED", vendorTypeList, totalAmount);
        populateVendorTypeAmounts(sponsorGroup, "DISADVANTAGED BUSINESS", vendorTypeList, totalAmount);
        populateVendorTypeAmounts(sponsorGroup, "HUB", vendorTypeList, totalAmount);
        populateVendorTypeAmounts(sponsorGroup, "VET", vendorTypeList, totalAmount);
        populateVendorTypeAmounts(sponsorGroup, "SDVO", vendorTypeList, totalAmount);
        populateVendorTypeAmounts(sponsorGroup, "HBCU", vendorTypeList, totalAmount);      

        return vendorTypeList;
    }
    
    public void populateVendorTypeAmounts(String sponsorGroup,String vendorType, List<VendorType> vendorTypeList,ScaleTwoDecimal totalAmount) {
        VendorType vendorTypes = VendorType.Factory.newInstance();
        ScaleTwoDecimal amount = get295AmountForSponsorGroupFirst(sponsorGroup,vendorType).add(get295AmountForSponsorGroupSecond(sponsorGroup,vendorType));
        vendorTypes.setTypeOfVendor(vendorType);
        vendorTypes.setActualAmount(amount.bigDecimalValue());
        vendorTypes.setActualPercent(getPct(amount.bigDecimalValue(), totalAmount.bigDecimalValue()));
        vendorTypeList.add(vendorTypes);
    }

    public ScaleTwoDecimal get295AmountForSponsorGroupFirst(String sponsorGroup,String vendorType) {

        List<Award> awardsList = new ArrayList<Award>();
        List<Sponsor> sponsorList = new ArrayList<Sponsor>();
        List<Award> awardList = new ArrayList<Award>();
        List<SponsorHierarchy> sponsorHierarchyList = new ArrayList<SponsorHierarchy>();
        List<SubcontractingExpenditureCategoryAmountsInDateRange> expenditureCategoryAmountsList = new ArrayList<SubcontractingExpenditureCategoryAmountsInDateRange>();

        List<SubcontractingExpenditureCategoryAmountsInDateRange> sECAIDRList = (List<SubcontractingExpenditureCategoryAmountsInDateRange>) getBusinessObjectService()
                .findAll(SubcontractingExpenditureCategoryAmountsInDateRange.class);
        Map<String, String> sponsorHierarchyMap = new HashMap<String, String>();
        sponsorHierarchyMap.put("hierarchyName", "Administering Activity");
        sponsorHierarchyMap.put("level1", sponsorGroup);
        sponsorHierarchyList.addAll((List<SponsorHierarchy>) getBusinessObjectService().findMatching(SponsorHierarchy.class, sponsorHierarchyMap));

        for (SponsorHierarchy sponsorHierarchy : sponsorHierarchyList) {
            Map<String, String> awardMap = new HashMap<String, String>();
            awardMap.put("sponsorCode", sponsorHierarchy.getSponsorCode());
            awardsList.addAll((List<Award>) getBusinessObjectService().findMatching(Award.class, awardMap));

            Map<String, String> sponsorMap = new HashMap<String, String>();
            sponsorMap.put("sponsorCode", sponsorHierarchy.getSponsorCode());
            sponsorList.addAll(getBusinessObjectService().findMatching(Sponsor.class, sponsorMap));
        }

        for (Award award : awardsList) {
            boolean hasSponsor = false;
            for (Sponsor sponsor : sponsorList) {
                if (award.getSponsorCode().equals(sponsor.getSponsorCode())) {
                    hasSponsor = true;
                }
            }
            if (hasSponsor) {
                awardList.add(award);
            }
        }
        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (SubcontractingExpenditureCategoryAmountsInDateRange sECAIDR : sECAIDRList) {
            boolean hasAward = false;
            for (Award award : awardList) {
                if (award.getPrimeSponsorCode() == null)
                    if (award.getAwardNumber().equalsIgnoreCase(sECAIDR.getAwardNumber())) {
                        hasAward = true;
                    }
            }
            if (hasAward) {
                expenditureCategoryAmountsList.add(sECAIDR);
                if(vendorType.equalsIgnoreCase("LARGE BUSINESS")){
                    amount = amount.add(sECAIDR.getLargeBusinessExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("SMALL BUSINESS")){
                    amount = amount.add(sECAIDR.getSmallBusinessExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("WOMAN OWNED")){
                    amount = amount.add(sECAIDR.getWomanOwnedExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("DISADVANTAGED BUSINESS")){
                    amount = amount.add(sECAIDR.getEightADisadvantageExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("HUB")){
                    amount = amount.add(sECAIDR.getHubZoneExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("VET")){
                    amount = amount.add(sECAIDR.getVeteranOwnedExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("SDVO")){
                    amount = amount.add(sECAIDR.getServiceDisabledVeteranOwnedExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("HBCU")){
                    amount = amount.add(sECAIDR.getHistoricalBlackCollegeExpenditureAmount());
                } 
            }
        }
        return amount;
    }

    public ScaleTwoDecimal get295AmountForSponsorGroupSecond(String sponsorGroup,String vendorType) {

        List<Award> awardsList = new ArrayList<Award>();
        List<Award> awardList = new ArrayList<Award>();
        List<Sponsor> sponsorList = new ArrayList<Sponsor>();
        List<SponsorHierarchy> sponsorHierarchyList = new ArrayList<SponsorHierarchy>();
        List<SubcontractingExpenditureCategoryAmountsInDateRange> expenditureCategoryAmountsList = new ArrayList<SubcontractingExpenditureCategoryAmountsInDateRange>(); 

        List<SubcontractingExpenditureCategoryAmountsInDateRange> expenditureCategoryAmountList = (List<SubcontractingExpenditureCategoryAmountsInDateRange>) getBusinessObjectService()
                .findAll(SubcontractingExpenditureCategoryAmountsInDateRange.class);

        Map<String, String> sponsorHierarchyMap = new HashMap<String, String>();
        sponsorHierarchyMap.put("hierarchyName", "Administering Activity");
        sponsorHierarchyMap.put("level1", sponsorGroup);
        sponsorHierarchyList.addAll((List<SponsorHierarchy>) getBusinessObjectService().findMatching(SponsorHierarchy.class, sponsorHierarchyMap));

        for (SponsorHierarchy sponsorHierarchy : sponsorHierarchyList) {
            Map<String, String> awardMap = new HashMap<String, String>();
            awardMap.put("primeSponsorCode", sponsorHierarchy.getSponsorCode());
            awardsList.addAll((List<Award>) getBusinessObjectService().findMatching(Award.class, awardMap));

            Map<String, String> sponsorMap = new HashMap<String, String>();
            sponsorMap.put("sponsorCode", sponsorHierarchy.getSponsorCode());
            sponsorList.addAll(getBusinessObjectService().findMatching(Sponsor.class, sponsorMap));
        }

        for (Award award : awardsList) {
            boolean hasSponsor = false;
            for (Sponsor sponsor : sponsorList) {
                if (award.getPrimeSponsorCode() != null)
                    if (award.getPrimeSponsorCode().equals(sponsor.getSponsorCode())) {
                        hasSponsor = true;
                    }
            }
            if (hasSponsor) {
                awardList.add(award);
            }
        }

        ScaleTwoDecimal amount = ScaleTwoDecimal.ZERO;
        for (SubcontractingExpenditureCategoryAmountsInDateRange expenditureCategoryAmount : expenditureCategoryAmountList) {
            boolean hasAward = false;
            for (Award award : awardList) {
                if (award.getAwardNumber().equalsIgnoreCase(expenditureCategoryAmount.getAwardNumber())) {
                    hasAward = true;
                }
            }
            if (hasAward) {
                expenditureCategoryAmountsList.add(expenditureCategoryAmount);
                if(vendorType.equalsIgnoreCase("LARGE BUSINESS")){
                    amount = amount.add(expenditureCategoryAmount.getLargeBusinessExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("SMALL BUSINESS")){
                    amount = amount.add(expenditureCategoryAmount.getSmallBusinessExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("WOMAN OWNED")){
                    amount = amount.add(expenditureCategoryAmount.getWomanOwnedExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("DISADVANTAGED BUSINESS")){
                    amount = amount.add(expenditureCategoryAmount.getEightADisadvantageExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("HUB")){
                    amount = amount.add(expenditureCategoryAmount.getHubZoneExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("VET")){
                    amount = amount.add(expenditureCategoryAmount.getVeteranOwnedExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("SDVO")){
                    amount = amount.add(expenditureCategoryAmount.getServiceDisabledVeteranOwnedExpenditureAmount());
                }
                if(vendorType.equalsIgnoreCase("HBCU")){
                    amount = amount.add(expenditureCategoryAmount.getHistoricalBlackCollegeExpenditureAmount());
                } 
            }
        }
        return amount;
    }

    private BigDecimal getPct(BigDecimal amt, BigDecimal totAmt) {
        BigDecimal pct = new BigDecimal("0.00");
        try {
            pct = amt.divide(totAmt, 3, BigDecimal.ROUND_UP);
            pct = pct.multiply(new BigDecimal("100"));
        }
        catch (ArithmeticException e) {
            pct = new BigDecimal("0");
        }
        return pct;
    }

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}
}
