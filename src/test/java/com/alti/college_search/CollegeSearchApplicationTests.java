package com.alti.college_search;

import com.alti.college_search.models.ResponseObj;
import com.alti.college_search.service.CollegeSearchService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class CollegeSearchApplicationTests {

    @Autowired
    CollegeSearchService collegeSearchService;

    @Test
    void checkSchoolsLoaded() {
        // given
        Integer count = 1;

        // when
        ResponseObj responseObj = collegeSearchService.getFilteredSchools("52556", 2017, 10, 0, null);
        Integer size = responseObj.getSchools().size();

        // then
        Assert.assertEquals(count, size);
    }

}
