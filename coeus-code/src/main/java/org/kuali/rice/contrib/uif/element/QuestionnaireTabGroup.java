package org.kuali.rice.contrib.uif.element;

import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.krad.uif.component.Component;
import org.kuali.rice.krad.uif.container.CollectionGroupBase;
import org.kuali.rice.krad.uif.container.GroupBase;
import org.kuali.rice.krad.uif.container.TabGroup;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.Header;
import org.kuali.rice.krad.uif.util.ComponentFactory;
import org.kuali.rice.krad.uif.util.ComponentUtils;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.springframework.context.LifecycleProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuestionnaireTabGroup extends TabGroup {

    private CollectionGroupBase collectionGroupPrototype;

    @Override
    public void performInitialization(Object model) {
        List<AnswerHeader> answerHeaders = ((ProposalDevelopmentDocumentForm)model).getQuestionnaireHelper().getAnswerHeaders();
        answerHeaders.addAll(((ProposalDevelopmentDocumentForm)model).getS2sQuestionnaireHelper().getAnswerHeaders());
        List<Component> tabs = new ArrayList<Component>();

        Collections.sort(answerHeaders, new Comparator<AnswerHeader>(){
            public int compare(AnswerHeader a1, AnswerHeader a2) {
                return a1.getLabel().compareTo(a2.getLabel());
            }
        });

        int index = 0;
        for (AnswerHeader answerHeader : answerHeaders) {
            GroupBase group = (GroupBase) ComponentFactory.getNewComponentInstance("Uif-VerticalBoxGroup");
            group.setHeader((Header)ComponentFactory.getNewComponentInstance("Uif-HeaderOne"));
            group.setHeaderText(answerHeader.getLabel());
            CollectionGroupBase questionCollection = ComponentUtils.copy(collectionGroupPrototype);
            questionCollection.setPropertyName("questionnaireHelper.answerHeaders[" + index + "].questions");
            group.setItems(Collections.singletonList(questionCollection));
            tabs.add(group);
            index++;
        }

        this.setItems(tabs);

        super.performInitialization(model);
    }

    public CollectionGroupBase getCollectionGroupPrototype() {
        return collectionGroupPrototype;
    }

    public void setCollectionGroupPrototype(CollectionGroupBase collectionGroupPrototype) {
        this.collectionGroupPrototype = collectionGroupPrototype;
    }
}
