update questionnaire_usage set QUESTIONNAIRE_LABEL='Grants.gov S2S Questionnaire' where questionnaire_ref_id_fk in (select questionnaire_ref_id from questionnaire where questionnaire_id=5)
/
