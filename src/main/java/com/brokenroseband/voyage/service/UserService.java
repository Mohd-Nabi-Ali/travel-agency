package com.brokenroseband.voyage.service;


import com.brokenroseband.voyage.config.JWTUtil;
import com.brokenroseband.voyage.entity.Role;
import com.brokenroseband.voyage.entity.Tour;
import com.brokenroseband.voyage.entity.User;
import com.brokenroseband.voyage.model.LoginInput;
import com.brokenroseband.voyage.repository.RoleRepository;
import com.brokenroseband.voyage.repository.TourRepository;
import com.brokenroseband.voyage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;

    /**
     * Репозиторий работы с пользователями
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Репозиторий работы с ролями
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Репозиторий работы с турами
     */
    @Autowired
    TourRepository tourRepository;

    @Autowired
    JWTUtil jwtUtil;

    /**
     * Кодировщик
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException("Пользователь не найден");
        return user;
    }

    /**
     * Получение всех пользователей
     * @return список пользователей
     */
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    /**
     * Сохранение пользователя
     * @param user Новый пользователь
     * @return сообщение результата
     */
    public String saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Этот логин занят";
        }
        if(userRepository.findByEmail(user.getEmail())!=null){
            return "Этот email занят";
        }
        user.setRoles(List.of(new Role(1L,"ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "";
    }

    public User updateUser(User user){
        Optional<User> updatedUser = userRepository.findById(user.getId());
        if(updatedUser.isPresent()){
            if(!updatedUser.get().getPassword().equals(user.getPassword())) {
                updatedUser.get().setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            updatedUser.get().setFirstname(user.getFirstname());
            updatedUser.get().setLastname(user.getLastname());
            updatedUser.get().setMiddlename(user.getMiddlename());
            updatedUser.get().setPassportdata(user.getPassportdata());
            updatedUser.get().setPhone(user.getPhone());
            updatedUser.get().setEmail(user.getEmail());
            updatedUser.get().setAge(user.getAge());
            updatedUser.get().setUsername(user.getUsername());
            return userRepository.save(updatedUser.get());
        }
        return userRepository.save(user);
    }
    /**
     * Удаление пользователя по идентификатору
     * @param userId Идентификатор пользователя
     * @return Успешно ли удаление
     */
    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    /**
     * Бронирование тура пользователем
     * @param tour Бронируемый тур
     * @return Пользователь
     */
    public ResponseEntity<User> bookTour(Tour tour){
        User user = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        if (tourRepository.findById(tour.getId()).isPresent()){
            Tour tour1 = tourRepository.findById(tour.getId()).get();
            tour1.setCount(tour1.getCount()-1);
            tourRepository.save(tour1);
            user.getTours().add(tour1);
            userRepository.save(user);
            return new ResponseEntity(user,HttpStatus.OK);
        }
            return new ResponseEntity(user,HttpStatus.NOT_FOUND);
    }
    /**
     * Поиск пользователя по почте
     * @param email Почта пользователя
     * @return Пользователь
     */
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * Вход в аккаунт пользователя
     * @param loginInput Почта и пароль пользователя
     * @return Результат авторизации
     */
    public ResponseEntity<String> login (LoginInput loginInput){
        if(userRepository.findByEmail(loginInput.getEmail()) !=null){
            User user = userRepository.findByEmail(loginInput.getEmail());
            if (bCryptPasswordEncoder.matches(loginInput.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user);
                return new ResponseEntity(token, HttpStatus.OK);
            }
            return new ResponseEntity("Wrong Data", HttpStatus.UNAUTHORIZED);
        }
        else{
            return new ResponseEntity("", HttpStatus.NOT_FOUND);
        }
    }

}
