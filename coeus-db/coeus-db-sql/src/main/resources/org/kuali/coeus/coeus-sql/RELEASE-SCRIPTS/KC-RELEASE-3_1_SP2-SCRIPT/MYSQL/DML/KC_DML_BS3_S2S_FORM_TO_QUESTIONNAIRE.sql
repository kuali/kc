insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/PHS398_TrainingBudget-V1.0','PHS398_TrainingBudget-V1.0',3,NOW(),'admin',UUID(),1);

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_1-V1.1','PHS_Fellowship_Supplemental_1_1-V1.1',1,NOW(),'admin',UUID(),1);

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_2-V1.2','PHS_Fellowship_Supplemental_1_2-V1.2',4,NOW(),'admin',UUID(),1);

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3','NSF_CoverPage_1_3-V1.3',2,NOW(),'admin',UUID(),1);