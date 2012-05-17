declare
    MaxTblVal INTEGER;
begin
    select max(ID) + 1 into MaxTblVal from 
        ((select max(DOC_HDR_ID) as ID from KREW_DOC_HDR_T)
        union (select max(DOC_TYP_ID) as ID from KREW_DOC_TYP_T) 
        union (select max(APP_DOC_STAT_TRAN_ID) as ID from KREW_APP_DOC_STAT_TRAN_T));
    execute immediate 'DROP SEQUENCE KREW_DOC_HDR_S';
    execute immediate 'CREATE SEQUENCE KREW_DOC_HDR_S START WITH ' || MaxTblVal || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/
