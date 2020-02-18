package com.donghyeon.designpattern.proxy;

import java.io.IOException;

public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void runCommand(String cmd) throws IOException {
        //많은 구현 소스들이 있다고 상상해주세요..ㅎ
        Runtime.getRuntime().exec(cmd);
        System.out.println("'" + cmd + "' 명령어가 실행됨.");
    }

}
