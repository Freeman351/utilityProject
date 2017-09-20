package com.freeman.payment.moneris.service;

/**
 * @author BDu
 * 
 */
public class AVSCode extends Domain {
	private String type;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final AVSCode A = new AVSCode(
			"A",
			"Street addresses match. The street addresses match but the postal/ZIP codes do not, "
					+ "or the request does not include the postal/ZIP code.",
			"P");
	public static final AVSCode B = new AVSCode(
			"B",
			"Street addresses match. Postal code not verified due to incompatible formats. "
					+ "(Acquirer sent both street address and postal code.)",
			"P");
	public static final AVSCode C = new AVSCode("C",
			"Street address and postal code not verified due to incompatible formats. "
					+ "(Acquirer sent both street address and postal code.)",
			"B");
	public static final AVSCode D = new AVSCode("D",
			"Street addresses and postal codes match.", "");
	public static final AVSCode G = new AVSCode("G",
			"Address information not verified for international transaction.",
			"S");
	public static final AVSCode I = new AVSCode("I",
			"Address information not verified.", "S");
	public static final AVSCode M = new AVSCode("M",
			"Street address and postal code match.", "");
	public static final AVSCode N = new AVSCode("N",
			"No match. Acquirer sent postal/ZIP code only, or street address only, "
					+ "or both postal code and street address.", "B");
	public static final AVSCode P = new AVSCode(
			"P",
			"Postal code match. Acquirer sent both postal code and street address, "
					+ "but street address not verified due to incompatible formats.",
			"S");
	public static final AVSCode R = new AVSCode(
			"R",
			"Retry: System unavailable or timed out. Issuer ordinarily performs "
					+ "its own AVS but was unavailable. Available for U.S. issuers only.",
			"B");
	public static final AVSCode S = new AVSCode(
			"S",
			"Not applicable. If present, replaced with G (for international) or U "
					+ "(for domestic) by V.I.P. Available for U.S. Issuers only.",
			"S");
	public static final AVSCode U = new AVSCode(
			"U",
			"Address not verified for domestic transaction. Visa tried to perform "
					+ "check on issuer's behalf but no AVS information was available on record, "
					+ "issuer is not an AVS participant, or AVS data was present in the request "
					+ "but issuer did not return an AVS result.", "B");
	public static final AVSCode W = new AVSCode("W",
			"Not applicable. If present, replaced with \"Z\" by V.I.P. Available for U.S. "
					+ "issuers only.", "S");
	public static final AVSCode X = new AVSCode("X",
			"Not applicable. If present, replaced with \"Y\" by V.I.P. "
					+ "Available for U.S. issuers only.", "B");
	public static final AVSCode Y = new AVSCode("Y",
			"Street address and postal code match.", "");
	public static final AVSCode Z = new AVSCode("Z",
			"Postal/ZIP matches; street address does not match or street address "
					+ "not included in request.", "S");

	/* An array of all codes defined. */
	private static final AVSCode[] codes = { A, B, C, D, G, I, M, N, P, R, S,
			U, W, X, Y, Z };

	private AVSCode(String aValue, String aCode, String type) {
		this.value = aValue;
		this.label = aCode;
		this.type = type;
	}

	public String toString() {
		return label;
	}

	/**
	 * Get all codes.
	 * 
	 * @return
	 */
	public static AVSCode[] getAllCodes() {
		return codes;
	}

	public static String getLabel(String aCode) {
		for (int i = 0, n = codes.length; i < n; i++) {
			if (codes[i].value.equalsIgnoreCase(aCode)) {
				return codes[i].toString();
			}
		}
		return "";
	}

	public static String getType(String aCode) {
		for (int i = 0, n = codes.length; i < n; i++) {
			if (codes[i].value.equalsIgnoreCase(aCode)) {
				return codes[i].type;
			}
		}
		return "B";
	}
}
