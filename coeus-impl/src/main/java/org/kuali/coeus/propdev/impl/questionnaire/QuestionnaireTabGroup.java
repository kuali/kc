package org.kuali.coeus.propdev.impl.questionnaire;


import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.CollectionGroupBase;
import org.kuali.rice.krad.uif.container.Group;
import org.kuali.rice.krad.uif.container.GroupBase;
import org.kuali.rice.krad.uif.container.TabGroup;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.Header;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycleRestriction;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.ComponentUtils;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.*;

public class QuestionnaireTabGroup extends TabGroup {

    private CollectionGroupBase collectionGroupPrototype;

    @Override
    public void performInitialization(Object model) {
        List<Component> tabs = new ArrayList<Component>();
        String formKey =  ((ProposalDevelopmentDocumentForm)model).getFormKey();
        tabs.addAll(createTabs(((ProposalDevelopmentDocumentForm)model).getQuestionnaireHelper().getAnswerHeaders(),"questionnaireHelper",formKey));
        tabs.addAll(createTabs(((ProposalDevelopmentDocumentForm)model).getS2sQuestionnaireHelper().getAnswerHeaders(),"s2sQuestionnaireHelper",formKey));

        Collections.sort(tabs, new Comparator<Component>(){
            public int compare(Component c1, Component c2) {
                return ((GroupBase)c1).getHeader().getHeaderText().compareTo(((GroupBase) c2).getHeader().getHeaderText());
            }
        });

        this.setItems(tabs);

        super.performInitialization(model);
    }

    private List<Component> createTabs(List<AnswerHeader> answerHeaders, String helper, String formKey) {
        List<Component> tabs = new ArrayList<Component>();
        int index = 0;
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.isActive()) {
                GroupBase group = (GroupBase) ComponentFactory.getNewComponentInstance("Uif-VerticalBoxGroup");
                group.setHeader((Header)ComponentFactory.getNewComponentInstance("Uif-SectionHeader"));
                String cssClass = "questionaire-" + (answerHeader.isCompleted()?"":"in") + "complete";
                group.setHeaderText(answerHeader.getLabel() + "&nbsp;<span class='" + cssClass + " icon-ok' />");
                group.getHeader().setRender(false);
                CollectionGroupBase questionCollection = ComponentUtils.copy(collectionGroupPrototype);
                initiateActionMenuItems(questionCollection.getHeader().getRightGroup(),index,helper,formKey,answerHeader.isNewerVersionPublished());
                questionCollection.setHeaderText(answerHeader.getLabel() + (answerHeader.isCompleted() ? "[color=green] (Complete)" : " [color=gray](Incomplete)") + "[/color]");
                questionCollection.setPropertyName(helper + ".answerHeaders[" + index + "].questions");
                group.setItems(Collections.singletonList(questionCollection));
                tabs.add(group);
            }
            index++;
        }

        return tabs;
    }

    protected void initiateActionMenuItems(Group group,int index, String helper, String formKey, boolean newerVersionPublished) {
        for (Component component : group.getItems()) {

            if (component instanceof Action){
                Action action = (Action) component;
                if (action.getActionLabel().equals("Print")) {
                    Properties parameters = new Properties();
                    parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "printQuestionnaire");
                    parameters.put(KRADConstants.FORM_KEY, formKey);
                    parameters.put("helper", helper);
                    parameters.put("index", String.valueOf(index));
                    action.getActionUrl().setHref(UrlFactory.parameterizeUrl("../kc-pd-krad/proposalDevelopment", parameters));
                } else if (action.getActionLabel().equals("Update Available")) {
                    action.setRender(newerVersionPublished);
                }
                action.addActionParameter("helper",helper);
                action.addActionParameter("index",String.valueOf(index));

            }
        }

    }


    @ViewLifecycleRestriction
    public CollectionGroupBase getCollectionGroupPrototype() {
        return collectionGroupPrototype;
    }

    public void setCollectionGroupPrototype(CollectionGroupBase collectionGroupPrototype) {
        this.collectionGroupPrototype = collectionGroupPrototype;
    }
}
