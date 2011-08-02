set serveroutput on size 1000000
declare
	CurSeqNextVal NUMBER;
	MaxTblVal NUMBER;
begin
begin
-- sequence: SEQ_QUESTION_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_QUESTION_ID';
-- tables(columns): QUESTION(QUESTION_ID)
	execute immediate 'select max(QUESTION_ID) from QUESTION' into MaxTblVal;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: SEQ_QUESTION_ID');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence SEQ_QUESTION_ID';
     execute IMMEDIATE 'create sequence SEQ_QUESTION_ID START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: SEQ_QUESTIONNAIRE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_QUESTIONNAIRE_ID';
-- tables(columns): QUESTIONNAIRE(QUESTIONNAIRE_ID)
	execute immediate 'select max(QUESTIONNAIRE_ID) from QUESTIONNAIRE' into MaxTblVal;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: SEQ_QUESTIONNAIRE_ID');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence SEQ_QUESTIONNAIRE_ID';
     execute IMMEDIATE 'create sequence SEQ_QUESTIONNAIRE_ID START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: SEQ_QUESTIONNAIRE_REF_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_QUESTIONNAIRE_REF_ID';
-- tables(columns): QUESTION(QUESTION_REF_ID)
--                  QUESTION_EXPLANATION(QUESTION_EXPLANATION_ID)
--                  QUESTIONNAIRE_ANSWER_HEADER(QUESTIONNAIRE_ANSWER_HEADER_ID)
--                  QUESTIONNAIRE_ANSWER(QUESTIONNAIRE_ANSWER_ID)
--                  QUESTIONNAIRE(QUESTIONNAIRE_REF_ID)
--                  QUESTIONNAIRE_QUESTIONS(QUESTIONNAIRE_QUESTIONS_ID)
--                  QUESTIONNAIRE_USAGE(QUESTIONNAIRE_USAGE_ID)
	execute immediate 'select max(TblVal) from (select max(QUESTION_REF_ID) TblVal from QUESTION union ' ||
	'select max(QUESTION_EXPLANATION_ID) from QUESTION_EXPLANATION union ' ||
	'select max(QUESTIONNAIRE_ANSWER_HEADER_ID) from QUESTIONNAIRE_ANSWER_HEADER union ' ||
	'select max(QUESTIONNAIRE_ANSWER_ID) from QUESTIONNAIRE_ANSWER union ' ||
	'select max(QUESTIONNAIRE_REF_ID) from QUESTIONNAIRE union ' ||
	'select max(QUESTIONNAIRE_QUESTIONS_ID) from QUESTIONNAIRE_QUESTIONS union ' ||
	'select max(QUESTIONNAIRE_USAGE_ID) from QUESTIONNAIRE_USAGE)' into MaxTblVal;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: SEQ_QUESTIONNAIRE_REF_ID');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence SEQ_QUESTIONNAIRE_REF_ID';
     execute IMMEDIATE 'create sequence SEQ_QUESTIONNAIRE_REF_ID START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;
end;
/

