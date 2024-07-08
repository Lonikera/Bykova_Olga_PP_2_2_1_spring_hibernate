package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      /*

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

       */

      // Создание и добавление пользователей с машинами
      User user1 = new User("Альфия", "Дулькина", "alfi@mail.ru");
      User user2 = new User("Глафира", "Репкина", "repca@mail.ru");
      User user3 = new User("Сидор", "Иванов", "sidr@bk.ru");

      Car car1 = new Car("Opel", 12);
      Car car2 = new Car("Lexus", 7);
      Car car3 = new Car("Lada", 11);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      // Вывод списка пользователей
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car: " + user.getCar());
      }

      // Поиск пользователей по машине
      try {
         User user4 = userService.getUserByCarModelAndSeries("Audi", 14);
         System.out.println("Пользователь с автомобилем Audi 14: " + user4);
      } catch (NoResultException e) {
         System.out.println("Не найден пользователь с автомобилем Audi 14.");
      }

      try {
         User user5 = userService.getUserByCarModelAndSeries("Opel", 12);
         System.out.println("Пользователь с автомобилем Opel 12: " + user5);
      } catch (NoResultException e) {
         System.out.println("Не найден пользователь с автомобилем Opel 12.");
      }

      context.close();
   }
}
