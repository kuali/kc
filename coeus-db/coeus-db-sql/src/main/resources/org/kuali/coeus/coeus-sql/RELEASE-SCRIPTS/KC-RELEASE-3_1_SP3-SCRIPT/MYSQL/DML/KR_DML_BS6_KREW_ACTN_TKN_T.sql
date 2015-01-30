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

-- KEW data for Proposal Development Person Certification Questionnaire

INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Proposal Person Certification'),'admin','S',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);
INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_TKN_T (ACTN_TKN_ID,DOC_HDR_ID,PRNCPL_ID,ACTN_CD,ACTN_DT,DOC_VER_NBR,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'Proposal Person Certification'),'admin','C',STR_TO_DATE('20100708','%Y%m%d'),1,1,1);
  
COMMIT;
