set serveroutput on size 1000000
declare
	CurSeqNextVal NUMBER;
	MaxTblVal NUMBER;
begin
begin
-- sequence: KREW_DOC_HDR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_HDR_S';
-- tables(columns): KREW_DOC_HDR_T(DOC_HDR_ID)
--					KREW_DOC_TYP_T(DOC_TYP_ID)
--					KREW_APP_DOC_STAT_TRAN_T(APP_DOC_STAT_TRAN_ID)
	select max(TblVal) into MaxTblVal from (select max(DOC_HDR_ID) TblVal from KREW_DOC_HDR_T union
	select max(DOC_TYP_ID) from KREW_DOC_TYP_T union
	select max(APP_DOC_STAT_TRAN_ID) from KREW_APP_DOC_STAT_TRAN_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_DOC_HDR_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_DOC_HDR_S';
     execute IMMEDIATE 'create sequence KREW_DOC_HDR_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;
end;
/