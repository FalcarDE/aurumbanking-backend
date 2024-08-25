# App Frontend


## Login Screen

<details>
<summary> Login Screen </summary>

<div style="display: flex; justify-content: center; align-items: center;">

    <figure style="margin: 0 20px; text-align: center;">
        <img src="images/app/login-screen/login-screen.png" width="60%">
        <figcaption>Screenshot of login screen</figcaption>
    </figure>

    <figure style="margin: 0 20px; text-align: center;">
        <img src="images/app/login-screen/login-screen-wrong-credentials.png" width="60%">
        <figcaption>Screenshot of failed login</figcaption>
    </figure>

</div>

The <strong>Login Screen </strong> in the <strong> AurumBanking-App </strong> provides users with a simple interface to enter their email and password for authentication.

<h3> Screen Elements </h3>

<ul>
    <li><strong>Penguin Mascot</strong>: A friendly penguin mascot is displayed prominently at the top of the screen, providing a welcoming visual.</li>
    <li><strong>Email Field</strong>: A text input field where the user can enter their email address.</li>
    <li><strong>Password Field</strong>: A password input field where the user can enter their account password. The password is masked for security.</li>
    <li><strong>Login Button</strong>: A button labeled "Login" that submits the email and password for authentication.</li>
</ul>


<h3> Behavior </h3>

<ul>
    <li><strong>Successful Login</strong>: If the user enters the correct email and password, they are authenticated and redirected to the app's main screen.</li>
    <li><strong>Failed Login</strong>: If the user enters incorrect login credentials, a small error message is displayed at the bottom of the screen, informing the user that their credentials are incorrect. The message helps guide the user to try again with the correct information.</li>
</ul>


<h3> Error Handling </h3>

<ul>
    <li><strong>Incorrect Credentials</strong>: When the login attempt fails due to incorrect email or password, a small, clear error message is shown to the user below the login fields, helping them understand the issue and try again.</li>
</ul>

</details>


## Overview-Screen

<details>
<summary> Overview-Screen </summary>

<p> In the following GIF, you can see the functional elements of the Overview Screen. It consists of the app scaffold with some shortcut icons below, which allow the user to quickly access certain pages. </p>

<figure>
    <img src="images/app/overview-screen/Overview-Darkmode.gif" width="40%">
    <figcaption>Overview-Screen in Dark-Mode</figcaption>
</figure>

<p> Below that is the Depot segment, which displays the current depot value. This value is fetched and updated every 3 seconds. </p>

<p> Next are the recent transactions that the user has either received or executed. The three most recent transactions are shown. </p>

<p> Finally, there is the Chart Segment, which accumulates all transactions for a given day and displays them as a bar chart. 
It shows the current day, 2 days in the past, and 2 days in the future, as there may already be transactions scheduled for those days. 
Transactions for the current day are always displayed in the center. </p>

<p> When you tap approximately in the middle of the chart, a red marker appears to indicate that it is the current day. </p>

<p> In the following, you can see the Overview Screen in White Mode. </p>

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

<figure>
    <img src="images/app/overview-screen/Overview-Whitemode-1.png" width="80%">
    <figcaption>Overview-Screen in White-Mode</figcaption>
</figure>

<figure>
    <img src="images/app/overview-screen/Overview-Whitemode-2.png" width="80%">
    <figcaption>Overview-Screen in White-Mode</figcaption>
</figure>
</div>

</details>



## Depot-Screen

<details>
<summary>Depot-Screen</summary>
</details>

## Transaction-Screen

<details>
<summary>Transaction-Screen</summary>

<h3>Overview</h3>

<p> The <strong>Transaction Overview</strong> screen in the <strong>AurumBanking</strong> app allows users to choose between different types of transactions. The interface is designed for ease of use, allowing users to perform standard, international, or permanent transactions. </p>

<div style="text-align: center;">

    <figure>
        <img src="images/app/transaction-screen/transaction-option-view.png" width="40%">
        <figcaption>Screenshot of the main transaction view</figcaption>
    </figure>

</div>

<h4>Transaction Options</h4>

