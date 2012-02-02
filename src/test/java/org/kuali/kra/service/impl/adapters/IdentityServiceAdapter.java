package org.kuali.kra.service.impl.adapters;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.core.api.exception.RiceIllegalStateException;
import org.kuali.rice.kim.api.identity.CodedAttribute;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.address.EntityAddress;
import org.kuali.rice.kim.api.identity.affiliation.EntityAffiliation;
import org.kuali.rice.kim.api.identity.affiliation.EntityAffiliationType;
import org.kuali.rice.kim.api.identity.citizenship.EntityCitizenship;
import org.kuali.rice.kim.api.identity.email.EntityEmail;
import org.kuali.rice.kim.api.identity.employment.EntityEmployment;
import org.kuali.rice.kim.api.identity.entity.Entity;
import org.kuali.rice.kim.api.identity.entity.EntityDefault;
import org.kuali.rice.kim.api.identity.entity.EntityDefaultQueryResults;
import org.kuali.rice.kim.api.identity.entity.EntityQueryResults;
import org.kuali.rice.kim.api.identity.external.EntityExternalIdentifier;
import org.kuali.rice.kim.api.identity.external.EntityExternalIdentifierType;
import org.kuali.rice.kim.api.identity.name.EntityName;
import org.kuali.rice.kim.api.identity.personal.EntityBioDemographics;
import org.kuali.rice.kim.api.identity.personal.EntityEthnicity;
import org.kuali.rice.kim.api.identity.phone.EntityPhone;
import org.kuali.rice.kim.api.identity.principal.EntityNamePrincipalName;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.kim.api.identity.privacy.EntityPrivacyPreferences;
import org.kuali.rice.kim.api.identity.residency.EntityResidency;
import org.kuali.rice.kim.api.identity.type.EntityTypeContactInfo;
import org.kuali.rice.kim.api.identity.visa.EntityVisa;

public class IdentityServiceAdapter implements IdentityService {

    @Override
    public EntityAddress addAddressToEntity(EntityAddress address) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityAffiliation addAffiliationToEntity(EntityAffiliation affiliation) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityBioDemographics addBioDemographicsToEntity(EntityBioDemographics bioDemographics)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityCitizenship addCitizenshipToEntity(EntityCitizenship citizenship) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEmail addEmailToEntity(EntityEmail email) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEmployment addEmploymentToEntity(EntityEmployment employment) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityTypeContactInfo addEntityTypeContactInfoToEntity(EntityTypeContactInfo entityTypeContactInfo)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEthnicity addEthnicityToEntity(EntityEthnicity ethnicity) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityExternalIdentifier addExternalIdentifierToEntity(EntityExternalIdentifier externalId)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityName addNameToEntity(EntityName name) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityPhone addPhoneToEntity(EntityPhone phone) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public Principal addPrincipalToEntity(Principal principal) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityPrivacyPreferences addPrivacyPreferencesToEntity(EntityPrivacyPreferences privacyPreferences)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityResidency addResidencyToEntity(EntityResidency residency) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityVisa addVisaToEntity(EntityVisa visa) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public Entity createEntity(Entity entity) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityQueryResults findEntities(QueryByCriteria query) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityDefaultQueryResults findEntityDefaults(QueryByCriteria query) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getAddressType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityAffiliationType getAffiliationType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getCitizenshipStatus(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getEmailType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getEmploymentStatus(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getEmploymentType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public Entity getEntity(String id) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public Entity getEntityByPrincipalId(String principalId) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public Entity getEntityByPrincipalName(String principalName) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityDefault getEntityDefault(String id) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityDefault getEntityDefaultByPrincipalId(String principalId) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityDefault getEntityDefaultByPrincipalName(String principalName) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityPrivacyPreferences getEntityPrivacyPreferences(String id) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getEntityType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityExternalIdentifierType getExternalIdentifierType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getNameType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public CodedAttribute getPhoneType(String code) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public Principal getPrincipal(String principalId) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public Principal getPrincipalByPrincipalName(String principalName) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public Principal getPrincipalByPrincipalNameAndPassword(String principalName, String password)
            throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityAddress inactivateAddress(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityAffiliation inactivateAffiliation(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityCitizenship inactivateCitizenship(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEmail inactivateEmail(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEmployment inactivateEmployment(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public Entity inactivateEntity(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityTypeContactInfo inactivateEntityTypeContactInfo(String entityId, String entityTypeCode)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityName inactivateName(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityPhone inactivatePhone(String id) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public Principal inactivatePrincipal(String principalId) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public Principal inactivatePrincipalByName(String principalName) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityAddress updateAddress(EntityAddress address) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityAffiliation updateAffiliation(EntityAffiliation affiliation) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityBioDemographics updateBioDemographics(EntityBioDemographics bioDemographics) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityCitizenship updateCitizenship(EntityCitizenship citizenship) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEmail updateEmail(EntityEmail email) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEmployment updateEmployment(EntityEmployment employment) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public Entity updateEntity(Entity entity) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityTypeContactInfo updateEntityTypeContactInfo(EntityTypeContactInfo entityTypeContactInfo)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityEthnicity updateEthnicity(EntityEthnicity ethnicity) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityExternalIdentifier updateExternalIdentifier(EntityExternalIdentifier externalId)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityName updateName(EntityName name) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityPhone updatePhone(EntityPhone phone) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public Principal updatePrincipal(Principal principal) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityPrivacyPreferences updatePrivacyPreferences(EntityPrivacyPreferences privacyPreferences)
            throws RiceIllegalArgumentException, RiceIllegalStateException {
        
        return null;
    }

    @Override
    public EntityResidency updateResidency(EntityResidency residency) throws RiceIllegalArgumentException,
            RiceIllegalStateException {
        return null;
    }

    @Override
    public EntityVisa updateVisa(EntityVisa visa) throws RiceIllegalArgumentException, RiceIllegalStateException {
        return null;
    }

    @Override
    public Entity getEntityByEmployeeId(String employeeId) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityDefault getEntityDefaultByEmployeeId(String employeeId) throws RiceIllegalArgumentException {
        return null;
    }

    @Override
    public EntityNamePrincipalName getDefaultNamesForPrincipalId(String principalId) {
        return null;
    }
    
    
}
