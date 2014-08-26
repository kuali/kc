package org.kuali.coeus.propdev.impl.questionnaire;


import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.CollectionGroupBase;
import org.kuali.rice.krad.uif.container.GroupBase;
import org.kuali.rice.krad.uif.container.TabGroup;
import org.kuali.rice.krad.uif.element.Header;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.ComponentUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuestionnaireTabGroup extends TabGroup {

    private CollectionGroupBase collectionGroupPrototype;

    @Override
    public void performInitialization(Object model) {
        List<Component> tabs = new ArrayList<Component>();

        tabs.addAll(createTabs(((ProposalDevelopmentDocumentForm)model).getQuestionnaireHelper().getAnswerHeaders(),"questionnaireHelper"));
        tabs.addAll(createTabs(((ProposalDevelopmentDocumentForm)model).getS2sQuestionnaireHelper().getAnswerHeaders(),"s2sQuestionnaireHelper"));

        Collections.sort(tabs, new Comparator<Component>(){
            public int compare(Component c1, Component c2) {
                return ((GroupBase)c1).getHeader().getHeaderText().compareTo(((GroupBase) c2).getHeader().getHeaderText());
            }
        });

        this.setItems(tabs);

        super.performInitialization(model);
    }

    private List<Component> createTabs(List<AnswerHeader> answerHeaders, String helper) {
        List<Component> tabs = new ArrayList<Component>();
        int index = 0;
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.isActive()) {
                GroupBase group = (GroupBase) ComponentFactory.getNewComponentInstance("Uif-VerticalBoxGroup");
                group.setHeader((Header)ComponentFactory.getNewComponentInstance("Uif-SectionHeader"));
                group.setHeaderText(answerHeader.getLabel());
                group.getHeader().setRender(false);
                CollectionGroupBase questionCollection = ComponentUtils.copy(collectionGroupPrototype);
                questionCollection.setHeader((Header) ComponentFactory.getNewComponentInstance("Uif-SectionHeader"));
                questionCollection.setHeaderText(answerHeader.getLabel() + (answerHeader.isCompleted() ? "[color=green] (Complete)" : " [color=gray](Incomplete)") + "[/color]");
                questionCollection.setPropertyName(helper + ".answerHeaders[" + index + "].questions");
                group.setItems(Collections.singletonList(questionCollection));
                tabs.add(group);
            }
            index++;
        }

        return tabs;
    }

    public CollectionGroupBase getCollectionGroupPrototype() {
        return collectionGroupPrototype;
    }

    public void setCollectionGroupPrototype(CollectionGroupBase collectionGroupPrototype) {
        this.collectionGroupPrototype = collectionGroupPrototype;
    }
}
