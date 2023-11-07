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
        Scanner console = new Scanner(System.in);
        System.out.println("Добро пожаловать!!!");
        System.out.println("Вы зарегестрированы?");
        String yesNo = console.nextLine();
        if(yesNo.equals("да")) {
            System.out.println("Введите ваше Имя:");
            String name = console.nextLine();
            System.out.println("Введите вашу Фамилию:");
            String lastName = console.nextLine();
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
            System.out.println("Введите ваше имя: ");
            String name = console.nextLine();
            System.out.println("Введите фамилию: ");
            String lastName = console.nextLine();
            System.out.println("Сколько вам лет?");
            int age = console.nextInt();
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                    if(ai == 0){
                        System.out.println("Поздравляю ваши данные будут записаны первыми!!!");
                        user = new User(name, lastName, age);
                        list.add(user);
                        objectOutputStream.writeObject(list);
                    }
                    else{
                        System.out.println("Лист создан");
                        listCopy = ((ArrayList<User>)objectInputStream.readObject());
                        System.out.println("Лист загружен");
                        System.out.println( listCopy.get(0));
                       /* user = new User(name, lastName, age);
                        list.add(user);
                        objectOutputStream.writeObject(list);
                        System.out.println("Ваши данные записаны!");*/
                    }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}