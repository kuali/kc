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

-- make krms_rule_t.prop_id nullable
alter table krms_rule_t change column prop_id prop_id varchar(40) DEFAULT NULL AFTER typ_id
/

-- add krms_actn_t.nmspc_cd
alter table krms_actn_t add column nmspc_cd varchar(40) NOT NULL after nm
/

-- make krms_agenda_t default to 'Y'
alter table krms_agenda_t change column actv actv varchar(1) DEFAULT 'Y' AFTER typ_id
/

-- make krms_prop_t.typ_id nullable 
alter table krms_prop_t change column typ_id typ_id varchar(40) DEFAULT NULL AFTER desc_txt
/

-- change krms_rule_t.descr_txt to desc_t for consistency
alter table krms_rule_t change column descr_txt desc_txt varchar(4000) DEFAULT NULL AFTER nm
/
DELIMITER ;
