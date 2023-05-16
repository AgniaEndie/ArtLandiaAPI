package ru.endienasg.artlandia.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.endienasg.artlandia.models.entities.Role;
import ru.endienasg.artlandia.models.entities.User;
import ru.endienasg.artlandia.models.repositories.IUserRepository;
import ru.endienasg.artlandia.models.requests.UserAuthenticationRequest;
import ru.endienasg.artlandia.models.requests.UserDeleteRequest;
import ru.endienasg.artlandia.models.requests.UserRegistrationRequest;
import ru.endienasg.artlandia.models.requests.UserUpdateRequest;
import ru.endienasg.artlandia.models.responses.UserAuthenticationResponse;
import ru.endienasg.artlandia.models.responses.UserDeleteResponse;
import ru.endienasg.artlandia.models.responses.UserRegistrationResponse;
import ru.endienasg.artlandia.models.responses.UserUpdateResponse;
import ru.endienasg.artlandia.security.jwt.JwtTokenService;

import java.sql.SQLDataException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service("userDetailsService")
@Slf4j
public class UserService implements UserDetailsService {
    private IUserRepository repository;
    JwtTokenService service = new JwtTokenService();
    @Async
    public CompletableFuture<UserRegistrationResponse> registryUser(UserRegistrationRequest request,IUserRepository repository) {
        return CompletableFuture.supplyAsync(()->{
            UserRegistrationResponse response = new UserRegistrationResponse();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User userToSave = new User(UUID.randomUUID().toString(),request.getUsername(),encoder.encode(request.getPassword()), Role.USER);
            User fromSave = repository.save(userToSave);
            log.info("Запрос на registryUser");
            if(fromSave != null){
                response.setUsername(fromSave.getUsername());
                response.setRole(fromSave.getRole());
                return response;
            }else{
                response.setMessage("Пользователь с таким именем уже существует");
                response.setExitCode(2);
                return response;
            }
        });
    }
    @Async
    public CompletableFuture<UserAuthenticationResponse> authenticateUser(UserAuthenticationRequest request,IUserRepository repository){
        return CompletableFuture.supplyAsync(()->{
           UserAuthenticationResponse response = new UserAuthenticationResponse();
           User user = repository.findByUsername(request.getUsername());
           response.setUsername(user.getUsername());
           response.setToken(service.generateToken(user));
           return response;
        });
    }
    @Async
    public CompletableFuture<UserUpdateResponse> updateUser(UserUpdateRequest request,IUserRepository repository){
        return CompletableFuture.supplyAsync(()->{
           UserUpdateResponse response = new UserUpdateResponse();
           return response;
        });
    }

    @Async
    public CompletableFuture<UserDeleteResponse> deleteUser(UserDeleteRequest request,IUserRepository repository){
        return CompletableFuture.supplyAsync(()->{
           UserDeleteResponse response = new UserDeleteResponse();
            return response;
        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        return null;
    }


}
