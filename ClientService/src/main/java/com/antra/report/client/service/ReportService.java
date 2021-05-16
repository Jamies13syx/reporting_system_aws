package com.antra.report.client.service;

import com.antra.report.client.pojo.FileType;
import com.antra.report.client.pojo.reponse.ReportVO;
import com.antra.report.client.pojo.reponse.SqsResponse;
import com.antra.report.client.pojo.request.ReportRequest;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ReportService {
    CompletableFuture<ReportVO> generateReportsSync(ReportRequest request);

    ReportVO generateReportsAsync(ReportRequest request);

    void updateAsyncPDFReport(SqsResponse response);

    void updateAsyncExcelReport(SqsResponse response);

    List<ReportVO> getReportList();

    InputStream getFileBodyByReqId(String reqId, FileType type);

    boolean deleteReportById(String Id);
}
