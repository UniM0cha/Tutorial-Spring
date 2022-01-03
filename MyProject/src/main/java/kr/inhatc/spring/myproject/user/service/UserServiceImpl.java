package kr.inhatc.spring.myproject.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.inhatc.spring.myproject.user.entity.Users;
import kr.inhatc.spring.myproject.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public List<Users> userList() {
    List<Users> list = userRepository.findAllByOrderByIdDesc();
    return list;
  }

  @Override
  public void saveUsers(Users user) {
    userRepository.save(user);
  }

  @Override
  public Users userDetail(String id) {
    Optional<Users> optional = userRepository.findById(id);
    // Optional 타입은 데이터가 없을 수 도 있으므로 있는지 없는지 살펴보는 클래스 타입이다....
    if (optional.isPresent()) {
      Users user = optional.get();
      return user;
    } else {
      throw new NullPointerException();
    }
  }

  @Override
  public void deleteUsers(String id) {
    userRepository.deleteById(id);
  }

}
