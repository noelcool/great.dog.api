package great.dog.api.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "hospital")
public class Hospital extends BaseEntity {

	private static final long serialVersionUID = 1575143816595676489L;
	
	
	

}
