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

import org.kuali.kra.iacuc.IacucPersonTraining;
import org.kuali.kra.iacuc.IacucProcedureCategoryCustomData;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonTrainingService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.IacucProtocolSpeciesService;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;


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
        // for copy procedures
    }

    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteProcedurePersonResponsible(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean, org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void deleteProcedurePersonResponsible(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProcedurePersonResponsible selectedPersonResponsible, IacucProtocol iacucProtocol) {
        // for delete procedure person
    }
    
       
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteStudyGroupLocation(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void deleteStudyGroupLocation(IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, 
            IacucProtocolStudyGroupLocation selectedStudyGroupLocation, IacucProtocol iacucProtocol) {
        // for delete study location
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProcedurePersonResponsible(org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void addProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible, 
            IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        // for add person procedures - outside of procedure panel
    }
    

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#resetProcedurePanel(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void resetProcedurePanel(IacucProtocol protocol) {
        // for reset persistent state
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getRevisedStudyGroupBeans(java.util.List)
     */
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(IacucProtocol iacucProtocol, List<IacucProcedure> allProcedures) {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = iacucProtocol.getIacucProtocolStudyGroupBeans();
        if(studyGroupBeans.isEmpty()) {
            studyGroupBeans = getNewListOfStudyGroupBeans(iacucProtocol, allProcedures);
        }
       return studyGroupBeans;
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
    
    
    protected List<IacucPersonTraining> getIacucPersonTrainingDetails(String personId) {
        return getIacucProtocolPersonTrainingService().getIacucPersonTrainingDetails(personId);
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#setTrainingDetails(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void setTrainingDetails(IacucProtocol protocol) {
        for(ProtocolPersonBase protocolPerson : protocol.getProtocolPersons()) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson;
            iacucProtocolPerson.setIacucPersonTrainings(getIacucPersonTrainingDetails(iacucProtocolPerson.getPersonId()));
        }
    }
    
    /**
     * This method is to find total study groups available
     * @param protocol
     * @return
     */
    private int getTotalStudyGroups(IacucProtocol protocol) {
        int totalStudyGroups = 0;
        for(IacucProtocolStudyGroupBean studyGroupBean : protocol.getIacucProtocolStudyGroupBeans()) {
            totalStudyGroups = totalStudyGroups + studyGroupBean.getIacucProtocolStudyGroups().size();
        }
        return totalStudyGroups;
    }
    
    /**
     * This method is to get all procedure categories and format study group bean to display in ui
     * A study group bean is created for each procedure/category code
     * @return
     */
    private List<IacucProtocolStudyGroupBean> getNewListOfStudyGroupBeans(IacucProtocol protocol, List<IacucProcedure> allProcedures) {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = new ArrayList<IacucProtocolStudyGroupBean>();
        for(IacucProcedure iacucProcedure : getAllProcedures()) {
            IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean = getCurrentStudyGroupForProcedure(allProcedures, protocol, iacucProcedure);
            if(ObjectUtils.isNull(iacucProtocolStudyGroupBean)) {
                iacucProtocolStudyGroupBean = new IacucProtocolStudyGroupBean();
                iacucProtocolStudyGroupBean.setProcedureCategoryCode(iacucProcedure.getProcedureCategoryCode());
                iacucProtocolStudyGroupBean.setProcedureCode(iacucProcedure.getProcedureCode());
            }
            studyGroupBeans.add(iacucProtocolStudyGroupBean);
        }
        return studyGroupBeans;
    }
    
    /**
     * This method is to get matching study group for a given procedure
     * @param protocol
     * @param iacucProcedure
     * @return
     */
    private IacucProtocolStudyGroupBean getCurrentStudyGroupForProcedure(List<IacucProcedure> allProcedures, IacucProtocol protocol, IacucProcedure iacucProcedure) {
        List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroups = protocol.getIacucProtocolStudyGroups();
        IacucProtocolStudyGroupBean currentStudyGroup = null;
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroup : iacucProtocolStudyGroups) {
            if(iacucProtocolStudyGroup.getProcedureCategoryCode().equals(iacucProcedure.getProcedureCategoryCode()) && 
                    iacucProtocolStudyGroup.getProcedureCode().equals(iacucProcedure.getProcedureCode())) {
                currentStudyGroup = iacucProtocolStudyGroup;
                selectUsedProcedureCategory(allProcedures, iacucProtocolStudyGroup.getProcedureCode());
                break;
            }
        }
        return currentStudyGroup;
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

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProtocolStudyGroup(org.kuali.kra.iacuc.IacucProtocolForm)
     */
    public void addProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {
        boolean isNewCategoryBean = ObjectUtils.isNull(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
        List<String> protocolSpeciesAndGroups =  selectedProtocolStudyGroupBean.getProtocolSpeciesAndGroups(); 
        if(isNewCategoryBean) {
            setAttributesForNewStudyGroupBean(selectedProtocolStudyGroupBean, iacucProtocol);
            iacucProtocol.getIacucProtocolStudyGroups().add(selectedProtocolStudyGroupBean);
        }
        List<IacucProtocolStudyGroup> newStudyGroups = getNewProtocolStudyGroups(protocolSpeciesAndGroups, iacucProtocol);
        addProcedureCustomData(selectedProtocolStudyGroupBean, newStudyGroups, iacucProtocol);
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().addAll(newStudyGroups);
        HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList = getUpdatedProtocolStudyGroupSpeciesList(iacucProtocol, newStudyGroups);
        addNewResponsibleProceduresForAllProtocolPersons(iacucProtocol, selectedProtocolStudyGroupBean, newStudyGroups,studyGroupSpeciesList);
        addNewResponsibleProceduresForAllProcedureLocations(iacucProtocol, selectedProtocolStudyGroupBean, newStudyGroups, studyGroupSpeciesList);
        updateProtocolStudyGroupSpeciesUsage(iacucProtocol, newStudyGroups);
    }
    
    
    /**
     * This method is to get a list of new protocol study groups.
     * @param protocol
     * @param protocolSpeciesAndGroups
     * @param iacucProtocolStudyGroupBean
     * @return a new set of protocol study groups based on selected group and species
     */
    private List<IacucProtocolStudyGroup> getNewProtocolStudyGroups(List<String> protocolSpeciesAndGroups, 
            IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for(String iacucProtocolSpeciesId : protocolSpeciesAndGroups) {
            IacucProtocolStudyGroup iacucProtocolStudyGroup = new IacucProtocolStudyGroup();
            iacucProtocolStudyGroup.setIacucProtocolSpeciesId(Integer.parseInt(iacucProtocolSpeciesId));
            iacucProtocolStudyGroup.setStudyGroupId(getNextStudyGroupId(protocol));
            protocolStudyGroups.add(iacucProtocolStudyGroup);
        }
        return protocolStudyGroups;
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
     * This method is to get a sorted list of current study groups.
     * @param protocol
     * @return
     */
    private List<IacucProtocolStudyGroup> getSortedStudyGroups(IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for(IacucProtocolStudyGroupBean studyGroupBean : protocol.getIacucProtocolStudyGroupBeans()) {
            for (IacucProtocolStudyGroup studyGroup : studyGroupBean.getIacucProtocolStudyGroups()) {
                protocolStudyGroups.add((IacucProtocolStudyGroup) ObjectUtils.deepCopy(studyGroup));
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
     * This method is to add procedure custom data to each new study group.
     * Custom data attributes are added based on attributes configured for each procedure code.
     * @param selectedIacucProtocolStudyGroupBean
     * @param newStudyGroups
     */
    private void addProcedureCustomData(IacucProtocolStudyGroupBean selectedIacucProtocolStudyGroupBean, 
            List<IacucProtocolStudyGroup> newStudyGroups, IacucProtocol protocol) {
        List<IacucProcedureCategoryCustomData> procedureCustomDataList = getIacucProcedureCustomData(selectedIacucProtocolStudyGroupBean.getProcedureCategoryCode());
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : newStudyGroups) {
            List<IacucProtocolStudyCustomData> protocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>();
            for(IacucProcedureCategoryCustomData procedureCategoryCustomData : procedureCustomDataList) {
                if(procedureCategoryCustomData.isActive()) {
                    IacucProtocolStudyCustomData newIacucProtocolStudyCustomData = new IacucProtocolStudyCustomData();
                    newIacucProtocolStudyCustomData.setProcedureCustomAttributeId(procedureCategoryCustomData.getId());
                    newIacucProtocolStudyCustomData.setIacucProcedureCategoryCustomData(procedureCategoryCustomData);
                    updateAttributesForNewProcedureCustomData(newIacucProtocolStudyCustomData, protocol, iacucProtocolStudyGroup);
                    protocolStudyCustomDataList.add(newIacucProtocolStudyCustomData);
                }
            }
            iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList().addAll(protocolStudyCustomDataList);
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
        newIacucProtocolStudyCustomData.setProtocolId(protocol.getProtocolId());
        newIacucProtocolStudyCustomData.setProtocolNumber(protocol.getProtocolNumber());
        newIacucProtocolStudyCustomData.setSequenceNumber(protocol.getSequenceNumber());
    }
    
    /**
     * This method is to set attribute values for new study group bean (header)
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
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#deleteProtocolStudyGroup(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupBean, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProtocolStudyGroup deletedIacucProtocolStudyGroup, IacucProtocol iacucProtocol) {
        IacucProtocolStudyGroupSpecies updatedStudyGroupSpecies = getIacucProtocolStudyGroupSpeciesForStudyGroup(deletedIacucProtocolStudyGroup, iacucProtocol);
        updatedStudyGroupSpecies.setUsageCount(updatedStudyGroupSpecies.getUsageCount()-1);
        if(updatedStudyGroupSpecies.getUsageCount() == 0) {
            iacucProtocol.getIacucProtocolStudyGroupSpeciesList().remove(updatedStudyGroupSpecies);
        }
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().remove(deletedIacucProtocolStudyGroup);
    }
    
    /**
     * This method is to get iacuc protocol study group species based on given study group
     * This is required to keep track of species usage in the study group.
     * @param iacucProtocolStudyGroup
     * @param iacucProtocol
     * @return
     */
    private IacucProtocolStudyGroupSpecies getIacucProtocolStudyGroupSpeciesForStudyGroup(IacucProtocolStudyGroup iacucProtocolStudyGroup, IacucProtocol iacucProtocol) {
        IacucProtocolStudyGroupSpecies updatedStudyGroupSpecies = null;
        for(IacucProtocolStudyGroupSpecies iacucProtocolStudyGroupSpecies : iacucProtocol.getIacucProtocolStudyGroupSpeciesList()) {
            if(iacucProtocolStudyGroupSpecies.getSpeciesCode().equals(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode())) {
                updatedStudyGroupSpecies = iacucProtocolStudyGroupSpecies;
            }
        }
        return updatedStudyGroupSpecies;
    }

    /**
     * This method is to revise and update the study group species (tracked by species) 
     * list based on protocol species and groups.
     * Extract only species from group and species information
     * New species information is update to the collection
     * @param protocol
     * @param newStudyGroups
     * @return
     */
    private HashMap<Integer, IacucProtocolStudyGroupSpecies> getUpdatedProtocolStudyGroupSpeciesList(IacucProtocol protocol, List<IacucProtocolStudyGroup> newStudyGroups) {
        HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList = getProtocolStudyGroupSpeciesList(protocol);
        for(IacucProtocolStudyGroup protocolStudyGroup : newStudyGroups) {
            Integer speciesCode = protocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode();
            IacucProtocolStudyGroupSpecies studyGroupSpecies = studyGroupSpeciesList.get(speciesCode);
            if(ObjectUtils.isNull(studyGroupSpecies)) {
                studyGroupSpecies = getNewProtocolStudyGroupSpecies(protocol, protocolStudyGroup.getIacucProtocolSpecies());
                studyGroupSpeciesList.put(speciesCode, studyGroupSpecies);
                protocol.getIacucProtocolStudyGroupSpeciesList().add(studyGroupSpecies);
            }
        }
        return studyGroupSpeciesList;
    }
    
    /**
     * This method is to update the usage count of species based on group species in protocol study.
     * @param protocol
     * @param newStudyGroups
     */
    private void updateProtocolStudyGroupSpeciesUsage(IacucProtocol protocol, List<IacucProtocolStudyGroup> newStudyGroups) {
        for(IacucProtocolStudyGroup iacucProtocolStudyGroup : newStudyGroups) {
            for(IacucProtocolStudyGroupSpecies iacucProtocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
                if(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode().equals(iacucProtocolStudyGroupSpecies.getSpeciesCode())) {
                    iacucProtocolStudyGroupSpecies.setUsageCount(iacucProtocolStudyGroupSpecies.getUsageCount() + 1);
                    break;
                }
            }
        }
    }
            
    
    /**
     * This method is to add new procedure and corresponding details for each protocol person
     * @param protocol
     */
    private void addNewResponsibleProceduresForAllProtocolPersons(IacucProtocol protocol, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean,
            List<IacucProtocolStudyGroup> newStudyGroups, HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList) {
        List<ProtocolPersonBase> protocolPersons = protocol.getProtocolPersons();
        for(ProtocolPersonBase protocolPerson : protocolPersons) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson;
            addPersonResponsibleProcedures(iacucProtocolPerson, protocol, selectedProtocolStudyGroupBean, newStudyGroups, studyGroupSpeciesList);
        }
    }

    /**
     * This method is to add study group details for a protocol person
     * Person procedure details are recorded based on species.
     * We need to get distinct species from protocol species and set procedure
     * details for each person 
     * @param protocolPerson
     * @param protocol
     * @param selectedProtocolStudyGroupBean
     * @param selectedStudyGroups
     */
    private void addPersonResponsibleProcedures(IacucProtocolPerson protocolPerson, IacucProtocol protocol, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, List<IacucProtocolStudyGroup> selectedStudyGroups,
            HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList) {
        HashMap<Integer, IacucProcedurePersonResponsible> personStudyProcedures = getPersonProcedureStudy(protocolPerson);
        for(IacucProtocolStudyGroup protocolStudyGroup : selectedStudyGroups) {
            Integer speciesCode = protocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode();
            IacucProtocolStudyGroupSpecies studyGroupSpecies = studyGroupSpeciesList.get(speciesCode);
            IacucProcedurePersonResponsible newResposibleProcedure = personStudyProcedures.get(studyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
            if(ObjectUtils.isNull(newResposibleProcedure)) {
                newResposibleProcedure = getNewPersonResponsibleProcedure(protocolPerson, studyGroupSpecies, protocol);
                protocolPerson.getProcedureDetails().add(newResposibleProcedure);
                personStudyProcedures.put(studyGroupSpecies.getIacucProtocolStudyGroupSpeciesId(), newResposibleProcedure);
            }
            addPersonResponsibleProcedureDetails(selectedProtocolStudyGroupBean, newResposibleProcedure);
        }
    }
    
    /**
     * This method is to get all procedure study (species and linked procedures)
     * handled by a given person
     * Map it with the distinct species code to identify whether details exists for that species
     * @param iacucProtocolPerson
     * @return
     */
    private HashMap<Integer, IacucProcedurePersonResponsible> getPersonProcedureStudy(IacucProtocolPerson iacucProtocolPerson) {
        List<IacucProcedurePersonResponsible> personProcedures = iacucProtocolPerson.getProcedureDetails();
        HashMap<Integer, IacucProcedurePersonResponsible> personStudyProcedures = new HashMap<Integer, IacucProcedurePersonResponsible>();
        for(IacucProcedurePersonResponsible procedureResponsible : personProcedures) {
            Integer iacucProtocolStudyGroupSpeciesId = procedureResponsible.getIacucProtocolStudyGroupSpeciesId();
            personStudyProcedures.put(iacucProtocolStudyGroupSpeciesId, procedureResponsible);
        }
        return personStudyProcedures;
    }
    
    /**
     * This method is to get a new person responsible procedure information
     * Person responsibility for a procedure is recorded based on species
     * @param protocolPerson
     * @param studyGroupSpecies
     * @param protocol
     * @return
     */
    private IacucProcedurePersonResponsible getNewPersonResponsibleProcedure(IacucProtocolPerson protocolPerson, 
            IacucProtocolStudyGroupSpecies studyGroupSpecies, IacucProtocol protocol) {
        IacucProcedurePersonResponsible resposibleProcedure = new IacucProcedurePersonResponsible();
        resposibleProcedure.setProtocolPersonId(protocolPerson.getProtocolPersonId());
        resposibleProcedure.setIacucProtocolStudyGroupSpeciesId(studyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
        resposibleProcedure.setIacucProtocolStudyGroupSpecies(studyGroupSpecies);
        resposibleProcedure.setProtocolNumber(protocol.getProtocolNumber());
        resposibleProcedure.setSequenceNumber(protocol.getSequenceNumber());
        return resposibleProcedure;
    }
    
    /**
     * This method is to add procedure details for recorded species.
     * It has all procedures performed on a species.
     * @param selectedProtocolStudyGroupBean
     * @param newResposibleProcedure
     */
    private void addPersonResponsibleProcedureDetails(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean,
            IacucProcedurePersonResponsible newResposibleProcedure) {
        boolean isProcedureExists = isPersonResponsibleProcedureExists(selectedProtocolStudyGroupBean, newResposibleProcedure);
        if(!isProcedureExists) {
            IacucPersonProcedureDetail resposibleProcedureDetail = new IacucPersonProcedureDetail();
            resposibleProcedureDetail.setIacucProtocolStudyGroupHeaderId(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
            resposibleProcedureDetail.setIacucProtocolStudyGroupBean(selectedProtocolStudyGroupBean);
            newResposibleProcedure.getResponsibleProcedures().add(resposibleProcedureDetail);
        }
    }
    
    /**
     * This method is to check whether new procedure already exists.
     * Procedures are linked to species
     * @param selectedProtocolStudyGroupBean
     * @param newResposibleProcedure
     * @return
     */
    private boolean isPersonResponsibleProcedureExists(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProcedurePersonResponsible newResposibleProcedure) {
        boolean isProcedureExists = false;
        if(!newResposibleProcedure.getResponsibleProcedures().isEmpty()) {
            for(IacucPersonProcedureDetail iacucPersonProcedureDetail : newResposibleProcedure.getResponsibleProcedures()) {
                if(iacucPersonProcedureDetail.getIacucProtocolStudyGroupHeaderId().equals(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                    isProcedureExists = true;
                    break;
                }
            }
        }
        return isProcedureExists;
    }
    
    /**
     * This method is to check whether new procedure already exists.
     * Procedures are linked to species
     * @param selectedProtocolStudyGroupBean
     * @param newResposibleProcedure
     * @return
     */
    private boolean isLocationResponsibleProcedureExists(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, 
            IacucProcedureLocationDetail resposibleProcedure) {
        boolean isProcedureExists = false;
        if(!resposibleProcedure.getResponsibleProcedures().isEmpty()) {
            for(IacucProtocolLocationProcedure iacucProtocolLocationProcedure : resposibleProcedure.getResponsibleProcedures()) {
                if(iacucProtocolLocationProcedure.getIacucProtocolStudyGroupHeaderId().equals(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                    isProcedureExists = true;
                    break;
                }
            }
        }
        return isProcedureExists;
    }
    
    /**
     * This method is to get a new protocol study group species
     * @param protocol
     * @param iacucProtocolSpecies
     * @return
     */
    private IacucProtocolStudyGroupSpecies getNewProtocolStudyGroupSpecies(IacucProtocol protocol, IacucProtocolSpecies iacucProtocolSpecies) {
        IacucProtocolStudyGroupSpecies newProtocolStudyGroupSpecies = new IacucProtocolStudyGroupSpecies();
        newProtocolStudyGroupSpecies.setIacucProtocolStudyGroupSpeciesId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_LOCATION_SEQUENCE_ID));
        newProtocolStudyGroupSpecies.setSpeciesCode(iacucProtocolSpecies.getSpeciesCode());
        newProtocolStudyGroupSpecies.setUsageCount(0);
        newProtocolStudyGroupSpecies.setProtocolId(protocol.getProtocolId());
        newProtocolStudyGroupSpecies.setProtocolNumber(protocol.getProtocolNumber());
        newProtocolStudyGroupSpecies.setSequenceNumber(protocol.getSequenceNumber());
        return newProtocolStudyGroupSpecies;
    }
    
    /**
     * This method is to get a list of distinct species handled in this study
     * @param protocol
     * @param iacucProtocolSpecies
     * @return
     */
    private HashMap<Integer, IacucProtocolStudyGroupSpecies> getProtocolStudyGroupSpeciesList(IacucProtocol protocol) {
        HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpecies = new HashMap<Integer, IacucProtocolStudyGroupSpecies>();
        for(IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            Integer speciesCode = protocolStudyGroupSpecies.getSpeciesCode();
            studyGroupSpecies.put(speciesCode, protocolStudyGroupSpecies);
        }
        return studyGroupSpecies;
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProcedureLocation(org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupLocation, org.kuali.kra.iacuc.IacucProtocol)
     */
    public void addProcedureLocation(IacucProtocolStudyGroupLocation newStudyGroupLocation, IacucProtocol protocol) {
        updateAttributesForNewProcedureLocation(newStudyGroupLocation, protocol);
        HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList = getProtocolStudyGroupSpeciesList(protocol);
        HashMap<Integer, IacucProcedureLocationDetail> speciesAndProcedureLocations = new HashMap<Integer, IacucProcedureLocationDetail>();
        addLocationProcedureDetails(speciesAndProcedureLocations, protocol, newStudyGroupLocation, studyGroupSpeciesList);
        protocol.getIacucProtocolStudyGroupLocations().add(newStudyGroupLocation);
    }
    
    /**
     * This method is to add procedure details to a specific location.
     * We need to maintain distinct species linked to multiple procedures
     * @param speciesAndProcedureLocations
     * @param protocol
     * @param newStudyGroupLocation
     */
    private void addLocationProcedureDetails(HashMap<Integer, IacucProcedureLocationDetail> speciesAndProcedureLocations, IacucProtocol protocol, 
            IacucProtocolStudyGroupLocation studyGroupLocation, HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList) {
        List<IacucProtocolStudyGroupBean> studyGroupBeans = protocol.getIacucProtocolStudyGroupBeans();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : studyGroupBeans) {
            for(IacucProtocolStudyGroup protocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                Integer speciesCode = protocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode();
                IacucProtocolStudyGroupSpecies studyGroupSpecies = studyGroupSpeciesList.get(speciesCode);
                IacucProcedureLocationDetail resposibleProcedure = speciesAndProcedureLocations.get(studyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
                if(ObjectUtils.isNull(resposibleProcedure)) {
                    resposibleProcedure = new IacucProcedureLocationDetail();
                    resposibleProcedure.setIacucProtocolStudyGroupSpeciesId(studyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
                    resposibleProcedure.setIacucProtocolStudyGroupSpecies(studyGroupSpecies);
                    speciesAndProcedureLocations.put(studyGroupSpecies.getIacucProtocolStudyGroupSpeciesId(), resposibleProcedure);
                    studyGroupLocation.getProcedureDetails().add(resposibleProcedure);
                }
                addLocationProcedures(iacucProtocolStudyGroupBean, resposibleProcedure);
            }
        }
    }
    
    /**
     * This method is to add procedures handled at a specific location
     * @param iacucProtocolStudyGroupBean
     * @param resposibleProcedure
     */
    private void addLocationProcedures(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean, IacucProcedureLocationDetail resposibleProcedure) {
        boolean isProcedureExists = isLocationResponsibleProcedureExists(iacucProtocolStudyGroupBean, resposibleProcedure);
        if(!isProcedureExists) {
            IacucProtocolLocationProcedure locationProcedure = new IacucProtocolLocationProcedure();
            locationProcedure.setIacucProtocolStudyGroupHeaderId(iacucProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
            locationProcedure.setIacucProtocolStudyGroupBean(iacucProtocolStudyGroupBean);
            resposibleProcedure.getResponsibleProcedures().add(locationProcedure);
        }
    }
 

    /**
     * This method is to set study group reference
     * @param newIacucProtocolStudyGroupLocation
     * @param protocolStudyGroup
     * @param protocol
     */
    private void updateAttributesForNewProcedureLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation, 
            IacucProtocol protocol) { 
        newIacucProtocolStudyGroupLocation.setStudyGroupLocationId(getNextStudyGroupLocationId(protocol));
        newIacucProtocolStudyGroupLocation.setIacucProtocolStudyGroupLocationId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_LOCATION_SEQUENCE_ID));
        newIacucProtocolStudyGroupLocation.setProtocolId(protocol.getProtocolId());
        newIacucProtocolStudyGroupLocation.setProtocolNumber(protocol.getProtocolNumber());
        newIacucProtocolStudyGroupLocation.setSequenceNumber(protocol.getSequenceNumber());
    }
 
    /**
     * This method is to get the next study group location id.
     * It is just a serial number generated based on the list of study group locations
     * @param iacucProtocol
     * @return
     */
    private Integer getNextStudyGroupLocationId(IacucProtocol iacucProtocol) {
        int totalStudyGroupLocs = iacucProtocol.getIacucProtocolStudyGroupLocations().size();
        Integer nextStudyGroupLocationId = totalStudyGroupLocs + 1;
        return nextStudyGroupLocationId;
    }
 
    /**
     * This method is to add new procedure and corresponding details for each protocol person
     * @param protocol
     */
    private void addNewResponsibleProceduresForAllProcedureLocations(IacucProtocol protocol, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean,
            List<IacucProtocolStudyGroup> newStudyGroups, HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList) {
        List<IacucProtocolStudyGroupLocation> procedureLocations = protocol.getIacucProtocolStudyGroupLocations();
        for(IacucProtocolStudyGroupLocation procedureLocation : procedureLocations) {
            HashMap<Integer, IacucProcedureLocationDetail> speciesAndProcedureLocations = getLocationProcedureStudy(procedureLocation);
            addLocationProcedureDetails(speciesAndProcedureLocations, protocol, procedureLocation, studyGroupSpeciesList);
        }
    }
 
    /**
     * This method is to get all procedure study (species and linked procedures) 
     * handled at a specific location
     * @param procedureLocation
     * @return
     */
    private HashMap<Integer, IacucProcedureLocationDetail> getLocationProcedureStudy(IacucProtocolStudyGroupLocation procedureLocation) {
        List<IacucProcedureLocationDetail> locationProcedures = procedureLocation.getProcedureDetails();
        HashMap<Integer, IacucProcedureLocationDetail> locationStudyProcedures = new HashMap<Integer, IacucProcedureLocationDetail>();
        for(IacucProcedureLocationDetail procedureResponsible : locationProcedures) {
            Integer iacucProtocolStudyGroupSpeciesId = procedureResponsible.getIacucProtocolStudyGroupSpeciesId();
            locationStudyProcedures.put(iacucProtocolStudyGroupSpeciesId, procedureResponsible);
        }
        return locationStudyProcedures;
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#setProcedureSummaryGroupedBySpecies(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void setProcedureSummaryGroupedBySpecies(IacucProtocol protocol) {
        for(IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            protocolStudyGroupSpecies.setProtocolStudyProcedures(new ArrayList<IacucProtocolStudyGroupBean>());
            Integer speciesCode = protocolStudyGroupSpecies.getSpeciesCode();
            for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
                for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                    if(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode().equals(speciesCode)) {
                        protocolStudyGroupSpecies.getProtocolStudyProcedures().add(protocolStudyGroupBean);
                    }
                }
            }
        }
        for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            protocolStudyGroupBean.setIacucProtocolStudyGroupPersons(getAllStudyGroupPersonsForProcedure(protocol, protocolStudyGroupBean));
            protocolStudyGroupBean.setIacucProtocolStudyGroupLocations(getAllStudyGroupLocationsForProcedure(protocol, protocolStudyGroupBean));
            protocolStudyGroupBean.setIacucProtocolStudyCustomDataList(getAllStudyGroupCustomDataForProcedure(protocol, protocolStudyGroupBean));
        }
    }
   
    /**
     * This method is to get person study group details based on a procedure
     * @param protocol
     * @param protocolStudyGroupBean
     * @return
     */
    private List<IacucProcedurePersonResponsible> getAllStudyGroupPersonsForProcedure(IacucProtocol protocol, IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        List<IacucProcedurePersonResponsible> iacucProtocolStudyGroupPersons = new ArrayList<IacucProcedurePersonResponsible>(); 
        for(ProtocolPersonBase protocolPersonBase : protocol.getProtocolPersons()) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPersonBase;
            for(IacucProcedurePersonResponsible personResponsibleProcedure : iacucProtocolPerson.getProcedureDetails()) {
                for(IacucPersonProcedureDetail responsibleProcedure : personResponsibleProcedure.getResponsibleProcedures()) {
                    if(responsibleProcedure.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId()) &&
                            responsibleProcedure.isStudyProcedureActive()) {
                        iacucProtocolStudyGroupPersons.add(personResponsibleProcedure);
                        break;
                    }
                }
                
            }
        }
        return iacucProtocolStudyGroupPersons;
    }

    /**
     * This method is to get location study group based on a procedure
     * @param protocol
     * @param protocolStudyGroupBean
     * @return
     */
    private List<IacucProtocolStudyGroupLocation> getAllStudyGroupLocationsForProcedure(IacucProtocol protocol, IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        List<IacucProtocolStudyGroupLocation> iacucProtocolStudyGroupLocations = new ArrayList<IacucProtocolStudyGroupLocation>(); 
        for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : protocol.getIacucProtocolStudyGroupLocations()) {
            for(IacucProcedureLocationDetail locationResponsibleProcedure : iacucProtocolStudyGroupLocation.getProcedureDetails()) {
                for(IacucProtocolLocationProcedure responsibleProcedure : locationResponsibleProcedure.getResponsibleProcedures()) {
                    if(responsibleProcedure.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId()) &&
                            responsibleProcedure.isStudyProcedureActive()) {
                        iacucProtocolStudyGroupLocations.add(iacucProtocolStudyGroupLocation);
                        break;
                    }
                }
                
            }
        }
        return iacucProtocolStudyGroupLocations;
    }

    /**
     * This method is to get custom data study group based on a procedure
     * @param protocol
     * @param protocolStudyGroupBean
     * @return
     */
    private List<IacucProtocolStudyCustomData> getAllStudyGroupCustomDataForProcedure(IacucProtocol protocol, IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>(); 
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                    iacucProtocolStudyCustomDataList.addAll(iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList());
                }
            }
        }
        return iacucProtocolStudyCustomDataList;
    }
    
}