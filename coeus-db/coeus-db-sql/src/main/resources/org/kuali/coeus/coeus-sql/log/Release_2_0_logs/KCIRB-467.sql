DELETE FROM QUESTION;
DELETE FROM QUESTION_EXPLANATION;
DELETE FROM QUESTIONNAIRE_ANSWER_HEADER;
DELETE FROM QUESTIONNAIRE_ANSWER;
DELETE FROM QUESTIONNAIRE;
DELETE FROM QUESTIONNAIRE_QUESTIONS;
DELETE FROM QUESTIONNAIRE_USAGE;

DROP SEQUENCE SEQ_QUESTIONNAIRE_ID;
DROP SEQUENCE SEQ_QUESTION_ID;

CREATE SEQUENCE SEQ_QUESTIONNAIRE_REF_ID INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_QUESTIONNAIRE_ID INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_QUESTION_ID INCREMENT BY 1 START WITH 1;