package great.dog.api.controller.api;

import great.dog.api.domain.dto.DogDto;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.service.DogService;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
//@Tag(name = "dog", description ="dog")
@RequestMapping("${spring.api}/dog")
public class DogController {

    private final DogService dogService;

    @GetMapping("/{id}")
    @Operation(summary = "dog 테이블에서 각 개별 dog 정보를 받아온다")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        DefaultRes defaultRes = new DefaultRes(id);
        DogDto.Response dog = dogService.findById(id);
        if (dog != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(dog);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findByUserId(@PathVariable("id") Long id) {
        DefaultRes defaultRes = new DefaultRes(id);
        List<DogDto.Response> dog = dogService.findByUserId(id);
        if (dog != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_SUCCESS);
            defaultRes.setData(dog);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody DogDto.SaveRequest dto) {
        int result = dogService.save(dto);
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
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody DogDto.UpdateRequest dto) {
        int result = dogService.update(id, dto);
        DefaultRes defaultRes = new DefaultRes(dto);
        if (result > 0) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.UPDATE_SUCCESS);
        } else {
            defaultRes.setResMsg(StatusMsg.UPDATE_FAIL);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }


}

