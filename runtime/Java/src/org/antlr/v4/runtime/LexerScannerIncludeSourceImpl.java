/*
 * [The "BSD license"]
 *  Copyright (c) 2015 Henrik Sorensen
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.antlr.v4.runtime;

import java.io.IOException;

/**
 * Implementation of interface that allow the lexer to include another file
 * into the scanning stream.
 * The lexer expects a CharStream object to be returned.
 */
public class LexerScannerIncludeSourceImpl implements LexerScannerIncludeSource {
	
	/**
	 * The embedSource method reads the fileName and optionally substitutes text
	 * before returning the CharStream for the file.
	 */
	public CharStream embedSource(String fileName, String substituteFrom, String substituteTo) {
		ANTLRInputStream istrm = null;
		try {
			istrm = new ANTLRFileStream(fileName);
			if (substituteFrom != null) {
				String beforeStream = String.copyValueOf(istrm.data, 0,
						istrm.size());
				String replacedStream = beforeStream.replaceAll(substituteFrom,
						substituteTo);
				istrm = new ANTLRInputStream(replacedStream);
				// set filename so it can be found in common token
				istrm.name=fileName;
			}
		} catch (IOException e) {
			// TODO: Add error handling
			e.printStackTrace();
		}

		return istrm;
	}
}
