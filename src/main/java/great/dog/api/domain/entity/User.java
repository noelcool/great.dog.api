package great.dog.api.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "user")
@DynamicUpdate @DynamicInsert
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -8161352668492942511L;

	private String userName;
	
	private String password;
	
	private String nickName;
	
	private Timestamp loginTimestamp;
	
	@OneToMany(mappedBy="user")
	private List<Dog> dogs;

}
