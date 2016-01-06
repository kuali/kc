package org.kuali.coeus.award.finance.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.award.finance.AccountInformationDto;
import org.kuali.coeus.award.finance.AccountService;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.kra.award.commitments.AwardFandaRate;
import org.kuali.kra.award.commitments.AwardFandaRateService;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.ValidRates;
import org.kuali.kra.external.award.FinancialIndirectCostRecoveryTypeCode;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.codiform.moo.curry.Translate;

import java.util.*;
import java.util.stream.Collectors;

@Component("accountService")
public class AccountServiceImpl implements AccountService {

    public static final String RATE_CLASS_CODE = "rateClassCode";
    public static final String RATE_TYPE_CODE = "rateTypeCode";
    public static final String UNIT_ADMINISTRATOR_TYPE = "unitAdministratorType";
    public static final String ADMINISTRATIVE_CONTACT = "Administrative Contact";
    public static final String AWARD_BASIS_OF_PAYMENT = "awardBasisOfPayment";
    public static final String AWARD_METHOD_OF_PAYMENT = "awardMethodOfPayment";
    final int ACCOUNT_NAME_LENGTH = 40;

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("awardFandaRateService")
    private AwardFandaRateService awardFandaRateService;

    @Override
    public AccountInformationDto createAccountInformation(Award award) {

        AccountInformationDto accountInformation = Translate.to(AccountInformationDto.class).from(award);
        setName(award, accountInformation);
        setDefaultAddress(award, accountInformation);
        setAdminAddress(award, accountInformation);
        setIncomeGuidelineText(award, accountInformation);
        AwardFandaRate currentFandaRate = award.getCurrentFandaRate();

        if (currentFandaRate != null) {
	        String rateClassCode = currentFandaRate.getFandaRateType().getRateClassCode();
	        String rateTypeCode = currentFandaRate.getFandaRateType().getRateTypeCode();
	        String icrTypeCode = getIndirectCostTypeCode(rateClassCode, rateTypeCode);
	
	        accountInformation.setOffCampusIndicator(!currentFandaRate.getOnOffCampusFlag());
	        accountInformation.setIndirectCostTypeCode(icrTypeCode + StringUtils.EMPTY);
	        accountInformation.setIndirectCostRate(determineIndirectCostRateCode(award));
        }
        
        accountInformation.setHigherEdFunctionCode(award.getActivityType().getHigherEducationFunctionCode());
        return accountInformation;

    }

    /*
    If there are multiple icr codes mapped in the system, just pick one.
     */
    protected String determineIndirectCostRateCode(Award award) {
            List<ValidRates> validRates = getMatchingValidRates(award.getCurrentFandaRate());
            return (!validRates.isEmpty()) ? validRates.get(0).getIcrRateCode() : StringUtils.EMPTY;
    }

    public List<ValidRates> getMatchingValidRates(AwardFandaRate rate) {
        AwardFandaRateService fandaRateService = getAwardFandaRateService();
        List<ValidRates> validRates = fandaRateService.getValidRates(rate);
        return validRates.stream()
                .filter(currentRate -> StringUtils.isNotBlank(currentRate.getIcrRateCode()))
                .collect(Collectors.toList());
    }

    protected String getIndirectCostTypeCode(String rateClassCode, String rateTypeCode) {
        Map<String, Object> criteria = new HashMap<>();
        criteria.put(RATE_CLASS_CODE, rateClassCode);
        criteria.put(RATE_TYPE_CODE, rateTypeCode);
        FinancialIndirectCostRecoveryTypeCode icrCostTypeCode= businessObjectService.findByPrimaryKey(FinancialIndirectCostRecoveryTypeCode.class, criteria);
        return Objects.nonNull(icrCostTypeCode)? icrCostTypeCode.getIcrTypeCode() : StringUtils.EMPTY;
    }

    protected void setName(Award award, AccountInformationDto accountInformation) {

        String accountName = StringUtils.EMPTY;
        accountName = getAcronym(award, accountName);
        accountName = addSponsorAwardNumber(award, accountName);
        accountName = addPrincipalInvestigator(award, accountName);
        accountName = shortenAccountName(accountName);
        accountInformation.setAccountName(accountName);
    }

    private String shortenAccountName(String accountName) {
        if (Objects.nonNull(accountName) && accountName.length() > ACCOUNT_NAME_LENGTH) {
            accountName = accountName.substring(0, ACCOUNT_NAME_LENGTH - 1);
        }
        return accountName;
    }

