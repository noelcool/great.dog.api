package great.dog.api.controller.api;


import great.dog.api.domain.dto.DogFeedingDto;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.service.DogFeedingService;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/dogFeeding")
public class DogFeedingController {

    private final DogFeedingService dogFeedingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        DefaultRes<Object> defaultRes = new DefaultRes<>();
        DogFeedingDto.Response res = dogFeedingService.findById(id);
        if(!Objects.isNull(res)) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
    }

    @GetMapping("/dogId/{dogId}")
    public ResponseEntity<?> findByDogIdAndDelYn(@PathVariable("dogId") Long dogId) {
        DefaultRes<Object> defaultRes = new DefaultRes<>();
        List<DogFeedingDto.Response> res = dogFeedingService.findByDogId(dogId);
        if(!Objects.isNull(res)) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody DogFeedingDto.SaveRequest request) {
        DefaultRes<Object> defaultRes = new DefaultRes<>();
        int result = dogFeedingService.save(request);
        if (result > 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.CREATED_SUCCESS);
        } else {
            defaultRes.setResMsg(StatusMsg.CREATED_FAIL);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DogFeedingDto.UpdateRequest request) {
        int result = dogFeedingService.update(id, request);
        DefaultRes defaultRes = new DefaultRes(StatusCode.BAD_REQUEST, StatusMsg.UPDATE_FAIL, request);
        if (result > 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.UPDATE_SUCCESS);
        } else {
            defaultRes.setResMsg(StatusMsg.UPDATE_FAIL);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

}
