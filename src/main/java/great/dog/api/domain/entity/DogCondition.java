package great.dog.api.domain.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "dog_condition")
@NoArgsConstructor
public class DogCondition extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1164298676924406063L;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="dog_id")
	private Dog dog;
	private Float weight;
	private Float height;

	@Builder
	private DogCondition(Float weight, Float height, Dog dog) {
		this.weight = weight;
		this.height = height;
		this.dog = dog;
	}

}
