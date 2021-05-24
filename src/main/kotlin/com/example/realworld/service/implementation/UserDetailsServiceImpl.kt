package com.example.realworld.service.implementation

import com.example.realworld.entity.UserDetailsImpl
import com.example.realworld.repository.UserRepository
import com.example.realworld.service.UserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username!!)

        if (user == null) throw UsernameNotFoundException("Username or password invalid!")
        else return UserDetailsImpl(user)
    }
}