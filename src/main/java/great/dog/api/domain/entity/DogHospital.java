package great.dog.api.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter @Setter
@Entity
@Table(name = "dog_hospital")
public class DogHospital extends BaseEntity {

	private static final long serialVersionUID = 1575143816595676489L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="dog_id", insertable = false, updatable = false)
	private Dog dog;

	private String name;
	private String address;
	private String phoneNumber;
	private String reservationDate;
	private String comment;
	@ColumnDefault(value = "'N'")
	private String visitYn;

}
