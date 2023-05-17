package ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.dsprovider.impl;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.property.PropertyContainer;
import ru.croc.javaschool.smartcity.meteodatacontrol.back.dbcontrol.dsprovider.DataSourceProvider;

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
     * Контейнер со свойствами базы данных.
     */
    private final PropertyContainer propertyContainer;

    /**
     * Создаёт {@link DerbyDataSourceProvider}.
     *
     * @param container контейнер со свойствами базы данных
     */
    public DerbyDataSourceProvider(PropertyContainer container) {
        this.propertyContainer = container;
    }

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
        this.dataSource.setDatabaseName(propertyContainer.getProperty("database.name"));
        String username = propertyContainer.getProperty("database.username");
        String password = propertyContainer.getProperty("database.password");
        if (!username.isEmpty() && !password.isEmpty()) {
            dataSource.setUser(username);
            dataSource.setPassword(password);
        }
        dataSource.setCreateDatabase("create");
    }
}
