package de.fhb.systemintegration.wikipedics.dao.inter;

/**
 * This is an generic dao layer interface. Which defines
 * the generic method for the database access.
 *
 * @author mlelansky
 * @version 0.0.1
 * @param <T> the class type of the entity
 * @param <PK> the class type of the primary key
 */
public interface GenericDAO<T, PK> {

    /**
     * This method saves an object into the database.
     * @param object the object to store into the database
     * @return the successfully saved object
     */
    T save(final T object);

    /**
     * This method updates an object into the database.
     * @param object the object which should be updated
     * @return the successfully updated object
     */
    T update(final T object);

    /**
     * This methods drops an object from the database.
     * @param object the object which should be deleted
     */
    void delete(final T object);

    /**
     * This method find an object with a specific id.
     * @param id the id of the object
     * @return the founded object or an empty object
     */
    T findById(final PK id);
}
