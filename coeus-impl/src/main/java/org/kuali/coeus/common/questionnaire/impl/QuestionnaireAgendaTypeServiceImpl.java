package org.kuali.coeus.common.questionnaire.impl;


import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.impl.unit.UnitAgendaTypeServiceImpl;
import org.kuali.rice.core.api.exception.RiceIllegalArgumentException;
import org.kuali.rice.krms.api.engine.ExecutionEnvironment;
import org.kuali.rice.krms.api.repository.agenda.AgendaDefinition;
import org.kuali.rice.krms.framework.engine.Agenda;
import org.kuali.rice.krms.framework.engine.AgendaTree;
import org.kuali.rice.krms.framework.engine.BasicAgenda;
import org.kuali.rice.krms.impl.provider.repository.LazyAgendaTree;
import org.kuali.rice.krms.impl.provider.repository.RepositoryToEngineTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("questionnaireAgendaTypeService")
public class QuestionnaireAgendaTypeServiceImpl extends UnitAgendaTypeServiceImpl {

    @Autowired
    @Qualifier("repositoryToEngineTranslator")
    private RepositoryToEngineTranslator repositoryToEngineTranslator;

    @Override
    public Agenda loadAgenda(AgendaDefinition agendaDefinition) {

        if (agendaDefinition == null) { throw new RiceIllegalArgumentException("agendaDefinition must not be null"); }
        if (repositoryToEngineTranslator == null) {
            return null;
        }

        return new QuestionnaireAgenda(agendaDefinition.getAttributes(), new LazyAgendaTree(agendaDefinition, repositoryToEngineTranslator),
                agendaDefinition.getTypeId(),agendaDefinition.isActive());
    }

    private class QuestionnaireAgenda extends BasicAgenda {

        private Map<String, String> qualifiers;
        private boolean isActive;

        public QuestionnaireAgenda(Map<String, String> qualifiers, AgendaTree agendaTree, String agendaTypeId,boolean isActive) {
            super(qualifiers, agendaTree);
            this.isActive = isActive;
            this.qualifiers = new HashMap<String, String>(qualifiers);
            this.qualifiers.put("typeId", agendaTypeId);
        }

        @Override
        public boolean appliesTo(ExecutionEnvironment environment) {
            if (!isActive){
                return false;
            }
            String environmentId = environment.getSelectionCriteria().getAgendaQualifiers().get("typeId");
            String agendaId = this.qualifiers.get("typeId");
            return StringUtils.equals(environmentId,agendaId);
    }
    }

    public RepositoryToEngineTranslator getRepositoryToEngineTranslator() {
        return repositoryToEngineTranslator;
    }

    public void setRepositoryToEngineTranslator(RepositoryToEngineTranslator repositoryToEngineTranslator) {
        this.repositoryToEngineTranslator = repositoryToEngineTranslator;
    }
}
