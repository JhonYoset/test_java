package com.semillero.test1.commons;

public interface iCrudCommonsDto <DTOReq, DTORes, ID>{
    public DTORes save (DTOReq entity);
    public DTORes update(ID id, DTOReq entity);
    public DTORes findById(ID id);
    public DTORes delete(ID id);
}
