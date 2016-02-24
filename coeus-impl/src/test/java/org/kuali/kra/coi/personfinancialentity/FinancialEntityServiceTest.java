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
package org.kuali.kra.coi.personfinancialentity;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.common.framework.version.VersioningService;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinancialEntityServiceTest {
    private static final String  PERSON_ID = "10000000001";
    private static final String  UNIT_NUMBER = "000001";
    private static final String  UNIT_NAME = "University";
    private static final String  ENTITY_NUMBER = "1";
    private static final String  ENTITY_NAME_1 = "Entity 1";
    private static final String  GROUP_NAME_1 = "Group 1";
    private static final String  GROUP_NAME_2 = "Group 2";
    private static final String  COLUMN_NAME_11 = "COLUMN_NAME_11";
    private static final String  COLUMN_LABEL_11 = "Column 11";
    private static final String  COLUMN_NAME_12 = "COLUMN_NAME_12";
    private static final String  COLUMN_LABEL_12 = "Column 12";
    private static final String  COLUMN_NAME_21 = "COLUMN_NAME_21";
    private static final String  COLUMN_LABEL_21 = "Column 21";
    private static final String  GUITYPE_DROPDOWN = "DROPDOWN";
    private static final String  GUITYPE_CHECKBOX = "CHECKBOX";
    private static final String  LOOKUP_ARGUIMENT= "Argument 1";
    private static final Integer  SORT_ID_1= 1;
    private static final Integer  SORT_ID_2= 2;
    private static final String  COMMENT_1= "Comment 1";

    Mockery context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }};

    @Before
    public void setUp() throws Exception {
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
     @Test
    public void testGetActiveFinancialEntities() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
        personFinIntDisclosure.setPersonId(PERSON_ID);
        personFinIntDisclosure.setStatusCode(1);
        personFinIntDisclosure.setCurrentFlag(true);
        personFinIntDisclosure.setEntityNumber(ENTITY_NUMBER);
        personFinIntDisclosure.setEntityName(ENTITY_NAME_1);
        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", PERSON_ID);
                fieldValues.put("statusCode", "1");     
                fieldValues.put("currentFlag", "Y");     
                one(businessObjectService).findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues, "entityName", true);
                will(returnValue(activeEntities));
                Map fieldValues1 = new HashMap();
                fieldValues1.put("entityNumber", ENTITY_NUMBER);
                one(businessObjectService).findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues1, "sequenceNumber", false);
                will(returnValue(activeEntities));


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
        List<PersonFinIntDisclosure> entities = financialEntityService.getFinancialEntities(PERSON_ID, true);
        Assert.assertEquals(entities.size(), 1);
        Assert.assertEquals(entities.get(0).getPersonId(), PERSON_ID);
        Assert.assertEquals(entities.get(0).getStatusCode().intValue(), 1);
        Assert.assertEquals(entities.get(0).getVersions().size(), 1);
        Assert.assertEquals(entities.get(0).getVersions().get(0).getEntityNumber(), ENTITY_NUMBER);
       Assert.assertEquals(entities.get(0).isCurrentFlag(), true);

    }

     @Test
    public void testGetInactiveFinancialEntities() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
        personFinIntDisclosure.setPersonId(PERSON_ID);
        personFinIntDisclosure.setEntityNumber(ENTITY_NUMBER);
        personFinIntDisclosure.setStatusCode(2);
        personFinIntDisclosure.setEntityName(ENTITY_NAME_1);
        personFinIntDisclosure.setCurrentFlag(true);
        final List<PersonFinIntDisclosure> activeEntities = new ArrayList<PersonFinIntDisclosure>();
        activeEntities.add(personFinIntDisclosure);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", PERSON_ID);
                fieldValues.put("statusCode", "2");     
                fieldValues.put("currentFlag", "Y");     
                one(businessObjectService).findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues, "entityName", true);
                will(returnValue(activeEntities));
                Map fieldValues1 = new HashMap();
                fieldValues1.put("entityNumber", ENTITY_NUMBER);
                one(businessObjectService).findMatchingOrderBy(PersonFinIntDisclosure.class, fieldValues1, "sequenceNumber", false);
                will(returnValue(activeEntities));


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
        List<PersonFinIntDisclosure> entities = financialEntityService.getFinancialEntities(PERSON_ID, false);
        Assert.assertEquals(entities.size(), 1);
        Assert.assertEquals(entities.get(0).getPersonId(), PERSON_ID);
        Assert.assertEquals(entities.get(0).getStatusCode().intValue(), 2);
        Assert.assertEquals(entities.get(0).getVersions().size(), 1);
        Assert.assertEquals(entities.get(0).getVersions().get(0).getEntityNumber(), ENTITY_NUMBER);
        Assert.assertEquals(entities.get(0).isCurrentFlag(), true);

    }
     
     @Test
    public void testGetFinancialEntityReporter() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        FinancialEntityReporter reporter = new FinancialEntityReporter();
        reporter.setPersonId(PERSON_ID);
        reporter.setReporterRoleId("FER");
        reporter.setFinancialEntityReporterUnits(new ArrayList<FinancialEntityReporterUnit>());
        FinancialEntityReporterUnit financialEntityReporterUnit = new FinancialEntityReporterUnit() {
            public String getUnitName() {
                return UNIT_NAME;
            }
        };
        financialEntityReporterUnit.setUnitNumber(UNIT_NUMBER);
        financialEntityReporterUnit.setUnitName(UNIT_NAME);
        financialEntityReporterUnit.setLeadUnitFlag(true);
        reporter.getFinancialEntityReporterUnits().add(financialEntityReporterUnit);

        
        final List<FinancialEntityReporter> reporters = new ArrayList<FinancialEntityReporter>();
        reporters.add(reporter);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("personId", PERSON_ID);
                fieldValues.put("reporterRoleId", "FER");     
                one(businessObjectService).findMatching(FinancialEntityReporter.class, fieldValues);
                will(returnValue(reporters));


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
//        financialEntityService.setKcPersonService(getMockKcPersonService());
        
        FinancialEntityReporter financialEntityReporter = financialEntityService.getFinancialEntityReporter(PERSON_ID);
        Assert.assertEquals(financialEntityReporter.getFinancialEntityReporterUnits().size(), 1);
        Assert.assertEquals(financialEntityReporter.getPersonId(), PERSON_ID);
        Assert.assertEquals(financialEntityReporter.getReporterRoleId(), "FER");
        Assert.assertEquals(financialEntityReporter.getFinancialEntityReporterUnits().get(0).getUnitNumber(), UNIT_NUMBER);
        Assert.assertEquals(financialEntityReporter.getFinancialEntityReporterUnits().get(0).getUnitName(), UNIT_NAME);

    }
     
    @Test
    public void testGetFinancialEntityRelationshipTypes() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        
        final List<FinIntEntityRelType> relationTypes = new ArrayList<FinIntEntityRelType>();
        relationTypes.add(getFinIntEntityRelType("1", 1, true));
        relationTypes.add(getFinIntEntityRelType("2", 2, true));
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("active", "Y");
                one(businessObjectService).findMatchingOrderBy(FinIntEntityRelType.class, fieldValues, "sortId", true);
                will(returnValue(relationTypes));


            }
        });
        financialEntityService.setBusinessObjectService(businessObjectService);
        
        List<FinIntEntityRelType> returnRelationTypes = financialEntityService.getFinancialEntityRelationshipTypes();
        Assert.assertEquals(returnRelationTypes.size(), 2);
        Assert.assertEquals(returnRelationTypes.get(0).getRelationshipTypeCode(), "1");
        Assert.assertEquals(returnRelationTypes.get(0).getSortId(), new Integer(1));
        Assert.assertEquals(returnRelationTypes.get(1).getRelationshipTypeCode(), "2");
        Assert.assertEquals(returnRelationTypes.get(1).getSortId(), new Integer(2));

    }
     
    @Test
    public void testGetFinancialEntityDataMatrix() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        
        final List<FinIntEntityRelType> relationTypes = new ArrayList<FinIntEntityRelType>();
        relationTypes.add(getFinIntEntityRelType("1", 1, true));
        relationTypes.add(getFinIntEntityRelType("2", 2, true));
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("active", "Y");
                one(businessObjectService).findMatchingOrderBy(FinIntEntityRelType.class, fieldValues, "sortId", true);
                will(returnValue(relationTypes));


            }
        });
        
        final List<FinEntitiesDataGroup> dataGroups = getDataGroups();
        context.checking(new Expectations() {
            {
                one(businessObjectService).findAll(FinEntitiesDataGroup.class);
                will(returnValue(dataGroups));


            }
        });

        financialEntityService.setBusinessObjectService(businessObjectService);
        
        List<FinEntityDataMatrixBean> dataMatrixs = financialEntityService.getFinancialEntityDataMatrix();
        Assert.assertEquals(dataMatrixs.size(), 3);
        Assert.assertEquals(dataMatrixs.get(0).getColumnName(), COLUMN_NAME_11);
        Assert.assertEquals(dataMatrixs.get(1).getColumnName(), COLUMN_NAME_12);
        Assert.assertEquals(dataMatrixs.get(2).getColumnName(), COLUMN_NAME_21);
        Assert.assertEquals(dataMatrixs.get(0).getColumnLabel(), COLUMN_LABEL_11);
        Assert.assertEquals(dataMatrixs.get(1).getColumnLabel(), COLUMN_LABEL_12);
        Assert.assertEquals(dataMatrixs.get(2).getColumnLabel(), COLUMN_LABEL_21);
        Assert.assertEquals(dataMatrixs.get(0).getGuiType(), GUITYPE_DROPDOWN);
        Assert.assertEquals(dataMatrixs.get(1).getGuiType(), GUITYPE_CHECKBOX);
        Assert.assertEquals(dataMatrixs.get(2).getGuiType(), GUITYPE_CHECKBOX);
        Assert.assertEquals(dataMatrixs.get(0).getLookupArgument(), LOOKUP_ARGUIMENT);
        Assert.assertEquals(dataMatrixs.get(1).getLookupArgument(), "");
        Assert.assertEquals(dataMatrixs.get(2).getLookupArgument(), "");

 
    }
     
    @Test
    public void testGetFinDisclosureDetails() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        
        final List<FinIntEntityRelType> relationTypes = new ArrayList<FinIntEntityRelType>();
        relationTypes.add(getFinIntEntityRelType("1", 1, true));
        relationTypes.add(getFinIntEntityRelType("2", 2, true));
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("active", "Y");
                one(businessObjectService).findMatchingOrderBy(FinIntEntityRelType.class, fieldValues, "sortId", true);
                will(returnValue(relationTypes));


            }
        });
        
        final List<FinEntitiesDataGroup> dataGroups = getDataGroups();
        context.checking(new Expectations() {
            {
                one(businessObjectService).findAll(FinEntitiesDataGroup.class);
                will(returnValue(dataGroups));


            }
        });

        financialEntityService.setBusinessObjectService(businessObjectService);
        List<FinEntityDataMatrixBean> dataMatrixs = financialEntityService.getFinancialEntityDataMatrix();
        dataMatrixs.get(0).getRelationshipTypeBeans().get(0).setStringValue("1");
        dataMatrixs.get(1).getRelationshipTypeBeans().get(1).setBooleanValue(true);
        dataMatrixs.get(2).setComments(COMMENT_1);

        List<PersonFinIntDisclDet>  disclosureDetails = financialEntityService.getFinDisclosureDetails(dataMatrixs,"1",1);
        Assert.assertEquals(disclosureDetails.size(), 4);
        Assert.assertEquals(disclosureDetails.get(0).getColumnName(), COLUMN_NAME_11);
        Assert.assertEquals(disclosureDetails.get(1).getColumnName(), COLUMN_NAME_12);
        Assert.assertEquals(disclosureDetails.get(2).getColumnName(), COLUMN_NAME_21);
        Assert.assertEquals(disclosureDetails.get(3).getColumnName(), COLUMN_NAME_21);
        Assert.assertEquals(disclosureDetails.get(0).getColumnValue(), "1");
        Assert.assertEquals(disclosureDetails.get(1).getColumnValue(), "1");
        Assert.assertEquals(disclosureDetails.get(2).getComments(), COMMENT_1);
        Assert.assertEquals(disclosureDetails.get(3).getComments(), COMMENT_1);

 
    }
     
    @Test
    public void testGetFinancialEntityDataMatrixForEdit() throws Exception {
        FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        
        final List<FinIntEntityRelType> relationTypes = new ArrayList<FinIntEntityRelType>();
        relationTypes.add(getFinIntEntityRelType("1", 1, true));
        relationTypes.add(getFinIntEntityRelType("2", 2, true));
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("active", "Y");
                one(businessObjectService).findMatchingOrderBy(FinIntEntityRelType.class, fieldValues, "sortId", true);
                will(returnValue(relationTypes));
                one(businessObjectService).findMatchingOrderBy(FinIntEntityRelType.class, fieldValues, "sortId", true);
                will(returnValue(relationTypes));


            }
        });
        
        final List<FinEntitiesDataGroup> dataGroups = getDataGroups();
        context.checking(new Expectations() {
            {
                one(businessObjectService).findAll(FinEntitiesDataGroup.class);
                will(returnValue(dataGroups));
                one(businessObjectService).findAll(FinEntitiesDataGroup.class);
                will(returnValue(dataGroups));


            }
        });

        financialEntityService.setBusinessObjectService(businessObjectService);
        List<FinEntityDataMatrixBean> dataMatrixs = financialEntityService.getFinancialEntityDataMatrix();
        dataMatrixs.get(0).getRelationshipTypeBeans().get(0).setStringValue("1");
        dataMatrixs.get(1).getRelationshipTypeBeans().get(1).setBooleanValue(true);
        dataMatrixs.get(2).setComments(COMMENT_1);

        List<PersonFinIntDisclDet>  disclosureDetails = financialEntityService.getFinDisclosureDetails(dataMatrixs,"1",1);
        List<FinEntityDataMatrixBean> matrixBeans = financialEntityService.getFinancialEntityDataMatrixForEdit(disclosureDetails);
        Assert.assertEquals(matrixBeans.size(), 3);
 
    }

    @Test
   public void testVersionPersonFinintDisclosures() throws Exception {
       FinancialEntityServiceImpl financialEntityService = new FinancialEntityServiceImpl();
       final VersioningService versioningService = context.mock(VersioningService.class);
       final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
       final PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
       personFinIntDisclosure.setPersonId(PERSON_ID);
       personFinIntDisclosure.setEntityNumber(ENTITY_NUMBER);
       personFinIntDisclosure.setSequenceNumber(1);
       personFinIntDisclosure.setStatusCode(2);
       personFinIntDisclosure.setCurrentFlag(true);
       personFinIntDisclosure.setPersonFinIntDisclosureId(1L);
       final List<FinancialEntityContactInfo> contactInfos = new ArrayList<FinancialEntityContactInfo>();
       FinancialEntityContactInfo contactInfo = new FinancialEntityContactInfo();
       contactInfo.setPersonFinIntDisclosureId(1L);
       contactInfo.setFinancialEntityContactInfoId(2L);
       contactInfos.add(contactInfo);
       personFinIntDisclosure.setFinEntityContactInfos(contactInfos);
       context.checking(new Expectations() {
           {
               final PersonFinIntDisclosure newPersonFinIntDisclosure = getPersonFinancialEntityDiscl();     
               one(versioningService).createNewVersion(personFinIntDisclosure);
               will(returnValue(newPersonFinIntDisclosure));


           }
       });
       context.checking(new Expectations() {
           {
               Map<String, Object> fieldValues = new HashMap<String, Object>();
               fieldValues.put("personFinIntDisclosureId", 1L);
               one(businessObjectService).findByPrimaryKey(PersonFinIntDisclosure.class, fieldValues);
               will(returnValue(personFinIntDisclosure));
               one(businessObjectService).save(personFinIntDisclosure);
               


           }
       });

       financialEntityService.setBusinessObjectService(businessObjectService);
       financialEntityService.setVersioningService(versioningService);
       PersonFinIntDisclosure newDisclosure = financialEntityService.versionPersonFinintDisclosure(personFinIntDisclosure, 
               new ArrayList<FinEntityDataMatrixBean>(), new ArrayList<FinancialEntityAttachment>());
       Assert.assertEquals(newDisclosure.getSequenceNumber(), new Integer(2));
       Assert.assertNull(newDisclosure.getFinEntityContactInfos().get(0).getPersonFinIntDisclosureId());
       Assert.assertNull(newDisclosure.getFinEntityContactInfos().get(0).getFinancialEntityContactInfoId());
       Assert.assertTrue(newDisclosure.isCurrentFlag());
       Assert.assertFalse(personFinIntDisclosure.isCurrentFlag());
   }
    
    private PersonFinIntDisclosure getPersonFinancialEntityDiscl() {
        PersonFinIntDisclosure personFinIntDisclosure = new PersonFinIntDisclosure();
        personFinIntDisclosure.setPersonId(PERSON_ID);
        personFinIntDisclosure.setEntityNumber(ENTITY_NUMBER);
        personFinIntDisclosure.setSequenceNumber(2);
        personFinIntDisclosure.setStatusCode(2);
        personFinIntDisclosure.setCurrentFlag(true);
        final List<FinancialEntityContactInfo> contactInfos = new ArrayList<FinancialEntityContactInfo>();
        FinancialEntityContactInfo contactInfo = new FinancialEntityContactInfo();
        contactInfo.setPersonFinIntDisclosureId(1L);
        contactInfo.setFinancialEntityContactInfoId(2L);
        contactInfos.add(contactInfo);
        personFinIntDisclosure.setFinEntityContactInfos(contactInfos);
        return personFinIntDisclosure;
    }
    
    private List<FinEntitiesDataGroup> getDataGroups() {
        List<FinEntitiesDataGroup> dataGroups = new ArrayList<FinEntitiesDataGroup>();    
        FinEntitiesDataGroup dataGroup1 = new FinEntitiesDataGroup();
        dataGroup1.setDataGroupId(1);
        dataGroup1.setDataGroupName(GROUP_NAME_1);
        dataGroup1.setDataGroupSortId(SORT_ID_1);
        dataGroup1.setFinEntitiesDataMatrixs(new ArrayList<FinEntitiesDataMatrix>());
        dataGroup1.getFinEntitiesDataMatrixs().add(getFinEntitiesDataMatrix(COLUMN_NAME_11, GUITYPE_DROPDOWN, LOOKUP_ARGUIMENT, COLUMN_LABEL_11));
        dataGroup1.getFinEntitiesDataMatrixs().add(getFinEntitiesDataMatrix(COLUMN_NAME_12, GUITYPE_CHECKBOX, "", COLUMN_LABEL_12));
        dataGroups.add(dataGroup1);
        FinEntitiesDataGroup dataGroup2 = new FinEntitiesDataGroup();
        dataGroup2.setDataGroupId(2);
        dataGroup2.setDataGroupSortId(SORT_ID_2);
        dataGroup2.setDataGroupName(GROUP_NAME_2);
        dataGroup2.setFinEntitiesDataMatrixs(new ArrayList<FinEntitiesDataMatrix>());
        dataGroup2.getFinEntitiesDataMatrixs().add(getFinEntitiesDataMatrix(COLUMN_NAME_21, GUITYPE_CHECKBOX, "", COLUMN_LABEL_21));
        dataGroup2.getFinEntitiesDataMatrixs().add(getFinEntitiesDataMatrix(COLUMN_NAME_12, GUITYPE_CHECKBOX, "", COLUMN_LABEL_12));
        // this inactive one will not be included
        dataGroup2.getFinEntitiesDataMatrixs().get(1).setActive(false);
        dataGroups.add(dataGroup2);
        
        return dataGroups;
        
    }
    
    private FinEntitiesDataMatrix getFinEntitiesDataMatrix(String columnName,String guiType,String lookupArgument,String columnLabel) {
        FinEntitiesDataMatrix dataMatrix = new FinEntitiesDataMatrix();
        dataMatrix.setColumnName(columnName);
        dataMatrix.setGuiType(guiType);
        dataMatrix.setLookupArgument(lookupArgument);
        dataMatrix.setColumnLabel(columnLabel);
        dataMatrix.setActive(true);
        return dataMatrix;
    }  
    
     private FinIntEntityRelType getFinIntEntityRelType(String code, Integer sortId, boolean active) {
         FinIntEntityRelType finIntEntityRelType = new FinIntEntityRelType();
         finIntEntityRelType.setRelationshipTypeCode(code);
         finIntEntityRelType.setSortId(sortId);
         finIntEntityRelType.setActive(active);
         finIntEntityRelType.setDescription("Description"+code);
         return finIntEntityRelType;
     }
}
