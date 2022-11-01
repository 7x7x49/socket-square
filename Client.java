import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in); // создаем сканнер
        Socket socket = new Socket("localhost", 5555); // создаем свзяь с сервером
        while (true) { // бесконечно повторяем
            System.out.print("Введите число для возведения в квадрат: ");
            int number = sc.nextInt(); // просим пользователя ввести число для возведения в квадрат
            if (number < 0) break; // если оно меньше, чем 0, завершаем цикл
            OutputStream output = socket.getOutputStream(); // получаем штучку (поток данных) для передачи данных на сервер (поток для записи)
            output.write(number); // в которую мы записываем наше число
            output.flush(); // и отправляем на сервер
            InputStream input = socket.getInputStream(); // получаем поток данных для передачи данных от сервера (поток данных для чтения)
            int response = input.read(); // получаем число, которое мы возвели в квадрат на сервере
            System.out.println("Результат: " + response);
        }
        socket.close(); // закрываем связь с сервером
    }
}
