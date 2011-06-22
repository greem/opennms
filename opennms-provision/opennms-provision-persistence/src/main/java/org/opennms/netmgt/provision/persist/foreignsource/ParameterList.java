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

package org.opennms.netmgt.provision.persist.foreignsource;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
/**
 * <p>ParameterList class.</p>
 *
 * @author ranger
 * @version $Id: $
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="pluginConfigParameter")
public class ParameterList {
    public List<PluginParameter> parameter;
    /**
     * <p>Constructor for ParameterList.</p>
     */
    public ParameterList() {
        parameter = new LinkedList<PluginParameter>();
    }

    /**
     * <p>Constructor for ParameterList.</p>
     *
     * @param m a {@link java.util.Map} object.
     */
    public ParameterList(Map<String,String> m) {
        parameter = new LinkedList<PluginParameter>();
        for (Map.Entry<String,String> e : m.entrySet()) {
            parameter.add(new PluginParameter(e));
        }
    }

    /**
     * <p>Setter for the field <code>parameter</code>.</p>
     *
     * @param list a {@link java.util.List} object.
     */
    public void setParameter(List<PluginParameter> list) {
        parameter = list;
    }
    
    /**
     * <p>Getter for the field <code>parameter</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<PluginParameter> getParameter() {
        return parameter;
    }
}
