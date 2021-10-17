package great.dog.api.service;

import great.dog.api.domain.entity.Dog;
import great.dog.api.domain.entity.User;
import great.dog.api.domain.request.DogRequest;
import great.dog.api.domain.response.DogResponse;
import great.dog.api.repository.DogRepository;
import great.dog.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class DogService {

    private final DogRepository dogRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<DogResponse> findAll() {
         // List<PostDto.Get> collect =
         //        allPost.stream().map(p -> modelMapper.map(p, PostDto.Get.class)).collect(Collectors.toList());

        List<Dog> dog = dogRepository.findAll();
        return dog.stream().map(d -> modelMapper.map(d, DogResponse.class)).collect(Collectors.toList());
    }

    public DogResponse findById(Long id) {
        Optional<Dog> dog = dogRepository.findById(id);
        return dog.map(value -> modelMapper.map(value, DogResponse.class)).orElse(null);
    }

    public int save(DogRequest dto) {
        // 사용자 정보 없음
        if (dto.getUserId() == null) return -1;

        Optional<User> oUser = userRepository.findById(dto.getUserId());
        // 사용자 없음
        if (!oUser.isPresent()) return -2;

        // 사용자 + 애견 정보 있음
        if (dogRepository.findByUserIdAndNameAndDelYn(dto.getUserId(), dto.getName(), "N").isPresent()) return -3;

//        modelMapper.typeMap(Item.class, Bill.class).addMappings(mapper -> {
//        mapper.map(Item::getStock, Bill::setQty);
//        mapper.map(Item::getPrice, Bill::setSinglePrice);
//         });
//        Bill bill2 = modelMapper.map(itemA, Bill.class);
//
//        modelMapper.typeMap(Item.class, Bill.class).addMappings(mapper -> {
//        mapper.map(Item::getStock, Bill::setQty);
//        mapper.map(Item::getPrice, Bill::setSinglePrice);
//        mapper.using((Converter<Boolean, Double>) context -> context.getSource() ? 20.0 : 0.0)
//                .map(Item::isSale, Bill::setDiscount);
//        });
//        Bill bill2 = modelMapper.map(request, Bill.class);

        Dog.DogBuilder dogBuilder = Dog.builder().
                name(dto.getName()).
                type(dto.getType()).
                user(oUser.get());
        return dogRepository.save(dogBuilder.build()) != null ? 1 : 0;
    }

    @Transactional
    public int update(DogRequest dto) {
        Optional<Dog> dog = dogRepository.
                findByUserIdAndNameAndDelYn(dto.getUserId(), dto.getName(), "N");
        if (!dog.isPresent()) {
            return -1;
        }
        dog.ifPresent(d -> {
            d.setName(dto.getName());
            d.setType(dto.getType());
            dogRepository.save(d);
        });
        return 1;
    }

}
