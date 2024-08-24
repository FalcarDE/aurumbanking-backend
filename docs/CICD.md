# Pipeline
The configuration file for the pipeline can be found on:
> Path: .gitlab-ci.yml

Our has 3 stages: 
 - prepare
 - build
 - package

In the following are all the services with pipeline stages:

![all-services-stages-pipeline](images/pipeline/pipeline-1.png)
*Figure 1: This is the pipeline diagram illustrating all services workflow.*

Besides that we are running for each service several tests, you can see those on the pipeline test-result, too.

![test-pipeline](images/pipeline/pipeline-2.png)
*Figure 2: Pipeline-Test-Result .*


To speed up building and deployment, we are using pipeline runs that execute only the specific service as determined by the pipeline rules. 
These rules, which dictate how and when each part of the pipeline is executed, can be reviewed under the section "Pipeline Running Rules." 
This setup ensures a more efficient use of resources and minimizes the time required for each pipeline execution. 
Detailed information and configurations on how these pipeline runs are controlled 
and optimized for faster and more precise execution of our development and operations processes can be found in that section.

![test-pipeline](images/pipeline/pipeline-3.png)
*Figure 3: This is the pipeline diagram illustrating only one workflow with the branch-name matching rule.*




## Setup-Pipeline

**_token:_**

  - linux: **_glrt-xznuGhoqctjSmbVNxpm_**
  - windows-hoang: **_glrt-xznuGhoqctjSmbVNxpm_** / **_glrt-aHawSL4WALWi1s6BXdVi_**
  - steffan-gitlab-runner: **_glrt-Fzfyj9euFsuo1f_szyUo_**
  - milena-gitlab-runner: **_glrt-pzYxkWTn55mxxy4S4hXA_**
  - salma-gitlab-runner: **_glrt-sQu4HSKd7RgotJkPHwCn_**

```bash
docker run --rm -it -v gitlab-runner-config:/etc/gitlab-runner gitlab/gitlab-runner:latest register .\gitlab-runner.exe register --url https://git.ai.fh-erfurt.de --token [$token einfügen]
```

## Pipeline Running Rules

**_Set the branch name like the following rules, so only certain service related part
of the entire pipeline will be executed._**

### Customer Information Service

- **Prepare Build Cache**
    - Runs for branches matching: `feature/customer-information-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/customer-information-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/customer-information-service`
    - Also runs for the branch: `test/permant` and `main`



### Depot Service

- **Prepare Build Cache**
    - Runs for branches matching: `feature/depot-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/depot-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/depot-service`
    - Also runs for the branch: `test/permant` and `main`

### Login Service

- **Prepare Build Cache**
    - Runs for branches matching: `feature/login-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/login-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/login-service`
    - Also runs for the branch: `test/permant` and `main`


### Support Service

- **Prepare Build Cache**
    - Runs for branches matching: `feature/support-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/support-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/support-service`
    - Also runs for the branch: `test/permant` and `main`


### Transaction Service

- **Prepare Build Cache**
    - Runs for branches matching: `feature/transaction-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build**
    - Runs for branches matching: `feature/transaction-service`
    - Also runs for the branch: `test/permant` and `main`

- **Build Image**
    - Runs for branches matching: `feature/transaction-service`
    - Also runs for the branch: `test/permant` and `main`


### Documentation

- **Prepare Documentation Pages**
    - Runs for branches matching: `docs/`
    - Also runs for the branch: `test/permant` and `main`


- **Build Documentation Pages**
    - Runs for branches matching: `docs/`
    - Also runs for the branch: `test/permant` and `main`
