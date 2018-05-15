package com.kapil.sample;

import com.google.gson.Gson;
import com.kapil.sample.network.data.Scenario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import rx.Observable;
import rx.Subscriber;
import java.lang.reflect.Type;

/**
 * Created by kapilsharma on 04/05/18.
 */

public class CommonUtils {

    private static final Random randomGenerator = new Random();






    public static <T> Observable<T> readFromFile(String fileName, Class<T> clazz) {
        return readFromFile(fileName).map(string -> new Gson().fromJson(string, clazz));
    }


    public static  Observable<ArrayList<Scenario>> readFromFile(String fileName, Type type) {
        return readFromFile(fileName).map(string -> new Gson().fromJson(string, type));
    }

    public static Observable<String> readFromFile(String fileName) {
        return Observable.create(subscriber -> {
            try {
                InputStream inputStream = MainApplication.getInstance().getAssets().open(fileName);
                subscriber.onNext(readInputStream(inputStream));
            } catch (FileNotFoundException e) {
                subscriber.onError(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static String readInputStream(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        StringBuffer stringBuffer = new StringBuffer();
        while (scanner.hasNext()) {
            stringBuffer.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuffer.toString();
    }

    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return randomGenerator.nextInt((max - min) + 1) + min;
    }
}
