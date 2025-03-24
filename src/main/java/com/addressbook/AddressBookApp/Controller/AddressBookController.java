package com.addressbook.AddressBookApp.Controller;


import com.addressbook.AddressBookApp.DTO.AddressBookDTO;
import com.addressbook.AddressBookApp.Entity.AddressBook;
import com.addressbook.AddressBookApp.Interfaces.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    @Autowired

    private IAddressBookService addressBookService;


    @GetMapping
    public ResponseEntity<List<AddressBook>> getAllContacts() {
        return ResponseEntity.ok(addressBookService.getAllContacts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressBook> getContactById(@PathVariable int id) {
        return addressBookService.getContactById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AddressBook> addContact(@RequestBody AddressBookDTO addressBookDTO) {
        return ResponseEntity.ok(addressBookService.addContact(addressBookDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressBook> updateContact(@PathVariable int id, @RequestBody AddressBookDTO addressBookDTO) {
        return addressBookService.updateContact(id, addressBookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        if (addressBookService.deleteContact(id)) {
            return ResponseEntity.ok("Contact deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}