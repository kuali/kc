

CREATE OR REPLACE package s2sRRPerfSites_V11Pkg as


procedure getStateName (as_state_code IN osp$state_code.state_code%type,
                           cur_type IN OUT result_sets.cur_generic);
procedure getCountryName (as_country_code IN osp$country_code.country_code%type,
                           cur_type IN OUT result_sets.cur_generic) ;

end;
/

CREATE OR REPLACE PACKAGE BODY s2sRRPerfSites_V11Pkg as



-------------------------------
-- procedure getStateName
-------------------------------
procedure getStateName
          (as_state_code IN osp$state_code.state_code%type,
           cur_type IN OUT result_sets.cur_generic) is

ls_string varchar2(200);
begin
		BEGIN

			CASE   	as_state_code
				WHEN 'AK' THEN ls_string := 'AK: Alaska';
				WHEN 'AZ' THEN ls_string := 'AZ: Arizona';
				WHEN 'AR' THEN ls_string := 'AR: Arkansas';
				WHEN 'CA' THEN ls_string := 'CA: California';
				WHEN 'CO' THEN ls_string := 'CO: Colorado';
				WHEN 'CT' THEN ls_string := 'CT: Connecticut';
				WHEN 'DC' THEN ls_string := 'DC: District of Columbia';
				WHEN 'DE' THEN ls_string := 'DE: Delaware';
				WHEN 'FL' THEN ls_string := 'FL: Florida';
				WHEN 'GA' THEN ls_string := 'GA: Georgia';
				WHEN 'HI' THEN ls_string := 'HI: Hawaii';
				WHEN 'ID' THEN ls_string := 'ID: Idaho';
				WHEN 'IL' THEN ls_string := 'IL: Illinois';
				WHEN 'IN' THEN ls_string := 'IN: Indiana';
				WHEN 'IA' THEN ls_string := 'IA: Iowa';
				WHEN 'KS' THEN ls_string := 'KS: Kansas';
				WHEN 'KY' THEN ls_string := 'KY: Kentucky';
				WHEN 'LA' THEN ls_string := 'LA: Louisiana';
				WHEN 'ME' THEN ls_string := 'ME: Maine';
				WHEN 'MD' THEN ls_string := 'MD: Maryland';
				WHEN 'MA' THEN ls_string := 'MA: Massachusetts';
				WHEN 'MI' THEN ls_string := 'MI: Michigan';
				WHEN 'MN' THEN ls_string := 'MN: Minnesota';
				WHEN 'MS' THEN ls_string := 'MS: Mississippi';
				WHEN 'MO' THEN ls_string := 'MO: Missouri';
				WHEN 'MT' THEN ls_string := 'MT: Montana';
				WHEN 'NE' THEN ls_string := 'NE: Nebraska';
				WHEN 'NV' THEN ls_string := 'NV: Nevada';
				WHEN 'NH' THEN ls_string := 'NH: New Hampshire';
				WHEN 'NJ' THEN ls_string := 'NJ: New Jersey';
				WHEN 'NM' THEN ls_string := 'NM: New Mexico';
				WHEN 'NY' THEN ls_string := 'NY: New York';
				WHEN 'NC' THEN ls_string := 'NC: North Carolina';
				WHEN 'ND' THEN ls_string := 'ND: North Dakota';
				WHEN 'OH' THEN ls_string := 'OH: Ohio';
				WHEN 'OK' THEN ls_string := 'OK: Oklahoma';
				WHEN 'OR' THEN ls_string := 'OR: Oregon';
				WHEN 'PA' THEN ls_string := 'PA: Pennsylvania';
				WHEN 'RI' THEN ls_string := 'RI: Rhode Island';
				WHEN 'SC' THEN ls_string := 'SC: South Carolina';
				WHEN 'SD' THEN ls_string := 'SD: South Dakota';
				WHEN 'TN' THEN ls_string := 'TN: Tennessee';
				WHEN 'TX' THEN ls_string := 'TX: Texas';
				WHEN 'UT' THEN ls_string := 'UT: Utah';
				WHEN 'VT' THEN ls_string := 'VT: Vermont';
				WHEN 'VA' THEN ls_string := 'VA: Virginia';
				WHEN 'WA' THEN ls_string := 'WA: Washington';
				WHEN 'WV' THEN ls_string := 'WV: West Virginia';
				WHEN 'WI' THEN ls_string := 'WI: Wisconsin';
				WHEN 'WY' THEN ls_string := 'WY: Wyoming';
				WHEN 'AS' THEN ls_string := 'AS: American Samoa';
				WHEN 'FM' THEN ls_string := 'FM: Federated States of Micronesia';
				WHEN 'GU' THEN ls_string := 'GU: Guam';
				WHEN 'MH' THEN ls_string := 'MH: Marshall Islands';
				WHEN 'MP' THEN ls_string := 'MP: Northern Mariana Islands';
				WHEN 'PW' THEN ls_string := 'PW: Palau';
				WHEN 'PR' THEN ls_string := 'PR: Puerto Rico';
				WHEN 'PS' THEN ls_string := 'PS: Trust Territory of Pacific';
				WHEN 'VI' THEN ls_string := 'VI: Virgin Islands of the U.S.';
				WHEN 'FQ' THEN ls_string := 'FQ: Baker Island';
				WHEN 'HQ' THEN ls_string := 'HQ: Howard Island';
				WHEN 'JQ' THEN ls_string := 'JQ: Johnston Atoll';
				WHEN 'KQ' THEN ls_string := 'KQ: Kingman Reef';
				WHEN 'MQ' THEN ls_string := 'MQ: Midway Islands';
				WHEN 'BQ' THEN ls_string := 'BQ: Navassa Island';
				WHEN 'LQ' THEN ls_string := 'LQ: Palmyra Atoll';
				WHEN 'WQ' THEN ls_string := 'WQ: Wake Island';
				WHEN 'AA' THEN ls_string := 'AA: APO/FPO Central and South America';
				WHEN 'AE' THEN ls_string := 'AE: APO/FPO Europe, Middle East, and Africa';
				WHEN 'AP' THEN ls_string := 'AP: APO/FPO Korea, Japan, Philippines, Other Pacific';
				ELSE ls_string := null;
			END CASE	;
		EXCEPTION
			When NO_DATA_FOUND then			
			ls_string := null;
		END;
		open cur_type for
		select ls_string as state_name
		from dual;

