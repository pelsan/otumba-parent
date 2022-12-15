/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otumba.reports.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author peter
 */
@Controller
public class ReportsController {
    @GetMapping(value = "/reports/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReports(@PathVariable("id") String id) {
        return new ResponseEntity<>("GET Reports API triggered", null, HttpStatus.OK);
    }
    @GetMapping(value = "/report/billing/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getReportBill(@PathVariable("id") String id) {
        return new ResponseEntity<>("GET Report Billing API triggered", null, HttpStatus.OK);
    }
    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Object> deleteReport(@PathVariable String id) {
        return new ResponseEntity<>("DELETE Reports API triggered", null, HttpStatus.OK);
    }
}