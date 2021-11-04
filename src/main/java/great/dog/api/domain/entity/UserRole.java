package great.dog.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_type"})})
@DynamicUpdate // update 시 null 인 필드 제외
@DynamicInsert // insert 시 null 인 필드 제외
@NoArgsConstructor
public class UserRole extends BaseEntity implements GrantedAuthority {

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_ROLE_USER"))
    private User user;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public enum RoleType {
        ROLE_ADMIN, ROLE_VIEW
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return this.roleType.name();
    }
}
