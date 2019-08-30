package id.jasoet.auth.module

import id.jasoet.auth.handler.UserHandler
import org.koin.dsl.module

val handlerModule = module {

    single {
        UserHandler(get())
    }

}