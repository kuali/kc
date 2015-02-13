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
update NEGOTIATION set 
	ASSOCIATED_DOCUMENT_ID = (select SUBAWARD_CODE from SUBAWARD where SUBAWARD_ID = NEGOTIATION.ASSOCIATED_DOCUMENT_ID) 
	where NEGOTIATION_ASSC_TYPE_ID = 
		(select NEGOTIATION_ASSC_TYPE_ID from NEGOTIATION_ASSOCIATION_TYPE where NEGOTIATION_ASSC_TYPE_CODE = 'SWD')
/

DELIMITER ;
