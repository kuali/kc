UPDATE PROTOCOL_ONLN_RVWS SET REVIEW_DETERM_RECOM_CD = 4 WHERE REVIEW_DETERM_RECOM_CD = 6;
DELETE FROM PROTOCOL_ONLN_RVW_DETERM_RECOM WHERE REVIEW_DETERM_RECOM_CD = 6;
