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





--
--     KULRICE-9179: KRAD_MSG_T not in client dataset upgrade scripts
--

CREATE TABLE IF NOT EXISTS krad_msg_t
(
    nmspc_cd VARCHAR(20) NOT NULL,
    cmpnt_cd VARCHAR(100) NOT NULL,
    msg_key VARCHAR(100) NOT NULL,
    loc VARCHAR(255) NOT NULL,
    obj_id VARCHAR(36) NOT NULL,
    ver_nbr DECIMAL(8) NOT NULL DEFAULT 1,
    msg_desc VARCHAR(255),
    txt VARCHAR(4000),
    PRIMARY KEY (nmspc_cd,cmpnt_cd,msg_key,loc),
    UNIQUE krad_msg_tc0(obj_id)
)
/
--
-- KULRICE-9152: PK for krad_msg_t is too long for MySQL 5.1
--

ALTER TABLE krad_msg_t MODIFY loc varchar(80) not null
/
DELIMITER ;

