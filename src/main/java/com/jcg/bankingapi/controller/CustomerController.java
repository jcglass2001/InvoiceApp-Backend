package com.jcg.bankingapi.controller;

import com.jcg.bankingapi.domain.Customer;
import com.jcg.bankingapi.domain.User;
import com.jcg.bankingapi.domain.dto.UserDTO;
import com.jcg.bankingapi.domain.dto.response.HttpResponse;
import com.jcg.bankingapi.service.CustomerService;
import com.jcg.bankingapi.service.InvoiceService;
import com.jcg.bankingapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static java.util.Map.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final UserService userService;
    @GetMapping("/list")
    public ResponseEntity<HttpResponse> getCustomers(@AuthenticationPrincipal UserDTO user,
                                                     @RequestParam Optional<Integer> page,
                                                     @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of(
                                "user", userService.getUserByEmail(user.getEmail()),
                                "page", customerService.getCustomers(
                                        page.orElse(0),
                                        size.orElse(10)),
                                "stats", customerService.getStats()
                        ))
                        .message("Customers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createCustomer(@AuthenticationPrincipal UserDTO user, @RequestBody Customer customer){
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of(
                                "user", userService.getUserByEmail(user.getEmail()),
                                "customer", customerService.createCustomer(customer)
                        ))
                        .message("Customer created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<HttpResponse> getCustomer(@AuthenticationPrincipal UserDTO user, @PathVariable("id") Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of(
                                "user", userService.getUserByEmail(user.getEmail()),
                                "customers", customerService.getCustomer(id)
                        ))
                        .message("Customer retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @GetMapping("/search")
    public ResponseEntity<HttpResponse> searchCustomer(@AuthenticationPrincipal UserDTO user,
                                                       @RequestParam Optional<String> name,
                                                       @RequestParam Optional<Integer> page,
                                                       @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of(
                                "user", userService.getUserByEmail(user.getEmail()),
                                "customers", customerService.searchCustomers(
                                        name.orElse(""),
                                        page.orElse(0),
                                        size.orElse(10))
                        ))
                        .message("Customers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateCustomer(@AuthenticationPrincipal UserDTO user, @RequestBody Customer customer){
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of(
                                "user", userService.getUserByEmail(user.getEmail()),
                                "customers", customerService.updateCustomer(customer)
                        ))
                        .message("Customer updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
