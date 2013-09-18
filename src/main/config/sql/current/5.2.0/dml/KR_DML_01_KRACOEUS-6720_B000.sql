UPDATE KRMS_FUNC_T SET DESC_TXT='Checks if the opportunity is already selected for the proposal' WHERE NM='proposalGrantsRule'
/
UPDATE KRMS_TERM_SPEC_T SET DESC_TXT='Is S2S Submission' WHERE nm=(select FUNC_ID from KRMS_FUNC_T where NM='proposalGrantsRule')
/
