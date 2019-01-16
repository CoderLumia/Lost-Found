package com.lumia.lostfound.dao;

import com.lumia.lostfound.entity.Academy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyMapperTest {

    @Autowired
    private AcademyMapper academyMapper;

    @Test
    public void testSave(){
        List<Academy> academies = academyMapper.selectList(null);
        for (Academy academy : academies){
            System.out.println(academy);
        }

    }
}
