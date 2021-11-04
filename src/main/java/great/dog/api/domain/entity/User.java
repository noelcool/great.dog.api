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
import org.hibernate.annotations.Where;

@Getter @Setter
@Entity
@Table(name = "user")
@DynamicUpdate // update 시 null 인 필드 제외
@DynamicInsert // insert 시 null 인 필드 제외
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8161352668492942511L;

	private String name;
	
	private String password;
	
	private String nickName;
	
	private Timestamp loginTimestamp;
	
	@OneToMany(mappedBy="user")
	private List<Dog> dogs;

	@Singular("userRoles")
	@JsonIgnoreProperties({"createTimestamp", "updateTimestmap"})
	@JsonManagedReference
	@OneToMany(mappedBy="user")
	private Set<UserRole> userRoles;
	
	@Builder
	public User(String name, String password, String nickName) {
		this.name = name;
		this.password = password;
		this.nickName = nickName;
	}

}
