package org.vaadin.activiti.simpletravel.domain;

import org.apache.commons.lang.StringUtils;

public enum Country {

	AFGHANISTAN,
	ALBANIA,
	ALGERIA,
	AMERICAN_SAMOA,
	ANDORRA,
	ANGOLA,
	ANGUILLA,
	ANTARCTICA,
	ANTIGUA_AND_BARBUDA,
	ARGENTINA,
	ARMENIA,
	ARUBA,
	AUSTRALIA,
	AUSTRIA,
	AZERBAIJAN,
	BAHAMAS,
	BAHRAIN,
	BANGLADESH,
	BARBADOS,
	BELARUS,
	BELGIUM,
	BELIZE,
	BENIN,
	BERMUDA,
	BHUTAN,
	BOLIVIA,
	BOSNIA_AND_HERZEGOWINA,
	BOTSWANA,
	BOUVET_ISLAND,
	BRAZIL,
	BRITISH_INDIAN_OCEAN_TERRITORY,
	BRUNEI_DARUSSALAM,
	BULGARIA,
	BURKINA_FASO,
	BURUNDI,
	CAMBODIA,
	CAMEROON,
	CANADA,
	CAPE_VERDE,
	CAYMAN_ISLANDS,
	CENTRAL_AFRICAN_REPUBLIC,
	CHAD,
	CHILE,
	CHINA,
	CHRISTMAS_ISLAND,
	COCOS_ISLANDS,
	COLOMBIA,
	COMOROS,
	CONGO,
	COOK_ISLANDS,
	COSTA_RICA,
	COTE_D_IVOIRE("Côte d'Ivoire"),
	CROATIA,
	CUBA,
	CYPRUS,
	CZECH_REPUBLIC,
	DENMARK,
	DJIBOUTI,
	DOMINICA,
	DOMINICAN_REPUBLIC,
	EAST_TIMOR,
	ECUADOR,
	EGYPT,
	EL_SALVADOR,
	EQUATORIAL_GUINEA,
	ERITREA,
	ESTONIA,
	ETHIOPIA,
	FALKLAND_ISLANDS,
	FAROE_ISLANDS,
	FIJI,
	FINLAND,
	FRANCE,
	FRANCE_METROPOLITAN,
	FRENCH_GUIANA,
	FRENCH_POLYNESIA,
	FRENCH_SOUTHERN_TERRITORIES,
	GABON,
	GAMBIA,
	GEORGIA,
	GERMANY,
	GHANA,
	GIBRALTAR,
	GREECE,
	GREENLAND,
	GRENADA,
	GUADELOUPE,
	GUAM,
	GUATEMALA,
	GUINEA,
	GUINEA_BISSAU,
	GUYANA,
	HAITI,
	HEARD_AND_MC_DONALD_ISLANDS("Heard Island and McDonald Islands"),
	HONDURAS,
	HONG_KONG,
	HUNGARY,
	ICELAND,
	INDIA,
	INDONESIA,
	IRAN,
	IRAQ,
	IRELAND,
	ISRAEL,
	ITALY,
	JAMAICA,
	JAPAN,
	JORDAN,
	KAZAKHSTAN,
	KENYA,
	KIRIBATI,
	KOREA_DEMOCRATIC_PEOPLE_S_REPUBLIC_OF("Korea, Democratic People's Republic of"),
	KOREA_REPUBLIC_OF("Korea, Republic of"),
	KUWAIT,
	KYRG_PEOPLE_S_DEMOCRATIC_REPUBLIC("Kyrg People's Democratic Republic"),
	LATVIA,
	LEBANON,
	LESOTHO,
	LIBERIA,
	LIBYAN_ARAB_JAMAHIRIYA,
	LIECHTENSTEIN,
	LITHUANIA,
	LUXEMBOURG,
	MACAU,
	MACEDONIA_THE_FORMER_YUGOSLAV_REPUBLIC_OF("Macedonia, The Former Yugoslav Republic of"),
	MADAGASCAR,
	MALAWI,
	MALAYSIA,
	MALDIVES,
	MALI,
	MALTA,
	MARSHALL_ISLANDS,
	MARTINIQUE,
	MAURITANIA,
	MAURITIUS,
	MAYOTTE,
	MEXICO,
	MICRONESIA,
	MOLDOVA,
	MONACO,
	MONGOLIA,
	MONTENEGRO,
	MONTSERRAT,
	MOROCCO,
	MOZAMBIQUE,
	MYANMAR,
	NAMIBIA,
	NAURU,
	NEPAL,
	NETHERLANDS,
	NETHERLANDS_ANTILLES,
	NEW_CALEDONIA,
	NEW_ZEALAND,
	NICARAGUA,
	NIGER,
	NIGERIA,
	NIUE,
	NORFOLK_ISLAND,
	NORTHERN_MARIANA_ISLANDS,
	NORWAY,
	OMAN,
	PAKISTAN,
	PALAU,
	PANAMA,
	PAPUA_NEW_GUINEA,
	PARAGUAY,
	PERU,
	PHILIPPINES,
	PITCAIRN,
	POLAND,
	PORTUGAL,
	PUERTO_RICO,
	QATAR,
	REUNION,
	ROMANIA,
	RUSSIAN_FEDERATION,
	RWANDA,
	ST_HELENA,
	SAINT_KITTS_AND_NEVIS,
	SAINT_LUCIA,
	ST_PIERRE_AND_MIQUELON,
	SAINT_VINCENT_AND_THE_GRENADINES,
	SAMOA,
	SAN_MARINO,
	SAO_TOME_AND_PRINCIPE,
	SAUDI_ARABIA,
	SENEGAL,
	SERBIA,
	SEYCHELLES,
	SIERRA_LEONE,
	SINGAPORE,
	SLOVAKIA,
	SLOVENIA,
	SOLOMON_ISLANDS,
	SOMALIA,
	SOUTH_AFRICA,
	SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS,
	SPAIN,
	SRI_LANKA,
	SUDAN,
	SURINAME,
	SVALBARD_AND_JAN_MAYEN_ISLANDS,
	SWAZILAND,
	SWEDEN,
	SWITZERLAND,
	SYRIAN_ARAB_REPUBLIC,
	TAIWAN_PROVINCE_OF_CHINA("Taiwan, Province of China"),
	TAJIKISTAN,
	TANZANIA_UNITED_REPUBLIC_OF("Tanzania, United Republic of"),
	THAILAND,
	TOGO,
	TOKELAU,
	TONGA,
	TRINIDAD_AND_TOBAGO,
	TUNISIA,
	TURKEY,
	TURKMENISTAN,
	TURKS_AND_CAICOS_ISLANDS,
	TUVALU,
	UGANDA,
	UKRAINE,
	UNITED_ARAB_EMIRATES,
	UNITED_KINGDOM,
	UNITED_STATES,
	UNITED_STATES_MINOR_OUTLYING_ISLANDS,
	URUGUAY,
	UZBEKISTAN,
	VANUATU,
	VATICAN_CITY_STATE,
	VENEZUELA,
	VIETNAM,
	VIRGIN_ISLANDS_UK,
	VIRGIN_ISLANDS_US,
	WALLIS_AND_FUTUNA_ISLANDS,
	WESTERN_SAHARA,
	YEMEN,
	YUGOSLAVIA,
	ZAIRE,
	ZAMBIA,
	ZIMBABWE;

	private final String displayName;

	private Country() {
		displayName = replaceUnderscoresWithSpacesAndCapitalizeWords(name());
	}

	private Country(final String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	private static String replaceUnderscoresWithSpacesAndCapitalizeWords(final String text) {
		final String[] words = text.split("_");
		final StringBuilder sb = new StringBuilder();

		for (final String word : words) {
			if (word.equals("AND")) {
				sb.append("and");
			} else {
				sb.append(StringUtils.capitalize(word.toLowerCase()));
			}

			sb.append(" ");
		}

		return sb.toString().trim();
	}
}
