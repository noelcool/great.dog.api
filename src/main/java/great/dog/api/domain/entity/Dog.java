package great.dog.api.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "dog")
@DynamicUpdate
@DynamicInsert //값이 변경된 컬럼만 update, insert
@NoArgsConstructor
public class Dog extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -7159864540484776301L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String name;
	
	private String type;
	
	@OneToMany
	@JoinColumn(name = "dog_id")
	private List<DogCondition> dogConditions;
	
	@OneToMany
	@JoinColumn(name = "dog_id")
	private List<DogDisease> dogDiseases;
	
	@OneToMany
	@JoinColumn(name = "dog_id")
	private List<DogFeeding> feeding;

	@OneToMany
	@JoinColumn(name = "dog_id")
	private List<DogHospital> hospital;

	@Builder
	public Dog(String name, String type, User user) {
		this.name = name;
		this.type = type;
		this.user = user;
	}

}
