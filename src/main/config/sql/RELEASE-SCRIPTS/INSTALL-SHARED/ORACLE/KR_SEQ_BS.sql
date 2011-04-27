set define off 
set sqlblanklines on 
spool KR_SEQ_BS-Oracle-Install.log

declare
	l_new_seq INTEGER;
begin
	select nvl(max(to_number(ROLE_MBR_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_MBR_T where to_number(ROLE_MBR_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_MBR_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(to_number(PERM_ID)),'1') + 1 into l_new_seq from KRIM_PERM_T where to_number(PERM_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_PERM_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(to_number(ROLE_PERM_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_PERM_T where to_number(ROLE_PERM_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_PERM_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(to_number(ID)),'1') + 1 into l_new_seq from 
		((select max(to_number(ATTR_DATA_ID)) as ID from KRIM_RSP_ATTR_DATA_T where to_number(ATTR_DATA_ID) < 10000) 
		union (select max(to_number(ATTR_DATA_ID)) as ID from KRIM_DLGN_MBR_ATTR_DATA_T where to_number(ATTR_DATA_ID) < 10000)
		union (select max(to_number(ATTR_DATA_ID)) as ID from KRIM_ROLE_MBR_ATTR_DATA_T where to_number(ATTR_DATA_ID) < 10000)
		union (select max(to_number(ATTR_DATA_ID)) as ID from KRIM_PERM_ATTR_DATA_T where to_number(ATTR_DATA_ID) < 10000));
	execute immediate 'CREATE SEQUENCE KRIM_ATTR_DATA_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
	
	select nvl(max(to_number(GRP_ID)),'1') + 1 into l_new_seq from KRIM_GRP_T where to_number(GRP_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_GRP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select nvl(max(to_number(GRP_MBR_ID)),'1') + 1 into l_new_seq from KRIM_GRP_MBR_T where to_number(GRP_MBR_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_GRP_MBR_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
		
	select nvl(max(to_number(ROLE_RSP_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_T where to_number(ROLE_RSP_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
		
	select nvl(max(to_number(ROLE_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_T where to_number(ROLE_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
		
	select nvl(max(to_number(ROLE_RSP_ACTN_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_ACTN_T where to_number(ROLE_RSP_ACTN_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ACTN_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/

