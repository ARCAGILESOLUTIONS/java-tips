package com.ashishpaliwal.javatips.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class reads a specified object/file from S3 and writes locally
 */
public class S3Reader {

  public static final int BUFFER_SIZE = 64 * 1024;

  AmazonS3 s3;

  public void init(String accessKey, String secretKey) {
    s3 = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
  }

  /**
   * Read a character oriented file from S3
   *
   * @param bucketName  Name of bucket
   * @param key         File Name
   * @throws IOException
   */
  public void readFromS3(String bucketName, String key) throws IOException {
    S3Object s3object = s3.getObject(new GetObjectRequest(
            bucketName, key));
    System.out.println(s3object.getObjectMetadata().getContentType());
    System.out.println(s3object.getObjectMetadata().getContentLength());

    BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
    String line;
    while((line = reader.readLine()) != null) {
      // can copy the content locally as well
      // using a buffered writer
      System.out.println(line);
    }
  }

  /**
   * Read a binary file from S3
   *
   * @param bucketName  bucket name
   * @param key         file name
   * @throws IOException
   */
  public void readS3ObjectUsingByteArray(String bucketName, String key) throws IOException {
    S3Object s3object = s3.getObject(new GetObjectRequest(
            bucketName, key));

    InputStream stream = s3object.getObjectContent();
    byte[] content = new byte[BUFFER_SIZE];

    int totalSize = 0;

    int bytesRead;
    while ((bytesRead = stream.read(content)) != -1) {
      System.out.println(String.format("%d bytes read from stream", bytesRead));
      totalSize += bytesRead;
    }
    System.out.println("Total Size of file in bytes = "+totalSize);
  }

  /**
   * Read a binary file from S3
   *
   * @param bucketName  bucket name
   * @param key         file name
   * @throws IOException
   */
  public void readS3ObjectUsingByteArray(String bucketName, String key, String outputFileName) throws IOException {
    S3Object s3object = s3.getObject(new GetObjectRequest(
            bucketName, key));

    InputStream stream = s3object.getObjectContent();
    byte[] content = new byte[BUFFER_SIZE];

    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFileName));

    int totalSize = 0;

    int bytesRead;
    while ((bytesRead = stream.read(content)) != -1) {
      System.out.println(String.format("%d bytes read from stream", bytesRead));
      outputStream.write(content, 0, bytesRead);
      totalSize += bytesRead;
    }
    System.out.println("Total Size of file in bytes = "+totalSize);

    // close resource even during exception

    outputStream.close();
  }

  public static void main(String[] args) throws IOException {
    S3Reader s3Reader = new S3Reader();
    s3Reader.init(args[0], args[1]);
    s3Reader.readFromS3("paliwalashish", "bucket1/Sample10k.txt");
    s3Reader.readS3ObjectUsingByteArray("paliwalashish", "bucket1/Sample10k.txt");
    s3Reader.readS3ObjectUsingByteArray("paliwalashish", "bucket1/Sample10k.txt", "Sample10kOutput.txt");
  }

}