<ul>
    <li><strong>Standard Transfer</strong>:  
        This option allows users to perform a regular domestic bank transfer.
    </li>
    <li><strong>International Transfer</strong>:  
        Users can choose this option to send money to an account in a different country.
    </li>
    <li><strong>Permanent Order</strong>:  
        This option lets users set up a permanent order, which will regularly send a specified amount of money to a designated account.
    </li>
</ul>

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-standart-transaction-screen.png" width="100%">
        <figcaption>Screenshot of the standard transaction screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-international-transaction-screen.png" width="100%">
        <figcaption>Screenshot of the international transaction screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-permanent-transaction.png" width="100%">
        <figcaption>Screenshot of the permanent transaction screen</figcaption>
    </figure>

</div>

<h3>Standard Transfer</h3>

<p> The <code>StandardTransferScreen</code> is specifically designed for domestic transfers. It offers a straightforward form where users can enter the necessary details to complete their transaction. </p>

<h4>Key Features</h4>

<ul>
    <li><strong>Recipient</strong>: Users must enter the name of the person or entity they are transferring money to.</li>
    <li><strong>IBAN</strong>: The International Bank Account Number (IBAN) of the recipient is required for the transaction.</li>
    <li><strong>BIC</strong>: The Bank Identifier Code (BIC) is another crucial piece of information for ensuring the transfer reaches the correct destination.</li>
    <li><strong>Amount</strong>: Users specify the amount they wish to transfer.</li>
    <li><strong>Purpose</strong>: An optional field where users can describe the reason for the transfer.</li>
    <li><strong>Execution Date</strong>: Users can select the date on which they want the transaction to be executed. This is particularly useful for scheduling future payments.</li>
</ul>

<h4>Validation and Error Handling</h4>

<ul>
    <li> The screen includes robust validation to ensure that all required fields are correctly filled out before the user can proceed.</li>
    <li> If a field is incorrectly filled or left blank, the user receives immediate feedback via highlighted fields and an error message.</li>
    <li> This validation process is crucial for preventing errors that could delay or fail the transaction.</li>
</ul>

<h4>Form Submission</h4>

<ul>
    <li> Once all required fields are validated, the user can submit the form to initiate the transfer.</li>
    <li> If the submission is successful, the user is redirected to a confirmation screen, affirming that their transaction is being processed.</li>
    <li> In case of a failure, a clear error message is displayed, allowing the user to understand what went wrong and take corrective action.</li>
</ul>

<h4>Edit and Review Mode</h4>

<ul>
    <li> Allows users to review and edit input details before final submission.</li>
</ul>


<h3>International Transfer</h3>

<p> The <code>International Transfer</code> screen is tailored for cross-border transactions, requiring additional input: </p>

<h4>Key Features</h4>

<ul>
    <li><strong>Country</strong>: Users must select the country to which they are transferring money.</li>
    <li><strong>Recipient</strong>: Users enter the name of the person or entity they are transferring money to.</li>
    <li><strong>IBAN</strong>: The International Bank Account Number (IBAN) of the recipient, which is crucial for the transaction.</li>
    <li><strong>BIC</strong>: The Bank Identifier Code (BIC) necessary to route the transfer to the correct bank.</li>
    <li><strong>Amount</strong>: Users specify the amount they wish to transfer.</li>
    <li><strong>Purpose</strong>: An optional field where users can describe the reason for the transfer.</li>
    <li><strong>Execution Date</strong>: The date on which the transaction is to be executed. This field is required and allows for future scheduling.</li>
</ul>

<h4>State Management</h4>

<ul>
    <li><strong>Editable State</strong>: The screen includes a toggle for editing mode, allowing users to either review or modify the details before confirming the transaction.</li>
    <li><strong>Error Handling</strong>: The <code>validateInput</code> function checks all fields for correctness, highlighting any issues directly in the UI by changing the border color of the problematic input fields.</li>
    <li><strong>Snackbar Notifications</strong>: If a user tries to submit the form with errors or if the transaction fails, a Snackbar message appears to inform the user.</li>
</ul>

<h4>Transaction Logic</h4>

