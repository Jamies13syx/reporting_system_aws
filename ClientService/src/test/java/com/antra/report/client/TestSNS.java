package com.antra.report.client;

import com.antra.report.client.pojo.reponse.ReportVO;
import com.antra.report.client.pojo.request.ReportRequest;
import com.antra.report.client.service.ReportService;
import com.antra.report.client.service.SNSService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSNS {

    @Autowired
    SNSService snsService;

    @Autowired
    ReportService reportService;

    @Test
    public void testReportService() throws ExecutionException, InterruptedException {
        ReportRequest request = new ReportRequest();
        request.setSubmitter("this_test");
        request.setDescription("This test thread pool");
        request.setHeaders(List.of("Id","Name","Age"));
        request.setData(List.of(List.of("1","Dd","23"),List.of("2","AJ","32")));
        ReportVO reportVO = reportService.generateReportsSync(request).get();
        System.out.println(reportVO);
    }
    @Test
    public void testSNSSend() {
        ReportRequest request = new ReportRequest();
        request.setSubmitter("Dawei_test");
        request.setDescription("This is just a test");
        request.setHeaders(List.of("Id","Name","Age"));
        request.setData(List.of(List.of("1","Dd","23"),List.of("2","AJ","32")));

//        snsService.generateReport(request);
    }
}
