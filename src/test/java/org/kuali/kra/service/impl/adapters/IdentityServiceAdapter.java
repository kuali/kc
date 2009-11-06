package org.kuali.kra.service.impl.adapters;

import org.kuali.rice.core.jaxb.MapStringStringAdapter;
import org.kuali.rice.kim.bo.entity.dto.*;
import org.kuali.rice.kim.bo.reference.dto.*;
import org.kuali.rice.kim.service.IdentityService;

import javax.jws.WebParam;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Map;

public class IdentityServiceAdapter implements IdentityService {
    public KimPrincipalInfo getPrincipal(@WebParam(name = "principalId") String principalId) {
        return null;
    }

    public KimPrincipalInfo getPrincipalByPrincipalName(@WebParam(name = "principalName") String principalName) {
        return null;
    }

    public KimPrincipalInfo getPrincipalByPrincipalNameAndPassword(@WebParam(name = "principalName") String principalName, @WebParam(name = "password") String password) {
        return null;
    }

    public KimEntityDefaultInfo getEntityDefaultInfo(@WebParam(name = "entityId") String entityId) {
        return null;
    }

    public KimEntityDefaultInfo getEntityDefaultInfoByPrincipalId(@WebParam(name = "principalId") String principalId) {
        return null;
    }

    public KimEntityDefaultInfo getEntityDefaultInfoByPrincipalName(@WebParam(name = "principalName") String principalName) {
        return null;
    }

    public KimEntityInfo getEntityInfo(@WebParam(name = "entityId") String entityId) {
        return null;
    }

    public KimEntityInfo getEntityInfoByPrincipalId(@WebParam(name = "principalId") String principalId) {
        return null;
    }

    public KimEntityInfo getEntityInfoByPrincipalName(@WebParam(name = "principalName") String principalName) {
        return null;
    }

    public List<KimEntityDefaultInfo> lookupEntityDefaultInfo(@XmlJavaTypeAdapter(value = MapStringStringAdapter.class) Map<String, String> searchCriteria, @WebParam(name = "unbounded") boolean unbounded) {
        return null;
    }

    public List<KimEntityInfo> lookupEntityInfo(@XmlJavaTypeAdapter(value = MapStringStringAdapter.class) Map<String, String> searchCriteria, @WebParam(name = "unbounded") boolean unbounded) {
        return null;
    }

    public int getMatchingEntityCount(@XmlJavaTypeAdapter(value = MapStringStringAdapter.class) Map<String, String> searchCriteria) {
        return 0;
    }

    public KimEntityPrivacyPreferencesInfo getEntityPrivacyPreferences(@WebParam(name = "entityId") String entityId) {
        return null;
    }

    public Map<String, KimEntityNamePrincipalNameInfo> getDefaultNamesForPrincipalIds(@WebParam(name = "principalIds") List<String> principalIds) {
        return null;
    }

    public Map<String, KimEntityNameInfo> getDefaultNamesForEntityIds(@WebParam(name = "entityIds") List<String> entityIds) {
        return null;
    }

    public AddressTypeInfo getAddressType(@WebParam(name = "code") String code) {
        return null;
    }

    public AffiliationTypeInfo getAffiliationType(@WebParam(name = "code") String code) {
        return null;
    }

    public CitizenshipStatusInfo getCitizenshipStatus(@WebParam(name = "code") String code) {
        return null;
    }

    public EmailTypeInfo getEmailType(@WebParam(name = "code") String code) {
        return null;
    }

    public EmploymentStatusInfo getEmploymentStatus(@WebParam(name = "code") String code) {
        return null;
    }

    public EmploymentTypeInfo getEmploymentType(@WebParam(name = "code") String code) {
        return null;
    }

    public EntityNameTypeInfo getEntityNameType(@WebParam(name = "code") String code) {
        return null;
    }

    public EntityTypeInfo getEntityType(@WebParam(name = "code") String code) {
        return null;
    }

    public ExternalIdentifierTypeInfo getExternalIdentifierType(@WebParam(name = "code") String code) {
        return null;
    }

    public PhoneTypeInfo getPhoneType(@WebParam(name = "code") String code) {
        return null;
    }
}
