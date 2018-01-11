package com.solt.jdc.boot.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Page")
public class PageNotFoundException extends RuntimeException {
}
