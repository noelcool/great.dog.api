package great.dog.api.domain.dto;

import great.dog.api.domain.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DogDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SaveRequest {
        private String name;
        private String type;
        private String birth;
        private Long userId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UpdateRequest {
        private String name;
        private String type;
        private String birth;
        private Long userId;
        private String delYn;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private String name;
        private String type;
        private String birth;
        private Long userId;
        private String delYn;
        private List<DogCondition> dogConditions;
        private List<DogDisease> dogDiseases;
        private List<DogFeeding> dogFeedings;
        private List<DogHospital> dogHospitals;
    }
}
