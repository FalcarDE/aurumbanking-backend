# App und Backend Testing

## Unit Testing in the Backend-System

<details>
<summary> Unit Testing in the Backend-System </summary>

In our project, we place a strong emphasis on ensuring the reliability and stability of our services through comprehensive unit testing. Our testing strategy is incorporated directly into the CI/CD pipeline, which ensures that our services are consistently tested before any changes are integrated.

<h3> Unit Tests for Services </h3>

<p> We have implemented a robust set of unit tests that target various layers of our services. These tests focus on the following areas: </p>

<ul>
    <li><strong>Endpoint Testing (Resource Tests):</strong>
        <p>Resource tests are specifically focused on testing the external endpoints of our services. These tests simulate real-world API calls to ensure that the endpoints are functioning correctly, returning the expected responses, and handling error scenarios appropriately. This ensures that our services provide the correct output and behavior when interacting with external clients.</p>
    </li>
    <li><strong>Repository Testing (Repository Tests):</strong>
        <p>Our repository tests are responsible for testing the interaction with the database. These tests validate that the data layer behaves correctly, ensuring that our database queries and transactions are executed as expected. This helps in verifying that the data is being correctly retrieved, stored, and manipulated by the application.</p>
    </li>
</ul>

<h3> CI/CD Pipeline Integration </h3>

<p>Our CI/CD pipeline is configured to automatically run all unit tests whenever a change is pushed to the repository. This integration ensures that:</p>

<ol>
    <li><strong>Test Results in Pipeline:</strong>
        <p>The pipeline captures the results of all unit tests, including the service, resource, and repository tests. Any failure in these tests prevents the build from proceeding, ensuring that only thoroughly tested code is deployed.</p>
    </li>
</ol>

<h3> Pipeline Test Results </h3>

<div style="text-align: center;">

<figure>
    <img src="images/testing/pipeline-test-results.png" width="100%">
    <figcaption>Pipeline-Test for all services  </figcaption>
</figure>

</div>

<p> The pipeline logs and reports provide detailed information about the status and results of each test, helping us to maintain a reliable and stable codebase throughout the development lifecycle. </p>

<p> For more details, check the code implementation in the respective service within the <strong> test </strong> directory of each service. </p>

</details>



## UI - Testing in the App

<details>
<summary> UI Monkey Tests </summary>

<p> To Ensure a UI-Test we perform an UI monkey testing, which randomly interacts with the user interface to catch unexpected crashes or issues. 
This test simulates a wide range of user interactions to ensure the robustness of our UI. 
These UI tests are also integrated into our pipeline, providing a broader validation of the system's stability.
</p>

<p>To run the Monkey Test, go to the root folder of the app project and execute the following command:</p>

<pre><code> adb shell monkey -p de.fhe.ai.mc --throttle 50 -v 500 </code></pre>

<p>Below is a brief excerpt from the Monkey Test in our app: </p>

<figure>
    <img src="images/testing/MonkeyTest.gif" width="100%">
    <figcaption> Monkey-Test Example in the AurumBanking-App </figcaption>
</figure>


</details>

