package de.fhb.systemintegration.wikipedics.dao.inter.impl;

import de.fhb.systemintegration.wikipedics.dao.inter.GenericDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements the GenericDAO interface.
 *
 * @author mlelansky
 * @version 0.0.1
 * @param <T> the entity type
 * @param <PK> the primary key type
 */
abstract class GenericDAOImpl<T, PK> implements GenericDAO<T, PK>,
        AutoCloseable {

    /**
     * This is the actual used entity manager instance.
     */
    private final EntityManager em;
    /**
     * This is the actual used entity transaction.
     */
    private final EntityTransaction transaction;

    /**
     * This is the default constructor of the class.
     */
    public GenericDAOImpl() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("wikipedics");
        this.em = factory.createEntityManager();
        this.transaction = this.em.getTransaction();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T save(final T object) {
        T result = null;
        this.transaction.begin();
        try {
            result = (T) this.getEntityClass().newInstance();
            this.em.persist(object);
            this.em.flush();
            transaction.commit();
            result = object;
        } catch (InstantiationException | IllegalAccessException e) {
            System.err.println(e.getLocalizedMessage());
        } catch (RuntimeException e) {
            if (this.transaction.isActive()) {
                this.transaction.rollback();
            }
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T update(final T object) {
        T result = null;
        this.transaction.begin();
        try {
            result = (T) this.getEntityClass().newInstance();
            this.em.persist(object);
            this.em.flush();
            this.transaction.commit();
            result = object;
        } catch (InstantiationException | IllegalAccessException e) {
                System.err.println(e.getLocalizedMessage());
        } catch (RuntimeException e) {
            if (this.transaction.isActive()) {
                this.transaction.rollback();
            }
        }
        return result;
    }

    @Override
    public void delete(final T object) {
        this.transaction.begin();
        try {
            this.em.remove(object);
            this.em.flush();
            this.transaction.commit();
        } catch (RuntimeException e) {
            if (this.transaction.isActive()) {
                this.transaction.rollback();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(final PK id) {
        T object = (T) this.em.find(this.getEntityClass(), id);
        if (object == null) {
            try {
                object = (T) this.getEntityClass().newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        return object;
    }

    @Override
    public List<T> findAll() {
        List<T> fullList = new ArrayList<>();
        TypedQuery<? extends List> typedQuery = this.getEntityManager().
                createQuery("SELECT entity FROM "
                        + this.getEntityClass().getSimpleName() + " entity",
                        fullList.getClass());
        fullList.addAll((Collection<? extends T>) typedQuery.getResultList());
        return fullList;
    }

    @Override
    public List<T> findByNamedQuery(final String query) {
        return this.findByNamedQuery(query, new HashMap<String, Object>());
    }

    @Override
    public List<T> findByNamedQuery(final String query,
                                       final Map<String, ?> params) {
        List<T> resultList = new ArrayList<>();
        TypedQuery<? extends List> typedQuery = this.em.
                createNamedQuery(query, resultList.getClass());
        for (Map.Entry<String, ?> entry: params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            typedQuery.setParameter(key, value);
        }
        resultList.addAll((Collection<? extends T>) typedQuery.getResultList());
        return  resultList;
    }

    /**
     * This method returns the actual entity manager.
     * @return the entity manager
     */
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        this.em.close();
    }

    /**
     * This method return the entity class.
     * @return the entity class.
     */
    @SuppressWarnings("unchecked")
    private Class<?> getEntityClass() {
        return ((Class) ((ParameterizedType) this.getClass().
                getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
