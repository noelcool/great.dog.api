package great.dog.api.config;

import great.dog.api.domain.entity.User;
import great.dog.api.domain.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityUser extends User implements UserDetails {

    // 인증 및 인가에 이용하는 필드 값
    private final boolean accountNonExpired;
    private final boolean accountNonlocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public SecurityUser(User user) {
        super();
        setId(user.getId());
        setEmail(user.getEmail());
        setName(user.getName());
        setPassword(user.getPassword());
        setDelYn(user.getDelYn());
        setUserRoles(user.getUserRoles());
        this.accountNonExpired = true;
        this.accountNonlocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public Set<UserRole.RoleType> getRoleTypes() {
        return getUserRoles().stream().map(t -> t.getRoleName()).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
