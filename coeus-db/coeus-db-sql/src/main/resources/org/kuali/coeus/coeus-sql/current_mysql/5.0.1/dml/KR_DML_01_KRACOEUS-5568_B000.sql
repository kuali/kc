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
UPDATE KRCR_PARM_T
SET VAL = '0',
    PARM_DESC_TXT = 'When the sponsor or the prime sponsor has the sponsor type specified in this parameter and the KFS parameter FEDERAL_ONLY_IND is "Y", the Effort reporting Document is routed.  Also is used in proposal for checking grants.gov checking.'
WHERE PARM_NM = 'FEDERAL_SPONSOR_TYPE_CODE' AND NMSPC_CD = 'KC-AWARD'
/

DELIMITER ;
