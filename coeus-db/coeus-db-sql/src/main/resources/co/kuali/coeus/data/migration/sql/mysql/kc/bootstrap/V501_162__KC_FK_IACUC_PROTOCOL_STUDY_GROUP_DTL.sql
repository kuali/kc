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
ALTER TABLE IACUC_PROTOCOL_STUDY_GROUP_DTL 
ADD CONSTRAINT FK_STUD_GRP_HDR_PAIN_CAT_CODE 
FOREIGN KEY (MAX_PAIN_CATEGORY_CODE) 
REFERENCES IACUC_PAIN_CATEGORY (PAIN_CATEGORY_CODE)
/

ALTER TABLE IACUC_PROTOCOL_STUDY_GROUP_DTL 
ADD CONSTRAINT FK_IACUC_PROT_STUD_GRP_HDR_ID 
FOREIGN KEY (IACUC_PROTO_STUDY_GRP_HDR_ID) 
REFERENCES IACUC_PROTOCOL_STUDY_GROUP_HDR (IACUC_PROTO_STUDY_GRP_HDR_ID)
/

ALTER TABLE IACUC_PROTOCOL_STUDY_GROUP_DTL 
ADD CONSTRAINT FK_PROT_STUD_GRP_HDR_SPEC_CODE 
FOREIGN KEY (SPECIES_CODE) 
REFERENCES IACUC_SPECIES (SPECIES_CODE)
/

DELIMITER ;
