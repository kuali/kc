/*
 * Copyright 2005-2013 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.iacuc.procedures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.kuali.kra.iacuc.IacucPainCategory;
import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.IacucProcedureCategoryCustomData;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonTrainingService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;

/**
 * This class...
 */
/**
 * This class...
 */
/**
 * This class...
 */
public class IacucProtocolProcedureServiceImpl implements IacucProtocolProcedureService {

    private BusinessObjectService businessObjectService;
    private IacucProtocolPersonTrainingService iacucProtocolPersonTrainingService;
    private static final String PROTOCOL_STUDY_GROUP_SEQUENCE_ID = "SEQ_IACUC_PROTO_STUDY_GROUP_ID";
    private static final String PROTOCOL_PERSON_RESPONSIBLE_SEQUENCE_ID = "SEQ_IACUC_PROC_PERS_RESP_ID";
    private static final String PROTOCOL_STUDY_GROUP_LOCATION_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_GRP_LOC_ID";
    private static final String PROTOCOL_STUDY_GROUP_CUSTOM_DATA_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_CUS_DAT_ID";
    private static final String PROTOCOL_STUDY_GROUP_HEADER_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_GRP_HDR_ID";
    private static final String PROTOCOL_STUDY_GROUP_DETAIL_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_GRP_DTL_ID";
     
    private IacucProtocolSpeciesService iacucProtocolSpeciesService;
    
