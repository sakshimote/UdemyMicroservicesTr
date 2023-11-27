package com.example.demo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.AutoUserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserServiceImpl;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    
 
    private User user;

    @BeforeEach
    public void setup(){
        //employeeRepository = Mockito.mock(EmployeeRepository.class);
        //employeeService = new EmployeeServiceImpl(employeeRepository);
        user = User.builder()
                .id(1L)
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
    }

    // JUnit test for savee method
    @DisplayName("JUnit test for saveUser method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){
        // given - precondition or setup
        given(userRepository.findByEmail(user.getEmail()))
                .willReturn(Optional.empty());

        given(userRepository.save(user)).willReturn(user);

        System.out.println(userRepository);
        System.out.println(userServiceImpl);

        // when -  action or the behaviour that we are going test
        UserDto userDto=AutoUserMapper.MAPPER.mapToUserDto(user);
        UserDto user = userServiceImpl.addUser(userDto);
        

        System.out.println(userDto);
        // then - verify the output
        assertThat(userDto).isNotNull();
    }
    


    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
    @Test
    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
        // given - precondition or setup

       User employee1 = User.builder()
                .id(2L)
                .firstName("Tony")
                .lastName("Stark")
                .email("tony@gmail.com")
                .build();

        given(userRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<UserDto> employeeList = userServiceImpl.getAllUsers();

        // then - verify the output
        assertThat(employeeList).isEmpty();
        assertThat(employeeList.size()).isEqualTo(0);
    }


    // JUnit test for getAllEmployees method
    @DisplayName("JUnit test for getAllEmployees method")
    @Test
    public void givenEmployeesList_whenGetAllEmployees_thenReturnEmployeesList(){
        // given - precondition or setup

        User user1 = User.builder()
                .id(2L)
                .firstName("Tony")
                .lastName("Stark")
                .email("tony@gmail.com")
                .build();

        given(userRepository.findAll()).willReturn(List.of(user,user1));

        // when -  action or the behaviour that we are going test
        List<UserDto> employeeList = userServiceImpl.getAllUsers();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    // JUnit test for getEmployeeById method
    @DisplayName("JUnit test for getEmployeeById method")
    @Test
    public void givenEmployeeId_whenGetEmployeeById_thenReturnEmployeeObject(){
        // given
        given(userRepository.findById(1L)).willReturn(Optional.of(user));

        // when
      UserDto savedEmployee = userServiceImpl.getUserById(user.getId());

        // then
        assertThat(savedEmployee).isNotNull();

    }
    
    
    
//    
// // JUnit test for saveEmployee method
//    @DisplayName("JUnit test for saveEmployee method which throws exception")
//    @Test
//    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){
//        // given - precondition or setup
//        given(userRepository.findByEmail(user.getEmail()))
//                .willReturn(Optional.of(user));
//UserDto userDto=AutoUserMapper.MAPPER.mapToUserDto(user);
//
//        // when -  action or the behaviour that we are going test
//       assertThrows(ResourceNotFoundException.class, () -> {
//           userServiceImpl.addUser(userDto);
//        });
//
//        
//        // then
//        verify(userRepository, never()).save(any(User.class));
//    }
//    
//    
//    

    // JUnit test for updateEmployee method
//    @DisplayName("JUnit test for updateEmployee method")
//    @Test
//    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
//        // given - precondition or setup
//        given(userRepository.save(user)).willReturn(user);
//        user.setEmail("ram@gmail.com");
//        user.setFirstName("Ram");
//        // when -  action or the behaviour that we are going test
//        UserDto userDto=AutoUserMapper.MAPPER.mapToUserDto(user);
////       UserDto updatedEmployee = userServiceImpl.updateUser(userDto);
////
////        // then - verify the output
////        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
////        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
    }

    // JUnit test for deleteEmployee method
//    @DisplayName("JUnit test for deleteEmployee method")
//    @Test
//    public void givenEmployeeId_whenDeleteEmployee_thenNothing(){
//        // given - precondition or setup
//        long employeeId = 1L;
//
//        willDoNothing().given(userRepository).deleteById(employeeId);
//
//        // when -  action or the behaviour that we are going test
////       userServiceImpl.deleteUser(employeeId);
////
////        // then - verify the output
////        verify(userRepository, times(1)).deleteById(employeeId);
//    }
//}
