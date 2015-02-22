package com.ashishpaliwal.javatips.aws;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.Iterator;
import java.util.List;

/**
 * List Objects in S3 bucket
 */
public class S3Base {

  AmazonS3 s3;

  public void init(String accessKey, String secretKey) {
    s3 = new AmazonS3Client(new BasicAWSCredentials(accessKey, secretKey));
  }

  public void listAllBuckets() {
    // List all the buckets
    List<Bucket> buckets = s3.listBuckets();
    for (Bucket next : buckets) {
      System.out.println(next.getName());
      listBucketContent(next.getName());
    }
  }

  public void listBucketContent(String bucketName) {
    ObjectListing listing = s3.listObjects(new ListObjectsRequest().withBucketName(bucketName));
    for (S3ObjectSummary objectSummary : listing.getObjectSummaries()) {
      System.out.println(" -> " + objectSummary.getKey() + "  " +
              "(size = " + objectSummary.getSize()/1024 + " KB)");
    }
  }

  public static void main(String[] args) {
    S3Base base = new S3Base();
    base.init(args[0], args[1]);
    base.listAllBuckets();
  }

}