<ul>
    <li><strong>Form Validation</strong>: Before submitting, the form is validated to ensure that all required fields are filled out. This is managed by checking the state of each input field and applying necessary validation rules.</li>
    <li><strong>ViewModel Interaction</strong>: The <code>TransferViewModel</code> handles the logic of creating and submitting a new transaction request. If the request is successful, the user is navigated to a success screen; otherwise, an error message is displayed via Snackbar.</li>
    <li><strong>Navigation</strong>: Upon successful validation and submission, the screen navigates the user to the <code>SuccessfulTransaction</code> route.</li>
</ul>


<h3>Permanent Order Transfer</h3>

<h4>Function Definition</h4>

<p> The <code>Permanent Order</code> screen allows users to set up recurring transactions. </p>

<h4>Key Features</h4>

<ul>
    <li><strong>Country</strong>: Stores the selected country for the transfer. Defaults to "Deutschland".</li>
    <li><strong>Recipient</strong>: Stores the name of the recipient of the transfer.</li>
    <li><strong>IBAN</strong>: Stores the IBAN (International Bank Account Number) of the recipient.</li>
    <li><strong>BIC</strong>: Stores the BIC (Bank Identifier Code) of the recipient's bank.</li>
    <li><strong>Amount</strong>: Stores the amount of money to be transferred.</li>
    <li><strong>Purpose</strong>: Stores the purpose or reference of the transfer.</li>
    <li><strong>First Execution Date</strong>: Stores the date for the first execution of the transfer.</li>
    <li><strong>Last Execution Date</strong>: Stores the date for the last execution of the transfer.</li>
    <li><strong>Transaction Classification</strong>: Stores the classification of the transaction, defaulted to "Dauerauftrag" (standing order).</li>
</ul>

<h4>State Management and Validation</h4>

<ul>
    <li><strong>isEditable</strong>: Controls whether the input fields are editable.</li>
    <li><strong>showSnackbar</strong>: Controls the visibility of the Snackbar for displaying messages.</li>
    <li><strong>snackbarMessage</strong>: Stores the message to be displayed in the Snackbar.</li>
    <li><strong>errorFields</strong>: Keeps track of the fields that have validation errors.</li>
</ul>

<h4>User Interface Elements</h4>

<ul>
    <li><strong>TransferInteractionScreenBar</strong>: A custom app bar that provides options to toggle edit mode and confirm the transaction.</li>
    <li><strong>OutlinedTextField</strong>: Used for input fields like recipient, country, IBAN, BIC, amount, and purpose.</li>
    <li><strong>DateChoiceButton</strong>: Custom button to select the first and last execution dates.</li>
    <li><strong>CreateBackOrEditButton</strong>: A button that either allows users to go back or toggle the edit mode.</li>
    <li><strong>CreateConfirmSendButton</strong>: A button that confirms the transaction and sends the data to the ViewModel for processing.</li>
</ul>


<h3>Handling Incomplete or Invalid Form Inputs</h3>

<p> In the transfer interaction screen, proper validation of user inputs is crucial to ensure that all required fields are completed and valid before proceeding. When a user attempts to submit the form with incomplete or invalid information, the system performs a series of checks. </p>

<h4>Process Overview</h4>

<ul>
    <li>Validation Check: The system checks all required fields to verify their completeness. This includes fields such as country, recipient, IBAN, BIC, amount, purpose, and execution dates.</li>
    <li>Error Notification: If any fields are empty or invalid, the user is notified through a snackbar message. This message typically prompts the user to correct the missing or incorrect information.</li>
    <li>UI Feedback: The interface provides visual feedback to guide the user in correcting the errors. This often involves highlighting the problematic fields or displaying specific error messages.</li>
</ul>


<h3>Screens in White Mode</h3>

<p> The app offers the option to switch to White Mode, providing a bright and clear user interface. </p>

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-standard-transaction-screen-whitemode.png" width="100%">
        <figcaption>Screenshot of the standard transaction screen in Whitemode</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-international-transaction-screen-check-entries-whitemode.png" width="100%">
        <figcaption>Screenshot of the international transaction screen in Whitemode</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/transaction-screen/transaction-permanent-transaction-screen-with-calendar-whitemode.png" width="100%">
        <figcaption>Screenshot of the permanent transaction screen in Whitemode</figcaption>
    </figure>

