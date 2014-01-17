DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
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
