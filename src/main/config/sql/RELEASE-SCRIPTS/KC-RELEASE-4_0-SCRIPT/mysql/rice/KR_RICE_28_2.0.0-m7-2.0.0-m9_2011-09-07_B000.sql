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

-- KULRICE-5360 rename KIM entity fields
ALTER TABLE KRIM_ENTITY_NM_T CHANGE TITLE_NM PREFIX_NM VARCHAR(20)
/

ALTER TABLE KRIM_ENTITY_BIO_T  CHANGE BIRTH_STATE_CD BIRTH_STATE_PVC_CD VARCHAR(2)
/

ALTER TABLE KRIM_ENTITY_ADDR_T CHANGE POSTAL_STATE_CD STATE_PVC_CD VARCHAR(2)
/
ALTER TABLE KRIM_ENTITY_ADDR_T CHANGE CITY_NM CITY VARCHAR(30)
/

ALTER TABLE KRIM_PND_NM_MT CHANGE TITLE_NM PREFIX_NM VARCHAR(20)
/

ALTER TABLE KRIM_PND_ADDR_MT CHANGE POSTAL_STATE_CD STATE_PVC_CD VARCHAR(2)
/

ALTER TABLE KRIM_PND_ADDR_MT CHANGE CITY_NM CITY VARCHAR(30)
/
DELIMITER ;