</div>

</details>


## Support-Screen

<details>
<summary>Support Screen </summary>

<h3> Support Option View </h3>

<div style="text-align: center;">

    <figure>
        <img src="images/app/support-screen/support_main_view.png" width="40%">
        <figcaption>Screenshot of the main support view</figcaption>
    </figure>

</div>

<h3> Support Option View </h3>

<p> The <strong>Support Option View</strong> in the AurumBanking app is designed to provide users with various options to resolve any issues or answer questions quickly and efficiently. </p>

<h4> Options: </h4>

<ol>
    <li><strong>Support Inquiry</strong>:  
        Tap the mail icon to send a direct support request.
    </li>
    <li><strong>Phone Support</strong>:  
        Users can choose to contact customer support by phone. Tapping opens the integrated telephone app on the used mobile device. The number is automatically inserted.
    </li>
    <li><strong>FAQ Section</strong>:  
        The information icon leads users to the frequently asked questions (FAQ) section.
    </li>
</ol>

<h3> Support Request View </h3>
<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view.png" width="100%">
        <figcaption>Screenshot of the support request screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view-success.png" width="100%">
        <figcaption>Screenshot of the error message</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/suppot-request-view-fail.png" width="100%">
        <figcaption>Screenshot of the success message</figcaption>
    </figure>

</div>

<h4> Support Request Screen </h4>

<p> This screen allows users to submit a support request by filling out the necessary information. The user can enter their legal name, the type of inquiry, their email address, and a message detailing their issue. </p>

<ul>
    <li><strong>Legal Name</strong>: The user's legal name, which is provided from the session manager.</li>
    <li><strong>Type of Inquiry</strong>: Users can choose from a dropdown-menu (e.g., Überweisung, Konto, Benutzerdaten).</li>
    <li><strong>Email Address</strong>: The user's contact email address, which also is provided.</li>
    <li><strong>Message</strong>: A text field where the user can enter their specific issue or question.</li>
</ul>


<p> At the bottom, there is a <strong>Send</strong> button that the user can tap to submit the support request. When the button is clicked, the user inputs get checked. For example the message field must be filled and the type must be selected.
At the top is a small menu bar with a check icon with the same functionality as the "senden" button </p> 

<h4> Support Request Submitted (Success) Screen </h4>

<p> Once the user successfully submits their support request, a confirmation popup appears to inform them that their request has been received. This dialog reassures the user that their inquiry is being processed. </p>

<ul>
    <li><strong>Confirmation Message</strong>: "Anfrage gesendet" — informs the user that the support request has been sent.</li>
    <li><strong>Details</strong>: The message also states that the request will be processed as quickly as possible.</li>
</ul>


<p> The user can tap <strong>OK</strong> to dismiss the popup and return to the app. </p>>

<h4> Support Request Error (Failure) Screen </h4>

If there are any issues with the support request submission (e.g., missing required fields), the user is shown an error popup. This message prompts the user to complete the missing information.

<ul>
    <li><strong>Error Message</strong>: "Fehler" — indicates that an error occurred during the submission process.</li>
    <li><strong>Details</strong>: The message encourages the user to "Bitte füllen Sie alle Felder korrekt aus." (Please fill out all fields correctly).</li>
    <li><strong>Sending Error</strong>: If there is an error from sending, there is a different error message to inform the user.</li>
</ul>


The user can tap <strong>OK</strong> to dismiss the error and return to the form to correct the mistakes.

<h3> Support Sub Screens </h3>

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/FAQ-Screen.png" width="100%">
        <figcaption>Screenshot of the FAQ screen</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-support-screen.png" width="100%">
        <figcaption>Screenshot of the telephone support</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-app-screen.png" width="100%">
        <figcaption>Screenshot of telephone app screen</figcaption>
    </figure>

</div>

<h4> FAQ Screen </h4>


