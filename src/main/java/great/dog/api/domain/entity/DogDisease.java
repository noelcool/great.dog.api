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
@Table(name = "dog_disease")
public class DogDisease extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 385704323098922545L;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="dog_id")
	private Dog dog;
	private String name;
	private String region; //위치
	private String comment;

	@Builder
	private DogDisease(String name, String region, String comment, Dog dog) {
		this.name = name;
		this.region = region;
		this.comment = comment;
		this.dog = dog;
	}
}
