package com.example.demo.provider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactUrl;
import au.com.dius.pact.provider.junitsupport.target.Target;
import au.com.dius.pact.provider.junitsupport.target.TestTarget;
import com.example.demo.TaskBackendApplication;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;

@RunWith(PactRunner.class)
@Provider("Tasks")
@PactUrl(urls = {"file:///C:\\Users\\Win10\\Projetos\\Pact\\demo-pact-backend\\src\\test\\resources\\BasicConsumer-Tasks.json"})
public class TasksProviderTest {
    @TestTarget
    public final Target target = new HttpTarget("localhost",8080); // onde o provedor esta funcionando

    @BeforeClass
    public static void setup(){
        SpringApplication.run(TaskBackendApplication.class);
    }
    @State("There is a task with id = 1")
    public void saveTask1(){

    }

}
