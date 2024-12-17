package com.example.klass.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{

    private Users user; 
    public UserPrincipal(Users user) {
        this.user = user;

    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// Implement this method
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Implement this method
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Implement this method
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Implement this method
		return true;
	}
}
