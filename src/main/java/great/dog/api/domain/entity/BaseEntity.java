package great.dog.api.domain.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@EntityListeners(value = AuditingEntityListener.class)
@Getter @Setter
@MappedSuperclass //객체의 입장에서 공통 매핑 정보가 필요할 때 사용
@Where(clause = "delYn = 'N'")
public abstract class BaseEntity implements Serializable { //객체 직렬화
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", updatable = false, nullable = false, insertable=false)
	private Long id;
	
	@ColumnDefault(value = "'N'") // table create 시 기본값
	private String delYn = "N";
	
	@CreatedDate
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp createTimestamp;
	
	@LastModifiedDate
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Timestamp updateTimestamp;

}
