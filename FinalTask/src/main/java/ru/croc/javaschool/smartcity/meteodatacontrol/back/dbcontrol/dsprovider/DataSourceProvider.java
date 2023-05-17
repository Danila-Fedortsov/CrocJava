package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.dsprovider;

import javax.sql.DataSource;

/**
 * Интерфейс провайдера источника данных.
 *
 * @author Danila Fedortsov
 */
public interface DataSourceProvider {
    /**
     * Метод получения источника данных.
     *
     * @return источник данных
     */
    DataSource getDataSource();
}
