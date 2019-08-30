package id.jasoet.auth

import com.typesafe.config.Config
import id.jasoet.auth.module.dataSourceModule
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.Logger.slf4jLogger
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.get
import javax.sql.DataSource

fun Application.mainModule() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(Koin) {
        slf4jLogger()

        modules(dataSourceModule)
    }

    routing {
        val database = get<DataSource>()
        val config = get<Config>()
        get("/") {
            val response = mapOf(
                    "greeting" to "Ktor with Database and Koin",
                    "database_url" to config.getString("dataSource.url"),
                    "connection_ready" to !database.connection.isClosed
            )
            call.respond(response)
        }
    }

}