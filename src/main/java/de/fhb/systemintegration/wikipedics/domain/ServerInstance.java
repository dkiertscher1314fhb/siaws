package de.fhb.systemintegration.wikipedics.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class stands for on running instances.
 *
 * @author mlelansky
 * @version 0.0.1
 */
@Entity
@Table(name = "SERVER_INSTANCE")
public final class ServerInstance {

    /**
     * This is the id of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * This is the id of the used image.
     */
    @Column(name = "IMAGE_ID")
    private String imageId;

    /**
     * This is the id of the running instance.
     */
    @Column(name = "INSTANCE_ID", nullable = false, unique = true)
    private String instanceId;

    /**
     * The public url of the instance.
     */
    @Column(name = "URL", unique = true)
    private String url;

    /**
     * This is the type of the server instance.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private ServerType type;

    /**
     * This is the default constructor.
     */
    public ServerInstance() {
        super();
    }

    /**
     * This method return the id of the entity.
     * @return the actual id of the entity
     */
    public Long getId() {
        return this.id;
    }

    /**
     * This method sets the new id of the entity.
     * @param _id the new id
     */
    public void setId(final Long _id) {
        this.id = _id;
    }

    /**
     * This method returns the actual used image id.
     * @return the image id
     */
    public String getImageId() {
        return this.imageId;
    }

    /**
     * This method sets the new image id.
     * @param _imageId the image id to set
     */
    public void setImageId(final String _imageId) {
        this.imageId = _imageId;
    }

    /**
     * This is method returns the actual used instance id.
     * @return the instance id
     */
    public String getInstanceId() {
        return this.instanceId;
    }

    /**
     * This method sets the instance id.
     * @param _instanceId the instance id to set
     */
    public void setInstanceId(final String _instanceId) {
        this.instanceId = _instanceId;
    }

    /**
     * This method returns the actual public url of the instance.
     * @return the public url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * This method sets the new public url of the instance.
     * @param _url the new public url
     */
    public void setUrl(final String _url) {
        this.url = _url;
    }

    /**
     * This method sets the actual server type.
     * @return the actual server type
     */
    public ServerType getType() {
        return this.type;
    }

    /**
     * This method sets the new server type of the instance.
     * @param _type the new server type
     */
    public void setType(final ServerType _type) {
        this.type = _type;
    }
}
