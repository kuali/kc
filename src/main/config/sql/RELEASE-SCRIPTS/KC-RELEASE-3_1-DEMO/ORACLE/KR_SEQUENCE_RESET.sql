set serveroutput on size 1000000
declare
	CurSeqNextVal NUMBER;
	MaxTblVal NUMBER;
begin
begin
-- sequence: KRSB_BAM_PARM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_BAM_PARM_S';
-- tables(columns): KRSB_BAM_PARM_T(BAM_PARM_ID)
	select max(BAM_PARM_ID) into MaxTblVal from KRSB_BAM_PARM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRSB_BAM_PARM_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRSB_BAM_PARM_S';
    execute IMMEDIATE 'create sequence KRSB_BAM_PARM_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
  end if;
exception when others then null;
end;

begin
-- sequence: KRSB_BAM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_BAM_S';
-- tables(columns): KRSB_BAM_T(BAM_ID)
	select max(BAM_ID) into MaxTblVal from KRSB_BAM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRSB_BAM_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRSB_BAM_S';
     execute IMMEDIATE 'create sequence KRSB_BAM_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRSB_FLT_SVC_DEF_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_FLT_SVC_DEF_S';
-- tables(columns): KRSB_FLT_SVC_DEF_T(FLT_SVC_DEF_ID)
	select max(FLT_SVC_DEF_ID) into MaxTblVal from KRSB_FLT_SVC_DEF_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRSB_FLT_SVC_DEF_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRSB_FLT_SVC_DEF_S';
     execute IMMEDIATE 'create sequence KRSB_FLT_SVC_DEF_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRSB_MSG_QUE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_MSG_QUE_S';
-- tables(columns): KRSB_MSG_QUE_T(MSG_QUE_ID)
	select max(MSG_QUE_ID) into MaxTblVal from KRSB_MSG_QUE_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRSB_MSG_QUE_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRSB_MSG_QUE_S';
     execute IMMEDIATE 'create sequence KRSB_MSG_QUE_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRSB_SVC_DEF_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_SVC_DEF_S';
-- tables(columns): KRSB_SVC_DEF_T(SVC_DEF_ID)
	select max(SVC_DEF_ID) into MaxTblVal from KRSB_SVC_DEF_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRSB_SVC_DEF_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRSB_SVC_DEF_S';
     execute IMMEDIATE 'create sequence KRSB_SVC_DEF_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRNS_LOCK_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRNS_LOCK_S';
-- tables(columns): KRNS_PESSIMISTIC_LOCK_T(PESSIMISTIC_LOCK_ID)
	select max(PESSIMISTIC_LOCK_ID) into MaxTblVal from KRNS_PESSIMISTIC_LOCK_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRNS_LOCK_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRNS_LOCK_S';
     execute IMMEDIATE 'create sequence KRNS_LOCK_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRNS_MAINT_LOCK_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRNS_MAINT_LOCK_S';
-- tables(columns): KRNS_MAINT_LOCK_T(MAINT_LOCK_ID)
	select max(MAINT_LOCK_ID) into MaxTblVal from KRNS_MAINT_LOCK_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRNS_MAINT_LOCK_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRNS_MAINT_LOCK_S';
     execute IMMEDIATE 'create sequence KRNS_MAINT_LOCK_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRNS_NTE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRNS_NTE_S';
-- tables(columns): KRNS_NTE_T(NTE_ID)
	select max(NTE_ID) into MaxTblVal from KRNS_NTE_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRNS_NTE_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRNS_NTE_S';
     execute IMMEDIATE 'create sequence KRNS_NTE_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ATTR_DATA_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ATTR_DATA_ID_S';
