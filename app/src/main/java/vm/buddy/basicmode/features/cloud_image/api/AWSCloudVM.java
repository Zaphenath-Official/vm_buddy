package vm.buddy.basicmode.features.cloud_image.api;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class AWSCloudVM {
    private Ec2Client ec2;
    private S3Client s3;
    private AwsBasicCredentials creds;

    // Call this on login or when AWS credentials are available
    /**
     * Initializes AWS clients with provided credentials and region.
     *
     * @param accessKey AWS Access Key ID
     * @param secretKey AWS Secret Access Key
     * @param region    AWS Region (e.g., "us-west-2")
     */
    public void handleAWSClient(String accessKey, String secretKey, String region) {
        creds = AwsBasicCredentials.create(accessKey, secretKey);
        ec2 = Ec2Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(creds))
            .build();
        s3 = S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(creds))
            .build();
    }

    // Call this on logout or exit
    public void clearCredentials() {
        creds = null;
        if (ec2 != null) ec2.close();
        if (s3 != null) s3.close();
        ec2 = null;
        s3 = null;
    }

    // 1. Retrieve instance from AWS to S3 bucket (export EC2 instance to S3)
    public String exportInstanceToS3(String instanceId, String s3Bucket, String s3Prefix) {
        ExportToS3TaskSpecification s3Spec = ExportToS3TaskSpecification.builder()
                .diskImageFormat(DiskImageFormat.VMDK)
                .containerFormat(ContainerFormat.OVA)
                .s3Bucket(s3Bucket)
                .s3Prefix(s3Prefix)
                .build();

        CreateInstanceExportTaskRequest request = CreateInstanceExportTaskRequest.builder()
                .instanceId(instanceId)
                .description("Exported by vm buddy")
                .exportToS3Task(s3Spec)
                .build();

        CreateInstanceExportTaskResponse response = ec2.createInstanceExportTask(request);
        System.out.println("Export task started: " + response.exportTask().exportTaskId());
        return response.exportTask().exportTaskId();
    }

    // 2. Download VM image to local machine (from S3)
    public void downloadImageFromS3(String s3Bucket, String s3Key, String localFilePath) throws Exception {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3Bucket)
                .key(s3Key)
                .build();
        try (InputStream s3Stream = s3.getObject(getObjectRequest);
             FileOutputStream fos = new FileOutputStream(localFilePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = s3Stream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        System.out.println("Downloaded s3://" + s3Bucket + "/" + s3Key + " to " + localFilePath);
    }

    // 3. Upload a VM image to S3 bucket from local machine
    public void uploadImageToS3(String localFilePath, String s3Bucket, String s3Key) {
        File file = new File(localFilePath);
        s3.putObject(PutObjectRequest.builder()
                        .bucket(s3Bucket)
                        .key(s3Key)
                        .build(),
                RequestBody.fromFile(file));
        System.out.println("Uploaded " + localFilePath + " to s3://" + s3Bucket + "/" + s3Key);
    }

    // 4. Retrieve a VM image from S3 bucket to AWS EC2 instance (import as AMI)
    public void importImageFromS3ToEC2(String s3Bucket, String s3Key, String imageName) {
        ImportImageRequest importRequest = ImportImageRequest.builder()
                .description("Imported by vm buddy")
                .diskContainers(
                        ImageDiskContainer.builder()
                                .userBucket(UserBucket.builder()
                                        .s3Bucket(s3Bucket)
                                        .s3Key(s3Key)
                                        .build())
                                .format("VMDK") // or "OVA", "VHD", etc.
                                .build()
                )
                .build();
        ImportImageResponse response = ec2.importImage(importRequest);
        System.out.println("Import task started: " + response.importTaskId());
    }

    // Optional: List EC2 instances
    public void listInstances() {
        DescribeInstancesResponse response = ec2.describeInstances();
        response.reservations().forEach(reservation -> {
            reservation.instances().forEach(instance -> {
                System.out.println("Instance ID: " + instance.instanceId() + ", State: " + instance.state().name());
            });
        });
    }
}
