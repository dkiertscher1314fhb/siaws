package de.fhb.systemintegration.wikipedics.domain;

/**
 * This is an enum for the different ec2-instances.
 *
 * @author mlelansky
 * @version 0.0.1
 */
public enum ServerType {

    /**
     * This value stands for all rails instances.
     */
    RAILS_FRONTEND,

    /**
     * This value stands for all java instances.
     */
    JAVA_BACKEND,

    /**
     * This value stands for all neo4j instances.
     */
    NEO4J
}
