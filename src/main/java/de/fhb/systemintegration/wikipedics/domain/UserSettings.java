package de.fhb.systemintegration.wikipedics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class stands for the needed user settings.
 */
@Entity
@Table(name = "USERSETTINGS")
public final class UserSettings {

    /**
     * This is the minimal access key length.
     */
    private static final int MIN_ACCESSKEY_LENGTH = 20;

    /**
     * This is the primary key of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * This is the aws access key of the user.
     */
    @Column(name = "ACCESSKEY", nullable = false,
            length = MIN_ACCESSKEY_LENGTH, unique = true)
    private String accesskey;

    /**
     * This is the aws secret key of the user.
     */
    @Column(name = "SECRETKEY", nullable = false, unique = true)
    private String secretkey;

    /**
     * This is the aws region of the user.
     */
    @Column(name = "REGION", nullable = false)
    private String region;

    /**
     * This is the default constructor.
     */
    public UserSettings() {
        super();
        this.setRegion("EU-WEST-1");
    }

    /**
     * This method return the actual id.
     * @return the id of the entity
     */
    public Long getId() {
        return this.id;
    }

    /**
     * This method returns the actual used accesskey.
     * @return the aws accesskey
     */
    public String getAccesskey() {
        return accesskey;
    }

    /**
     * This method updates the aws accesskey.
     * @param _accesskey the new used accesskey
     */
    public void setAccesskey(final String _accesskey) {
        this.accesskey = _accesskey;
    }

    /**
     * This method returns the actual used secretkey.
     * @return the actual aws secretkey
     */
    public String getSecretkey() {
        return secretkey;
    }

    /**
     * This method updates the aws secretkey.
     * @param _secretkey the new used secretkey
     */
    public void setSecretkey(final String _secretkey) {
        this.secretkey = _secretkey;
    }

    /**
     * This method return the actual used region.
     * @return the actual used region.
     */
    public String getRegion() {
        return region;
    }

    /**
     * The method sets the new region.
     * @param _region the new region to use
     */
    public void setRegion(final String _region) {
        this.region = _region;
    }
}
