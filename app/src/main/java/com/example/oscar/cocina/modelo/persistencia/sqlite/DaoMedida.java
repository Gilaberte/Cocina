package com.example.oscar.cocina.modelo.persistencia.sqlite;

import java.util.List;

/**
 * Created by oscar on 06/10/2015.
 */
public interface DaoMedida {

    List<String> getMedidas();

    long getMedidaIdByName(String s);
}
