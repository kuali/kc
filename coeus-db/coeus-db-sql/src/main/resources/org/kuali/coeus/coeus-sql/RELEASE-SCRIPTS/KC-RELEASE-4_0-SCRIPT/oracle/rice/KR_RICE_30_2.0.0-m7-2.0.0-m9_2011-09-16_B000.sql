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

-- KULRICE-5360 add KIM entity fields
alter table KRIM_ENTITY_NM_T add TITLE_NM VARCHAR(20)
/
alter table KRIM_ENTITY_NM_T add NOTE_MSG VARCHAR(1024)
/
alter table KRIM_ENTITY_NM_T add NM_CHNG_DT DATE
/

alter table KRIM_ENTITY_ADDR_T add ATTN_LINE VARCHAR(45)
/
alter table KRIM_ENTITY_ADDR_T add ADDR_FMT VARCHAR(256)
/
alter table KRIM_ENTITY_ADDR_T add MOD_DT DATE DEFAULT SYSDATE
/
alter table KRIM_ENTITY_ADDR_T add VALID_DT DATE
/
alter table KRIM_ENTITY_ADDR_T add VALID_IND VARCHAR(1)
/
alter table KRIM_ENTITY_ADDR_T add NOTE_MSG VARCHAR(1024)
/


alter table KRIM_ENTITY_BIO_T add NOTE_MSG VARCHAR(1024)
/
alter table KRIM_ENTITY_BIO_T add GNDR_CHG_CD VARCHAR(20)
/

alter table KRIM_PND_NM_MT add TITLE_NM VARCHAR(20)
/
alter table KRIM_PND_NM_MT add NOTE_MSG VARCHAR(1024)
/
alter table KRIM_PND_NM_MT add NM_CHNG_DT DATE
/

alter table KRIM_PND_ADDR_MT add ATTN_LINE VARCHAR(45)
/
alter table KRIM_PND_ADDR_MT add ADDR_FMT VARCHAR(256)
/
alter table KRIM_PND_ADDR_MT add MOD_DT DATE DEFAULT SYSDATE
/
alter table KRIM_PND_ADDR_MT add VALID_DT DATE
/
alter table KRIM_PND_ADDR_MT add VALID_IND VARCHAR(1)
/
alter table KRIM_PND_ADDR_MT add NOTE_MSG VARCHAR(1024)
/
