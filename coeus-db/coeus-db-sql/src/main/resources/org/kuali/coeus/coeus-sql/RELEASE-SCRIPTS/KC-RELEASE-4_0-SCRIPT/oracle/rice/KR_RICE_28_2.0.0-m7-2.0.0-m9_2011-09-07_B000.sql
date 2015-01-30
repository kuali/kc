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

-- KULRICE-5360 rename KIM entity fields
alter table KRIM_ENTITY_NM_T rename column TITLE_NM to PREFIX_NM
/

alter table KRIM_ENTITY_BIO_T rename column BIRTH_STATE_CD to BIRTH_STATE_PVC_CD
/

alter table KRIM_ENTITY_ADDR_T rename column POSTAL_STATE_CD to STATE_PVC_CD
/
alter table KRIM_ENTITY_ADDR_T rename column CITY_NM to CITY
/

alter table KRIM_PND_NM_MT rename column TITLE_NM to PREFIX_NM
/
alter table KRIM_PND_ADDR_MT rename column POSTAL_STATE_CD to STATE_PVC_CD
/
alter table KRIM_PND_ADDR_MT rename column CITY_NM to CITY
/
