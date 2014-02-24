DELIMITER /
UPDATE NOTIFICATION_TYPE SET MESSAGE='The negotiation is complete.<br/><br/>Negotiation ID: <a title="" target="_self" href="{DOCUMENT_PREFIX}/kew/DocHandler.do?command=displayDocSearchView&amp;docId={DOCUMENT_NUMBER}">{NEGOTIATION_ID}</a><br/>Negotiator: {NEGOTIATOR}<br/>Negotiation Status: {NEGOTIATION_STATUS}<br/>Anticipated Project Start Date: {ANTICIPATED_START_DATE}<br/>Negotiation Start Date: {START_DATE}<br/>Negotiation End Date: {END_DATE}<br/>Title: {TITLE}<br/>Primary Investigator: {PI}<br/>Lead Unit: {LEAD_UNIT_NAME} ({LEAD_UNIT_NUMBER})<br/>Sponsor: {SPONSOR_NAME} ({SPONSOR_CODE})<br/>Prime Sponsor: {PRIME_SPONSOR_NAME} ({PRIME_SPONSOR_CODE})<br/>' WHERE MODULE_CODE=5 AND ACTION_CODE='100'
/

DELIMITER ;
