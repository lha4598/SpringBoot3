package com.devteria.identity_service.service;

import com.devteria.identity_service.dto.request.UserCreationRequest;
import com.devteria.identity_service.dto.request.UserUpdateRequest;
import com.devteria.identity_service.dto.response.UserResponse;
import com.devteria.identity_service.entity.User;
import com.devteria.identity_service.enums.Role;
import com.devteria.identity_service.exception.AppException;
import com.devteria.identity_service.exception.ErrorCode;
import com.devteria.identity_service.mapper.UserMapper;
import com.devteria.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor //TẠO 1 constructor cho tất cả các biến mà define là final
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // bất cứ field nào khi ko khai báo gì sẽ tự dộng là private final
                                                                //loại bỏ @Autowired
public class UserService {
    UserRepository userRepository; //private final
    UserMapper userMapper; //private final
    public User createUser(UserCreationRequest request){

        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED); // Ktra User có tồn tại ko

        User user = userMapper.toUser(request);//Map request vào user
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);//tùy vào các TH mà tùy chỉnh thông số cho phù hợp
                                                                                //default là 10
        user.setPassword(passwordEncoder.encode(request.getPassword())); //mã hóa mật khẩu

        HashSet<String> roles = new HashSet<>(); //tạo role cho user
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userRepository.save(user); //persist(lưu dữ liệu lâu dài) vào database
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new  RuntimeException("User not found")));
    }

    public UserResponse updateUser(String userId,UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new  RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
