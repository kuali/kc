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
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/additionalinformation.htm' WHERE PARM_NM = 'protocolAdditionalInformationHelpUrl'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/addpersonnelattachment.htm' WHERE PARM_NM = 'protocolAddPersonnelAttachmentHelpUrl'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/addprotocolattachment.htm' WHERE PARM_NM = 'protocolAddProtocolAttachmentHelpUrl'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/customdata3.htm' WHERE PARM_NM = 'protocolCustomDataHelp'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/notes2.htm' WHERE PARM_NM = 'protocolNotesHelpUrl'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/permissions2.htm' WHERE PARM_NM = 'protocolPermissionsHelp'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/persondetails1.htm' WHERE PARM_NM = 'protocolPersonnelHelpUrl'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/requiredfieldsforsavingdocument1.htm' WHERE PARM_NM = 'protocolRequiredFieldsHelpUrl'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/specialreview3.htm' WHERE PARM_NM = 'protocolSpecialReviewHelp'
/
UPDATE KRCR_PARM_T SET VAL = 'default.htm?turl=Documents/statusdates.htm' WHERE PARM_NM = 'protocolStatusAndDatesHelpUrl'
/
COMMIT
/
DELIMITER ;
