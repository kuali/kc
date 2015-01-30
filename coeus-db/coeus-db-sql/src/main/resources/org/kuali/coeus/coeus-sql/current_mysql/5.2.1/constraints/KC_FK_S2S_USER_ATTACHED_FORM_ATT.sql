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

ALTER TABLE S2S_USER_ATTACHED_FORM_ATT 
ADD CONSTRAINT FK1_S2S_USER_ATTACHED_FORM_ATT 
FOREIGN KEY (S2S_USER_ATTACHED_FORM_ID) 
REFERENCES S2S_USER_ATTACHED_FORM (S2S_USER_ATTACHED_FORM_ID)
ON DELETE CASCADE
/
ALTER TABLE S2S_USER_ATTD_FORM_ATT_FILE 
ADD CONSTRAINT FK1_S2S_USR_ATD_FRM_ATT_FIL
FOREIGN KEY (S2S_USER_ATTACHED_FORM_ATT_ID) 
REFERENCES S2S_USER_ATTACHED_FORM_ATT (S2S_USER_ATTACHED_FORM_ATT_ID) ON DELETE CASCADE
/
DELIMITER ;
