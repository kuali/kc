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
INSERT INTO SEQ_AWARD_SYNC_CHANGE_ID VALUES(NULL)
/
INSERT INTO AWARD_SYNC_CHANGE (AWARD_SYNC_CHANGE_ID,AWARD_ID,CLASS_NAME,ATTRIBUTE_NAME,OBJECT_DESC,DATA_DESC,SYNC_TYPE,SYNC_DESCEND,SYNC_FABRICATED_DESCEND,SYNC_COST_SHARE_DESCEND,XML_CHANGES,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_AWARD_SYNC_CHANGE_ID),(SELECT AWARD_ID FROM AWARD WHERE AWARD_NUMBER = '001002-00001' AND SEQUENCE_NUMBER = 2),'org.kuali.kra.award.home.Award','sponsorCode','Sponsor','000340 : NIH','A','ALL','Y','N',
'<org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>
<className>org.kuali.kra.award.home.Award</className>
<addIfNotFound>true</addIfNotFound>
<keys/>
<values>
<entry>
<string>sponsorCode</string>
<string>000340</string>
</entry>
</values>
</org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>',NOW(),'quickstart',0,UUID())
/
INSERT INTO SEQ_AWARD_SYNC_CHANGE_ID VALUES(NULL)
/
INSERT INTO AWARD_SYNC_CHANGE (AWARD_SYNC_CHANGE_ID,AWARD_ID,CLASS_NAME,ATTRIBUTE_NAME,OBJECT_DESC,DATA_DESC,SYNC_TYPE,SYNC_DESCEND,SYNC_FABRICATED_DESCEND,SYNC_COST_SHARE_DESCEND,XML_CHANGES,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_AWARD_SYNC_CHANGE_ID),(SELECT AWARD_ID FROM AWARD WHERE AWARD_NUMBER = '001002-00001' AND SEQUENCE_NUMBER = 3),'org.kuali.kra.award.home.Award','sponsorCode','Sponsor','000500 : NSF','A','ALL','Y','N',
'<org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>
<className>org.kuali.kra.award.home.Award</className>
<addIfNotFound>true</addIfNotFound>
<keys/>
<values>
<entry>
<string>sponsorCode</string>
<string>000500</string>
</entry>
</values>
</org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>',NOW(),'quickstart',0,UUID())
/
DELIMITER ;
