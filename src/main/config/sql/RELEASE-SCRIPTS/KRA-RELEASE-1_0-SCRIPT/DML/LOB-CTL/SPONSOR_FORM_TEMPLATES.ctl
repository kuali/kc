load data
infile *
into table SPONSOR_FORM_TEMPLATES
INSERT
fields terminated by ',' optionally enclosed by "'"
TRAILING NULLCOLS 
(
SPONSOR_CODE, 
PACKAGE_NUMBER, 
PAGE_NUMBER, 
PAGE_DESCRIPTION, 
FORM_TEMPLATE LOBFILE(constant "DML/LOB-CTL/LOB-DATA/SPONSOR_FORM_TEMPLATES-FORM_TEMPLATE.dat") TERMINATED BY '<!--EOR-->', 
VER_NBR, 
OBJ_ID, 
FILE_NAME, 
CONTENT_TYPE,
UPDATE_USER "user", 
UPDATE_TIMESTAMP sysdate
)
BEGINDATA
'009800', '1', '1', 'Cover Page', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Cover Page.xsl', 'text/xml'
'009800', '1', '2', 'Budget Summary Period 1', '1', 'E2DA8BFB-284D-686B-D766-EF1811DEE6EF', 'Budget Summary Period 1.xsl', 'text/xml'
'009800', '1', '3', 'Budget Summary Period 2', '1', '24CD14FE-F3F4-95A0-769C-2B2A45D333EC', 'Budget Summary Period 2.xsl', 'text/xml'
'009800', '1', '4', 'Budget Summary Period 3', '1', '2D3899D3-8B06-877B-89C7-3D640CBC37A5', 'Budget Summary Period 3.xsl', 'text/xml'
'009800', '1', '5', 'Budget Summary Period 4', '1', '399B0E68-519D-4BC9-ECF2-08489C507C09', 'Budget Summary Period 4.xsl', 'text/xml'
'009800', '1', '6', 'Budget Summary Period 5', '1', 'DA5BD637-91E8-0978-1E47-2011DCAD01E1', 'Budget Summary Period 5.xsl', 'text/xml'
'000340', '3', '1', 'Cover  Page', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Cover  Page.xsl', 'text/xml'
'000340', '3', '2', 'Detailed Budget', '1', 'E5A3BDDF-2DC2-803B-8FD5-782B4C986E42', 'Detailed Budget.xsl', 'text/xml'
'000500', '7', '1', 'Cover Page', '3', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Cover Page.xsl', 'text/xml'
'000500', '7', '2', 'Budget Attachment', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Attachment.xsl', 'text/xml'
'000500', '7', '3', 'Budget Summary Period 1', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Summary Period 1.xsl', 'text/xml'
'000500', '7', '4', 'Budget Summary Period 2', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Summary Period 2.xsl', 'text/xml'
'000500', '7', '5', 'Budget Summary Period 3', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Summary Period 3.xsl', 'text/xml'
'000500', '7', '6', 'Budget Summary Period 4', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Summary Period 4.xsl', 'text/xml'
'000500', '7', '7', 'Budget Summary Period 5', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Summary Period 5.xsl', 'text/xml'
'000500', '7', '8', 'Budget Summary Total', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Summary Total.xsl', 'text/xml'
'000340', '2', '1', 'Cover Page', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Cover Page.xsl', 'text/xml'
'000340', '2', '2', 'Cover Page Continued', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'CoverPageContinued.xsl', 'text/xml'
'000340', '2', '3', 'Table of Contents', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Table of Contents.xsl', 'text/xml'
'000340', '2', '4', 'Resources', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Resources.xsl', 'text/xml'
'000340', '2', '5', 'Personnel Report', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Personnel Report.xsl', 'text/xml'
'000340', '2', '6', 'Personal Data', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Personal Data.xsl', 'text/xml'
'000340', '2', '7', 'Detailed Budget Period 1', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Detailed Budget Period 1.xsl', 'text/xml'
'000340', '2', '8', 'Page 2-Performance Sites Key Personnel', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Page 2-Performance Sites Key Personnel.xsl', 'text/xml'
'000340', '2', '9', 'Budget Entire Proposed Period', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Budget Entire Proposed Period.xsl', 'text/xml'
'000340', '2', '10', 'BioSketch', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'BioSketch.xsl', 'text/xml'
'000340', '2', '11', 'Additional Sites', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'additionalSites.xsl', 'text/xml'
'000340', '2', '12', 'Modular Budget', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Modular Budget.xsl', 'text/xml'
'000340', '2', '13', 'Checklist', '2', '72CBB604-7950-DE36-614E-D34FFFCF2E95', 'Checklist.xsl', 'text/xml'
'000340', '3', '3', 'Personnel Report', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Personnel Report.xsl', 'text/xml'
'000340', '3', '4', 'Progress Summary', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Progress Summary.xsl', 'text/xml'
'000340', '3', '5', 'Budget Justification', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Budget Justification.xsl', 'text/xml'
'009800', '1', '7', 'Budget Summary Total', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Budget Summary Total.xsl', 'text/xml'
'009800', '1', '8', 'Budget Attachment', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Budget Attachment.xsl', 'text/xml'
'000618', '6', '1', 'Assurance 424B Page 1', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Assurance 424B Page 1.xsl', 'text/xml'
'000618', '6', '2', 'Assurance 424B Page 2', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Assurance 424B Page 2.xsl', 'text/xml'
'000618', '6', '3', 'Assurance Compliance Page 1', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Assurance Compliance Page 1.xsl', 'text/xml'
'000618', '6', '4', 'Assurance Compliance Page 2', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Assurance Compliance Page 2.xsl', 'text/xml'
'000618', '6', '5', 'FA-Certs Page 1', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'FA-Certs Page 1.xsl', 'text/xml'
'000106', '4', '1', 'Cover Page', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Cover Page.xsl', 'text/xml'
'000106', '4', '2', 'Budget Attachment', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Budget Attachment.xsl', 'text/xml'
'000106', '4', '3', 'Budget Summary', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Budget Summary.xsl', 'text/xml'
'000106', '4', '4', 'Contract Certifications', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Contract Certifications.xsl', 'text/xml'
'000106', '4', '5', 'Enviornment', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Enviornment.xsl', 'text/xml'
'000106', '4', '6', 'Grant Certifications', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Grant Certifications.xsl', 'text/xml'
'000106', '4', '7', 'Human Subjects', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Human Subjects.xsl', 'text/xml'
'000124', '5', '1', 'Cover Page', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Cover Page.xsl', 'text/xml'
'000124', '5', '2', 'ARObud1', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'ARObud1.xsl', 'text/xml'
'000124', '5', '3', 'ARObud2', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'ARObud2.xsl', 'text/xml'
'000124', '5', '4', 'ARObud3', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'ARObud3.xsl', 'text/xml'
'000124', '5', '5', 'Facilities', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Facilities.xsl', 'text/xml'
'000124', '5', '6', 'MURI Cover Page 1', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'MURI Cover Page 1.xsl', 'text/xml'
'000124', '5', '7', 'MURI Cover Page 2', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'MURI Cover Page 2.xsl', 'text/xml'
'000124', '5', '8', 'Project Abstract', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Project Abstract.xsl', 'text/xml'
'000124', '5', '9', 'Proprietary', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'Proprietary.xsl', 'text/xml'
'000124', '5', '10', 'Summary', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'summary.xsl', 'text/xml'
'000160', '8', '1', 'MURI Cover Page 1', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'MURI Cover Page 1.xsl', 'text/xml'
'000160', '8', '2', 'MURI Cover Page 2', '2', 'E874674D-E719-5817-8788-CA785CF7980A', 'MURI Cover Page 2.xsl', 'text/xml'
