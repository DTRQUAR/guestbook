package test.guestbook.task.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Qouer on 02.07.2016.
 */
public enum Role implements GrantedAuthority{
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
