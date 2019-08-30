package id.jasoet.auth.model

import io.ebean.annotation.WhenCreated
import io.ebean.annotation.WhenModified
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "t_user")
data class User(
        @Id
        var id: Long,
        var name: String,
        var email: String,
        @Column
        var bio: String?,
        var active: Boolean,
        @ManyToMany
        var groups: List<Group>,
        @WhenCreated
        @Column(name = "created")
        var whenCreated: Instant,
        @WhenModified
        @Column(name = "modified")
        var whenModified: Instant
)

@Entity
@Table(name = "t_group")
data class Group(
        @Id
        var id: Long,
        var name: String,
        @Column(length = 600)
        var description: String,
        var policy: String,
        @WhenCreated
        @Column(name = "created")
        var whenCreated: Instant,
        @WhenModified
        @Column(name = "modified")
        var whenModified: Instant
)

