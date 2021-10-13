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
public class DogDisease extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 385704323098922545L;

	@ManyToOne
	@JoinColumn(name="dog_id")
	private Dog dog;

	@Column(name = "disease_name")
	private String diseaseName;
	
	@Column(name = "disease_region")
	private String diseaseRegion;
	
	@Column(name = "disease_comment")
	private String diseaseComment;
	
}
