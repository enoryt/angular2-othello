package gmu.isa681.othello.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController

public class RestController {

 @CrossOrigin(origins = "http://localhost:4200")
 @RequestMapping("/api/hello")
 public String greet() {
  return "Hello from the other side!!!";
 }
}
