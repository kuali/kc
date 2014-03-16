set define off 
set sqlblanklines on 
spool KR_SEQ_BS-Oracle-Install.log

create or replace function numeric_val (p_string in varchar2) 
return NUMBER as l_number number;
begin
    return to_number(REGEXP_REPLACE(p_string, '[^0-9]+', ''));
end;
/

declare
	l_new_seq INTEGER;
begin
    select max(numeric_val(KIM_ATTR_DEFN_ID)) + 1 into l_new_seq from KRIM_ATTR_DEFN_T where numeric_val(KIM_ATTR_DEFN_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ATTR_DEFN_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(GRP_ID)) + 1 into l_new_seq from KRIM_GRP_T where numeric_val(GRP_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_GRP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    select max(numeric_val(GRP_MBR_ID)) + 1 into l_new_seq from KRIM_GRP_MBR_T where numeric_val(GRP_MBR_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_GRP_MBR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(PERM_TMPL_ID)) + 1 into l_new_seq from KRIM_PERM_TMPL_T where numeric_val(PERM_TMPL_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_PERM_TMPL_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    select max(numeric_val(PERM_ID)) + 1 into l_new_seq from KRIM_PERM_T where numeric_val(PERM_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_PERM_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(ROLE_ID)) + 1 into l_new_seq from KRIM_ROLE_T where numeric_val(ROLE_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(ROLE_MBR_ID)) + 1 into l_new_seq from KRIM_ROLE_MBR_T where numeric_val(ROLE_MBR_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_MBR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(RSP_ID)) + 1 into l_new_seq from KRIM_RSP_T where numeric_val(RSP_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_RSP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select max(numeric_val(ROLE_PERM_ID)) + 1 into l_new_seq from KRIM_ROLE_PERM_T where numeric_val(ROLE_PERM_ID) < 10000;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_PERM_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(ROLE_RSP_ID)) + 1 into l_new_seq from KRIM_ROLE_RSP_T where numeric_val(ROLE_RSP_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(ROLE_RSP_ACTN_ID)) + 1 into l_new_seq from KRIM_ROLE_RSP_ACTN_T where numeric_val(ROLE_RSP_ACTN_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ACTN_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select max(numeric_val(ID)) + 1 into l_new_seq from 
		((select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_RSP_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000)
		union (select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_DLGN_MBR_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000)
		union (select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_ROLE_MBR_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000)
		union (select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_PERM_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000));
	execute immediate 'CREATE SEQUENCE KRIM_ATTR_DATA_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(KIM_TYP_ID)) + 1 into l_new_seq from KRIM_TYP_T where numeric_val(KIM_TYP_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_TYP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(KIM_TYP_ATTR_ID)) + 1 into l_new_seq from KRIM_TYP_ATTR_T where numeric_val(KIM_TYP_ATTR_ID) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_TYP_ATTR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/

