package com.example.member;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MemberApplication {
    
    static {
        // .envファイルを明示的に読み込む
        Dotenv dotenv = Dotenv.configure()
            .directory("/Users/tsuda/project/spring-book-src-master/jwtsample")  // プロジェクトの実際のパス
            .filename(".env")  // ファイル名
            .load();

        // JWT_SECRET 環境変数を設定
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
    }

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(MemberApplication.class, args);
    }
}

