CREATE OR REPLACE VIEW OSP$SUBMISSION_TYPE_QUALIFIER AS SELECT
  SUBMISSION_TYPE_QUAL_CODE,
  DESCRIPTION,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM SUBMISSION_TYPE_QUALIFIER;
