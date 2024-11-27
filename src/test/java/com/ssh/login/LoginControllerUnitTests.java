package com.ssh.login;

import com.ssh.login.controller.LoginController;
import com.ssh.login.model.LoginProcessor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;


@ExtendWith(MockitoExtension.class)
public class LoginControllerUnitTests {
    
    @Mock
    private Model model;
    
    @Mock
    private LoginProcessor loginProcessor;
    
    @InjectMocks
    private LoginController loginController;
    
    @Test
    @DisplayName("Test login Ok")
    public void loginPostLoginSucceedsTest(){
        given(loginProcessor.login()).willReturn(true);
        String result = loginController.loginPost("username", "password", model);
        //assertEquals("login.html", result);
        assertEquals("redirect:/main", result);
        verify(model).addAttribute("message", "You are now logged in.");
        
    }
    
    @Test
    @DisplayName("Test login fail")
    public void loginPostLoginFailsTest(){
        given(loginProcessor.login()).willReturn(false);
        String result = loginController.loginPost("username", "password", model);
        assertEquals("login.html", result);
        verify(model).addAttribute("message", "Login failed!");
    }
    
}
