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

create or replace function is_numeric (p_string in varchar2) 
return NUMBER as l_number number;
begin
    l_number := p_string;
    return 1;
exception
    when others then
    return 0;
end;
/

declare
    l_new_seq INTEGER;
begin
    execute immediate 'DROP SEQUENCE KRIM_ATTR_DEFN_ID_BS_S';
    select nvl(max(to_number(KIM_ATTR_DEFN_ID)),'1') + 1 into l_new_seq from KRIM_ATTR_DEFN_T where decode(is_numeric(KIM_ATTR_DEFN_ID),1,to_number(KIM_ATTR_DEFN_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ATTR_DEFN_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_GRP_ID_BS_S';
    select nvl(max(to_number(GRP_ID)),'1') + 1 into l_new_seq from KRIM_GRP_T where decode(is_numeric(GRP_ID),1,to_number(GRP_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_GRP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    execute immediate 'DROP SEQUENCE KRIM_GRP_MBR_ID_BS_S';
    select nvl(max(to_number(GRP_MBR_ID)),'1') + 1 into l_new_seq from KRIM_GRP_MBR_T where decode(is_numeric(GRP_MBR_ID),1,to_number(GRP_MBR_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_GRP_MBR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_PERM_TMPL_ID_BS_S';
    select nvl(max(to_number(PERM_TMPL_ID)),'1') + 1 into l_new_seq from KRIM_PERM_TMPL_T where decode(is_numeric(PERM_TMPL_ID),1,to_number(PERM_TMPL_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_PERM_TMPL_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    execute immediate 'DROP SEQUENCE KRIM_PERM_ID_BS_S';
    select nvl(max(to_number(PERM_ID)),'1') + 1 into l_new_seq from KRIM_PERM_T where decode(is_numeric(PERM_ID),1,to_number(PERM_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_PERM_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_ROLE_ID_BS_S';
    select nvl(max(to_number(ROLE_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_T where decode(is_numeric(ROLE_ID),1,to_number(ROLE_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_ROLE_MBR_ID_BS_S';
    select nvl(max(to_number(ROLE_MBR_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_MBR_T where decode(is_numeric(ROLE_MBR_ID),1,to_number(ROLE_MBR_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_MBR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_RSP_ID_BS_S';
    select nvl(max(to_number(RSP_ID)),'1') + 1 into l_new_seq from KRIM_RSP_T where decode(is_numeric(RSP_ID),1,to_number(RSP_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_RSP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    execute immediate 'DROP SEQUENCE KRIM_ROLE_PERM_ID_BS_S';
    select nvl(max(to_number(ROLE_PERM_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_PERM_T where decode(is_numeric(ROLE_PERM_ID),1,to_number(ROLE_PERM_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_PERM_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_ROLE_RSP_ID_BS_S';
    select nvl(max(to_number(ROLE_RSP_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_T where decode(is_numeric(ROLE_RSP_ID),1,to_number(ROLE_RSP_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_ROLE_RSP_ACTN_ID_BS_S';
    select nvl(max(to_number(ROLE_RSP_ACTN_ID)),'1') + 1 into l_new_seq from KRIM_ROLE_RSP_ACTN_T where decode(is_numeric(ROLE_RSP_ACTN_ID),1,to_number(ROLE_RSP_ACTN_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_ROLE_RSP_ACTN_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';

    execute immediate 'DROP SEQUENCE KRIM_ATTR_DATA_ID_BS_S';
    select nvl(max(to_number(ID)),'1') + 1 into l_new_seq from 
        ((select max(to_number(ATTR_DATA_ID)) as ID from KRIM_RSP_ATTR_DATA_T where decode(is_numeric(ATTR_DATA_ID),1,to_number(ATTR_DATA_ID)) < 10000)
        union (select max(to_number(ATTR_DATA_ID)) as ID from KRIM_DLGN_MBR_ATTR_DATA_T where decode(is_numeric(ATTR_DATA_ID),1,to_number(ATTR_DATA_ID)) < 10000)
        union (select max(to_number(ATTR_DATA_ID)) as ID from KRIM_ROLE_MBR_ATTR_DATA_T where decode(is_numeric(ATTR_DATA_ID),1,to_number(ATTR_DATA_ID)) < 10000)
        union (select max(to_number(ATTR_DATA_ID)) as ID from KRIM_PERM_ATTR_DATA_T where decode(is_numeric(ATTR_DATA_ID),1,to_number(ATTR_DATA_ID)) < 10000));
    execute immediate 'CREATE SEQUENCE KRIM_ATTR_DATA_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_TYP_ID_BS_S';
    select nvl(max(to_number(KIM_TYP_ID)),'1') + 1 into l_new_seq from KRIM_TYP_T where decode(is_numeric(KIM_TYP_ID),1,to_number(KIM_TYP_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_TYP_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
    
    execute immediate 'DROP SEQUENCE KRIM_TYP_ATTR_ID_BS_S';
    select nvl(max(to_number(KIM_TYP_ATTR_ID)),'1') + 1 into l_new_seq from KRIM_TYP_ATTR_T where decode(is_numeric(KIM_TYP_ATTR_ID),1,to_number(KIM_TYP_ATTR_ID)) < 10000;
    execute immediate 'CREATE SEQUENCE KRIM_TYP_ATTR_ID_BS_S START WITH ' || l_new_seq || 
        ' MAXVALUE 99999999 MINVALUE 1 NOCYCLE NOCACHE NOORDER';
end;
/

