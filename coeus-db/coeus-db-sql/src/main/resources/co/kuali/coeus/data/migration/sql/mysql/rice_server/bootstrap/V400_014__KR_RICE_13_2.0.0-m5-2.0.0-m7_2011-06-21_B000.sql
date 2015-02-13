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

DELIMITER /

alter table KRIM_PERM_TMPL_T change column NMSPC_CD NMSPC_CD varchar(40) not null
/
alter table KRIM_PERM_TMPL_T change column NM NM varchar(100) not null
/
alter table KRIM_PERM_TMPL_T add constraint krim_perm_tmpl_tc1 unique (NM, NMSPC_CD)
/

alter table KRIM_RSP_TMPL_T change column NMSPC_CD NMSPC_CD varchar(40) not null
/
alter table KRIM_RSP_TMPL_T change column NM NM varchar(100) not null
/
alter table KRIM_RSP_TMPL_T add constraint krim_rsp_tmpl_tc1 unique (NM, NMSPC_CD)
/
DELIMITER ;
