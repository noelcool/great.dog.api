package great.dog.api.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter @Setter
@Entity
@Table(name = "user")
@DynamicUpdate // update 시 null 인 필드 제외
@DynamicInsert // insert 시 null 인 필드 제외
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8161352668492942511L;

	private String email;

	private String name;
	
	private String password;
	
	private Timestamp loginTimestamp;
	
	@OneToMany(mappedBy="user")
	private List<Dog> dogs;

	@Singular("userRoles")
	@JsonIgnoreProperties({"createTimestamp", "updateTimestmap"}) //json 조회시 출력에서 제외시킬 컬럼
	@JsonManagedReference // 무한 순회참조 현상을 막기 위해서 조회하고자 하는 엔티티에 선언해준다
	@OneToMany(mappedBy="user")
	private Set<UserRole> userRoles;
	
	@Builder
	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

}
