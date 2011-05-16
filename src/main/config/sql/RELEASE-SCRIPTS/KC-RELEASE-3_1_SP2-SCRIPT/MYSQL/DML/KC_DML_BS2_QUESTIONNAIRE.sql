insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE (QUESTIONNAIRE_REF_ID,QUESTIONNAIRE_ID,SEQUENCE_NUMBER,NAME,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,IS_FINAL,DOCUMENT_NUMBER,VER_NBR,OBJ_ID) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),2,2,'NSF s2s form supporting questions','These questions support  NSF Coverpage 1-3 Grants.gov forms. Revised for 4.3.7 to single form-specific Questionnaire.',STR_TO_DATE('20110212','%Y%m%d'),'admin','Y',10138,1,UUID());

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE (QUESTIONNAIRE_REF_ID,QUESTIONNAIRE_ID,SEQUENCE_NUMBER,NAME,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,IS_FINAL,DOCUMENT_NUMBER,VER_NBR,OBJ_ID) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),1,2,'PHS Fellowship Supplemental Form V1-0 & 1-1','The responses are used to populate the PHS Fellowhship Supplemental Form version 1-0 & 1-1 for submission via Grants.gov',STR_TO_DATE('20110212','%Y%m%d'),'admin','Y',10139,1,UUID());

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE (QUESTIONNAIRE_REF_ID,QUESTIONNAIRE_ID,SEQUENCE_NUMBER,NAME,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,IS_FINAL,DOCUMENT_NUMBER,VER_NBR,OBJ_ID) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,1,'PHS398 Training Budget Form version 1-0','Information required to support the training budget form created  November 2009. Antcipated NIH first use: January 2010.',STR_TO_DATE('20110212','%Y%m%d'),'admin','Y',10140,1,UUID());

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE (QUESTIONNAIRE_REF_ID,QUESTIONNAIRE_ID,SEQUENCE_NUMBER,NAME,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,IS_FINAL,DOCUMENT_NUMBER,VER_NBR,OBJ_ID) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),4,1,'PHS Fellowship Supplemental Form V1-2','The responses are used to populate the PHS Fellowship Supplemental Form version 1-2 for submission via Grants.gov.',STR_TO_DATE('20110212','%Y%m%d'),'admin','Y',10146,1,UUID());