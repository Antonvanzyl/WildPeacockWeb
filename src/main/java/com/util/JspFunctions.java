/**
 * File: JspFunctions.java
 * Date: 30 Oct 2013
 * Author: Anton Van Zyl
 */
package com.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * @author Anton Van Zyl
 * 
 */
public class JspFunctions {

	private static final DecimalFormat RANDS_AND_CENTS_FORMATTER;

	static {
		final DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
		formatSymbols.setGroupingSeparator(' ');
		RANDS_AND_CENTS_FORMATTER = new DecimalFormat("R #,##0.00", formatSymbols);
	}

	public static String formatMoneyWithCents(final BigDecimal amount) {
		if (amount == null) {
			return "";
		}
		synchronized (RANDS_AND_CENTS_FORMATTER) {
			return RANDS_AND_CENTS_FORMATTER.format(amount.abs());
		}
	}
}