<p> This screen provides users with a Frequently Asked Questions (FAQ) section, helping them resolve common issues and inquiries. Users can expand the questions to read answers about setting up their account, the security of the app, and how to transfer money to another account. </p>

<strong> Key Elements: </strong>

<ul>
    <li><strong>FAQ Cards</strong>: The screen displays collapsible cards for each FAQ topic.</li>
    <li><strong>Questions Addressed</strong>:
        <ul>
            <li>Setting up a new account in the app.</li>
            <li>Security measures to protect the account.</li>
            <li>Transferring money to another account.</li>
        </ul>
    </li>
</ul>


<h4> Telephone Support Screen </h4>


<p> This screen allows users to access AurumBanking's telephone support. The app provides the contact information, including the phone number and business hours (Monday to Friday, 8:00 - 20:00), enabling users to get direct support from customer service.
</p>

<ul>
    <li><strong>Key Elements</strong>:</li>
    <ul>
        <li><strong>Phone Number Display</strong>: The contact number for customer service.</li>
        <li><strong>Support Hours</strong>: Operating hours are clearly displayed for user reference.</li>
    </ul>
</ul>

<h4> Telephone App Screen </h4>


<p> This screen shows the user's telephone dialer with the AurumBanking support number pre-filled. From this screen, the user can directly call the support line, create a new contact, add the number to an existing contact, or send an SMS. </p>

<ul>
    <li><strong>Key Elements</strong>:</li>
    <ul>
        <li><strong>Dial Pad</strong>: The dialer pad with the pre-filled AurumBanking support number.</li>
        <li><strong>Options Menu</strong>: Additional options to create a new contact, add to an existing contact, or send an SMS.</li>
    </ul>
</ul>


<h3> Screens in White Mode </h3>

<div style="display: flex; justify-content: space-around; align-items: center; text-align: center;">

     <figure style="margin: 10px;">
        <img src="images/app/support-screen/support_main_view-whitemode.png" width="100%">
        <figcaption>Screenshot the support main view in whitemode</figcaption>
    </figure>

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/support-request-view-whitemode.png" width="100%">
        <figcaption>Screenshot of the support request screen in whitemode</figcaption>
    </figure>   

    <figure style="margin: 10px;">
        <img src="images/app/support-screen/telephone-support-screen-whitemode.png" width="100%">
        <figcaption>Screenshot of the telephone support in whitemode</figcaption>
    </figure>

</div>

</details>



## Settings-Screen

<details>
<summary> Settings Screen </summary>

<h3> Settings Option Screen in AurumBanking App </h3>

<div style="display: flex; justify-content: center;">

    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-option-screen.png" width="60%">
        <figcaption>Screenshot of the settings option screen</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-option-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of the settings option screen in white mode</figcaption>
    </figure>

</div>


<p>
    The <strong>Settings Option Screen</strong> in the <strong>AurumBanking</strong> app serves as the main hub for users to manage their personal settings. From this screen, the user can access various important functions related to their account and the app's appearance.
</p>

<h4> Overview of the Settings Option Screen </h4>

<p> Upon accessing the settings screen, the user is greeted by the app's mascot and a friendly prompt: **"Hier können Sie Einstellungen vornehmen!"**, which translates to "Here you can make settings!" in English. Below this prompt, the user is presented with three main options:
</p>

<ul>
    <li><strong>Personal Data (Persönliche Daten)</strong>: Allows the user to view and edit their personal information, such as their name, address, and contact details.</li>
    <li><strong>Change Password (Passwort ändern)</strong>: Provides a secure method for the user to change their account password.</li>
    <li><strong>Change Design (Design ändern)</strong>: Enables the user to switch between different design modes (e.g., light and dark mode) for the app's appearance.</li>
</ul>


<h4> Additional Notes </h4>

<ul>
    <li>This screen acts as a central location for managing account settings and appearance options within the app.</li>
    <li>The user can quickly access this screen by tapping the gear icon located in the top right corner of the main screen.</li>
</ul>


<h3> Change To Whitemode Screen </h3>
<div style="text-align: center;">

    <figure>
        <img src="images/app/settings-screen/settings-whitemode-screen-record.gif" width="40%">
        <figcaption>gif of changing to whitemode</figcaption>
    </figure>

