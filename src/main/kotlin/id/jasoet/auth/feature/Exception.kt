package id.jasoet.auth.feature

import id.jasoet.auth.extension.ValidationException
import io.ebean.DuplicateKeyException
import io.ktor.application.call
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun StatusPages.Configuration.registerRedirection() {
    exception<DuplicateKeyException> {
        call.respond(HttpStatusCode.Conflict)
    }

    exception<ValidationException> { cause ->
        call.respond(HttpStatusCode.BadRequest, cause.getMessages())
    }
}