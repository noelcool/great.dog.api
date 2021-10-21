package great.dog.api.controller.api;

import great.dog.api.domain.request.DogConditionRequest;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.domain.response.DogConditionResponse;
import great.dog.api.service.DogConditionService;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/dogCondition")
public class DogConditionController {

    private final DogConditionService dogConditionService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        DefaultRes defaultRes = new DefaultRes(StatusCode.BAD_REQUEST, StatusMsg.READ_FAIL);
        List<DogConditionResponse> dogCondition = dogConditionService.findAll();
        if (dogCondition != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(dogCondition);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        DefaultRes<Object> defaultRes = new DefaultRes<Object>(StatusCode.BAD_REQUEST, StatusMsg.READ_FAIL);
        DogConditionResponse res = dogConditionService.findById(id);
        if (res != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
    }

    @GetMapping("/dog/{dogId}")
    public ResponseEntity<?> findByUserId(@PathVariable("dogId") Long dogId) {
        DefaultRes<Object> defaultRes = new DefaultRes<Object>(StatusCode.BAD_REQUEST, StatusMsg.READ_FAIL);
        List<DogConditionResponse> res = dogConditionService.findByDogId(dogId);
        if (res != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody DogConditionRequest dto) {
        int result = dogConditionService.save(dto);
        DefaultRes defaultRes = new DefaultRes(StatusCode.BAD_REQUEST, StatusMsg.CREATED_FAIL, dto);
        if (result != 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.CREATED_SUCCESS);
            return new ResponseEntity(defaultRes, HttpStatus.OK);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DogConditionRequest dto) {
        int result = dogConditionService.update(id, dto);
        DefaultRes defaultRes = new DefaultRes(StatusCode.BAD_REQUEST, StatusMsg.UPDATE_FAIL, dto);
        if (result != 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.UPDATE_SUCCESS);
            return new ResponseEntity(defaultRes, HttpStatus.OK);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }
}
