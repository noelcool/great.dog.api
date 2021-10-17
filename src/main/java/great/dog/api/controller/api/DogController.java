package great.dog.api.controller.api;

import great.dog.api.domain.request.DogRequest;
import great.dog.api.domain.response.DefaultRes;
import great.dog.api.domain.response.DogResponse;
import great.dog.api.service.DogService;
import great.dog.api.util.StatusCode;
import great.dog.api.util.StatusMsg;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.aspectj.lang.annotation.DeclareWarning;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/dog")
public class DogController {

    private final DogService dogService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        DefaultRes defaultRes = new DefaultRes(StatusCode.NOT_FOUND, StatusMsg.NOT_FOUND_USER, id);
        DogResponse dog = dogService.findById(id);
        if (dog != null) {
            defaultRes.setResCode(StatusCode.OK);
            defaultRes.setResMsg(StatusMsg.READ_DOG);
            defaultRes.setData(dog);
        }
        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody DogRequest dto) {
        int result = dogService.save(dto);
        DefaultRes defaultRes = new DefaultRes(StatusCode.OK, StatusMsg.CREATED_DOG, dto);

        if (result == -1) {
            defaultRes.setResCode(StatusCode.NOT_FOUND);
            defaultRes.setResMsg(StatusMsg.OMIT_USER);
        } else if (result == -2) {
            defaultRes.setResCode(StatusCode.NOT_FOUND);
            defaultRes.setResMsg(StatusMsg.NOT_FOUND_USER);
        } else if (result == -3) {
            defaultRes.setResCode(StatusCode.SERVICE_UNAVAILABLE);
            defaultRes.setResMsg(StatusMsg.DUPLICATED_DOG);
        } else {
            defaultRes.setResCode(StatusCode.SERVICE_UNAVAILABLE);
            defaultRes.setResMsg(StatusMsg.INTERNAL_SERVER_ERROR);
            return new ResponseEntity(defaultRes, HttpStatus.OK);
        }

        return new ResponseEntity(defaultRes, HttpStatus.OK);
    }


}

