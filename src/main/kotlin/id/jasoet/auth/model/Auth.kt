package id.jasoet.auth.model

import io.ebean.annotation.WhenCreated
import io.ebean.annotation.WhenModified
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
        @Id
        var id: Long,
        var name: String,
        var email: String,
        @Column
        var bio: String?,
        var active: Boolean,
        @WhenCreated
        @Column(name = "created")
        var whenCreated: Instant,
        @WhenModified
        @Column(name = "modified")
        var whenModified: Instant
)

data class UserGroup(
        var id: Long,
        var user: User,
        var group: Group
)

data class Group(
        var id: Long,
        var name: String,
        var description: String,
        var policy: String
)
