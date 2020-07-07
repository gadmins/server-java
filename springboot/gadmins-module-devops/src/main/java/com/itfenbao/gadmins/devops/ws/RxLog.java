package com.itfenbao.gadmins.devops.ws;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 9:22 下午
 */
@Slf4j
@Component
public class RxLog {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${logging.file.dir}")
    private String logDir;

    private Disposable disposable;
    private int len = 1;

    private boolean isStart = false;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private CopyOnWriteArraySet<LoggingWSServer> webSocketSet = new CopyOnWriteArraySet<LoggingWSServer>();


    public void addWebSocket(LoggingWSServer wsServer) {
        this.webSocketSet.add(wsServer);
    }

    public void removeWebSocket(LoggingWSServer wsServer) {
        this.webSocketSet.remove(wsServer);
        if (this.webSocketSet.isEmpty()) {
            stop();
        }
    }

    public void start() {
        if (isStart) {
            return;
        }
        isStart = true;
        disposable = Observable.interval(500, TimeUnit.MILLISECONDS)
                .map(s -> {
                    String filePath = String.format("%s/%s.log", logDir, applicationName);
                    BufferedReader reader = new BufferedReader(new FileReader(filePath));
                    String[] lines = reader.lines().toArray(String[]::new);
                    if (len == 1 && lines.length > 200) {
                        len = lines.length - 200;
                    }
                    //只取从上次之后产生的日志
                    String[] copyOfRange = Arrays.copyOfRange(lines, len, lines.length);

                    //对日志进行着色，更加美观  PS：注意，这里要根据日志生成规则来操作
                    for (int i = 0; i < copyOfRange.length; i++) {
                        String line = (String) copyOfRange[i];
                        //先转义
                        line = line.replaceAll("&", "&amp;")
                                .replaceAll("<", "&lt;")
                                .replaceAll(">", "&gt;")
                                .replaceAll("\"", "&quot;");

                        //处理等级
                        line = line.replace("DEBUG", "<span style='color: blue;'>DEBUG</span>");
                        line = line.replace("INFO", "<span style='color: green;'>INFO</span>");
                        line = line.replace("WARN", "<span style='color: orange;'>WARN</span>");
                        line = line.replace("ERROR", "<span style='color: red;'>ERROR</span>");

                        //处理类名
                        String[] split = line.split("]");
                        if (split.length >= 2) {
                            String[] split1 = split[1].split("-");
                            if (split1.length >= 2) {
                                line = split[0] + "]" + "<span style='color: #298a8a;'>" + split1[0] + "</span>" + "-" + split1[1];
                            }
                        }

                        copyOfRange[i] = line;
                    }

                    //存储最新一行开始
                    len = lines.length;

                    String result = String.join("<br/>", copyOfRange);
                    return result;
                })
                .observeOn(Schedulers.io())
                .subscribe(s -> {
                    sendMsg(s);
                });
    }

    private void sendMsg(String s) {
        if (StringUtils.isNotBlank(s)) {
            webSocketSet.forEach(session -> {
                try {
                    session.sendMessage(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void stop() {
        disposable.dispose();
        isStart = false;
    }
}
