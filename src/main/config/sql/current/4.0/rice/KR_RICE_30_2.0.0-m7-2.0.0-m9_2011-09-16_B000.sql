--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
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
