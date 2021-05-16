# Final Evaluation Project
## What's new
### 1. Add delete feature
1. Unify request ID and File ID using same UUID
2. User can delete generated report by clicking *delete*, Excel report and PDF report
will be deleted from service repository accordingly.
3. Return 200 if delete success, 400 bad request if delete fail.

### 2. Use Redis in the ExcelRepositoryImpl
Instead of using concurrent hashmap, now Redis is used as Excel service repository.
### 3. Add Login Page using Spring Security
Add two authority roles "USER" and "ADMIN" using inMemoryAuthentication
When project starts, user must be logged in through login page first.
## What's improvement
### 1. Use multithreading in generateReportsSync
1. Configure thread pool in task executor through AsynConfig.
2. Change send direct report requests (PDF report request followed by Excel report request) to parallel process.

### 2. Improve email sending feature
Due to google security setting issue, personal gmail account is not allowed to send email through Amazon Lambda.
Change gmail sender to personal 163 mail box and enable SSL (change port number to 465)
### 3. Add Junit test cases
1. Test Jedis connection and save/delete file using RedisTemplate
2. Test save Excel File to Redis and delete Excel file from Redis
3. Rewrite test send email (due to anomaly on Email-queue and Lambda invocation)

### 4. Fix deployment issue and improve robustness
1. Set up AWS S3/SQS/SNS phase → Region differs, SQS and SNS in east-2 cannot link to S3 located in east-1:
  **Change S3 back to the same region as SQS and resolved**
2. Naming issue on my customized *PDF-request-queue* and *PDF_Request_queue* in original code → rise *the specific queue is non-existent exception*:

  **Change @SqsListener("PDF-response-queue") and  @SqsListener("Excel-response-queue") in client service ReportSQSListener**
3. Close outputstream in *ExcelGenerationServiceImpl.generateExcelReport()*
4. Fix hardcode name in *ExcelGenerationController.downloadExcel()*, replace hardcode with file ID.
