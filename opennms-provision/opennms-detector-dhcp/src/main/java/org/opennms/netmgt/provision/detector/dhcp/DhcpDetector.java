/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2008-2011 The OpenNMS Group, Inc.
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

package org.opennms.netmgt.provision.detector.dhcp;

import org.opennms.netmgt.provision.detector.dhcp.client.DhcpClient;
import org.opennms.netmgt.provision.detector.dhcp.request.DhcpRequest;
import org.opennms.netmgt.provision.detector.dhcp.response.DhcpResponse;
import org.opennms.netmgt.provision.support.BasicDetector;
import org.opennms.netmgt.provision.support.Client;
import org.opennms.netmgt.provision.support.ClientConversation.ResponseValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
/**
 * <p>DhcpDetector class.</p>
 *
 * @author ranger
 * @version $Id: $
 */
@Scope("prototype")
public class DhcpDetector extends BasicDetector<DhcpRequest, DhcpResponse> {
    
    private static final int DEFAULT_RETRY = 0;
    private static final int DEFAULT_TIMEOUT = 3000;
    
    /**
     * <p>Constructor for DhcpDetector.</p>
     */
    public DhcpDetector() {
        super("DHCP", 0);
        setTimeout(DEFAULT_TIMEOUT);
        setRetries(DEFAULT_RETRY);
    }

    /** {@inheritDoc} */
    @Override
    protected void onInit() {
        expectBanner(responseTimeGreaterThan(-1));
    }

    private ResponseValidator<DhcpResponse> responseTimeGreaterThan(final long num) {
        return new ResponseValidator<DhcpResponse>(){

            public boolean validate(DhcpResponse response) throws Exception {
                return response.validate(num);
            }
            
        };
    }

    /** {@inheritDoc} */
    @Override
    protected Client<DhcpRequest, DhcpResponse> getClient() {
        DhcpClient client = new DhcpClient();
        client.setRetries(1);
        return client;
    }

    
}
