package de.fhb.systemintegration.wikipedics.dao.inter;

import java.util.List;
import java.util.Map;

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

    /**
     * This method method find a list of entities by a named query
     * without parameters.
     * @param query the name of the named query
     * @return the list of entities
     */
    List<T> findByNamedQuery(final String query);

    /**
     * This method find a list of entities by a named query with parameters.
     * @param query the name of the named query
     * @param params the parameters of the query
     * @return the list of entities
     */
    List<T> findByNamedQuery(final String query, final Map<String, ?> params);

    /**
     * This method returns the all entries of the table.
     * @return all entries of the table
     */
    List<T> findAll();
}
