package great.dog.api.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "dog_condition")
public class DogCondition extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1164298676924406063L;
	
	@ManyToOne
	@JoinColumn(name="dog_id")
	private Dog dog;
	
	@Column(name="weight")
	private Float weight;
	
	@Column(name="height")
	private Float height;
	

}
