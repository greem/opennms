package org.opennms.netmgt.config.collectd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.opennms.core.xml.ValidateUsing;

/**
 * Top-level element for the collectd-configuration.xml
 *  configuration file.
 */

@XmlRootElement(name="collectd-configuration")
@ValidateUsing("collectd-configuration.xsd")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectdConfiguration implements Serializable {
    private static final long serialVersionUID = 7603370550780666722L;

    /**
     * The maximum number of threads used for data
     *  collection.
     */
    @XmlAttribute(name="threads")
    private Integer m_threads;

    /**
     * Package encapsulating addresses eligible to have
     *  SNMP data collected from them.
     */
    @XmlElement(name="package")
    private List<Package> m_packages = new ArrayList<Package>();

    /**
     * Service collectors
     */
    @XmlElement(name="collector")
    private List<Collector> m_collectors = new ArrayList<Collector>();

    public CollectdConfiguration() {
        super();
    }

    /**
     * The maximum number of threads used for data collection.
     */
    public Integer getThreads() {
        return m_threads == null? 0 : m_threads;
    }

    public void setThreads(final Integer threads) {
        m_threads = threads;
    }

    public List<Collector> getCollectors() {
        return Collections.unmodifiableList(m_collectors);
    }

    public void setCollectors(final List<Collector> collectors) {
        m_collectors = new ArrayList<Collector>(collectors);
    }

    public void addCollector(final Collector collector) throws IndexOutOfBoundsException {
        m_collectors.add(collector);
    }

    public void addCollector(final String service, final String className) {
        m_collectors.add(new Collector(service, className));
    }

    /**
     * Method removeCollector.
     * 
     * @param collector
     * @return true if the object was removed from the collection.
     */
    public boolean removeCollector(final Collector collector) {
        return m_collectors.remove(collector);
    }

    public List<Package> getPackages() {
        return Collections.unmodifiableList(m_packages);
    }

    public void setPackages(final List<Package> packages) {
        m_packages = new ArrayList<Package>(packages);
    }

    public void addPackage(final Package p) throws IndexOutOfBoundsException {
        m_packages.add(p);
    }

    public boolean removePackage(final Package p) {
        return m_packages.remove(p);
    }

    public Package getPackage(final String packageName) {
        for (final Package pkg : m_packages) {
            if (pkg.getName().equals(packageName)) {
                return pkg;
            }
        }
        return null;
    }

    public CollectdConfiguration getCollectdConfigurationForPackage(String collectionPackageName) {
        if (collectionPackageName == null) return null;
        final Package pkg = getPackage(collectionPackageName);
        if (pkg == null) return null;

        final Set<String> seenCollectors = new HashSet<String>();
        final CollectdConfiguration newConfig = new CollectdConfiguration();
        newConfig.setThreads(getThreads());
        newConfig.addPackage(pkg);

        for (final Service service : pkg.getServices()) {
            seenCollectors.add(service.getName());
        }

        for (final Collector collector : getCollectors()) {
            if (seenCollectors.contains(collector.getService())) {
                newConfig.addCollector(collector);
            }
        }

        return newConfig;
    }

    @Override
    public int hashCode() {
        final int prime = 631;
        int result = 1;
        result = prime * result + ((m_collectors == null) ? 0 : m_collectors.hashCode());
        result = prime * result + ((m_packages == null) ? 0 : m_packages.hashCode());
        result = prime * result + ((m_threads == null) ? 0 : m_threads.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CollectdConfiguration)) {
            return false;
        }
        final CollectdConfiguration other = (CollectdConfiguration) obj;
        if (m_collectors == null) {
            if (other.m_collectors != null) {
                return false;
            }
        } else if (!m_collectors.equals(other.m_collectors)) {
            return false;
        }
        if (m_packages == null) {
            if (other.m_packages != null) {
                return false;
            }
        } else if (!m_packages.equals(other.m_packages)) {
            return false;
        }
        if (m_threads == null) {
            if (other.m_threads != null) {
                return false;
            }
        } else if (!m_threads.equals(other.m_threads)) {
            return false;
        }
        return true;
    }

}
