package com.jeenit.portfolio.repository;

import com.jeenit.portfolio.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findByEmail(String email);
}
