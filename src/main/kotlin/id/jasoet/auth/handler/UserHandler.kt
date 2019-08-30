package id.jasoet.auth.handler

import id.jasoet.auth.extension.Handler
import id.jasoet.auth.extension.validate
import id.jasoet.auth.model.User
import id.jasoet.auth.model.query.QUser
import io.ebean.Database
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond

class UserHandler(private val database: Database) {

    val query = {
        QUser(database)
    }

    val get: Handler = {
        call.respond(query().findList())
    }

    val getById: Handler = {
        val id = call.parameters["id"]
        val country = id?.let { query().setId(it).findOne() }
        if (country != null) {
            call.respond(country)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    val post: Handler = {
        val user = call.receive<User>()
        user.validate()

        database.save(user)
        call.respond(HttpStatusCode.Created)
    }

    val putById: Handler = {
        val id = call.parameters["id"]

        val user = id?.let {
            query().setId(it).findOne()
        }

        if (user != null) {
            val update = call.receive<User>()

            user.apply {
                name = update.name
                email = update.email
                bio = update.bio
                active = update.active
                groups = update.groups
            }

            user.validate()

            database.save(user)

            call.respond(HttpStatusCode.OK)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    val deleteById: Handler = {
        val id = call.parameters["id"]
        val user = id?.let {
            query().setId(it).findOne()
        }
        if (user != null) {
            database.delete(user)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}