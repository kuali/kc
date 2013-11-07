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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String PROTOCOL_STUDY_GROUP_LOCATION_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_GRP_LOC_ID";
    private static final String PROTOCOL_STUDY_GROUP_HEADER_SEQUENCE_ID = "SEQ_IACUC_PROT_STUD_GRP_HDR_ID";
    private static final String PROTOCOL_STUDY_GROUP_SPECIES_SEQUENCE_ID = "SEQ_PROTO_STUDY_GRP_SPC_ID";
     
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
    
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#getIacucPersonTrainingDetails(java.lang.String)
     */
    public List<IacucPersonTraining> getIacucPersonTrainingDetails(String personId) {
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
            iacucProtocolStudyGroup.setCount(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCount());
            iacucProtocolStudyGroup.setPainCategoryCode(iacucProtocolStudyGroup.getIacucProtocolSpecies().getPainCategoryCode());
            iacucProtocolStudyGroup.setIacucPainCategory(iacucProtocolStudyGroup.getIacucPainCategory());
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
            deletePersonResponsibleProcedures(updatedStudyGroupSpecies, iacucProtocol);
            deleteLocationResponsibleProcedures(updatedStudyGroupSpecies, iacucProtocol);
            iacucProtocol.getIacucProtocolStudyGroupSpeciesList().remove(updatedStudyGroupSpecies);
        }
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().remove(deletedIacucProtocolStudyGroup);
        if(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroups().size() == 0) {
            deletePersonProcedures(iacucProtocol, selectedProtocolStudyGroupBean);
            deleteLocationProcedures(iacucProtocol, selectedProtocolStudyGroupBean);
            iacucProtocol.getIacucProtocolStudyGroups().remove(selectedProtocolStudyGroupBean);
        }
    }
    
    /**
     * This method is to removed related person procedure details
     * @param iacucProtocol
     * @param selectedProtocolStudyGroupBean
     */
    private void deletePersonProcedures(IacucProtocol iacucProtocol, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        for(ProtocolPersonBase protocolPerson : iacucProtocol.getProtocolPersons()) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson;
            for(IacucProcedurePersonResponsible procedureDetail : iacucProtocolPerson.getProcedureDetails()) {
                List<IacucPersonProcedureDetail> deletedPersonProcedures = new ArrayList<IacucPersonProcedureDetail>();
                for(IacucPersonProcedureDetail responsbleProcedure : procedureDetail.getResponsibleProcedures()) {
                    if(responsbleProcedure.getIacucProtocolStudyGroupHeaderId().equals(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                        deletedPersonProcedures.add(responsbleProcedure);
                    }
                }
                procedureDetail.getResponsibleProcedures().removeAll(deletedPersonProcedures);
            }
        }
    }

    
    /**
     * This method is to removed related location procedure details
     * @param iacucProtocol
     * @param selectedProtocolStudyGroupBean
     */
    private void deleteLocationProcedures(IacucProtocol iacucProtocol, IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        for(IacucProtocolStudyGroupLocation protocolStudyGroupLocation : iacucProtocol.getIacucProtocolStudyGroupLocations()) {
            for(IacucProcedureLocationDetail procedureDetail : protocolStudyGroupLocation.getProcedureDetails()) {
                List<IacucProtocolLocationProcedure> deletedLocationProcedures = new ArrayList<IacucProtocolLocationProcedure>();;
                for(IacucProtocolLocationProcedure responsbleProcedure : procedureDetail.getResponsibleProcedures()) {
                    if(responsbleProcedure.getIacucProtocolStudyGroupHeaderId().equals(selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                        deletedLocationProcedures.add(responsbleProcedure);
                    }
                }
                procedureDetail.getResponsibleProcedures().removeAll(deletedLocationProcedures);
            }
        }
    }
    
    /**
     * This method is to remove all species related person study group details
     * @param protocolStudyGroupSpecies
     * @param iacucProtocol
     */
    private void deletePersonResponsibleProcedures(IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies, IacucProtocol iacucProtocol) {
        for(ProtocolPersonBase protocolPerson : iacucProtocol.getProtocolPersons()) {
            List<IacucProcedurePersonResponsible> deletedProcedureDetails = new ArrayList<IacucProcedurePersonResponsible>();
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPerson;
            for(IacucProcedurePersonResponsible procedureDetail : iacucProtocolPerson.getProcedureDetails()) {
                if(procedureDetail.getIacucProtocolStudyGroupSpeciesId().equals(protocolStudyGroupSpecies.getIacucProtocolStudyGroupSpeciesId())) {
                    deletedProcedureDetails.add(procedureDetail);
                }
            }
            iacucProtocolPerson.getProcedureDetails().removeAll(deletedProcedureDetails);
        }
    }

    /**
     * This method is to remove all species related location study group details
     * @param protocolStudyGroupSpecies
     * @param iacucProtocol
     */
    private void deleteLocationResponsibleProcedures(IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies, IacucProtocol iacucProtocol) {
        for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : iacucProtocol.getIacucProtocolStudyGroupLocations()) {
            List<IacucProcedureLocationDetail> deletedProcedureDetails = new ArrayList<IacucProcedureLocationDetail>();
            for(IacucProcedureLocationDetail locationDetail : iacucProtocolStudyGroupLocation.getProcedureDetails()) {
                if(locationDetail.getIacucProtocolStudyGroupSpeciesId().equals(protocolStudyGroupSpecies.getIacucProtocolStudyGroupSpeciesId())) {
                    deletedProcedureDetails.add(locationDetail);
                }
            }
            iacucProtocolStudyGroupLocation.getProcedureDetails().removeAll(deletedProcedureDetails);
        }
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
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addPersonResponsibleProcedures(org.kuali.kra.iacuc.IacucProtocol, org.kuali.kra.iacuc.personnel.IacucProtocolPerson)
     */
    public void addPersonResponsibleProcedures(IacucProtocol protocol, IacucProtocolPerson protocolPerson) {
        HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList = getProtocolStudyGroupSpeciesList(protocol);
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            addPersonResponsibleProcedures(protocolPerson, protocol, iacucProtocolStudyGroupBean, iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups(), 
                    studyGroupSpeciesList);
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
        resposibleProcedure.setIacucProtocolStudyGroupSpeciesId(studyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
        resposibleProcedure.setIacucProtocolStudyGroupSpecies(studyGroupSpecies);
        setAttributesForPersonResponsibleProcedure(resposibleProcedure, protocolPerson, protocol);
        return resposibleProcedure;
    }
    
    /**
     * This method is to set protocol and person attributes for a new person responsible procedure
     * @param resposibleProcedure
     * @param protocolPerson
     * @param protocol
     */
    private void setAttributesForPersonResponsibleProcedure(IacucProcedurePersonResponsible resposibleProcedure, IacucProtocolPerson protocolPerson, IacucProtocol protocol) {
        resposibleProcedure.setProtocolPersonId(protocolPerson.getProtocolPersonId());
        resposibleProcedure.setProtocolNumber(protocol.getProtocolNumber());
        resposibleProcedure.setSequenceNumber(protocol.getSequenceNumber());
        
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
        return getNewProtocolStudyGroupSpecies(protocol, iacucProtocolSpecies.getSpeciesCode());
    }

    /**
     * This method is to get a new protocol study group species based on species code
     * @param protocol
     * @param speciesCode
     * @return
     */
    private IacucProtocolStudyGroupSpecies getNewProtocolStudyGroupSpecies(IacucProtocol protocol, Integer speciesCode) {
        IacucProtocolStudyGroupSpecies newProtocolStudyGroupSpecies = new IacucProtocolStudyGroupSpecies();
        newProtocolStudyGroupSpecies.setIacucProtocolStudyGroupSpeciesId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_SPECIES_SEQUENCE_ID));
        newProtocolStudyGroupSpecies.setSpeciesCode(speciesCode);
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
        addStudyGroupProceduresForSpecies(protocol);
        addStudyGroupProcedureDetailsForSpecies(protocol);
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#setProcedureSummaryBySpeciesGroup(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void setProcedureSummaryBySpeciesGroup(IacucProtocol protocol) {
        addProceduresForSpeciesGroups(protocol);
        addProcedureDetailsForSpeciesGroups(protocol);
    }
    
    /**
     * This method is to identify study group details for species group used in the study
     * This grouping is used for summary display (studies grouped by species group)
     * @param protocol
     */
    private void addProceduresForSpeciesGroups(IacucProtocol protocol) {
        for(IacucProtocolSpecies iacucProtocolSpecies : protocol.getIacucProtocolSpeciesList()) {
            iacucProtocolSpecies.setProtocolStudyProcedures(new ArrayList<IacucProtocolStudyGroupBean>());
            Integer speciesCode = iacucProtocolSpecies.getSpeciesCode();
            iacucProtocolSpecies.getProtocolStudyProcedures().addAll(getStudyGroupProceduresForSpecies(protocol, speciesCode));
        }
    }
    
    /**
     * This method is to identify study group details for species used in the study
     * This grouping is used for summary display
     * @param protocol
     */
    private void addStudyGroupProceduresForSpecies(IacucProtocol protocol) {
        for(IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            protocolStudyGroupSpecies.setProtocolStudyProcedures(new ArrayList<IacucProtocolStudyGroupBean>());
            Integer speciesCode = protocolStudyGroupSpecies.getSpeciesCode();
            protocolStudyGroupSpecies.getProtocolStudyProcedures().addAll(getStudyGroupProceduresForSpecies(protocol, speciesCode));
            protocolStudyGroupSpecies.refreshReferenceObject("iacucPersonResponsibleProcedures");
            protocolStudyGroupSpecies.refreshReferenceObject("iacucLocationResponsibleProcedures");
        }
    }
    
    /**
     * This method is to collect study details based on species code
     * @param protocol
     * @param speciesCode
     * @return
     */
    private List<IacucProtocolStudyGroupBean> getStudyGroupProceduresForSpecies(IacucProtocol protocol, Integer speciesCode) {
        List<IacucProtocolStudyGroupBean> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroupBean>();
        for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode().equals(speciesCode)) {
                    iacucProtocolStudyGroups.add(iacucProtocolStudyGroup);
                }
            }
            if(!iacucProtocolStudyGroups.isEmpty()) {
                IacucProtocolStudyGroupBean newProtocolStudyGroupBean = getNewProtocolStudyGroupBean(protocolStudyGroupBean);
                newProtocolStudyGroupBean.getIacucProtocolStudyGroups().addAll(iacucProtocolStudyGroups);
                protocolStudyGroups.add(newProtocolStudyGroupBean);
            }
        }
        return protocolStudyGroups;
    }
    
    /**
     * This method is to get a new copy of an existing study group header bean
     * @param protocolStudyGroupBean
     * @return
     */
    private IacucProtocolStudyGroupBean getNewProtocolStudyGroupBean(IacucProtocolStudyGroupBean protocolStudyGroupBean) {
        IacucProtocolStudyGroupBean newProtocolStudyGroupBean = new IacucProtocolStudyGroupBean();
        newProtocolStudyGroupBean.setIacucProtocolStudyGroupHeaderId(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
        newProtocolStudyGroupBean.setIacucProcedureCategory(protocolStudyGroupBean.getIacucProcedureCategory());
        newProtocolStudyGroupBean.setIacucProcedure(protocolStudyGroupBean.getIacucProcedure());
        return newProtocolStudyGroupBean;
    }
    
    /**
     * This method is to add related procedure details for each species
     * This includes person responsibilities, location and custom data.
     * This grouping is used for summary display
     * @param protocol
     */
    private void addStudyGroupProcedureDetailsForSpecies(IacucProtocol protocol) {
        for(IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies : protocol.getIacucProtocolStudyGroupSpeciesList()) {
            Integer totalSpeciesCount = 0;
            for(IacucProtocolStudyGroupBean protocolStudyGroupBean : protocolStudyGroupSpecies.getProtocolStudyProcedures()) {
                protocolStudyGroupBean.setIacucProtocolStudyGroupPersons(getAllStudyGroupPersonsForProcedure(protocol, protocolStudyGroupBean, protocolStudyGroupSpecies));
                protocolStudyGroupBean.setIacucProtocolStudyGroupLocations(getAllStudyGroupLocationsForProcedure(protocol, protocolStudyGroupBean, protocolStudyGroupSpecies));
                protocolStudyGroupBean.setIacucProtocolStudyCustomDataList(getAllStudyGroupCustomDataForProcedure(protocol, protocolStudyGroupBean, protocolStudyGroupSpecies));
                Integer totalProcSpeciesCount = 0;
                for(IacucProtocolStudyGroup studyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                    if(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId().equals(studyGroup.getIacucProtocolStudyGroupHeaderId()) &&
                            studyGroup.getIacucProtocolSpecies().getSpeciesCode().equals(protocolStudyGroupSpecies.getSpeciesCode())) {
                        totalProcSpeciesCount = totalProcSpeciesCount + studyGroup.getCount();
                    }
                }
                protocolStudyGroupBean.setSpeciesCount(totalProcSpeciesCount);
                totalSpeciesCount = totalSpeciesCount + totalProcSpeciesCount;
            }
            protocolStudyGroupSpecies.setTotalSpeciesCount(totalSpeciesCount);
        }
    }
    
    /**
     * This method is to add related procedure details for each species group
     * This includes person responsibilities, location and custom data.
     * This grouping is used for summary display
     * @param protocol
     */
    private void addProcedureDetailsForSpeciesGroups(IacucProtocol protocol) {
        HashMap<Integer, IacucProtocolStudyGroupSpecies> studyGroupSpeciesList = getProtocolStudyGroupSpeciesList(protocol);
        for(IacucProtocolSpecies iacucProtocolSpecies : protocol.getIacucProtocolSpeciesList()) {
            IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies = studyGroupSpeciesList.get(iacucProtocolSpecies.getSpeciesCode());
            if(ObjectUtils.isNotNull(protocolStudyGroupSpecies)) {
                protocolStudyGroupSpecies.refreshReferenceObject("iacucPersonResponsibleProcedures");
                protocolStudyGroupSpecies.refreshReferenceObject("iacucLocationResponsibleProcedures");
                Integer totalSpeciesCount = 0;
                for(IacucProtocolStudyGroupBean protocolStudyGroupBean : iacucProtocolSpecies.getProtocolStudyProcedures()) {
                    protocolStudyGroupBean.setIacucProtocolStudyGroupPersons(getAllStudyGroupPersonsForProcedure(protocol, protocolStudyGroupBean, protocolStudyGroupSpecies));
                    protocolStudyGroupBean.setIacucProtocolStudyGroupLocations(getAllStudyGroupLocationsForProcedure(protocol, protocolStudyGroupBean, protocolStudyGroupSpecies));
                    protocolStudyGroupBean.setIacucProtocolStudyCustomDataList(getAllStudyGroupCustomDataForSpeciesGroup(protocol, protocolStudyGroupBean, iacucProtocolSpecies));
                    Integer totalProcSpeciesCount = 0;
                    for(IacucProtocolStudyGroup studyGroup : protocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                        if(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId().equals(studyGroup.getIacucProtocolStudyGroupHeaderId()) &&
                                studyGroup.getIacucProtocolSpeciesId().equals(iacucProtocolSpecies.getIacucProtocolSpeciesId())) {
                            totalProcSpeciesCount = totalProcSpeciesCount + studyGroup.getCount();
                        }
                    }
                    protocolStudyGroupBean.setSpeciesCount(totalProcSpeciesCount);
                    totalSpeciesCount = totalSpeciesCount + totalProcSpeciesCount;
                }
                iacucProtocolSpecies.setTotalSpeciesCount(totalSpeciesCount);
            }
        }
    }
    
    /**
     * This method is to get person study group details based on a procedure
     * @param protocol
     * @param protocolStudyGroupBean
     * @return
     */
    private List<IacucProcedurePersonResponsible> getAllStudyGroupPersonsForProcedure(IacucProtocol protocol, IacucProtocolStudyGroupBean protocolStudyGroupBean, 
            IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies) {
        List<IacucProcedurePersonResponsible> iacucProtocolStudyGroupPersons = new ArrayList<IacucProcedurePersonResponsible>(); 
        for(IacucProcedurePersonResponsible personResponsibleProcedure : protocolStudyGroupSpecies.getIacucPersonResponsibleProcedures()) {
            for(IacucPersonProcedureDetail responsibleProcedure : personResponsibleProcedure.getResponsibleProcedures()) {
                if(responsibleProcedure.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId()) &&
                        responsibleProcedure.isStudyProcedureActive()) {
                    iacucProtocolStudyGroupPersons.add(personResponsibleProcedure);
                    break;
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
    private List<IacucProcedureLocationDetail> getAllStudyGroupLocationsForProcedure(IacucProtocol protocol, IacucProtocolStudyGroupBean protocolStudyGroupBean, 
            IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies) {
        List<IacucProcedureLocationDetail> iacucProtocolStudyGroupLocations = new ArrayList<IacucProcedureLocationDetail>(); 
        for(IacucProcedureLocationDetail locationResponsibleProcedure : protocolStudyGroupSpecies.getIacucLocationResponsibleProcedures()) {
            if(locationResponsibleProcedure.getIacucProtocolStudyGroupSpecies().getSpeciesCode().equals(protocolStudyGroupSpecies.getSpeciesCode())) {
                for(IacucProtocolLocationProcedure responsibleProcedure : locationResponsibleProcedure.getResponsibleProcedures()) {
                    if(responsibleProcedure.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId()) &&
                            responsibleProcedure.isStudyProcedureActive()) {
                        iacucProtocolStudyGroupLocations.add(locationResponsibleProcedure);
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
    private List<IacucProtocolStudyCustomData> getAllStudyGroupCustomDataForProcedure(IacucProtocol protocol, IacucProtocolStudyGroupBean protocolStudyGroupBean, 
            IacucProtocolStudyGroupSpecies protocolStudyGroupSpecies) {
        List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>(); 
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolSpecies().getSpeciesCode().equals(protocolStudyGroupSpecies.getSpeciesCode()) &&
                        iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                    iacucProtocolStudyCustomDataList.addAll(iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList());
                }
            }
        }
        return iacucProtocolStudyCustomDataList;
    }
    
    /**
     * This method is to get custom data details for give species group
     * @param protocol
     * @param protocolStudyGroupBean
     * @param protocolSpecies
     * @return
     */
    private List<IacucProtocolStudyCustomData> getAllStudyGroupCustomDataForSpeciesGroup(IacucProtocol protocol, IacucProtocolStudyGroupBean protocolStudyGroupBean, 
            IacucProtocolSpecies protocolSpecies) {
        List<IacucProtocolStudyCustomData> iacucProtocolStudyCustomDataList = new ArrayList<IacucProtocolStudyCustomData>(); 
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : protocol.getIacucProtocolStudyGroups()) {
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : iacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                if(iacucProtocolStudyGroup.getIacucProtocolSpecies().getIacucProtocolSpeciesId().equals(protocolSpecies.getIacucProtocolSpeciesId()) &&
                        iacucProtocolStudyGroup.getIacucProtocolStudyGroupHeaderId().equals(protocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId())) {
                    iacucProtocolStudyCustomDataList.addAll(iacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList());
                }
            }
        }
        return iacucProtocolStudyCustomDataList;
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#createNewProtocolStudyProcedures(org.kuali.kra.iacuc.IacucProtocol, org.kuali.kra.iacuc.IacucProtocol)
     */
    @SuppressWarnings("unchecked")
    public void createNewProtocolStudyProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        createNewStudyProcedures(sourceProtocol, destProtocol);
        createNewStudyGroupSpecies(sourceProtocol, destProtocol);
        HashMap<Integer, IacucProtocolStudyGroupBean> newProceduresMapping = getIacucProtocolStudyGroupProcedureMapping(destProtocol.getIacucProtocolStudyGroups()); 
        HashMap<Integer, IacucProtocolStudyGroupSpecies> newStudyGroupSpeciesMapping = getIacucProtocolStudyGroupSpeciesMapping(destProtocol.getIacucProtocolStudyGroupSpeciesList());
        createNewLocationProcedures(sourceProtocol, destProtocol, newProceduresMapping, newStudyGroupSpeciesMapping);
        createNewPersonProcedures(sourceProtocol, destProtocol, newProceduresMapping, newStudyGroupSpeciesMapping);
    }
    
    /**
     * This method is to create a new set of study details in procedures tab.
     * invoked during copy protocol
     * @param sourceProtocol
     * @param destProtocol
     */
    private void createNewStudyProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        destProtocol.setIacucProtocolStudyGroups(new ArrayList<IacucProtocolStudyGroupBean>());
        HashMap<Integer, IacucProtocolSpecies> newIacucProtocolSpeciesMapping = getIacucProtocolSpeciesMapping(destProtocol.getIacucProtocolSpeciesList());
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : sourceProtocol.getIacucProtocolStudyGroups()) {
            IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean = (IacucProtocolStudyGroupBean)deepCopy(iacucProtocolStudyGroupBean);
            newIacucProtocolStudyGroupBean.resetPersistenceState();
            setAttributesForNewStudyGroupBean(newIacucProtocolStudyGroupBean, destProtocol);
            for(IacucProtocolStudyGroup newIacucProtocolStudyGroup : newIacucProtocolStudyGroupBean.getIacucProtocolStudyGroups()) {
                newIacucProtocolStudyGroup.resetPersistenceState();
                newIacucProtocolStudyGroup.setIacucProtocolStudyGroupHeaderId(newIacucProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
                newIacucProtocolStudyGroup.setIacucProtocolStudyGroupBean(newIacucProtocolStudyGroupBean);
                IacucProtocolSpecies destIacucProtocolSpecies = newIacucProtocolSpeciesMapping.get(newIacucProtocolStudyGroup.getIacucProtocolSpeciesId());
                if(ObjectUtils.isNotNull(destIacucProtocolSpecies)) {
                    newIacucProtocolStudyGroup.setIacucProtocolSpeciesId(destIacucProtocolSpecies.getIacucProtocolSpeciesId());
                    newIacucProtocolStudyGroup.setIacucProtocolSpecies(destIacucProtocolSpecies);
                }
                for(IacucProtocolStudyCustomData newIacucProtocolStudyCustomData : newIacucProtocolStudyGroup.getIacucProtocolStudyCustomDataList()) {
                    newIacucProtocolStudyCustomData.resetPersistenceState();
                    setAttributesForNewProcedureStudyCustomData(newIacucProtocolStudyCustomData, destProtocol);
                    newIacucProtocolStudyCustomData.setIacucProtocolStudyGroupId(newIacucProtocolStudyGroup.getIacucProtocolStudyGroupId());
                }
            }
            destProtocol.getIacucProtocolStudyGroups().add(newIacucProtocolStudyGroupBean);
        }
    }
    
    /**
     * This method is to create a new set of study group species list.
     * invoked during copy protocol
     * @param sourceProtocol
     * @param destProtocol
     */
    private void createNewStudyGroupSpecies(IacucProtocol sourceProtocol, IacucProtocol destProtocol) {
        destProtocol.setIacucProtocolStudyGroupSpeciesList(new ArrayList<IacucProtocolStudyGroupSpecies>());
        for(IacucProtocolStudyGroupSpecies iacucProtocolStudyGroupSpecies : sourceProtocol.getIacucProtocolStudyGroupSpeciesList()) {
            IacucProtocolStudyGroupSpecies newProtocolStudyGroupSpecies = getNewProtocolStudyGroupSpecies(destProtocol, iacucProtocolStudyGroupSpecies.getSpeciesCode());
            newProtocolStudyGroupSpecies.setOldProtocolStudyGroupSpeciesId(iacucProtocolStudyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
            newProtocolStudyGroupSpecies.setUsageCount(iacucProtocolStudyGroupSpecies.getUsageCount());
            destProtocol.getIacucProtocolStudyGroupSpeciesList().add(newProtocolStudyGroupSpecies);
        }
    }
    
    /**
     * This method is to create a new set of study group locations
     * we need to link old protocol species and study groups with new set of procedures
     * invoked during copy protocol
     * @param sourceProtocol
     * @param destProtocol
     * @param newProceduresMapping
     * @param newStudyGroupSpeciesMapping
     */
    private void createNewLocationProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol, 
            HashMap<Integer, IacucProtocolStudyGroupBean> newProceduresMapping, HashMap<Integer, IacucProtocolStudyGroupSpecies> newStudyGroupSpeciesMapping) {
        destProtocol.setIacucProtocolStudyGroupLocations(new ArrayList<IacucProtocolStudyGroupLocation>());
        for(IacucProtocolStudyGroupLocation iacucProtocolStudyGroupLocation : sourceProtocol.getIacucProtocolStudyGroupLocations()) {
            IacucProtocolStudyGroupLocation newProtocolStudyGroupLocation = (IacucProtocolStudyGroupLocation)deepCopy(iacucProtocolStudyGroupLocation);
            updateAttributesForNewProcedureLocation(newProtocolStudyGroupLocation, destProtocol);
            for(IacucProcedureLocationDetail newIacucProcedureLocationDetail : newProtocolStudyGroupLocation.getProcedureDetails()) {
                newIacucProcedureLocationDetail.resetPersistenceState();
                IacucProtocolStudyGroupSpecies newProtocolStudyGroupSpecies = newStudyGroupSpeciesMapping.get(newIacucProcedureLocationDetail.getIacucProtocolStudyGroupSpeciesId());
                newIacucProcedureLocationDetail.setIacucProtocolStudyGroupSpeciesId(newProtocolStudyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
                newIacucProcedureLocationDetail.setIacucProtocolStudyGroupSpecies(newProtocolStudyGroupSpecies);
                newIacucProcedureLocationDetail.setIacucProtocolStudyGroupLocation(newProtocolStudyGroupLocation);
                for(IacucProtocolLocationProcedure newProtocolLocationProcedure : newIacucProcedureLocationDetail.getResponsibleProcedures()) {
                    newProtocolLocationProcedure.resetPersistenceState();
                    IacucProtocolStudyGroupBean newProtocolStudyGroupBean = newProceduresMapping.get(newProtocolLocationProcedure.getIacucProtocolStudyGroupHeaderId());
                    newProtocolLocationProcedure.setIacucProtocolStudyGroupHeaderId(newProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
                    newProtocolLocationProcedure.setIacucProtocolStudyGroupBean(newProtocolStudyGroupBean);
                    newProtocolLocationProcedure.setIacucProcedureLocationDetailId(newIacucProcedureLocationDetail.getIacucProcedureLocationDetailId());
                    newProtocolLocationProcedure.setIacucProcedureLocationDetail(newIacucProcedureLocationDetail);
                }
            }
            destProtocol.getIacucProtocolStudyGroupLocations().add(newProtocolStudyGroupLocation);
        }
    }

    /**
     * This method is to update study group persons and responsible procedures
     * Protocol persons are created at the top level - at the base.
     * we need to link old protocol species and study groups with new set of procedures
     * invoked during copy protocol
     * @param sourceProtocol
     * @param destProtocol
     * @param newProceduresMapping
     * @param newStudyGroupSpeciesMapping
     */
    private void createNewPersonProcedures(IacucProtocol sourceProtocol, IacucProtocol destProtocol, 
            HashMap<Integer, IacucProtocolStudyGroupBean> newProceduresMapping, HashMap<Integer, IacucProtocolStudyGroupSpecies> newStudyGroupSpeciesMapping) {
        for(ProtocolPersonBase protocolPersonBase : destProtocol.getProtocolPersons()) {
            IacucProtocolPerson newIacucProtocolPerson = (IacucProtocolPerson)protocolPersonBase;
            for(IacucProcedurePersonResponsible newProcedurePersonResponsible : newIacucProtocolPerson.getProcedureDetails()) {
                newProcedurePersonResponsible.resetPersistenceState();
                IacucProtocolStudyGroupSpecies newProtocolStudyGroupSpecies = newStudyGroupSpeciesMapping.get(newProcedurePersonResponsible.getIacucProtocolStudyGroupSpeciesId());
                newProcedurePersonResponsible.setProtocolId(destProtocol.getProtocolId());
                newProcedurePersonResponsible.setProtocolNumber(destProtocol.getProtocolNumber());
                newProcedurePersonResponsible.setSequenceNumber(destProtocol.getSequenceNumber());
                newProcedurePersonResponsible.setIacucProtocolStudyGroupSpeciesId(newProtocolStudyGroupSpecies.getIacucProtocolStudyGroupSpeciesId());
                newProcedurePersonResponsible.setIacucProtocolStudyGroupSpecies(newProtocolStudyGroupSpecies);
                newProcedurePersonResponsible.setProtocolPersonId(newIacucProtocolPerson.getProtocolPersonId());
                newProcedurePersonResponsible.setProtocolPerson(newIacucProtocolPerson);
                for(IacucPersonProcedureDetail newPersonProcedureDetail : newProcedurePersonResponsible.getResponsibleProcedures()) {
                    newPersonProcedureDetail.resetPersistenceState();
                    IacucProtocolStudyGroupBean newProtocolStudyGroupBean = newProceduresMapping.get(newPersonProcedureDetail.getIacucProtocolStudyGroupHeaderId());
                    newPersonProcedureDetail.setIacucProtocolStudyGroupHeaderId(newProtocolStudyGroupBean.getIacucProtocolStudyGroupHeaderId());
                    newPersonProcedureDetail.setIacucProtocolStudyGroupBean(newProtocolStudyGroupBean);
                    newPersonProcedureDetail.setIacucProcedurePersonResponsibleId(newProcedurePersonResponsible.getIacucProcedurePersonResponsibleId());
                    newPersonProcedureDetail.setIacucProcedurePersonResponsible(newProcedurePersonResponsible);
                }
            }
        }
    }
    
    protected Object deepCopy(Object obj) {
        if (obj instanceof Serializable) {
            return ObjectUtils.deepCopy((Serializable) obj);
        }
        return obj;
    }

    /**
     * This method is to get a map of protocol persons
     * @param protocolPersons
     * @return
     */
    private HashMap<Integer, IacucProtocolPerson> getIacucProtocolPersonMapping(List<ProtocolPersonBase> protocolPersons) {
        HashMap<Integer, IacucProtocolPerson> iacucProtocolPersons = new HashMap<Integer, IacucProtocolPerson>();
        for(ProtocolPersonBase protocolPersonBase : protocolPersons) {
            IacucProtocolPerson iacucProtocolPerson = (IacucProtocolPerson)protocolPersonBase;
            iacucProtocolPersons.put(iacucProtocolPerson.getProtocolPersonId(), iacucProtocolPerson);
        }
        return iacucProtocolPersons;
    }
    
    /**
     * This method is to get a map of list of protocol species
     * Map old protocol species to new protocol species during copy
     * @param iacucProtocolSpeciesList
     * @return
     */
    private HashMap<Integer, IacucProtocolSpecies> getIacucProtocolSpeciesMapping(List<IacucProtocolSpecies> iacucProtocolSpeciesList) {
        HashMap<Integer, IacucProtocolSpecies> protocolSpeciesList = new HashMap<Integer, IacucProtocolSpecies>();
        for(IacucProtocolSpecies iacucProtocolSpecies : iacucProtocolSpeciesList) {
            protocolSpeciesList.put(iacucProtocolSpecies.getOldProtocolSpeciesId(), iacucProtocolSpecies);
        }
        return protocolSpeciesList;
    }
 
    /**
     * This method is to get a map of protocol study group species
     * These are the distinct species list from iacuc protocol species
     * @param iacucProtocolStudyGroupSpeciesList
     * @return
     */
    private HashMap<Integer, IacucProtocolStudyGroupSpecies> getIacucProtocolStudyGroupSpeciesMapping(List<IacucProtocolStudyGroupSpecies> iacucProtocolStudyGroupSpeciesList) {
        HashMap<Integer, IacucProtocolStudyGroupSpecies> protocolStudyGroupSpeciesList = new HashMap<Integer, IacucProtocolStudyGroupSpecies>();
        for(IacucProtocolStudyGroupSpecies iacucProtocolStudyGroupSpecies : iacucProtocolStudyGroupSpeciesList) {
            protocolStudyGroupSpeciesList.put(iacucProtocolStudyGroupSpecies.getOldProtocolStudyGroupSpeciesId(), iacucProtocolStudyGroupSpecies);
        }
        return protocolStudyGroupSpeciesList;
    }
    
    /**
     * This method is to get a map of protocol study group procedures (header)
     * @param iacucProtocolStudyGroupProcedures
     * @return
     */
    private HashMap<Integer, IacucProtocolStudyGroupBean> getIacucProtocolStudyGroupProcedureMapping(List<IacucProtocolStudyGroupBean> iacucProtocolStudyGroupProcedures) {
        HashMap<Integer, IacucProtocolStudyGroupBean> protocolStudyGroupProcedures = new HashMap<Integer, IacucProtocolStudyGroupBean>();
        for(IacucProtocolStudyGroupBean iacucProtocolStudyGroupBean : iacucProtocolStudyGroupProcedures) {
            protocolStudyGroupProcedures.put(iacucProtocolStudyGroupBean.getOldProtocolStudyGroupHeaderId(), iacucProtocolStudyGroupBean);
        }
        return protocolStudyGroupProcedures;
    }
    
    
    /**
     * This method is to set protocol reference details for new study group custom data.
     * @param iacucProtocolStudyCustomData
     * @param protocol
     */
    private void setAttributesForNewProcedureStudyCustomData(IacucProtocolStudyCustomData iacucProtocolStudyCustomData, IacucProtocol protocol) {
        iacucProtocolStudyCustomData.setProtocolId(protocol.getProtocolId());
        iacucProtocolStudyCustomData.setProtocolNumber(protocol.getProtocolNumber());
        iacucProtocolStudyCustomData.setSequenceNumber(protocol.getSequenceNumber());
    }
    
}