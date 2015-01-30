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
INSERT INTO KREW_ACTN_ITM_S VALUES(NULL)
/
INSERT INTO KREW_ACTN_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KREW_ACTN_ITM_S),4091,'KC Award - *****PLACEHOLDER*****','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,(SELECT ACTN_RQST_ID FROM KREW_ACTN_RQST_T WHERE DOC_HDR_ID = 4091 AND ACTN_RQST_CD = 'C' AND PRNCPL_ID = (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart')),'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,NOW(),1)
/
INSERT INTO KREW_ACTN_ITM_S VALUES(NULL)
/
INSERT INTO KREW_ACTN_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KREW_ACTN_ITM_S),4109,'KC Award - Sync Descendants, 300120','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',10006,(SELECT ACTN_RQST_ID FROM KREW_ACTN_RQST_T WHERE DOC_HDR_ID = 4109 AND ACTN_RQST_CD = 'A' AND PRNCPL_ID = (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart')),'A',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),'ROUTED_BY_USER',NOW(),1)
/
DELIMITER ;
