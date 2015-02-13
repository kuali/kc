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
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2001','KC2000',1,'Nasa_PIandAORSupplementalDataSheet Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2001','KC2001','GrantsGov Form Name','Nasa_PIandAORSupplementalDataSheet-V1.0',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC141','KC-PD','NasaPIandAORSupplementalDataSheetV1_0 (Q IDs 110, 111, 112,113)',null,'Y',1,'NasaPIandAORSupplementalDataSheetV1_0 (Q IDs 110, 111, 112,113)')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2001','KC141',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2001','NasaPIandAORSupplementalDataSheetV1_0','S','KC141',1)
/
update KRMS_RULE_T set PROP_ID='KC2001' where RULE_ID='KC141'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2001','KC2001','KC2001','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2002','KC2001','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2003','KC2001','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2002','KC2000',1,'SF424 Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2002','KC2002','GrantsGov Form Name','SF424-V1.0,SF424-V2.0',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC142','KC-PD','RR and SF424 Overlap (Q IDs 129, 130, 31) executive order, date, review',null,'Y',1,'RR and SF424 Overlap (Q IDs 129, 130, 31) executive order, date, review')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2002','KC142',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2002','RR and SF424 forms (rr 1-1, 1-2) (sf 1-0, 2-0)','S','KC142',1)
/
update KRMS_RULE_T set PROP_ID='KC2002' where RULE_ID='KC142'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2004','KC2002','KC2002','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2005','KC2002','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2006','KC2002','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2003','KC2000',1,'ED_SF424_Supplement Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2003','KC2003','GrantsGov Form Name','ED_SF424_Supplement-V1.1',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC143','KC-PD','EDSF424SupplementV1_1(Q id 133)',null,'Y',1,'EDSF424SupplementV1_1(Q id 133)')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2003','KC143',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2003','EDSF424SupplementV1_1','S','KC143',1)
/
update KRMS_RULE_T set PROP_ID='KC2003' where RULE_ID='KC143'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2007','KC2003','KC2003','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2008','KC2003','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2009','KC2003','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2004','KC2000',1,'RR_SF424 Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2004','KC2004','GrantsGov Form Name','RR_SF424-V1.1,RR_SF424_1_2-V1.2',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC144','KC-PD','RR SF424 V1_2, 1_1 (Q IDs 128, 111) Other agencies, which',null,'Y',1,'RR SF424 V1_2, 1_1 (Q IDs 128, 111) Other agencies, which')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2004','KC144',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2004','RR SF424 V1_2, 1_1','S','KC144',1)
/
update KRMS_RULE_T set PROP_ID='KC2004' where RULE_ID='KC144'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2010','KC2004','KC2004','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2011','KC2004','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2012','KC2004','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2005','KC2000',1,'PHS398_Checklist 1.3 Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2005','KC2005','GrantsGov Form Name','PHS398_Checklist_1_3-V1.3',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC145','KC-PD','PHS398ChecklistV1_3 (Q ID 121) gvt disclose title',null,'Y',1,'PHS398ChecklistV1_3 (Q ID 121) gvt disclose title')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2005','KC145',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2005','PHS398ChecklistV1_3','S','KC145',1)
/
update KRMS_RULE_T set PROP_ID='KC2005' where RULE_ID='KC145'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2013','KC2005','KC2005','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2014','KC2005','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2015','KC2005','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2006','KC2000',1,'PHS398_CoverPageSupplement 1.4 Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2006','KC2006','GrantsGov Form Name','PHS398_CoverPageSupplement_1_4',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC146','KC-PD','PHS398CvrPgSupOverlap V1_4, 1_3 (Q IDs 2, 3, 4, 5, 6, & 7)',null,'Y',1,'PHS398CvrPgSupOverlap V1_4, 1_3 (Q IDs 2, 3, 4, 5, 6, & 7)')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2006','KC146',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2006','PHS398CoverPageSupplementV1_4','S','KC146',1)
/
update KRMS_RULE_T set PROP_ID='KC2006' where RULE_ID='KC146'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2016','KC2006','KC2006','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2017','KC2006','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2018','KC2006','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2007','KC2000',1,'RR_OtherProjectInfo Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2007','KC2007','GrantsGov Form Name','RR_OtherProjectInfo_1_2-V1.2,RR_OtherProjectInfo_1_3-V1.3',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC147','KC-PD','RROtherProjectInfoV1_2, 1_3 (Q IDs 125, 107) Historic place, explain',null,'Y',1,'RROtherProjectInfoV1_2, 1_3 (Q IDs 125, 107) Historic place, explain')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2007','KC147',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2007','RROtherProjectInfoV1_2, 1_3','S','KC147',1)
/
update KRMS_RULE_T set PROP_ID='KC2007' where RULE_ID='KC147'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2019','KC2007','KC2007','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2020','KC2007','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2021','KC2007','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2008','KC2000',1,'PHS398_CoverPageSupplement 1.3 Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2008','KC2008','GrantsGov Form Name','PHS398_CoverPageSupplement_1_3-V1.3',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC148','KC-PD','PHS398CoverPageSupplementV1_3 (Q ID 132) new NIH Investigator',null,'Y',1,'PHS398CoverPageSupplementV1_3 (Q ID 132) new NIH Investigator')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2008','KC148',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2008','PHS398CoverPageSupplementV1_3','S','KC148',1)
/
update KRMS_RULE_T set PROP_ID='KC2008' where RULE_ID='KC148'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2022','KC2008','KC2008','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2023','KC2008','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2024','KC2008','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2009','KC2000',1,'RR_OtherProjectInfo Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2009','KC2009','GrantsGov Form Name','RR_OtherProjectInfo-V1.1,RR_OtherProjectInfo_1_2-V1.2,RR_OtherProjectInfo_1_3-V1.3',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC149','KC-PD','RROtherProjectInfo ALL overlap (Q IDs 122, 123,124, 126, 127,107)',null,'Y',1,'RROtherProjectInfo ALL overlap (Q IDs 122, 123,124, 126, 127,107) proprietary, environmental, international')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2009','KC149',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2009','RROtherProjectInfoV1_1, 1-2, 1-3','S','KC149',1)
/
update KRMS_RULE_T set PROP_ID='KC2009' where RULE_ID='KC149'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2025','KC2009','KC2009','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2026','KC2009','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2027','KC2009','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2010','KC2000',1,'NASA_OtherProjectInformation Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2010','KC2010','GrantsGov Form Name','NASA_OtherProjectInformation-V1.0',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC150','KC-PD','NASAOtherProjectInformationV1_0 (Q IDs 101, 102, 103, 104, 105, 106, 107, 108, 109)',null,'Y',1,'NASAOtherProjectInformationV1_0 (Q IDs 101, 102, 103, 104, 105, 106, 107, 108, 109)')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2010','KC150',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2010','NASAOtherProjectInformationV1_0','S','KC150',1)
/
update KRMS_RULE_T set PROP_ID='KC2010' where RULE_ID='KC150'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2028','KC2010','KC2010','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2029','KC2010','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2030','KC2010','true','C',1,1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) values ('KC2011','KC2000',1,'PHS398_Checklist 1.1 Term')
/
insert into KRMS_TERM_PARM_T(TERM_PARM_ID,TERM_ID,NM,VAL,VER_NBR) values ('KC2011','KC2011','GrantsGov Form Name','PHS398_Checklist-V1.1',1)
/
insert into KRMS_RULE_T (RULE_ID,NMSPC_CD,NM,PROP_ID,ACTV,VER_NBR,DESC_TXT) values ('KC151','KC-PD','PHS398Checklist OVERLAP V1_1, 1-3 (Q IDs 114, 115, 116, 117, 118, 119, 120) change pi, inst, renewal',null,'Y',1,'PHS398Checklist OVERLAP V1_1, 1-3 (Q IDs 114, 115, 116, 117, 118, 119, 120) change pi, inst, renewal')
/
insert into KRMS_AGENDA_ITM_T(AGENDA_ITM_ID,RULE_ID,SUB_AGENDA_ID,AGENDA_ID,VER_NBR,WHEN_TRUE,WHEN_FALSE,ALWAYS) values ('KC2011','KC151',null,'Q1000',1,null,null,null)
/
insert into KRMS_PROP_T (PROP_ID,DESC_TXT,DSCRM_TYP_CD,RULE_ID,VER_NBR) values ('KC2011','PHS398ChecklistV1_1','S','KC151',1)
/
update KRMS_RULE_T set PROP_ID='KC2011' where RULE_ID='KC151'
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2031','KC2011','KC2011','T',0,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2032','KC2011','=','O',2,1)
/
insert into KRMS_PROP_PARM_T (PROP_PARM_ID,PROP_ID,PARM_VAL,PARM_TYP_CD,SEQ_NO,VER_NBR) values ('KC2033','KC2011','true','C',1,1)
/
update KRMS_AGENDA_T set INIT_AGENDA_ITM_ID='KC2001' where AGENDA_ID='Q1000'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2002' where AGENDA_ITM_ID='KC2001'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2003' where AGENDA_ITM_ID='KC2002'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2004' where AGENDA_ITM_ID='KC2003'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2005' where AGENDA_ITM_ID='KC2004'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2006' where AGENDA_ITM_ID='KC2005'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2007' where AGENDA_ITM_ID='KC2006'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2008' where AGENDA_ITM_ID='KC2007'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2009' where AGENDA_ITM_ID='KC2008'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2010' where AGENDA_ITM_ID='KC2009'
/
update KRMS_AGENDA_ITM_T set ALWAYS='KC2011' where AGENDA_ITM_ID='KC2010'
/
DELIMITER ;
