package edu.cmpe283.hostCollector.util;

import com.google.gson.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;


public abstract class GenericCollector<T> implements IStatCollector {

    private LogWriter writer;
    private int sampleTime;
    private Thread collectorThread;

    public GenericCollector(LogWriter writer, int sampleTime) {
        this.writer = writer;
        this.sampleTime = sampleTime;
    }

    public void startCollector() {
        stopCollector();

        collectorThread = new Thread(new Runnable() {
            @Override
            public void run() {

                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Date.class,
                        new DateTimeSerializer());
                Gson gson = gsonBuilder.create();
                while (true) {
                    T data = getData();
                    if (data != null) {
                        try {
                            JsonElement element = gson.toJsonTree(data);
                            if (element.isJsonArray()) {
                                JsonArray a = element.getAsJsonArray();
                                for (JsonElement e : a) {
                                    writer.write(e.toString() + "\n");
                                }
                            } else {
                                writer.write(element.toString() + "\n");
                            }

                        } catch (IOException e) {

                        }
                        try {
                            java.lang.Thread.sleep(sampleTime);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            }
        });
        collectorThread.start();
    }

    private static class DateTimeSerializer implements JsonSerializer<Date> {

        @Override
        public JsonElement serialize(Date src,
                                     java.lang.reflect.Type typeOfSrc,
                                     JsonSerializationContext context) {
            SimpleDateFormat format = new SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            /*format.setCalendar(new GregorianCalendar(new SimpleTimeZone(0,
                    "GMT")));
            */
            JsonPrimitive p = new JsonPrimitive(format.format(src));
            return p;

        }
    }

    public abstract T getData();

    public void stopCollector() {
        if (collectorThread != null && collectorThread.isAlive()) {
            collectorThread.interrupt();
            try {
                collectorThread.wait();
            } catch (InterruptedException e) {
            }
        }
    }

}
