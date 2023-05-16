package ru.endienasg.artlandia.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import ru.endienasg.artlandia.models.repositories.IUserRepository;
import ru.endienasg.artlandia.models.requests.*;
import ru.endienasg.artlandia.models.responses.UserAuthenticationResponse;
import ru.endienasg.artlandia.models.responses.UserDeleteResponse;
import ru.endienasg.artlandia.models.responses.UserRegistrationResponse;
import ru.endienasg.artlandia.models.responses.UserUpdateResponse;
import ru.endienasg.artlandia.services.UserService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    private IUserRepository repository;

    public UserController(IUserRepository repository){
        this.repository = repository;
    }

    private UserService userService = new UserService();

    @Async
    @PostMapping("register")
    public CompletableFuture<UserRegistrationResponse> registryUser(@RequestBody UserRegistrationRequest request){
        return CompletableFuture.supplyAsync(()->{
            try {
                return userService.registryUser(request,repository).get();
            } catch (InterruptedException | ExecutionException e) {
                //throw new RuntimeException(e);
                log.info("Произошла ошибка в методе RegistryUser");
            }
            return null;
        });
    }

    @Async
    @PostMapping("login")
    public CompletableFuture<UserAuthenticationResponse> authUser(@RequestBody UserAuthenticationRequest request){
        return CompletableFuture.supplyAsync(()->{
            try {
                return userService.authenticateUser(request,repository).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Async
    @PutMapping("update")
    public CompletableFuture<UserUpdateResponse> updateUser(@RequestBody UserUpdateRequest request){
        return CompletableFuture.supplyAsync(()->{
            try {
                return userService.updateUser(request,repository).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Async
    @DeleteMapping("delete")
    public CompletableFuture<UserDeleteResponse> deleteUser(@RequestBody UserDeleteRequest request){
        return CompletableFuture.supplyAsync(()->{
            try {
                return userService.deleteUser(request,repository).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @GetMapping("test")
    public String test(){
        return "Work";
    }
}
