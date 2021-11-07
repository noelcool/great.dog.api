package great.dog.api.controller.api;

import great.dog.api.domain.dto.DogConditionDto;
import great.dog.api.domain.response.DefaultRes;
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
@RequestMapping("${spring.api}/dogCondition")
public class DogConditionController {

    private final DogConditionService dogConditionService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        DefaultRes defaultRes = new DefaultRes();
        List<DogConditionDto.Response> dogCondition = dogConditionService.findAll();
        if (dogCondition != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(dogCondition);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        DefaultRes<Object> defaultRes = new DefaultRes<>();
        DogConditionDto.Response res = dogConditionService.findById(id);
        if (res != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
    }

    @GetMapping("/dogId/{dogId}")
    public ResponseEntity<?> findByDogId(@PathVariable("dogId") Long dogId) {
        DefaultRes<Object> defaultRes = new DefaultRes<>();

        List<DogConditionDto.Response> res = dogConditionService.findByDogId(dogId);
        if (res != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody DogConditionDto.Request dto) {
        int result = dogConditionService.save(dto);
        DefaultRes defaultRes = new DefaultRes(dto);
        if (result > 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.CREATED_SUCCESS);
        } else {
            defaultRes.setResMsg(StatusMsg.CREATED_FAIL);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DogConditionDto.Request dto) {
        int result = dogConditionService.update(id, dto);
        DefaultRes defaultRes = new DefaultRes(StatusCode.BAD_REQUEST, StatusMsg.UPDATE_FAIL, dto);
        if (result > 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.UPDATE_SUCCESS);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }



}
