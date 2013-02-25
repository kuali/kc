DELIMITER /
update KRMS_TERM_SPEC_T A set NM = (select FUNC_ID from KRMS_FUNC_T B where B.NM=A.NM) 
  where exists (select FUNC_ID from KRMS_FUNC_T C where C.NM=A.NM)
/
update krms_term_t set desc_txt = 
    (select CONCAT(krms_term_spec_t.desc_txt,'(',krms_term_t.desc_txt,')') from krms_term_spec_t
          where krms_term_spec_t.term_spec_id=krms_term_t.term_spec_id 
          		and krms_term_spec_t.nm in (select func_id from krms_func_t))
where exists  (select CONCAT(krms_term_spec_t.desc_txt,'(',krms_term_t.desc_txt,')') from krms_term_spec_t 
          where krms_term_spec_t.term_spec_id=krms_term_t.term_spec_id 
          		and krms_term_spec_t.nm in (select func_id from krms_func_t))
/

DELIMITER ;
