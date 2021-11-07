package great.dog.api.controller.api;

import great.dog.api.domain.dto.DogDiseaseDto;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.service.DogDiseaseService;
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
@RequestMapping("${spring.api}/dogDisease")
public class DogDiseaseController {

    private final DogDiseaseService dogDiseaseService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        DefaultRes<Object> defaultRes = new DefaultRes<>();
        DogDiseaseDto.Response res = dogDiseaseService.findById(id);
        if(!Objects.isNull(res)){
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity<Object>(defaultRes, HttpStatus.OK);
    }

    @GetMapping("/dogId/{dogId}")
    public ResponseEntity<?> findByDogId(@PathVariable("dogId") Long dogId) {
        DefaultRes<Object> defaultRes = new DefaultRes<>();
        List<DogDiseaseDto.Response> res = dogDiseaseService.findByDogId(dogId);
        if(!Objects.isNull(res)){
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(res);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody DogDiseaseDto.SaveRequest dto) {
        int result = dogDiseaseService.save(dto);
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
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DogDiseaseDto.UpdateRequest dto) {
        int result = dogDiseaseService.update(id, dto);
        DefaultRes defaultRes = new DefaultRes(StatusCode.BAD_REQUEST, StatusMsg.UPDATE_FAIL, dto);
        if (result > 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.UPDATE_SUCCESS);
        } else {
            defaultRes.setResMsg(StatusMsg.UPDATE_FAIL);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

}
