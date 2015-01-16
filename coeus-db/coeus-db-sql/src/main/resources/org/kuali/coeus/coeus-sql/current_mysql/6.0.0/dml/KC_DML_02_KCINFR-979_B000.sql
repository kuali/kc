DELIMITER /
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/

INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_PerformanceSite/OtherSite', 'The maximum number of other organizations allowed is 7', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_PerformanceSite/OtherSite/Address/State', 'The state code for one of the performance sites is not valid', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_PerformanceSite/OtherSite/Address/Country', 'Country code is missing for one of the performance sites', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/GrantApplication/ApplicationTypeCode', 'You have a non-standard application type for the SF424.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/GrantApplication/Revision/RevisionCode1', 'You have entered an invalid revision type for the SF424', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), 'GrantApplication/Forms/GrantApplication/ActivityTitle', 'Please enter the program title', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424/ApplicationTypeCode', 'You have a non-standard application type for the SF424', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424/Revision/RevisionCode1', 'You have entered an invalid revision type for the SF424', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424/Applicant/City', 'Organization''s city is not valid', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424/PhoneNumber', 'Phone number missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424/Email', 'Email address missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424/StateReview', 'Please answer the State Executive Order questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424/StateReviewAvailableDate', 'Please answer the State Review Date questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_Short/ProjectDescription', 'Project Description required, please include a Project Summary Abstract.', 'abstactsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_Short/ProjectDirectorGroup', 'Title missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_Short/ContactPersonGroup/Name/FirstName', 'First name missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_Short/ContactPersonGroup/Name/LastName', 'Last name missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_Short/ContactPersonGroup/Address/Street1', 'Address missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_Short/ContactPersonGroup/Address/City', 'City missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_Short/ContactPersonGroup/Address/Country', 'Country missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Assurances/ApplicationTypeCode', 'You have a non-standard application type for the SF424', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Assurances/Revision/RevisionCode1', 'You have entered an invalid revision type for the SF424', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist/FormerPDName/LastName', 'Last name is required for the former PD/PI', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist/FormerPDName/FirstName', 'First name is required for the former PD/PI', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist/IncomeBudgetPeriod/BudgetPeriod', 'You may have only 5 budget periods', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist/CertificationExplanation/Certifications', 'Please attach a Certification Explanation for the checklist', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage/NSFUnitConsideration/DivisionCode', 'Agency division code must be specified', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage/NSFUnitConsideration/ProgramCode', 'Agency program code must be specified', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage/PIInfo', 'You must have a Principal Investigator', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage/PIInfo/DegreeType', 'Enter degree information for the Principal Investigator', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage/PIInfo/DegreeYear', 'Enter the graduation date for the Principal Investigator''s highest degree', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage/FundingOpportunityNumber', 'Please enter the program announcement number', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/AbstractAttachments/Attachments', 'The abstract attachment is required.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverLetter/CoverLetterFile/CoverLetterFilename', 'Please attach a Cover Letter.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/OrganizationInfo/DUNSID', 'The selected organization does not have DUNS number', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/PDPIContactInfo', 'Contact information missing for PD/PI, please make sure a Principal Investigator has been added.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/PDPIContactInfo/Address/Street1', 'The PI address is invalid. Use the proposal person window to add a street address', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/PDPIContactInfo/Address/Country', 'The PI country code is invalid. Use the proposal person window to fix this', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/PDPIContactInfo/Address/City', 'The PI address is missing a city. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ActivityTitle', 'Please enter the program title', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/Address/Country', 'The country code for the AOR is not valid for Grants.gov', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/Title', 'The AOR must have a title', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ProjectTitle', 'You are missing a project title', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/EstimatedProjectFunding/TotalEstimatedAmount', 'You are missing total budget amounts', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/EstimatedProjectFunding/TotalfedNonfedrequested', 'You are missing total budget amounts', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/EstimatedProjectFunding/EstimatedProgramIncome', 'You are missing total budget amounts', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/ContactPersonInfo', 'You are missing a contact person for the proposal', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/StateID', 'Selected organization missing state', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/OrganizationInfo/Address/City', 'Selected organization missing city', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/OrganizationInfo/Address/County', 'Selected organization missing county', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/OrganizationInfo/Address/ZipPostalCode', 'Selected organization missing postal code', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/OrganizationInfo/Address', 'Selected organization missing address', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/ContactPersonInfo/Name/FirstName', 'First name missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/ContactPersonInfo/Name/MiddleName', 'Middle name missing for contact person', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/ContactPersonInfo/Name/LastName', 'Last name missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/ContactPersonInfo/Fax', 'Fax missing for contact person', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/ContactPersonInfo/Email', 'Email address missing for contact person', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantType/ApplicantTypeCode', 'Applicant type missing', 'proposal.SponsorProgramInformation',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/Location', 'Areas Affected by Project is required, please add this Abstract Type in the Abstracts and Attachments tab', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/EstimatedProjectFunding', 'Estimated Project funding missing', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/Address', 'Missing AOR address', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/Phone', 'Missing AOR phone number', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/Fax', 'Missing AOR Fax', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/Email', 'Missing AOR email address', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/OrganizationName', 'Missing AOR organization name', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/DepartmentName', 'Missing AOR department name', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo/DivisionName', 'Missing AOR division name', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/AORInfo', 'Missing AOR information', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424/ApplicantInfo/ContactPersonInfo/Phone', 'Phone number missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/ApplicantInfo/ContactPersonInfo/Name/FirstName', 'First name missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/ApplicantInfo/ContactPersonInfo/Name/LastName', 'Last name missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/ApplicantInfo/ContactPersonInfo/Phone', ' Phone number missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/PDPIContactInfo', ' Contact information missing for PD/PI, please make sure a Principal Investigator has been added.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/StateReview/StateReviewCodeType', ' Please answer the State Executive Order questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/StateReview/StateReviewDate', ' Please answer the State Review Date questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/ApplicationType', ' Please answer the Application Type questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_SF424_1_2/ApplicationType/isOtherAgencySubmission', ' Please answer the Application Type Other Agency Submission questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/PDPI', 'A PD/PI is required, please add a Principal Investigator to the proposal.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/Profile/BioSketchAttached', 'The PI must have a BioSketch. Upload the PDF on the Proposal Personnel screen, with "BIOSKETCH" as the description.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/PDPI/Profile/Address/Country', 'You have entered an invalid country code for your investigator. Use the proposal person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/KeyPerson/Profile/BioSketchsAttached', 'All key persons must have a BioSketch. Upload the PDF on the Proposal Personnel screen, with "BIOSKETCH" as the description.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/PDPI/Profile/Address/Street1', 'The PI address is missing Address Line 1. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/PDPI/Profile/Address/City', 'The PI address is missing a city. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/KeyPerson/Profile/Address/City', 'The address for each key person must have a city. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/KeyPerson/Profile/Address/Street1', 'The address for each key person must include Address Line 1. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPerson/PDPI/Profile/BioSketchsAttached/BioSketchAttached', 'Each investigator and key person must have a BioSketch. Upload the PDF on the Personnel Attachments panel, with "BIOSKETCH" as the attachment type.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/OtherAttachments/OtherAttachment', 'You must have an at least one Attachment with type ''Other'' or ''SupplementaryDocumentation''', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/OtherAttachments', 'You must have an at least one Attachment with type ''Other'' or ''SupplementaryDocumentation''', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/AbstractAttachments', 'You must have a ProjectSummary with the proposal. Please attach the ProjectSummary in Abstracts and Attachments window', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/ProjectNarrativeAttachments', 'You must have a Narrative with the proposal. Please attach the Narrative in Abstracts and Attachments window', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear1/BudgetJustificationAttachment', 'You must have a BudgetJustification with the proposal. Please attach the BudgetJustification in Abstracts and Attachments window', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo', 'RR Other Project Info form is not valid', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/HumanSubjectsSupplement/ExemptionNumbers/ExemptionNumber', 'Valid Human Subjects exemption numbers are E1,E2,E3,E4,E5,E6', 'specialReview',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/ProprietaryInformationIndicator', 'Please answer the Proprietary Information question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/EnvironmentalImpact', 'Please answer the Environmental Impact question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/InternationalActivities', 'Please answer the International Activities question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/EnvironmentalImpact/EnvironmentalExemption/EnvironmentalExemptionExplanation', 'Please answer the Environmental Impact question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/InternationalActivities/InternationalActivitiesIndicator', ' Please answer the International Activities question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/EnvironmentalImpact/EnvironmentalImpactIndicator', ' Please answer the Environmental Impact question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/EnvironmentalImpact/EnvironmentalImpactExplanation', 'Please explain the Environmental Impact question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo/InternationalActivities/ActivitiesPartnershipsCountries', 'Please explain the Activities Partnerships Countries question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/InternationalActivities', 'Please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/ProjectSummaryAttachments', 'The ProjectSummary proposal attachment is required for this form.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/ProjectNarrativeAttachments', 'The Narrative proposal attachment is required for this form.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2', ' You must include all required attachments and answer all required questions for this form.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/ProprietaryInformationIndicator', ' Please answer the Proprietary Information question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/EnvironmentalImpact/EnvironmentalImpactIndicator', ' Please answer the Environmental Impact question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/HistoricDesignation', ' Please answer the Historic Designation question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/InternationalActivities/InternationalActivitiesIndicator', ' Please answer the International Activities question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/EnvironmentalImpact/EnvironmentalImpactExplanation', 'Please explain the Environmental Impact question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/EnvironmentalImpact/EnvironmentalExemption/EnvironmentalExemptionExplanation', 'Please explain the Environmental Exemption question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/HistoricDesignationExplanation', 'Please explain the Historic Designation question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_2/InternationalActivities/ActivitiesPartnershipsCountries', 'Please explain the Activities Partnerships Countries question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/InternationalActivities', ' Please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/AbstractAttachments', 'The ProjectSummary proposal attachment is required for this form.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/ProjectNarrativeAttachments', 'The Narrative proposal attachment is required for this form.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3', 'You must include all required attachments and answered all required questions for this form.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/ProprietaryInformationIndicator', ' Please answer the Proprietary Information question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/EnvironmentalImpact/EnvironmentalImpactIndicator', ' Please answer the Environmental Impact question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/HistoricDesignation', ' Please answer the Historic Designation question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/InternationalActivities/InternationalActivitiesIndicator', ' Please answer the International Activities question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/EnvironmentalImpact/EnvironmentalImpactExplanation', 'Please explain the Environmental Impact question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/EnvironmentalImpact/EnvironmentalExemption/EnvironmentalExemptionExplanation', 'Please explain the Environmental Exemption question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/HistoricDesignationExplanation', 'Please explain the Historic Designation question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_OtherProjectInfo_1_3/InternationalActivities/ActivitiesPartnershipsCountries', 'Please explain the Activities Partnerships Countries question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/KeyPerson/Profile/BioSketchsAttached', 'All key persons must have a BioSketch.Upload the PDF on the Proposal Personnel screen, with "BIOSKETCH" as the description.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/PDPI/Profile/Address/City', 'The PI address is missing a city. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/PDPI/Profile/Address/Street1', 'The PI address is missing Address Line 1. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/KeyPerson/Profile/Address/City', 'The address for each key person must have a city. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/KeyPerson/Profile/Address/Street1', 'The address for each key person must include Address Line 1. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/KeyPerson/Profile/Email', 'The email address for each key person is required. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/KeyPerson/Profile/Phone', 'The phone for each key person is required. Please use the Proposal Person window to fix this.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/PDPI', 'A PD/PI is required, please add a Principal Investigator to the proposal.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded/PDPI/Profile/BioSketchsAttached/BioSketchAttached', 'Each investigator and key person must have a BioSketch. Upload the PDF on the Personnel Attachments panel, with "BIOSKETCH" as the attachment type.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded_1_2/PDPI', ' A PD/PI is required, please add a Principal Investigator to the proposal.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded_1_2/PDPI/Profile/BioSketchsAttached/BioSketchAttached', 'Each investigator and key person must have a BioSketch. Upload the PDF on the Personnel Attachments panel, with "BIOSKETCH" as the attachment type.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/ProjectNarrativeAttachments/Attachments', 'You must have at least one Project Narrative attachment with the proposal.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/PDPI/PDPIName', 'You must have a Principal Investigator.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/PDPI/PDPIName/LastName', 'Last name is required for the PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/PDPI/PDPIName/FirstName', 'First name is required for the PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/PDPI/IsNewInvestigator', 'Please answer the NIH beginning Investigator question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactName', 'The administrative officer for the lead unit is missing.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactName/LastName', 'Last name is required for the administrative contact person.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactName/FirstName', 'First name is required for the administrative contact person.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactPhone', 'Phone number is required for the administrative contact person.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactTitle', 'Title is required for the administrative contact person.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactAddress', 'Address is required for the administrative contact person.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactAddress/Street1', 'A valid address is required for the administrative contact person.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactAddress/City', 'City name is required for the contact administrative person address.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/ContactPersonInfo/ContactAddress/Country', 'Country name is required for the administrative contact person address.', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/StemCells/IsHumanStemCellsInvolved', 'Please answer the human embryonic stem cells question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement/StemCells/CellLines', 'The registration number of the specific cell line is too long. Please see the human embryonic stem cells question for explanation.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName/PDPIName', 'You must have a Principal Investigator.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName/PDPIName/LastName', 'Last name is required for the Principal Investigator.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName/PDPIName/FirstName', 'First name is required for the Principal Investigator.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName/USGovernmentParticipation', 'Please answer the US government participation question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName', 'Please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName/FederalAgency', 'Please select a U.S. Government Agency within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName/FederalAgencyDollar', 'Please enter the total dollar amount requested within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/Nasa_PIandAORSupplementalDataSheet/PrincipalInvestigatorName/InternationalParticipation', 'Please answer the international participation question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), 'budget.subaward.notfound', 'Subaward Budget has not been uploaded in the Budget Actions tab.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear1/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 1', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear1/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 1, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear1/KeyPersons', 'Key Persons for Year 1 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear2/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 2', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear2/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 2, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear2/KeyPersons', 'Key Persons for Year 2 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear3/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 3', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear3/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 3, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear3/KeyPersons', 'Key Persons for Year 3 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear4/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 4', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear4/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 4, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear4/KeyPersons', 'Key Persons for Year 4 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear5/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 5', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear5/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 5, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear5/KeyPersons', 'Key Persons for Year 5 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear1', 'Please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget/BudgetYear1/Equipment/EquipmentList/EquipmentItem', 'A description is required for each Equipment budget line item.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget10/BudgetYear/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget10/BudgetYear/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid, PI is required.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget10/BudgetYear/KeyPersons', 'Key Persons not valid in budget.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget10/BudgetJustificationAttachment', 'You must have a BudgetJustification with the proposal. Please attach the BudgetJustification in Abstracts and Attachments window', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget10/BudgetYear/Equipment/EquipmentList/EquipmentItem', 'A description is required for each Equipment budget line item.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetSummary', 'There is no budget summary information please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget', 'Please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear1/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 1', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudgetBudgetYear1/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 1, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear1/KeyPersons', 'Key Persons for Year 1 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear2/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 2', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear2/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 2, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear2/KeyPersons', 'Key Persons for Year 2 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear3/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 3', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear3/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 3, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear3/KeyPersons', 'Key Persons for Year 3 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear4/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 4', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear4/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 4, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear4/KeyPersons', 'Key Persons for Year 4 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear5/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid for Year 5', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear5/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid for Year 5, PI is required', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear5/KeyPersons', 'Key Persons for Year 5 not valid in budget', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear1/BudgetJustificationAttachment', 'A budget justification is required; please add one in Proposal Attachments', 'abstactsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget10/BudgetSummary', 'There is no budget summary information please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget10', 'Please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget10/BudgetJustificationAttachment', 'A budget justification is required; please add one in Proposal Attachments', 'abstactsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget10/BudgetYear/Equipment/EquipmentList/EquipmentItem', 'A description is required for each Equipment budget line item.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget10/BudgetYear/KeyPersons/TotalFundForAttachedKeyPersons', 'Total funding for Key Persons not valid', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget10/BudgetYear/KeyPersons/TotalFundForKeyPersons', 'Total funding for Key Persons not valid, PI is required.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget10/BudgetYear/KeyPersons', 'Key Persons not valid in budget.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_PersonalData/ProjectDirector', ' A PD/PI is required, please add a Principal Investigator to the proposal.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_PersonalData_1_2/ProjectDirector', ' A PD/PI is required, please add a Principal Investigator to the proposal.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/InternationalParticipation/InternationalParticipationPI', 'Please select the support provided by International Participation for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/InternationalParticipation/InternationalParticipatioEx', 'Please answer the International Participation Explanation question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/HistoricImpact/HistoricImpactEx', 'Please answer the Historic Sites Explanation question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/NASACivilServicePersonnel/FYFTE1', 'Please answer the Civil Service FTEs question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/NASACivilServicePersonnel', 'Please answer the Civil Service Personnel question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/HistoricImpact/HistoricImpactQ', 'Please answer the Historical Sites question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/HistoricImpact', 'Please answer the Historical Sites question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_OtherProjectInformation/InternationalParticipation', 'Please answer the International Participation question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NASA_SeniorKeyPersonSupplementalDataSheet/Senior_Key_Person', 'At least one Co-Investigator or Key Person is required for this form.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverLetter', 'Please attach a Cover Letter.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_CoverLetter_1_2/CoverLetterFile', 'Please attach a Cover Letter.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist_1_3/DisclosurePermission', 'Please select the DisclosurePermission statement for this form within the Questions tab.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist_1_3/IsInventionsAndPatents', 'Please answer the Inventions and Patents question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist_1_3/FormerPD_Name', 'Please answer the Former Principal Investigator or Program Director name for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist_1_3/FormerInstitutionName', 'Please answer the Former Institution Name for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_Checklist_1_3/IsPreviouslyReported', 'Please answer whether this invention has been reported previously to the PHS for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ContactPersonInfo/ContactName/FirstName', 'First name missing for contact person.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ContactPersonInfo/ContactName/LastName', 'Last name missing for contact person.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ContactPersonInfo/ContactPhone', 'Phone number missing for contact person.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ContactPersonInfo/ContactTitle', 'Title missing for contact person.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ContactPersonInfo/ContactAddress/Street1', 'Address missing for contact person.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ContactPersonInfo/ContactAddress/City', 'City missing for contact person.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ContactPersonInfo/ContactAddress/Country', 'Country missing for contact person.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/PDPI/PDPIName', 'You must have a Principal Investigator.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/StemCells/isHumanStemCellsInvolved', 'Please answer the Stem Cells question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/StemCells', 'Please answer the required stem cell questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/PDPI/PDPIName', 'You must have a Principal Investigator.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ContactPersonInfo/ContactName/FirstName', 'First name missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ContactPersonInfo/ContactName/LastName', 'Last name missing for proposal contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ContactPersonInfo/ContactPhone', 'Phone number missing for contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ContactPersonInfo/ContactTitle', 'Title missing for contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ContactPersonInfo/ContactAddress/Street1', 'Address missing for contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ContactPersonInfo/ContactAddress/City', 'City missing for contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ContactPersonInfo/ContactAddress/Country', 'Country missing for contact person, please verify the information in the Unit Administrator table.', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/StemCells/isHumanStemCellsInvolved', 'Please answer the Stem Cells question.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_ModularBudget', 'Please add a budget to the proposal and complete the Modular Budget tab.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/BudgetNarrativeAttachments/Attachments', 'Please attach a Budget Attachment file.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/OtherNarrativeAttachments/Attachments', 'Please attach an Other Attachments file.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms', 'Please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/ED_SF424_Supplement/ProjectDirector', 'You must have a Project Director/Principal Investigator.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CareerDevelopmentAwardSup_1_1/CareerDevelopmentAwardAttachments/Appendix', 'Career Research Strategy attachment is required.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CareerDevelopmentAwardSup_1_1/CareerDevelopmentAwardAttachments', 'PHS398_CareerDevelopmentAwardSup_1_1-V1.1 is not valid, missing required attachment.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_ResearchPlan_1_3/ResearchPlanAttachments/ResearchStrategy', 'Research Strategy attachment is required for Research Plan.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PerformanceSite_1_3/AttachedFile', 'Performance Sites attachment is required since Performance Site Locations exceed the maximum of 29.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PerformanceSite_1_4/AttachedFile', 'Performance Sites attachment is required since Performance Site Locations exceed the maximum of 29.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), 'performancesite.district.notfound', 'Organization''s Congressional District is not valid', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PerformanceSite_1_4/PrimarySite', 'Organization''s primary site is not valid', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PerformanceSite_1_4/OtherSite', 'Organization''s other site is not valid', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/ResearchTrainingPlan/HumanSubjectsInvolved', 'Human Subjects Involved is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/ResearchTrainingPlan/HumanSubjectsIndefinite', 'Human Subjects Indefinite is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/ResearchTrainingPlan/ClinicalTrial', 'Clinical Trial response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/ResearchTrainingPlan/Phase3ClinicalTrial', 'Phase 3 Clinical Trial response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/ResearchTrainingPlan/VertebrateAnimalsUsed', 'Vertebrate Animals Used is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/ResearchTrainingPlan/VertebrateAnimalsIndefinite', 'Vertebrate Animals Indefinite is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/ResearchTrainingPlan', 'Please answer all required questions and include all required attachments for this form.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/AdditionalInformation/Citizenship', 'Citizenship is not valid for PI, please make sure a PI has been added and that person has a valid citizenship type indicated.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/AdditionalInformation/ChangeOfInstitution', 'Change of Institution is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/AdditionalInformation', 'Additional information is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_1/Budget/FederalStipendRequested', 'Stipend Requested is not valid, please make sure a budget has been added.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/HumanSubjectsIndefinite', 'Human Subjects Indefinite is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/ClinicalTrial', 'Clinical Trial response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/Phase3ClinicalTrial', 'Phase 3 Clinical Trial response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/VertebrateAnimalsUsed', 'Vertebrate Animals Used is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/VertebrateAnimalsIndefinite', 'Vertebrate Animals Indefinite, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan', 'Please answer all required questions and include all required attachments for this form.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/StemCells', 'Stem Cells response, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/CurrentPriorNRSASupportIndicator', 'Current & Prior NRSA support response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_4/ClinicalTrial/isPhaseIIIClinicalTrial', 'Please answer the NIH-defined Phase III clinical trial question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/Citizenship', 'Citizenship is not valid for PI, please make sure a PI has been added and that person has a valid citizenship type indicated.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation', 'Additional information is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/Sponsors', 'Sponsors is not valid, please make sure the appropriate attachments and questions have been answered.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/FieldOfTraining', 'Field of training response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/StemCells/isHumanStemCellsInvolved', 'Human Stem Cells response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/SpecificAims', 'Specific Aims is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/ResearchStrategy', 'Research Strategy is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/RespectiveContributions', 'Respective Contributions is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/SelectionOfSponsorAndInstitution', 'Selection of Sponsor and Institution is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/ResponsibleConductOfResearch', 'Responsible Conduct of Research is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/FellowshipTrainingAndCareerGoals', 'Goals for Fellowship Training and Career is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/ActivitiesPlannedUnderThisAward', 'Activities Planned Under This Award is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/Sponsors/SponsorCosponsorInformation', 'Sponsor(s) and Co-Sponsor(s) Information is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage_1_3/NSFUnitConsideration/DivisionCode', 'Sponsor Division Code is required (must be at least 8 characters)', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/NSF_CoverPage_1_3/NSFUnitConsideration/ProgramCode', 'Sponsor Program Code is required (must be at least 4 characters)', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_TrainingBudget', 'Please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_TrainingBudget/BudgetYear/ResearchDirectCostsRequested', 'Direct costs invalid, a stipend budget line item is required equal to or greater than the trainee numbers and associated stipend levels indicated in the questionnaire. ', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_TrainingBudget/BudgetYear/TotalOtherDirectCostsRequested', 'Total Other Costs invalid, a stipend budget line item is required equal to or greater than the trainee numbers and associated stipend levels indicated in the questionnaire. ', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_TrainingBudget/CumulativeResearchDirectCostsRequested', 'Cumulative direct costs invalid, a stipend budget line item is required equal to or greater than the trainee numbers and associated stipend levels indicated in the questionnaire. ', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_TrainingBudget/CumulativeTotalOtherDirectCostsRequested', 'Cumulative other direct costs invalid, a stipend budget line item is required equal to or greater than the trainee numbers and associated stipend levels indicated in the questionnaire. ', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_TrainingBudget/BudgetJustification', 'You must have a TrainingBudgetJustification with the proposal. Please attach the BudgetJustification in Abstracts and Attachments window', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget10/BudgetSummary', 'Please add a budget to the proposal.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_Budget10', 'RR Budget10 form is not valid. Please attach the required details.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_FedNonFedBudget/BudgetYear1/Equipment/EquipmentList/EquipmentItem', 'A description is required for each Equipment budget line item.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), 's2s.budget.participantcount.required', 'You must provide a number of participants when including participant support costs for budget period', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), 's2s.budget.participantcost.required', 'You must provide participant support costs when defining the number of participants.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), 's2s.budget.modularbudget.required', 'Complete Modular Budget in the Modular Budget tab for each budget period', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_ModularBudget_1_2', 'Please add a budget to the proposal and complete the Modular Budget tab.', 'budgetVersions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_2_1/ApplicationTypeCode', 'You have a non-standard application type for the SF424', 'proposal',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_2_1/Revision/RevisionCode1', 'You have entered an invalid revision type for the SF424', 'grantsGov',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_2_1/Applicant/City', 'Organization''s city is not valid', 'proposal.Organization/Location',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_2_1/PhoneNumber', 'Phone number missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_2_1/Email', 'Email address missing for PD/PI.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_2_1/StateReview', 'Please answer the State Executive Order questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/SF424_2_1/StateReviewAvailableDate', 'Please answer the State Review Date questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded_2_0/PDPI', ' A PD/PI is required, please add a Principal Investigator to the proposal.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/RR_KeyPersonExpanded_2_0/PDPI/Profile/BioSketchsAttached/BioSketchAttached', 'Each investigator and key person must have a BioSketch. Upload the PDF on the Personnel Attachments panel, with "BIOSKETCH" as the attachment type.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PerformanceSite_2_0/AttachedFile', 'Performance Sites attachment is required since Performance Site Locations exceed the maximum of 29.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/ResearchTrainingPlan/HumanSubjectsInvolved', 'Human Subjects Involved is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_1_2/AdditionalInformation/ConcurrentSupport', 'Concurrent support response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS398_CoverPageSupplement_1_3/ClinicalTrial/isPhaseIIIClinicalTrial', 'Please answer the NIH-defined Phase III clinical trial question for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/AdditionalInformation/Citizenship', 'Citizenship is not valid for PI, please make sure a PI has been added and that person has a valid citizenship type indicated.', 'keyPersonnel',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/AdditionalInformation', 'Additional information is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/Sponsors', 'Sponsors is not valid, please make sure the appropriate attachments and questions have been answered.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/AdditionalInformation/FieldOfTraining', 'Field of training response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/AdditionalInformation/StemCells/isHumanStemCellsInvolved', 'Human Stem Cells response is not valid, please answer all required questions for this form within the Questions tab.', 'questions',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/ResearchTrainingPlan/SpecificAims', 'Specific Aims is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/ResearchTrainingPlan/ResearchStrategy', 'Research Strategy is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/ResearchTrainingPlan/RespectiveContributions', 'Respective Contributions is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/ResearchTrainingPlan/SelectionOfSponsorAndInstitution', 'Selection of Sponsor and Institution is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/ResearchTrainingPlan/ResponsibleConductOfResearch', 'Responsible Conduct of Research is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/AdditionalInformation/FellowshipTrainingAndCareerGoals', 'Goals for Fellowship Training and Career is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/AdditionalInformation/ActivitiesPlannedUnderThisAward', 'Activities Planned Under This Award is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_S2S_ERROR_ID VALUES(NULL)
/


INSERT INTO S2S_ERROR (S2S_ERROR_ID, MESSAGE_KEY, MESSAGE, FIX_LINK, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_S2S_ERROR_ID), '/GrantApplication/Forms/PHS_Fellowship_Supplemental_2_0/Sponsors/SponsorCosponsorInformation', 'Sponsor(s) and Co-Sponsor(s) Information is a required attachment for the PHS Fellowship Supplemental Form, please add this file in the proposal attachments panel.', 'abstractsAttachments',NOW(),'admin',1,UUID())
/

DELIMITER ;
