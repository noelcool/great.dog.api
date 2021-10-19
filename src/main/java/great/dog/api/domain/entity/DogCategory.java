package great.dog.api.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "dog_category")
public class DogCategory extends BaseEntity implements Serializable {

    private String type; // 식사, 미용, 질병 등
    private String value; // 주식, 화식 등등
}
