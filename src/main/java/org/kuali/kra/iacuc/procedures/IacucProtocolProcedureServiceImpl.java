/*
 * Copyright 2005-2010 The Kuali Foundation
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
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.kuali.kra.bo.PersonTraining;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.IacucSpecies;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonTrainingService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.ObjectUtils;

public class IacucProtocolProcedureServiceImpl implements IacucProtocolProcedureService {

    private BusinessObjectService businessObjectService;
    private IacucProtocolPersonTrainingService iacucProtocolPersonTrainingService;
    private static final String PROTOCOL_STUDY_GROUP_SEQUENCE_ID = "SEQ_IACUC_PROTO_STUDY_GROUP_ID";
    private static final String PROTOCOL_PERSON_RESPONSIBLE_SEQUENCE_ID = "SEQ_IACUC_PROC_PERS_RESP_ID";
    
    
    private SequenceAccessorService sequenceAccessorService;

    public List<IacucProcedure> getAllProcedures() {
        return (List<IacucProcedure>)getBusinessObjectService().findAllOrderBy(IacucProcedure.class, "procedureCategoryCode", true);
    }

    public List<IacucProcedureCategory> getAllProcedureCategories() {
        return (List<IacucProcedureCategory>)getBusinessObjectService().findAllOrderBy(IacucProcedureCategory.class, "procedureCategoryCode", true);
    }
    
    public HashMap<Integer, List<IacucProcedure>> getProcedureCategoryAndRelatedProcedures() {
        HashMap<Integer, List<IacucProcedure>> categoryAndProcedures = new HashMap<Integer, List<IacucProcedure>> ();
        List<IacucProcedureCategory> allCategories = getAllProcedureCategories();
        List<IacucProcedure> allProcedures = getAllProcedures();
        for(IacucProcedureCategory procedureCategory : allCategories) {
            List<IacucProcedure> categoryProcedures = new ArrayList<IacucProcedure>();
            for(IacucProcedure procedure : allProcedures) {
                if(procedure.getProcedureCategoryCode().equals(procedureCategory.getProcedureCategoryCode())) {
                    categoryProcedures.add(procedure);
                }
            }
            categoryAndProcedures.put(procedureCategory.getProcedureCategoryCode(), categoryProcedures);
        }
        return categoryAndProcedures;
    }
    
    public List<IacucProtocolSpecies> getProtocolSpecies() {
        Long protocolId = ((IacucProtocolForm) KNSGlobalVariables.getKualiForm()).getIacucProtocolDocument().getProtocol().getProtocolId();
        Map<String, Object> keyMap = new HashMap<String, Object> ();
        keyMap.put("protocolId", protocolId);
        return (List<IacucProtocolSpecies>) getBusinessObjectService().findMatching(IacucProtocolSpecies.class, keyMap);
    }

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProtocolStudyGroup(org.kuali.kra.iacuc.IacucProtocolForm)
     */
    public void addProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol) {
        List<String> protocolSpeciesAndGroups =  selectedProtocolStudyGroupBean.getProtocolSpeciesAndGroups(); 
        List<String> protocolPersonsResponsible =  selectedProtocolStudyGroupBean.getProtocolPersonsResponsible(); 
        List<IacucProtocolStudyGroup> protocolStudyGroups = addStudyGroupBeans(protocolSpeciesAndGroups, selectedProtocolStudyGroupBean); 
        List<IacucProcedurePersonResponsible> procedurePersonsResponsible = addPersonResponsible(protocolPersonsResponsible, null);
        updateStudyGroupDetailBean(procedurePersonsResponsible,selectedProtocolStudyGroupBean);
        addIacucProtocolStudyGroup(selectedProtocolStudyGroupBean, iacucProtocol, protocolStudyGroups);
    }

    public void deleteProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocolStudyGroupDetailBean selectedProcedureDetailBean) {
        selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().remove(selectedProcedureDetailBean);
    }
        
    /**
     * This method is to add protocol study group bean and format display values.
     * Return a list of original iacuc protocol study group
     * @param protocol
     * @param protocolSpeciesAndGroups
     * @param iacucProtocolStudyGroupBean
     * @return
     */
    private List<IacucProtocolStudyGroup> addStudyGroupBeans(List<String> protocolSpeciesAndGroups, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        List<IacucProtocolStudyGroup> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = getProcedureStudyGroupedBySpecies(protocolSpeciesAndGroups);
        for (Map.Entry<Integer, List<IacucProtocolStudyGroup>> entry : studiesGroupedBySpecies.entrySet()) {
            Integer speciesCount = 0;
            TreeSet<String> painCategory = new TreeSet<String>();
            HashMap<String, Integer> painCategoryCodes = new HashMap<String, Integer>();
            //Integer speciesCode = entry.getKey();
            List<String> speciesAndGroups = new ArrayList<String>();
            List<IacucProtocolStudyGroup> studyGroups = entry.getValue();
            IacucProtocolStudyGroupDetailBean newIacucProtocolStudyGroupDetailBean = new IacucProtocolStudyGroupDetailBean();
            for(IacucProtocolStudyGroup iacucProtocolStudyGroup : studyGroups) {
                IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
                painCategory.add(protocolSpecies.getIacucPainCategory().getPainCategory());
                painCategoryCodes.put(protocolSpecies.getIacucPainCategory().getPainCategory(), protocolSpecies.getIacucPainCategory().getPainCategoryCode());
                speciesCount = speciesCount + protocolSpecies.getSpeciesCount();
                speciesAndGroups.add(protocolSpecies.getGroupAndSpecies());
                protocolStudyGroups.add(iacucProtocolStudyGroup);
                newIacucProtocolStudyGroupDetailBean.getIacucProtocolStudyGroups().add(iacucProtocolStudyGroup);
            }
            newIacucProtocolStudyGroupDetailBean.setMaxPainCategory(painCategory.last());
            newIacucProtocolStudyGroupDetailBean.setMaxPainCategoryCode(painCategoryCodes.get(painCategory.last()));
            newIacucProtocolStudyGroupDetailBean.setSpeciesAndGroupsText(speciesAndGroups);
            newIacucProtocolStudyGroupDetailBean.setTotalSpeciesCount(speciesCount);
            selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans().add(newIacucProtocolStudyGroupDetailBean);
        }
        return protocolStudyGroups;
    }
    
    /**
     * This method is to build studies grouped by species.
     * We have a list of protocol species and groups as defined in the protocol and we group them
     * by species.
     * @param protocolSpeciesAndGroups
     * @return
     */
    private HashMap<Integer, List<IacucProtocolStudyGroup>> getProcedureStudyGroupedBySpecies(List<String> protocolSpeciesAndGroups) {
        HashMap<Integer, List<IacucProtocolStudyGroup>> studiesGroupedBySpecies = new HashMap<Integer, List<IacucProtocolStudyGroup>>();
        List<IacucProtocolStudyGroup> studyGroups = null;
        for(String iacucProtocolSpeciesId : protocolSpeciesAndGroups) {
            IacucProtocolStudyGroup iacucProtocolStudyGroup = new IacucProtocolStudyGroup();
            iacucProtocolStudyGroup.setIacucProtocolSpeciesId(Integer.parseInt(iacucProtocolSpeciesId));
            iacucProtocolStudyGroup.refreshReferenceObject("iacucProtocolSpecies");
            IacucProtocolSpecies protocolSpecies = iacucProtocolStudyGroup.getIacucProtocolSpecies();
            protocolSpecies.refreshReferenceObject("iacucPainCategory");
            protocolSpecies.refreshReferenceObject("iacucSpecies");
            IacucSpecies iacucSpecies = protocolSpecies.getIacucSpecies();
            Integer speciesCode = iacucSpecies.getSpeciesCode();
            studyGroups = studiesGroupedBySpecies.get(speciesCode) == null ? new ArrayList<IacucProtocolStudyGroup>() : studiesGroupedBySpecies.get(speciesCode);
            studyGroups.add(iacucProtocolStudyGroup);
            studiesGroupedBySpecies.put(iacucSpecies.getSpeciesCode(), studyGroups);
        }
        return studiesGroupedBySpecies;
    }
    
    /**
     * This method is to add person responsible to each study group.
     * This method is invoked when user select group/species and responsible persons
     * @param protocol
     * @param protocolPersonsResponsible
     * @return
     */
    private List<IacucProcedurePersonResponsible> addPersonResponsible(List<String> protocolPersonsResponsible, 
            IacucProcedurePersonResponsible newIacucProcedurePersonResponsible) {
        List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible = new ArrayList<IacucProcedurePersonResponsible>();
        for(String personKey : protocolPersonsResponsible) {
            IacucProcedurePersonResponsible iacucProcedurePersonResponsible = new IacucProcedurePersonResponsible();
            String personId = null;
            String personName = null;
            StringTokenizer personInfo = new StringTokenizer(personKey, "|"); 
            while(personInfo.hasMoreTokens()) { 
                personId = personInfo.nextToken(); 
                personName = personInfo.nextToken(); 
            } 
            //personResponsible.add(personName);
            iacucProcedurePersonResponsible.setPersonId(personId);
            iacucProcedurePersonResponsible.setPersonName(personName);
            String description =  ObjectUtils.isNull(newIacucProcedurePersonResponsible) ? null : newIacucProcedurePersonResponsible.getPersonResponsibleDescription();
            iacucProcedurePersonResponsible.setPersonResponsibleDescription(description);
            iacucProcedurePersonResponsible.setTrainings(getPersonTrainingDetails(personId));
            iacucProcedurePersonsResponsible.add(iacucProcedurePersonResponsible);
        }
        return iacucProcedurePersonsResponsible;
    }
    
    protected List<PersonTraining> getPersonTrainingDetails(String personId) {
        return getIacucProtocolPersonTrainingService().getPersonTrainingDetails(personId);
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
    private void updateStudyGroupDetailBean(List<IacucProcedurePersonResponsible> iacucProcedurePersonsResponsible, 
            IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean) {
        for(IacucProtocolStudyGroupDetailBean detailBean : selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
            System.out.println("species text ============> " + detailBean.getSpeciesAndGroupsText());
            System.out.println("total study groups ============> " + detailBean.getIacucProtocolStudyGroups().size());
            detailBean.setNewIacucProcedurePersonResponsible(new IacucProcedurePersonResponsible());
            for(IacucProcedurePersonResponsible personResponsible : iacucProcedurePersonsResponsible) {
                IacucProcedurePersonResponsible newPersonResponsible = (IacucProcedurePersonResponsible)ObjectUtils.deepCopy(personResponsible);
                detailBean.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
            }
            
            for(IacucProtocolStudyGroup protocolStudyGroup : detailBean.getIacucProtocolStudyGroups()) {
                for(IacucProcedurePersonResponsible personResponsible : iacucProcedurePersonsResponsible) {
                    IacucProcedurePersonResponsible newPersonResponsible = (IacucProcedurePersonResponsible)ObjectUtils.deepCopy(personResponsible);
                    protocolStudyGroup.getIacucProcedurePersonsResponsible().add(newPersonResponsible);
                }
            }
        }
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

    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#addProcedurePersonResponsible(org.kuali.kra.iacuc.procedures.IacucProcedurePersonResponsible, org.kuali.kra.iacuc.procedures.IacucProtocolStudyGroupDetailBean)
     */
    public void addProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible, IacucProtocolStudyGroupDetailBean procedureDetailBean) {
        List<String> protocolPersonsResponsible =  newIacucProcedurePersonResponsible.getProtocolPersonsResponsible(); 
        List<IacucProcedurePersonResponsible> procedurePersonsResponsible = addPersonResponsible(protocolPersonsResponsible, newIacucProcedurePersonResponsible);
        procedureDetailBean.getIacucProcedurePersonsResponsible().addAll(procedurePersonsResponsible);
    }
    
    public List<IacucProtocolStudyGroupBean> getRevisedStudyGroupBeans(List<IacucProtocolStudyGroupBean> studyGroupBeans) {
        //List<IacucProtocolStudyGroupBean> studyGroupBeans = form.getIacucProtocolDocument().getIacucProtocol().getIacucProtocolStudyGroupBeans();
        if(studyGroupBeans.isEmpty()) {
            studyGroupBeans = getNewListOfStudyGroupBeans();
        }
       return studyGroupBeans;
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
     * This method is to add study group and it collection details when a new study group
     * is added. 
     * @param selectedProtocolStudyGroupBean
     * @param iacucProtocol
     * @param protocolStudyGroups
     */
    private void addIacucProtocolStudyGroup(IacucProtocolStudyGroupBean selectedProtocolStudyGroupBean, IacucProtocol iacucProtocol,
            List<IacucProtocolStudyGroup> protocolStudyGroups) {
        for(IacucProtocolStudyGroupDetailBean detailBean : selectedProtocolStudyGroupBean.getIacucProtocolStudyGroupDetailBeans()) {
            for(IacucProtocolStudyGroup studyGroup : detailBean.getIacucProtocolStudyGroups()) {
                updateProtocolStudyGroup(studyGroup, iacucProtocol, detailBean, 
                        selectedProtocolStudyGroupBean);
                for(IacucProcedurePersonResponsible personResponsible : studyGroup.getIacucProcedurePersonsResponsible()) {
                    personResponsible.setIacucProcedurePersonResponsibleId(getNextSequenceNumber(PROTOCOL_PERSON_RESPONSIBLE_SEQUENCE_ID));
                    personResponsible.setIacucProtocolStudyGroupId(studyGroup.getIacucProtocolStudyGroupId());
                    personResponsible.setProtocolNumber(studyGroup.getProtocolNumber());
                    personResponsible.setSequenceNumber(studyGroup.getSequenceNumber());
                    //studyGroup.getIacucProcedurePersonsResponsible().add(personResponsible);
                }
                iacucProtocol.getIacucProtocolStudyGroups().add(studyGroup);
            }
        }
    }
    
    
    private String getPersonResponsibleDescription(IacucProtocolStudyGroupDetailBean detailBean, String personId) {
        String responsibleDescription = null;
        for(IacucProcedurePersonResponsible personResponsible : detailBean.getIacucProcedurePersonsResponsible()) {
            if(personResponsible.getPersonId().equalsIgnoreCase(personId)) {
                responsibleDescription = personResponsible.getPersonResponsibleDescription();
                break;
            }
        }
        return responsibleDescription;
    }
    
    /**
     * @see org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService#updateIacucProtocolStudyGroup(org.kuali.kra.iacuc.IacucProtocol)
     */
    public void updateIacucProtocolStudyGroup(IacucProtocol protocol) {
        /*
        List<IacucProtocolStudyGroup> iacucProtocolStudyGroups = protocol.getIacucProtocolStudyGroups();
        for(IacucProtocolStudyGroup studyGroup : iacucProtocolStudyGroups) {
            for(IacucProcedurePersonResponsible personResponsible : studyGroup.getIacucProcedurePersonsResponsible()) {
                personResponsible.setPersonResponsibleDescription(getPersonResponsibleDescription(detailBean, personResponsible.getPersonId()));
            }
        }
        */
    }

    /**
     * This method is to update the study group information found in the group bean.
     * Also set the protocol references to record information in database.
     * @param studyGroup
     * @param protocol
     * @param studyGroupDetailBean
     * @param studyGroupBean
     */
    private void updateProtocolStudyGroup(IacucProtocolStudyGroup studyGroup, IacucProtocol protocol, 
            IacucProtocolStudyGroupDetailBean studyGroupDetailBean, IacucProtocolStudyGroupBean studyGroupBean) {
        studyGroup.setIacucProtocolStudyGroupId(getNextSequenceNumber(PROTOCOL_STUDY_GROUP_SEQUENCE_ID));
        studyGroup.setStudyGroupId(getNextStudyGroupId(protocol));
        //studyGroup.setProtocolId(protocolId)
        studyGroup.setProtocolNumber(protocol.getProtocolNumber());
        studyGroup.setSequenceNumber(protocol.getSequenceNumber());
        //studyGroup.setIacucProtocolSpeciesId(studyGroupDetailBean.get);
        studyGroup.setPainCategoryCode(studyGroupDetailBean.getMaxPainCategoryCode());
        studyGroup.setProcedureCategoryCode(studyGroupBean.getProcedureCategoryCode());
        studyGroup.setProcedureCode(studyGroupBean.getProcedureCode());
        studyGroup.setCount(studyGroupDetailBean.getTotalSpeciesCount());
    }

    /**
     * This method is to get the next study group id.
     * It is just a serial number generated based on the list of study groups
     * @param protocol
     * @return
     */
    private Integer getNextStudyGroupId(IacucProtocol protocol) {
        int totalStudyGroups = protocol.getIacucProtocolStudyGroups().size();
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
    public List<IacucProtocolStudyGroup> getSortedStudyGroups(IacucProtocol protocol) {
        List<IacucProtocolStudyGroup> protocolStudyGroups = new ArrayList<IacucProtocolStudyGroup>();
        for (IacucProtocolStudyGroup studyGroup : protocol.getIacucProtocolStudyGroups()) {
            protocolStudyGroups.add((IacucProtocolStudyGroup) ObjectUtils.deepCopy(studyGroup));
        }
        Collections.sort(protocolStudyGroups, new Comparator<IacucProtocolStudyGroup>() {
            public int compare(IacucProtocolStudyGroup group1, IacucProtocolStudyGroup group2) {
                return group1.getStudyGroupId().compareTo(group2.getStudyGroupId());
            }
        });
        return protocolStudyGroups;
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

}
