package com.thanhdao.course_online.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.thanhdao.course_online.entity.common.BaseSoftDeleteEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Transactional
@JsonSerialize
@Builder
@Table(name = "`user`")
public class User extends BaseSoftDeleteEntity implements UserDetails {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;

    @Column(nullable = false)
    private String password;
    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(length = Integer.MAX_VALUE, nullable = false, unique = true)
    private String email;

    @Column(length = Integer.MAX_VALUE)
    private String avatar;
    @Column
    private String publicKey;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "UserRole", joinColumns = {@JoinColumn(referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
    private Set<Role> roles;

    @Column(length = Integer.MAX_VALUE)
    private String description;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
