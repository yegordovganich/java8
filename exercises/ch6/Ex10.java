package exercises.ch6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by y.dovganich on 06.04.2017.
 */
/*
10. Write a program that asks the user for a URL, then reads the web page at that
URL, and then displays all the links. Use a CompletableFuture for each stage.
Don’t call get. To prevent your program from terminating prematurely, call
ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
 */
public class Ex10 {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://shakhtar.com/");

        CompletableFuture.supplyAsync(() -> readPage(url))
            .thenApply(page -> getLinks(page))
            .thenAccept(list -> list.forEach(System.out::println));

        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
    }

    private static String readPage(URL url) {
        try {
            URLConnection connection = url.openConnection();
            StringBuilder contetn = new StringBuilder();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) contetn.append(inputLine);
            }
            return contetn.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error when reading page.";
        }
    }

    private static List<String> getLinks(String page) {
        List<String> links = new ArrayList<>();
        Pattern pattern = Pattern.compile("(?i)href=\"https?://(.*?)\"");
        Matcher matcher = pattern.matcher(page);
        while (matcher.find()) {
            links.add(matcher.group(1));
        }
        return links;
    }
}
