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
package org.kuali.kra.coi.disclosure;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterDisclosureBean implements Serializable {


    private static final long serialVersionUID = 8636120108965638825L;
    private CoiDisclosure coiDisclosure;
    private List<CoiDisclosureProjectBean> awardProjects;
    private List<CoiDisclosureProjectBean> proposalProjects;
    private List<CoiDisclosureProjectBean> protocolProjects;
    private List<CoiDisclosureProjectBean> iacucProtocolProjects;
    private List<CoiDisclosureProjectBean> manualAwardProjects;
    private List<CoiDisclosureProjectBean> manualProposalProjects;
    private List<CoiDisclosureProjectBean> manualProtocolProjects;
    private List<CoiDisclosureProjectBean> manualIacucProtocolProjects;
    private List<CoiDisclosureProjectBean> manualTravelProjects;
    private List<CoiDisclosureProjectBean> allProjects;
    private List<CoiDisclosureProjectBean> otherManualProjects;
    private List<AnswerHeader> answerHeaders;

    private List<CoiDisclosureProjectBean> allDisclosureProjects;
    private List<CoiGroupedMasterDisclosureBean> allDisclosuresGroupedByProjects;
    private boolean disclosureGroupedByEvent;

    
    public MasterDisclosureBean() {
        awardProjects = new ArrayList<CoiDisclosureProjectBean>();
        proposalProjects = new ArrayList<CoiDisclosureProjectBean>();
        protocolProjects = new ArrayList<CoiDisclosureProjectBean>();
        iacucProtocolProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualAwardProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualProposalProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualProtocolProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualIacucProtocolProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualTravelProjects = new ArrayList<CoiDisclosureProjectBean>();
        otherManualProjects = new ArrayList<CoiDisclosureProjectBean>();
        allProjects = new ArrayList<CoiDisclosureProjectBean>();
        
        setAllDisclosureProjects(new ArrayList<CoiDisclosureProjectBean>());
        setAllDisclosuresGroupedByProjects(new ArrayList<CoiGroupedMasterDisclosureBean>());
        setDisclosureGroupedByEvent(true);
    }
    
    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public List<CoiDisclosureProjectBean> getAwardProjects() {
        return awardProjects;
    }

    public void setAwardProjects(List<CoiDisclosureProjectBean> awardProjects) {
        this.awardProjects = awardProjects;
    }

    public List<CoiDisclosureProjectBean> getProposalProjects() {
        return proposalProjects;
    }

    public void setProposalProjects(List<CoiDisclosureProjectBean> proposalProjects) {
        this.proposalProjects = proposalProjects;
    }

    public List<CoiDisclosureProjectBean> getProtocolProjects() {
        return protocolProjects;
    }

    public void setProtocolProjects(List<CoiDisclosureProjectBean> protocolProjects) {
        this.protocolProjects = protocolProjects;
    }

    public List<CoiDisclosureProjectBean> getIacucProtocolProjects() {
        return iacucProtocolProjects;
    }

    public void setIacucProtocolProjects(List<CoiDisclosureProjectBean> protocolProjects) {
        this.iacucProtocolProjects = protocolProjects;
    }

    public List<CoiDisclosureProjectBean> getManualAwardProjects() {
        return manualAwardProjects;
    }

    public void setManualAwardProjects(List<CoiDisclosureProjectBean> manualAwardProjects) {
        this.manualAwardProjects = manualAwardProjects;
    }

    public List<CoiDisclosureProjectBean> getManualProposalProjects() {
        return manualProposalProjects;
    }

    public void setManualProposalProjects(List<CoiDisclosureProjectBean> manualProposalProjects) {
        this.manualProposalProjects = manualProposalProjects;
    }

    public List<CoiDisclosureProjectBean> getManualProtocolProjects() {
        return manualProtocolProjects;
    }

    public void setManualProtocolProjects(List<CoiDisclosureProjectBean> manualProtocolProjects) {
        this.manualProtocolProjects = manualProtocolProjects;
    }

    public List<CoiDisclosureProjectBean> getManualIacucProtocolProjects() {
        return manualIacucProtocolProjects;
    }

    public void setManualIacucProtocolProjects(List<CoiDisclosureProjectBean> manualIacucProtocolProjects) {
        this.manualIacucProtocolProjects = manualIacucProtocolProjects;
    }

    /*
     * Cannot use CoiDisclosureEventType.AWARD directly in switch because it is a string. 
     * Could use a enum with Strings but that would have to match the description field in the CoiDisclosureEventType
     * table but since it is not the PK, better to use the codes directly.
     */
    public void addProject(CoiDisclosureProjectBean coiDisclosureProjectBean, String projectTypeCode) {
        int typeCode = Integer.parseInt(projectTypeCode);
        switch (typeCode) {
            case 1 :
                getAwardProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.AWARD));
                break;
            case 2 :
                getProposalProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.DEVELOPMENT_PROPOSAL));
                break;
            case 3 :
                getProtocolProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.IRB_PROTOCOL));
                break;
            case 4 :
                getIacucProtocolProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.IACUC_PROTOCOL));
                break;
            case 10 :
                getProposalProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL));
                break;
            case 11 :
                getManualAwardProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.MANUAL_AWARD));
                break;
            case 12 :
                getManualProposalProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.MANUAL_DEVELOPMENT_PROPOSAL));
                break;
            case 13 :
                getManualProtocolProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.MANUAL_IRB_PROTOCOL));
                break;
            case 15 :
                getManualTravelProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.MANUAL_TRAVEL));
                break;
            case 16 :
                getManualIacucProtocolProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(CoiDisclosureEventType.MANUAL_IACUC_PROTOCOL));
                break;
            default:
                // create temp string to pass other values
                getOtherManualProjects().add(coiDisclosureProjectBean);
                coiDisclosureProjectBean.setExcludeFE(isEventExcludFE(""+typeCode));
        }
        getAllDisclosureProjects().add(coiDisclosureProjectBean);
    }
    
    /*
     * excluded FE from event.  this is specifically for annual project check or manual events
     */
    private boolean isEventExcludFE(String eventTypeCode) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("eventTypeCode", eventTypeCode);
        CoiDisclosureEventType coiDisclosureEventType =  KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CoiDisclosureEventType.class, fieldValues);
        return coiDisclosureEventType == null ? false : coiDisclosureEventType.isExcludeFinancialEntities();
    }

    public List<CoiDisclosureProjectBean> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(List<CoiDisclosureProjectBean> allProjects) {
        this.allProjects = allProjects;
    }
    
    public List<List<CoiDisclosureProjectBean>> getProjectLists() {
        List<List<CoiDisclosureProjectBean>> projects = new ArrayList<List<CoiDisclosureProjectBean>> ();
        projects.add(awardProjects);
        projects.add(proposalProjects);
        projects.add(protocolProjects);
        projects.add(iacucProtocolProjects);
        projects.add(manualAwardProjects);
        projects.add(manualProposalProjects);
        projects.add(manualProtocolProjects);
        projects.add(manualIacucProtocolProjects);
        projects.add(manualTravelProjects);
        projects.add(otherManualProjects);
        return projects;
    }

    public List<AnswerHeader> getAnswerHeaders() {
        return answerHeaders;
    }

    public void setAnswerHeaders(List<AnswerHeader> answerHeaders) {
        this.answerHeaders = answerHeaders;
    }
    
    public List<CoiDisclosureProjectBean> getManualTravelProjects() {
        return manualTravelProjects;
    }

    public void setManualTravelProjects(List<CoiDisclosureProjectBean> manualTravelProjects) {
        this.manualTravelProjects = manualTravelProjects;
    }

    public void setOtherManualProjects(List<CoiDisclosureProjectBean> otherManualProjects) {
        this.otherManualProjects = otherManualProjects;
    }

    public List<CoiDisclosureProjectBean> getOtherManualProjects() {
        return otherManualProjects;
    }

    public List<CoiGroupedMasterDisclosureBean> getAllDisclosuresGroupedByProjects() {
        return allDisclosuresGroupedByProjects;
    }

    public void setAllDisclosuresGroupedByProjects(List<CoiGroupedMasterDisclosureBean> allDisclosuresGroupedByProjects) {
        this.allDisclosuresGroupedByProjects = allDisclosuresGroupedByProjects;
    }

    public List<CoiDisclosureProjectBean> getAllDisclosureProjects() {
        return allDisclosureProjects;
    }

    public void setAllDisclosureProjects(List<CoiDisclosureProjectBean> allDisclosureProjects) {
        this.allDisclosureProjects = allDisclosureProjects;
    }

    public boolean isDisclosureGroupedByEvent() {
        return disclosureGroupedByEvent;
    }

    public void setDisclosureGroupedByEvent(boolean disclosureGroupedByEvent) {
        this.disclosureGroupedByEvent = disclosureGroupedByEvent;
    }

}
