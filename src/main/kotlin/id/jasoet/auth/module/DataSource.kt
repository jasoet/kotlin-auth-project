package id.jasoet.auth.module

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ebean.Database
import io.ebean.DatabaseFactory
import io.ebean.config.DatabaseConfig
import org.koin.dsl.module
import javax.sql.DataSource

val dataSourceModule = module {

    single<Config> {
        ConfigFactory.load()
    }

    single<DataSource> {
        buildDataSource(get())
    }

    single {
        buildEbeanDatabase(get(), "EbeanDataSource", true)
    }

}

private fun buildDataSource(config: Config): HikariDataSource {
    val hikariConfig = HikariConfig()
    hikariConfig.poolName = config.getString("dataSource.poolName")
    hikariConfig.jdbcUrl = config.getString("dataSource.url")
    hikariConfig.username = config.getString("dataSource.username")
    hikariConfig.password = config.getString("dataSource.password")
    hikariConfig.driverClassName = config.getString("dataSource.driverClassName")

    hikariConfig.addDataSourceProperty("cachePrepStmts", "true")
    hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250")
    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

    return HikariDataSource(hikariConfig)
}

private fun buildEbeanDatabase(dataSource: DataSource, name: String, default: Boolean): Database {
    val ebeanConfig = DatabaseConfig().apply {
        this.name = name
        setDataSource(dataSource)
        isDefaultServer = default
    }

    return DatabaseFactory.create(ebeanConfig)
}
