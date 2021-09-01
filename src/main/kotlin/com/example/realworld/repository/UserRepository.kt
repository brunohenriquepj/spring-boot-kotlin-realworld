package com.example.realworld.repository

import com.example.realworld.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailOrUserName(email: String, userName: String): User?
    fun findByEmail(email: String): User?
    fun getByEmail(email: String): User

    @Transactional
    @Modifying
    @Query("""
        update User u
        set u.email = :#{#user.email}, u.userName = :#{#user.userName},
        u.password = :#{#user.password}, u.bio = :#{#user.bio},
        u.image = :#{#user.image}
        where u.id = :id
    """)
    fun update(@Param("id") id: Long, @Param("user") user: User)
}
