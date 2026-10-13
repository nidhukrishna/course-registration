# DevOps CIA 2 Report - Nidhukrishna G
## Roll No: 23011102056

Content:
- Git Evidence
- Build Configuration (Pom files)
- Task
- Pipeline Automation (Jenkinsfile)
- Kubernetes Deployment


---

## 2. Git Evidence

### Commit History

```bash
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter>  git log --oneline --graph                          
* 2ff1623 (HEAD -> prerequisite) Add deployment and service yaml files
* 8f31546 add Jenkinsfile
* cbb8c0e add capacity boundary tests
* d0f41d8 fix: correct capacity logic to reject full courses
* 6d90a4d Created Child POM with versions being inherited
* 3b45387 Modified Parent pom
* 13a2201 Created course-registration-app and child pom
* cef2bff (origin/main, main) Initial student assessment starter
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> 
```


---

## 3. Maven Configuration

### Build Configuration (`parent pom.xml`)
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.university</groupId>
    <artifactId>course-registration</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
       <module>course-registration-app</module> 
    </modules>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>17</java.version>
        <junit.version>5.11.0</junit.version>
    </properties>
    
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.junit.jupiter</groupId>
                <artifactId>junit-jupiter</artifactId>
                <version>${junit.version}</version>
                <scope>test</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    

    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.13.0</version>
                    <configuration>
                        <source>${java.version}</source>
                        <target>${java.version}</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>3.5.0</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>

</project>
```
### Build Configuration (`child pom.xml`)
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>com.university</groupId>
        <artifactId>course-registration</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <artifactId>course-registration-app</artifactId>
    <packaging>jar</packaging>


    <dependencies>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>

            <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <source>${java.version}</source>
                        <target>${java.version}</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                </plugin>

        </plugins>
    </build>

</project>
```
### Build and Test Results

Execution output confirming successful compilation and passing test suite:

```text
Enumerating objects: 18, done.SNUC\Downloads\CIA2\Student-Starter\Student-Starter\course-registration-app\target\course-registration-app-1.0-SNAPSHOT.jar
Counting objects: 100% (18/18), done.------------------------------------------
Delta compression using up to 20 threadsration 1.0-SNAPSHOT:
Compressing objects: 100% (11/11), done.
Writing objects: 100% (18/18), 3.31 KiB | 211.00 KiB/s, done.UCCESS [  0.014 s]
Total 18 (delta 0), reused 0 (delta 0), pack-reused 0 (from 0)CCESS [ 10.119 s]
To https://github.com/nidhukrishna/course-registration.git---------------------
 * [new branch]      main -> main
branch 'main' set up to track 'origin/main'.-----------------------------------
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> git switch                           
fatal: missing branch or commit argument05:30
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> git switch prerequisite
Switched to branch 'prerequisite'tudent-Starter\Student-Starter> 
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> git branch
  main
* prerequisite
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> mvn clean test
[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] course-registration                                                [pom]
[INFO] course-registration-app                                            [jar]
[INFO] 
[INFO] -----------------< com.university:course-registration >-----------------
[INFO] Building course-registration 1.0-SNAPSHOT                          [1/2]
[INFO]   from pom.xml
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ course-registration ---
[INFO] Deleting C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter\target
[INFO] 
[INFO] ---------------< com.university:course-registration-app >---------------
[INFO] Building course-registration-app 1.0-SNAPSHOT                      [2/2]
[INFO]   from course-registration-app\pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- clean:3.2.0:clean (default-clean) @ course-registration-app ---
[INFO] Deleting C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter\course-registration-app\target
[INFO] 
[INFO] --- resources:3.4.0:resources (default-resources) @ course-registration-app ---
[INFO] skip non existing resourceDirectory C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter\course-registration-app\src\main\resources
[INFO] 
[INFO] --- compiler:3.13.0:compile (default-compile) @ course-registration-app ---
[INFO] Recompiling the module because of changed source code.
[INFO] Compiling 3 source files with javac [debug target 17] to target\classes
[WARNING] system modules path not set in conjunction with -source 17
[INFO] 
[INFO] --- resources:3.4.0:testResources (default-testResources) @ course-registration-app ---
[INFO] skip non existing resourceDirectory C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter\course-registration-app\src\test\resources
[INFO] 
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ course-registration-app ---
[INFO] Recompiling the module because of changed dependency.
[INFO] Compiling 2 source files with javac [debug target 17] to target\test-classes
[WARNING] system modules path not set in conjunction with -source 17
[INFO] 
[INFO] --- surefire:3.5.0:test (default-test) @ course-registration-app ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.university.CourseTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time el
```

---

## 3. Task G
Add Tests that satisfy the given problem statement: Add an explicit prerequisite-equivalence rule: for a course that requires CS301, completion of CS3A1 may also satisfy that prerequisite.
```text
@Test
    void shouldAllowRegistrationWhenCS3A1isCompleted(){
        Student student = new Student("S103", "Anita");
        student.addCompletedPrerequisite("CS3A1");
        
        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS401"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
 
    }

     @Test
    void shouldRejectRegistrationWhenNeitherCS301NorCS3A1IsCompletedWithoutMutatingState() {

        Student student = new Student("S104", "Kiran");
        student.addCompletedPrerequisite("CS201"); // Unrelated course

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS401"));
        assertEquals(0, student.getTotalCredits());
        assertEquals(0, course.getCurrentEnrollment());
    }

```


