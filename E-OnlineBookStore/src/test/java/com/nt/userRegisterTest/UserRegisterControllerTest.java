package com.nt.userRegisterTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.controller.UserRegisterController;
import com.nt.entity.UserRegister;
import com.nt.model.UserRequest;
import com.nt.model.UserRequestDto;
import com.nt.service.IUserRegisterService;

@WebMvcTest(UserRegisterController.class)
public class UserRegisterControllerTest {

    @MockBean
    private IUserRegisterService userService;

    @Autowired
    private MockMvc mock;

    @Test
    public void createUserRegister() throws Exception {

        UserRequestDto userDto = new UserRequestDto();
        userDto.setFirstName("chanchal");
        userDto.setLastName("Sambhe");
        userDto.setEmail("chanchalsambhe@gmail.com");
        userDto.setContactId(907674336);
        userDto.setPassword("123");

        UserRegister user = new UserRegister();
        user.setFirstName("chanchal1");
       // user.setLastName("Sambhe");
        user.setEmail("chanchalsambhe@gmail.com");
        user.setContactId(907674378);
      //  user.setPassword("123");

        when(userService.insertUserRegister(userDto)).thenReturn(user);

        mock.perform(
                post("/userregister")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto))
        ).andExpect(status().isCreated())
         .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
    
    @Test
    public void getLoginDetailsTest() throws Exception {
    	
		UserRequest userReq = new UserRequest();
		userReq.setFirstName("shubhaman");
		userReq.setLastName("Gill");

		Integer id = 1;

		when(userService.getUserDetails(id)).thenReturn(userReq);

		mock.perform(

				get("/userDetails/{id}", id).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(id)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
    
	@Test
	public void checkLoginTest() throws Exception {

		UserRequestDto userDto = new UserRequestDto();
		userDto.setFirstName("chanchal");
		userDto.setLastName("Sambhe");
		userDto.setEmail("chanchalsambhe@gmail.com");
		userDto.setContactId(907674336);
		userDto.setPassword("123");

		UserRegister user = new UserRegister();
		user.setFirstName("chanchal1");
		// user.setLastName("Sambhe");
		user.setEmail("chanchalsambhe@gmail.com");
		user.setContactId(907674378);
		// user.setPassword("123");

		when(userService.checkUserDetails(userDto)).thenReturn(user);

		mock.perform(post("/userLogin").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDto))).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	}
	@Test
	public void getLoginDetails3Test() throws Exception {

		UserRegister userReq = new UserRegister();
		userReq.setFirstName("shubhaman");
		userReq.setLastName("Gill");
		userReq.setEmail("S@gmail.com");

		Integer id = 1;

		when(userService.getUserDetails3(id)).thenReturn(userReq);

		mock.perform(

				get("/userDetails3/{id}", id).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(id)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	
	@Test
	public void saveUserWithFilesTest() throws Exception {

	   /* // Prepare DTO
	    UserRequestDto userDto = new UserRequestDto();
	    userDto.setFirstName("chanchal");
	    userDto.setLastName("Sambhe");
	    userDto.setEmail("chanchalsambhe@gmail.com");
	    userDto.setContactId(907674336);
	    userDto.setPassword("123");*/
		
		// json data
		String jsonData = "{\"firstName\":\"chanchal\",\"lastName\":\"sambhe\",\"email\":\"C@gmail.com\",\"contactId\":98977546563}";

	    // Prepare expected user entity
	    UserRegister user = new UserRegister();
	    user.setFirstName("chanchalS");
	    user.setEmail("chanchalsambhe@gmail.com");

	    // Prepare mock files
	    MockMultipartFile file1 = new MockMultipartFile(
	            "files",                    // must match @RequestParam name
	            "springQue.txt",
	            "text/plain",
	            "Spring Boot autoconfiguration test file".getBytes()
	    );

	    MockMultipartFile file2 = new MockMultipartFile(
	            "files",
	            "javaDoc.txt",
	            "text/plain",
	            "Java introduced by James Gosling...".getBytes()
	    );

		/*  // JSON data as string (this is what your controller expects)
		String json = new ObjectMapper().writeValueAsString(userDto);
		
		// Add jsonData part (it must be a string, not application/json content type)
		MockMultipartFile jsonData = new MockMultipartFile(
		        "jsonData",                 // must match @RequestParam name
		        "",
		        "application/json",
		        json.getBytes()
		);*/

	  MultipartFile[] files = new MultipartFile[]{file1, file2};
	    
	  UserRequestDto userObject = new ObjectMapper().readValue(jsonData, UserRequestDto.class);
	    when(userService.uploadMultiUserRegister(userObject, files)).thenReturn(user);

	    mock.perform(multipart("/userregisteruploadmultifile")
	    		 .file(file1)
	    		 .file(file2)
	             .param("jsonData",jsonData )
	                    .contentType(MediaType.MULTIPART_FORM_DATA)
	                    .accept(MediaType.APPLICATION_JSON))
	   .andDo(print())
	            .andExpect(status().isCreated())
	            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

	}
	
	@Test
	public void getAllUsersDetailsDataTest()throws Exception {
		
		UserRegister user1 = new UserRegister();
		user1.setFirstName("shubhaman");
		user1.setLastName("Gill");
		user1.setEmail("S@gmail.com");
		
		UserRegister user2 = new UserRegister();
		user2.setFirstName("Virat");
		user2.setLastName("Kolhi");
		user2.setEmail("VK@gmail.com");
		
		List<UserRegister> list = List.of(user1,user2);
		
		when(userService.getAllUserRegisterData()).thenReturn(list);
		mock.perform(
				get("/getallusersdetails")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(list))
				).andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
		
		
	}

}
