package com.jeenit.portfolio.dto;

import com.jeenit.portfolio.model.Contact;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDTO implements DTO<Contact> {
    @NotBlank
    @Size(min = 3, message = "name too short")
    @Size(max = 25, message = "name too long")
    private String name;

    @NotBlank
    @Email
    @Size(max = 255, message = "email too long")
    private String email;

    @NotBlank
    @Size(min = 10, message = "message too short")
    @Size(max = 511, message = "message too long")
    private String message;

    @Override
    public Contact toEntity() {
        Contact contact = new Contact();
        contact.setEmail(this.email);
        contact.setName(this.name);
        contact.setMessage(this.message);

        return contact;
    }
}
