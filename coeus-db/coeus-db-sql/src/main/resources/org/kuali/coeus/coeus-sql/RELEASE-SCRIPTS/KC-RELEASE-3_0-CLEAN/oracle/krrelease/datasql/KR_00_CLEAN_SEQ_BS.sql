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
spool KR_CLEAN_SEQ_BS-Oracle-Install.log

drop sequence KRIM_ATTR_DEFN_ID_BS_S;
drop sequence KRIM_GRP_ID_BS_S;
drop sequence KRIM_GRP_MBR_ID_BS_S;
drop sequence KRIM_PERM_TMPL_ID_BS_S;
drop sequence KRIM_PERM_ID_BS_S;
drop sequence KRIM_ROLE_ID_BS_S;
drop sequence KRIM_ROLE_MBR_ID_BS_S;
drop sequence KRIM_RSP_ID_BS_S;
drop sequence KRIM_ROLE_PERM_ID_BS_S;
drop sequence KRIM_ROLE_RSP_ID_BS_S;
drop sequence KRIM_ROLE_RSP_ACTN_ID_BS_S;
drop sequence KRIM_ATTR_DATA_ID_BS_S;
drop sequence KRIM_TYP_ID_BS_S;
drop sequence KRIM_TYP_ATTR_ID_BS_S;

drop function is_numeric;
