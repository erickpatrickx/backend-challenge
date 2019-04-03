package com.invillia.acme.auth.service;

import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.invillia.acme.auth.AuthApplication;
import com.invillia.acme.auth.dto.LoginDTO;
import com.invillia.acme.auth.service.AuthService;

/**
 * Teste para service do Auth
 * 
 * @author erick.oliveira
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class AuthServiceTest {

	@Autowired
	private AuthService authService;

	LoginDTO login = LoginDTO.builder().login("admin").senha("123456").build();
	LoginDTO loginFail = LoginDTO.builder().login("admin").senha("12345684 ").build();

	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);

	@Test
	public void deveRealizarLoginRetornarToken() throws IOException {
		String token = authService.login(request, response, login.getLogin(), login.getSenha());
		Assert.assertNotNull(token);
	}

	@Test
	public void naoDeveRealizarLoginRetornarToken() throws IOException {
		String token = authService.login(request, response, loginFail.getLogin(), loginFail.getSenha());
		Assert.assertNull(token);
	}
}