/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IacucProtocolProceduresHelper implements Serializable{


    private static final long serialVersionUID = -2090976351003068814L;

    protected IacucProtocolForm form;
    
    protected IacucProtocolStudyGroup newIacucProtocolStudyGroup;
    protected IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean;
    protected IacucProcedurePersonResponsible newIacucProcedurePersonResponsible;
    private IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation;
    
    private List<IacucProcedure> allProcedures;
  
    protected int MAX_CATEGORY_COLUMNS = 3;
    
    private IacucProcedureNavigation currentProcedureDetailTab;

    protected IacucProtocolPerson selectedProtocolPerson;
    
    boolean summaryGroupedBySpecies = false;
    
    public IacucProtocolProceduresHelper(IacucProtocolForm form) {
        setForm(form); 
        setNewIacucProtocolStudyGroup(new IacucProtocolStudyGroup());
        setNewIacucProtocolStudyGroupBean(new IacucProtocolStudyGroupBean());
        setNewIacucProcedurePersonResponsible(new IacucProcedurePersonResponsible());
        setAllProcedures(new ArrayList<IacucProcedure>());
        initializeIncludedProceduresAndCategories();
        setCurrentProcedureDetailTab(IacucProcedureNavigation.PROCEDURES);
        setNewIacucProtocolStudyGroupLocation(new IacucProtocolStudyGroupLocation());
    }    
    
    
    public void prepareView() {
        initializeIncludedProceduresAndCategories();
    }
    
    private void initializeIncludedProceduresAndCategories() {
        if(getAllProcedures().isEmpty()) {
            setAllProcedures(getIacucProtocolProcedureService().getAllProcedures());
        }
        if(getProtocol().getIacucProtocolStudyGroupBeans().isEmpty()) {
            getProtocol().setIacucProtocolStudyGroupBeans(getIacucProtocolProcedureService().getRevisedStudyGroupBeans(getProtocol(), getAllProcedures()));
        }
    }

    protected IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return (IacucProtocolProcedureService) KcServiceLocator.getService("iacucProtocolProcedureService");
    }

    protected IacucProtocol getProtocol() {
        IacucProtocolDocument document = form.getIacucProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) Iacuc ProtocolDocument in ProtocolForm");
        }
        return document.getIacucProtocol();
    }
    
    public IacucProtocolForm getForm() {
        return form;
    }

    public void setForm(IacucProtocolForm form) {
        this.form = form;
    }

    public boolean isModifyProtocolProcedures() {
        final ProtocolTaskBase task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_PROCEDURES, (IacucProtocol) form.getProtocolDocument().getProtocol());
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }

    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }

    public IacucProtocolStudyGroup getNewIacucProtocolStudyGroup() {
        return newIacucProtocolStudyGroup;
    }

    public void setNewIacucProtocolStudyGroup(IacucProtocolStudyGroup newIacucProtocolStudyGroup) {
        this.newIacucProtocolStudyGroup = newIacucProtocolStudyGroup;
    }


    public List<IacucProcedure> getAllProcedures() {
        return allProcedures;
    }

    public void setAllProcedures(List<IacucProcedure> allProcedures) {
        this.allProcedures = allProcedures;
    }


    public int getMaxCategoriesInAColumn() {
        return ((int) Math.ceil(getAllProcedures().size() / MAX_CATEGORY_COLUMNS)); 
    }


    public IacucProtocolStudyGroupBean getNewIacucProtocolStudyGroupBean() {
        return newIacucProtocolStudyGroupBean;
    }


    public void setNewIacucProtocolStudyGroupBean(IacucProtocolStudyGroupBean newIacucProtocolStudyGroupBean) {
        this.newIacucProtocolStudyGroupBean = newIacucProtocolStudyGroupBean;
    }


    public IacucProcedurePersonResponsible getNewIacucProcedurePersonResponsible() {
        return newIacucProcedurePersonResponsible;
    }


    public void setNewIacucProcedurePersonResponsible(IacucProcedurePersonResponsible newIacucProcedurePersonResponsible) {
        this.newIacucProcedurePersonResponsible = newIacucProcedurePersonResponsible;
    }

    public IacucProcedureNavigation[] getProcedureNavigationTabs() {
        return IacucProcedureNavigation.values();
    }

    public IacucProcedureNavigation getCurrentProcedureDetailTab() {
        return currentProcedureDetailTab;
    }

    public void setCurrentProcedureDetailTab(IacucProcedureNavigation currentProcedureDetailTab) {
        this.currentProcedureDetailTab = currentProcedureDetailTab;
    }

    public IacucProtocolPerson getSelectedProtocolPerson() {
        return selectedProtocolPerson;
    }

    public void setSelectedProtocolPerson(IacucProtocolPerson selectedProtocolPerson) {
        this.selectedProtocolPerson = selectedProtocolPerson;
    }


    public IacucProtocolStudyGroupLocation getNewIacucProtocolStudyGroupLocation() {
        return newIacucProtocolStudyGroupLocation;
    }


    public void setNewIacucProtocolStudyGroupLocation(IacucProtocolStudyGroupLocation newIacucProtocolStudyGroupLocation) {
        this.newIacucProtocolStudyGroupLocation = newIacucProtocolStudyGroupLocation;
    }


    public boolean isSummaryGroupedBySpecies() {
        return summaryGroupedBySpecies;
    }


    public void setSummaryGroupedBySpecies(boolean summaryGroupedBySpecies) {
        this.summaryGroupedBySpecies = summaryGroupedBySpecies;
    }

    public boolean isProcedureViewedBySpecies() {
        return getIacucProtocolProcedureService().isProcedureViewedBySpecies();
    }
}
