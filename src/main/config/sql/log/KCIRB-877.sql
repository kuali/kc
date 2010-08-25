delete from krim_role_perm_t where perm_id = (select perm_id from krim_perm_t where PERM_TMPL_ID = (select PERM_TMPL_ID from KRIM_PERM_TMPL_T where NM = 'Answer Questionnaire Permission'));

delete from KRIM_PERM_ATTR_DATA_T where perm_id = (select perm_id from krim_perm_t where PERM_TMPL_ID = (select PERM_TMPL_ID from KRIM_PERM_TMPL_T where NM = 'Answer Questionnaire Permission'));

delete from krim_perm_t where PERM_TMPL_ID = (select PERM_TMPL_ID from KRIM_PERM_TMPL_T where NM = 'Answer Questionnaire Permission');
delete from KRIM_PERM_TMPL_T where NM = 'Answer Questionnaire Permission';
