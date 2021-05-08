package com.forty_percent.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

public enum UserRole {
	ADMIN(Sets.newHashSet(UserPermission.API_USER_READ, UserPermission.API_USER_WRITE)),
	USER(Sets.newHashSet()),
	API_USER(Sets.newHashSet());

	private final Set<UserPermission> permissions;

	UserRole(Set<UserPermission> permissions){
		this.permissions = permissions;
	}

	public Set<UserPermission> getPermissions(){
		return permissions;
	}

	public Set<GrantedAuthority> getGrantedAuthorities() {
		Set<GrantedAuthority> permissions = getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
				.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		return permissions;
	}
}
