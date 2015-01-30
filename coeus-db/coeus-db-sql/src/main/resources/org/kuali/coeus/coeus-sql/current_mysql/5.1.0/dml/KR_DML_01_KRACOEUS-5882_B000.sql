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
insert into KRMS_TYP_T(TYP_ID,NM,NMSPC_CD,SRVC_NM,ACTV,VER_NBR) 
	values ('KC1008','IACUC Java Function Term Service','KC-IACUC','iacucJavaFunctionKrmsTermService','Y',1)
/
-- public Boolean allProtocols();
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1068','allProtocols','True for all protocols','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2069','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-IACUC'),
				'All protocols','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2043',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-IACUC')),
					1,'All protocols')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2069','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2065','KC-IACUC','All Protocols Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
-- public Boolean isProtocolAmendment(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1069','isProtocolAmendment','Is Protocol an amendment','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0127',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2070','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-IACUC'),
				'Is Protocol an amendment','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2044',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-IACUC')),
					1,'Is Protocol an amendment')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2070','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2066','KC-IACUC','Protocol amenedment check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
--    public Boolean isProtocolRenewal(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1070','isProtocolRenewal','Is Protocol a renewal','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0128',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2071','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-IACUC'),
				'Is Protocol a renewal','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2045',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-IACUC')),
					1,'Is Protocol a renewal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2071','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2067','KC-IACUC','Protocol renewal check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
--    public Boolean isProtocolLeadUnitBelow(ProtocolBase protocol,String leadUnit);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1071','isProtocolLeadUnitBelow','Check Protocol leadunit below passed in unit','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0129',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0130',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-IACUC'),
				'Unit number','Unit number','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2072','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-IACUC'),
				'Check Protocol leadunit below passed in unit','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2072','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2068','KC-IACUC','Protocol leadunit below check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2056', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol leadunit below check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Unit number', 1)
/
--    public Boolean hasProtocolContainsOrganization(ProtocolBase protocol,String organizationId);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1072','hasProtocolContainsOrganization','Check Protocol organization with passed in organization','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0131',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0132',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-IACUC'),
				'Organization Id','Organization Id','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2073','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-IACUC'),
				'Check Protocol organization with passed in organization','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2073','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2069','KC-IACUC','Protocol organization check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2057', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol organization check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Organization Id', 1)
/
--    public Boolean hasFundingSourceContainsSponsor(ProtocolBase protocol,String sponsor);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1073','hasFundingSourceContainsSponsor','Check Protocol organization with passed in organization','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0133',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0134',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-IACUC'),
				'Sponsor Code','Sponsor Code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2074','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-IACUC'),
				'Check Protocol funding source with passed in sponsor','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2074','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2070','KC-IACUC','Protocol funding source check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2058', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol funding source check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Sponsor Code', 1)
/
--    public Boolean hasFundingSourceContainsUnit(ProtocolBase protocol,String unitNumber);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1074','hasFundingSourceContainsUnit','Check Protocol unit with passed in unit number','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0135',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0136',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-IACUC'),
				'Unit number','Unit number','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2075','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-IACUC'),
				'Check Protocol unit with passed in unit number','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2075','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2071','KC-IACUC','Protocol unit check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2059', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol unit check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Unit number', 1)
/
--    public Boolean hasProtocolContainsAreaOfResearch(ProtocolBase protocol,String areaOfResearchCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1075','hasProtocolContainsAreaOfResearch','Check Protocol area of research with passed in area of research','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0137',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0138',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-IACUC'),
				'Area of research code','Area of research code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2076','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-IACUC'),
				'Check Protocol area of research with passed in area of research','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2076','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2072','KC-IACUC','Protocol area of research check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2060', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol area of research check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Area of research code', 1)
/
--    public Boolean isSubmitUserProtocolPi(ProtocolBase protocol,String userId);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1076','isSubmitUserProtocolPi','Check protocol pi with passed in user id','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0139',(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0140',(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-IACUC'),
				'User Id','User Id','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2077','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-IACUC'),
				'Check protocol pi with passed in user id','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2077','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2073','KC-IACUC','Protocol Pi check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2061', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol Pi check Resolver' and NMSPC_CD='KC-IACUC'), 
			'User Id', 1)
