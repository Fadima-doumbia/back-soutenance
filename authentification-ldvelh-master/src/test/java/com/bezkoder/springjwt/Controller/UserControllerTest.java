package com.bezkoder.springjwt.Controller;

import com.bezkoder.springjwt.controllers.UserController;
import com.bezkoder.springjwt.models.User;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    User user;



}
