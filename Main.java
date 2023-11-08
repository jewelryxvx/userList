import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static long ai;
    public static void main(String[] args) {
        User user;
        Scanner console = new Scanner(System.in);
        System.out.println("Добро пожаловать!!!");
        System.out.println("Вы зарегестрированы?");
        String yesNo = console.nextLine();
        System.out.println("Введите ваше Имя:");
        String name = console.nextLine();
        System.out.println("Введите вашу Фамилию:");
        String lastName = console.nextLine();
        ArrayList<User> list = new ArrayList<User>();
        ArrayList<User> listCopy = new ArrayList<User>();
        File file = new File("User.txt");
        try {
            file.createNewFile();
            ai= Files.size(file.toPath());
        }
        catch(IOException e){
            e.printStackTrace();
        }
        if(yesNo.equals("да")) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<User>) objectInputStream.readObject();
                for (User u:list) {
                    if(u.getName().equals(name) && u.getLastName().equals(lastName)){
                        System.out.println("Вы вошли в систему, ваши данные: Имя "+u.getName()+" Фамилия: "+u.getLastName()+" Полных лет: "+u.getAge());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(yesNo.equals("нет")){
            System.out.println("Сколько вам лет?");
            int age = console.nextInt();
            if(ai == 0){
                try(ObjectOutputStream objectoutputStream = new ObjectOutputStream(new FileOutputStream(file)) ){
                        System.out.println("Поздравляю ваши данные будут записаны первыми!!!");
                        user = new User(name, lastName, age);
                        list.add(user);
                        objectOutputStream.writeObject(list);
                }catch(Exception e){
                    e.printStackTrace();
                }
                    }      
                    else if(ai>0){
                        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                          ObjectOutputStream objectoutputStream = new ObjectOutputStream(new FileOutputStream(file))){
                        listCopy = (ArrayList<User>) objectInputStream.readObject();
                        System.out.println("Лист загружен");
                        System.out.println( listCopy.get(0));
                        user = new User(name, lastName, age);
                        listCopy.add(user);
                        objectOutputStream.writeObject(list);
                        System.out.println("Ваши данные записаны!");
                    }catch(Exception e){
                            e.printStackTrace();
                    }
                }
        }

    }
}