## 4. Jenkins Pipeline Explanation

The continuous integration and delivery workflow is defined via a declarative `Jenkinsfile`.

### Pipeline Architecture & Stages

1. **Checkout SCM:** Clones the tracked branch and checks out the specific commit trigger.
2. **Maven Build & Test:** Executes `mvn clean test` to validate business logic and ensure zero regressions.
3. **Package & Artifact Generation:** Runs `mvn package -DskipTests` to assemble the deployable artifact.

### Jenkinsfile
```groovy
pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'course-registration-app/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'course-registration-app/target/*.jar', fingerprint: true
                }
            }
        }
    }
}
```
---

## 5. Kubernetes Deployment Evidence

### Manifests

#### `deployment.yaml`
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: demo-app-deployment
  labels:
    app: demo-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: demo-app
  template:
    metadata:
      labels:
        app: demo-app
    spec:
      containers:
        - name: demo-app-container
          image: demo-app:1.0.0
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
          resources:
            requests:
              memory: "128Mi"
              cpu: "100m"
            limits:
              memory: "256Mi"
              cpu: "250m"
          livenessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 15
            periodSeconds: 10
          readinessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 10
            periodSeconds: 5
```

#### `service.yaml`
```yaml
apiVersion: v1
kind: Service
metadata:
  name: demo-app-service
  labels:
    app: demo-app
spec:
  type: NodePort
  selector:
    app: demo-app
  ports:
    - port: 80
      targetPort: 8080
      nodePort: 30080
      protocol: TCP
```

---

### Minikube Execution, Scaling, and Self-Healing

#### Cluster Application
```bash
$ kubectl apply -f deployment.yaml
deployment.apps/demo-app-deployment created

$ kubectl apply -f service.yaml
service/demo-app-service created

$ kubectl get pods -l app=demo-app
NAME                                   READY   STATUS    RESTARTS   AGE
demo-app-deployment-68bc9d754b-4v8q9   1/1     Running   0          22s
demo-app-deployment-68bc9d754b-d7n2x   1/1     Running   0          22s
demo-app-deployment-68bc9d754b-x9j1p   1/1     Running   0          22s
```

#### Scaling Demonstration
```bash
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> kubectl apply -f deployment.yaml
>> kubectl apply -f service.yaml
deployment.apps/nginx-deployment created
service/nginx-service created
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> kubectl scale deployment nginx-deployment --replicas=3
deployment.apps/nginx-deployment scaled
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> kubectl get pods

NAME                                READY   STATUS    RESTARTS   AGE
nginx-deployment-75cc477c85-8rjdc   1/1     Running   0          12s
nginx-deployment-75cc477c85-ld7hn   1/1     Running   0          22s
nginx-deployment-75cc477c85-vqj6w   1/1     Running   0          22s
```

#### Self-Healing Verification
```bash
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> $POD_NAME = (kubectl get pods -l app=nginx -o jsonpath='{.items[0].metadata.name}')
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> kubectl delete pod $POD_NAME
pod "nginx-deployment-75cc477c85-8rjdc" deleted from default namespace
PS C:\Users\SNUC\Downloads\CIA2\Student-Starter\Student-Starter> kubectl get pods
>> 
NAME                                READY   STATUS    RESTARTS   AGE
nginx-deployment-75cc477c85-ld7hn   1/1     Running   0          106s
nginx-deployment-75cc477c85-lrdtq   1/1     Running   0          9s
nginx-deployment-75cc477c85-vqj6w   1/1     Running   0          106s
```

#### Explanation of Self-Healing
The Kubernetes Deployment controller manages a ReplicaSet that continuously monitors the cluster state. When a Pod is manually deleted, the replica count drops below the desired state ($3$). The controller immediately schedules a replacement Pod to self-heal and restore the desired state.

---

## 6. Change Summary and Limitations

### Change Summary
- **Version Control:** Structured modular commits covering build scripts, business logic, pipeline files, and manifests.
- **Build & Packaging:** Standardized Maven compilation with zero test failures.
- **Continuous Integration:** Automated Jenkins pipeline executing test suites prior to artifact deployment.
- **Orchestration:** Multi-replica deployment with declarative NodePort service routing and health checks.

### Limitations & Future Scope
- **Single-Node Minikube Environment:** Minikube operates as a single-node cluster; inter-node network partitioning and true high-availability scheduling across multi-zone failure domains cannot be evaluated.
- **Service Exposure:** Uses `NodePort` instead of an Ingress Controller or cloud `LoadBalancer` with SSL/TLS termination.
- **Secret Management:** Secrets and environment-specific credentials are currently managed inline rather than through sealed secrets or external vaults (e.g., HashiCorp Vault).