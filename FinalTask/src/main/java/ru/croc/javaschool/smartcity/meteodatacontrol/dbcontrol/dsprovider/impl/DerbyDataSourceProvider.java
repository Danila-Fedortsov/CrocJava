package ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.dsprovider.impl;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.dsprovider.DataSourceProvider;
import ru.croc.javaschool.smartcity.meteodatacontrol.dbcontrol.property.PropertyContainer;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Провайдер источника данных для работы с Derby (JavaDB).
 *
 * @author Danila Fedortsov
 */
public class DerbyDataSourceProvider implements DataSourceProvider {

    /**
     * Источник данных.
     */
    private EmbeddedDataSource dataSource;

    /**
     * Возвращает источник данных.
     *
     * @return источник данных
     */
    @Override
    public DataSource getDataSource() {
        if (Objects.isNull(dataSource)) {
            createDataSource();
        }
        return dataSource;
    }

    /**
     * Создает источник данных.
     */
    private void createDataSource() {
        this.dataSource = new EmbeddedDataSource();
        this.dataSource.setDatabaseName(PropertyContainer.getProperty("database.name"));
        String username = PropertyContainer.getProperty("database.username");
        String password = PropertyContainer.getProperty("database.password");
        if (!username.isEmpty() && !password.isEmpty()) {
            dataSource.setUser(username);
            dataSource.setPassword(password);
        }
        dataSource.setCreateDatabase("create");
    }
}
