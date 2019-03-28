# Janos' Cloud Scanner

This application lets you scan one or more cloud accounts for compliance with a certain ruleset. For example, to scan
an AWS account to make sure there are no publicly readable S3 buckets, you can use this config file:

```yaml
---
connections:
  aws-test:
    type: aws
    accessKeyId: ""
    secretAccessKey: ""
  exoscale-test:
    type: exoscale
    key: ""
    secret: ""
rules:
  - type: S3_PUBLIC_READ_PROHIBITED
    include:
      - .*
    exclude:
      - .*public.*
  - type: FIREWALL_PUBLIC_SERVICE_PROHIBITED
    protocol: 6
    ports:
      - 22
    include:
      - .*
    exclude:
      - .*public.*
```

The result of the application will be like this:

```
exoscale-test	s3	opsbears-honeypot-terraform	COMPLIANT
aws-test	s3	elasticbeanstalk-us-east-1-556933211225	COMPLIANT
aws-test	s3	janoszen-access-test	NONCOMPLIANT
```

It is very similar to AWSConfig in its intention, but it is designed from the ground up to support multiple cloud
providers and accounts at once.

Note that at this time it is very early in development, so use at your own risk.