    private String addPrincipalInvestigator(Award award, String accountName) {
        if (Objects.nonNull(award.getPrincipalInvestigator())
                && Objects.nonNull(award.getPrincipalInvestigator().getPerson())) {
            accountName +=  award.getPrincipalInvestigator().getPerson().getLastName()
                    + award.getPrincipalInvestigator().getPerson().getFirstName();
        }
        return accountName;
    }

    private String addSponsorAwardNumber(Award award, String accountName) {
        if (Objects.nonNull(award.getSponsorAwardNumber())) { accountName += award.getSponsorAwardNumber() + "-"; }
        return accountName;
    }

    private String getAcronym(Award award, String accountName) {
        if (Objects.nonNull(award.getSponsor().getAcronym())) {
            accountName += award.getSponsor().getAcronym() + "-";
        }
        return accountName;
    }

    protected void setDefaultAddress(Award award, AccountInformationDto accountInformation) {
        //default address is the PI address
        KcPerson principalInvestigator = award.getPrincipalInvestigator().getPerson();
        if (Objects.nonNull(principalInvestigator)) {

            String streetAddress = StringUtils.EMPTY;
            if (principalInvestigator.getAddressLine1() != null) {
                streetAddress += principalInvestigator.getAddressLine1();
            }
            if (principalInvestigator.getAddressLine2() != null) {
                streetAddress += principalInvestigator.getAddressLine2();
            }

            if (principalInvestigator.getAddressLine3() != null) {
                streetAddress += principalInvestigator.getAddressLine3();
            }
            accountInformation.setDefaultAddressStreetAddress(streetAddress);
            accountInformation.setDefaultAddressCityName(principalInvestigator.getCity());
            // getState returns state code
            accountInformation.setDefaultAddressStateCode(principalInvestigator.getState());
            accountInformation.setDefaultAddressZipCode(principalInvestigator.getPostalCode());
        }
    }

    protected void setAdminAddress(Award award, AccountInformationDto accountInformation) {
        List<AwardUnitContact> unitContacts = award.getAwardUnitContacts();
        unitContacts.forEach(contact -> {
            contact.refreshReferenceObject(UNIT_ADMINISTRATOR_TYPE);
            // Send the address of the administrative contact
            UnitAdministratorType adminType = contact.getUnitAdministratorType();
            if (Objects.nonNull(adminType)
                    && ADMINISTRATIVE_CONTACT.equals(adminType.getDescription())) {
                KcPerson adminPerson = contact.getPerson();
                if (Objects.nonNull(adminPerson)) {
                    String adminStreetAddress = "";
                    if (adminPerson.getAddressLine1() != null) {
                        adminStreetAddress += adminPerson.getAddressLine1();
                    }
                    if (adminPerson.getAddressLine2() != null) {
                        adminStreetAddress += adminPerson.getAddressLine2();
                    }

                    if (adminPerson.getAddressLine3() != null) {
                        adminStreetAddress += adminPerson.getAddressLine3();
                    }

                    accountInformation.setAdminContactAddressStreetAddress(adminStreetAddress);
                    accountInformation.setAdminContactAddressStreetAddress(adminPerson.getAddressLine1());
                    accountInformation.setAdminContactAddressCityName(adminPerson.getCity());
                    accountInformation.setAdminContactAddressStateCode(adminPerson.getState());
                    accountInformation.setAdminContactAddressZipCode(adminPerson.getPostalCode());
                }
            }
        });
    }

    protected void setIncomeGuidelineText(Award award, AccountInformationDto accountInformation) {
        award.refreshReferenceObject(AWARD_BASIS_OF_PAYMENT);
        String paymentBasis = award.getAwardBasisOfPayment().getDescription();
        award.refreshReferenceObject(AWARD_METHOD_OF_PAYMENT);
        String paymentMethod = award.getAwardMethodOfPayment().getDescription();

        String incomeGuidelineText = StringUtils.EMPTY;
        if (paymentBasis != null) {
            incomeGuidelineText += paymentBasis;
        }
        if (paymentMethod != null) {
            incomeGuidelineText += " " + paymentMethod;
        }
        accountInformation.setIncomeGuidelineText(incomeGuidelineText);
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public AwardFandaRateService getAwardFandaRateService() {
        return awardFandaRateService;
    }

    public void setAwardFandaRateService(AwardFandaRateService awardFandaRateService) {
        this.awardFandaRateService = awardFandaRateService;
    }
}