</div>

<p>The <strong>AurumBanking</strong> app provides a setting option to change the visual design of the app. This allows the user to switch between different themes, such as a light or dark mode, depending on their preference.</p>

<h4>Overview of the White Mode Setting</h4>

<p>The white mode (or light mode) setting is accessible from the <strong>Settings</strong> screen within the AurumBanking app. Upon selecting this option, the user interface of the app transitions to a lighter color palette, making it easier to view in well-lit environments.</p>

<p>By selecting the <strong>Möchten Sie auf White-Mode/Dark-Mode wechseln</strong> option, the user can alter the appearance of the app. The app will ask for confirmation, and after clicking <strong>"verstanden"</strong>, the app will switch to the desired mode.</p>


<h3> Change Password Screen in AurumBanking App </h3>

<div style="display: flex; justify-content: center;">
    
    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-new-password-screen.png" width="60%">
        <figcaption>Screenshot of the new password screen</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-new-password-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of the new password screen in white mode</figcaption>
    </figure>
    
</div>

<h4> Change Password Screen in AurumBanking App </h4>

<p>The <strong>Change Password Screen</strong> in the <strong>AurumBanking</strong> app allows users to securely update their account password. This screen provides an intuitive and user-friendly interface for changing the password in three simple steps.</p>

<h4> Overview of the Change Password Screen </h4>

<p>Upon accessing this screen, the user is prompted with the message <strong>"Hier können Sie ihr Passwort ändern!"</strong>, which translates to "Here you can change your password!" in English.</p>

<p>The user is presented with three fields:</p>
<ul>
    <li><strong>Old Password (Altes Passwort)</strong>: The user enters their current password.</li>
    <li><strong>New Password (Neues Passwort)</strong>: The user enters a new password they would like to set.</li>
    <li><strong>Repeat New Password (Neues Passwort wiederholen)</strong>: The user repeats the new password for confirmation.</li>
</ul>

<p>At the bottom, the user can tap the <strong>"Bestätigen"</strong> (Confirm) button to complete the password change process.</p>

<h4> Additional Notes </h4>

<ul>
    <li>The change password process is designed to ensure security, requiring the user to input their old password before confirming the new one.</li>
    <li>This screen can be accessed from the <strong>Settings</strong> menu by selecting the <strong>Change Password</strong> option.</li>
</ul>



<h3> Personal Data Screen in AurumBanking App </h3>

<div style="display: flex; justify-content: center;">
    
    <figure style="margin-right: 20px;">
        <img src="images/app/settings-screen/settings-personal-data-screen.png" width="60%">
        <figcaption>Screenshot of settings personal data</figcaption>
    </figure>
    
    <figure>
        <img src="images/app/settings-screen/settings-personal-data-screen-whitemode.png" width="60%">
        <figcaption>Screenshot of settings personal data in whitemode</figcaption>
    </figure>
    
</div>



<p>The <strong>Personal Data Screen</strong> in the <strong>AurumBanking</strong> app allows the user to view their account information in a detailed and structured format. This screen provides essential details about the user's account, such as their legal name, address, contact information, and account number.</p>

<h4> Overview of the Personal Data Screen </h4>

<p>On this screen, the user can see their information displayed clearly under the following categories:</p>
<ul>
    <li><strong>Profile Picture</strong>: Profile Picture of the user.</li>
    <li><strong>Legal Name</strong>: The full legal name of the account holder.</li>
    <li><strong>Address</strong>: The account holder's address, including street, postal code, city, and country.</li>
    <li><strong>Phone Number</strong>: The user's contact phone number.</li>
    <li><strong>Email Address</strong>: The registered email associated with the user's account.</li>
    <li><strong>Account Number</strong>: The unique account number assigned to the user.</li>
</ul>


<h4> Additional Notes </h4>

<ul>
    <li>The user can access this screen by navigating through the <strong>Settings</strong> menu and selecting the <strong>Personal Data</strong> option.</li>
    <li>All the data shown here is private and secure, ensuring that only the account holder has access to their personal details.</li>
</ul>

</details>

