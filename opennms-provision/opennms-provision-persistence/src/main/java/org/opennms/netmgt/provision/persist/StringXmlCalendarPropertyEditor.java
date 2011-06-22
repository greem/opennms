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

package org.opennms.netmgt.provision.persist;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>StringXmlCalendarPropertyEditor class.</p>
 *
 * @author ranger
 * @version $Id: $
 */
public class StringXmlCalendarPropertyEditor extends PropertyEditorSupport implements PropertyEditor {
    
    /** {@inheritDoc} */
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(DatatypeFactory.newInstance().newXMLGregorianCalendar(text));
        } catch (DatatypeConfigurationException e) {
            throw new IllegalArgumentException("Unable to convert " + text + " to and XMLCalender");
        }
    }

    /**
     * <p>getAsText</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAsText() {
        return ((XMLGregorianCalendar)getValue()).toXMLFormat();
    } 
}
