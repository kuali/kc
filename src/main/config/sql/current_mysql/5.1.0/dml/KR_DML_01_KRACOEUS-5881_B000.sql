DELIMITER /
insert into KRMS_TYP_T(TYP_ID,NM,NMSPC_CD,SRVC_NM,ACTV,VER_NBR) 
	values ('KC1007','IRB Java Function Term Service','KC-PROTOCOL','irbJavaFunctionKrmsTermService','Y',1)
/
delete from krms_cntxt_vld_term_spec_t where TERM_SPEC_ID=(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and NM='allProtocols')
/
delete from krms_term_spec_ctgry_t where TERM_SPEC_ID=(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and NM='allProtocols')
/
delete from krms_term_t where TERM_SPEC_ID=(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and NM='allProtocols')
/
delete from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and NM='allProtocols'
/
-- public Boolean allProtocols();
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1048','allProtocols','True for all protocols','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2049','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-PROTOCOL'),
				'All protocols','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2034',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-PROTOCOL')),
					1,'All protocols')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2049','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2045','KC-PROTOCOL','All Protocols Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='allProtocols' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/

-- public Boolean isProtocolAmendment(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1049','isProtocolAmendment','Is Protocol an amendment','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0095',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2050','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-PROTOCOL'),
				'Is Protocol an amendment','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2035',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-PROTOCOL')),
					1,'Is Protocol an amendment')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2050','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2046','KC-PROTOCOL','Protocol amenedment check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolAmendment' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean isProtocolRenewal(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1050','isProtocolRenewal','Is Protocol a renewal','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0096',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2051','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-PROTOCOL'),
				'Is Protocol a renewal','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2036',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-PROTOCOL')),
					1,'Is Protocol a renewal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2051','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2047','KC-PROTOCOL','Protocol renewal check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewal' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean isProtocolLeadUnitBelow(ProtocolBase protocol,String leadUnit);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1051','isProtocolLeadUnitBelow','Check Protocol leadunit below passed in unit','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0097',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0098',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-PROTOCOL'),
				'Unit number','Unit number','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2052','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol leadunit below passed in unit','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2052','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2048','KC-PROTOCOL','Protocol leadunit below check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolLeadUnitBelow' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2043', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol leadunit below check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Unit number', 1)
/

--    public Boolean hasProtocolContainsOrganization(ProtocolBase protocol,String organizationId);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1052','hasProtocolContainsOrganization','Check Protocol organization with passed in organization','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0099',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0100',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-PROTOCOL'),
				'Organization Id','Organization Id','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2053','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol organization with passed in organization','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2053','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2049','KC-PROTOCOL','Protocol organization check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsOrganization' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2044', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol organization check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Organization Id', 1)
/
    
--    public Boolean hasFundingSourceContainsSponsor(ProtocolBase protocol,String sponsor);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1053','hasFundingSourceContainsSponsor','Check Protocol organization with passed in organization','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0101',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0102',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-PROTOCOL'),
				'Sponsor Code','Sponsor Code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2054','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol funding source with passed in sponsor','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2054','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2050','KC-PROTOCOL','Protocol funding source check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsSponsor' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2045', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol funding source check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Sponsor Code', 1)
/
   
--    public Boolean hasFundingSourceContainsUnit(ProtocolBase protocol,String unitNumber);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1054','hasFundingSourceContainsUnit','Check Protocol unit with passed in unit number','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0103',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0104',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-PROTOCOL'),
				'Unit number','Unit number','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2055','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol unit with passed in unit number','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2055','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2051','KC-PROTOCOL','Protocol unit check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasFundingSourceContainsUnit' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2046', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol unit check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Unit number', 1)
/

--    public Boolean hasProtocolContainsAreaOfResearch(ProtocolBase protocol,String areaOfResearchCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1055','hasProtocolContainsAreaOfResearch','Check Protocol area of research with passed in area of research','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0105',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0106',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-PROTOCOL'),
				'Area of research code','Area of research code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2056','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol area of research with passed in area of research','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2056','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2052','KC-PROTOCOL','Protocol area of research check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsAreaOfResearch' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2047', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol area of research check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Area of research code', 1)
