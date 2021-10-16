package great.dog.api.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "hospital")
public class Hospital extends BaseEntity {

	private static final long serialVersionUID = 1575143816595676489L;

	@ManyToOne
	@JoinColumn(name="dog_id", insertable = false, updatable = false)
	private Dog dog;

	private String name;
	private String address;
	private String phoneNumber;
	private String reservationDate;
	private String comment;

}
