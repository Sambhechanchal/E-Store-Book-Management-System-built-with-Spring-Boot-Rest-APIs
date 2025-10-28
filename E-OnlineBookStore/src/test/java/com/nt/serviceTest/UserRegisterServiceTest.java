package com.nt.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.FileEntity;
import com.nt.entity.UserRegister;
import com.nt.model.UserRequest;
import com.nt.model.UserRequestDto;
import com.nt.repository.FileRepository;
import com.nt.repository.UserRegisterRepo;
import com.nt.service.IUserRegisterService;

@SpringBootTest
public class UserRegisterServiceTest {
	
	
	@MockBean
	private UserRegisterRepo userRepo;
	
	
	@Autowired
	private  IUserRegisterService userService;
	
	@MockBean 
	private FileRepository fileRepo;
	
	
	@Test
	public void insertUserRegisterTest() {
		
		
		UserRequestDto userDto = new UserRequestDto();
		userDto.setFirstName("Chanchal");
		userDto.setLastName("sambhe");
		userDto.setEmail("c@gmail.com");
		userDto.setPassword("S@123");
		userDto.setContactId(977664353);
		
	
		  UserRegister user = new UserRegister();
	        user.setFirstName("chanchal");
	      user.setPassword(Base64.getEncoder().encodeToString("S@123".getBytes()));
	        user.setEmail("chanchalsambhe@gmail.com");
	        user.setContactId(907674378);
	   
	        when(userRepo.save(any(UserRegister.class))).thenReturn(user);
	        UserRegister result = userService.insertUserRegister(userDto);
	        
	        assertNotNull(result);
	        assertEquals("Chanchal",result.getFirstName());
	        assertEquals("c@gmail.com",result.getEmail());
	        verify(userRepo , times(1)).save(any(UserRegister.class));
	        
		
	}
	
	
	@Test
	public void getUserDetailsTest() {

		// input
		int id = 1;

		// output actual output
		UserRequest input = new UserRequest();
		input.setFirstName("chanchal");
		input.setLastName("Sambhe");

		// userRepo find method return
		UserRegister user = new UserRegister();
		user.setFirstName("chanchal");
		user.setLastName("sambhe");
		
		
		Optional<UserRegister> opt = Optional.of(user);

		// check the input and output
		when(userRepo.findById(id)).thenReturn(opt);

		// call the userService class method

		UserRequest userDetails = userService.getUserDetails(id);

		assertNotNull(userDetails);
		assertEquals("chanchal", userDetails.getFirstName());
		assertEquals("sambhe", userDetails.getLastName());
		verify(userRepo, times(1)).findById(id);

	}
	
	@Test
	public void checkUserDetailsTest() {

		
		// output actual input of method 
		UserRequestDto userDto = new UserRequestDto();
        userDto.setFirstName("chanchal");
        userDto.setLastName("Sambhe");
        userDto.setEmail("chanchalsambhe@gmail.com");
        userDto.setContactId(907674336);
        userDto.setPassword("123");
        
     
      // userRepo find method return
		UserRegister user = new UserRegister();
		user.setFirstName("chanchal");
		user.setLastName("sambhe");
		user.setEmail("chanchalsambhe@gmail.com");
		
	//   we are getting encoded password from database
	    user.setPassword(Base64.getEncoder().encodeToString("123".getBytes()));
	    
	  

		// check the input and output
		when(userRepo.findByEmail(userDto.getEmail())).thenReturn(user);

		// call the userService class method

		UserRegister userDetails=  userService.checkUserDetails(userDto);
System.out.println(userDetails);
		assertNotNull(userDetails);
		assertEquals("chanchalsambhe@gmail.com", userDetails.getEmail());
		
	//   we are getting encoded password from database
		String decoded = new String(Base64.getDecoder().decode(userDetails.getPassword()));
        assertEquals("123", decoded);
        // so to check or verify the password we need to decode it 
        verify(userRepo, times(1)).findByEmail(userDto.getEmail());

	}
	
	@Test
	public void uploadMultiUserRegisterTest() {
		
		// for service method input
		UserRequestDto userDto = new UserRequestDto();
		userDto.setFirstName("chanchal");
		userDto.setLastName("sambhe");
		userDto.setEmail("c@gmail.com");
		userDto.setPassword("S@123");
		userDto.setContactId(977664353);
		
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
	    MultipartFile[] files = new MultipartFile[]{file1, file2};
		
		// userRepo save method input
		  UserRegister user = new UserRegister();
	        user.setFirstName("chanchal");
	      user.setPassword(Base64.getEncoder().encodeToString("S@123".getBytes()));
	        user.setEmail("chanchalsambhe@gmail.com");
	        user.setContactId(907674378);
	        
	        
	        when(userRepo.save(any(UserRegister.class))).thenReturn(user);
	       // when(fileRepo.save(any(FileEntity.class))).thenReturn();
	        
//	        when(fileRepo.save(file)).thenReturn()
	        
	        UserRegister uploadMultiUserRegister = userService.uploadMultiUserRegister(userDto,files);
	        
	        assertNotNull(uploadMultiUserRegister);
	        assertEquals("chanchal", uploadMultiUserRegister.getFirstName());
	        verify(userRepo, times(1)).save(any(UserRegister.class));
	   //     verify(fileRepo, times(1)).save(any(FileEntity.class));
	}
	
	
	@Test
	public void getAllUserRegisterDataTest() {
		
		UserRegister user1 = new UserRegister();
		user1.setFirstName("shubhaman");
		user1.setLastName("Gill");
		user1.setEmail("S@gmail.com");
		
		UserRegister user2 = new UserRegister();
		user2.setFirstName("Virat");
		user2.setLastName("Kolhi");
		user2.setEmail("VK@gmail.com");
		
		List<UserRegister> list = List.of(user1,user2);
		
		when(userRepo.findAll()).thenReturn(list);
		
		List<UserRegister> data = userService.getAllUserRegisterData();
		assertNotNull(data);
		verify(userRepo , times(1)).findAll();
	}
}