/
--    public Boolean isLeadUnitProtocolCampus(ProtocolBase protocol,String campusCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1077','isLeadUnitProtocolCampus','Check protocol lead unit with passed in campus code','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0141',(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0142',(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-IACUC'),
				'Campus code','Campus code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2078','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-IACUC'),
				'Check protocol lead unit with passed in campus code','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2078','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2074','KC-IACUC','Protocol campus check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2062', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol campus check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Campus code', 1)
/
--    public Boolean hasProtocolContainsDocumentType(ProtocolBase protocol,String documentTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1078','hasProtocolContainsDocumentType','Check protocol contains passed in document type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0143',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0144',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-IACUC'),
				'Document type code','Document type code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2079','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-IACUC'),
				'Check protocol contains passed in document type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2079','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2075','KC-IACUC','Protocol document type check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2063', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol document type check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Document type code', 1)
/
--    public Boolean isProtocolInSubmission(ProtocolBase protocol,String submissionTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1079','isProtocolInSubmission','Check protocol submissions contains passed in submission type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0145',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0146',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-IACUC'),
				'Submission type code','Submission type code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2080','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-IACUC'),
				'Check protocol contains passed in submision type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2080','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2076','KC-IACUC','Protocol submission type check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2064', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol submission type check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Submission type code', 1)
/
--    public Boolean hasProtocolContainsNotifySubmissionQualifierType(ProtocolBase protocol,Integer submissionNumber,String submissionQualifierTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1080','hasProtocolContainsNotifySubmissionQualifierType','Check Protocol has notify submission qualifier type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0147',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0148',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-IACUC'),
				'Submission number','Submission number','java.lang.Integer',2)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0149',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-IACUC'),
				'Submission qualifier type code','Submission qualifier type code','java.lang.String',3)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2081','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-IACUC'),
				'Check Protocol has notify submission qualifier type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2081','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2077','KC-IACUC','Protocol notify qualifier type check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2065', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol notify qualifier type check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Submission number', 1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2066', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol notify qualifier type check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Submission qualifier type code', 1)
/
--    public Boolean hasSubmissionType(ProtocolBase protocol,Integer submissionNumber,String submissionTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1081','hasSubmissionType','Check Protocol has notify submission qualifier type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0150',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0151',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-IACUC'),
				'Submission number','Submission number','java.lang.Integer',2)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0152',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-IACUC'),
				'Submission type code','Submission type code','java.lang.String',3)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2082','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-IACUC'),
				'Check Protocol has submission type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2082','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2078','KC-IACUC','Protocol submission check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2067', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol submission check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Submission number', 1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2068', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol submission check Resolver' and NMSPC_CD='KC-IACUC'), 
			'Submission type code', 1)
/
--	public Boolean isPINotFaculty(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1082','isPINotFaculty','Is Protocol PI not a faculty','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0153',(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2083','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-IACUC'),
				'Is Protocol PI not a faculty','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2046',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-IACUC')),
					1,'Is Protocol PI not a faculty')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2083','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2079','KC-IACUC','Protocol Pi faculty check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
--    public Boolean isSpecialReviewExists(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1083','isSpecialReviewExists','Check Protocol has any special reviews','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0154',(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2084','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-IACUC'),
				'Check Protocol has any special reviews','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2047',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-IACUC')),
					1,'Check Protocol has any special reviews')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2084','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2080','KC-IACUC','Protocol special review check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
--    public Boolean hasAllPersonsCompletedTraining(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1084','hasAllPersonsCompletedTraining','Check all protocol persons have completed training','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0155',(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2085','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-IACUC'),
				'Have protocol persons completed training','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2048',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-IACUC')),
					1,'Have protocol persons completed training')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2085','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2081','KC-IACUC','Protocol persons training check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
--    public Boolean isPiChangedInAmendmentOrRenewal(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1085','isPiChangedInAmendmentOrRenewal','Check protocol pi has changed during amendment/renewal','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0156',(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2086','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC'),
				'Has PI changed during amendment/renewal','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2049',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')),
					1,'Has PI changed during amendment/renewal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2086','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2082','KC-IACUC','Protocol amendment PI check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
--    public Boolean isOrganizationChangedInAmendmentOrRenewal(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1086','isOrganizationChangedInAmendmentOrRenewal','Check protocol organization has changed during amendment/renewal','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0157',(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2087','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC'),
				'Has organization changed during amendment/renewal','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2050',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')),
					1,'Has organization changed during amendment/renewal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2087','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2083','KC-IACUC','Protocol amendment organization check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/
--    public Boolean isProtocolRenewalWithAmendment(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1087','isProtocolRenewalWithAmendment','Check protocol has renewal with amendment','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IACUC Java Function Term Service' and NMSPC_CD='KC-IACUC'),'KC-IACUC')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0158',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-IACUC'),
				'IacucProtocol','Iacuc Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2088','KC-IACUC',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-IACUC'),
				'Check protocol has renewal with amendment','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2051',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-IACUC')),
					1,'Check protocol has renewal with amendment')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2088','KC-IACUC-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-IACUC')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-IACUC')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-IACUC' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2084','KC-IACUC','Protocol amendment with renewal check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-IACUC' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-IACUC')),
					'Y',1)
/

DELIMITER ;
