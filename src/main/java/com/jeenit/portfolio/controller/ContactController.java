package com.jeenit.portfolio.controller;

import com.jeenit.portfolio.dto.ContactDTO;
import com.jeenit.portfolio.model.Contact;
import com.jeenit.portfolio.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("")
    public ResponseEntity<Void> saveMessage(@RequestBody @Valid ContactDTO contactDTO) {
        contactService.saveMessage(contactDTO.toEntity());    // If validation exception occurs throw appropriate error
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<Contact>> getAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

    @GetMapping("{idOrEmail}")
    public ResponseEntity<Contact> getMessage(@PathVariable(name = "idOrEmail", required = false) String idOrEmail) {
        Contact contact;
        if (idOrEmail.matches("\\d+")) {
            contact = contactService.getMessageById(Integer.parseInt(idOrEmail));
        } else {
            contact = contactService.getMessageByEmail(idOrEmail);
        }

        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contact);
    }
}