/
--    public Boolean isSubmitUserProtocolPi(ProtocolBase protocol,String userId);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1056','isSubmitUserProtocolPi','Check protocol pi with passed in user id','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0107',(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0108',(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-PROTOCOL'),
				'User Id','User Id','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2057','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-PROTOCOL'),
				'Check protocol pi with passed in user id','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2057','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2053','KC-PROTOCOL','Protocol Pi check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSubmitUserProtocolPi' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2048', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol Pi check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'User Id', 1)
/
--    public Boolean isLeadUnitProtocolCampus(ProtocolBase protocol,String campusCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1057','isLeadUnitProtocolCampus','Check protocol lead unit with passed in campus code','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0109',(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0110',(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-PROTOCOL'),
				'Campus code','Campus code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2058','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-PROTOCOL'),
				'Check protocol lead unit with passed in campus code','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2058','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2054','KC-PROTOCOL','Protocol campus check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isLeadUnitProtocolCampus' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2049', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol campus check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Campus code', 1)
/
--    public Boolean hasProtocolContainsDocumentType(ProtocolBase protocol,String documentTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1058','hasProtocolContainsDocumentType','Check protocol contains passed in document type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0111',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0112',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-PROTOCOL'),
				'Document type code','Document type code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2059','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-PROTOCOL'),
				'Check protocol contains passed in document type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2059','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2055','KC-PROTOCOL','Protocol document type check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsDocumentType' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2050', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol document type check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Document type code', 1)
/
--    public Boolean isProtocolInSubmission(ProtocolBase protocol,String submissionTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1059','isProtocolInSubmission','Check protocol submissions contains passed in submission type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0113',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0114',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-PROTOCOL'),
				'Submission type code','Submission type code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2060','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-PROTOCOL'),
				'Check protocol contains passed in submision type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2060','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2056','KC-PROTOCOL','Protocol submission type check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolInSubmission' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2051', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol submission type check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Submission type code', 1)
/
--    public Boolean hasProtocolContainsNotifySubmissionQualifierType(ProtocolBase protocol,Integer submissionNumber,String submissionQualifierTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1060','hasProtocolContainsNotifySubmissionQualifierType','Check Protocol has notify submission qualifier type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0115',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0116',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-PROTOCOL'),
				'Submission number','Submission number','java.lang.Integer',2)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0117',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-PROTOCOL'),
				'Submission qualifier type code','Submission qualifier type code','java.lang.String',3)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2061','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol has notify submission qualifier type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2061','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2057','KC-PROTOCOL','Protocol notify qualifier type check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsNotifySubmissionQualifierType' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2052', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol notify qualifier type check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Submission number', 1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2053', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol notify qualifier type check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Submission qualifier type code', 1)
/
--    public Boolean hasSubmissionType(ProtocolBase protocol,Integer submissionNumber,String submissionTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1061','hasSubmissionType','Check Protocol has notify submission qualifier type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0118',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0119',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-PROTOCOL'),
				'Submission number','Submission number','java.lang.Integer',2)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0120',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-PROTOCOL'),
				'Submission type code','Submission type code','java.lang.String',3)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2062','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol has submission type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2062','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2058','KC-PROTOCOL','Protocol submission check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasSubmissionType' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2054', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol submission check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Submission number', 1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2055', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol submission check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Submission type code', 1)
