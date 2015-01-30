--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

set define off 
set sqlblanklines on 
spool KR_SEQ_BS-Oracle-Install.log

create or replace function numeric_val (p_string in varchar2) 
return NUMBER as l_number number;
begin
	if p_string is null
	then
		return 0;
	end if;
    return to_number(REGEXP_REPLACE(p_string, '[^0-9]+', ''));
end;
/

declare
	l_new_seq INTEGER;
begin
    select max(numeric_val(KIM_ATTR_DEFN_ID)) + 1 into l_new_seq from KRIM_ATTR_DEFN_T where numeric_val(KIM_ATTR_DEFN_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_ATTR_DEFN_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
        
    select max(numeric_val(GRP_ID)) + 1 into l_new_seq from KRIM_GRP_T where numeric_val(GRP_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_GRP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    select max(numeric_val(GRP_MBR_ID)) + 1 into l_new_seq from KRIM_GRP_MBR_T where numeric_val(GRP_MBR_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_GRP_MBR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(PERM_TMPL_ID)) + 1 into l_new_seq from KRIM_PERM_TMPL_T where numeric_val(PERM_TMPL_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_PERM_TMPL_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    select max(numeric_val(PERM_ID)) + 1 into l_new_seq from KRIM_PERM_T where numeric_val(PERM_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_PERM_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(ROLE_ID)) + 1 into l_new_seq from KRIM_ROLE_T where numeric_val(ROLE_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(ROLE_MBR_ID)) + 1 into l_new_seq from KRIM_ROLE_MBR_T where numeric_val(ROLE_MBR_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_MBR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select max(numeric_val(ROLE_PERM_ID)) + 1 into l_new_seq from KRIM_ROLE_PERM_T where numeric_val(ROLE_PERM_ID) < 10000;
	if l_new_seq is null then
    	l_new_seq:=1;
    end if;
	execute immediate 'CREATE SEQUENCE KRIM_ROLE_PERM_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

	select max(numeric_val(ID)) + 1 into l_new_seq from 
		((select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_RSP_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000)
		union (select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_DLGN_MBR_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000)
		union (select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_ROLE_MBR_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000)
		union (select max(numeric_val(ATTR_DATA_ID)) as ID from KRIM_PERM_ATTR_DATA_T where numeric_val(ATTR_DATA_ID) < 10000));
	if l_new_seq is null then
    	l_new_seq:=1;
    end if;
	execute immediate 'CREATE SEQUENCE KRIM_ATTR_DATA_ID_BS_S START WITH ' || l_new_seq || 
		' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    select max(numeric_val(KIM_TYP_ID)) + 1 into l_new_seq from KRIM_TYP_T where numeric_val(KIM_TYP_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_TYP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(KIM_TYP_ATTR_ID)) + 1 into l_new_seq from KRIM_TYP_ATTR_T where numeric_val(KIM_TYP_ATTR_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_TYP_ATTR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(RSP_ID)) + 1 into l_new_seq from KRIM_RSP_T where numeric_val(RSP_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_RSP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(ROLE_RSP_ID)) + 1 into l_new_seq from KRIM_ROLE_RSP_T where numeric_val(ROLE_RSP_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
        
    select max(numeric_val(ROLE_RSP_ACTN_ID)) + 1 into l_new_seq from KRIM_ROLE_RSP_ACTN_T where numeric_val(ROLE_RSP_ACTN_ID) < 10000;
    if l_new_seq is null then
    	l_new_seq:=1;
    end if;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ACTN_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/

