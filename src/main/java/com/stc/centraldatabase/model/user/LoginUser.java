package com.stc.centraldatabase.model.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * LoginUser.java
 *
 * @author Ei Ei Swe Minn
 */
public class LoginUser implements UserDetails {

	/** serialVersionUID */
	private static final long serialVersionUID = 325548812722259208L;

	private Integer id;
	private String username;
	private String password;
	private Set<String> roles = new HashSet<String>();

	public LoginUser() {
	}

	public LoginUser(String username, String passwordHash) {
		this.username = username;
		this.password = passwordHash;
	}

	/**
	 * Get the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username.
	 *
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Get the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get the roles.
	 *
	 * @return the roles
	 */
	public Set<String> getRoles() {
		return roles;
	}

	/**
	 * Set the roles.
	 *
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<String> roles = this.getRoles();

		if (roles == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
