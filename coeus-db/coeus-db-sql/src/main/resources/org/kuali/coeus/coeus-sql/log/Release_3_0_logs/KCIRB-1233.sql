-- The following DML should be included in the Rice bootstrap data --

UPDATE krns_parm_t SET parm_nm = 'questionnaireQuestionnaireHelpUrl' WHERE parm_nm = 'questionnaireQuestionnaireHelp';

COMMIT;
