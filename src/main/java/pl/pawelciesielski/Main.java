package pl.pawelciesielski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }
    public List<String> addStar(List<String> strings) {
       return strings.stream()
               .map(s -> s + "*")
               .collect(Collectors.toList());
    }



}
