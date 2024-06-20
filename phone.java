
// Урок 6. Хранение и обработка данных ч3: множество коллекций Set
// Формат сдачи: ссылка на подписанный git-проект.
// Задание
// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что в во входной структуре будут повторяющиеся имена с разными телефонами,
// их необходимо считать, как одного человека с разными телефонами. 
// Вывод должен быть отсортирован по убыванию числа телефонов.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class PhoneBook {
    public HashMap<String, ArrayList<Integer>> phoneBook = new HashMap<>();

    public void addNumber(String name, Integer phoneNum) {

        ArrayList<Integer> numbers = phoneBook.getOrDefault(name, new ArrayList<>());
        numbers.add(phoneNum);
        phoneBook.put(name, numbers);
    }

    public ArrayList<Integer> find(String name) {
        return phoneBook.getOrDefault(name, new ArrayList<>());
    }

    public HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return phoneBook;
    }

    public HashMap<String, Integer> phoneCalc() {

        HashMap<String, Integer> phoneNumbers = new HashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> pair : phoneBook.entrySet()) {
            String name = pair.getKey();
            ArrayList<Integer> phones = pair.getValue();
            int numbers = phones.size();
            phoneNumbers.put(name, numbers);
        }
        // System.out.println(phoneNumbers);

        return phoneNumbers;
    }

    public void printBook() {
        System.out.println("Телефонный справочник в порядке заполнения");
        for (Map.Entry<String, ArrayList<Integer>> pair : phoneBook.entrySet()) {
            String name = pair.getKey();
            String str = "";
            str = str + name + ": ";
            ArrayList<Integer> phones = pair.getValue();
            for (int i = 0; i < phones.size(); i++) {
                str = str + phones.get(i) + " ";
            }
            System.out.println(str);
        }
        System.out.println(" ");
    }

    public void printBookForCountTlf(ArrayList<String> listName) {
        System.out.println("Телефонный справочник по убыванию количества номеров");
        for (int i = 0; i < listName.size(); i++) {
            String str = "";
            str = listName.get(i) + ": \t";
            ArrayList<Integer> tlfList = phoneBook.get(listName.get(i));
            for (int j = 0; j < tlfList.size(); j++) {
                str = str + tlfList.get(j) + " \t";
            }
            System.out.println(str);
        }
    }

    public static ArrayList<String> rangingName(HashMap<String, Integer> users) {

        HashMap<String, Integer> userNumber = users;

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();

        int findnumber;
        int userCount = 0;
        for (String key : userNumber.keySet()) {
            names.add(key);
            numbers.add(userNumber.get(key));
            userCount++;
        }
        ArrayList<Integer> numbersSort = (ArrayList<Integer>) numbers.clone();

        Collections.sort(numbersSort, Collections.reverseOrder());

        String nameuser = new String();
        ArrayList<String> usersSort = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            findnumber = numbersSort.get(i);

            for (int j = 0; j < userCount; j++) {

                if (findnumber == numbers.get(j)) {
                    nameuser = names.get(j);
                    usersSort.add(nameuser);
                    numbers.set(j, 0);
                    break;
                }
            }
        }
        return usersSort;
    }

    public static void main() {

        PhoneBook myPhoneBook = new PhoneBook();

        myPhoneBook.addNumber("Ivanov", 12346);
        myPhoneBook.addNumber("Sidorov", 7909123);
        myPhoneBook.addNumber("Sidorov", 8908123);
        myPhoneBook.addNumber("Ivanov", 654321);
        myPhoneBook.addNumber("Trofimov", 709128765);
        myPhoneBook.addNumber("Petrov", 8007);
        myPhoneBook.addNumber("Ivanov", 809876543);
        myPhoneBook.addNumber("Trofimov", 80987651);

        myPhoneBook.printBook();

        HashMap<String, Integer> users = myPhoneBook.phoneCalc();

        ArrayList<String> str2 = rangingName(users);

        System.out.println("Фамилии в порядке возрастания количества номеров телефонов");
        for (int i = 0; i < str2.size(); i++) {
            System.out.println(str2.get(i));

        }
        myPhoneBook.printBookForCountTlf(str2);
    }
}
