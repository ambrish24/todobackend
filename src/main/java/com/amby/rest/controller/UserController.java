package com.amby.rest.controller;

import com.amby.rest.exceptions.UserServiceException;
import com.amby.rest.model.request.UserDetailsRequestModel;
import com.amby.rest.model.response.ErrorMessages;
import com.amby.rest.model.response.UserRest;
import com.amby.rest.service.UserService;
import com.amby.rest.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users") // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    /*{
        "firstName": "Sergey",
        "lastName": "Kargopolov",
        "email": "test@test.com",
        "password": "123"
    }*/

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

    @GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public UserRest getUser(@PathVariable String id) {
        UserRest returnValue = new UserRest();

        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }


}
