package great.dog.api.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "dog_condition")
public class DogCondition extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1164298676924406063L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="dog_id", insertable = false, updatable = false)
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
