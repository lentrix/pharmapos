/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.pharmapos.models;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lentrix
 */
public class Test {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println(dt.format(DateTimeFormatter.ofPattern("MMM-d-u")));
    }
}
