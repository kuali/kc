--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

INSERT INTO EPS_PROP_PER_DOC_TYPE (DOCUMENT_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ('6','NSF_Collaborator','admin',SYSDATE,sys_guid(),1);

INSERT INTO NARRATIVE_TYPE (NARRATIVE_TYPE_CODE,DESCRIPTION,NARRATIVE_TYPE_GROUP,ALLOW_MULTIPLE,SYSTEM_GENERATED,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
VALUES ('148','NSF_Collaborator','P','Y','Y','admin',SYSDATE,sys_guid(),1);
