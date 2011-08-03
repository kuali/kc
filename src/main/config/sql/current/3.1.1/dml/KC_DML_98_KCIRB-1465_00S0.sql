declare
    MaxTblVal INTEGER;
begin
    select max(QUESTION_ID) + 1 into MaxTblVal from QUESTION;
    execute immediate 'DROP SEQUENCE SEQ_QUESTION_ID';
    execute immediate 'CREATE SEQUENCE SEQ_QUESTION_ID START WITH ' || MaxTblVal || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/
declare
    MaxTblVal INTEGER;
begin
    select max(QUESTIONNAIRE_ID) + 1 into MaxTblVal from QUESTIONNAIRE;
    execute immediate 'DROP SEQUENCE SEQ_QUESTIONNAIRE_ID';
    execute immediate 'CREATE SEQUENCE SEQ_QUESTIONNAIRE_ID START WITH ' || MaxTblVal || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/
declare
    MaxTblVal INTEGER;
begin
    select max(ID) + 1 into MaxTblVal from 
       ((select max(QUESTION_REF_ID) as ID from QUESTION)
       union (select max(QUESTION_EXPLANATION_ID) as ID from QUESTION_EXPLANATION)
       union (select max(QUESTIONNAIRE_ANSWER_HEADER_ID) as ID from QUESTIONNAIRE_ANSWER_HEADER)
       union (select max(QUESTIONNAIRE_ANSWER_ID) as ID from QUESTIONNAIRE_ANSWER)
       union (select max(QUESTIONNAIRE_REF_ID) as ID from QUESTIONNAIRE)
       union (select max(QUESTIONNAIRE_QUESTIONS_ID) as ID from QUESTIONNAIRE_QUESTIONS)
       union (select max(QUESTIONNAIRE_USAGE_ID) as ID from QUESTIONNAIRE_USAGE));
    execute immediate 'DROP SEQUENCE SEQ_QUESTIONNAIRE_REF_ID';
    execute immediate 'CREATE SEQUENCE SEQ_QUESTIONNAIRE_REF_ID START WITH ' || MaxTblVal || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/
