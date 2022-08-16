package com.alkemy.disney.servicios;

import java.util.List;

public interface BaseServicio<E> {
    
    public List<E> listar()throws Exception;
    
    public E buscarPorId(Long id) throws Exception;
    
    public E guardar(E entidad) throws Exception;
    
    public E actualizar(E entidad) throws Exception;
    
    public void eliminar(Long id)throws Exception;
    
}
