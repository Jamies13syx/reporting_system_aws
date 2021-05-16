package com.antra.evaluation.reporting_system;

import com.antra.evaluation.reporting_system.pojo.api.PDFRequest;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import com.antra.evaluation.reporting_system.service.*;
public class PDFTest {

    @Test
    public void findFile() throws FileNotFoundException {
        File f = new File("Coffee_Landscape.jasper");
        System.out.println(f.exists());

        File file = ResourceUtils.getFile("classpath:Coffee_Landscape.jasper");
        System.out.println(file.exists());
    }

}
