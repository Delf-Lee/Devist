package com.tdl.devist.model;

import com.tdl.devist.repository.TestModelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Profile("dev")
public class TestModelTests {
    @Autowired
    private TestModelRepository testModelRepository;

    @Test
    public void testTestModel() {
        TestModel testModel = new TestModel();
        testModel.setName("test");
        testModel.setContent("test content");
        testModelRepository.save(testModel);

        TestModel tm = testModelRepository.findAll().get(0);
        Assert.assertEquals("test", tm.getName());
        Assert.assertEquals("test content", tm.getContent());
    }
}
