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

SET foreign_key_checks = 0;

DELETE FROM KRIM_PHONE_TYP_T WHERE PHONE_TYP_CD = 'FAX';

-- Copied from rice-1.0.3.2\scripts\upgrades\1.0.3 to 1.0.4\db-updates\oracle\2011-04-13.sql (and corrected)
INSERT INTO KRIM_PHONE_TYP_T (ACTV_IND,DISPLAY_SORT_CD,OBJ_ID,PHONE_TYP_CD,PHONE_TYP_NM,VER_NBR) 
    VALUES ('Y','e','5B97C50B03946110E0404F8189D85213','FAX','Facsimile',1);

SET foreign_key_checks = 1;
