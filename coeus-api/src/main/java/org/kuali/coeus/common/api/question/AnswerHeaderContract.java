package org.kuali.coeus.common.api.question;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.api.model.Inactivatable;

import java.util.List;

public interface AnswerHeaderContract extends IdentifiableNumeric, Inactivatable {

    String getModuleItemCode();

    String getModuleItemKey();

    String getModuleSubItemCode();

    String getModuleSubItemKey();

    Long getQuestionnaireId();

    boolean isCompleted();

    List<? extends AnswerContract> getAnswers();
}
