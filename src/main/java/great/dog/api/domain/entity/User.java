package great.dog.api.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

	private String userName;
	
	private String password;
	
	private String nickName;
	
	private Timestamp loginTimestamp;
	
	@OneToMany(mappedBy="user")
	private List<Dog> dogs;
	
	@Builder
	public User(String userName, String password, String nickName) {
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
	}

}
