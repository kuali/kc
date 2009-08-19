/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.questionnaire;

import java.sql.Statement;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBroker;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.SequenceAccessorService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.OjbCollectionAware;
import org.springframework.transaction.annotation.Transactional;
import org.springmodules.orm.ojb.PersistenceBrokerCallback;

public class QuestionnaireDaoOjb extends PlatformAwareDaoBaseOjb implements OjbCollectionAware, QuestionnaireDao {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory
            .getLog(QuestionnaireDaoOjb.class);

    private String questionColumns = "(QUESTIONNAIRE_QUESTIONS_ID,QUESTIONNAIRE_REF_ID_FK,QUESTION_REF_ID_FK,QUESTION_NUMBER,PARENT_QUESTION_NUMBER,CONDITION_FLAG,CONDITION,CONDITION_VALUE,QUESTION_SEQ_NUMBER,UPDATE_USER,UPDATE_TIMESTAMP)";
    private String usageColumns = "(QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER)";

    public void runScripts(final String[] sqls, final Long questionnaireRefId) {

         this.getPersistenceBrokerTemplate().execute(new PersistenceBrokerCallback() {
            public Object doInPersistenceBroker(PersistenceBroker pb) {
                Statement stmt = null;
                try {
                    stmt = pb.serviceConnectionManager().getConnection().createStatement();
                    String userName = new UniversalUser(GlobalVariables.getUserSession().getPerson()).getPersonUserIdentifier();
                    for (int i = 0; i < sqls.length; i++) {
                        if (StringUtils.isNotBlank(sqls[i])) {
                            String sql = sqls[i];
                            if (sql.startsWith("insert into Q")) {
                                Long questionnaireQuestionId = KraServiceLocator.getService(SequenceAccessorService.class)
                                        .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID");
                                sql = sql.replace("insert into Q", "insert into questionnaire_questions " + questionColumns + " values("
                                        + questionnaireQuestionId + "," + questionnaireRefId + ",");
                                sql = sql.replace("user,sysdate", "'"+userName+"',sysdate");
                            } else if (sql.startsWith("insert U")) {
                                // TODO : sub_mod_code and rule_id are set to '0' for now
                                Long questionnaireUsageId = KraServiceLocator.getService(SequenceAccessorService.class)
                                .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID");
                                String[] params = sql.split(";");
                                sql = "insert into questionnaire_usage " + usageColumns + " values(" + questionnaireUsageId + ",'" 
                                     + params[1] + "','0'," +  questionnaireRefId + ","+ params[2]+ ",'0','"+params[3]+"',sysdate,'"+userName+"')";
                            } else if (sql.startsWith("delete U")) {
                                // TODO : this may not be appropriate because one module may be associated with different version of questionnaire
                                Long questionnaireUsageId = KraServiceLocator.getService(SequenceAccessorService.class)
                                .getNextAvailableSequenceNumber("SEQ_QUESTIONNAIRE_ID");
                                String[] params = sql.split(";");
                                sql = "delete from questionnaire_usage where QUESTIONNAIRE_REF_ID_FK = " + 
                                     questionnaireRefId + " and MODULE_ITEM_CODE = '"+params[1]+"' and QUESTIONNAIRE_SEQUENCE_NUMBER = "+params[2];
                            } else if (sql.startsWith("update QCond")) {
                                String[] params = sql.split(";");
                                sql = "update questionnaire_questions set CONDITION_FLAG = " + params[1] + ", CONDITION = " + params[2] 
                                        + ", CONDITION_VALUE = " + params[3] + ", UPDATE_USER = '" + userName+ "', UPDATE_TIMESTAMP = sysdate where QUESTIONNAIRE_REF_ID_FK = "
                                        + questionnaireRefId + " and QUESTION_REF_ID_FK = " + params[4] + " and  QUESTION_NUMBER = "
                                        + params[5];

                            //} else if (sql.startsWith("commit")) {
                                //String[] params = sql.split(";");
                               // sql = "update questionnaire_questions set PARENT_QUESTION_NUMBER = "+params[1] +",QUESTION_SEQ_NUMBER = " + params[2]
                                //        + " where QUESTIONNAIRE_ID = " + questionnaireId + " and QUESTION_REF_ID_FK = " + params[3]
                                //        + " and  QUESTION_NUMBER = " + params[4];

                            }else if (sql.startsWith("update QMove")) {
                                String[] params = sql.split(";");
                                sql = "update questionnaire_questions set QUESTION_SEQ_NUMBER = " + params[1] + ", UPDATE_USER = '" + userName+ "', UPDATE_TIMESTAMP = sysdate "
                                        + " where QUESTIONNAIRE_REF_ID_FK = " + questionnaireRefId + " and QUESTION_REF_ID_FK = " + params[2]
                                        + " and  QUESTION_NUMBER = " + params[3];

                            } else if (sql.startsWith("update QSeq")) {
                                String[] params = sql.split(";");
                                // TODO : update the siblings', that is after this node,  sequence_num
                                sql = "update questionnaire_questions set QUESTION_SEQ_NUMBER = QUESTION_SEQ_NUMBER + 1 where QUESTIONNAIRE_REF_ID_FK = " + questionnaireRefId 
                                        + " and  PARENT_QUESTION_NUMBER = " + params[2] + " and  QUESTION_SEQ_NUMBER >= " + params[1];

                            } else if (sql.startsWith("delete Q")) {
                                String[] params = sql.split(";");
                                sql = "delete from questionnaire_questions where QUESTIONNAIRE_REF_ID_FK = " + questionnaireRefId + " and QUESTION_REF_ID_FK = " + params[1]
                                        + " and  QUESTION_NUMBER = " + params[2];
                                LOG.info("Save run scripts " + i + sql);
                                stmt.addBatch(sql);
                                // TODO : update the siblings', that is after this node,  sequence_num
                                sql = "update questionnaire_questions set QUESTION_SEQ_NUMBER = QUESTION_SEQ_NUMBER -1 where QUESTIONNAIRE_REF_ID_FK = " + questionnaireRefId 
                                        + " and  PARENT_QUESTION_NUMBER = " + params[3] + " and  QUESTION_SEQ_NUMBER > " + params[4];

                            } else {
                                // TODO : something not wanted
                                sql = "";
                            }
                            if (StringUtils.isNotBlank(sql)) {
                                LOG.info("Save run scripts " + i + sql);
                                stmt.addBatch(sql);
                            }
                        }
                    }
                    int[] updCnt = stmt.executeBatch();
                    for (int i = 0; i < updCnt.length; i++) {
                        // do we need to do check
                        LOG.info("results " + i + updCnt[i]);
                    }
                }
                catch (Exception e) {
                    LOG.error("exception error " + e.getStackTrace());
                    GlobalVariables.getUserSession().addObject("qnError", (Object)("error running scripts " + e.getMessage()));
                    throw new RuntimeException("run Questionnaire sql scripts Exception", e);
                    //ErrorMap errorMap = GlobalVariables.getErrorMap();
                    //errorMap.putError("sqlerror", e.getMessage()+e.getStackTrace());
                }
                finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        }
                        catch (Exception e) {
                            LOG.error("error closing statement", e);
                            GlobalVariables.getUserSession().addObject("qnError", (Object)("error closing statement " + e.getMessage()));
                            throw new RuntimeException("run Questionnaire sql scripts Exception", e);
                            // ErrorMap errorMap = GlobalVariables.getErrorMap();
                           // errorMap.putError("closestmterror", e.getMessage()+e.getStackTrace());
                        }
                    }
                }
                return null;
            }
        });

    }

}
