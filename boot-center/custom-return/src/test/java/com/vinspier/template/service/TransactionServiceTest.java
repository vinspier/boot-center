package com.vinspier.template.service;


import com.vinspier.template.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void doWithinCheckedEpThrow() throws Exception{
        transactionService.doWithinCheckedEpThrow();
    }

    @Test
    public void doWithinUnCheckedEpThrow() {
        transactionService.doWithinUnCheckedEpThrow();
    }

}