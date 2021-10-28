package great.dog.api.domain.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter @Setter
@Entity
@Table(name = "dog_feeding")
public class DogFeeding extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = -4222750668833189505L;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="dog_id", insertable = false, updatable = false)
	private Dog dog;
	
	private String type;

	private String quantity;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp feedingTimestamp;

	@Builder
	private DogFeeding(String type, String quantity, Timestamp feedingTimestamp, Dog dog) {
		this.type = type;
		this.quantity = quantity;
		this.feedingTimestamp = feedingTimestamp;
		this.dog = dog;
	}

}
