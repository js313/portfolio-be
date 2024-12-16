package com.jeenit.portfolio.service;

import com.jeenit.portfolio.model.Contact;
import com.jeenit.portfolio.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContactService {
    final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void saveMessage(Contact contact) {
        contactRepository.save(contact);
    }

    public List<Contact> getAllMessages() {
        return contactRepository.findAll();
    }

    public Contact getMessageById(int id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact getMessageByEmail(String email) {
        return contactRepository.findByEmail(email).orElse(null);
    }
}
