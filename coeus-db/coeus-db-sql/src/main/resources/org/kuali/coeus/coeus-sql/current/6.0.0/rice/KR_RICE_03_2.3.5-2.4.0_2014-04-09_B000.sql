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

-- This alters the size of the address fields on the address and person maintenance document tables so they are a bit longer and match
ALTER TABLE krim_entity_addr_t MODIFY (addr_line_1 VARCHAR2(128))
/
ALTER TABLE krim_entity_addr_t MODIFY (addr_line_2 VARCHAR2(128))
/
ALTER TABLE krim_entity_addr_t MODIFY (addr_line_3 VARCHAR2(128))
/

ALTER TABLE krim_pnd_addr_mt MODIFY (addr_line_1 VARCHAR2(128))
/
ALTER TABLE krim_pnd_addr_mt MODIFY (addr_line_2 VARCHAR2(128))
/
ALTER TABLE krim_pnd_addr_mt MODIFY (addr_line_3 VARCHAR2(128))
/
