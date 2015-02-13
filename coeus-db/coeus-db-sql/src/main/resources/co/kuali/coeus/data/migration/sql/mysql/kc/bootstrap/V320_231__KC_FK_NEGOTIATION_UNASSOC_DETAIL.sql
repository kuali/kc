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
ALTER TABLE NEGOTIATION_UNASSOC_DETAIL
	ADD ( CONSTRAINT NEGOTIATION_UNASSOC_DETAIL_FK1
	FOREIGN KEY(NEGOTIATION_ID)
	REFERENCES NEGOTIATION(NEGOTIATION_ID)
	)
/
ALTER TABLE NEGOTIATION_UNASSOC_DETAIL
	ADD ( CONSTRAINT NEGOTIATION_UNASSOC_DETAIL_FK2
	FOREIGN KEY(LEAD_UNIT)
	REFERENCES UNIT(UNIT_NUMBER)
	 )
/
ALTER TABLE NEGOTIATION_UNASSOC_DETAIL
	ADD ( CONSTRAINT NEGOTIATION_UNASSOC_DETAIL_FK3
	FOREIGN KEY(SPONSOR_CODE)
	REFERENCES SPONSOR(SPONSOR_CODE)
	 )
/
ALTER TABLE NEGOTIATION_UNASSOC_DETAIL
	ADD ( CONSTRAINT NEGOTIATION_UNASSOC_DETAIL_FK4
	FOREIGN KEY(PRIME_SPONSOR_CODE)
	REFERENCES SPONSOR(SPONSOR_CODE)
	 )
/
ALTER TABLE NEGOTIATION_UNASSOC_DETAIL
	ADD ( CONSTRAINT NEGOTIATION_UNASSOC_DETAIL_FK5
	FOREIGN KEY(SUBAWARD_ORG)
	REFERENCES ORGANIZATION(ORGANIZATION_ID)
	 )
/
DELIMITER ;
