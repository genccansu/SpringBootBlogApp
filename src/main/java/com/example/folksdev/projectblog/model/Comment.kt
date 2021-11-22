package com.example.folksdev.projectblog.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table
data class Comment @JvmOverloads constructor(
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val body: String,
    val creationDate: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    val posts: Post,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    val user: User,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Comment

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

}
