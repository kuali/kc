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

alter table KRIM_PERM_TMPL_T modify NMSPC_CD varchar2(40) not null
/
alter table KRIM_PERM_TMPL_T modify NM varchar2(100) not null
/
alter table KRIM_PERM_TMPL_T add constraint KRIM_PERM_TMPL_TC1 unique (NM, NMSPC_CD)
/

alter table KRIM_RSP_TMPL_T modify NMSPC_CD varchar2(40) not null
/
alter table KRIM_RSP_TMPL_T modify NM varchar2(100) not null
/
alter table KRIM_RSP_TMPL_T add constraint KRIM_RSP_TMPL_TC1 unique (NM, NMSPC_CD)
/
