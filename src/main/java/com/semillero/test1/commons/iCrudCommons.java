package com.semillero.test1.commons;

public interface iCrudCommons <T, ID>{
    public T save (T entity);
    public T update(ID id, T entity);
    public T findById(ID id);
    public T delete(ID id);
}
