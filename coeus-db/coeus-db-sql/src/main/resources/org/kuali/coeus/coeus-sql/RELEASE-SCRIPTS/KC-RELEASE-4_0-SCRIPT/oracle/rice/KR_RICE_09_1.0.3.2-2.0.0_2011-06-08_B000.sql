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

-- make krms_rule_t.prop_id nullable
alter table krms_rule_t modify (prop_id NULL)
/

-- add krms_actn_t.nmspc_cd
alter table krms_actn_t add nmspc_cd varchar2(40) not null
/

-- make krms_agenda_t default to 'Y'
alter table krms_agenda_t modify actv varchar2(1) DEFAULT 'Y'
/

-- make krms_prop_t.typ_id nullable 
alter table krms_prop_t modify (typ_id NULL)
/

-- change krms_rule_t.descr_txt to desc_t for consistency
alter table krms_rule_t rename column descr_txt to desc_txt
/
