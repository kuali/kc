set define off
--Change 'Fiscal' in KC [Report Class=6] to Payment/Invoice
UPDATE REPORT_CLASS SET DESCRIPTION='Payment/Invoice' WHERE REPORT_CLASS_CODE=6;

-- Delete 'Invoice' Report Type[55] from 'Financial' Report Class[1]
delete from VALID_CLASS_REPORT_FREQ where REPORT_CODE = 55 and REPORT_CLASS_CODE = 1;

-- Insert new Report Types
INSERT INTO REPORT VALUES(1, SYS_GUID(), 63, 'SF 425', 'Y', sysdate, 'KRADBA');
INSERT INTO REPORT VALUES(1, SYS_GUID(), 64, 'Standard Institutional', 'N', sysdate, 'KRADBA');
INSERT INTO REPORT VALUES(1, SYS_GUID(), 65, 'Payment', 'N', sysdate, 'KRADBA');
INSERT INTO REPORT VALUES(1, SYS_GUID(), 66, 'SF 1034 Final', 'Y', sysdate, 'KRADBA');
INSERT INTO REPORT VALUES(1, SYS_GUID(), 67, 'Online', 'N', sysdate, 'KRADBA');
INSERT INTO REPORT VALUES(1, SYS_GUID(), 68, 'Other', 'N', sysdate, 'KRADBA');

-- Financial[1], SF424[63], (Quarterly[3], Semi-Annual[6], Annual[7], At Expiration[13])
insert into VALID_CLASS_REPORT_FREQ VALUES(480, 1, 63, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(412, 1, 63, 6, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(413, 1, 63, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(414, 1, 63, 13, sysdate, 'KRADBA', 1, sys_guid());

--Payment/Invoice[6], None[1], None[1]
insert into VALID_CLASS_REPORT_FREQ VALUES(415, 6, 1, 1, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], Final[5], (30 days post[10], 60 days post[11], 90 days post[12], at expiration [13], as required[14], 45 days post[33], 75 days post[43], 15 days post[58])
insert into VALID_CLASS_REPORT_FREQ VALUES(416, 6, 5, 10, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(417, 6, 5, 11, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(418, 6, 5, 12, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(419, 6, 5, 13, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(420, 6, 5, 14, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(421, 6, 5, 33, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(422, 6, 5, 43, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(423, 6, 5, 58, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], Form Provided...[35], (Monthly[2], Quarterly[3], Scheduled[4], One in advance[5], Annual[7], At expiration[13], As Required[14], 15 days after each quarter[56])
insert into VALID_CLASS_REPORT_FREQ VALUES(424, 6, 35, 2, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(425, 6, 35, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(426, 6, 35, 4, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(427, 6, 35, 5, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(428, 6, 35, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(429, 6, 35, 13, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(430, 6, 35, 14, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(431, 6, 35, 56, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], SF270[51], (Monthly[2], Quarterly[3], Scheduled[4], One in advance[5], Annual[7], At expiration[13], As Required[14], 15 days after each quarter[56])
insert into VALID_CLASS_REPORT_FREQ VALUES(432, 6, 51, 2, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(433, 6, 51, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(434, 6, 51, 4, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(435, 6, 51, 5, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(436, 6, 51, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(437, 6, 51, 13, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(438, 6, 51, 14, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(439, 6, 51, 56, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], Standard Institutional[64], (Monthly[2], Quarterly[3], Scheduled[4], One in advance[5], Annual[7], At expiration[13], As Required[14], 15 days after each quarter[56])
insert into VALID_CLASS_REPORT_FREQ VALUES(440, 6, 64, 2, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(441, 6, 64, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(442, 6, 64, 4, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(443, 6, 64, 5, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(444, 6, 64, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(445, 6, 64, 13, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(446, 6, 64, 14, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(447, 6, 64, 56, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], Standard Institutional[65], (Monthly[2], Quarterly[3], Scheduled[4], One in advance[5], Semi-Annual[6], Annual[7], Bi-Monthly[9], At expiration[13])
insert into VALID_CLASS_REPORT_FREQ VALUES(448, 6, 65, 2, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(449, 6, 65, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(450, 6, 65, 4, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(451, 6, 65, 5, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(452, 6, 65, 6, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(453, 6, 65, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(454, 6, 65, 9, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(455, 6, 65, 13, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], SF 1034 Final[66], (Monthly[2], Quarterly[3], Scheduled[4], One in advance[5], Annual[7], At expiration[13], As Required[14], 15 days after each quarter[56])
insert into VALID_CLASS_REPORT_FREQ VALUES(456, 6, 66, 2, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(457, 6, 66, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(458, 6, 66, 4, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(459, 6, 66, 5, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(460, 6, 66, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(461, 6, 66, 13, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(462, 6, 66, 14, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(463, 6, 66, 56, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], Online[67], (Monthly[2], Quarterly[3], Scheduled[4], One in advance[5], Annual[7], At expiration[13], As Required[14], 15 days after each quarter[56])
insert into VALID_CLASS_REPORT_FREQ VALUES(464, 6, 67, 2, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(465, 6, 67, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(466, 6, 67, 4, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(467, 6, 67, 5, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(468, 6, 67, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(469, 6, 67, 13, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(470, 6, 67, 14, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(471, 6, 67, 56, sysdate, 'KRADBA', 1, sys_guid());

-- Payment/Invoice[6], Other[68], (Monthly[2], Quarterly[3], Scheduled[4], One in advance[5], Annual[7], At expiration[13], As Required[14], 15 days after each quarter[56])
insert into VALID_CLASS_REPORT_FREQ VALUES(472, 6, 68, 2, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(473, 6, 68, 3, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(474, 6, 68, 4, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(475, 6, 68, 5, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(476, 6, 68, 7, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(477, 6, 68, 13, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(478, 6, 68, 14, sysdate, 'KRADBA', 1, sys_guid());
insert into VALID_CLASS_REPORT_FREQ VALUES(479, 6, 68, 56, sysdate, 'KRADBA', 1, sys_guid());