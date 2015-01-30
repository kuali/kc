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