/
--	public Boolean isPINotFaculty(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1062','isPINotFaculty','Is Protocol PI not a faculty','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0121',(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2063','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-PROTOCOL'),
				'Is Protocol PI not a faculty','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2037',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-PROTOCOL')),
					1,'Is Protocol PI not a faculty')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2063','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2059','KC-PROTOCOL','Protocol Pi faculty check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPINotFaculty' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean isSpecialReviewExists(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1063','isSpecialReviewExists','Check Protocol has any special reviews','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0122',(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2064','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol has any special reviews','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2038',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-PROTOCOL')),
					1,'Check Protocol has any special reviews')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2064','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2060','KC-PROTOCOL','Protocol special review check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isSpecialReviewExists' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean hasAllPersonsCompletedTraining(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1064','hasAllPersonsCompletedTraining','Check all protocol persons have completed training','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0123',(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2065','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-PROTOCOL'),
				'Have protocol persons completed training','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2039',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-PROTOCOL')),
					1,'Have protocol persons completed training')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2065','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2061','KC-PROTOCOL','Protocol persons training check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasAllPersonsCompletedTraining' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean isPiChangedInAmendmentOrRenewal(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1065','isPiChangedInAmendmentOrRenewal','Check protocol pi has changed during amendment/renewal','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0124',(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2066','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL'),
				'Has PI changed during amendment/renewal','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2040',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')),
					1,'Has PI changed during amendment/renewal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2066','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2062','KC-PROTOCOL','Protocol amendment PI check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isPiChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean isOrganizationChangedInAmendmentOrRenewal(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1066','isOrganizationChangedInAmendmentOrRenewal','Check protocol organization has changed during amendment/renewal','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0125',(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2067','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL'),
				'Has organization changed during amendment/renewal','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2041',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')),
					1,'Has organization changed during amendment/renewal')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2067','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2063','KC-PROTOCOL','Protocol amendment organization check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isOrganizationChangedInAmendmentOrRenewal' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean isProtocolRenewalWithAmendment(ProtocolBase protocol);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1067','isProtocolRenewalWithAmendment','Check protocol has renewal with amendment','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0126',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.protocol.ProtocolBase',1)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2068','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-PROTOCOL'),
				'Check protocol has renewal with amendment','java.lang.Boolean','Y',1)
/
insert into KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
	values ('KC2042',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-PROTOCOL')),
					1,'Check protocol has renewal with amendment')
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2068','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2064','KC-PROTOCOL','Protocol amendment with renewal check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isProtocolRenewalWithAmendment' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
--    public Boolean hasProtocolContainsSubjectType(Protocol protocol, String subjectTypeCode);
insert into KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
	values ('KC1088','hasProtocolContainsSubjectType','Check Protocol has passed in Subject Type','java.lang.Boolean',1,'Y',
			(select TYP_ID from KRMS_TYP_T where NM='IRB Java Function Term Service' and NMSPC_CD='KC-PROTOCOL'),'KC-PROTOCOL')
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0159',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsSubjectType' and NMSPC_CD='KC-PROTOCOL'),
				'IrbProtocol','Irb Protocol BO','org.kuali.kra.irb.Protocol',1)
/
insert into KRMS_FUNC_PARM_T (FUNC_PARM_ID,FUNC_ID,NM,DESC_TXT,TYP,SEQ_NO) 
	values ('KC0160',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsSubjectType' and NMSPC_CD='KC-PROTOCOL'),
				'Subject Type Code','Subject Type Code','java.lang.String',2)
/
insert into KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
	values ('KC2089','KC-PROTOCOL',(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsSubjectType' and NMSPC_CD='KC-PROTOCOL'),
				'Check Protocol has subject type','java.lang.Boolean','Y',1)
/
insert into KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
	values ('KC2089','KC-PROTOCOL-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsSubjectType' and NMSPC_CD='KC-PROTOCOL')),
					'Y')
/
insert into KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
	values ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsSubjectType' and NMSPC_CD='KC-PROTOCOL')), 
			(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PROTOCOL' and NM='Function'))
/
insert into KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
	values ('KC2085','KC-PROTOCOL','Protocol subject type check Resolver',
			(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'),
				(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PROTOCOL' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='hasProtocolContainsSubjectType' and NMSPC_CD='KC-PROTOCOL')),
					'Y',1)
/
insert into KRMS_TERM_RSLVR_PARM_SPEC_T (TERM_RSLVR_PARM_SPEC_ID, TERM_RSLVR_ID, NM, VER_NBR) 
	values ('KC2069', (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Protocol subject type check Resolver' and NMSPC_CD='KC-PROTOCOL'), 
			'Subject Type Code', 1)
/

DELIMITER ;
