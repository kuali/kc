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
CREATE TABLE NEGOTIATION_NOTIFICATION
(
      NOTIFICATION_ID DECIMAL(20)
        , NOTIFICATION_TYPE_ID DECIMAL(6) NOT NULL
        , DOCUMENT_NUMBER VARCHAR(40) NOT NULL
        , OWNING_DOCUMENT_ID_FK DECIMAL(12) NOT NULL
        , RECIPIENTS VARCHAR(1000)
        , SUBJECT VARCHAR(1000)
        , MESSAGE VARCHAR(4000)
        , UPDATE_USER VARCHAR(60) NOT NULL
        , UPDATE_TIMESTAMP DATE NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , OBJ_ID VARCHAR(36) NOT NULL
)
/
ALTER TABLE NEGOTIATION_NOTIFICATION
    ADD CONSTRAINT NEGOTIATION_NOTIFICATIONP1
PRIMARY KEY (NOTIFICATION_ID)
/

DELIMITER ;
