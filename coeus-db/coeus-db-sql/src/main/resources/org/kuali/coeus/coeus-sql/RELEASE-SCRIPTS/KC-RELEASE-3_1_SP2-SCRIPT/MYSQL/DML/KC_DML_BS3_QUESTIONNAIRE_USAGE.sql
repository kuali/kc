insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=2 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'NSF Cover page  s2s form supporting questions',NOW(),'admin',1,UUID(),'N');

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=3 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'PHS398 Training Budget V1-0',NOW(),'admin',1,UUID(),'Y');

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=4 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'PHS Fellowship Form V1-2',NOW(),'admin',1,UUID(),'Y');

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=1 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'PHS Fellowship Form V1-1',NOW(),'admin',1,UUID(),'Y');