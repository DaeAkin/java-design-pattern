package com.donghyeon.designpattern.proxy;

public class CommandExecutorProxy implements CommandExecutor {

    private boolean isAdmin;
    private CommandExecutor executor;

    public CommandExecutorProxy(String user, String pwd){
        if("donghyeon".equals(user) && "daeakin@".equals(pwd)) isAdmin=true;
        executor = new CommandExecutorImpl();
    }

    @Override
    public void runCommand(String cmd) throws Exception {
        if(isAdmin){
            executor.runCommand(cmd);
        }else{
            if(cmd.trim().startsWith("rm")){
                throw new Exception("관리자가 아니면 rm 명령어를 사용할 수 없습니다.");
            }else{
                executor.runCommand(cmd);
            }
        }
    }

}
