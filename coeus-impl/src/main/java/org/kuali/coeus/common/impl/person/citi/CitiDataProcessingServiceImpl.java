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
package org.kuali.coeus.common.impl.person.citi;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.attr.PersonTraining;
import org.kuali.coeus.common.framework.person.citi.*;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kim.api.identity.principal.Principal;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DictionaryValidationService;
import org.kuali.rice.krad.util.KRADUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("citiDataProcessingService")
public class CitiDataProcessingServiceImpl implements CitiDataProcessingService {

    private static final String STATUS_CODE = "statusCode";
    private static final String PERSON_ID = "personId";
    private static final String TRAINING_CODE = "trainingCode";
    private static final String DATE_REQUESTED = "dateRequested";
    private static final String DATE_SUBMITTED = "dateSubmitted";
    private static final String GROUP_ID = "groupId";
    private static final String STAGE_NUMBER = "stageNumber";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("dictionaryValidationService")
    private DictionaryValidationService dictionaryValidationService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("identityService")
    private IdentityService identityService;

    @Override
    public void processRecords() {
        getBusinessObjectService().deleteMatching(PersonTrainingCitiRecordError.class, Collections.emptyMap());

        final Collection<PersonTrainingCitiMap> mappings = getBusinessObjectService().findAll(PersonTrainingCitiMap.class);

        final List<PersonTrainingCitiRecord> sourceRecords = (List<PersonTrainingCitiRecord>) getBusinessObjectService().findMatching(PersonTrainingCitiRecord.class,
                Collections.singletonMap(STATUS_CODE,
                        Stream.of(PersonTrainingCitiRecordStatus.STAGED.getCode(),
                                PersonTrainingCitiRecordStatus.ERRORED.getCode())
                                .collect(Collectors.toList())));

        final List<PersonTraining> targetRecords = sourceRecords.stream().map(sr -> {
            resetErroredStatus(sr);
            final List<PersonTrainingCitiRecordError> errors = validateCitiRecord(sr);

            if (CollectionUtils.isNotEmpty(errors)) {
                addErrors(sr, errors.toArray(new PersonTrainingCitiRecordError[errors.size()]));
            }

            final Optional<PersonTrainingCitiMap> optionalMapping = mappings.stream()
                    .filter(m -> m.getGroupId().equals(sr.getGroupId()) &&
                            m.getStageNumber().equals(sr.getStageNumber()))
                    .findFirst();
            final PersonTrainingCitiMap mapping;
            if (!optionalMapping.isPresent()) {
                mapping = createAndSaveEmptyMapping(sr);
                mappings.add(mapping);
            } else {
                mapping = optionalMapping.get();
            }

            if (mapping.getTrainingCode() == null) {
                addErrors(sr, new PersonTrainingCitiRecordError(GROUP_ID + "=" + sr.getGroupId() + " " + STAGE_NUMBER + "=" + sr.getStageNumber() + " is not mapped to a " + TRAINING_CODE));
            }


            final Principal principal = StringUtils.isNotBlank(sr.getInstitutionalUsername()) ? getIdentityService().getPrincipalByPrincipalName(sr.getInstitutionalUsername()) : null;
            if (principal == null)  {
                addErrors(sr, new PersonTrainingCitiRecordError("principal id not found from institutional username " + sr.getInstitutionalUsername()));
            }

            if (PersonTrainingCitiRecordStatus.ERRORED.getCode().equals(sr.getStatusCode())) {
                return null;
            } else {
                sr.setStatusCode(PersonTrainingCitiRecordStatus.PROCESSED.getCode());
                return createOrUpdatePersonTraining(sr, mapping, principal);
            }

        }).filter(Objects::nonNull).collect(Collectors.toList());

        getBusinessObjectService().save(targetRecords);
        getBusinessObjectService().save(sourceRecords);
    }

    protected PersonTrainingCitiMap createAndSaveEmptyMapping(PersonTrainingCitiRecord sr) {
        final PersonTrainingCitiMap newMapping = new PersonTrainingCitiMap();
        newMapping.setGroupId(sr.getGroupId());
        newMapping.setStageNumber(sr.getStageNumber());

        //cannot save if required fields are not present
        if (StringUtils.isNotBlank(newMapping.getGroupId()) && StringUtils.isNotBlank(newMapping.getStageNumber())) {
            return getBusinessObjectService().save(newMapping);
        }

        return newMapping;
    }

    protected PersonTraining createOrUpdatePersonTraining(PersonTrainingCitiRecord sr, PersonTrainingCitiMap mapping, Principal principal) {
        final String personId = principal.getPrincipalId();
        final Integer trainingCode = mapping.getTrainingCode();
        final Timestamp dateRequested = sr.getRegistrationDate();
        final Timestamp dateSubmitted = sr.getDateCompleted();
        final PersonTraining pt = findPersonTraining(personId, trainingCode, dateRequested, dateSubmitted);

        pt.setPersonId(personId);
        pt.setTrainingCode(trainingCode);
        pt.setActive(true);
        pt.setScore(sr.getScore());
        pt.setTrainingNumber(-1);
        pt.setDateRequested(dateRequested);
        pt.setDateSubmitted(dateSubmitted);

        return pt;
    }

    protected PersonTraining findPersonTraining(String personId, Integer trainingCode, Timestamp dateRequested, Timestamp dateSubmitted) {
        final Map<String, Object> criteria = new HashMap<>();
        criteria.put(PERSON_ID, personId);
        criteria.put(TRAINING_CODE, trainingCode);
        criteria.put(DATE_REQUESTED, dateRequested);
        criteria.put(DATE_SUBMITTED, dateSubmitted);

        final Collection<PersonTraining> existingTrainings = getBusinessObjectService().findMatching(PersonTraining.class, criteria);
        if (!existingTrainings.isEmpty()) {
            return existingTrainings.iterator().next();
        }
        return new PersonTraining();
    }

    protected List<PersonTrainingCitiRecordError> validateCitiRecord(PersonTrainingCitiRecord sr) {
        globalVariableService.getMessageMap().clearErrorMessages();
        dictionaryValidationService.validateBusinessObject(sr, true);
        return globalVariableService.getMessageMap().getErrorMessages().values()
                .stream()
                .flatMap(Collection::stream)
                .map(message -> new PersonTrainingCitiRecordError(KRADUtils.getMessageText(message, false)))
                .collect(Collectors.toList());
    }

    protected void addErrors(PersonTrainingCitiRecord sr, PersonTrainingCitiRecordError... errors) {
        sr.setStatusCode(PersonTrainingCitiRecordStatus.ERRORED.getCode());
        Stream.of(errors).forEach(sr::addError);
    }

    protected void resetErroredStatus (PersonTrainingCitiRecord sr) {
        if (PersonTrainingCitiRecordStatus.ERRORED.getCode().equals(sr.getStatusCode())) {
            sr.setStatusCode(PersonTrainingCitiRecordStatus.STAGED.getCode());
        }
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public DictionaryValidationService getDictionaryValidationService() {
        return dictionaryValidationService;
    }

    public void setDictionaryValidationService(DictionaryValidationService dictionaryValidationService) {
        this.dictionaryValidationService = dictionaryValidationService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public IdentityService getIdentityService() {
        return identityService;
    }

    public void setIdentityService(IdentityService identityService) {
        this.identityService = identityService;
    }
}
