/**
 * File: IOUtils.java
 * Date: 09 Nov 2013
 * Author: Anton Van Zyl
 */
package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Anton Van Zyl
 *
 */
public class IOUtils {

	protected static long copy(InputStream input, OutputStream output)
	        throws IOException {
	    byte[] buffer = new byte[4096]; 
	    long count = 0L;
	    int n = 0;
	    while (-1 != (n = input.read(buffer))) {
	        output.write(buffer, 0, n);
	        count += n;
	    }
	    return count;
	}
	
}
