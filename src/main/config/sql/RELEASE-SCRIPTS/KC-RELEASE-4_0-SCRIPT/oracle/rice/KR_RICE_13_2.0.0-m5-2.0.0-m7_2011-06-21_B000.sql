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
