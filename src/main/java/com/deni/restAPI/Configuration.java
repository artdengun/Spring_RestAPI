package com.deni.restAPI;


import com.deni.restAPI.entity.Employee;
import com.deni.restAPI.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class Configuration {

    public Configuration(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // kita inject data
    private EmployeeRepository employeeRepository;

    // hello world
    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    // UPDATE DATA
    @PutMapping("/{id}")
    public ResponseEntity<?> putEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        employee.setId(id);
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // CARI DATA BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    // TAMBAH DATA
    @PostMapping
    public ResponseEntity<?> postEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    // BACA DATA SEMUANYA
    @GetMapping
    public ResponseEntity<?> getEmployee() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    // HAPUS DATA

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmploye(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
