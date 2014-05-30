package org.kuali.coeus.common.api.ynq;

import org.kuali.coeus.sys.api.model.Describable;

import java.util.Date;
import java.util.List;

public interface YnqContract extends Describable {

    String getQuestionId();
    
    String getDateRequiredFor();

    Date getEffectiveDate();
    
    String getExplanationRequiredFor();
    
    String getGroupName();
    
    Integer getNoOfAnswers();
    
    String getQuestionType();
    
    String getStatus();

    List<? extends YnqExplanationContract> getYnqExplanations();
}
