package great.dog.api.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class DogFeedingDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String type;
        private String quantity;
        private Timestamp feedingTimestamp;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SaveRequest {
        private String type;
        private String quantity;
        private Timestamp feedingTimestamp;
        private Long dogId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class UpdateRequest {
        private String type;
        private String quantity;
        private Timestamp feedingTimestamp;
    }

}