    private SequenceAccessorService sequenceAccessorService;

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getAllProcedures()
     */
    public List<IacucProcedure> getAllProcedures() {
        return (List<IacucProcedure>)getBusinessObjectService().findAllOrderBy(IacucProcedure.class, "procedureCategoryCode", true);
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getAllProcedureCategories()
     */
    public List<IacucProcedureCategory> getAllProcedureCategories() {
        return (List<IacucProcedureCategory>)getBusinessObjectService().findAllOrderBy(IacucProcedureCategory.class, "procedureCategoryCode", true);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getProtocolSpecies()
     */
    public List<IacucProtocolSpecies> getProtocolSpecies() {
        Long protocolId = ((IacucProtocolForm) KNSGlobalVariables.getKualiForm()).getIacucProtocolDocument().getProtocol().getProtocolId();
        Map<String, Object> keyMap = new HashMap<String, Object> ();
        keyMap.put("protocolId", protocolId);
        return (List<IacucProtocolSpecies>) getBusinessObjectService().findMatching(IacucProtocolSpecies.class, keyMap);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#createNewStudyGroups(org.kuali.kra.iacuc.IacucProtocol, java.util.List, java.util.HashMap)
     */
    public void createNewStudyGroups(IacucProtocol iacucProtocol, List<IacucProtocolStudyGroupBean> sourceStudyGroupBeans, 
            HashMap<Integer, Integer> protocolSpeciesIdMapping) {
        iacucProtocol.setIacucProtocolStudyGroupBeans(new ArrayList<IacucProtocolStudyGroupBean>());
        iacucProtocol.setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroupBean>());
        for(IacucProtocolStudyGroupBean studyGroupBean : sourceStudyGroupBeans) {
            IacucProtocolStudyGroupBean newStudyGroupBean = getNewCopyOfStudyGroupBean(studyGroupBean);
            newStudyGroupBean.setIacucProtocolStudyGroupDetailBeans(new ArrayList<IacucProtocolStudyGroupDetailBean>());
            setAttributesForNewStudyGroupBean(newStudyGroupBean, iacucProtocol);
            for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : studyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                IacucProtocolStudyGroupDetailBean newStudyGroupDetailBean = getNewCopyOfStudyGroupDetailBean(studyGroupDetailBean);
                newStudyGroupDetailBean.setIacucProtocolStudyCustomDataList(new ArrayList<IacucProtocolStudyCustomData>());
                newStudyGroupDetailBean.setIacucProcedurePersonsResponsible(new ArrayList<IacucProcedurePersonResponsible>());
                newStudyGroupDetailBean.setIacucProtocolStudyGroupLocations(new ArrayList<IacucProtocolStudyGroupLocation>());

                newStudyGroupDetailBean.setIacucProtocolStudyGroupDetailId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_DETAIL_SEQUENCE_ID));
                newStudyGroupDetailBean.setIacucProtocolStudyGroupHeaderId(newStudyGroupBean.getIacucProtocolStudyGroupHeaderId());

                List<IacucProtocolStudyGroup> studyGroups = new ArrayList<IacucProtocolStudyGroup>();
                for(IacucProtocolStudyGroup protocolStudyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
                    IacucProtocolStudyGroup newProtocolStudyGroup = getNewCopyOfProtocolStudyGroup(protocolStudyGroup);
                    newProtocolStudyGroup.setIacucProtocolStudyCustomDataList(new ArrayList<IacucProtocolStudyCustomData>());
                    newProtocolStudyGroup.setIacucProcedurePersonsResponsible(new ArrayList<IacucProcedurePersonResponsible>());
                    newProtocolStudyGroup.setIacucProtocolStudyGroupLocations(new ArrayList<IacucProtocolStudyGroupLocation>());
                    setAttributesForNewProtocolStudyGroup(newProtocolStudyGroup, newStudyGroupDetailBean, iacucProtocol);
                    Integer newProtocolSpeciesId = protocolSpeciesIdMapping.get(newProtocolStudyGroup.getIacucProtocolSpeciesId());
                    if(newProtocolSpeciesId == null) {
                        newProtocolSpeciesId = newProtocolStudyGroup.getIacucProtocolSpeciesId();
                    }
                    newProtocolStudyGroup.setIacucProtocolSpeciesId(newProtocolSpeciesId);
                    studyGroups.add(newProtocolStudyGroup);
                    
                    for(IacucProcedurePersonResponsible procedurePersonResponsible : protocolStudyGroup.getIacucProcedurePersonsResponsible()) {
                        IacucProcedurePersonResponsible newPersonResponsible = getNewCopyOfPersonResponsible(procedurePersonResponsible);
                        setAttributesForNewProcedurePersonResponsible(newPersonResponsible, newProtocolStudyGroup, newStudyGroupBean);
                        newProtocolStudyGroup.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
                    }
                    
                    for(IacucProtocolStudyGroupLocation studyGroupLocation : protocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
                        IacucProtocolStudyGroupLocation newStudyGroupLocation = getNewCopyOfStudyGroupLocation(studyGroupLocation);
                        updateAttributesForNewProcedureLocation(newStudyGroupLocation, newProtocolStudyGroup, iacucProtocol);
                        newProtocolStudyGroup.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
                    }
                    
                    for(IacucProtocolStudyCustomData studyGroupCustomData : protocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                        IacucProtocolStudyCustomData newStudyCustomData = getNewCopyOfStudyCustomData(studyGroupCustomData);
                        updateAttributesForNewProcedureCustomData(newStudyCustomData, iacucProtocol, newProtocolStudyGroup);
                        newProtocolStudyGroup.getIacucProtocolStudyCustomDataList().add(newStudyCustomData);
                    }
                    
                }
                newStudyGroupDetailBean.setIacucProtocolStudyGroups(studyGroups);
                newStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().add(newStudyGroupDetailBean);
            }
            iacucProtocol.getIacucProtocolStudyGroups().add(newStudyGroupBean);
        }
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProtocolStudyGroup(org.kuali.kra.iacuc.IacucProtocolForm)
     */
    public void addProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {
        boolean isNewCategoryBean = ObjectUtils.isNull(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
        List<String> protocolSpeciesAndGroups =  selectedProtocolStudyGroupBean.getProtocolSpeciesAndGroups(); 
        List<String> protocolPersonsResponsible =  selectedProtocolStudyGroupBean.getProtocolPersonsResponsible(); 
        if(isNewCategoryBean) {
            setAttributesForNewStudyGroupBean(selectedProtocolStudyGroupBean, iacucProtocol);
        }
        List<IacucProtocolStudyGroupDetailBean> newDetailBeans = addStudyGroupBeans(protocolSpeciesAndGroups, selectedProtocolStudyGroupBean, iacucProtocol);
        List<IacucProcedurePersonResponsible> procedurePersonsResponsible = addPersonsResponsible(protocolPersonsResponsible);
        addPersonToDetailBean(newDetailBeans, procedurePersonsResponsible,selectedProtocolStudyGroupBean, iacucProtocol);
        addProcedureCustomData(selectedProtocolStudyGroupBean, newDetailBeans, iacucProtocol);
        if(isNewCategoryBean) {
            iacucProtocol.getIacucProtocolStudyGroups().add(selectedProtocolStudyGroupBean);
        }else {
            IacucProtocolStudyGroupBean selectedProtocolStudyGroup = getProtocolStudyGroup(iacucProtocol, selectedProtocolStudyGroupBean);
            selectedProtocolStudyGroup.getIacucProtocolStudyGroupDetailBeans().addAll(getAllDetailBeansThatDoesNotExist(newDetailBeans, selectedProtocolStudyGroup));
        }
    }

    private List<IacucProtocolStudyGroupDetailBean> getAllDetailBeansThatDoesNotExist(List<IacucProtocolStudyGroupDetailBean> newDetailBeans, IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean) {
        List<IacucProtocolStudyGroupDetailBean> newBeansToAdd = new ArrayList<IacucProtocolStudyGroupDetailBean>();
        newBeansToAdd.addAll(newDetailBeans);
        newBeansToAdd.removeAll(iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans());
        return newBeansToAdd;
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteProtocolStudyGroup(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, IacucProtocol iacucProtocol) {
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().remove(selectedProcedureDetailBean);
        IacucProtocolStudyGroupBean selectedProtocolStudyGroup = getProtocolStudyGroup(iacucProtocol, selectedProtocolStudyGroupBean);
        if(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().size() == 0) {
            iacucProtocol.getIacucProtocolStudyGroups().remove(selectedProtocolStudyGroup);
            selectedProtocolStudyGroupBean.setIacucProtocolStudyGroupHeaderId(null);
        }else {
            selectedProtocolStudyGroup.getIacucProtocolStudyGroupDetailBeans().remove(selectedProcedureDetailBean);
        }
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteProcedurePersonResponsible(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean, org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void deleteProcedurePersonResponsible(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProcedurePersonResponsible selectedPersonResponsible, IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroup beanProtocolStudyGroup : selectedProcedureDetailBean.getIacucProtocolStudyGroups()) {
            beanProtocolStudyGroup.getIacucProcedurePersonsResponsible().remove(selectedPersonResponsible);
        }
        selectedProcedureDetailBean.getIacucProcedurePersonsResponsible().remove(selectedPersonResponsible);
    }
    
       
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteStudyGroupLocation(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void deleteStudyGroupLocation(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProtocolStudyGroupLocation selectedStudyGroupLocation, IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroup beanProtocolStudyGroup : selectedProcedureDetailBean.getIacucProtocolStudyGroups()) {
            beanProtocolStudyGroup.getIacucProtocolStudyGroupLocations().remove(selectedStudyGroupLocation);
        }
        selectedProcedureDetailBean.getIacucProtocolStudyGroupLocations().remove(selectedStudyGroupLocation);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProcedurePersonResponsible(org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void addProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible, 
            IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        List<String> protocolPersonsResponsible =  newIacucProcedurePersonResponsible.getProtocolPersonsResponsible(); 
        List<IacucProcedurePersonResponsible> procedurePersonsResponsible = addNewPersonResponsible(protocolPersonsResponsible, newIacucProcedurePersonResponsible);
        addPersonToStudyGroupAndDetailBean(selectedProcedureDetailBean, procedurePersonsResponsible, selectedProtocolStudyGroupBean);
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProcedureLocation(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void addProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProtocol protocol) {
        int locationGroupIndex = selectedProcedureDetailBean.getIacucProtocolStudyGroupLocations().size()+1;
        newIacucProtocolStudyGroupLocation.setLocationGroupIndex(locationGroupIndex);
        selectedProcedureDetailBean.getIacucProtocolStudyGroupLocations().add(newIacucProtocolStudyGroupLocation);
        for(IacucProtocolStudyGroup protocolStudyGroup : selectedProcedureDetailBean.getIacucProtocolStudyGroups()) {
            IacucProtocolStudyGroupLocation newStudyGroupLocation = (IacucProtocolStudyGroupLocation)ObjectUtils.deepCopy(newIacucProtocolStudyGroupLocation);
            newStudyGroupLocation.setLocationGroupIndex(locationGroupIndex);
            updateAttributesForNewProcedureLocation(newStudyGroupLocation, protocolStudyGroup, protocol);
            protocolStudyGroup.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
        }
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#resetProcedurePanel(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void resetProcedurePanel(IacucProtocol protocol) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans = protocol.getIacucProtocolStudyGroups();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocolStudyGroupBeans) {
            for(IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                iacucProtocolStudyGroupDetailBean.resetPersistenceState();
                for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups()) {
                    iacucProtocolStudyGroup.resetPersistenceState();
                    for(IacucProcedurePersonResponsible protocolPersonResponsible : iacucProtocolStudyGroup.getIacucProcedurePersonsResponsible()) {
                        protocolPersonResponsible.resetPersistenceState();
                    }
                    for(IacucProtocolStudyGroupLocation protocolLocation : iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
                        protocolLocation.resetPersistenceState();
                    }
                    for(IacucProtocolStudyCustomData protocolCustomData : iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                        protocolCustomData.resetPersistenceState();
                    }
                }
            }
        }
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#setIacucProtocolStudyGroupReferences(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void setIacucProtocolStudyGroupReferences(IacucProtocol protocol) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans = protocol.getIacucProtocolStudyGroupBeans();
        HashMap<Integer, Integer> newSpeciesIdMapping = new HashMap<Integer, Integer>();
        if(isSpeciesAndProcedureExists(protocol)) {
            newSpeciesIdMapping = getIacucProtocolSpeciesService().getNewProtocolSpeciesMap(protocol);
        }
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocolStudyGroupBeans) {
            for(IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups()) {
                    Integer newProtocolSpeciesId = newSpeciesIdMapping.get(iacucProtocolStudyGroup.getIacucProtocolSpeciesId());
                    iacucProtocolStudyGroup.setIacucProtocolSpeciesId(newProtocolSpeciesId);
                    setStudyGoupPersonResponsibleReferences(iacucProtocolStudyGroup.getIacucProcedurePersonsResponsible(), protocol);
                    setStudyGoupCustomDataReferences(iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList(), protocol);
                    setStudyGoupLocationReferences(iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations(), protocol);
                }
            }
        }
    }
    
    private boolean isSpeciesAndProcedureExists(IacucProtocol protocol) {
        return protocol.getIacucProtocolSpeciesList().size() > 0 && protocol.getIacucProtocolStudyGroups().size() > 0;
    }
    
    private void setStudyGoupPersonResponsibleReferences(List<IacucProcedurePersonResponsible> personsResponsible, IacucProtocol protocol) {
        for(IacucProcedurePersonResponsible protocolPersonResponsible : personsResponsible) {
            protocolPersonResponsible.setProtocolId(protocol.getProtocolId());
            protocolPersonResponsible.setProtocolNumber(protocol.getProtocolNumber());
            protocolPersonResponsible.setSequenceNumber(protocol.getSequenceNumber());
        }
    }
    
    private void setStudyGoupCustomDataReferences(List<IacucProtocolStudyCustomData> beanCustomDataList, IacucProtocol protocol) {
        for(IacucProtocolStudyCustomData protocolCustomData : beanCustomDataList) {
            protocolCustomData.setProtocolId(protocol.getProtocolId());
            protocolCustomData.setProtocolNumber(protocol.getProtocolNumber());
            protocolCustomData.setSequenceNumber(protocol.getSequenceNumber());
        }
    }
    
    private void setStudyGoupLocationReferences(List<IacucProtocolStudyGroupLocation> beanLocations, IacucProtocol protocol) {
        for(IacucProtocolStudyGroupLocation protocolLocation : beanLocations) {
            protocolLocation.setProtocolId(protocol.getProtocolId());
            protocolLocation.setProtocolNumber(protocol.getProtocolNumber());
            protocolLocation.setSequenceNumber(protocol.getSequenceNumber());
        }
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#updateIacucProtocolStudyGroup(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void updateIacucProtocolStudyGroup(IacucProtocol protocol) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans = protocol.getIacucProtocolStudyGroupBeans();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocolStudyGroupBeans) {
            for(IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                updateExistingStudyGroupPersonResponsible(iacucProtocolStudyGroupDetailBean.getIacucProcedurePersonsResponsible(), 
                        iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups());
                updateExistingStudyGroupLocation(iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroupLocations(), 
                        iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups());
                updateExistingStudyGroupCustomData(iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyCustomDataList(), 
                        iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups());
            }
        }
    }


    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getRevisedStudyGroupBeans(java.util.List)
     */
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(IacucProtocol iacucProtocol, List<IacucProcedure> allProcedures) {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = iacucProtocol.getIacucProtocolStudyGroupBeans();
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroups = iacucProtocol.getIacucProtocolStudyGroups();
        if(studyGroupBeans.isEmpty()) {
            studyGroupBeans = getNewListOfStudyGroupBeans();
            if(!iacucProtocolStudyGroups.isEmpty()) {
                groupProtocolStudyAndBeans(iacucProtocol, studyGroupBeans, allProcedures);
            }
        }
       return studyGroupBeans;
    }
    