end;

-------------------------------
-- procedure getCountryName
-------------------------------
procedure getCountryName
          (as_country_code IN osp$country_code.country_code%type,
           cur_type IN OUT result_sets.cur_generic) is

ls_string varchar2(200);
begin
		BEGIN

			CASE   	as_country_code
				WHEN 'AFG' THEN ls_string := 'AFG: AFGHANISTAN';
				WHEN 'ALB' THEN ls_string := 'ALB: ALBANIA';
				WHEN 'DZA' THEN ls_string := 'DZA: ALGERIA';
				WHEN 'AND' THEN ls_string := 'AND: ANDORRA';
				WHEN 'AGO' THEN ls_string := 'AGO: ANGOLA';
				WHEN 'AIA' THEN ls_string := 'AIA: ANGUILLA';
				WHEN 'ATA' THEN ls_string := 'ATA: ANTARCTICA';
				WHEN 'ATG' THEN ls_string := 'ATG: ANTIGUA AND BARBUDA';
				WHEN 'ARG' THEN ls_string := 'ARG: ARGENTINA';
				WHEN 'ARM' THEN ls_string := 'ARM: ARMENIA';
				WHEN 'ABW' THEN ls_string := 'ABW: ARUBA';
				WHEN 'AUS' THEN ls_string := 'AUS: AUSTRALIA';
				WHEN 'AUT' THEN ls_string := 'AUT: AUSTRIA';
				WHEN 'AZE' THEN ls_string := 'AZE: AZERBAIJAN';
				WHEN 'BHS' THEN ls_string := 'BHS: BAHAMAS';
				WHEN 'BHR' THEN ls_string := 'BHR: BAHRAIN';
				WHEN 'BGD' THEN ls_string := 'BGD: BANGLADESH';
				WHEN 'BRB' THEN ls_string := 'BRB: BARBADOS';
				WHEN 'BLR' THEN ls_string := 'BLR: BELARUS';
				WHEN 'BEL' THEN ls_string := 'BEL: BELGIUM';
				WHEN 'BLZ' THEN ls_string := 'BLZ: BELIZE';
				WHEN 'BEN' THEN ls_string := 'BEN: BENIN';
				WHEN 'BMU' THEN ls_string := 'BMU: BERMUDA';
				WHEN 'BTN' THEN ls_string := 'BTN: BHUTAN';
				WHEN 'BOL' THEN ls_string := 'BOL: BOLIVIA';
				WHEN 'BIH' THEN ls_string := 'BIH: BOSNIA AND HERZEGOWINA';
				WHEN 'BWA' THEN ls_string := 'BWA: BOTSWANA';
				WHEN 'BVT' THEN ls_string := 'BVT: BOUVET ISLAND';
				WHEN 'BRA' THEN ls_string := 'BRA: BRAZIL';
				WHEN 'IOT' THEN ls_string := 'IOT: BRITISH INDIAN OCEAN TERRITORY';
				WHEN 'BRN' THEN ls_string := 'BRN: BRUNEI DARUSSALAM';
				WHEN 'BGR' THEN ls_string := 'BGR: BULGARIA';
				WHEN 'BFA' THEN ls_string := 'BFA: BURKINA FASO';
				WHEN 'BDI' THEN ls_string := 'BDI: BURUNDI';
				WHEN 'KHM' THEN ls_string := 'KHM: CAMBODIA';
				WHEN 'CMR' THEN ls_string := 'CMR: CAMEROON';
				WHEN 'CAN' THEN ls_string := 'CAN: CANADA';
				WHEN 'CPV' THEN ls_string := 'CPV: CAPE VERDE';
				WHEN 'CYM' THEN ls_string := 'CYM: CAYMAN ISLANDS';
				WHEN 'CAF' THEN ls_string := 'CAF: CENTRAL AFRICAN REPUBLIC';
				WHEN 'TCD' THEN ls_string := 'TCD: CHAD';
				WHEN 'CHL' THEN ls_string := 'CHL: CHILE';
				WHEN 'CHN' THEN ls_string := 'CHN: CHINA';
				WHEN 'CXR' THEN ls_string := 'CXR: CHRISTMAS ISLAND';
				WHEN 'CCK' THEN ls_string := 'CCK: COCOS (KEELING) ISLANDS';
				WHEN 'COL' THEN ls_string := 'COL: COLOMBIA';
				WHEN 'COM' THEN ls_string := 'COM: COMOROS';
				WHEN 'COD' THEN ls_string := 'COD: CONGO, Democratic Republic of (was Zaire)';
				WHEN 'COG' THEN ls_string := 'COG: CONGO, People''s Republic of';
				WHEN 'COK' THEN ls_string := 'COK: COOK ISLANDS';
				WHEN 'CRI' THEN ls_string := 'CRI: COSTA RICA';
				WHEN 'CIV' THEN ls_string := 'CIV: COTE D''IVOIRE';
				WHEN 'HRV' THEN ls_string := 'HRV: CROATIA (local name: Hrvatska)';
				WHEN 'CUB' THEN ls_string := 'CUB: CUBA';
				WHEN 'CYP' THEN ls_string := 'CYP: CYPRUS';
				WHEN 'CZE' THEN ls_string := 'CZE: CZECH REPUBLIC';
				WHEN 'DNK' THEN ls_string := 'DNK: DENMARK';
				WHEN 'DJI' THEN ls_string := 'DJI: DJIBOUTI';
				WHEN 'DMA' THEN ls_string := 'DMA: DOMINICA';
				WHEN 'DOM' THEN ls_string := 'DOM: DOMINICAN REPUBLIC';
				WHEN 'TLS' THEN ls_string := 'TLS: EAST TIMOR';
				WHEN 'ECU' THEN ls_string := 'ECU: ECUADOR';
				WHEN 'EGY' THEN ls_string := 'EGY: EGYPT';
				WHEN 'SLV' THEN ls_string := 'SLV: EL SALVADOR';
				WHEN 'GNQ' THEN ls_string := 'GNQ: EQUATORIAL GUINEA';
				WHEN 'ERI' THEN ls_string := 'ERI: ERITREA';
				WHEN 'EST' THEN ls_string := 'EST: ESTONIA';
				WHEN 'ETH' THEN ls_string := 'ETH: ETHIOPIA';
				WHEN 'FLK' THEN ls_string := 'FLK: FALKLAND ISLANDS (MALVINAS)';
				WHEN 'FRO' THEN ls_string := 'FRO: FAROE ISLANDS';
				WHEN 'FJI' THEN ls_string := 'FJI: FIJI';
				WHEN 'FIN' THEN ls_string := 'FIN: FINLAND';
				WHEN 'FRA' THEN ls_string := 'FRA: FRANCE';
				WHEN 'FXX' THEN ls_string := 'FXX: FRANCE, METROPOLITAN';
				WHEN 'GUF' THEN ls_string := 'GUF: FRENCH GUIANA';
				WHEN 'PYF' THEN ls_string := 'PYF: FRENCH POLYNESIA';
				WHEN 'ATF' THEN ls_string := 'ATF: FRENCH SOUTHERN TERRITORIES';
				WHEN 'GAB' THEN ls_string := 'GAB: GABON';
				WHEN 'GMB' THEN ls_string := 'GMB: GAMBIA';
				WHEN 'GEO' THEN ls_string := 'GEO: GEORGIA';
				WHEN 'DEU' THEN ls_string := 'DEU: GERMANY';
				WHEN 'GHA' THEN ls_string := 'GHA: GHANA';
				WHEN 'GIB' THEN ls_string := 'GIB: GIBRALTAR';
				WHEN 'GRC' THEN ls_string := 'GRC: GREECE';
				WHEN 'GRL' THEN ls_string := 'GRL: GREENLAND';
				WHEN 'GRD' THEN ls_string := 'GRD: GRENADA';
				WHEN 'GLP' THEN ls_string := 'GLP: GUADELOUPE';
				WHEN 'GTM' THEN ls_string := 'GTM: GUATEMALA';
				WHEN 'GIN' THEN ls_string := 'GIN: GUINEA';
				WHEN 'GNB' THEN ls_string := 'GNB: GUINEA-BISSAU';
				WHEN 'GUY' THEN ls_string := 'GUY: GUYANA';
				WHEN 'HTI' THEN ls_string := 'HTI: HAITI';
				WHEN 'HMD' THEN ls_string := 'HMD: HEARD AND MC DONALD ISLANDS';
				WHEN 'HND' THEN ls_string := 'HND: HONDURAS';
				WHEN 'HKG' THEN ls_string := 'HKG: HONG KONG';
				WHEN 'HUN' THEN ls_string := 'HUN: HUNGARY';
				WHEN 'ISL' THEN ls_string := 'ISL: ICELAND';
				WHEN 'IND' THEN ls_string := 'IND: INDIA';
				WHEN 'IDN' THEN ls_string := 'IDN: INDONESIA';
				WHEN 'IRN' THEN ls_string := 'IRN: IRAN (ISLAMIC REPUBLIC OF)';
				WHEN 'IRQ' THEN ls_string := 'IRQ: IRAQ';
				WHEN 'IRL' THEN ls_string := 'IRL: IRELAND';
				WHEN 'ISR' THEN ls_string := 'ISR: ISRAEL';
				WHEN 'ITA' THEN ls_string := 'ITA: ITALY';
				WHEN 'JAM' THEN ls_string := 'JAM: JAMAICA';
				WHEN 'JPN' THEN ls_string := 'JPN: JAPAN';
				WHEN 'JOR' THEN ls_string := 'JOR: JORDAN';
				WHEN 'KAZ' THEN ls_string := 'KAZ: KAZAKHSTAN';
				WHEN 'KEN' THEN ls_string := 'KEN: KENYA';
				WHEN 'KIR' THEN ls_string := 'KIR: KIRIBATI';
				WHEN 'PRK' THEN ls_string := 'PRK: KOREA, DEMOCRATIC PEOPLE''S REPUBLIC OF';
				WHEN 'KOR' THEN ls_string := 'KOR: KOREA, REPUBLIC OF';
				WHEN 'KWT' THEN ls_string := 'KWT: KUWAIT';
				WHEN 'KGZ' THEN ls_string := 'KGZ: KYRGYZSTAN';
				WHEN 'LAO' THEN ls_string := 'LAO: LAO PEOPLE''S DEMOCRATIC REPUBLIC';
				WHEN 'LVA' THEN ls_string := 'LVA: LATVIA';
				WHEN 'LBN' THEN ls_string := 'LBN: LEBANON';
				WHEN 'LSO' THEN ls_string := 'LSO: LESOTHO';
				WHEN 'LBR' THEN ls_string := 'LBR: LIBERIA';
				WHEN 'LBY' THEN ls_string := 'LBY: LIBYAN ARAB JAMAHIRIYA';
				WHEN 'LIE' THEN ls_string := 'LIE: LIECHTENSTEIN';
				WHEN 'LTU' THEN ls_string := 'LTU: LITHUANIA';
				WHEN 'LUX' THEN ls_string := 'LUX: LUXEMBOURG';
				WHEN 'MAC' THEN ls_string := 'MAC: MACAU';
				WHEN 'MKD' THEN ls_string := 'MKD: MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF';
				WHEN 'MDG' THEN ls_string := 'MDG: MADAGASCAR';
				WHEN 'MWI' THEN ls_string := 'MWI: MALAWI';
				WHEN 'MYS' THEN ls_string := 'MYS: MALAYSIA';
				WHEN 'MDV' THEN ls_string := 'MDV: MALDIVES';
				WHEN 'MLI' THEN ls_string := 'MLI: MALI';
				WHEN 'MLT' THEN ls_string := 'MLT: MALTA';
				WHEN 'MTQ' THEN ls_string := 'MTQ: MARTINIQUE';
				WHEN 'MRT' THEN ls_string := 'MRT: MAURITANIA';
				WHEN 'MUS' THEN ls_string := 'MUS: MAURITIUS';
				WHEN 'MYT' THEN ls_string := 'MYT: MAYOTTE';
				WHEN 'MEX' THEN ls_string := 'MEX: MEXICO';
				WHEN 'MDA' THEN ls_string := 'MDA: MOLDOVA, REPUBLIC OF';
				WHEN 'MCO' THEN ls_string := 'MCO: MONACO';
				WHEN 'MNG' THEN ls_string := 'MNG: MONGOLIA';
				WHEN 'MSR' THEN ls_string := 'MSR: MONTSERRAT';
				WHEN 'MAR' THEN ls_string := 'MAR: MOROCCO';
				WHEN 'MOZ' THEN ls_string := 'MOZ: MOZAMBIQUE';
				WHEN 'MMR' THEN ls_string := 'MMR: MYANMAR';
				WHEN 'NAM' THEN ls_string := 'NAM: NAMIBIA';
				WHEN 'NRU' THEN ls_string := 'NRU: NAURU';
				WHEN 'NPL' THEN ls_string := 'NPL: NEPAL';
				WHEN 'NLD' THEN ls_string := 'NLD: NETHERLANDS';
				WHEN 'ANT' THEN ls_string := 'ANT: NETHERLANDS ANTILLES';
				WHEN 'NCL' THEN ls_string := 'NCL: NEW CALEDONIA';
				WHEN 'NZL' THEN ls_string := 'NZL: NEW ZEALAND';
				WHEN 'NIC' THEN ls_string := 'NIC: NICARAGUA';
				WHEN 'NER' THEN ls_string := 'NER: NIGER';
				WHEN 'NGA' THEN ls_string := 'NGA: NIGERIA';
				WHEN 'NIU' THEN ls_string := 'NIU: NIUE';
				WHEN 'NFK' THEN ls_string := 'NFK: NORFOLK ISLAND';
				WHEN 'NOR' THEN ls_string := 'NOR: NORWAY';
				WHEN 'OMN' THEN ls_string := 'OMN: OMAN';
				WHEN 'PAK' THEN ls_string := 'PAK: PAKISTAN';
				WHEN 'PSE' THEN ls_string := 'PSE: PALESTINIAN TERRITORY, Occupied';
				WHEN 'PAN' THEN ls_string := 'PAN: PANAMA';
				WHEN 'PNG' THEN ls_string := 'PNG: PAPUA NEW GUINEA';
				WHEN 'PRY' THEN ls_string := 'PRY: PARAGUAY';
				WHEN 'PER' THEN ls_string := 'PER: PERU';
				WHEN 'PHL' THEN ls_string := 'PHL: PHILIPPINES';
				WHEN 'PCN' THEN ls_string := 'PCN: PITCAIRN';
				WHEN 'POL' THEN ls_string := 'POL: POLAND';
				WHEN 'PRT' THEN ls_string := 'PRT: PORTUGAL';
				WHEN 'QAT' THEN ls_string := 'QAT: QATAR';
				WHEN 'REU' THEN ls_string := 'REU: REUNION';
				WHEN 'ROU' THEN ls_string := 'ROU: ROMANIA';
				WHEN 'RUS' THEN ls_string := 'RUS: RUSSIAN FEDERATION';
				WHEN 'RWA' THEN ls_string := 'RWA: RWANDA';
				WHEN 'KNA' THEN ls_string := 'KNA: SAINT KITTS AND NEVIS';
				WHEN 'LCA' THEN ls_string := 'LCA: SAINT LUCIA';
				WHEN 'VCT' THEN ls_string := 'VCT: SAINT VINCENT AND THE GRENADINES';
				WHEN 'WSM' THEN ls_string := 'WSM: SAMOA';
				WHEN 'SMR' THEN ls_string := 'SMR: SAN MARINO';
				WHEN 'STP' THEN ls_string := 'STP: SAO TOME AND PRINCIPE';
				WHEN 'SAU' THEN ls_string := 'SAU: SAUDI ARABIA';
				WHEN 'SEN' THEN ls_string := 'SEN: SENEGAL';
				WHEN 'SYC' THEN ls_string := 'SYC: SEYCHELLES';
				WHEN 'SLE' THEN ls_string := 'SLE: SIERRA LEONE';
				WHEN 'SGP' THEN ls_string := 'SGP: SINGAPORE';
				WHEN 'SVK' THEN ls_string := 'SVK: SLOVAKIA (Slovak Republic)';
				WHEN 'SVN' THEN ls_string := 'SVN: SLOVENIA';
				WHEN 'SLB' THEN ls_string := 'SLB: SOLOMON ISLANDS';
				WHEN 'SOM' THEN ls_string := 'SOM: SOMALIA';
				WHEN 'ZAF' THEN ls_string := 'ZAF: SOUTH AFRICA';
				WHEN 'SGS' THEN ls_string := 'SGS: SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS';
				WHEN 'ESP' THEN ls_string := 'ESP: SPAIN';
				WHEN 'LKA' THEN ls_string := 'LKA: SRI LANKA';
				WHEN 'SHN' THEN ls_string := 'SHN: ST. HELENA';
				WHEN 'SPM' THEN ls_string := 'SPM: ST. PIERRE AND MIQUELON';
				WHEN 'SDN' THEN ls_string := 'SDN: SUDAN';
				WHEN 'SUR' THEN ls_string := 'SUR: SURINAME';
				WHEN 'SJM' THEN ls_string := 'SJM: SVALBARD AND JAN MAYEN ISLANDS';
				WHEN 'SWZ' THEN ls_string := 'SWZ: SWAZILAND';
				WHEN 'SWE' THEN ls_string := 'SWE: SWEDEN';
				WHEN 'CHE' THEN ls_string := 'CHE: SWITZERLAND';
				WHEN 'SYR' THEN ls_string := 'SYR: SYRIAN ARAB REPUBLIC';
				WHEN 'TWN' THEN ls_string := 'TWN: TAIWAN';
				WHEN 'TJK' THEN ls_string := 'TJK: TAJIKISTAN';
				WHEN 'TZA' THEN ls_string := 'TZA: TANZANIA, UNITED REPUBLIC OF';
				WHEN 'THA' THEN ls_string := 'THA: THAILAND';
				WHEN 'TGO' THEN ls_string := 'TGO: TOGO';
				WHEN 'TKL' THEN ls_string := 'TKL: TOKELAU';
				WHEN 'TON' THEN ls_string := 'TON: TONGA';
				WHEN 'TTO' THEN ls_string := 'TTO: TRINIDAD AND TOBAGO';
				WHEN 'TUN' THEN ls_string := 'TUN: TUNISIA';
				WHEN 'TUR' THEN ls_string := 'TUR: TURKEY';
				WHEN 'TKM' THEN ls_string := 'TKM: TURKMENISTAN';
				WHEN 'TCA' THEN ls_string := 'TCA: TURKS AND CAICOS ISLANDS';
				WHEN 'TUV' THEN ls_string := 'TUV: TUVALU';
				WHEN 'UGA' THEN ls_string := 'UGA: UGANDA';
				WHEN 'UKR' THEN ls_string := 'UKR: UKRAINE';
				WHEN 'ARE' THEN ls_string := 'ARE: UNITED ARAB EMIRATES';
				WHEN 'GBR' THEN ls_string := 'GBR: UNITED KINGDOM';
				WHEN 'USA' THEN ls_string := 'USA: UNITED STATES';
				WHEN 'UMI' THEN ls_string := 'UMI: UNITED STATES MINOR OUTLYING ISLANDS';
				WHEN 'URY' THEN ls_string := 'URY: URUGUAY';
				WHEN 'UZB' THEN ls_string := 'UZB: UZBEKISTAN';
				WHEN 'VUT' THEN ls_string := 'VUT: VANUATU';
				WHEN 'VAT' THEN ls_string := 'VAT: VATICAN CITY STATE (HOLY SEE)';
				WHEN 'VEN' THEN ls_string := 'VEN: VENEZUELA';
				WHEN 'VNM' THEN ls_string := 'VNM: VIET NAM';
				WHEN 'VGB' THEN ls_string := 'VGB: VIRGIN ISLANDS (BRITISH)';
				WHEN 'WLF' THEN ls_string := 'WLF: WALLIS AND FUTUNA ISLANDS';
				WHEN 'ESH' THEN ls_string := 'ESH: WESTERN SAHARA';
				WHEN 'YEM' THEN ls_string := 'YEM: YEMEN';
				WHEN 'YUG' THEN ls_string := 'YUG: YUGOSLAVIA';
				WHEN 'ZMB' THEN ls_string := 'ZMB: ZAMBIA';
				WHEN 'ZWE' THEN ls_string := 'ZWE: ZIMBABWE';

				ELSE ls_string := null;
			END CASE	;
			EXCEPTION
				When NO_DATA_FOUND then			
				ls_string := null;
			END;
			open cur_type for
			select ls_string as country_name
			from dual;				
				
end;
END;

/


