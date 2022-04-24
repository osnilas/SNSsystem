# Supplementary Specification (FURPS+)

## Functionality


|   **_Function_**   | Description/Example                                                                   |
|:------------------:|:--------------------------------------------------------------------------------------|
|     **Email**      | Adding services related to sending email                                              |
|   **Reporting**    | Support for generating reports                                                        |
| **Authentication** | Different functionalities depending on the user (example: Administrator, nurse, etc.) |
| **Communication**  | Communication and data sharing between different vaccination centers                  |
|    **Security**    | Controlled access to certain system features or data                                  |
| **Accessibility**  | Support for English and Portuguese language                                           |




## Usability


**Human Factor**

Speed, ease, and error-free processing are paramount in the operation of an application, as the SNS user wishes 
to leave quickly, or they perceive the vaccination process (and) as less positive. 
The same can be said of the experience of the nurse or receptionist as delays can cause larger problems in the vaccination process

    

## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


(fill in here )

## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


(fill in here )

## Supportability

_**Configurability**_

Vaccines, vaccination capacities, personnel numbers, and so on will differ between vaccination centers.They desire the ability to modify these configurations to reflect the evolution of the vaccination process and performance needs.
Therefore,the system will be somewhat configurable to reflect these needs.

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

  During the system development, the team must: (i) adopt best practices for identifying
 requirements, and for OO software analysis and design; (ii) adopt recognized coding standards (e.g.,
 CamelCase); (iii) use Javadoc to generate useful documentation for Java code.

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._


 The application must be developed in Java language using the IntelliJ IDE or NetBeans. The
application graphical interface is to be developed in JavaFX 11.

 The development team must implement unit tests for all methods, except for methods that
implement Input/Output operations. The unit tests should be implemented using the JUnit 5
framework. The JaCoCo plugin should be used to generate the coverage report.

**The application should run on Microsoft Windows, macOS and several Unix-like OSs.**

 The application should use object serialization to ensure data persistence between two runs of the
application.
### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


(fill in here )

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here )