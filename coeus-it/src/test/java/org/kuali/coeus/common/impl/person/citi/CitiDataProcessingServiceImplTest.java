package org.kuali.coeus.common.impl.person.citi;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.person.attr.PersonTraining;
import org.kuali.coeus.common.framework.person.citi.*;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.*;

public class CitiDataProcessingServiceImplTest extends KcIntegrationTestBase {

    private BusinessObjectService businessObjectService;
    private CitiDataProcessingService citiDataProcessingService;

    @Before
    public void init() {
        businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        citiDataProcessingService = KcServiceLocator.getService(CitiDataProcessingService.class);

        businessObjectService.deleteMatching(PersonTrainingCitiRecordError.class, Collections.emptyMap());
        businessObjectService.deleteMatching(PersonTrainingCitiRecord.class, Collections.emptyMap());
        businessObjectService.deleteMatching(PersonTrainingCitiMap.class, Collections.emptyMap());
    }

    @Test
    public void test_basic_load_no_map() {
        PersonTrainingCitiRecord record = new PersonTrainingCitiRecord();
        record.setGroupId("1");
        record.setGroup("A group");
        record.setStageNumber("2");
        record.setStage("A stage");
        record.setCurriculum("A curr");
        record.setInstitutionalUsername("quickstart");
        record.setPassingScore("70");
        record.setStatusCode(PersonTrainingCitiRecordStatus.STAGED.getCode());

        record = businessObjectService.save(record);

        Assert.assertTrue(Objects.toString(record.getErrors()), CollectionUtils.isEmpty(record.getErrors()));
        Assert.assertEquals(PersonTrainingCitiRecordStatus.STAGED.getCode(), record.getStatusCode());

        citiDataProcessingService.processRecords();
        record = businessObjectService.findBySinglePrimaryKey(PersonTrainingCitiRecord.class, record.getId());

        Assert.assertTrue(Objects.toString(record.getErrors()), CollectionUtils.isNotEmpty(record.getErrors()));
        Assert.assertEquals(PersonTrainingCitiRecordStatus.ERRORED.getCode(), record.getStatusCode());

        final Map<String, String> criteria = new HashMap<>();
        criteria.put("groupId", "1");
        criteria.put("stageNumber", "2");

        //make sure empty mapping gets created
        final Collection<PersonTrainingCitiMap> mappings = businessObjectService.findMatching(PersonTrainingCitiMap.class, criteria);
        Assert.assertTrue(Objects.toString(mappings), CollectionUtils.isNotEmpty(mappings));
        Assert.assertNull(mappings.iterator().next().getTrainingCode());
    }

    @Test
    public void test_basic_load_with_map() {
        PersonTrainingCitiRecord record = new PersonTrainingCitiRecord();
        record.setGroupId("1");
        record.setGroup("A group");
        record.setStageNumber("2");
        record.setStage("A stage");
        record.setCurriculum("A curr");
        record.setInstitutionalUsername("quickstart");
        record.setPassingScore("70");
        record.setStatusCode(PersonTrainingCitiRecordStatus.STAGED.getCode());

        record = businessObjectService.save(record);

        PersonTrainingCitiMap map = new PersonTrainingCitiMap();
        map.setGroupId("1");
        map.setStageNumber("2");
        map.setTrainingCode(1);

        map = businessObjectService.save(map);

        citiDataProcessingService.processRecords();
        record = businessObjectService.findBySinglePrimaryKey(PersonTrainingCitiRecord.class, record.getId());

        Assert.assertTrue(record.getErrors() != null ? Objects.toString(new ArrayList<>(record.getErrors())) : "", CollectionUtils.isEmpty(record.getErrors()));
        Assert.assertEquals(PersonTrainingCitiRecordStatus.PROCESSED.getCode(), record.getStatusCode());

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("trainingCode", 1);
        criteria.put("personId", "10000000001");
        final Collection<PersonTraining> trainings = businessObjectService.findMatching(PersonTraining.class, criteria);

        Assert.assertTrue(Objects.toString(trainings), CollectionUtils.isNotEmpty(trainings));
    }

    @Test
    public void test_basic_load_error() {
        PersonTrainingCitiRecord record = new PersonTrainingCitiRecord();
        record.setGroupId("1");
        record.setGroup("A group");
        record.setStageNumber("2");
        record.setStage("A stage");
        record.setCurriculum("A curr");
        record.setInstitutionalUsername("junk_user");
        record.setStatusCode(PersonTrainingCitiRecordStatus.STAGED.getCode());

        record = businessObjectService.save(record);

        PersonTrainingCitiMap map = new PersonTrainingCitiMap();
        map.setGroupId("1");
        map.setStageNumber("2");
        map.setTrainingCode(1);

        map = businessObjectService.save(map);

        citiDataProcessingService.processRecords();
        record = businessObjectService.findBySinglePrimaryKey(PersonTrainingCitiRecord.class, record.getId());

        Assert.assertTrue(record.getErrors() != null ? Objects.toString(new ArrayList<>(record.getErrors())) : "", CollectionUtils.isNotEmpty(record.getErrors()));
        Assert.assertEquals(PersonTrainingCitiRecordStatus.ERRORED.getCode(), record.getStatusCode());

        final Map<String, Object> criteria = new HashMap<>();
        criteria.put("trainingCode", 1);
        criteria.put("personId", "10000000001");
        final Collection<PersonTraining> trainings = businessObjectService.findMatching(PersonTraining.class, criteria);

        Assert.assertTrue(Objects.toString(trainings), CollectionUtils.isEmpty(trainings));
    }
}
