package com.example.practice.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

@Data
public class QQProperties extends SocialProperties {
    private String providerId="qq";
}