-- tables(columns): KRIM_PND_ROLE_MBR_ATTR_DATA_MT(ATTR_DATA_ID)
-- 					KRIM_PND_DLGN_MBR_ATTR_DATA_T(ATTR_DATA_ID)
-- 					KRIM_PND_GRP_ATTR_DATA_T(ATTR_DATA_ID)
	select max(TblVal) into MaxTblVal from (select max(ATTR_DATA_ID) TblVal from KRIM_PND_ROLE_MBR_ATTR_DATA_MT union
	select max(ATTR_DATA_ID) from KRIM_PND_DLGN_MBR_ATTR_DATA_T union
	select max(ATTR_DATA_ID) from KRIM_PND_GRP_ATTR_DATA_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ATTR_DATA_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ATTR_DATA_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ATTR_DATA_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_DLGN_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_DLGN_ID_S';
-- tables(columns): KRIM_PND_DLGN_T(DLGN_ID)
	select max(DLGN_ID) into MaxTblVal from KRIM_PND_DLGN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_DLGN_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_DLGN_ID_S';
     execute IMMEDIATE 'create sequence KRIM_DLGN_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_DLGN_MBR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_DLGN_MBR_ID_S';
-- tables(columns): KRIM_PND_DLGN_MBR_T(DLGN_MBR_ID)
	select max(DLGN_MBR_ID) into MaxTblVal from KRIM_PND_DLGN_MBR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_DLGN_MBR_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_DLGN_MBR_ID_S';
     execute IMMEDIATE 'create sequence KRIM_DLGN_MBR_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_ADDR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_ADDR_ID_S';
-- tables(columns): KRIM_PND_ADDR_MT(ENTITY_ADDR_ID)
	select max(ENTITY_ADDR_ID) into MaxTblVal from KRIM_PND_ADDR_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_ADDR_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_ADDR_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_ADDR_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_AFLTN_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_AFLTN_ID_S';
-- tables(columns): KRIM_PND_AFLTN_MT(ENTITY_AFLTN_ID)
	select max(ENTITY_AFLTN_ID) into MaxTblVal from KRIM_PND_AFLTN_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_AFLTN_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_AFLTN_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_AFLTN_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_EMAIL_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_EMAIL_ID_S';
-- tables(columns): KRIM_PND_EMAIL_MT(ENTITY_EMAIL_ID)
	select max(ENTITY_EMAIL_ID) into MaxTblVal from KRIM_PND_EMAIL_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_EMAIL_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_EMAIL_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_EMAIL_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_EMP_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_EMP_ID_S';
-- tables(columns): KRIM_PND_EMP_INFO_MT(ENTITY_EMP_ID)
	select max(ENTITY_EMP_ID) into MaxTblVal from KRIM_PND_EMP_INFO_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_EMP_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_EMP_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_EMP_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_ETHNIC_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_ETHNIC_ID_S';
-- tables(columns): KRIM_ENTITY_ETHNIC_T(ID)
	select max(ID) into MaxTblVal from KRIM_ENTITY_ETHNIC_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_ETHNIC_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRSB_BAM_PARM_S';
     execute IMMEDIATE 'create sequence KRSB_BAM_PARM_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_EXT_ID_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_EXT_ID_ID_S';
-- tables(columns): KRIM_ENTITY_EXT_ID_T(ENTITY_EXT_ID_ID)
	select max(ENTITY_EXT_ID_ID) into MaxTblVal from KRIM_ENTITY_EXT_ID_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_EXT_ID_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRSB_BAM_PARM_S';
     execute IMMEDIATE 'create sequence KRSB_BAM_PARM_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_ID_S';
-- tables(columns): KRIM_PERSON_DOCUMENT_T(entity_id)
	select max(entity_id) into MaxTblVal from KRIM_PERSON_DOCUMENT_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_NM_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_NM_ID_S';
-- tables(columns): KRIM_PND_NM_MT(ENTITY_NM_ID)
	select max(ENTITY_NM_ID) into MaxTblVal from KRIM_PND_NM_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_NM_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_NM_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_NM_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_PHONE_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_PHONE_ID_S';
-- tables(columns): KRIM_PND_PHONE_MT(ENTITY_PHONE_ID)
	select max(ENTITY_PHONE_ID) into MaxTblVal from KRIM_PND_PHONE_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_PHONE_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_PHONE_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_PHONE_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_RESIDENCY_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_RESIDENCY_ID_S';
-- tables(columns): KRIM_ENTITY_RESIDENCY_T(ID)
	select max(ID) into MaxTblVal from KRIM_ENTITY_RESIDENCY_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_RESIDENCY_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_RESIDENCY_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_RESIDENCY_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_VISA_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_VISA_ID_S';
-- tables(columns): KRIM_ENTITY_VISA_T(ID)
	select max(ID) into MaxTblVal from KRIM_ENTITY_VISA_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ENTITY_VISA_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ENTITY_VISA_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ENTITY_VISA_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_GRP_ATTR_DATA_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_GRP_ATTR_DATA_ID_S';
-- tables(columns): KRIM_GRP_ATTR_DATA_T(ATTR_DATA_ID)
	select max(ATTR_DATA_ID) into MaxTblVal from KRIM_GRP_ATTR_DATA_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_GRP_ATTR_DATA_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_GRP_ATTR_DATA_ID_S';
     execute IMMEDIATE 'create sequence KRIM_GRP_ATTR_DATA_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_GRP_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_GRP_ID_S';
-- tables(columns): KRIM_GRP_DOCUMENT_T(grp_id)
-- 					KRIM_GRP_T(GRP_ID)
	select max(TblVal) into MaxTblVal from (select max(GRP_ID) TblVal from KRIM_GRP_DOCUMENT_T union
	select max(GRP_ID) from KRIM_GRP_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_GRP_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_GRP_ID_S';
     execute IMMEDIATE 'create sequence KRIM_GRP_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_GRP_MBR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_GRP_MBR_ID_S';
-- tables(columns): KRIM_PND_GRP_MBR_T(GRP_MBR_ID)
-- 					KRIM_GRP_MBR_T(GRP_MBR_ID)
-- 					KRIM_PND_GRP_PRNCPL_MT(GRP_MBR_ID)
	select max(TblVal) into MaxTblVal from (select max(GRP_MBR_ID) TblVal from KRIM_PND_GRP_MBR_T union
	select max(GRP_MBR_ID) from KRIM_GRP_MBR_T union
	select max(GRP_MBR_ID) from KRIM_PND_GRP_PRNCPL_MT);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_GRP_MBR_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_GRP_MBR_ID_S';
     execute IMMEDIATE 'create sequence KRIM_GRP_MBR_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_PRNCPL_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_PRNCPL_ID_S';
-- tables(columns): KRIM_PERSON_DOCUMENT_T(prncpl_id)
	select max(prncpl_id) into MaxTblVal from KRIM_PERSON_DOCUMENT_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_PRNCPL_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_PRNCPL_ID_S';
     execute IMMEDIATE 'create sequence KRIM_PRNCPL_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_ID_S';
-- tables(columns): KRIM_ROLE_DOCUMENT_T(role_id)
	select max(role_id) into MaxTblVal from KRIM_ROLE_DOCUMENT_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ROLE_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ROLE_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ROLE_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_MBR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_MBR_ID_S';
-- tables(columns): KRIM_ROLE_MBR_T(ROLE_MBR_ID)
-- 					KRIM_PND_ROLE_MBR_MT(ROLE_MBR_ID)
	select max(TblVal) into MaxTblVal from (select max(ROLE_MBR_ID) TblVal from KRIM_ROLE_MBR_T union
	select max(ROLE_MBR_ID) from KRIM_PND_ROLE_MBR_MT);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ROLE_MBR_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ROLE_MBR_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ROLE_MBR_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_PERM_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_PERM_ID_S';
-- tables(columns): KRIM_PND_ROLE_PERM_T(ROLE_PERM_ID)
	select max(ROLE_PERM_ID) into MaxTblVal from KRIM_PND_ROLE_PERM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ROLE_PERM_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ROLE_PERM_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ROLE_PERM_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_RSP_ACTN_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_RSP_ACTN_ID_S';
-- tables(columns): KRIM_PND_ROLE_RSP_ACTN_MT(ROLE_RSP_ACTN_ID)
	select max(ROLE_RSP_ACTN_ID) into MaxTblVal from KRIM_PND_ROLE_RSP_ACTN_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ROLE_RSP_ACTN_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ROLE_RSP_ACTN_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ROLE_RSP_ACTN_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_RSP_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_RSP_ID_S';
-- tables(columns): KRIM_PND_ROLE_RSP_T(ROLE_RSP_ID)
	select max(ROLE_RSP_ID) into MaxTblVal from KRIM_PND_ROLE_RSP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KRIM_ROLE_RSP_ID_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KRIM_ROLE_RSP_ID_S';
     execute IMMEDIATE 'create sequence KRIM_ROLE_RSP_ID_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_ACTN_ITM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_ACTN_ITM_S';
-- tables(columns): KREW_ACTN_ITM_T(ACTN_ITM_ID)
	select max(ACTN_ITM_ID) into MaxTblVal from KREW_ACTN_ITM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_ACTN_ITM_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_ACTN_ITM_S';
     execute IMMEDIATE 'create sequence KREW_ACTN_ITM_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_ACTN_RQST_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_ACTN_RQST_S';
-- tables(columns): KREW_ACTN_RQST_T(ACTN_RQST_ID)
	select max(ACTN_RQST_ID) into MaxTblVal from KREW_ACTN_RQST_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_ACTN_RQST_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_ACTN_RQST_S';
     execute IMMEDIATE 'create sequence KREW_ACTN_RQST_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_ACTN_TKN_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_ACTN_TKN_S';
-- tables(columns): KREW_ACTN_TKN_T(ACTN_TKN_ID)
	select max(ACTN_TKN_ID) into MaxTblVal from KREW_ACTN_TKN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_ACTN_TKN_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_ACTN_TKN_S';
     execute IMMEDIATE 'create sequence KREW_ACTN_TKN_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

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

begin
-- sequence: KREW_DOC_NTE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_NTE_S';
-- tables(columns): KREW_DOC_NTE_T(DOC_NTE_ID)
-- 					KREW_ATT_T(ATTACHMENT_ID)
	select max(TblVal) into MaxTblVal from (select max(DOC_NTE_ID) TblVal from KREW_DOC_NTE_T union
	select max(ATTACHMENT_ID) from KREW_ATT_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_DOC_NTE_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_DOC_NTE_S';
     execute IMMEDIATE 'create sequence KREW_DOC_NTE_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_DOC_TYP_ATTR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_TYP_ATTR_S';
-- tables(columns): KREW_DOC_TYP_ATTR_T(DOC_TYP_ATTRIB_ID)
	select max(DOC_TYP_ATTRIB_ID) into MaxTblVal from KREW_DOC_TYP_ATTR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_DOC_TYP_ATTR_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_DOC_TYP_ATTR_S';
     execute IMMEDIATE 'create sequence KREW_DOC_TYP_ATTR_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_EDL_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_EDL_S';
-- tables(columns): KREW_STYLE_T(STYLE_ID)
--					KREW_EDL_DEF_T(EDOCLT_DEF_ID)
--					KREW_EDL_ASSCTN_T(EDOCLT_ASSOC_ID)
	select max(TblVal) into MaxTblVal from (select max(STYLE_ID) TblVal from KREW_STYLE_T union
	select max(EDOCLT_DEF_ID) from KREW_EDL_DEF_T union
	select max(EDOCLT_ASSOC_ID) from KREW_EDL_ASSCTN_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_EDL_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_EDL_S';
     execute IMMEDIATE 'create sequence KREW_EDL_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_HLP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_HLP_S';
-- tables(columns): KREW_HLP_T(HLP_ID)
	select max(HLP_ID) into MaxTblVal from KREW_HLP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_HLP_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_HLP_S';
     execute IMMEDIATE 'create sequence KREW_HLP_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_OUT_BOX_ITM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_OUT_BOX_ITM_S';
-- tables(columns): KREW_OUT_BOX_ITM_T(ACTN_ITM_ID)
	select max(ACTN_ITM_ID) into MaxTblVal from KREW_OUT_BOX_ITM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_OUT_BOX_ITM_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_OUT_BOX_ITM_S';
     execute IMMEDIATE 'create sequence KREW_OUT_BOX_ITM_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_RSP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RSP_S';
-- tables(columns): KREW_RULE_RSP_T(RULE_RSP_ID)
	select max(RULE_RSP_ID) into MaxTblVal from KREW_RULE_RSP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_RSP_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_RSP_S';
     execute IMMEDIATE 'create sequence KREW_RSP_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_RTE_NODE_CFG_PARM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RTE_NODE_CFG_PARM_S';
-- tables(columns): KREW_RTE_NODE_CFG_PARM_T(RTE_NODE_CFG_PARM_ID)
	select max(RTE_NODE_CFG_PARM_ID) into MaxTblVal from KREW_RTE_NODE_CFG_PARM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_RTE_NODE_CFG_PARM_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_RTE_NODE_CFG_PARM_S';
     execute IMMEDIATE 'create sequence KREW_RTE_NODE_CFG_PARM_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_RTE_NODE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RTE_NODE_S';
-- tables(columns): KREW_RTE_NODE_T(RTE_NODE_ID)
--					KREW_RTE_BRCH_PROTO_T(RTE_BRCH_PROTO_ID)
--					KREW_RTE_NODE_INSTN_T(RTE_NODE_INSTN_ID)
--					KREW_RTE_BRCH_T(RTE_BRCH_ID)
--					KREW_RTE_BRCH_ST_T(RTE_BRCH_ST_ID)
--					KREW_RTE_NODE_INSTN_ST_T(RTE_NODE_INSTN_ST_ID)
--					KREW_DOC_TYP_PROC_T(DOC_TYP_PROC_ID)
	select max(TblVal) into MaxTblVal from (select max(RTE_NODE_ID) TblVal from KREW_RTE_NODE_T union
	select max(RTE_BRCH_PROTO_ID) from KREW_RTE_BRCH_PROTO_T union
	select max(RTE_NODE_INSTN_ID) from KREW_RTE_NODE_INSTN_T union
	select max(RTE_BRCH_ID) from KREW_RTE_BRCH_T union
	select max(RTE_BRCH_ST_ID) from KREW_RTE_BRCH_ST_T union
	select max(RTE_NODE_INSTN_ST_ID) from KREW_RTE_NODE_INSTN_ST_T union
	select max(DOC_TYP_PROC_ID) from KREW_DOC_TYP_PROC_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_RTE_NODE_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_RTE_NODE_S';
     execute IMMEDIATE 'create sequence KREW_RTE_NODE_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_RTE_TMPL_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RTE_TMPL_S';
-- tables(columns): KREW_RULE_TMPL_T(RULE_TMPL_ID)
-- 					KREW_RULE_ATTR_T(RULE_ATTR_ID)
--					KREW_RULE_TMPL_ATTR_T(RULE_TMPL_ATTR_ID)
--					KREW_RULE_T(RULE_ID)
--					KREW_DLGN_RSP_T(DLGN_RULE_ID)
--					KREW_RULE_EXT_T(RULE_EXT_ID)
--					KREW_RULE_EXT_VAL_T(RULE_EXT_VAL_ID)
	select max(TblVal) into MaxTblVal from (select max(RULE_TMPL_ID) TblVal from KREW_RULE_TMPL_T union
	select max(RULE_ATTR_ID) from KREW_RULE_ATTR_T union
	select max(RULE_TMPL_ATTR_ID) from KREW_RULE_TMPL_ATTR_T union
	select max(RULE_ID) from KREW_RULE_T union
	select max(DLGN_RULE_ID) from KREW_DLGN_RSP_T union
	select max(RULE_EXT_ID) from KREW_RULE_EXT_T union
	select max(RULE_EXT_VAL_ID) from KREW_RULE_EXT_VAL_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_RTE_TMPL_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_RTE_TMPL_S';
     execute IMMEDIATE 'create sequence KREW_RTE_TMPL_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_RULE_EXPR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RULE_EXPR_S';
-- tables(columns): KREW_RULE_EXPR_T(RULE_EXPR_ID)
	select max(RULE_EXPR_ID) into MaxTblVal from KREW_RULE_EXPR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_RULE_EXPR_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_RULE_EXPR_S';
     execute IMMEDIATE 'create sequence KREW_RULE_EXPR_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_RULE_TMPL_OPTN_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RULE_TMPL_OPTN_S';
-- tables(columns): KREW_RULE_TMPL_OPTN_T(RULE_TMPL_OPTN_ID)
	select max(RULE_TMPL_OPTN_ID) into MaxTblVal from KREW_RULE_TMPL_OPTN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_RULE_TMPL_OPTN_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_RULE_TMPL_OPTN_S';
     execute IMMEDIATE 'create sequence KREW_RULE_TMPL_OPTN_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_SRCH_ATTR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_SRCH_ATTR_S';
-- tables(columns): KREW_DOC_HDR_EXT_T(DOC_HDR_EXT_ID)
--					KREW_DOC_HDR_EXT_FLT_T(DOC_HDR_EXT_FLT_ID)
--					KREW_DOC_HDR_EXT_LONG_T(DOC_HDR_EXT_LONG_ID)
--					KREW_DOC_HDR_EXT_DT_T(DOC_HDR_EXT_DT_ID)
	select max(TblVal) into MaxTblVal from (select max(DOC_HDR_EXT_ID) TblVal from KREW_DOC_HDR_EXT_T union
	select max(DOC_HDR_EXT_FLT_ID) from KREW_DOC_HDR_EXT_FLT_T union
	select max(DOC_HDR_EXT_LONG_ID) from KREW_DOC_HDR_EXT_LONG_T union
	select max(DOC_HDR_EXT_DT_ID) from KREW_DOC_HDR_EXT_DT_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_SRCH_ATTR_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_SRCH_ATTR_S';
     execute IMMEDIATE 'create sequence KREW_SRCH_ATTR_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_EDL_FLD_DMP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_EDL_FLD_DMP_S';
-- tables(columns): KREW_EDL_FLD_DMP_T(EDL_FIELD_DMP_ID)
	select max(EDL_FIELD_DMP_ID) into MaxTblVal from KREW_EDL_FLD_DMP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_EDL_FLD_DMP_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_EDL_FLD_DMP_S';
     execute IMMEDIATE 'create sequence KREW_EDL_FLD_DMP_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREW_DOC_LNK_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_LNK_S';
-- tables(columns): KREW_DOC_LNK_T(DOC_LNK_ID)
	select max(DOC_LNK_ID) into MaxTblVal from KREW_DOC_LNK_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREW_DOC_LNK_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREW_DOC_LNK_S';
     execute IMMEDIATE 'create sequence KREW_DOC_LNK_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_CHNL_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_CHNL_S';
-- tables(columns): KREN_CHNL_T(CHNL_ID)
	select max(CHNL_ID) into MaxTblVal from KREN_CHNL_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_CHNL_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_CHNL_S';
     execute IMMEDIATE 'create sequence KREN_CHNL_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_CHNL_SUBSCRP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_CHNL_SUBSCRP_S';
-- tables(columns): KREN_CHNL_SUBSCRP_T(CHNL_SUBSCRP_ID)
	select max(CHNL_SUBSCRP_ID) into MaxTblVal from KREN_CHNL_SUBSCRP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_CHNL_SUBSCRP_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_CHNL_SUBSCRP_S';
     execute IMMEDIATE 'create sequence KREN_CHNL_SUBSCRP_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_CNTNT_TYP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_CNTNT_TYP_S';
-- tables(columns): KREN_CNTNT_TYP_T(CNTNT_TYP_ID)
	select max(CNTNT_TYP_ID) into MaxTblVal from KREN_CNTNT_TYP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_CNTNT_TYP_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_CNTNT_TYP_S';
     execute IMMEDIATE 'create sequence KREN_CNTNT_TYP_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_MSG_DELIV_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_MSG_DELIV_S';
-- tables(columns): KREN_MSG_DELIV_T(MSG_DELIV_ID)
	select max(MSG_DELIV_ID) into MaxTblVal from KREN_MSG_DELIV_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_MSG_DELIV_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_MSG_DELIV_S';
     execute IMMEDIATE 'create sequence KREN_MSG_DELIV_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_MSG_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_MSG_S';
-- tables(columns): KREN_MSG_T(MSG_ID)
	select max(MSG_ID) into MaxTblVal from KREN_MSG_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_MSG_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_MSG_S';
     execute IMMEDIATE 'create sequence KREN_MSG_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_NTFCTN_MSG_DELIV_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_NTFCTN_MSG_DELIV_S';
-- tables(columns): KREN_NTFCTN_MSG_DELIV_T(NTFCTN_MSG_DELIV_ID)
	select max(NTFCTN_MSG_DELIV_ID) into MaxTblVal from KREN_NTFCTN_MSG_DELIV_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_NTFCTN_MSG_DELIV_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_NTFCTN_MSG_DELIV_S';
     execute IMMEDIATE 'create sequence KREN_NTFCTN_MSG_DELIV_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_NTFCTN_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_NTFCTN_S';
-- tables(columns): KREN_NTFCTN_T(NTFCTN_ID)
	select max(NTFCTN_ID) into MaxTblVal from KREN_NTFCTN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_NTFCTN_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_NTFCTN_S';
     execute IMMEDIATE 'create sequence KREN_NTFCTN_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_PRIO_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_PRIO_S';
-- tables(columns): KREN_PRIO_T(PRIO_ID)
	select max(PRIO_ID) into MaxTblVal from KREN_PRIO_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_PRIO_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_PRIO_S';
     execute IMMEDIATE 'create sequence KREN_PRIO_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_PRODCR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_PRODCR_S';
-- tables(columns): KREN_PRODCR_T(PRODCR_ID)
	select max(PRODCR_ID) into MaxTblVal from KREN_PRODCR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_PRODCR_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_PRODCR_S';
     execute IMMEDIATE 'create sequence KREN_PRODCR_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_DELIV_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_DELIV_S';
-- tables(columns): KREN_RECIP_DELIV_T(RECIP_DELIV_ID)
	select max(RECIP_DELIV_ID) into MaxTblVal from KREN_RECIP_DELIV_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_RECIP_DELIV_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_RECIP_DELIV_S';
     execute IMMEDIATE 'create sequence KREN_RECIP_DELIV_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_LIST_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_LIST_S';
-- tables(columns): KREN_RECIP_LIST_T(RECIP_LIST_ID)
	select max(RECIP_LIST_ID) into MaxTblVal from KREN_RECIP_LIST_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_RECIP_LIST_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_RECIP_LIST_S';
     execute IMMEDIATE 'create sequence KREN_RECIP_LIST_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_PREF_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_PREF_S';
-- tables(columns): KREN_RECIP_PREFS_T(RECIP_PREFS_ID)
	select max(RECIP_PREFS_ID) into MaxTblVal from KREN_RECIP_PREFS_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_RECIP_PREF_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_RECIP_PREF_S';
     execute IMMEDIATE 'create sequence KREN_RECIP_PREF_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_S';
-- tables(columns): KREN_RECIP_T(RECIP_ID)
	select max(RECIP_ID) into MaxTblVal from KREN_RECIP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_RECIP_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_RECIP_S';
     execute IMMEDIATE 'create sequence KREN_RECIP_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_RVWER_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RVWER_S';
-- tables(columns): KREN_RVWER_T(RVWER_ID)
	select max(RVWER_ID) into MaxTblVal from KREN_RVWER_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_RVWER_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_RVWER_S';
     execute IMMEDIATE 'create sequence KREN_RVWER_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

begin
-- sequence: KREN_SNDR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_SNDR_S';
-- tables(columns): KREN_SNDR_T(SNDR_ID)
	select max(SNDR_ID) into MaxTblVal from KREN_SNDR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('Sequence: KREN_SNDR_S');
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
    execute IMMEDIATE 'drop sequence KREN_SNDR_S';
     execute IMMEDIATE 'create sequence KREN_SNDR_S START WITH ' || (MaxTblVal + 1) || ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
   end if;
exception when others then null;
end;

end;
/

DROP SEQUENCE KREW_ACTN_RQST_S;
CREATE SEQUENCE KREW_ACTN_RQST_S MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 2573 NOCACHE ORDER NOCYCLE ;

DROP SEQUENCE KREW_ACTN_TKN_S;
CREATE SEQUENCE KREW_ACTN_TKN_S MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 2356 NOCACHE ORDER NOCYCLE ;