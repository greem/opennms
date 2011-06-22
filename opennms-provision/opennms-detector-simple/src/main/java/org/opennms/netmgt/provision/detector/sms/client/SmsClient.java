/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2009-2011 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2011 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.netmgt.provision.detector.sms.client;

import java.io.IOException;
import java.net.InetAddress;

import org.opennms.netmgt.provision.detector.sms.response.SmsResponse;
import org.opennms.netmgt.provision.detector.simple.request.LineOrientedRequest;
import org.opennms.netmgt.provision.support.Client;

/**
 * Detector client implemenation for the <code>SmsDetector</code>
 *
 * @author <a href="mailto:david@opennms.org">David Hustace</a>
 * @version $Id: $
 */
public class SmsClient implements Client<LineOrientedRequest, SmsResponse> {
    
    private boolean m_isSupported = false;
    
    /**
     * <p>close</p>
     */
    public void close() {
        
    }

    /** {@inheritDoc} */
    public void connect(InetAddress address, int port, int timeout) throws IOException, Exception {
    }

    /**
     * <p>receiveBanner</p>
     *
     * @return a {@link org.opennms.netmgt.provision.detector.sms.response.SmsResponse} object.
     * @throws java.io.IOException if any.
     * @throws java.lang.Exception if any.
     */
    public SmsResponse receiveBanner() throws IOException, Exception {
        return receiveResponse();
    }

    /**
     * <p>sendRequest</p>
     *
     * @param request a {@link org.opennms.netmgt.provision.detector.simple.request.LineOrientedRequest} object.
     * @return a {@link org.opennms.netmgt.provision.detector.sms.response.SmsResponse} object.
     * @throws java.io.IOException if any.
     * @throws java.lang.Exception if any.
     */
    public SmsResponse sendRequest(LineOrientedRequest request) throws IOException, Exception {
        return null;
    }
    
    private SmsResponse receiveResponse() {
        SmsResponse smsResponse = new SmsResponse();
        return smsResponse;
    }

    /**
     * <p>setSupported</p>
     *
     * @param isSupported a boolean.
     */
    public void setSupported(boolean isSupported) {
        m_isSupported = isSupported;
    }

    /**
     * <p>isSupported</p>
     *
     * @return a boolean.
     */
    public boolean isSupported() {
        return m_isSupported;
    }

}
