package kms.bootcamp.footballturfmanagementservice.dto;

import java.io.Serializable;

public class JwtResponse extends HttpBase implements Serializable {
    private static final long serialVersionUID = -170667041747974286L;

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
