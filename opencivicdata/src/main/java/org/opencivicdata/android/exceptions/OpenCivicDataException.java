package org.opencivicdata.android.exceptions;

/*
 * This file is a part of Open Civic Data.
 *
 * Copyright (c) Sunlight Foundation, 2014 under the terms and conditions
 * of the BSD-3 license in the LICENSE file contained in this source
 * distribution.
 *
 * Contributors:
 * - Paul R. Tagliamonte <paultag@sunlightfoundation.com>
 */

public class OpenCivicDataException extends RuntimeException {
    public OpenCivicDataException(String e) {
        super(e);
    }
}
