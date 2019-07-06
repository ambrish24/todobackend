package com.amby.rest.controller;

import com.amby.rest.model.request.UserDetailsRequestModel;
import com.amby.rest.model.response.*;
import com.amby.rest.service.UserService;
import com.amby.rest.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/users") // http://localhost:8080/photo_app/users
public class UserController {

    @Autowired
    UserService userService;

    /* To GET Authorization Token & UserId

    http://localhost:8080/photo_app/users/login
    {
        "email": "test@test.com",
        "password": "123"
    }

    Accept : application/json
    Content-Type : application/json

    */

    /*
    http://localhost:8080/photo_app/users
    {
        "firstName": "Sergey",
        "lastName": "Kargopolov",
        "email": "test@test.com",
        "password": "123"
    }

    Accept : application/json
    Content-Type : application/json

    */

    @PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest();

        // Handle UserServiceException
        // if(userDetails.getFirstName().isEmpty()) throw new UserServiceException("USExcp : "+ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        // Handle All Other Exception
        if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("NPE : "+ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    /* GET Authorization Token & UserId first using POST request http://localhost:8080/photo_app/users/login
     then

    http://localhost:8080/photo_app/users/generateduserid

    Accept : application/json
    Content-Type : application/json
    Authorization : token value

    */
    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }

    /* GET Authorization Token & UserId first using POST request http://localhost:8080/photo_app/users/login
     then

    http://localhost:8080/photo_app/users/generateduserid

    {
        "firstName": "Sergey",
        "lastName": "Kargopolov"
    }

    Accept : application/json
    Content-Type : application/json
    Authorization : token value

    */
    @PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto updateUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updateUser, returnValue);

        return returnValue;
    }

    /* GET Authorization Token & UserId first using POST request http://localhost:8080/photo_app/users/login
     then

    http://localhost:8080/photo_app/users/generateduserid

    Accept : application/json
    Content-Type : application/json
    Authorization : token value

    */
    @DeleteMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public OperationStatusModel deleteUser(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }

    /* GET Authorization Token & UserId first using POST request http://localhost:8080/photo_app/users/login
     then

    http://localhost:8080/photo_app/users?page=0&limit=5

    Accept : application/json
    Content-Type : application/json
    Authorization : token value

    */
    @GetMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<UserRest> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getUsers(page, limit);

        /*Type listType = new TypeToken<List<UserRest>>() {
        }.getType();
        returnValue = new ModelMapper().map(users, listType);*/

		for (UserDto userDto : users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(userDto, userModel);
			returnValue.add(userModel);
		}

        return returnValue;
    }


}
