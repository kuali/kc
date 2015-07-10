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

INSERT INTO SEQ_ROLODEX_ID VALUES(NULL);

INSERT INTO ROLODEX (ROLODEX_ID, ORGANIZATION, OWNED_BY_UNIT, SPONSOR_ADDRESS_FLAG, ADDRESS_LINE_1, ADDRESS_LINE_2,
CITY, STATE, POSTAL_CODE, COUNTRY_CODE, COUNTY, EMAIL_ADDRESS, FAX_NUMBER, FIRST_NAME, LAST_NAME, PHONE_NUMBER, TITLE,
ACTV_IND, CREATE_USER,UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
values ((SELECT (MAX(ID)) FROM SEQ_ROLODEX_ID), 'University', '000001', 'N', '1375 N Scottsdale Rd, Scottsdale, AZ 85257-3454',
'Suite 480','Scottsdale', 'AZ', '85257-3454', 'USA', 'Maricopa', 'sean.warren@rsmart.com', '602-391-2172','Sean','Warren',
 '480-414-0450', 'OSP Approver', 'Y', 'admin', NOW(), 'admin', 1, UUID());

UPDATE ORGANIZATION SET
ADDRESS = '1375 N Scottsdale Rd, Suite 480, Scottsdale, AZ 85257-3454',
AGENCY_SYMBOL = '75-08-9701',
ANIMAL_WELFARE_ASSURANCE = 'A-3125-01',
CABLE_ADDRESS = 'MITCAM',
CAGE_NUMBER = '56AK9',
COM_GOV_ENTITY_CODE = '80230',
CONGRESSIONAL_DISTRICT = 'AZ-002',
CONTACT_ADDRESS_ID = (SELECT ROLODEX_ID FROM ROLODEX WHERE EMAIL_ADDRESS='sean.warren@rsmart.com'),
COUNTY = 'Maricopa',
DUNS_NUMBER = '113837822',
DUNS_PLUS_FOUR_NUMBER = '1138378220000',
FEDRAL_EMPLOYER_ID = '042103594',
HUMAN_SUB_ASSURANCE = 'FWA00004881',
INCORPORATED_DATE = '2000-09-12',
INCORPORATED_IN = 'Arizona'
WHERE ORGANIZATION_ID = '000001';
