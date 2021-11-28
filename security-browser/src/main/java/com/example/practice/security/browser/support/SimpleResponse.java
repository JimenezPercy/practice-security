package com.example.practice.security.browser.support;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleResponse  <T> {

    private T content;
}
