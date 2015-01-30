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

INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4101,'KC Award - Sync Descendants, 300120','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',10006,10117,'A',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),'ROUTED_BY_USER',SYSDATE,1)
/
INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4102,'KC Award - Created by Award 010002-00001 Ver 2','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,10121,'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,SYSDATE,1)
/
INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4103,'KC Award - Created by Award 010002-00001 Ver 2','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,10122,'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,SYSDATE,1)
/
INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4104,'KC Award - Created by Award 010002-00001 Ver 2','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,10123,'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,SYSDATE,1)
/
INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4105,'KC Award - Created by Award 010002-00001 Ver 2','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,10124,'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,SYSDATE,1)
/
INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4106,'KC Award - Created by Award 010002-00001 Ver 2','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,10125,'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,SYSDATE,1)
/
INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4107,'KC Award - Created by Award 010002-00001 Ver 2','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,10126,'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,SYSDATE,1)
/
INSERT INTO KREW_OUT_BOX_ITM_T (ACTN_ITM_ID,DOC_HDR_ID,DOC_HDR_TTL,DOC_TYP_LBL,DOC_HDLR_URL,DOC_TYP_NM,RSP_ID,ACTN_RQST_ID,RQST_CD,PRNCPL_ID,ROLE_NM,ASND_DT,VER_NBR) 
    VALUES (KREW_OUT_BOX_ITM_S.NEXTVAL,4108,'KC Award - Created by Award 010002-00001 Ver 2','KC Award','${kuali.docHandler.url.prefix}/awardHome.do?methodToCall=docHandler','AwardDocument',-3,10127,'C',(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),null,SYSDATE,1)
/
