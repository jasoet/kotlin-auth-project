package id.jasoet.auth.migration

import id.jasoet.auth.extension.executeMigration
import id.jasoet.auth.module.dataSourceModule
import org.koin.Logger.slf4jLogger
import org.koin.core.context.startKoin
import javax.sql.DataSource

fun main() {
    val koin = startKoin {
        slf4jLogger()
        modules(dataSourceModule)
    }.koin

    val dataSource = koin.get<DataSource>()
    dataSource.executeMigration("classpath:/dbmigration/h2")
}
