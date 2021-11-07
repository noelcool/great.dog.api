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
    //boolean isAccountNonExpired();
    //boolean isAccountNonLocked();
    //boolean isCredentialsNonExpired();
    //boolean isEnabled();
    private final boolean accountNonExpired; // 계정 만료 여부
    private final boolean accountNonLocked; // 계정 잠금 여부
    private final boolean credentialsNonExpired; // 패스워드 만료 여부
    private final boolean enabled; //계정 사용 가능 여부

    public SecurityUser(User user) {
        super();
        setId(user.getId());
        setEmail(user.getEmail());
        setName(user.getName());
        setPassword(user.getPassword());
        setDelYn(user.getDelYn());
        setUserRoles(user.getUserRoles());
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public Set<UserRole.RoleType> getRoleTypes() {
        return getUserRoles().stream().map(t -> t.getRoleName()).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserRoles();
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
