update KRMS_TYP_T set SRVC_NM='{http://kc.kuali.org/core/v5_0}javaFunctionTermResolverTypeService' where SRVC_NM = 'functionTermResolverTypeService'
/
insert into KRMS_TYP_T values ('KC1005','Stored Function Term Resolver Type Service','KC-PD','{http://kc.kuali.org/core/v5_0}storedFunctionTermResolverTypeService','Y',1)
/
insert into KRMS_TYP_T values ('KC1006','ProposalDevelopment Java Function Term Service','KC-PD','krmsPropDevJavaFunctionTermService','Y',1)
/
insert into KRMS_TYP_T values ('KC1007','IRB Java Function Term Service','KC-PD','krmsIrbJavaFunctionTermService','Y',1)
/
update KRMS_TERM_SPEC_T A set NM = (select FUNC_ID from KRMS_FUNC_T B where B.NM=A.NM) 
  where exists (select FUNC_ID from KRMS_FUNC_T C where C.NM=A.NM)
/
update krms_term_t set desc_txt = 
    (select CONCAT(krms_term_spec_t.desc_txt,'(',krms_term_t.desc_txt,')') from krms_term_spec_t
          where krms_term_spec_t.term_spec_id=krms_term_t.term_spec_id 
          		and krms_term_spec_t.nm in (select func_id from krms_func_t) and krms_term_spec_t.nm between 'KC1000' and 'KC2025')
where exists  (select CONCAT(krms_term_spec_t.desc_txt,'(',krms_term_t.desc_txt,')') from krms_term_spec_t 
          where krms_term_spec_t.term_spec_id=krms_term_t.term_spec_id 
          		and krms_term_spec_t.nm in (select func_id from krms_func_t) and krms_term_spec_t.nm between 'KC1000' and 'KC2025')
/
