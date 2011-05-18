INSERT INTO SEQ_QUESTIONNAIRE_REF_ID VALUES (NULL);
SET @id1 = last_insert_id();
INSERT INTO SEQ_QUESTIONNAIRE_ID VALUES (NULL);
SET @id2 = last_insert_id();
INSERT INTO QUESTIONNAIRE (QUESTIONNAIRE_REF_ID, QUESTIONNAIRE_ID, SEQUENCE_NUMBER, NAME, DESCRIPTION, UPDATE_USER, UPDATE_TIMESTAMP, IS_FINAL, VER_NBR, OBJ_ID, FILE_NAME, DOCUMENT_NUMBER) VALUES (@id1, @id2, 1, 'Proposal Person Certification', 'Questions to verify the proposal person is acceptable for the proposal development document.', 'admin', NOW(), 'N', 1, UUID(), NULL, NULL);

update questionnaire set IS_FINAL = 'Y' where name = 'Proposal Person Certification';