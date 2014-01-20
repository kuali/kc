INSERT INTO QUESTIONNAIRE (QUESTIONNAIRE_REF_ID, QUESTIONNAIRE_ID, SEQUENCE_NUMBER, NAME, DESCRIPTION, UPDATE_USER, UPDATE_TIMESTAMP, IS_FINAL, VER_NBR, OBJ_ID, FILE_NAME, DOCUMENT_NUMBER) 
    VALUES (6111, 10001, 1, 'Proposal Person Certification', 'Questions to verify the proposal person is acceptable for the proposal development document.', 'admin', NOW(), 'N', 1, UUID(), NULL, (SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Proposal Person Preloaded Questionnaire'));

update questionnaire set IS_FINAL = 'Y' where name = 'Proposal Person Certification';