    /**
     * This method is to update individual study group records that are part of the grouped category.
     * Here attributes linked to person responsible are updated.
     * @param beanStudyGroups
     */
    private void updateExistingStudyGroupPersonResponsible(List<IacucProcedurePersonResponsible> beanPersonsResponsible, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(IacucProcedurePersonResponsible beanPersonResponsible : beanPersonsResponsible) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
                for(IacucProcedurePersonResponsible protocolPersonResponsible : iacucProtocolStudyGroup.getIacucProcedurePersonsResponsible()) {
                    if(protocolPersonResponsible.getPersonId().equalsIgnoreCase(beanPersonResponsible.getPersonId())) {
                        protocolPersonResponsible.setPersonResponsibleDescription(beanPersonResponsible.getPersonResponsibleDescription());
                    }
                }
            }
        }
    }
    
    /**
     * This method is to update individual study group records that are part of the grouped category.
     * Here attributes linked to study group location are updated.
     * @param beanStudyGroupLocations
     */
    private void updateExistingStudyGroupLocation(List<IacucProtocolStudyGroupLocation> beanLocations, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(IacucProtocolStudyGroupLocation beanLocation : beanLocations) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
                for(IacucProtocolStudyGroupLocation protocolLocation : iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
                    if(protocolLocation.getLocationGroupIndex().equals(beanLocation.getLocationGroupIndex())) { 
                        protocolLocation.setLocationId(beanLocation.getLocationId());
                        protocolLocation.setLocationTypeCode(beanLocation.getLocationTypeCode());
                        protocolLocation.setLocationRoom(beanLocation.getLocationRoom());
                        protocolLocation.setStudyGroupLocationDescription(beanLocation.getStudyGroupLocationDescription());
                    }
                }
            }
        }
    }

    /**
     * This method is to update individual study group records that are part of the grouped category.
     * Here attributes linked to study group custom data are updated.
     * @param beanCustomDataList
     * @param iacucProtocolStudyGroups
     */
    private void updateExistingStudyGroupCustomData(List<IacucProtocolStudyCustomData> beanCustomDataList, List<IacucProtocolStudyGroup> iacucProtocolStudyGroups) {
        for(IacucProtocolStudyCustomData beanCustomData : beanCustomDataList) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
                for(IacucProtocolStudyCustomData protocolCustomData : iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                    if(protocolCustomData.getProcedureCustomAttributeId().equals(beanCustomData.getProcedureCustomAttributeId())) { 
                        protocolCustomData.setValue(beanCustomData.getValue());
                    }
                }
            }
        }
    }
    
    /**
     * This method is to set study group reference
     * @param newIacucProtocolStudyGroupLocation
     * @param protocolStudyGroup
     * @param protocol
     */
    private void updateAttributesForNewProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, IacucProtocolStudyGroup protocolStudyGroup, 
            IacucProtocol protocol) { 
        newIacucProtocolStudyGroupLocation.setStudyGroupLocationId(getNextStudyGroupLocationId(protocolStudyGroup));
        newIacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupId(protocolStudyGroup.getIacucProtocolStudyGroupId());
        newIacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupLocationId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_LOCATION_SEQUENCE_ID));
        newIacucProtocolStudyGroupLocation.setProtocolId(protocol.getProtocolId());
        newIacucProtocolStudyGroupLocation.setProtocolNumber(protocol.getProtocolNumber());
        newIacucProtocolStudyGroupLocation.setSequenceNumber(protocol.getSequenceNumber());
    }

    /**
     * This method is to get the protocol study group for a given bean
     * @param protocol
     * @param selectedStudyGroupBean
     * @return
     */
    private IacucProtocolStudyGroupBean getProtocolStudyGroup(IacucProtocol protocol, IacucProtocolStudyGroupBean selectedStudyGroupBean) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroups = protocol.getIacucProtocolStudyGroups();
        IacucProtocolStudyGroupBean selectedProtocolStudyGroup = null;
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            if(selectedStudyGroupBean.getProcedureCategoryCode().equals(iacucProtocolStudyGroup.getProcedureCategoryCode()) && 
                    selectedStudyGroupBean.getProcedureCode().equals(iacucProtocolStudyGroup.getProcedureCode())) {
                selectedProtocolStudyGroup = iacucProtocolStudyGroup;
            }
        }
        return selectedProtocolStudyGroup;
    }
    
    /**
     * This method is to group the display and persisted objects
     * @param protocol
     * @param iacucProtocolStudyGroupBeans
     */
    private void groupProtocolStudyAndBeans(IacucProtocol protocol, List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupBeans, 
            List<IacucProcedure> allProcedures) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroups = protocol.getIacucProtocolStudyGroups();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocolStudyGroupBeans) {
            for(IacucProtocolStudyGroupBean iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
                if(iacucProtocolStudyGroupBean.getProcedureCategoryCode().equals(iacucProtocolStudyGroup.getProcedureCategoryCode()) && 
                        iacucProtocolStudyGroupBean.getProcedureCode().equals(iacucProtocolStudyGroup.getProcedureCode())) {
                    iacucProtocolStudyGroupBean.setIacucProtocolStudyGroupHeaderId(iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId());
                    iacucProtocolStudyGroupBean.setProtocol(iacucProtocolStudyGroup.getProtocol());
                    iacucProtocolStudyGroupBean.setProtocolId(iacucProtocolStudyGroup.getProtocolId());
                    iacucProtocolStudyGroupBean.setProtocolNumber(iacucProtocolStudyGroup.getProtocolNumber());
                    iacucProtocolStudyGroupBean.setSequenceNumber(iacucProtocolStudyGroup.getSequenceNumber());
                    iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().addAll(iacucProtocolStudyGroup.getIacucProtocolStudyGroupDetailBeans());
                    selectUsedProcedureCategory(allProcedures, iacucProtocolStudyGroup.getProcedureCode());
                    break;
                }
            }
        }
        syncStudyGroupAndBeans(iacucProtocolStudyGroupBeans);
    }
    
    /**
     * This method is to check if a procedure is persisted.
     * @param allProcedures
     * @param selectedProcedureCode
     */
    private void selectUsedProcedureCategory(List<IacucProcedure> allProcedures, Integer selectedProcedureCode) {
        for(IacucProcedure iacucProcedure : allProcedures) {
            if(iacucProcedure.getProcedureCode().equals(selectedProcedureCode)) {
                iacucProcedure.setProcedureSelected(true);
                break;
            }
        }
    }
    
    /**
     * This method is to sync study groups beans and protocol study groups
     * @param studyGroupBeans
     */
    private void syncStudyGroupAndBeans(List<IacucProtocolStudyGroupBean> studyGroupBeans) {
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : studyGroupBeans) {
            for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                syncPersonsResposible(studyGroupDetailBean);
                syncStudyGroupLocation(studyGroupDetailBean);
                syncStudyGroupCustomData(studyGroupDetailBean);
            }
        }
    }
    
    
    /**
     * This method is to sync detail bean and study group persons responsible
     * @param studyGroupDetailBean
     */
    private void syncPersonsResposible(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        List<IacucProcedurePersonResponsible> protocolPersonsResponsible = getDistinctPersonsResponsibleForDetailBean(studyGroupDetailBean);
        for(IacucProcedurePersonResponsible personResponsible : protocolPersonsResponsible) {
            IacucProcedurePersonResponsible newPersonResponsible = getNewCopyOfPersonResponsible(personResponsible);
            newPersonResponsible.setTrainings(getIacucPersonTrainingDetails(newPersonResponsible.getPersonId()));
            studyGroupDetailBean.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
        }
    }

    /**
     * This method is to sync detail bean and study group locations
     * @param studyGroupDetailBean
     */
    private void syncStudyGroupLocation(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        List<IacucProtocolStudyGroupLocation> protocolGroupLocations = getDistinctGroupLocationsForDetailBean(studyGroupDetailBean);
        for(IacucProtocolStudyGroupLocation groupLocation : protocolGroupLocations) {
            IacucProtocolStudyGroupLocation newStudyGroupLocation = (IacucProtocolStudyGroupLocation)ObjectUtils.deepCopy(groupLocation);
            studyGroupDetailBean.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
        }
    }
    
    /**
     * This method is to sync detail bean and study group custom data
     * @param studyGroupDetailBean
     */
    private void syncStudyGroupCustomData(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        List<IacucProtocolStudyCustomData> protocolStudyCustomDataList = getDistinctGroupCustomDataListForDetailBean(studyGroupDetailBean);
        for(IacucProtocolStudyCustomData groupCustomData : protocolStudyCustomDataList) {
            IacucProtocolStudyCustomData newIacucProtocolStudyCustomData = (IacucProtocolStudyCustomData)ObjectUtils.deepCopy(groupCustomData);
            studyGroupDetailBean.getIacucProtocolStudyCustomDataList().add(newIacucProtocolStudyCustomData);
        }
    }

    /**
     * This method is to get a list of distinct list of persons responsible and
     * add to the corresponding detail bean so that it gets populated correctly in the tab
     * @param studyGroupDetailBean
     * @return
     */
    private List<IacucProcedurePersonResponsible> getDistinctPersonsResponsibleForDetailBean(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        Set<String> distinctPersonIds = new HashSet<String>();
        List<IacucProcedurePersonResponsible> beanPersonsResponsible = new ArrayList<IacucProcedurePersonResponsible>();
        for(IacucProtocolStudyGroup protocolStudyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProcedurePersonResponsible personResponsible : protocolStudyGroup.getIacucProcedurePersonsResponsible()) {
                if(!distinctPersonIds.contains(personResponsible.getPersonId())) {
                    distinctPersonIds.add(personResponsible.getPersonId());
                    beanPersonsResponsible.add(personResponsible);
                }
            }
        }
        return beanPersonsResponsible;
    }
    
    /**
     * This method is to get a list of distinct list of locations and
     * add to the corresponding detail bean so that it gets populated correctly in the tab
     * @param studyGroupDetailBean
     * @return
     */
    private List<IacucProtocolStudyGroupLocation> getDistinctGroupLocationsForDetailBean(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        Set<Integer> distinctLocationIndexes = new HashSet<Integer>();
        List<IacucProtocolStudyGroupLocation> beanGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
        for(IacucProtocolStudyGroup protocolStudyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroupLocation groupLocation : protocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
                if(!distinctLocationIndexes.contains(groupLocation.getLocationGroupIndex())) {
                    distinctLocationIndexes.add(groupLocation.getLocationGroupIndex());
                    beanGroupLocations.add(groupLocation);
                }
            }
        }
        return beanGroupLocations;
    }
    
    /**
     * This method is to get a list of distinct list of custom data and
     * add to the corresponding detail bean so that it gets populated correctly in the tab
     * @param studyGroupDetailBean
     * @return
     */
    private List<IacucProtocolStudyCustomData> getDistinctGroupCustomDataListForDetailBean(IacucProtocolStudyGroupDetailBean studyGroupDetailBean) {
        Set<Integer> distinctCustomIds = new HashSet<Integer>();
        List<IacucProtocolStudyCustomData> beanGroupCustomDataList = new ArrayList<IacucProtocolStudyCustomData>();
        for(IacucProtocolStudyGroup protocolStudyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyCustomData groupCustomData : protocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                if(!distinctCustomIds.contains(groupCustomData.getProcedureCustomAttributeId())) {
                    distinctCustomIds.add(groupCustomData.getProcedureCustomAttributeId());
                    beanGroupCustomDataList.add(groupCustomData);
                }
            }
        }
        return beanGroupCustomDataList;
    }
    
    /**
     * This method is invoked 
     * when responsible person is added under each group - section Persons Responsible
     * @param protocolPersonsResponsible
     * @param newIacucProcedurePersonResponsible
     * @return
     */
    private List<IacucProcedurePersonResponsible> addNewPersonResponsible(List<String> protocolPersonsResponsible, 
            IacucProcedurePersonResponsible newIacucProcedurePersonResponsible) {
        List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible = addPersonsResponsible(protocolPersonsResponsible);
        for(IacucProcedurePersonResponsible personResponsible : iacucProcedurePersonsResponsible) {
            String description =  newIacucProcedurePersonResponsible.getPersonResponsibleDescription();
            personResponsible.setPersonResponsibleDescription(description);
        }
        return iacucProcedurePersonsResponsible;
    }
    
    /**
     * This method is to update the bean detail collection
     * update bean details - persons responsible
     * Since user can select multiple person responsible, create new person responsible 
     * for each detail
     * add study group bean to the list
     * @param iacucProcedurePersonsResponsible
     * @param iacucProtocolStudyGroupBean
     */
    private void addPersonToDetailBean(List<IacucProtocolStudyGroupDetailBean> studyGroupDetailBeans, 
            List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroupDetailBean detailBean : studyGroupDetailBeans) {
            addPersonToStudyGroupAndDetailBean(detailBean, iacucProcedurePersonsResponsible, selectedProtocolStudyGroupBean);
        }
    }

    /**
     * This method is to add new person to study group list and set it in the bean detail.
     * @param detailBean
     * @param iacucProcedurePersonsResponsible
     */
    private void addPersonToStudyGroupAndDetailBean(IacucProtocolStudyGroupDetailBean detailBean, 
            List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        detailBean.setNewIacucProcedurePersonResponsible(new IacucProcedurePersonResponsible());
        for(IacucProcedurePersonResponsible personResponsible : iacucProcedurePersonsResponsible) {
            IacucProcedurePersonResponsible newPersonResponsible = getNewCopyOfPersonResponsible(personResponsible);
            detailBean.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
        }
        addNewStudyGroupPersonResponsible(detailBean, iacucProcedurePersonsResponsible, selectedProtocolStudyGroupBean);
    }
    
    /**
     * This method is to add new responsible person information to study group collection.
     * @param detailBean
     * @param iacucProcedurePersonsResponsible
     */
    private void addNewStudyGroupPersonResponsible(IacucProtocolStudyGroupDetailBean detailBean, 
            List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        for(IacucProtocolStudyGroup protocolStudyGroup : detailBean.getIacucProtocolStudyGroups()) {
            for(IacucProcedurePersonResponsible personResponsible : iacucProcedurePersonsResponsible) {
                IacucProcedurePersonResponsible newPersonResponsible = getNewCopyOfPersonResponsible(personResponsible);
                setAttributesForNewProcedurePersonResponsible(newPersonResponsible, protocolStudyGroup, selectedProtocolStudyGroupBean);
                protocolStudyGroup.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
            }
        }
    }
    
    /**
     * This method is to set values to reference attributes in person responsible entity
     * Basically set all information required for a new record
     * Also set the protocol references to record information in database.
     * @param personResponsible
     * @param studyGroup
     */
    private void setAttributesForNewProcedurePersonResponsible(IacucProcedurePersonResponsible personResponsible, 
            IacucProtocolStudyGroup studyGroup, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        personResponsible.setIacucProcedurePersonResponsibleId(getNextSequenceNumber(PROTOCOL_PERSON_RESPONSIBLE_SEQUENCE_ID));
        personResponsible.setIacucProtocolStudyGroupId(studyGroup.getIacucProtocolStudyGroupId());
        personResponsible.setProtocolNumber(selectedProtocolStudyGroupBean.getProtocolNumber());
        personResponsible.setSequenceNumber(selectedProtocolStudyGroupBean.getSequenceNumber());
        personResponsible.setProtocolId(selectedProtocolStudyGroupBean.getProtocolId());
    }
    
    /**
     * This method is to add person responsible to each study group.
     * This method is invoked when user select group/species and responsible persons
     * @param protocol
     * @param protocolPersonsResponsible
     * @return
     */
    private List<IacucProcedurePersonResponsible> addPersonsResponsible(List<String> protocolPersonsResponsible) {
        List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible = new ArrayList<IacucProcedurePersonResponsible>();
        for(String personKey : protocolPersonsResponsible) {
            IacucProcedurePersonResponsible iacucProcedurePersonResponsible = new IacucProcedurePersonResponsible();
            String personId = null;
            String personName = null;
            StringTokenizer personInfo = new StringTokenizer(personKey, Constants.IACUC_PROCEDURE_PERSON_RESPONSIBLE_DELIMITER); 
            while(personInfo.hasMoreTokens()) { 
                personId = personInfo.nextToken(); 
                personName = personInfo.nextToken(); 
            } 
            iacucProcedurePersonResponsible.setPersonId(personId);
            iacucProcedurePersonResponsible.setPersonName(personName);
            iacucProcedurePersonResponsible.setTrainings(getIacucPersonTrainingDetails(personId));
            iacucProcedurePersonsResponsible.add(iacucProcedurePersonResponsible);
        }
        return iacucProcedurePersonsResponsible;
    }
    
    protected List<IacucPersonTraining> getIacucPersonTrainingDetails(String personId) {
        return getIacucProtocolPersonTrainingService().getIacucPersonTrainingDetails(personId);
    }
    
    /**
     * This method is to add protocol study group bean and format display values.
     * Return a list of original study group detail beans
     * @param protocol
     * @param protocolSpeciesAndGroups
     * @param iacucProtocolStudyGroupBean
     * @return
     */
    private List<IacucProtocolStudyGroupDetailBean> addStudyGroupBeans(List<String> protocolSpeciesAndGroups, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol protocol) {
        List<IacucProtocolStudyGroupDetailBean> updatedDetailBeans = new ArrayList<IacucProtocolStudyGroupDetailBean>();
        HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = getNewProcedureStudyGroupedBySpecies(protocolSpeciesAndGroups);
        for (Map.Entry<Integer, List<IacucProtocolStudyGroup>> entry : studiesGroupedBySpecies.entrySet()) {
            Integer speciesCode = entry.getKey();
            List<IacucProtocolStudyGroup> studyGroups = entry.getValue();
            IacucProtocolStudyGroupDetailBean selectedDetailBean = null;
            selectedDetailBean = addNewStudyGroupDetailBean(studyGroups, selectedProtocolStudyGroupBean, speciesCode, protocol);
            updatedDetailBeans.add(selectedDetailBean);
        }
        return updatedDetailBeans;
    }
    
    /**
     * This method is to add a new study group detail bean
     * Detail bean is grouped by species and all study groups linked to this group
     * are stored here.
     * @param studyGroups
     * @param selectedProtocolStudyGroupBean
     */
    private IacucProtocolStudyGroupDetailBean addNewStudyGroupDetailBean(List<IacucProtocolStudyGroup> studyGroups, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, Integer speciesCode, IacucProtocol protocol) {
        IacucProtocolStudyGroupDetailBean newIacucProtocolStudyGroupDetailBean = new IacucProtocolStudyGroupDetailBean();
        addIacucProtocolStudyGroups(selectedProtocolStudyGroupBean, studyGroups, newIacucProtocolStudyGroupDetailBean, 
                speciesCode, protocol);
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().add(newIacucProtocolStudyGroupDetailBean);
        return newIacucProtocolStudyGroupDetailBean;
    }
    
    /**
     * This method is to add protocol study group and update group detail bean for changes
     * to protocol study groups
     * @param studyGroups
     * @param iacucProtocolStudyGroupDetailBean
     * @param speciesCode
     */
    private void addIacucProtocolStudyGroups(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, List<IacucProtocolStudyGroup> studyGroups, 
            IacucProtocolStudyGroupDetailBean iacucProtocolStudyGroupDetailBean, Integer speciesCode, IacucProtocol protocol) {
        Integer speciesCount = 0;
        TreeSet<IacucPainCategory> painCategory = new TreeSet<IacucPainCategory>();
        List<String> speciesAndGroups = new ArrayList<String>();
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : studyGroups) {
            IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
            painCategory.add(protocolSpecies.getIacucPainCategory());
            speciesCount = speciesCount + protocolSpecies.getSpeciesCount();
            speciesAndGroups.add(protocolSpecies.getGroupAndSpecies());
            iacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups().add(iacucProtocolStudyGroup);
        }
        iacucProtocolStudyGroupDetailBean.setIacucProtocolStudyGroupDetailId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_DETAIL_SEQUENCE_ID));
        iacucProtocolStudyGroupDetailBean.setIacucProtocolStudyGroupHeaderId(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
        iacucProtocolStudyGroupDetailBean.setSpeciesCode(speciesCode);
        iacucProtocolStudyGroupDetailBean.setMaxPainCategory(painCategory.last().getPainCategory());
        iacucProtocolStudyGroupDetailBean.setMaxPainCategoryCode(painCategory.last().getPainCategoryCode());
        iacucProtocolStudyGroupDetailBean.setMaxIacucPainCategory(painCategory.last());
        iacucProtocolStudyGroupDetailBean.setSpeciesAndGroupsText(speciesAndGroups);
        iacucProtocolStudyGroupDetailBean.setTotalSpeciesCount(speciesCount);
        setAttributesForNewProtocolStudyGroups(studyGroups, iacucProtocolStudyGroupDetailBean, protocol);
    }
    
    /**
     * This method is to update the study group information found in the group bean.
     * set all information required for existing record
     * Also set the protocol references to record information in database.
     * @param studyGroups
     * @param studyGroupDetailBean
     */
    private void setAttributesForNewProtocolStudyGroups(List<IacucProtocolStudyGroup> studyGroups, 
            IacucProtocolStudyGroupDetailBean studyGroupDetailBean, IacucProtocol protocol) {
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : studyGroups) {
            setAttributesForNewProtocolStudyGroup(iacucProtocolStudyGroup, studyGroupDetailBean, protocol);
        }
    }

    private void setAttributesForNewProtocolStudyGroup(IacucProtocolStudyGroup iacucProtocolStudyGroup, 
            IacucProtocolStudyGroupDetailBean studyGroupDetailBean, IacucProtocol protocol) {
        iacucProtocolStudyGroup.setIacucProtocolStudyGroupId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_SEQUENCE_ID));
        iacucProtocolStudyGroup.setIacucProtocolStudyGroupDetailId(studyGroupDetailBean.getIacucProtocolStudyGroupDetailId());
        iacucProtocolStudyGroup.setStudyGroupId(getNextStudyGroupId(protocol));
        iacucProtocolStudyGroup.setPainCategory(studyGroupDetailBean.getMaxPainCategory());
        iacucProtocolStudyGroup.setPainCategoryCode(studyGroupDetailBean.getMaxPainCategoryCode());
        iacucProtocolStudyGroup.setCount(studyGroupDetailBean.getTotalSpeciesCount());
    }
    
    /**
     * This method is to get the next study group location id.
     * It is just a serial number generated based on the list of study group locations
     * @param iacucProtocolStudyGroup
     * @return
     */
    private Integer getNextStudyGroupLocationId(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        int totalStudyGroupLocs = iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations().size();
        Integer nextStudyGroupLocationId = 1;
        if(totalStudyGroupLocs > 0) {
            List<IacucProtocolStudyGroupLocation> sortedStudyGroupLocations = getSortedStudyGroupLocations(iacucProtocolStudyGroup);
            IacucProtocolStudyGroupLocation studyGroupLocation = sortedStudyGroupLocations.get(totalStudyGroupLocs - 1);
            nextStudyGroupLocationId = studyGroupLocation.getStudyGroupLocationId() + 1;
        }
        return nextStudyGroupLocationId;
    }
    
    /**
     * This method is to get the next study group id.
     * It is just a serial number generated based on the list of study groups
     * @param protocol
     * @return
     */
    private Integer getNextStudyGroupId(IacucProtocol protocol) {
        int totalStudyGroups = getTotalStudyGroups(protocol);
        Integer nextStudyGroupId = 1;
        if(totalStudyGroups > 0) {
            List<IacucProtocolStudyGroup> sortedStudyGroups = getSortedStudyGroups(protocol);
            IacucProtocolStudyGroup studyGroup = sortedStudyGroups.get(totalStudyGroups - 1);
            nextStudyGroupId = studyGroup.getStudyGroupId() + 1;
        }
        return nextStudyGroupId;
    }

    /**
     * This method is to find total study groups available
     * @param protocol
     * @return
     */
    private int getTotalStudyGroups(IacucProtocol protocol) {
        int totalStudyGroups = 0;
        for(IacucProtocolStudyGroupBean studyGroupBean : protocol.getIacucProtocolStudyGroupBeans()) {
            for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : studyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                totalStudyGroups = totalStudyGroups + studyGroupDetailBean.getIacucProtocolStudyGroups().size();
            }
        }
        return totalStudyGroups;
    }
    
    /**
     * This method is to get a sorted list of current study groups.
     * @param protocol
     * @return
     */
    private List<IacucProtocolStudyGroup> getSortedStudyGroups(IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for(IacucProtocolStudyGroupBean studyGroupBean : protocol.getIacucProtocolStudyGroupBeans()) {
            for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : studyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
                for (IacucProtocolStudyGroup studyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
                    protocolStudyGroups.add((IacucProtocolStudyGroup) ObjectUtils.deepCopy(studyGroup));
                }
            }
        }
        Collections.sort(protocolStudyGroups, new Comparator<IacucProtocolStudyGroup>() {
            public int compare(IacucProtocolStudyGroup group1, IacucProtocolStudyGroup group2) {
                return group1.getStudyGroupId().compareTo(group2.getStudyGroupId());
            }
        });
        return protocolStudyGroups;
    }
    
    /**
     * This method is to get a sorted list of current study group locations.
     * @param iacucProtocolStudyGroup
     * @return
     */
    private List<IacucProtocolStudyGroupLocation> getSortedStudyGroupLocations(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        List<IacucProtocolStudyGroupLocation> protocolStudyGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>();
        for (IacucProtocolStudyGroupLocation studyGroupLocation : iacucProtocolStudyGroup.getIacucProtocolStudyGroupLocations()) {
            protocolStudyGroupLocations.add((IacucProtocolStudyGroupLocation) ObjectUtils.deepCopy(studyGroupLocation));
        }
        Collections.sort(protocolStudyGroupLocations, new Comparator<IacucProtocolStudyGroupLocation>() {
            public int compare(IacucProtocolStudyGroupLocation location1, IacucProtocolStudyGroupLocation location2) {
                return location1.getStudyGroupLocationId().compareTo(location2.getStudyGroupLocationId());
            }
        });
        return protocolStudyGroupLocations;
    }
    
    /**
     * This method is to build studies grouped by species.
     * We have a list of protocol species and groups as defined in the protocol and we group them
     * by species.
     * @param protocolSpeciesAndGroups
     * @return
     */
    private HashMap<Integer, List<IacucProtocolStudyGroup>> getNewProcedureStudyGroupedBySpecies(List<String> protocolSpeciesAndGroups) {
        HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = new HashMap<Integer, List<IacucProtocolStudyGroup>>();
        List<IacucProtocolStudyGroup> studyGroups = null;
        for(String iacucProtocolSpeciesId : protocolSpeciesAndGroups) {
            IacucProtocolStudyGroup iacucProtocolStudyGroup = new IacucProtocolStudyGroup();
            iacucProtocolStudyGroup.setIacucProtocolSpeciesId(Integer.parseInt(iacucProtocolSpeciesId));
            Integer speciesCode = getSpeciesCodeFromIacucProtocolSpecies(iacucProtocolStudyGroup);
            studyGroups = studiesGroupedBySpecies.get(speciesCode) == null ? new ArrayList<IacucProtocolStudyGroup>() : studiesGroupedBySpecies.get(speciesCode);
            studyGroups.add(iacucProtocolStudyGroup);
            studiesGroupedBySpecies.put(speciesCode, studyGroups);
        }
        return studiesGroupedBySpecies;
    }
    
    /**
     * This method is to get species code from iacuc protocol species
     * @param iacucProtocolStudyGroup
     * @return
     */
    private Integer getSpeciesCodeFromIacucProtocolSpecies(IacucProtocolStudyGroup iacucProtocolStudyGroup) {
        iacucProtocolStudyGroup.refreshReferenceObject("iacucProtocolSpecies");
        IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
        protocolSpecies.refreshReferenceObject("iacucPainCategory");
        protocolSpecies.refreshReferenceObject("iacucSpecies");
        IacucSpecies iacucSpecies = protocolSpecies.getIacucSpecies();
        return iacucSpecies.getSpeciesCode();
    }
    
    /**
     * This method is to set attribute values for new study group
     * @param selectedIacucProtocolStudyGroupBean
     * @param protocol
     */
    private void setAttributesForNewStudyGroupBean(IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean, IacucProtocol protocol) {
        selectedIacucProtocolStudyGroupBean.setIacucProtocolStudyGroupHeaderId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_HEADER_SEQUENCE_ID));
        selectedIacucProtocolStudyGroupBean.setProtocolId(protocol.getProtocolId());
        selectedIacucProtocolStudyGroupBean.setProtocolNumber(protocol.getProtocolNumber());
        selectedIacucProtocolStudyGroupBean.setSequenceNumber(protocol.getSequenceNumber());
    }
    
    /**
     * This method is to get all procedure categories and format study group bean to display in ui
     * This method is invoked initially when there are no procedures added to the protocol
     * A study group bean is created for each procedure/category code
     * @return
     */
    private List<IacucProtocolStudyGroupBean> getNewListOfStudyGroupBeans() {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = new ArrayList<IacucProtocolStudyGroupBean>();
        for(IacucProcedure iacucProcedure : getAllProcedures()) {
            IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean = new IacucProtocolStudyGroupBean();
            newIacucProtocolStudyGroupBean.setProcedureCategoryCode(iacucProcedure.getProcedureCategoryCode());
            newIacucProtocolStudyGroupBean.setProcedureCode(iacucProcedure.getProcedureCode());
            newIacucProtocolStudyGroupBean.setProcedureDescription(iacucProcedure.getProcedureDescription());
            newIacucProtocolStudyGroupBean.setProcedureCategory(iacucProcedure.getIacucProcedureCategory().getProcedureCategory());
            studyGroupBeans.add(newIacucProtocolStudyGroupBean);
        }
        return studyGroupBeans;
    }
    
 
    /**
     * This method is to add procedure custom data in detail bean.
     * Custom data attributes are added based on attributes configured for each procedure code.
     * @param selectedIacucProtocolStudyGroupBean
     * @param newStudyGroupDetailBeans
     */
    private void addProcedureCustomData(IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean, 
            List<IacucProtocolStudyGroupDetailBean> newStudyGroupDetailBeans, IacucProtocol protocol) {
        List<IacucProcedureCategoryCustomData> procedureCustomDataList = getIacucProcedureCustomData(selectedIacucProtocolStudyGroupBean.getProcedureCategoryCode());
        for(IacucProtocolStudyGroupDetailBean studyGroupDetailBean : newStudyGroupDetailBeans) {
            List<IacucProtocolStudyCustomData> protocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>();
            for(IacucProcedureCategoryCustomData procedureCategoryCustomData : procedureCustomDataList) {
                if(procedureCategoryCustomData.isActive()) {
                    IacucProtocolStudyCustomData newIacucProtocolStudyCustomData = new IacucProtocolStudyCustomData();
                    newIacucProtocolStudyCustomData.setProcedureCustomAttributeId(procedureCategoryCustomData.getId());
                    newIacucProtocolStudyCustomData.setIacucProcedureCategoryCustomData(procedureCategoryCustomData);
                    protocolStudyCustomDataList.add(newIacucProtocolStudyCustomData);
                }
            }
            for(IacucProtocolStudyCustomData iacucProtocolStudyCustomData : protocolStudyCustomDataList) {
                if(!isCustomDataExists(iacucProtocolStudyCustomData, studyGroupDetailBean.getIacucProtocolStudyCustomDataList())) {
                    studyGroupDetailBean.getIacucProtocolStudyCustomDataList().add(iacucProtocolStudyCustomData);
                }
            }
            addProcedureCustomDataInProtocolStudyGroup(studyGroupDetailBean, protocolStudyCustomDataList, protocol);
        }
    }
    
    /**
     * This method is to check if we have already added custom attributes to the group
     * @param procedureCategoryCustomData
     * @param studyGroupDetailBean
     * @return
     */
    private boolean isCustomDataExists(IacucProtocolStudyCustomData newIacucProtocolStudyCustomData, 
            List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList) {
        boolean customDataExists = false;
        for(IacucProtocolStudyCustomData iacucProtocolStudyCustomData : iacucProtocolStudyCustomDataList) {
            if(iacucProtocolStudyCustomData.getProcedureCustomAttributeId().equals(newIacucProtocolStudyCustomData.getProcedureCustomAttributeId())) {
                customDataExists = true;
                break;
            }
        }
        return customDataExists;
    }

    /**
     * This method is to add procedure custom data in original protocol study group
     * @param studyGroupDetailBean
     * @param protocolStudyCustomDataList
     * @param protocol
     */
    private void addProcedureCustomDataInProtocolStudyGroup(IacucProtocolStudyGroupDetailBean studyGroupDetailBean,
            List<IacucProtocolStudyCustomData> protocolStudyCustomDataList, IacucProtocol protocol) {
        for(IacucProtocolStudyGroup protocolStudyGroup : studyGroupDetailBean.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyCustomData studyGroupCustomData : protocolStudyCustomDataList) {
                if(!isCustomDataExists(studyGroupCustomData, protocolStudyGroup.getIacucProtocolStudyCustomDataList())) {
                    IacucProtocolStudyCustomData newIacucProtocolStudyCustomData = (IacucProtocolStudyCustomData)ObjectUtils.deepCopy(studyGroupCustomData);
                    protocolStudyGroup.getIacucProtocolStudyCustomDataList().add(newIacucProtocolStudyCustomData);
                    updateAttributesForNewProcedureCustomData(newIacucProtocolStudyCustomData, protocol, protocolStudyGroup);
                }
            }
        }
    }
    
    /**
     * This method is to set reference attributes and primary key value for new custom data
     * @param newIacucProtocolStudyCustomData
     * @param protocol
     * @param protocolStudyGroup
     */
    private void updateAttributesForNewProcedureCustomData(IacucProtocolStudyCustomData newIacucProtocolStudyCustomData, 
            IacucProtocol protocol, IacucProtocolStudyGroup protocolStudyGroup) { 
        newIacucProtocolStudyCustomData.setIacucProtocolStudyCustomDataId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_CUSTOM_DATA_SEQUENCE_ID));
        newIacucProtocolStudyCustomData.setProtocolId(protocol.getProtocolId());
        newIacucProtocolStudyCustomData.setProtocolNumber(protocol.getProtocolNumber());
        newIacucProtocolStudyCustomData.setSequenceNumber(protocol.getSequenceNumber());
        newIacucProtocolStudyCustomData.setIacucProtocolStudyGroupId(protocolStudyGroup.getIacucProtocolStudyGroupId());
    }
    
    /**
     * This method is to get custom data attributes for each procedure category
     * @return
     */
    private List<IacucProcedureCategoryCustomData> getIacucProcedureCustomData(Integer procedureCategoryCode) {
        Map<String, Integer> matchingKey = new HashMap<String, Integer>();
        matchingKey.put("procedureCategoryCode", procedureCategoryCode);
        return (List<IacucProcedureCategoryCustomData>)getBusinessObjectService().findMatchingOrderBy(IacucProcedureCategoryCustomData.class, matchingKey, "sortId", true);
    }
    
    /**
     * This method is to get the next sequence number
     * This is the primary key
     * @return
     */
    private Integer getNextSequenceNumber(String sequenceKey) {
        return getSequenceAccessorService().getNextAvailableSequenceNumber(sequenceKey).intValue();
    }

    public SequenceAccessorService getSequenceAccessorService() {
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }
    
    /**
     * This method is to get a copy of person responsible
     * @param personResponsible
     * @return
     */
    private IacucProcedurePersonResponsible getNewCopyOfPersonResponsible(IacucProcedurePersonResponsible personResponsible) {
        return (IacucProcedurePersonResponsible)ObjectUtils.deepCopy(personResponsible);
    }

    /**
     * This method is to get a copy of study group bean
     * @param protocolStudyGroupBean
     * @return
     */
    private IacucProtocolStudyGroupBean getNewCopyOfStudyGroupBean(IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        return (IacucProtocolStudyGroupBean)ObjectUtils.deepCopy(protocolStudyGroupBean);
    }
    
    /**
     * This method is to get a copy of study group detail bean
     * @param protocolStudyGroupDetailBean
     * @return
     */
    private IacucProtocolStudyGroupDetailBean getNewCopyOfStudyGroupDetailBean(IacucProtocolStudyGroupDetailBean protocolStudyGroupDetailBean) {
        return (IacucProtocolStudyGroupDetailBean)ObjectUtils.deepCopy(protocolStudyGroupDetailBean);
    }

    /**
     * This method is to get a copy of study group location
     * @param protocolStudyGroupLocation
     * @return
     */
    private IacucProtocolStudyGroupLocation getNewCopyOfStudyGroupLocation(IacucProtocolStudyGroupLocation protocolStudyGroupLocation) {
        return (IacucProtocolStudyGroupLocation)ObjectUtils.deepCopy(protocolStudyGroupLocation);
    }
    
    /**
     * This method is to get a copy of study group custom data
     * @param protocolStudyCustomData
     * @return
     */
    private IacucProtocolStudyCustomData getNewCopyOfStudyCustomData(IacucProtocolStudyCustomData protocolStudyCustomData) {
        return (IacucProtocolStudyCustomData)ObjectUtils.deepCopy(protocolStudyCustomData);
    }

    /**
     * This method is to get a copy of study group
     * @param protocolStudyGroup
     * @return
     */
    private IacucProtocolStudyGroup getNewCopyOfProtocolStudyGroup(IacucProtocolStudyGroup protocolStudyGroup) {
        return (IacucProtocolStudyGroup)ObjectUtils.deepCopy(protocolStudyGroup);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public IacucProtocolPersonTrainingService getIacucProtocolPersonTrainingService() {
        return iacucProtocolPersonTrainingService;
    }

    public void setIacucProtocolPersonTrainingService(IacucProtocolPersonTrainingService iacucProtocolPersonTrainingService) {
        this.iacucProtocolPersonTrainingService = iacucProtocolPersonTrainingService;
    }

    public IacucProtocolSpeciesService getIacucProtocolSpeciesService() {
        return iacucProtocolSpeciesService;
    }

    public void setIacucProtocolSpeciesService(IacucProtocolSpeciesService iacucProtocolSpeciesService) {
        this.iacucProtocolSpeciesService = iacucProtocolSpeciesService;
    }

}