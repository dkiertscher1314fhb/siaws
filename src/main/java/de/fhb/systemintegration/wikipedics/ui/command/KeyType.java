package de.fhb.systemintegration.wikipedics.ui.command;

/**
 * This is the key type enum.
 */
enum KeyType {

    /**
     * The secret key type.
     */
    SECRET_KEY("secret key"),

    /**
     * The access key type.
     */
    ACCESS_KEY("access key");

    /**
     * The readable name of the enum elements.
     */
    private final String name;

    /**
     * This the initialisation constructor of the enum.
     * @param _name the readable name of the enum type
     */
    private KeyType(final String _name) {
        this.name = _name;
    }

    /**
     * This method return the readable name of the enum elements.
     * @return the enum name
     */
    public String getName() {
        return this.name;
    }
}
