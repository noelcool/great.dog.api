package great.dog.api.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@Entity
@Table(name = "feeding")
public class Feeding extends BaseEntity {
	
	private static final long serialVersionUID = -4222750668833189505L;

	@ManyToOne
	@JoinColumn(name="dog_id", insertable = false, updatable = false)
	private Dog dog;
	
	private String type;

	private Timestamp feedingTimestamp;
	

}
