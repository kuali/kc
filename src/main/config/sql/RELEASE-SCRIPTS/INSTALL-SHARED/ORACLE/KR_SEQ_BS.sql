set define off 
set sqlblanklines on 
spool KR_SEQ_BS-Oracle-Install.log

declare
	l_new_seq INTEGER;
begin
	select nvl(max(ROLE_MBR_ID),'1') + 1 into l_new_seq from KRIM_ROLE_MBR_T;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_MBR_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(PERM_ID),'1') + 1 into l_new_seq from KRIM_PERM_T;
	execute immediate 'CREATE SEQUENCE KRIM_PERM_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(ROLE_PERM_ID),'1') + 1 into l_new_seq from KRIM_ROLE_PERM_T;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_PERM_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(ID),'1') + 1 into l_new_seq from 
		((select max(ATTR_DATA_ID) as ID from KRIM_RSP_ATTR_DATA_T) 
		union (select max(ATTR_DATA_ID) as ID from KRIM_DLGN_MBR_ATTR_DATA_T)
		union (select max(ATTR_DATA_ID) as ID from KRIM_ROLE_MBR_ATTR_DATA_T)
		union (select max(ATTR_DATA_ID) as ID from KRIM_PERM_ATTR_DATA_T));
	execute immediate 'CREATE SEQUENCE KRIM_ATTR_DATA_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(GRP_MBR_ID),'1') + 1 into l_new_seq from KRIM_GRP_MBR_T;
	execute immediate 'CREATE SEQUENCE KRIM_GRP_MBR_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
		
	select nvl(max(ROLE_RSP_ID),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_T;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
		
	select nvl(max(ROLE_ID),'1') + 1 into l_new_seq from KRIM_ROLE_T;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
		
	select nvl(max(ROLE_RSP_ACTN_ID),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_ACTN_T;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ACTN_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/

