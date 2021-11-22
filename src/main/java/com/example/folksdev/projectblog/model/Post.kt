package com.example.folksdev.projectblog.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table
data class Post @JvmOverloads constructor(
    @Id
    @Column(name = "post_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val title: String,
    val body: String,
    val creationDate: LocalDate,


    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val comments: List<Comment>,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    val user: User,


    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Post

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

}

