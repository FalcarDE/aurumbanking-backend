package de.fhe.cc.team4.aurumbanking.core

import de.fhe.cc.team4.aurumbanking.model.AuthUser
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes

@ApplicationScoped
class Application {
    companion object {
        private val AuthUserList = ArrayList<AuthUser>()
        fun addAuthUser(AuthUser: AuthUser) = AuthUserList.add(AuthUser)
        fun findAuthUser(AuthUsername: String) = AuthUserList.find { AuthUser -> AuthUser.username == AuthUsername }
    }

    fun loadAuthUsers(@Observes evt: StartupEvent?) {
        addAuthUser(AuthUser("Hoang", "admin", "admin"))
        addAuthUser(AuthUser("Milena", "admin", "admin"))
    }
}