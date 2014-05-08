DELIMITER /
update krms_term_rslvr_parm_spec_t set nm = 'Questionnaire ID' where nm = 'Questionnaire Ref ID'
/
update krms_term_parm_t set nm = 'Questionnaire ID' where nm = 'Questionnaire Ref ID'
/
DELIMITER ;

