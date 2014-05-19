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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.custom.ProposalDevelopmentCustomDataHelper;
import org.kuali.coeus.propdev.impl.question.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.specialreview.SpecialReviewHelper;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelAddWizardHelper;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.ToggleMenu;
import org.kuali.rice.krad.web.bind.ChangeTracking;
import org.kuali.rice.krad.web.bind.RequestAccessible;
import org.kuali.rice.krad.web.form.TransactionalDocumentFormBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ChangeTracking
public class ProposalDevelopmentDocumentForm extends TransactionalDocumentFormBase {

    private static final long serialVersionUID = 1381360399393420225L;
    
    private SpecialReviewHelper specialReviewHelper;
    private ProposalDevelopmentQuestionnaireHelper questionnaireHelper;
    private KeyPersonnelAddWizardHelper addKeyPersonHelper;
    @RequestAccessible
    protected S2sOpportunity newS2sOpportunity;
    private Map<String,List<String>> editableAttachments;
    private ProposalDevelopmentCustomDataHelper customDataHelper;
    private String selectedCustomDataGroup;

    public ProposalDevelopmentDocumentForm() {
        super();
    }

    public void initialize() {
        specialReviewHelper = new SpecialReviewHelper(getProposalDevelopmentDocument(), true);
        specialReviewHelper.prepareView();
        
        questionnaireHelper = new ProposalDevelopmentQuestionnaireHelper(getProposalDevelopmentDocument());
        
        addKeyPersonHelper = new KeyPersonnelAddWizardHelper();
        
        newS2sOpportunity = new S2sOpportunity();

        editableAttachments = new HashMap<String,List<String>>();

        customDataHelper = new ProposalDevelopmentCustomDataHelper(this.getProposalDevelopmentDocument());
    }

    public int findIndexOfPageId(List<Action> actions) {
        return findIndexOfPageId(actions, getPageId());
    }
    
    public int findIndexOfPageId(List<Action> actions, String pageId) {
        for (int i = 0, len = actions.size(); i < len; i++) {
            Action action = actions.get(i);
            if (StringUtils.equals(pageId, action.getNavigateToPageId())) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean canGoBack() {
        return findIndexOfPageId(getOrderedNavigationActions()) > 0;
    }
    
    public boolean canGoForward() {
        List<Action> actions = getOrderedNavigationActions();
        return findIndexOfPageId(actions) < actions.size();
    }
    
    public List<Action> getOrderedNavigationActions() {
        List<Action> actions = new ArrayList<Action>();
        addAllActions(actions, view.getNavigation().getItems());
        return actions;
    }
    
    protected void addAllActions(List<Action> actionList, List<? extends Component> components) {
        for (Component component : components) {
            if (component instanceof ToggleMenu) {
                addAllActions(actionList, ((ToggleMenu) component).getMenuItems());
            } else if (component instanceof Action) {
                actionList.add((Action) component);
            }
        }
    }    
    
    @Override
    protected String getDefaultDocumentTypeName() {
        return "ProposalDevelopmentDocument";
    }
    
    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) getDocument();
    }
    
    public DevelopmentProposal getDevelopmentProposal() {
        return getProposalDevelopmentDocument().getDevelopmentProposal();
    }

    public SpecialReviewHelper getSpecialReviewHelper() {
        return specialReviewHelper;
    }

    public void setSpecialReviewHelper(SpecialReviewHelper specialReviewHelper) {
        this.specialReviewHelper = specialReviewHelper;
    }

    public ProposalDevelopmentQuestionnaireHelper getQuestionnaireHelper() {
        return questionnaireHelper;
    }

    public void setQuestionnaireHelper(ProposalDevelopmentQuestionnaireHelper questionnaireHelper) {
        this.questionnaireHelper = questionnaireHelper;
    }

    public KeyPersonnelAddWizardHelper getAddKeyPersonHelper() {
        return addKeyPersonHelper;
    }

    public void setAddKeyPersonHelper(KeyPersonnelAddWizardHelper addKeyPersonHelper) {
        this.addKeyPersonHelper = addKeyPersonHelper;
    }

    public S2sOpportunity getNewS2sOpportunity() {
        return newS2sOpportunity;
    }

    public void setNewS2sOpportunity(S2sOpportunity newOpportunity) {
        this.newS2sOpportunity = newOpportunity;
    }

    public Map<String, List<String>> getEditableAttachments() {
        return editableAttachments;
    }

    public void setEditableAttachments(Map<String, List<String>> editableAttachments) {
        this.editableAttachments = editableAttachments;
    }

    public ProposalDevelopmentCustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    public void setCustomDataHelper(ProposalDevelopmentCustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }

    public String getSelectedCustomDataGroup() {
        return selectedCustomDataGroup;
    }

    public void setSelectedCustomDataGroup(String selectedCustomDataGroup) {
        this.selectedCustomDataGroup = selectedCustomDataGroup;
    }
}
