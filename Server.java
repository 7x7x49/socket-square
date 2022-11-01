import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) throws Exception {
        try (ServerSocket socket = new ServerSocket(5555)) { // создаем возможность связи сервера с клиентом (серверсокет)
            while (true) { // бесконечно повторяем
                Socket client = socket.accept(); // получаем последнего клиента
                InputStream input = client.getInputStream(); // получаем поток данных для чтения от клиента
                OutputStream output = client.getOutputStream(); // получаем поток для записи от клиента
                while (true) { // бесконечно повторяем
                    int request = input.read(); // получаем последнее число от клиента
                    if (request == -1) { // если его не существует (несуществующий байт равен -1)
                        System.out.printf("Соединение с %s разорвано\n", socket.getInetAddress().getHostAddress());
                        break; // разрываем связь
                    }
                    long start = System.currentTimeMillis(); // получаем текущее время
                    System.out.println("Число для возведения в квадрат: " + request);
                    int response = request * request; // вычисляем квадрат
                    System.out.println("Результат: " + response);
                    output.write(request * request); // записываем данные для клиента
                    output.flush(); // и отправляем их
                    System.out.printf("Посчитано и отправлено за %d миллисекунд\n", System.currentTimeMillis() - start);
                    // выводим в консоль разницу между текущим временем и временем начала вычисления
                }
            }
        }
    }
}
