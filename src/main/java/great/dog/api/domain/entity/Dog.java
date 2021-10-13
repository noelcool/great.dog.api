package great.dog.api.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "dog")
@DynamicUpdate @DynamicInsert //값이 변경된 컬럼만 update, insert
public class Dog extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -7159864540484776301L;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	private String dogName;
	
	private String breed;
	
	@OneToMany
	@JoinColumn(name = "dog_id")
	private List<DogCondition> dogConditions;
	
	@OneToMany
	@JoinColumn(name = "dog_id")
	private List<DogDisease> dogDiseases;

}
