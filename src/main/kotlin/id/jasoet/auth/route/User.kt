package id.jasoet.auth.route

import id.jasoet.auth.handler.UserHandler
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.route
import org.koin.ktor.ext.get

fun Route.user() {

    route("/user") {

        val handler: UserHandler = get()

        get("/", handler.get)

        get("/{id}", handler.getById)

        post("/", handler.post)

        put("/{id}", handler.putById)

        delete("/{id}", handler.deleteById)

    }
}
