package org.kuali.coeus.dc.questseq;

import org.kuali.coeus.dc.common.db.ConnectionDaoService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.setString;
import static org.kuali.coeus.dc.common.db.PreparedStatementUtils.setLong;

public class QuestSeqDaoImpl implements QuestSeqDao {
    private static final String QUESTION_ID = "Question ID";
    private static final String QUESTION_REF_ID = "Question Ref ID";
    private static final String QUESTIONNAIRE_ID = "Questionnaire ID";
    private static final String QUESTIONNAIRE_REF_ID = "Questionnaire Ref ID";
    private static final String QUESTION_SEQ_ID = "Question Seq ID";
    private static final String QUESTIONNAIRE_SEQ_ID = "Questionnaire Seq ID";

    private ConnectionDaoService connectionDaoService;

    @Override
    public void convertQuestSeqKrmsValues() {
        final Collection<TermParm> parmsQuestion = getTermParmWithName(QUESTION_ID);
        for (TermParm parm : parmsQuestion) {
            parm.name = QUESTION_SEQ_ID;
            //not fixing the value because the value is already the question seq id
            parm.versionNumber = parm.versionNumber + 1;
        }
        updateTermParms(parmsQuestion);
        updateTermResolverParamSpecName(QUESTION_ID, QUESTION_SEQ_ID);
        updateTermResolverParamSpecName(QUESTION_REF_ID, QUESTION_SEQ_ID);

        final Collection<TermParm> parmsQuestionnaire = getTermParmWithName(QUESTIONNAIRE_ID);
        parmsQuestionnaire.addAll(getTermParmWithName(QUESTIONNAIRE_REF_ID));
        for (TermParm parm : parmsQuestionnaire) {
            parm.name = QUESTIONNAIRE_SEQ_ID;
            parm.value = getQuestionnaireSeqId(parm.value);
            parm.versionNumber = parm.versionNumber + 1;
        }

        updateTermParms(parmsQuestionnaire);
        updateTermResolverParamSpecName(QUESTIONNAIRE_ID, QUESTIONNAIRE_SEQ_ID);
        updateTermResolverParamSpecName(QUESTIONNAIRE_REF_ID, QUESTIONNAIRE_SEQ_ID);
    }

    public ConnectionDaoService getConnectionDaoService() {
        return connectionDaoService;
    }

    public void setConnectionDaoService(ConnectionDaoService connectionDaoService) {
        this.connectionDaoService = connectionDaoService;
    }

    private String getQuestionnaireSeqId(String questionnaireId) {
        Connection connection = connectionDaoService.getCoeusConnection();
        //the questionnaire_id is actually the questionnaire seq id.  The questionnaire_ref_id is the questionnaire id.
        try (PreparedStatement stmt =
                     setString(1, questionnaireId,
                             connection.prepareStatement("SELECT QUESTIONNAIRE_ID FROM QUESTIONNAIRE WHERE QUESTIONNAIRE_REF_ID = ?"));
             ResultSet result = stmt.executeQuery()) {

            if (result.next()) {
                return result.getString(1);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Collection<TermParm> getTermParmWithName(String name) {
        Connection connection = connectionDaoService.getRiceConnection();
        Collection<TermParm> parms = new ArrayList<>();
        try (PreparedStatement stmt =
                     setString(1, name,
                             connection.prepareStatement("SELECT term_parm_id, term_id, nm, val, ver_nbr FROM krms_term_parm_t WHERE nm = ?"));
             ResultSet result = stmt.executeQuery()) {

            while (result.next()) {
                parms.add(new TermParm(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getLong(5)));
            }
            return parms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateTermParms(Collection<TermParm> parms) {
        for (TermParm parm : parms) {
            Connection connection = connectionDaoService.getRiceConnection();
            try (PreparedStatement stmt = setString(4, parm.id,
                    setLong(3, parm.versionNumber,
                            setString(2, parm.value,
                                    setString(1, parm.name, connection.prepareStatement("UPDATE krms_term_parm_t SET nm = ?, val = ?, ver_nbr = ? WHERE term_parm_id = ?")))))) {
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void updateTermResolverParamSpecName(String oldName, String newName) {
        Connection connection = connectionDaoService.getRiceConnection();
        try (PreparedStatement stmt = setString(2, oldName, setString(1, newName, connection.prepareStatement("update KRMS_TERM_RSLVR_PARM_SPEC_T set nm = ? where nm = ?")))) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static class TermParm {

        public TermParm(String id, String termId, String name, String value, Long versionNumber) {
            this.id = id;
            this.termId = termId;
            this.name = name;
            this.value = value;
            this.versionNumber = versionNumber;
        }

        private String id;
        private String termId;
        private String name;
        private String value;
        private Long versionNumber;
    }
}
