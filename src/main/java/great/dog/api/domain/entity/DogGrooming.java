package great.dog.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "dog_grooming")
public class DogGrooming extends BaseEntity implements Serializable {

    private String type; //전체 미용, 부분 미용, 귀청소, 발바닥 털 정리, 발톱 깍기
    private Long amount; //총액, 셀프 미용은 금액 자동으로 0원 처리

}
